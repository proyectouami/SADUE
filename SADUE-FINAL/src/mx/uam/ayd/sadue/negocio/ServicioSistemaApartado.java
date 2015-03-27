package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOApartados;
import mx.uam.ayd.sadue.vistas.VistaSistemaApartado;

public class ServicioSistemaApartado {
	ConexionDB conexion;
	private DAOApartados ap = new DAOApartados(conexion);
	
	public ServicioSistemaApartado(ConexionDB cone){
		conexion=cone;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaSistemaApartado");
		VistaSistemaApartado ventana = new VistaSistemaApartado(this);
		ventana.setVisible(true);
	}
	
	public void apartar(){
		System.out.println("Inicia clase \"Apartar\"");
		ServicioApartar servicioAp = new ServicioApartar(ap,conexion);
		servicioAp.inicia();
	}
	
	public void apartados(){
		System.out.println("Inicia clase \"Apartados\"");
		ServicioApartados servicioAp = new ServicioApartados(ap,conexion);
		servicioAp.inicia();
	}
	/*
	public void reporteApartados(){
		System.out.println("Inicia clase \"Reporte Apartados por Periodo\"");
	}
	*/
}
