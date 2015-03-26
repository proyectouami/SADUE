package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.DAOUsuarios;
import mx.uam.ayd.sadue.modelo.Usuario;
import mx.uam.ayd.sadue.vistas.VistaAltaUsuario;



public class ServicioAltaUsuario {
	
	DAOUsuarios usuario;
	
	public ServicioAltaUsuario(DAOUsuarios u) {
		usuario = u;
	}
	
	public void inicia(){
		System.out.println("Metodo inicia de la clase VistaAltaUsuario");
		VistaAltaUsuario ventana = new VistaAltaUsuario(this);
		ventana.setVisible(true); // En este momento aparece la ventana "Alta Usuario"
	}
	
	public boolean agregarUsuario(String usr, String passwd, int tipo){
		Usuario u = new Usuario(usr, passwd, tipo);
		if(usuario.agregaUsuario(u))
			return true;
		else
			return false;
	}
	
	public boolean buscarUsuario(String usr){
		if(usuario.buscaUsuario(usr))
			return true;
		else
			return false;
	}

}
