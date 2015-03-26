package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.DAOApartados;
import mx.uam.ayd.sadue.datos.DAOEscuelas;
import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.vistas.VistaSeleccionUniformesApartar;

public class ServicioSeleccionUniformesApartar {
	
	private DAOApartados apartados = new DAOApartados();
	private DAOProductos productos = new DAOProductos();
	private DAOEscuelas escuelas = new DAOEscuelas();
	private Producto[] listaTemporal;
	private Producto producto;
	
	public ServicioSeleccionUniformesApartar(DAOApartados a) {
		// TODO Auto-generated constructor stub
		this.apartados = a;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaSeleccionUniformesApartar");
		VistaSeleccionUniformesApartar ventana = new VistaSeleccionUniformesApartar(this);
		ventana.setVisible(true);
	}
	
	public Producto[] listaUniformes(String escuela){
		return productos.dameProductos(escuela);
	}
	
	public Escuela[] buscarEscuela(){
		return escuelas.dameEscuelas();
	}
	
	public Producto listaTemporalUniformes(String lista, int cantidad){
		String s;
		ArrayList<String> productoArreglo = new ArrayList<String>();
		
		StringTokenizer st = new StringTokenizer(lista, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			productoArreglo.add(s);
		}
		
		producto = new Producto(Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)), Double.parseDouble(productoArreglo.get(4)),
				String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), cantidad);
		
		return producto;
	}
}
