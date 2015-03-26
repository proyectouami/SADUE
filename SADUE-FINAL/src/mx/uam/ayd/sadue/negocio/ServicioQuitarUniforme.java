package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.vistas.VistaQuitarUniforme;
import mx.uam.ayd.sadue.datos.*;

public class ServicioQuitarUniforme {
	
	private DAOProductos p = new DAOProductos();
	private DAOEscuelas escuelas = new DAOEscuelas();
	private Producto producto;
	private ArrayList<String> productoArreglo = new ArrayList<String>(4);
	
	public ServicioQuitarUniforme(DAOProductos p) {
		// TODO Auto-generated constructor stub
		this.p = p;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaQuitarUniforme");
		VistaQuitarUniforme ventana = new VistaQuitarUniforme(this);
		ventana.setVisible(true);
	}
	
	//En este metodo se elimina el uniforme seleccionado del DAOProductos
	public boolean quitarProducto(String prodl){
		
		String s;
		StringTokenizer st = new StringTokenizer(prodl, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			productoArreglo.add(s);
		}
		/*for(int i=0; i < productoArreglo.size(); i++){
			//{"0 Pants", "1 Playera", "2 Sudadera", "3 Short", "4 Camisa", "5 Pantalon", "6 Sueter", "7 Blusa", "8 Jumper", "Calcetas"}
			if(productoArreglo.get(i).equalsIgnoreCase("Pants") || productoArreglo.get(i).equalsIgnoreCase("Playera")
					|| productoArreglo.get(i).equalsIgnoreCase("Sudadera")|| productoArreglo.get(i).equals("short")
					|| productoArreglo.get(i).equals("Camisa") || productoArreglo.get(i).equals("Pantalon")
					|| productoArreglo.get(i).equals("Sueter") || productoArreglo.get(i).equals("blusa")
					|| productoArreglo.get(i).equals("Jumper") || productoArreglo.get(i).equals("Calcetas")){
				productoArreglo.remove(i);
				i--;
			}
		}*/
		
		producto = new Producto(Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)), Double.parseDouble(productoArreglo.get(2)),
				String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(4)), Integer.parseInt((productoArreglo.get(5))));
		p = new DAOProductos();
		if(p.quitaProducto(producto))
			return true;
		else
			return false;
	}
	
	public Producto[] listaUniformes(){
		return p.dameProductos();
	}
	
	public Escuela[] buscarEscuela(){
		return escuelas.dameEscuelas();
	}
	
	public Producto[] listaUniformes(String escuela){
		return p.dameProductos(escuela);
	}

}
