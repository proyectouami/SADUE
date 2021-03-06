package mx.uam.ayd.sadue.negocio;

import mx.uam.ayd.sadue.datos.ConexionDB;
import mx.uam.ayd.sadue.datos.DAOEscuelas;
import mx.uam.ayd.sadue.datos.DAOProductos;
import mx.uam.ayd.sadue.modelo.Escuela;
import mx.uam.ayd.sadue.modelo.Producto;
import mx.uam.ayd.sadue.vistas.VistaAgregarUniforme;

public class ServicioAgregarUniforme {
ConexionDB conexion;	
private DAOProductos daoProducto= new DAOProductos(conexion);
private DAOEscuelas daoEscuelas = new  DAOEscuelas(conexion); 
	
	public ServicioAgregarUniforme(DAOProductos producto,ConexionDB cone){
		conexion=cone;
		this.daoProducto=producto;
	}
	
	public void inicia(){
		System.out.println("Metodo que inicia la VistaAgregarUniforme");
		VistaAgregarUniforme ventana = new VistaAgregarUniforme(this,conexion);
		ventana.setVisible(true);
	}
	
	public boolean agregarUniforme(int iduni,double precuni,String talla, String escuela,int existencia){
		
		String nomuni = "";
		
		//Claves para tipo de uniforme:
		//{"0 Pants", "1 Playera", "2 Sudadera", "3 Short", "4 Camisa", "5 Pantalon", "6 Sueter", "7 Blusa", "8 Jumper", "9 Calcetas"}
		if(iduni == 0){
			nomuni = "Pants";
		}
		else if(iduni == 1){
			nomuni = "Playera";
		}
		else if(iduni == 2){
			nomuni = "Sudadera";
		}
		else if(iduni == 3){
			nomuni = "Short";
		}
		else if(iduni == 4){
			nomuni = "Camisa";
		}
		else if(iduni == 5){
			nomuni = "Pantalon";
		}
		else if(iduni == 6){
			nomuni = "Sueter";
		}
		else if(iduni == 7){
			nomuni = "Blusa";
		}
		else if(iduni == 8){
			nomuni = "Jumper";
		}
		else if(iduni == 9){
			nomuni = "Calcetas";
		}
		
		Producto proc = new Producto(iduni, nomuni, precuni, talla, escuela, existencia);
		if(daoProducto.agregaProducto(proc)){
			System.out.println("\t ID PRODUCTO \t NOMBRE\t\tPRECIO\t\tTALLA\t\tExistencia\tESCUELA");
			//el for es nomas para ver que se agregaron los productos.
			for (int i = 0; i < daoProducto.cuantosProductos(); i++) {
				System.out.println("\t\t"+daoProducto.dameProductos()[i].dameIdProducto()+"\t "+daoProducto.dameProductos()[i].dameNombreProducto()
					+"\t\t"+daoProducto.dameProductos()[i].damePrecio()+"\t\t"+daoProducto.dameProductos()[i].dameTalla()+"\t\t"
						+daoProducto.dameProductos()[i].dameExistencia()+" \t\t"+daoProducto.dameProductos()[i].dameEscuela());
			}
			return true;
		}
		else{
			return false;
		}
	}
	/**
	 * Metodo para buscar todas las escuelas
	 * @return arreglo de escuelas
	 */

	public Escuela[] buscarEscuela() {
		// TODO Auto-generated method stub
		return daoEscuelas.dameEscuelas();
	}
	/**
	 * Metodo para buscar una Escuela
	 * @param id clave de la escuela a buscar
	 * @return nombre de la escuela
	 */
	public String buscarUnaEscuela(int id){
		return daoEscuelas.buscaEscuela(id).getNombreEscuela();
	}
	
	public boolean buscarProducto(int id, String escuela, String talla){
		if(daoProducto.buscaProducto(id, escuela, talla))
			return true;
		else
			return false;
	}

}
