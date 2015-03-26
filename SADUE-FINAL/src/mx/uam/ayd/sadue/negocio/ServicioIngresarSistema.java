package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.DAOUsuarios;
import mx.uam.ayd.sadue.vistas.VistaIngresarSistema;

public class ServicioIngresarSistema {
	
	DAOUsuarios usuario;
	
	public ServicioIngresarSistema(DAOUsuarios u) {
		// TODO Auto-generated constructor stub
		usuario = u;
	}
	
	public void inicia(){
		System.out.println("Metodo inicia de la clase VistaIngresarSistema");
		VistaIngresarSistema ventana = new VistaIngresarSistema(this);
		ventana.setVisible(true); // En este momento aparece la ventana principal
	}
	
	public boolean buscarUsuario(String usr, String passwd){
		if(usuario.buscaUsuario(usr, passwd))
			return true;
		else
			return false;
	}
	
	public int buscarTipoUsuario(String usr, String passwd){
		int tipo;
		tipo = usuario.buscaTipoUsuario(usr, passwd);
		return tipo;
	}

}
