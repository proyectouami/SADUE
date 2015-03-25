package mx.uam.ayd.sadue.modelo;

public class Producto {
	
	int idProducto;
	String nombreProducto;
	double precio;
	String talla;
	int existencia;
	String escuela;
	
	public Producto(int idProd, String nomProd, double prec, String t, String esc, int exist){
		idProducto = idProd;
		nombreProducto = nomProd;
		precio = prec;
		talla = t;
		escuela = esc;
		existencia = exist;
	}

	public int dameIdProducto() {
		return idProducto;
	}

	public double damePrecio() {
		return precio;
	}

	public void cambiaId(int id){
		idProducto = id;
	}
	
	public String dameTalla(){
		return talla;
	}
	
	public String dameEscuela(){
		return escuela;
	}
	
	public int dameExistencia(){
		return existencia;
	}
	
	public String dameNombreProducto(){
		return nombreProducto;
	}
	
	public String toString() {
		return idProducto + "," + nombreProducto + "," + precio + "," + talla + "," + escuela + "," + existencia;
	}
}
