package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.vistas.VistaConsultarAlmacen;

public class ServicioConsultarAlmacen {
	ConexionDB conexion;
	DAOProductos daoProductos;
	
	public ServicioConsultarAlmacen(DAOProductos p,ConexionDB cone){
		conexion=cone;
		daoProductos = p;
	}

	public void inicia(){
		VistaConsultarAlmacen vCA = new VistaConsultarAlmacen(this);
		vCA.setVisible(true);
	}
	
	public Producto[] listaUniformes(){
		return daoProductos.dameProductos();
	}

	public Producto listaTemporalUniformes(String lista, int cantidad){
		String s;
		ArrayList<String> productoArreglo = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(lista, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			productoArreglo.add(s);
		}
		
		Producto producto = new Producto(Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)), Double.parseDouble(productoArreglo.get(4)),
				String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), cantidad);
		

		return producto;
	}
	public void actualizarProducto(String lista, int cantidad){
		String s;
		ArrayList<String> productoArreglo = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(lista, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			productoArreglo.add(s);
		}
		
		Producto producto = new Producto(Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)), Double.parseDouble(productoArreglo.get(4)),
				String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), cantidad);
		daoProductos.actualizaProducto(producto,  -Integer.parseInt((productoArreglo.get(5))));

	}
	
	public void cambiarPrecio(int id, String uniforme, String escuela, String talla, int cantidad){
		System.out.println("Inicia clase \"Apartar\"");
		ServicioCambioPrecio servicioCp = new ServicioCambioPrecio(daoProductos,conexion);
		servicioCp.recibeId(id, uniforme, escuela, talla, cantidad);
		servicioCp.inicia();
	}

}
