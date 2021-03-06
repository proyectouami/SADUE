package mx.uam.ayd.sadue;

import javax.swing.UIManager;

import mx.uam.ayd.sadue.vistas.*;
import mx.uam.ayd.sadue.datos.*;
import mx.uam.ayd.sadue.negocio.*;

public class Aplicacion {
	ConexionDB conexion= new ConexionDB();
	DAOProductos p = new DAOProductos(conexion);
	DAOUsuarios u = new DAOUsuarios(conexion);
	DAOEscuelas e = new DAOEscuelas(conexion);
	DAOVenta v = new DAOVenta(conexion);
	DAOProductosVentidos pv=new DAOProductosVentidos(conexion);
	boolean valida;
	public static void main(String[] args) {
		
		Aplicacion app = new Aplicacion();
		
		//Cambiamos el Look and Feel de las ventanas
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Inicia la aplicacion
		app.inicia();
	}
	
	//Este metodo inicializa la aplicacion
	public void principal() {
		System.out.println("Metodo inicia de la clase Principal");
		VistaPrincipal ventana = new VistaPrincipal(this);
		ventana.setVisible(true); // En este momento aparece la ventana principal
	}
	
	//Este metodo inicializa la ventana principal
	public void inicia(){
		valida=conexion.crearConexion();
		if(valida==true){
			System.out.println("Metodo inicia \"Ingresar Sistema\"");
			ServicioIngresarSistema servicioIngresarSistema = new ServicioIngresarSistema(u);
			servicioIngresarSistema.inicia(); // En este momento aparece la ventana de ingreso al sistema
		}else{
			System.exit(0);
		}
		
	}

	//Este metodo inicializa una Nueva Venta
		public void nuevaVenta(){
			System.out.println("Metodo que inicia \"Nueva Venta\"");
			ServicioNuevaVenta servicioNuevaVenta = new ServicioNuevaVenta(v,conexion);
			servicioNuevaVenta.inicia();
		}
	
	//Este metodo inicializa el Sistema de Apartado
	public void sistemaApartado(){
		System.out.println("Metodo que inicia \"Sistema de Apartado\"");
		ServicioSistemaApartado servicioSistemaApartado = new ServicioSistemaApartado(conexion);
		servicioSistemaApartado.inicia();
	}
	
	
	public void reporteVentas(){
		System.out.println("Metodo que inicia \"Reporte de Ventas por Dia\"");
		ServicioReporteVentas Reporte = new ServicioReporteVentas(pv,conexion);
		Reporte.inicia();
	}
	
	/*
	//Este metodo inicializa Reporte de Ventas por Periodo
	public void reportePeriodo(){
		System.out.println("Metodo que inicia \"Reporte de Ventas por Periodo\"");
		ServicioReportePeriodo servicioReportePeriodo = new ServicioReportePeriodo();
		servicioReportePeriodo.inicia();
	}*/
	
	//Este metodo inicializa Agregar Uniformes
	public void agregarUniforme(){
		System.out.println("Metodo que inicia \"Agregar Uniformes\"");
		ServicioAgregarUniforme servicioAgregarUniforme = new ServicioAgregarUniforme(p,conexion);
		servicioAgregarUniforme.inicia();
	}
	
	//Este metodo inicializa Quitar Uniformes
	public void quitarUniforme(){
		System.out.println("Metodo que inicia \"Quitar Uniformes\"");
		ServicioQuitarUniforme servicioQuitarUniforme = new ServicioQuitarUniforme(p,conexion);
		servicioQuitarUniforme.inicia();
	}

	//Este metodo inicializa Consultar Inventario
	public void consultarInventario(){
		System.out.println("Metodo que inicia \"Consultar Inventario\"");
		ServicioConsultarAlmacen servicioConsultarInventario = new ServicioConsultarAlmacen(p,conexion);
		servicioConsultarInventario.inicia();
	}
	
	//Este metodo muestra la ventana Acerca de SADUE
	public void acercaDe(){
		System.out.println("Metodo que muestra ventana \"Acerca de SADUE\"");
		DialogoAcercaDe dialogo = new DialogoAcercaDe();
		dialogo.setVisible(true);
	}
	
	public void altaUsuario(){
		System.out.println("Metodo que muestra ventana \"Alta de Usuario\"");
		ServicioAltaUsuario servicioAltaUsuario = new ServicioAltaUsuario(u);
		servicioAltaUsuario.inicia();
	}
	
	//Este metodo permite salir del sistema
	public void salir(){
		System.out.println("Metodo que cierra el sistema");
		DialogoSalirSistema dialogo = new DialogoSalirSistema();
		dialogo.setVisible(true);
	}
	
	public void cerrarSesion(){
		System.out.println("Metodo que cierra sesion");
		DialogoCierraSesion dialogo = new DialogoCierraSesion();
		dialogo.setVisible(true);
	}
	
	public void dialogoNoAcceso(){
		System.out.println("Metodo que muestra dialogo \"No Acesso\"");
		DialogoNoAcceso dialogo = new DialogoNoAcceso();
		dialogo.setVisible(true);
	}
	
	public void dialogoUsuarios(){
		System.out.println("Metodo que muestra dialogo \"Lista Usuarios\"");
		ServicioListaUsuarios servicioListaUsuarios = new ServicioListaUsuarios(u,conexion);
		servicioListaUsuarios.inicia();
	}
	
	public void altaEscuela(){
		System.out.println("Metodo que muestra ventana \"Alta de Escuelas\"");
		ServicioAltaEscuela servicioAltaEscuela = new ServicioAltaEscuela(e);
		servicioAltaEscuela.inicia();
	}
	
	public void dialogoEscuelas(){
		System.out.println("Metodo que muestra dialogo \"Lista Escuelas\"");
		ServicioListaEscuelas servicioListaEscuelas = new ServicioListaEscuelas(e,conexion);
		servicioListaEscuelas.inicia();
	}
	
	public void cambiarContrasenia(){
		System.out.println("Metodo que muestra ventana \"Alta de Usuario\"");
		ServicioCambiarContrasenia servicioCambiarContrasenia = new ServicioCambiarContrasenia(u);
		servicioCambiarContrasenia.inicia();
	}
	
}
