package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Apartado;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.modelo.ProductoApartado;


/**
 * Esta clase representa una liberia
 *
 * @author Alberto
 *
 */
public class DAOProductos {

	ConexionDB conexion;
	private String query;
	private ResultSet rs;
	
	public DAOProductos(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librer’a
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaProducto(Producto producto) {

		int llave;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="insert into Productos values ("+producto.dameIdProducto()+",'"+producto.dameNombreProducto()+"',"+producto.damePrecio()+",'"+producto.dameTalla()+"','"+producto.dameEscuela()+"',"+producto.dameExistencia()+")";
			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			//statement.execute("insert into Productos values ("+producto.dameIdProducto()+",'"+producto.dameNombreProducto()+"',"+producto.damePrecio()+",'"+producto.dameTalla()+"','"+producto.dameEscuela()+"',"+producto.dameExistencia()+")",Statement.RETURN_GENERATED_KEYS);
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			conexion.ejecutarSQL(query);
			query="select * from Producto";
			rs=conexion.ejecutarSQLSelect(query);
			
			if (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    producto.cambiaId(llave); // Asigna la llave al producto
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
	public boolean quitaProducto(Producto producto) {

		int resultado = 0;
		boolean valida;

		
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="DELETE FROM Productos WHERE productoId="+producto.dameIdProducto()+" AND escuela='"+producto.dameEscuela()+"' AND talla='" + producto.dameTalla() +"'";
			// Recibe los resutados
			//resultado = statement.executeUpdate("DELETE FROM Productos WHERE productoId="+producto.dameIdProducto()+" AND escuela='"+producto.dameEscuela()+"' AND talla='" + producto.dameTalla() +"'");

			valida=conexion.ejecutarSQL(query);
			return valida;
	}
	
	public boolean actualizaProducto(Producto producto, int cantidad) {

		int llave;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="update Productos SET existencia=" + (producto.dameExistencia()-cantidad) + "WHERE productoId=" + producto.dameIdProducto()
					+ "AND talla='" + producto.dameTalla() + "' AND escuela='" + producto.dameEscuela() + "'";
			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			//statement.execute("update Productos SET existencia=" + (producto.dameExistencia()-cantidad) + "WHERE productoId=" + producto.dameIdProducto()
				//	+ "AND talla='" + producto.dameTalla() + "' AND escuela='" + producto.dameEscuela() + "'");
			rs=conexion.ejecutarSQLSelect(query);
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			if (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    producto.cambiaId(llave); // Asigna la llave al producto
			}
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean cambiaPrecio(Producto producto) {

		int llave;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="update Productos SET precio=" + producto.damePrecio() + "WHERE productoId=" + producto.dameIdProducto()
					+ " AND escuela='" + producto.dameEscuela() + "' AND talla='" + producto.dameTalla() + "'";
			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			//statement.execute("update Productos SET precio=" + producto.damePrecio() + "WHERE productoId=" + producto.dameIdProducto()
				//	+ " AND escuela='" + producto.dameEscuela() + "' AND talla='" + producto.dameTalla() + "'");
			rs=conexion.ejecutarSQLSelect(query);
			//ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave
			if (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    producto.cambiaId(llave); // Asigna la llave al producto
			}
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	public Producto buscaProducto(int id, String escuela) {

		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos WHERE nombre='"+id+"' AND escuela='"+escuela+"'";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Productos WHERE nombre='"+id+"' AND escuela='"+escuela+"'");
			rs=conexion.ejecutarSQLSelect(query);
			
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("existencia"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}
	
	public boolean buscaProducto(int id, String escuela, String talla) {

		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos WHERE productoId="+id+" AND escuela='"+escuela+"' AND talla='" + talla + "'";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Productos WHERE productoId="+id+" AND escuela='"+escuela+"' AND talla='" + talla + "'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("existencia"));
				if((producto.dameIdProducto() == id) && (producto.dameEscuela().equals(rs.getString("escuela"))) && (producto.dameTalla().equals(rs.getString("talla"))))
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
	
	public int dameExistencia(int id, String escuela, String talla){
		
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT existencia FROM Productos WHERE productoId=" + id + " AND escuela='"+escuela+"' AND talla='" + talla + "'";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT existencia FROM Productos WHERE productoId=" + id + " AND escuela='"+escuela+"' AND talla='" + talla + "'");
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
	
	public Producto buscaProducto(String escuela) {

		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos WHERE escuela='"+escuela+"'";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Productos WHERE escuela='"+escuela+"'");
			rs=conexion.ejecutarSQLSelect(query);
			
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("existencia"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}

	public Producto buscaProducto(int id) {

		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos WHERE productoId="+id;
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Productos WHERE productoId="+id);
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("Existencia"));
				producto.cambiaId(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}

	/**
	 * Regresa los materiales de la libreria como un arreglo de materiales
	 * @return el arreglo de material
	 */
	public Producto[] dameProductos() {

		ArrayList<Producto> productosTemp = new ArrayList<Producto>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Productos");
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next())
			{
				// Crea una nueva instancia del objeto
				Producto producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("existencia"));
;
				productosTemp.add(producto);
			}

			Producto productosTempArreglo[]=new Producto[productosTemp.size()];
			productosTemp.toArray(productosTempArreglo);
			return productosTempArreglo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Producto[] dameProductos(String escuela) {

		ArrayList<Producto> productosTemp = new ArrayList<Producto>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos WHERE escuela='" + escuela + "' ORDER BY productoId ASC";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT * FROM Productos WHERE escuela='" + escuela + "' ORDER BY productoId ASC");
			rs=conexion.ejecutarSQLSelect(query);
			
			while(rs.next())
			{
				// Crea una nueva instancia del objeto
				Producto producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("existencia"));
;
				productosTemp.add(producto);
			}

			Producto productosTempArreglo[]=new Producto[productosTemp.size()];
			productosTemp.toArray(productosTempArreglo);
			return productosTempArreglo;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * Regresa numero de materiales en la libreria
	 * @return un entero con el numero de materiales
	 */
	public int cuantosProductos() {
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT COUNT(*) FROM Productos";
			// Recibe los resutados
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Productos");
			
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

