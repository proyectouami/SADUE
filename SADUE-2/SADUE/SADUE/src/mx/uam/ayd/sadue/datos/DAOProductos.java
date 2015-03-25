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
	/**
	 * Constructor de la clase
	 */
	public DAOProductos(ConexionDB cone) {
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librer’a
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaProducto(Producto producto) {
		ResultSet rs;
		int llave=-1;
		String query;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="insert into Productos values ("+producto.dameIdProducto()+",'"+producto.dameNombreProducto()+"',"+producto.damePrecio()+",'"+producto.dameTalla()+"','"+producto.dameEscuela()+"',"+producto.dameExistencia()+")";
			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			//statement.execute("insert into Productos values ("+producto.dameIdProducto()+",'"+producto.dameNombreProducto()+"',"+producto.damePrecio()+",'"+producto.dameTalla()+"','"+producto.dameEscuela()+"',"+producto.dameExistencia()+")",Statement.RETURN_GENERATED_KEYS);
			conexion.ejecutarSQL(query);
			query="select * from Productos";
			rs=conexion.ejecutarSQLSelect(query);
			
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			producto.cambiaId(llave); // Asigna la llave al producto
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
		String query;
		boolean valida;
		// Crea el statement
		//Statement statement = ManejadorBD.dameConnection().createStatement();
		// Recibe los resutados
		query="DELETE FROM Productos WHERE productoId="+producto.dameIdProducto()+" AND escuela='"+producto.dameEscuela()+"' AND talla='" + producto.dameTalla() +"'";
		//resultado = statement.executeUpdate("DELETE FROM Productos WHERE productoId="+producto.dameIdProducto()+" AND escuela='"+producto.dameEscuela()+"' AND talla='" + producto.dameTalla() +"'");
		valida=conexion.ejecutarSQL(query);
		return valida;

		

	}
	
	public boolean actualizaProducto(Producto producto, int cantidad) {
		ResultSet rs;
		int llave=-1;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="update Productos SET existencia=" + (producto.dameExistencia()-cantidad) + "WHERE productoId=" + producto.dameIdProducto()
					+ "AND talla='" + producto.dameTalla() + "' AND escuela='" + producto.dameEscuela() + "'";
			/*statement.execute("update Productos SET existencia=" + (producto.dameExistencia()-cantidad) + "WHERE productoId=" + producto.dameIdProducto()
					+ "AND talla='" + producto.dameTalla() + "' AND escuela='" + producto.dameEscuela() + "'");*/
			conexion.ejecutarSQL(query);
			query="select * from Productos";
			rs= conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			producto.cambiaId(llave); // Asigna la llave al producto
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean cambiaPrecio(Producto producto) {
		ResultSet rs;
		int llave=-1;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="update Productos SET precio=" + producto.damePrecio() + "WHERE productoId=" + producto.dameIdProducto()
					+ " AND escuela='" + producto.dameEscuela() + "' AND talla='" + producto.dameTalla() + "'";
			/*statement.execute("update Productos SET precio=" + producto.damePrecio() + "WHERE productoId=" + producto.dameIdProducto()
					+ " AND escuela='" + producto.dameEscuela() + "' AND talla='" + producto.dameTalla() + "'");*/
			conexion.ejecutarSQL(query);
			query="select * from Productos";
			rs = conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			producto.cambiaId(llave); // Asigna la llave al producto
			return true;
		} catch (SQLException e) {

			// Cacha excepcion
			e.printStackTrace();
			return false;
		}
	}

	public Producto buscaProducto(int id, String escuela) {
		ResultSet rs;
		Producto producto = null;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			query="SELECT * FROM Productos WHERE nombre='"+id+"' AND escuela='"+escuela+"'";
			// Recibe los resutados
			//rs = statement.executeQuery("SELECT * FROM Productos WHERE nombre='"+id+"' AND escuela='"+escuela+"'");
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
		ResultSet rs;
		Producto producto = null;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Productos WHERE productoId="+id+" AND escuela='"+escuela+"' AND talla='" + talla + "'";
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
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT existencia FROM Productos WHERE productoId=" + id + " AND escuela='"+escuela+"' AND talla='" + talla + "'";
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
		ResultSet rs;
		String query;
		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			
			// Recibe los resutados
			query="SELECT * FROM Productos WHERE escuela='"+escuela+"'";
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
		ResultSet rs;
		String query;
		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Productos WHERE productoId="+id;
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
		ResultSet rs;
		String query;
		ArrayList<Producto> productosTemp = new ArrayList<Producto>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Productos";
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
		ResultSet rs;
		String query;
		ArrayList<Producto> productosTemp = new ArrayList<Producto>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM Productos WHERE escuela='" + escuela + "' ORDER BY productoId ASC";
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
		ResultSet rs;
		String query;
		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();
			
			// Recibe los resutados
			query="SELECT COUNT(*) FROM Productos";
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM Productos");
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

