package test.mx.uam.ayd.sadue.datos;

import junit.framework.TestCase;
import mx.uam.ayd.sadue.datos.*;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;

public class DAOProductosTest extends TestCase {
	private ConexionDB conexion = new ConexionDB();
	private DAOProductos test = new DAOProductos(conexion);

	protected void setUp() throws Exception {
		super.setUp();
		conexion.crearConexion();
	}
	
	///SIIIIIIIII
	public void testDAOProductos() {
		assertNotNull(conexion);
	}
	
	//SIIIIIIIIIII
	public void testAgregaProducto() {
		
		Producto prueba = new Producto(1, "chamarra", 320, "M", "UAMI", 4);
		assertEquals(true, test.agregaProducto(prueba));
	}
	
	//SIIIIIIIIIII
	public void testQuitaProducto() {
		
		Producto prueba = new Producto(2, "falda", 240, "CH", "UNAM", 2);
		assertEquals(true, test.quitaProducto(prueba));
	}
	
	//SIIIIIIIIIII
	public void testActualizaProducto() {
		
		Producto prueba = new Producto(2, "falda", 240, "CH", "UNAM", 2);
		int cantidad = 4;
		assertEquals(true, test.actualizaProducto(prueba,cantidad));
	}

	//SIIIIIIIIIII
	public void testcambiaPrecio() {
		
		Producto prueba = new Producto(3, "pantalon", 280, "G", "POLI", 9);
		assertEquals(true, test.cambiaPrecio(prueba));
	}	
	
	///NOOOOO
	public void testBuscaProductoIntString() {
		
		Producto prueba = new Producto(1,"chamarra",320,"M","UAMI",4);
		assertEquals(prueba,test.buscaProducto(1,"UAMI"));
	}
	
	//NOOOOO
	public void testBuscaProductoIntStringString() {
		
		Producto prueba = new Producto(1, "chamarra",320,"M","UAMI",4);
		assertEquals(prueba, test.buscaProducto(1,"UAMI","M"));
	}

	//SIIIIIIIIIII
	public void testDameExistencia() {
		assertEquals(4, test.dameExistencia(1,"UAMI","M"));
	}
	
	///NOOOOOOO
	public void testBuscaProductoString() {
		
		Producto prueba = new Producto(1, "chamarra",320,"M","UAMI",4);
		assertEquals(prueba,test.buscaProducto("UAMI"));
	}
	
	///SIIIII
	public void testBuscaProductoInt() {
		
		Producto prueba = new Producto(1,"chamarra",320,"M","UAMI",4);
		Producto aux= test.buscaProducto(1);
		assertEquals(prueba.dameIdProducto(),aux.dameIdProducto());
		
	}
	
	///SIIIIIIIIII
	public void testDameProductos() {
		
		assertNotNull(conexion);
	}
	
	///NOOOOOOO ??(LISTA)??
	public void testDameProductosString() {
		fail("Not yet implemented");
	}

	///SIIIIIIIIII
	public void testCuantosProductos() {
		assertNotNull(conexion);
	}

}
