package mx.uam.ayd.sadue.modelo;

public class Escuela {
	
	int claveEscuela;
	String nombreEscuela;
	
	public Escuela(int cveEscuela, String nomEsc){
		nombreEscuela = nomEsc;
		claveEscuela = cveEscuela;
	}
	
	public int getClaveEscuela() {
		return claveEscuela;
	}

	public String getNombreEscuela() {
		return nombreEscuela;
	}
	
	public void cambiaId(int cveEscuela){
		claveEscuela = cveEscuela;
	}
	
	public String toString() {
		return claveEscuela + " " + nombreEscuela;
	}

}
