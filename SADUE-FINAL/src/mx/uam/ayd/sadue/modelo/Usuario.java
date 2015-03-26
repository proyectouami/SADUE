package mx.uam.ayd.sadue.modelo;

public class Usuario {
	
	private String usuario;
	private String password;
	private int tipoUsuario;
	private int id;
	
	public Usuario(String u, String p, int tu){
		usuario = u;
		password = p;
		tipoUsuario = tu;
	}
	
	public String dameUsuario(){
		return usuario;
	}
	
	public String damePassword(){
		return password;
	}
	
	public int dameTipoUsuario(){
		return tipoUsuario;
	}
	
	public String toString() {
		return "<html> " + "Usuario: " + usuario + " <br>  Password: " + password + " <br>  Tipo de Usuario: " + tipoUsuario + " </html>";
	}
	
	public int dameId(){
		return id;
	}
	
	public void cambiaId(int id){
		this.id = id;
	}

}
