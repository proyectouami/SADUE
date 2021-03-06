package mx.uam.ayd.sadue.datos;

import java.sql.*;
import javax.swing.JOptionPane;


/**
 *
 * @author n3to
 */
public class ConexionDB {
    Connection conexion=null;
    
    public Connection getConexion()
    {
        return conexion;
    }
/**
* Método utilizado para establecer la conexión con la base de datos
* @return estado regresa el estado de la conexión, true si se estableció la conexión,
* falso en caso contrario
*/
    public boolean crearConexion(){
    try {
      Class.forName("com.mysql.jdbc.Driver");
      //primero poner la base de datos (base_de_datos), usuario de base(root), contraseña (en mi caso no tengo solo se pone "").
      conexion = DriverManager.getConnection("jdbc:mysql://localhost/base_de_datos","root","");
      System.out.println("Conexion Establecida");
      JOptionPane.showMessageDialog(null, "Conexion Establecida");
      
      
    }catch (SQLException | ClassNotFoundException ex    ) {
      ex.printStackTrace();
      System.out.println("Error de Conexion");
      JOptionPane.showMessageDialog(null, "Error de Conexion" + ex);
      return false;
    }
    return true;
    }
/**
*
*Método utilizado para realizar las instrucciones: INSERT, DELETE y UPDATE
*@param sql Cadena que contiene la instrucción SQL a ejecutar
*@return estado regresa el estado de la ejecución, true(éxito) o false(error)
*
*/
    public boolean ejecutarSQL(String sql){
    try {
      Statement sentencia = conexion.createStatement();
      sentencia.executeUpdate(sql);
    } catch (SQLException ex) {
      ex.printStackTrace();
    return false;
    }
 
    return true;
    }
/**
*
*Método utilizado para realizar la instrucción SELECT
*@param sql Cadena que contiene la instrucción SQL a ejecutar
*@return resultado regresa los registros generados por la consulta
*
*/
    public ResultSet ejecutarSQLSelect(String sql){
    ResultSet resultado;
    try {
      Statement sentencia = conexion.createStatement();
      resultado = sentencia.executeQuery(sql);
    } catch (SQLException ex) {
      ex.printStackTrace();
      System.out.println("Error de Conexion");
      JOptionPane.showMessageDialog(null, "Error de Conexion" + ex);
      return null;
    }
 
    return resultado;
    }
   
}
