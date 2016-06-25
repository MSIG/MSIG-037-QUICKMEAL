/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import java.sql.*;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class Conexion {

    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://192.168.1.10:3306/quickmeal";
    //private String url = "jdbc:mysql://localhost:3306/quickmeal";
    private String usuario = "root";
    private String contrasenia = "Derwin2012mg";
    
    private Connection conexion = null;
    
    public Connection conectar(){
        try{
            Class.forName(driver).newInstance();
            conexion = DriverManager.getConnection(url,usuario,contrasenia);
            return conexion;
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException error){
            System.out.println("Error de conexion "+ error);
            return null;
        }
    }
    
    public void cerrarConexion(){
        try{
            conexion.close();
        }catch(Exception error){
            System.out.println("Error de conexion "+ error);
        }
    }
    
    public ResultSet listarRegistros(String consulta){
        try{
            conexion = conectar();
            PreparedStatement acceso_datos = conexion.prepareStatement(consulta);
            ResultSet registros = acceso_datos.executeQuery();
            return registros;
        }catch(Exception error){
            System.out.println("Error al listar registros "+ error);
            return null;
        }
    }
    
    public String ejecutarFuncion(String funcion){
        String result;
        String valor = "";
        try{
            conexion = conectar();
            PreparedStatement acceso_datos = conexion.prepareStatement(funcion);
            ResultSet registros = acceso_datos.executeQuery();
            while(registros.next()){
                valor = registros.getString("result");
                System.out.println(valor);
            }
            result = valor;
            conexion.close();
        }catch(Exception error){
            result = "Error al ejecutar funcion " + error;
        }
        return result;
    }
    
     public String ejecutarConsulta(String pScript){
        try{
            conexion = conectar();
            PreparedStatement acceso_datos = conexion.prepareStatement(pScript);
            int registros = acceso_datos.executeUpdate();
            conexion.close();
            return " Registros Actualizados: "+registros;
        }catch(Exception error){
            return "Error : " + error;
        }
    }
}