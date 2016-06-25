/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoItem;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoItem {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoItem> listar() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from item order by descripcion_item";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoItem registro;
            while (result.next()) {
                registro = new ObjetoItem();
                registro.setId_item(result.getInt("id_item"));
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_item(result.getString("descripcion_item"));
                registro.setCosto_item(result.getFloat("costo_item"));
                registro.setPrecio_item(result.getFloat("precio_item"));
                registro.setEstado_item(result.getString("estado_item"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar items " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoItem> listarItemPorCategoria(int categoria) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from item where estado_item = 'A' and id_categoria = " + categoria;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoItem registro;
            while (result.next()) {
                registro = new ObjetoItem();
                registro.setId_item(result.getInt("id_item"));
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_item(result.getString("descripcion_item"));
                registro.setCosto_item(result.getFloat("costo_item"));
                registro.setPrecio_item(result.getFloat("precio_item"));
                registro.setEstado_item(result.getString("estado_item"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar items por categoria " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoItem> buscar(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from item where descripcion_item like '%" + x + "%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoItem registro;
            while (result.next()) {
                registro = new ObjetoItem();
                registro.setId_item(result.getInt("id_item"));
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_item(result.getString("descripcion_item"));
                registro.setCosto_item(result.getFloat("costo_item"));
                registro.setPrecio_item(result.getFloat("precio_item"));
                registro.setEstado_item(result.getString("estado_item"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar items " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoItem> buscarActivo(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from item where descripcion_item like '%" + x + "%' and estado_item = 'A' order by descripcion_item";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoItem registro;
            while (result.next()) {
                registro = new ObjetoItem();
                registro.setId_item(result.getInt("id_item"));
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_item(result.getString("descripcion_item"));
                registro.setCosto_item(result.getFloat("costo_item"));
                registro.setPrecio_item(result.getFloat("precio_item"));
                registro.setEstado_item(result.getString("estado_item"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar items " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoItem> buscarNombre(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from item where descripcion_item = '" + x + "'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoItem registro;
            while (result.next()) {
                registro = new ObjetoItem();
                registro.setId_item(result.getInt("id_item"));
                registro.setId_categoria(result.getInt("id_categoria"));
                registro.setDescripcion_item(result.getString("descripcion_item"));
                registro.setCosto_item(result.getFloat("costo_item"));
                registro.setPrecio_item(result.getFloat("precio_item"));
                registro.setEstado_item(result.getString("estado_item"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar items " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public void actualizarItem(ObjetoItem item) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_item(" + item.getId_item() + "," + item.getId_categoria() + ",'" + item.getDescripcion_item() + "'," + item.getCosto_item() + "," + item.getPrecio_item() + ",'" + item.getEstado_item() + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al actualizar items " + error);
        }
        acceso.cerrarConexion();
    }

    public void insertarItem(ObjetoItem item) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_item(" + item.getId_categoria() + ",'" + item.getDescripcion_item() + "'," + item.getCosto_item() + "," + item.getPrecio_item() + ",'" + item.getEstado_item() + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar items " + error);
        }
        acceso.cerrarConexion();
    }
}