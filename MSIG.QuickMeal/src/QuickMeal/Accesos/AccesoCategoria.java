/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoCategoria;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoCategoria {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoCategoria> listar() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from categoria where estado_categoria = 'A'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoCategoria registro;
            while (result.next()) {
                registro = new ObjetoCategoria();
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_categoria(result.getString("descripcion_categoria"));
                registro.setEstado_categoria(result.getString("estado_categoria"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar categorias " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCategoria> buscar(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from categoria where descripcion_categoria like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoCategoria registro;
            while (result.next()) {
                registro = new ObjetoCategoria();
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_categoria(result.getString("descripcion_categoria"));
                registro.setEstado_categoria(result.getString("estado_categoria"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar categorias " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCategoria> buscarCategoria(String descripcion) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from categoria where descripcion_categoria = '" + descripcion + "'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoCategoria registro;
            while (result.next()) {
                registro = new ObjetoCategoria();
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_categoria(result.getString("descripcion_categoria"));
                registro.setEstado_categoria(result.getString("estado_categoria"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar categorias " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public void insertarCategoria(ObjetoCategoria categoria) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_categoria('" + categoria.getDescripcion_categoria() + "','" + categoria.getEstado_categoria() + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en categorias " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarCategoria(ObjetoCategoria categoria) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select actualizar_categoria(" + categoria.getId_categoria() + ",'" + categoria.getDescripcion_categoria() + "','" + categoria.getEstado_categoria() + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al actualizar categoria " + error);
        }
        acceso.cerrarConexion();
    }
}