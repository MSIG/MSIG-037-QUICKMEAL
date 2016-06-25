/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoEstacion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoEstacion {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoEstacion> listar() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from estacion where estado_estacion = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoEstacion registro;
            while (result.next()) {
                registro = new ObjetoEstacion();
                registro.setId_estacion(result.getInt("id_estacion"));
                registro.setDescripcion_estacion(result.getString("descripcion_estacion"));
                registro.setUbiciacion_estacion(result.getString("ubicacion_estacion"));
                registro.setEstado_estacion(result.getString("estado_estacion"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar estacion " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoEstacion> buscar(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from estacion where descripcion_estacion like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoEstacion registro;
            while (result.next()) {
                registro = new ObjetoEstacion();
                registro.setId_estacion(result.getInt("id_estacion"));
                registro.setDescripcion_estacion(result.getString("descripcion_estacion"));
                registro.setUbiciacion_estacion(result.getString("ubicacion_estacion"));
                registro.setEstado_estacion(result.getString("estado_estacion"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar estacion " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public void insertarEstacion(ObjetoEstacion estacion) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_estacion('" + estacion.getDescripcion_estacion() + "', '" + estacion.getUbiciacion_estacion() + "', '" + estacion.getEstado_estacion() + "') as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error insertar estacion " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarEstacion(ObjetoEstacion estacion) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select actualizar_estacion(" + estacion.getId_estacion() + ",'" + estacion.getDescripcion_estacion() + "', '" + estacion.getUbiciacion_estacion() + "', '" + estacion.getEstado_estacion() + "') as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en actualizar estacion " + error);
        }
        acceso.cerrarConexion();
    }
}