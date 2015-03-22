package mx.uam.ayd.sadue.modelo;


public class Venta{
	
	String fechaInicio;
	int tipoPago;
	String nombreCliente;
	String apellidoCliente;
	double pagoTotal;
	
	public Venta(String nomCliente, String apellCliente, String fechIni, int tipPago, double total){
		nombreCliente = nomCliente;
		apellidoCliente = apellCliente;
		fechaInicio = fechIni;
		tipoPago = tipPago;
		pagoTotal = total;
	}
	
	public double damePagoTotal() {
		return pagoTotal;
	}

	public String dameNombreCliente() {
		return nombreCliente;
	}



	public String dameApellidoCliente() {
		return apellidoCliente;
	}



	public String dameFechaInicio() {
		return fechaInicio;
	}

	public int dameTipoPago() {
		return tipoPago;
	}

	public void cambiaId(int llave) {
		// TODO Auto-generated method stub
		
	}
	
	public String toString() {
		return "<html> " + "Cliente: " + nombreCliente + " " + apellidoCliente +"Fecha: " + fechaInicio +"tipo Pago: "+ tipoPago +  "</html>";
	}

}

