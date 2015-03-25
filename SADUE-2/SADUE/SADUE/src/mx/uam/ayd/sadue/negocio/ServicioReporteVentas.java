package mx.uam.ayd.sadue.negocio;


import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOApartados;
import mx.uam.ayd.sadue.datos.DAOProductosVentidos;
import mx.uam.ayd.sadue.datos.DAOVenta;
import mx.uam.ayd.sadue.modelo.Apartado;
import mx.uam.ayd.sadue.modelo.Venta;
import mx.uam.ayd.sadue.modelo.ProductoVentido;
import mx.uam.ayd.sadue.modelo.Venta;
import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.vistas.VistaConsultarAlmacen;
import mx.uam.ayd.sadue.vistas.VistaReporteVentas;

public class ServicioReporteVentas {
	ConexionDB conexion;
	private DAOProductosVentidos productosv = new DAOProductosVentidos(conexion);
	private ProductoVentido productov;
	private ArrayList<String> apartadoArreglo = new ArrayList<String>(4);
	
	//DAOProductosVentidos daoProductosV;
	
	public ServicioReporteVentas(DAOProductosVentidos v,ConexionDB cone){
		conexion=cone;
		productosv = v;
	}
	
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaReporteVentas");
		VistaReporteVentas vRV = new VistaReporteVentas(this);
		vRV.setVisible(true);
	
	}
	
	public ProductoVentido[] listaProductosv(){
		
		return productosv.dameProductos();
	}
	

}
