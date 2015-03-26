package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Escuela;

public class DAOEscuelas {
	
	ConexionDB conexion;
	private String query;
	private ResultSet rs;
	
	public DAOEscuelas(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librería
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaEscuela(Escuela escuela) {

		int llave;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="insert into Escuelas values (DEFAULT,'"+escuela.getNombreEscuela()+"')";
			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			//statement.execute("insert into Escuelas values (DEFAULT,'"+escuela.getNombreEscuela()+"')",Statement.RETURN_GENERATED_KEYS);
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			conexion.ejecutarSQL(query);
			query="select * from Escuela";
			rs=conexion.ejecutarSQLSelect(query);
			if (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    escuela.cambiaId(llave); // Asigna la llave al autor
			}
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

		int resultado = 0;
		boolean valida;
		
			// Crea el statement+"'"
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="DELETE FROM Escuelas WHERE nombre='"+escuela.getNombreEscuela();
			// Recibe los resutados
			//resultado = statement.executeUpdate("DELETE FROM Escuelas WHERE nombre='"+escuela.getNombreEscuela()+"'");
			valida=conexion.ejecutarSQL(query);
			return valida;
	

	}

	public boolean buscaEscuela(String nombre) {

		Escuela esc = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Escuelas WHERE nombre='"+nombre+"'";
			// Recibe los resutados
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

		Escuela esc = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Escuelas WHERE escuelaId="+id+" AND nombre='"+nombre+"'";
			// Recibe los resutados
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

		Escuela escuela = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Escuelas WHERE escuelaId="+id;
			// Recibe los resutados
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

		ArrayList <Escuela> escuelasTemp = new ArrayList <Escuela>();
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Escuelas";
			// Recibe los resutados
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
		
		Escuela esc = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Escuelas WHERE nombre='"+nombre+"'";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Escuelas WHERE nombre='"+nombre+"'");

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
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT COUNT(*) FROM Escuelas";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Escuelas");
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
