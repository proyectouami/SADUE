package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Apartado;

public class DAOApartados {
	ConexionDB conexion;
	/**
	 * Constructor de la clase
	 */
	public DAOApartados(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librer�a
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaApartado(Apartado apartado) {
		ResultSet rs;
		String query;
		int llave=-1;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="insert into Apartados values (DEFAULT,'" + apartado.dameNombreCliente() + "','" + apartado.dameApellidoCliente() + "','" + apartado.dameDireccionCliente()
					+ "','" + apartado.dameEmailCliente() + "'," + apartado.dameTelCliente() + "," + apartado.dameNoTarjetaCliente() + ",'" + apartado.dameFecha()
					+ "'," + apartado.damePagoTotal() + ",'" + apartado.dameCuenta() + "','" + apartado.dameDeuda() + "')";
			/*statement.execute("insert into Apartados values (DEFAULT,'" + apartado.dameNombreCliente() + "','" + apartado.dameApellidoCliente() + "','" + apartado.dameDireccionCliente()
						+ "','" + apartado.dameEmailCliente() + "'," + apartado.dameTelCliente() + "," + apartado.dameNoTarjetaCliente() + ",'" + apartado.dameFecha()
						+ "'," + apartado.damePagoTotal() + ",'" + apartado.dameCuenta() + "','" + apartado.dameDeuda() + "')",Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave*/
			conexion.ejecutarSQL(query);
			query="select * from Apartados";
			rs=conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			apartado.cambiaId(llave); // Asigna la llave al autor
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permite quitar un material a la librer�a
	 * @return true si el material se quito exitosamente, false sino
	 */
	public boolean quitaApartado(Apartado apartado) {
		ResultSet rs;
		String query;
		int resultado = 0;
		boolean valida;
		
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="DELETE FROM Apartados WHERE apartadoId=" + apartado.dameId();
			//resultado = statement.executeUpdate("DELETE FROM Apartados WHERE apartadoId=" + apartado.dameId());
			valida=conexion.ejecutarSQL(query);
			return valida;

		

	}
	
	public boolean actualizaApartado(Apartado apartado) {
		ResultSet rs;
		String query;
		int llave;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="update Apartados SET cuenta='" + apartado.dameCuenta() + "', deuda='" + apartado.dameDeuda() + "'" +
					"WHERE apartadoId=" + apartado.dameId();
			/*statement.execute("update Apartados SET cuenta='" + apartado.dameCuenta() + "', deuda='" + apartado.dameDeuda() + "'" +
					"WHERE apartadoId=" + apartado.dameId());
			ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave*/
			conexion.ejecutarSQL(query);
			query= "select * from Apartados WHERE apartadoId="+apartado.dameId();
			rs=conexion.ejecutarSQLSelect(query);
			if (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    apartado.cambiaId(llave); // Asigna la llave al autor
			}
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	public Apartado buscaApartado(String nomCliente) {
		ResultSet rs;
		String query;
		Apartado apartado = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Apartados WHERE nombreCliente='"+nomCliente+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Apartados WHERE nombreCliente='"+nomCliente+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				apartado = new Apartado(rs.getInt("apartadoId"),rs.getString("nombreCliente"), rs.getString("apellidoCliente"),
						rs.getString("direccionCliente"), rs.getString("email"), rs.getInt("telefono"), rs.getInt("noTarjeta"),
						rs.getString("fecha"), rs.getDouble("totalPagar"), rs.getString("cuenta"), rs.getString("deuda"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartado;

	}
	
	public int dameId(String nombre, long tarjeta, double pago){
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT apartadoId FROM Apartados WHERE nombreCliente='" + nombre + "' AND noTarjeta="+tarjeta+"AND totalPagar=" + pago;
			//ResultSet rs = statement.executeQuery("SELECT apartadoId FROM Apartados WHERE nombreCliente='" + nombre + "' AND noTarjeta="+tarjeta+"AND totalPagar=" + pago);
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

	public Apartado buscaApartado(int id) {
		ResultSet rs;
		String query;
		Apartado apartado = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Apartados WHERE idApartado="+id;
			//ResultSet rs = statement.executeQuery("SELECT * FROM Apartados WHERE idApartado="+id);
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				apartado = new Apartado(rs.getInt("apartadoId"),rs.getString("nombreCliente"), rs.getString("apellidoCliente"),
						rs.getString("direccionCliente"), rs.getString("email"), rs.getInt("telefono"), rs.getInt("noTarjeta"),
						rs.getString("fecha"), rs.getDouble("totalPagar"), rs.getString("cuenta"), rs.getString("deuda"));
				apartado.cambiaId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return apartado;

	}

	/**
	 * Regresa los materiales de la libreria como un arreglo de materiales
	 * @return el arreglo de material
	 */
	public Apartado[] dameApartados() {
		ResultSet rs;
		String query;
		ArrayList <Apartado> apartadosTemp = new ArrayList <Apartado>();
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			query="SELECT * FROM Apartados";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Apartados");
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next()){
				// Crea una nueva instancia del objeto
				Apartado apartado = new Apartado(rs.getInt("apartadoId"),rs.getString("nombreCliente"), rs.getString("apellidoCliente"),
						rs.getString("direccionCliente"), rs.getString("email"), rs.getLong("telefono"), rs.getLong("noTarjeta"),
						rs.getString("fecha"), rs.getDouble("totalPagar"), rs.getString("cuenta"), rs.getString("deuda"));
				apartadosTemp.add(apartado);
			}
			Apartado apartadosTempArreglo[]=new Apartado[apartadosTemp.size()];
			apartadosTemp.toArray(apartadosTempArreglo);
			return apartadosTempArreglo;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Regresa numero de materiales en la libreria
	 * @return un entero con el numero de materiales
	 */
	public int cuantosApartados() {
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT COUNT(*) FROM Apartados";
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Apartados");
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
	/*
	public Revista[] buscaMaterialCuyoNombreContenga(String nombreBusco) {
		ArrayList <Revista> revistasTemp = new ArrayList <Revista> ();
		for(Material m:materiales) {
			if (m.dameNombre().contains(nombreBusco))
				revistasTemp.add(m);
		}
		Material materialArreglo[]=new Material[revistasTemp.size()];
		revistasTemp.toArray(materialArreglo);
		return materialArreglo;
	}*/

}
