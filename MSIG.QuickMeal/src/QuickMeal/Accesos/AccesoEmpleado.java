/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickMeal.Accesos;
import QuickMeal.Objetos.ObjetoEmpleado;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Gigi
 */
public class AccesoEmpleado {
    Mensaje mensaje = new Mensaje();
    
    public void insertarEmpleado(ObjetoEmpleado objEmpleado){
        String sql;
        Conexion acceso = new Conexion();
        
        try{
            sql = "select insertar_empleado('" +objEmpleado.getTipo_empleado()+ "','" 
                    +objEmpleado.getNombre_empleado()+ "','" +objEmpleado.getEstado_empleado()
                    + "'," +objEmpleado.getBono_empleado()+ ",'" +objEmpleado.getEmpresa_empleado()
                    + "','" +objEmpleado.getFecha_ingreso()+ "'," +objEmpleado.getFecha_salida()+ ") as result";
            System.out.println("Ejecutando: " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critica", "Error al insertar en empleados " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarEmpleado(ObjetoEmpleado objEmpleado){
        String sql, fecha_salida;
        Conexion acceso = new Conexion();
        
        try{
            
            //colacar vacio si la fecha de salidad viene null
            if(objEmpleado.getFecha_salida().equals("null")){
                fecha_salida = "null";
            } else {
                fecha_salida = "'" + objEmpleado.getFecha_salida() +  "'";
            }
            
            sql = "select actualizar_empleado(" +objEmpleado.getId_empleado()+ ",'" +objEmpleado.getTipo_empleado()+ "','" +objEmpleado.getNombre_empleado()+ "','" +objEmpleado.getEstado_empleado()+ "'," +objEmpleado.getBono_empleado()+ ",'" +objEmpleado.getEmpresa_empleado()+ "','" +objEmpleado.getFecha_ingreso()+ "'," +fecha_salida+ ") as result";
            System.out.println("Ejecutando ACCESOEMPLEADO " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critica", "Error al insertar en empleados " + error);
        }
        acceso.cerrarConexion();
    }
    
    public ArrayList<ObjetoEmpleado> seleccionarEmpleado(String vNombre_empleado){
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from empleado where nombre_empleado like '%" +vNombre_empleado+ "%'";
        
        try{
            System.out.println("Ejecutando en ACCESO EMPLEADO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoEmpleado registros;
            
            while(tabla.next()){
                registros = new ObjetoEmpleado();
                registros.setId_empleado(tabla.getInt("id_empleado"));
                registros.setTipo_empleado(tabla.getString("tipo_empleado"));
                registros.setNombre_empleado(tabla.getString("nombre_empleado"));
                registros.setEstado_empleado(tabla.getString("estado_empleado"));
                registros.setBono_empleado(tabla.getDouble("bono_empleado"));
                registros.setEmpresa_empleado(tabla.getString("empresa_empleado"));
                registros.setFecha_ingreso(tabla.getString("fecha_ingreso_empleado"));
                registros.setFecha_salida(tabla.getString("fecha_salida_empleado"));
                lista.add(registros);
            }
        }catch(Exception error){
            System.out.println("ACCESO EMPLEADO: SELECCION EMPLEADO");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoEmpleado> retornaEmpleado(){
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select id_empleado, tipo_empleado, nombre_empleado, estado_empleado, bono_empleado, empresa_empleado, fecha_ingreso_empleado, fecha_salida_empleado from empleado;";
        
        try{
            System.out.println("Ejecutando en ACCESO EMPLEADO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoEmpleado registros;
            
            while(tabla.next()){
                registros = new ObjetoEmpleado();
                registros.setId_empleado(tabla.getInt("id_empleado"));
                registros.setTipo_empleado(tabla.getString("tipo_empleado"));
                registros.setNombre_empleado(tabla.getString("nombre_empleado"));
                registros.setEstado_empleado(tabla.getString("estado_empleado"));
                registros.setBono_empleado(tabla.getDouble("bono_empleado"));
                registros.setEmpresa_empleado(tabla.getString("empresa_empleado"));
                registros.setFecha_ingreso(tabla.getString("fecha_ingreso_empleado"));
                registros.setFecha_salida(tabla.getString("fecha_salida_empleado"));
                lista.add(registros);
            }
        }catch(Exception error){
            System.out.println("ACCESO EMPLEADO: SELECCION EMPLEADO");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoEmpleado> buscarEmpleado(String empleado) {
        
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from empleado where nombre_empleado = '" +empleado+ "'";
        
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoEmpleado registros;

                while (tabla.next()) {
                    System.out.println("entro");
                    registros = new ObjetoEmpleado();
                    registros.setId_empleado(tabla.getInt("id_empleado"));
                    registros.setTipo_empleado(tabla.getString("tipo_empleado"));
                    registros.setNombre_empleado(tabla.getString("nombre_empleado"));
                    registros.setEstado_empleado(tabla.getString("estado_empleado"));
                    registros.setBono_empleado(tabla.getDouble("bono_empleado"));
                    registros.setEmpresa_empleado(tabla.getString("empresa_empleado"));
                    registros.setFecha_ingreso(tabla.getString("fecha_ingreso_empleado"));
                    registros.setFecha_salida(tabla.getString("fecha_salida_empleado"));
                    lista.add(registros);
                }
            }
        } catch (Exception error) {
            System.out.println("Error al buscar el empleado " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
}
