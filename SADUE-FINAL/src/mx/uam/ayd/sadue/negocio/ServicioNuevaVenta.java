package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.DAOEscuelas;
import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.datos.DAOProductosVentidos;
import mx.uam.ayd.sadue.datos.DAOVenta;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.modelo.ProductoVentido;
import mx.uam.ayd.sadue.modelo.Venta;
import mx.uam.ayd.sadue.vistas.VistaNuevaVenta;

public class ServicioNuevaVenta {
	DAOVenta daoVenta;
	DAOEscuelas daoEscuela = new DAOEscuelas();
	DAOProductos daoProductos = new DAOProductos();
	private ArrayList<String> ventaArreglo = new ArrayList<String>();
	private ArrayList<String> productoArreglo = new ArrayList<String>();
	DAOProductosVentidos daoVentidos = new DAOProductosVentidos();

	Producto producto;
	Venta venta;

	
	public ServicioNuevaVenta(DAOVenta daoVent) {
		daoVenta = daoVent;
	}
	
	public ServicioNuevaVenta() {
	
	}
	
	public void inicia(){
		VistaNuevaVenta vnVenta = new VistaNuevaVenta(this);
		vnVenta.setVisible(true);
	}
	
	public static double calculaPagoTotal(double[] totales){
		double total = 0;
		for(int i=0; i < totales.length; i++){
			total += totales[i];
		}
		
		return total;
	}

	public void agregarVenta(int cero,String pVenta, String[] prod){
		String s;
		StringTokenizer st = new StringTokenizer(pVenta, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			ventaArreglo.add(s);
		}
		
		venta = new Venta(String.valueOf(ventaArreglo.get(0)),String.valueOf(ventaArreglo.get(1)),String.valueOf(ventaArreglo.get(2)),
							Integer.parseInt(ventaArreglo.get(3)),Double.parseDouble(ventaArreglo.get(4)));
		daoVenta = new DAOVenta();
		daoVenta.agregaVenta(venta);
		
		String p;
		String nombre = String.valueOf(ventaArreglo.get(1));
		//long tarjeta = Long.parseLong(ventaArreglo.get(5));
		double total = Double.parseDouble(ventaArreglo.get(4));
		for(int i=0; i < prod.length;i ++){
			StringTokenizer st2 = new StringTokenizer(prod[i], ",");
			while(st2.hasMoreTokens()){
				p = st2.nextToken();
				productoArreglo.add(p);
			}
			ProductoVentido producto = new ProductoVentido(daoVenta.dameId(nombre, total), Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)),
					Double.parseDouble(productoArreglo.get(4)),	String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), Integer.parseInt((productoArreglo.get(5))));
			daoVentidos.agregaProducto(producto);
		}
		
		daoProductos = new DAOProductos();
		int exististencia;
		exististencia = daoProductos.dameExistencia(Integer.parseInt(productoArreglo.get(0)), String.valueOf(productoArreglo.get(2)), String.valueOf(productoArreglo.get(3)));
		
		producto = new Producto(Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)),
				Double.parseDouble(productoArreglo.get(4)),	String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), exististencia);
		daoProductos.actualizaProducto(producto, Integer.parseInt((productoArreglo.get(5))));

	}
	
	//Metodo que realiza desuento
	
	public double realizaDescNVenta(String descuento, double total){
		double totalcdesc=-1;
		double aux=-1;
		double desc=-1;
		
		desc=Double.parseDouble(descuento);
		System.out.println("realize cast desc"+descuento);
		aux=total*(desc/100);
		System.out.println("realize calculo desc"+aux);
		totalcdesc=total-aux;
		System.out.println("realize calculo total a pagar"+totalcdesc);
		
		return totalcdesc;
	}

}