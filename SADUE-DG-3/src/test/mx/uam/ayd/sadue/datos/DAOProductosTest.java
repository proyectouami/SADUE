package test.mx.uam.ayd.sadue.datos;



import junit.framework.TestCase;
import mx.uam.ayd.sadue.datos.*;
import mx.uam.ayd.sadue.modelo.Producto;

public class DAOProductosTest extends TestCase {
	private ConexionDB conexion = new ConexionDB();
	private DAOProductos test = new DAOProductos(conexion);

	protected void setUp() throws Exception {
		super.setUp();
		conexion.crearConexion();
	}

	public void testDAOProductos() {
		fail("Not yet implemented");
	}

	public void testAgregaProducto() {
		
		Producto prueba = new Producto(32, "chafa", 1, "M", "UAMI", 0);
		assertEquals(true, test.agregaProducto(prueba));
	}

	public void testQuitaProducto() {
		fail("Not yet implemented");
	}

	public void testActualizaProducto() {
		fail("Not yet implemented");
	}

	public void testBuscaProductoIntString() {
		fail("Not yet implemented");
	}

	public void testBuscaProductoIntStringString() {
		fail("Not yet implemented");
	}

	public void testDameExistencia() {
		fail("Not yet implemented");
	}

	public void testBuscaProductoString() {
		fail("Not yet implemented");
	}

	public void testBuscaProductoInt() {
		fail("Not yet implemented");
	}

	public void testDameProductos() {
		fail("Not yet implemented");
	}

	public void testDameProductosString() {
		fail("Not yet implemented");
	}

	public void testCuantosProductos() {
		fail("Not yet implemented");
	}

}
