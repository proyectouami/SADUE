package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOApartados;
import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.datos.DAOProductosApartados;
import mx.uam.ayd.sadue.modelo.Apartado;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.modelo.ProductoApartado;
import mx.uam.ayd.sadue.vistas.VistaApartar;

public class ServicioApartar {
	ConexionDB conexion;
	private DAOApartados apartados;
	private DAOProductosApartados productosAp;
	private Apartado apartado;
	private ProductoApartado producto;
	private Producto produc;
	private DAOProductos pr;
	private ArrayList<String> apartadoArreglo = new ArrayList<String>(8);
	private ArrayList<String> productoArreglo = new ArrayList<String>(4);
	
	public ServicioApartar(DAOApartados ap,ConexionDB cone){
		conexion=cone;
		apartados = ap;
	}
	
	//Inicia la vista
	public void inicia(){
		System.out.println("Metodo que inicia la VistaSistemaApartado");
		VistaApartar ventanaAp = new VistaApartar(this,conexion);
		ventanaAp.setVisible(true);
	}
	
	//Lista apartados realizados
	public Apartado[] listaApartados(){
		return apartados.dameApartados();
	}
	
	//Calcula el total a pagar
	public double calculaPagoTotal(double[] totales){
		double total = 0;
		for(int i=0; i < totales.length; i++){
			total += totales[i];
		}
		
		return total;
	}
	
	//Realiza el apartado de productos
	public void realizarApartado(int cero, String apart, String[] prod){
		String s;
		StringTokenizer st = new StringTokenizer(apart, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			apartadoArreglo.add(s);
		}
		//C�lculo de cuenta y deuda
		double cuenta;
		double deuda;
		cuenta = (Double.parseDouble(apartadoArreglo.get(8))*(.30));
		cuenta = Math.round(cuenta*Math.pow(10, 2))/Math.pow(10, 2);
		deuda = (Double.parseDouble(apartadoArreglo.get(8))*(.70));
		deuda = Math.round(deuda*Math.pow(10, 2))/Math.pow(10, 2);
		String cuenta1 = String.valueOf(cuenta);
		String deuda1 = String.valueOf(deuda);
		//se crea un objeto de tipo apartado
		apartado = new Apartado(Integer.parseInt(apartadoArreglo.get(0)),String.valueOf(apartadoArreglo.get(1)), String.valueOf(apartadoArreglo.get(2)),
				String.valueOf(apartadoArreglo.get(3)), String.valueOf(apartadoArreglo.get(4)), Long.parseLong(apartadoArreglo.get(5)),
				Long.parseLong(apartadoArreglo.get(6)), String.valueOf(apartadoArreglo.get(7)), Double.parseDouble(apartadoArreglo.get(8)),
				cuenta1, deuda1);
		apartados = new DAOApartados(conexion);
		//se genera el apartado en la base de datos
		apartados.agregaApartado(apartado);
		
		//Separa el arreglo de la cadena productos
		String p;
		String nombre = String.valueOf(apartadoArreglo.get(1));
		long tarjeta = Long.parseLong(apartadoArreglo.get(6));
		double total = Double.parseDouble(apartadoArreglo.get(8));
		for(int i=0; i < prod.length;i ++){
			StringTokenizer st2 = new StringTokenizer(prod[i], ",");
			while(st2.hasMoreTokens()){
				p = st2.nextToken();
				productoArreglo.add(p);
			}
			//se crea un objeto de tipo ProductoApartado
			//el par�metro apartados.dameId(nombre, tarjeta, total) obtiene el id
			//correspondiente al apartado realizado en ese momento
			//ese par�metro asocia al arreglo de productos con el apartado
			producto = new ProductoApartado(apartados.dameId(nombre, tarjeta, total), Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)),
					Double.parseDouble(productoArreglo.get(4)),	String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), Integer.parseInt((productoArreglo.get(5))));
			productosAp = new DAOProductosApartados(conexion);
			//se agregan los productos a la base de datos
			productosAp.agregaProducto(producto, apartado);
			//Actualiza existencias de cada producto apartado
			pr = new DAOProductos(conexion);
			int exististencia;
			exististencia = pr.dameExistencia(Integer.parseInt(productoArreglo.get(0)), String.valueOf(productoArreglo.get(2)), String.valueOf(productoArreglo.get(3)));
			
			produc = new Producto(Integer.parseInt(productoArreglo.get(0)),String.valueOf(productoArreglo.get(1)),
					Double.parseDouble(productoArreglo.get(4)),	String.valueOf(productoArreglo.get(3)), String.valueOf(productoArreglo.get(2)), exististencia);
			pr.actualizaProducto(produc, Integer.parseInt((productoArreglo.get(5))));
			productoArreglo.clear();
		}
	}
	
	//Regresa el apartadoId del registro de apartado generado para asociarlo al arreglo de productos
	public int dameIdApartado(String nombre, long tarjeta, double total){
		int id;
		id = apartados.dameId(nombre, tarjeta, total);
		return id;
	}
	
	//realiza descuento en modulo apartado de uniformes
		
		public double realizaDescuentoApartado(String descuento, double total){
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
