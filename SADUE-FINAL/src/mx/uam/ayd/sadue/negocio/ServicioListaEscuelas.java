package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.DAOEscuelas;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.vistas.DialogoListaEscuelas;

public class ServicioListaEscuelas {

	private DAOEscuelas escuelas;
	private Escuela esc;
	private ArrayList<String> escuelaArreglo = new ArrayList<String>(3);
	
	public ServicioListaEscuelas(DAOEscuelas escuela){
		escuelas = escuela;
	}
	
	public void inicia(){
		System.out.println("Metodo inicia de la clase DialogoListaUsuarios");
		DialogoListaEscuelas ventana = new DialogoListaEscuelas(this);
		ventana.setVisible(true); // En este momento aparece la ventana principal
	}
	
	public Escuela[] buscarEscuela(){
		return escuelas.dameEscuelas();
	}
	
	public boolean eliminarEscuela(String escl){
		
		String p, escuelaCadena = "";
		String[] escuelaArregloAux;
		
		StringTokenizer st = new StringTokenizer(escl, ":,<>/ ");
		while(st.hasMoreTokens()){
			p = st.nextToken();
			escuelaArreglo.add(p);
		}
		escuelaArregloAux = new String[escuelaArreglo.size()];
		escuelaArreglo.toArray(escuelaArregloAux);
		
		int clave = Integer.parseInt(escuelaArreglo.get(0));
		for(int i=1; i < escuelaArreglo.size(); i++){
			escuelaCadena += (escuelaArregloAux[i] + " ");
		}
		
		esc = new Escuela(clave,escuelaCadena);
		escuelas = new DAOEscuelas();
		if(escuelas.quitaEscuela(esc))
			return true;
		else
			return false;
	}
	
}
