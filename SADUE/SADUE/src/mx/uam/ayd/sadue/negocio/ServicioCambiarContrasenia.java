package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.modelo.*;
import mx.uam.ayd.sadue.datos.DAOUsuarios;
import mx.uam.ayd.sadue.vistas.VistaCambiarContrasenia;

public class ServicioCambiarContrasenia {
	
	DAOUsuarios usuario;
	Usuario datUsusario;
	
	public ServicioCambiarContrasenia(DAOUsuarios u){
		usuario = u;
	}

	/**
	 * Metodo para iniciar el servicio de cambio de contrase–a.
	 * @return null, es un metodo que lanza la ventana.
	 */
	public void inicia(){
		VistaCambiarContrasenia vistCambContrasenia = new VistaCambiarContrasenia(this);
		vistCambContrasenia.setVisible(true);
	}
	/**
	 * Metodo para validar de que un usuario existe en la BD.
	 * @param nombre del usuario
	 * @return regresa true si el usuario existe, false si no.
	 */
	public boolean validaExistencia(String us){
		if(usuario.buscaUsuario(us))
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo para cambiar la contrase–a del usuario.
	 * @param nombre del usuario
	 * @return true si se cambio la contrase–a, false si no.
	 */
	public void cambiaContrasenia(String us, String passwd){
		//obtiene el tipo de usuario a cambiar la contrase–a
		int id = usuario.idUsuario(us);
		//crea un nuevo usuario
		Usuario usua = new Usuario(us, passwd,id);
		
		//Borra el usuario con pasword anterior
		usuario.quitaUsuario(usuario.datosUsuario(us));
		//Agrega susuario anterior con pasword nuevo
		usuario.agregaUsuario(usua);
	}
}
