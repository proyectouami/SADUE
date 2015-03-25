package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Venta;

public class DAOVenta {
	ConexionDB conexion;
	/**
	 * Constructor de la clase
	 */
	public DAOVenta(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la Venta
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaVenta(Venta venta) {
		ResultSet rs;
		String query;
		int llave=-1;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave 
			query="insert into Ventas values (DEFAULT,'"+venta.dameNombreCliente()+"','"+venta.dameApellidoCliente()+"','"+venta.dameFechaInicio()+"',"+venta.dameTipoPago()+","+venta.damePagoTotal()+")";
			//statement.execute("insert into Ventas values (DEFAULT,'"+venta.dameNombreCliente()+"','"+venta.dameApellidoCliente()+"','"+venta.dameFechaInicio()+"',"+venta.dameTipoPago()+","+venta.damePagoTotal()+")",Statement.RETURN_GENERATED_KEYS);
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			conexion.ejecutarSQL(query);
			query="select * from Ventas";
			rs=conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			venta.cambiaId(llave); // Asigna la llave al producto
		    System.out.println("venta agregada");
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
		ResultSet rs;
		String query;
		Venta venta = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Ventas WHERE fecha='"+fecha+"' AND claveVenta='"+claveVenta+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Ventas WHERE fecha='"+fecha+"' AND claveVenta='"+claveVenta+"'");
			rs=conexion.ejecutarSQLSelect(query);
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
		ResultSet rs;
		String query;
		Venta venta = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Ventas WHERE ventaId="+claveVenta;
			//ResultSet rs = statement.executeQuery("SELECT * FROM Ventas WHERE ventaId="+claveVenta);
			rs=conexion.ejecutarSQLSelect(query);
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
		ResultSet rs;
		String query;
		ArrayList<Venta> ventasTemp = new ArrayList<Venta>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Ventas WHERE fechaInicio= "+fechInicio+"AND fechaFin= "+fechFin;
			//ResultSet rs = statement.executeQuery("SELECT * FROM Ventas WHERE fechaInicio= "+fechInicio+"AND fechaFin= "+fechFin);
			rs=conexion.ejecutarSQLSelect(query);
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
	ResultSet rs;
	String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT apartadoId FROM Apartados WHERE nombreCliente='" + nombre + "'AND totalPagar=" + pago;
			//ResultSet rs = statement.executeQuery("SELECT apartadoId FROM Apartados WHERE nombreCliente='" + nombre + "'AND totalPagar=" + pago);
			rs=conexion.ejecutarSQLSelect(query);
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
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT COUNT(*) FROM Ventas WHERE fechaInicio= "+fechInicio+"AND fechaFin= "+fechFin;
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Ventas WHERE fechaInicio= "+fechInicio+"AND fechaFin= "+fechFin);
			rs=conexion.ejecutarSQLSelect(query);
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


