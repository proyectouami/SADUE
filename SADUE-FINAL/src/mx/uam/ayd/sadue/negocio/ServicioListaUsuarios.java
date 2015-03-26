package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.DAOUsuarios;
import mx.uam.ayd.sadue.modelo.Usuario;
import mx.uam.ayd.sadue.vistas.DialogoListaUsuarios;

public class ServicioListaUsuarios {
	
	private DAOUsuarios usuarios;
	private Usuario usr;
	private ArrayList<String> usuarioArreglo = new ArrayList<String>(3);
	
	public ServicioListaUsuarios(DAOUsuarios usuario){
		this.usuarios = usuario;
	}
	
	public void inicia(){
		System.out.println("Metodo inicia de la clase DialogoListaUsuarios");
		DialogoListaUsuarios ventana = new DialogoListaUsuarios(this);
		ventana.setVisible(true); // En este momento aparece la ventana principal
	}
	
	public Usuario[] buscarUsuario(){
		return usuarios.dameUsuarios();
	}
	
	public boolean eliminarUsuario(String usrl){
		
		String p;
		StringTokenizer st = new StringTokenizer(usrl, ":,<>/ ");
		while(st.hasMoreTokens()){
			p = st.nextToken();
			usuarioArreglo.add(p);
		}
		for(int i=0; i < usuarioArreglo.size(); i++){
			if(usuarioArreglo.get(i).equalsIgnoreCase("Usuario") || usuarioArreglo.get(i).equalsIgnoreCase("Password")
					|| usuarioArreglo.get(i).equalsIgnoreCase("Tipo")|| usuarioArreglo.get(i).equals("de") || usuarioArreglo.get(i).equals("br")
					|| usuarioArreglo.get(i).equals("html")){
				usuarioArreglo.remove(i);
				i--;
			}
		}
		
		usr = new Usuario(usuarioArreglo.get(0),String.valueOf(usuarioArreglo.get(1)), Integer.parseInt(usuarioArreglo.get(2)));
		usuarios = new DAOUsuarios();
		if(usuarios.quitaUsuario(usr))
			return true;
		else
			return false;
	}
}