package mx.uam.ayd.sadue.modelo;

public class ProductoApartado {

	int idApartado;
	int idProducto;
	String nombreProducto;
	double precio;
	String talla;
	int cantidad;
	String escuela;
	
	public ProductoApartado(int idApart, int idProd, String nomProd, double prec, String t, String esc, int cant){
		idApartado = idApart;
		idProducto = idProd;
		nombreProducto = nomProd;
		precio = prec;
		talla = t;
		escuela = esc;
		cantidad = cant;
	}

	public int dameIdApartado() {
		return idApartado;
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
	
	public int dameCantidad(){
		return cantidad;
	}
	
	public String dameNombreProducto(){
		return nombreProducto;
	}
	
	public String toString() {
		return idProducto + "," + nombreProducto + "," + precio + "," + talla + "," + escuela + "," + cantidad;
	}
	
}
