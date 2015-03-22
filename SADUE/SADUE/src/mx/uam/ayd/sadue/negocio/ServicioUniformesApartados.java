package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOApartados;
import mx.uam.ayd.sadue.datos.DAOProductosApartados;
import mx.uam.ayd.sadue.modelo.ProductoApartado;
import mx.uam.ayd.sadue.vistas.DialogoUniformesApartados;

public class ServicioUniformesApartados {
	ConexionDB conexion;
	private DAOApartados apartados;
	private DAOProductosApartados productos = new DAOProductosApartados(conexion);
	private int id;
	
	public ServicioUniformesApartados(DAOApartados a,ConexionDB cone) {
		// TODO Auto-generated constructor stub
		conexion=cone;
		this.apartados = a;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia DialogoUniformesApartados");
		DialogoUniformesApartados ventana = new DialogoUniformesApartados(this);
		ventana.setVisible(true);
	}
	
	public ProductoApartado[] listaUniformes(){
		return productos.dameProductos(id);
	}
	
	public void recibeId(int id){
		this.id = id;
	}
	
}
