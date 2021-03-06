package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Usuario;

public class DAOUsuarios {
	ConexionDB conexion;
	static int user;
	/**
	 * Constructor de la clase
	 */
	public DAOUsuarios(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librer�a
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaUsuario(Usuario usuario) {
		ResultSet rs;
		String query;
		int llave=-1;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="insert into Usuarios values (DEFAULT,'"+usuario.dameUsuario()+"','"+usuario.damePassword()+"',"+usuario.dameTipoUsuario()+")";
			//statement.execute("insert into Usuarios values (DEFAULT,'"+usuario.dameUsuario()+"','"+usuario.damePassword()+"',"+usuario.dameTipoUsuario()+")",Statement.RETURN_GENERATED_KEYS);
			conexion.ejecutarSQL(query);
			query="select * from Usuarios";
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			rs=conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			usuario.cambiaId(llave); // Asigna la llave al autor
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
	public boolean quitaUsuario(Usuario usuario) {
		ResultSet rs;
		String query;
		int resultado = 0;
		boolean valida;
			// Crea el statement
//			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="DELETE FROM Usuarios WHERE usuario='"+usuario.dameUsuario()+"' AND password='"+usuario.damePassword()+"' AND tipoUsuario="+usuario.dameTipoUsuario();
			//resultado = statement.executeUpdate("DELETE FROM Usuarios WHERE usuario='"+usuario.dameUsuario()+"' AND password='"+usuario.damePassword()+"' AND tipoUsuario="+usuario.dameTipoUsuario());
			valida=conexion.ejecutarSQL(query);
			return valida;
		

		

	}

	public boolean buscaUsuario(String usuario) {

		Usuario usr = null;
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Usuarios WHERE usuario='"+usuario+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Usuarios WHERE usuario='"+usuario+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				usr = new Usuario(rs.getString("usuario"), rs.getString("password"), rs.getInt("tipoUsuario"));
				if(usr.dameUsuario().equals(rs.getString("usuario")))
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
	
	public boolean buscaUsuario(String usuario, String password) {

		Usuario usr = null;
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Usuarios WHERE usuario='"+usuario+"' AND password='"+password+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Usuarios WHERE usuario='"+usuario+"' AND password='"+password+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				usr = new Usuario(rs.getString("usuario"), rs.getString("password"), rs.getInt("tipoUsuario"));
				if(usr.dameUsuario().equals(rs.getString("usuario")) && usr.damePassword().equals(rs.getString("password")))
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
	
	public int buscaTipoUsuario(String usuario, String password) {

		Usuario usr = null;
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Usuarios WHERE usuario='"+usuario+"' AND password='"+password+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Usuarios WHERE usuario='"+usuario+"' AND password='"+password+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				usr = new Usuario(rs.getString("usuario"), rs.getString("password"), rs.getInt("tipoUsuario"));
				user = rs.getInt("tipoUsuario");
				return usr.dameTipoUsuario();
			}
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;

	}

	public Usuario buscaUsuario(int id) {

		Usuario usuario = null;
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Usuarios WHERE usuarioId="+id;
			//ResultSet rs = statement.executeQuery("SELECT * FROM Usuarios WHERE usuarioId="+id);
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				usuario = new Usuario(rs.getString("usuario"), rs.getString("password"), rs.getInt("tipoUsuario"));
				usuario.cambiaId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;

	}

	/**
	 * Regresa los materiales de la libreria como un arreglo de materiales
	 * @return el arreglo de material
	 */
	public Usuario[] dameUsuarios() {
		ResultSet rs;
		String query;
		ArrayList <Usuario> usuariosTemp = new ArrayList <Usuario>();
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			// Recibe los resutados
			query="SELECT * FROM Usuarios";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Usuarios");
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next()){
				// Crea una nueva instancia del objeto
				Usuario usuario = new Usuario(rs.getString("usuario"), rs.getString("password"), rs.getInt("tipoUsuario"));
				usuariosTemp.add(usuario);
			}
			Usuario usuariosTempArreglo[]=new Usuario[usuariosTemp.size()];
			usuariosTemp.toArray(usuariosTempArreglo);
			return usuariosTempArreglo;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Usuario datosUsuario(String usuario){
		ResultSet rs;
		String query;
		Usuario usr = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Usuarios WHERE usuario='"+usuario+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM Usuarios WHERE usuario='"+usuario+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				usr = new Usuario(rs.getString("usuario"), rs.getString("password"), rs.getInt("tipoUsuario"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usr;
	}


	/**
	 * Regresa numero de materiales en la libreria
	 * @return un entero con el numero de materiales
	 */
	public int cuantosUsuarios() {
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT COUNT(*) FROM Usuarios";
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Usuarios");
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