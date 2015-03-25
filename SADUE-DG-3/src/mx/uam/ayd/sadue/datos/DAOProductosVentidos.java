package mx.uam.ayd.sadue.datos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import mx.uam.ayd.sadue.modelo.Apartado;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.modelo.ProductoApartado;
import mx.uam.ayd.sadue.modelo.ProductoVentido;

public class DAOProductosVentidos {
	ConexionDB conexion;
	public DAOProductosVentidos(ConexionDB cone) {
		// TODO Auto-generated constructor stub
		conexion=cone;
	}

	/**
	 * Permite agregar un material a la librer�a
	 * @return true si el material se agrego exitosamente, false sino
	 */
	public boolean agregaProducto(ProductoVentido pVentido) {
		ResultSet rs;
		String query;
		int llave=-1;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Envia instruccion SQL, nota el DEFAULT es para insertar la llave autogenerada
			query="insert into ProductosVendidos values ("+pVentido.dameIdVenta()+","+pVentido.dameIdProducto()+",'"+pVentido.dameNombreProducto()+"',"+pVentido.damePrecio()+
					",'"+pVentido.dameTalla()+"','"+pVentido.dameEscuela()+"',"+pVentido.dameCantidad()+")";
			/*statement.execute("insert into ProductosVendidos values ("+pVentido.dameIdVenta()+","+pVentido.dameIdProducto()+",'"+pVentido.dameNombreProducto()+"',"+pVentido.damePrecio()+
					",'"+pVentido.dameTalla()+"','"+pVentido.dameEscuela()+"',"+pVentido.dameCantidad()+")",Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = statement.getGeneratedKeys(); // Recupera la llave*/
			conexion.ejecutarSQL(query);
			query="select * from ProductosVendidos";
			rs=conexion.ejecutarSQLSelect(query);
			while (rs != null && rs.next()) {
			    llave = rs.getInt(1);
			    
			}
			pVentido.cambiaId(llave); // Asigna la llave al producto
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
/*	public boolean quitaProducto(Apartado apartado) {

		int resultado = 0;

		try {
			// Crea el statement
			Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			resultado = statement.executeUpdate("DELETE FROM ProductosApartados WHERE apartadoId="+apartado.dameId());

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if(resultado == 0) {
			return false;
		} else {
			return true;
		}

	}*/

	public Producto buscaProducto(int id, String escuela) {
		ResultSet rs;
		String query;
		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM ProductosApartados WHERE nombre='"+id+"' AND escuela='"+escuela+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados WHERE nombre='"+id+"' AND escuela='"+escuela+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;

	}
	
	public boolean buscaProducto(int id, String escuela, String talla) {
		ResultSet rs;
		String query;
		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM ProductosApartados WHERE productoId="+id+" AND escuela='"+escuela+"' AND talla='" + talla + "'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados WHERE productoId="+id+" AND escuela='"+escuela+"' AND talla='" + talla + "'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
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
	
	public Producto buscaProducto(String escuela) {
		ResultSet rs;
		String query;
		Producto producto = null;

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM ProductosApartados WHERE escuela='"+escuela+"'";
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados WHERE escuela='"+escuela+"'");
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
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
			query="SELECT * FROM ProductosApartados WHERE productoId="+id;
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados WHERE productoId="+id);
			rs=conexion.ejecutarSQLSelect(query);
			if(rs.next())
			{
				// Crea una nueva instancia del objeto
				producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
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
			query="SELECT * FROM ProductosApartados";
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados");
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next())
			{
				// Crea una nueva instancia del objeto
				Producto producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
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
	
	public Producto[] dameProductos(Apartado apartado) {
		ResultSet rs;
		String query;
		ArrayList<Producto> productosTemp = new ArrayList<Producto>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM ProductosApartados WHERE apartadoId=" + apartado.dameId() + " ORDER BY productoId ASC";
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados WHERE apartadoId=" + apartado.dameId() + " ORDER BY productoId ASC");
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next())
			{
				// Crea una nueva instancia del objeto
				Producto producto = new Producto(rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
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
	
	public ProductoApartado[] dameProductos(int id) {
		ResultSet rs;
		String query;
		ArrayList<ProductoApartado> productosTemp = new ArrayList<ProductoApartado>();

		try {
			// Crea el statement
			//Statement statement = ManejadorBD.dameConnection().createStatement();

			// Recibe los resutados
			query="SELECT * FROM ProductosApartados WHERE apartadoId=" + id;
			//ResultSet rs = statement.executeQuery("SELECT * FROM ProductosApartados WHERE apartadoId=" + id);
			rs=conexion.ejecutarSQLSelect(query);
			while(rs.next())
			{
				// Crea una nueva instancia del objeto
				ProductoApartado producto = new ProductoApartado(rs.getInt("apartadoId"), rs.getInt("productoId"), rs.getString("nombre"), rs.getDouble("precio"),rs.getString("talla"),rs.getString("escuela"),rs.getInt("cantidad"));
				productosTemp.add(producto);
			}

			ProductoApartado productosTempArreglo[]=new ProductoApartado[productosTemp.size()];
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
			query="SELECT COUNT(*) FROM ProductosApartados";
			//ResultSet rs = statement.executeQuery("SELECT COUNT(*) FROM ProductosApartados");
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

