package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Escuela;

public class DAOEscuelas {
	ConexionDB conexion;
	public DAOEscuelas(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librería
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaEscuela(Escuela escuela) {
		ResultSet rs;
		String query;
		int llave=-1;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="insert into Escuelas values (DEFAULT,'"+escuela.getNombreEscuela()+"')";
			//statement.execute("insert into Escuelas values (DEFAULT,'"+escuela.getNombreEscuela()+"')",Statement.RETURN_GENERATED_KEYS);
			conexion.ejecutarSQL(query);
			query="select * from Escuelas";
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			rs=conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			escuela.cambiaId(llave); // Asigna la llave al autor
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Permite quitar un material a la librería
	 * @return true si el material se quito exitosamente, false sino
	 */
	public boolean quitaEscuela(Escuela escuela) {
		ResultSet rs;
		String query;
		int resultado = 0;
		boolean valida;

			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="DELETE FROM Escuelas WHERE nombre='"+escuela.getNombreEscuela()+"'";
			//resultado = statement.executeUpdate("DELETE FROM Escuelas WHERE nombre='"+escuela.getNombreEscuela()+"'");
			valida=conexion.ejecutarSQL(query);
			return valida;

	}

	public boolean buscaEscuela(String nombre) {
		ResultSet rs;
		String query;
		Escuela esc = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Escuelas WHERE nombre='"+nombre+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Escuelas WHERE nombre='"+nombre+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				esc = new Escuela(rs.getInt("escuelaId"), rs.getString("nombre"));
				if(esc.getNombreEscuela().equals(rs.getString("nombre")))
					return true;
				else
					return false;
			}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	public boolean buscaEscuela(int id, String nombre) {
		ResultSet rs;
		String query;
		Escuela esc = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Escuelas WHERE escuelaId="+id+" AND nombre='"+nombre+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Escuelas WHERE escuelaId="+id+" AND nombre='"+nombre+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				esc = new Escuela(rs.getInt("escuelaId"), rs.getString("nombre"));
				if(esc.getNombreEscuela().equals(rs.getString("nombre")))
					return true;
				else
					return false;
			}
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public Escuela buscaEscuela(int id) {
		ResultSet rs;
		String query;
		Escuela escuela = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Escuelas WHERE escuelaId="+id;
			//ResultSet rs = statement.executeQuery("SELECT * FROM Escuelas WHERE escuelaId="+id);
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				escuela = new Escuela(rs.getInt("escuelaId"), rs.getString("nombre"));
				escuela.cambiaId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return escuela;

	}

	/**
	 * Regresa los materiales de la libreria como un arreglo de materiales
	 * @return el arreglo de material
	 */
	public Escuela[] dameEscuelas() {
		ResultSet rs;
		String query;
		ArrayList <Escuela> escuelasTemp = new ArrayList <Escuela>();
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			query="SELECT * FROM Escuelas";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Escuelas");
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next()){
				// Crea una nueva instancia del objeto
				Escuela escuela = new Escuela(rs.getInt("escuelaId"), rs.getString("nombre"));
				escuelasTemp.add(escuela);
			}
			Escuela escuelasTempArreglo[] = new Escuela[escuelasTemp.size()];
			escuelasTemp.toArray(escuelasTempArreglo);
			return escuelasTempArreglo;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Escuela datosEscuela(String nombre){
		ResultSet rs;
		String query;
		Escuela esc = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Escuelas WHERE nombre='"+nombre+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Escuelas WHERE nombre='"+nombre+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				esc = new Escuela(rs.getInt("escuelaId"), rs.getString("nombre"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return esc;
	}


	/**
	 * Regresa numero de materiales en la libreria
	 * @return un entero con el numero de materiales
	 */
	public int cuantasEscuelas() {
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			
			// Recibe los resutados
			query="SELECT COUNT(*) FROM Escuelas";
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Escuelas");
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
