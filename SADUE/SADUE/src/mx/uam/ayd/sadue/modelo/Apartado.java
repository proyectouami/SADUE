package mx.uam.ayd.sadue.modelo;

public class Apartado {
	
	private String nomCliente;
	private String apellCliente;
	private String dirCliente;
	private String emailCliente;
	private double totalPagar;
	private long telCliente;
	private long noTarjeta;
	private String fecha;
	private int idApartado;
	private String aCuenta;
	private String deuda;
	
	public Apartado(int idApartado, String nomCliente, String apellCliente, String dirCliente, String emailCliente, long telCliente, long noTarjeta,
			String fecha, double totalPago, String aCta, String debe) {
		// TODO Auto-generated constructor stub
		this.idApartado = idApartado;
		this.nomCliente = nomCliente;
		this.apellCliente = apellCliente;
		this.dirCliente = dirCliente;
		this.emailCliente = emailCliente;
		this.telCliente = telCliente;
		this.noTarjeta = noTarjeta;
		this.totalPagar = totalPago;
		this.fecha = fecha;
		aCuenta = aCta;
		deuda = debe;
	}
	
	public String dameNombreCliente(){
		return nomCliente;
	}
	
	public String dameApellidoCliente(){
		return apellCliente;
	}
	
	public String dameDireccionCliente(){
		return dirCliente;
	}
	
	public String dameEmailCliente(){
		return emailCliente;
	}
	
	public long dameTelCliente(){
		return telCliente;
	}
	
	public long dameNoTarjetaCliente(){
		return noTarjeta;
	}
	
	public double damePagoTotal(){
		return totalPagar;
	}
	
	public String dameFecha(){
		return fecha;
	}
	
	public int dameId(){
		return idApartado;
	}
	
	public String toString() {
		return "<html> " + "Cliente: " + nomCliente + " " + apellCliente + " <br>  Direccion: " + dirCliente + " <br>  Telefono: " + telCliente
				+ " <br>  E-mail: " + emailCliente + " <br>  No. de Tarjeta: " + noTarjeta + " <br>  Fecha de Apartado: " + fecha
				+ " <br>  No. de Apartado: " + idApartado + " </html>";
	}
	
	public void cambiaId(int id){
		idApartado = id;
	}
	
	public String dameCuenta(){
		return aCuenta;
	}
	
	public String dameDeuda(){
		return deuda;
	}

}
