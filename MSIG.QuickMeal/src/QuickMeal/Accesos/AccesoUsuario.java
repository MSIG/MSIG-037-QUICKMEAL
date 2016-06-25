/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoUsuario;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Gigi
 */
public class AccesoUsuario {
    Mensaje mensaje = new Mensaje();
    
    public void insertarUsuario(ObjetoUsuario usuario){
        String sql;
        Conexion acceso = new Conexion();
        try{
            sql = "select insertar_usuario(" +usuario.getId_empleado()+ ",'" +usuario.getTipo_usuario()+ "','" +usuario.getNombre_usuario()+ "','" +usuario.getContrasenia_usuario()+ "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critica", "Error al insertar en usuarios " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarUsuario(ObjetoUsuario objUsuario){
        
        String sql;
        Conexion acceso = new Conexion();  
        
        try{
            sql = "select actualizar_usuario(" +objUsuario.getId_usuario()+ "," +objUsuario.getId_empleado()+ ",'" +objUsuario.getTipo_usuario()+ "','" +objUsuario.getNombre_usuario()+ "','" +objUsuario.getContrasenia_usuario()+ "') as result";
            System.out.println("Ejecutando ACESSOUSUARIO " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critica", "Error al insertar en usuarios " + error);
        }
        
        acceso.cerrarConexion();
    }
    
    public ArrayList<ObjetoUsuario> seleccionarUsuario(String pNombre_usuario){
        
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_usuario, a.id_empleado, a.nombre_usuario, a.contrasenia_usuario, a.tipo_usuario, b.nombre_empleado from usuario a, empleado b where a.id_empleado = b.id_empleado and a.nombre_usuario like '%" +pNombre_usuario+ "%'";
        
        try{
            System.out.println("Ejecutando en ACCESO USUARIO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoUsuario registros;
            
            while(tabla.next()){
                registros = new ObjetoUsuario();
                registros.setId_usuario(tabla.getInt("id_usuario"));
                registros.setId_empleado(tabla.getInt("id_empleado"));
                registros.setNombre_usuario(tabla.getString("nombre_usuario"));
                registros.setContrasenia_usuario(tabla.getString("contrasenia_usuario"));
                registros.setTipo_usuario(tabla.getString("tipo_usuario"));
                registros.setNombre_empleado(tabla.getString("nombre_empleado"));
                lista.add(registros);
            }
        }catch(Exception error){
            System.out.println("ACCESO USUARIO: SELECCION USUARIO");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoUsuario> retornaUsuario() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.*, b.nombre_empleado from usuario as a, empleado as b where a.id_empleado = b.id_empleado";
        try {
            System.out.println("Ejecutando en ACCESO USUARIO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoUsuario registros;

            while (tabla.next()) {
                registros = new ObjetoUsuario();
                registros.setId_usuario(tabla.getInt("id_usuario"));
                registros.setId_empleado(tabla.getInt("id_empleado"));
                registros.setTipo_usuario(tabla.getString("tipo_usuario"));
                registros.setNombre_usuario(tabla.getString("nombre_usuario"));
                registros.setNombre_empleado(tabla.getString("nombre_empleado"));
                registros.setContrasenia_usuario(tabla.getString("contrasenia_usuario"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO USUARIO: SELECCION USUARIO");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoUsuario> retornaNombreEmpleados(){
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from empleado";
        try{
            System.out.println("Ejecutando en ACCESO USUARIO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoUsuario registros;
            
            while(tabla.next()){
                System.out.println("Ejecutando WHILE ");
                registros = new ObjetoUsuario();
                registros.setId_empleado(tabla.getInt("id_empleado"));
                registros.setNombre_empleado(tabla.getString("nombre_empleado"));
                lista.add(registros);
            }
        }catch(Exception error){
            System.out.println("ACCESO USUARIO: SELECCION USUARIO");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
}
