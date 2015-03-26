package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Venta;

public class DAOVenta {

	/**
	 * Constructor de la clase
	 */
	public DAOVenta() {

	}

	/**
	 * Permite agregar un material a la Venta
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaVenta(Venta venta) {

		int llave;

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave 
			statement.execute("insert into Ventas values (DEFAULT,'"+venta.dameNombreCliente()+"','"+venta.dameApellidoCliente()+"','"+venta.dameFechaInicio()+"',"+venta.dameTipoPago()+","+venta.damePagoTotal()+")",Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			if (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    venta.cambiaId(llave); // Asigna la llave al producto
			    System.out.println("venta agregada");
			}
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permite quitar un material a la librer’a
	 * @return true si el material se quito exitosamente, false sino
	 */
/*	public boolean quitaProducto(Producto producto) {

		int resultado = 0;

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			resultado = statement.executeUpdate("DELETE FROM Productos WHERE nombre='"+producto.getNombreProducto()+"' AND idProducto='"+producto.getIdProducto()+"'");

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(resultado == 0) {
			return false;
		} else {
			return true;
		}

	}*/

	public Venta buscaVenta(String fecha, int claveVenta) {

		Venta venta = null;

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Ventas WHERE fecha='"+fecha+"' AND claveVenta='"+claveVenta+"'");

			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				venta = new Venta(rs.getString("nombreCliente"),rs.getString("ApelliidoCliente"), rs.getString("fechaInicio"), rs.getInt("tipoPago"),rs.getDouble("pagoTotal"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venta;

	}

	public Venta buscaVenta(int claveVenta) {

		Venta venta = null;

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Ventas WHERE ventaId="+claveVenta);

			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				venta = new Venta(rs.getString("nombreCliente"),rs.getString("ApelliidoCliente"), rs.getString("fechaInicio"), rs.getInt("tipoPago"),rs.getDouble("pagoTotal"));
				venta.cambiaId(claveVenta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return venta;

	}

	/**
	 * Regresa los materiales de la libreria como un arreglo de materiales
	 * @return el arreglo de material
	 */
	public Venta[] dameVentas(String fechInicio, String fechFin) {

		ArrayList<Venta> ventasTemp = new ArrayList<Venta>();

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT * FROM Ventas WHERE fechaInicio= "+fechInicio+"AND fechaFin= "+fechFin);

			while(rs.next())
			{
				// Crea una nueva instancia del objeto
				Venta venta = new Venta(rs.getString("nombreCliente"),rs.getString("ApelliidoCliente"), rs.getString("fechaInicio"),rs.getInt("tipoPago"),rs.getDouble("pagoTotal"));
				ventasTemp.add(venta);
			}

			Venta ventasTempArreglo[]=new Venta[ventasTemp.size()];
			ventasTemp.toArray(ventasTempArreglo);
			return ventasTempArreglo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
public int dameId(String nombre,  double pago){
		
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT apartadoId FROM Apartados WHERE nombreCliente='" + nombre + "'AND totalPagar=" + pago);
			if (rs.next()) {
		        return rs.getInt(1);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
	}


	/**
	 * Regresa numero de materiales en la libreria
	 * @return un entero con el numero de materiales
	 */
	public int cuantasVentas(String fechInicio, String fechFin) {
		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Ventas WHERE fechaInicio= "+fechInicio+"AND fechaFin= "+fechFin);
			if (rs.next()) {
		        return rs.getInt(1);
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}


