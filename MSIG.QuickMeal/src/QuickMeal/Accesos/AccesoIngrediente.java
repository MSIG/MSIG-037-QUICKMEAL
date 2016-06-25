/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoIngrediente;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoIngrediente {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoIngrediente> listar() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from ingrediente order by id_item";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoIngrediente registro;
            while (result.next()) {
                registro = new ObjetoIngrediente();
                registro.setId_ingrediente(result.getInt("id_ingrediente"));
                registro.setId_producto(result.getInt("id_producto"));
                registro.setId_item(result.getInt("id_item"));
                registro.setProporcion_ingrediente(result.getFloat("proporcion_ingrediente"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar ingredientes " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoIngrediente> listarIngredientes(String descripcion) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_ingrediente, a.id_producto, b.descripcion_producto, "
                + "a.id_item, c.descripcion_item, a.proporcion_ingrediente, "
                + "b.unidad_medida_producto from ingrediente as a, producto as b, "
                + "item as c where a.id_producto = b.id_producto and a.id_item = c.id_item "
                + " and  c.descripcion_item like '%"+descripcion+"%'";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoIngrediente registro;
            while (result.next()) {
                registro = new ObjetoIngrediente();
                registro.setId_ingrediente(result.getInt("id_ingrediente"));
                registro.setId_producto(result.getInt("id_producto"));
                registro.setDescripcion_producto(result.getString("descripcion_producto"));
                registro.setId_item(result.getInt("id_item"));
                registro.setDescripcion_item(result.getString("descripcion_item"));
                registro.setProporcion_ingrediente(result.getFloat("proporcion_ingrediente"));
                registro.setUnidad_medida_producto(result.getString("unidad_medida_producto"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar ingredientes " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoIngrediente> buscar(int x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from ingrediente where id_item = " + x + " order by id_producto";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoIngrediente registro;
            while (result.next()) {
                registro = new ObjetoIngrediente();
                registro.setId_ingrediente(result.getInt("id_ingrediente"));
                registro.setId_producto(result.getInt("id_producto"));
                registro.setId_item(result.getInt("id_item"));
                registro.setProporcion_ingrediente(result.getFloat("proporcion_ingrediente"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar ingredientes " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public void insertarIngrediente(ObjetoIngrediente ingrediente) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_ingrediente(" + ingrediente.getId_producto() + "," + ingrediente.getId_item() + "," + ingrediente.getProporcion_ingrediente() + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en ingrdiente " + error);
        }
        acceso.cerrarConexion();
    }

    public void eliminarIngrediente(ObjetoIngrediente ingrediente) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select eliminar_ingrediente(" + ingrediente.getId_ingrediente() + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al eliminar en ingrdiente " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarIngrediente(ObjetoIngrediente ingrediente) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_ingrediente(" + ingrediente.getId_ingrediente() + "," + ingrediente.getId_producto() + "," + ingrediente.getId_item() + "," + ingrediente.getProporcion_ingrediente() + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al actualizar en ingrdiente " + error);
        }

        acceso.cerrarConexion();
    }
}