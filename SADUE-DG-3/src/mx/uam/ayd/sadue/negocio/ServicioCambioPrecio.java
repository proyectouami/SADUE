package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.vistas.VistaCambioPrecio;

public class ServicioCambioPrecio {
	ConexionDB conexion;
	DAOProductos p;
	private Producto producto;
	private int id;
	private String uniforme;
	private String escuela;
	private String talla;
	private int cantidad;
	
	public ServicioCambioPrecio(DAOProductos dao,ConexionDB cone) {
		// TODO Auto-generated constructor stub
		conexion=cone;
		p = dao;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaCambiarPrecio");
		VistaCambioPrecio ventana = new VistaCambioPrecio(this);
		ventana.setVisible(true);
	}
	
	public void recibeId(int id, String uniforme, String escuela, String talla, int cantidad){
		this.id = id;
		this.uniforme = uniforme;
		this.escuela = escuela;
		this.talla = talla;
		this.cantidad = cantidad;
	}
	
	public boolean cambiaPrecio(double precio){
		
		//se crea un objeto producto
		producto = new Producto(id, uniforme, precio, talla, escuela, cantidad);
		p = new DAOProductos(conexion);
		if(p.cambiaPrecio(producto))
			return true;
		else
			return false;
	}

}
