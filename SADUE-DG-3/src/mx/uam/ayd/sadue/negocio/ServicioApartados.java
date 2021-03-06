package mx.uam.ayd.sadue.negocio;

import java.util.ArrayList;
import java.util.StringTokenizer;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOApartados;
import mx.uam.ayd.sadue.modelo.Apartado;
import mx.uam.ayd.sadue.vistas.VistaApartados;

public class ServicioApartados {
	ConexionDB conexion;
	private DAOApartados apartados = new DAOApartados(conexion);
	private Apartado apartado;
	private ArrayList<String> apartadoArreglo = new ArrayList<String>(4);
	
	public ServicioApartados(DAOApartados ap,ConexionDB cone) {
		// TODO Auto-generated constructor stub
		conexion=cone;
		apartados = ap;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaApartados");
		VistaApartados ventana = new VistaApartados(this,conexion);
		ventana.setVisible(true);
	}
	
	public void verProductos(int id){
		System.out.println("Metodo que inicia Ver Uniformes Apartados");
		ServicioUniformesApartados servicio = new ServicioUniformesApartados(apartados,conexion);
		servicio.recibeId(id);
		servicio.inicia();
	}
	
	//En este metodo se elimina el uniforme seleccionado del DAOProductos
	public boolean finalizarApartado(String apart){
		
		String s;
		StringTokenizer st = new StringTokenizer(apart, ",");
		while(st.hasMoreTokens()){
			s = st.nextToken();
			apartadoArreglo.add(s);
		}
		
		//se crea un objeto de tipo apartado
		apartado = new Apartado(Integer.parseInt(apartadoArreglo.get(0)),String.valueOf(apartadoArreglo.get(2)), String.valueOf(apartadoArreglo.get(3)),
				String.valueOf(apartadoArreglo.get(4)), String.valueOf(apartadoArreglo.get(5)), Long.parseLong(apartadoArreglo.get(6)),
				Long.parseLong(apartadoArreglo.get(7)), String.valueOf(apartadoArreglo.get(1)), Double.parseDouble(apartadoArreglo.get(8)),
				"Pagada", "No debe");
		apartados = new DAOApartados(conexion);
		if(apartados.actualizaApartado(apartado))
			return true;
		else
			return false;
	}
	
	public Apartado[] listaApartados(){
		return apartados.dameApartados();
	}

}
