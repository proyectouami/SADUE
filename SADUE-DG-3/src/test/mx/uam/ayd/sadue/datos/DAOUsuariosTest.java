package test.mx.uam.ayd.sadue.datos;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOUsuarios;
import mx.uam.ayd.sadue.modelo.Usuario;
import junit.framework.TestCase;

public class DAOUsuariosTest extends TestCase {
	private ConexionDB conexion = new ConexionDB();
	private DAOUsuarios test = new DAOUsuarios(conexion);
	
	protected void setUp() throws Exception {
		super.setUp();
		conexion.crearConexion();
	}
	
	public void testDAOUsuarios() {
		
		assertNotNull(conexion);
		
	}

	public void testAgregaUsuario() {
		
		Usuario prueba = new Usuario("Leo", "123456", 0);
		assertEquals(true, test.agregaUsuario(prueba));
	}
	
	public void testQuitaUsuario(){
		
		Usuario prueba = new Usuario("Gerald", "123456", 0);
		assertEquals(true, test.quitaUsuario(prueba));
		
	}
	
	public void testBuscaUsuario(){
		
		assertEquals(false, test.buscaUsuario(getName()));
		
	}
	
	public void testBuscaUsuarioStringString(){
		
		fail("Not yet implemented");
	}
	
	public void testBuscaTipoUsuarioStringString(){
		
		fail("Not yet implemented");
	}
	
	public void testBuscaUsuarioInt(){
		
		fail("Not yet implemented");
	}
	
	public void testDameUsuarios(){
		
		assertEquals("Usuarios", "Gerald", "Gerald");	
		
	}
	
	public void testDatosUsuario(){
		
		assertEquals("eva", "eva");
	}
	
	public void testCuantosUsuarios(){
		
		fail("Not yet implemented");
	}
	
	



}
