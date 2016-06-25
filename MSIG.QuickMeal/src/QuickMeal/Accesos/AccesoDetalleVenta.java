/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoDetalleVenta;
import QuickMeal.Objetos.ObjetoItem;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoDetalleVenta {

    private Mensaje mensaje = new Mensaje();

    public void insertarDetalleVenta(int id_venta, int id_item, int cantidad) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_detalle_venta(" + id_venta + ", " + id_item + ", " + cantidad + ") as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al insertar detalle venta " + error);
        }
        acceso.cerrarConexion();
    }
    
    
    public void quitarItem(int orden, int item, String cliente) {
        
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "update detalle_venta set estado_detalle_venta = 'C' where id_venta = ( select id_venta from orden where id_orden = " + orden + ") and id_item =  " + item;
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarConsulta(sql);
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al quitar items en la orden " + error);
        }
        acceso.cerrarConexion();
    }

    public void cambiaEstadoItemVenta(int orden) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select cambia_estado_item_venta(" + orden + ") as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al actualizar estado de items en la orden " + error);
        }
        acceso.cerrarConexion();
    }
    
    public ArrayList<ObjetoDetalleVenta> buscarIdVenta(String orden, String cliente){
        
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from venta as b where b.id_cliente = " + cliente + " and b.id_venta = (select id_venta from orden as a where id_orden = " + orden + ")";
        
        try{
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoDetalleVenta registros;
            
            while(tabla.next()){
                registros = new ObjetoDetalleVenta();
                registros.setId_venta(tabla.getInt("id_venta"));
                lista.add(registros);
            }
        }catch(Exception error){
            System.out.println("Ocurrio un error al buscar el id de la venta");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoItem> buscarDetalleVenta(String orden){
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select c.descripcion_item from detalle_venta as a, orden as b, item as c where a.id_venta = b.id_venta and a.id_item = c.id_item and a.estado_detalle_venta = 'P' and b.id_orden = " + orden +" order by a.id_detalle_venta";
        
        try{
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoItem registros;
            
            while(tabla.next()){
                registros = new ObjetoItem();
                registros.setDescripcion_item(tabla.getString("descripcion_item"));
                lista.add(registros);
            }
        }catch(Exception error){
            System.out.println("Ocurrio un error al buscar el detalle de la venta");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}