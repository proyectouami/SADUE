package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.DAOEscuelas;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.vistas.VistaAltaEscuela;

public class ServicioAltaEscuela {
	
	DAOEscuelas escuela;
	
	public ServicioAltaEscuela(DAOEscuelas e) {
		escuela = e;
	}
	
	public void inicia(){
		System.out.println("Metodo inicia de la clase VistaAltaEscuelas");
		VistaAltaEscuela ventana = new VistaAltaEscuela(this);
		ventana.setVisible(true); // En este momento aparece la ventana "Alta Usuario"
	}
	
	public boolean agregarEscuela(int clave, String nombre){
		Escuela e = new Escuela(clave, nombre);
		if(escuela.agregaEscuela(e))
			return true;
		else
			return false;
	}
	
	public boolean buscarEscuela(String nombre){
		if(escuela.buscaEscuela(nombre))
			return true;
		else
			return false;
	}

}
