/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoVenta;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoVenta {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoVenta> buscarVenta(int id_estacion, int id_usuario, int id_cliente) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from venta where id_estacion = " + id_estacion + " and id_cliente = " + id_cliente + " and id_usuario = " + id_usuario + " and "
                + "fecha_venta = (select max(fecha_venta) from venta where id_estacion = " + id_estacion + " and id_cliente = " + id_cliente + " and id_usuario = " + id_usuario + ")";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoVenta registro;
            while (result.next()) {
                registro = new ObjetoVenta();
                registro.setId_venta(result.getInt("id_venta"));
                registro.setId_cliente(result.getInt("id_cliente"));
                registro.setId_estacion(result.getInt("id_estacion"));
                registro.setId_usuario(result.getInt("id_usuario"));
                registro.setNota_venta(result.getString("nota_venta"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar venta " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public void insertarVenta(ObjetoVenta venta) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_venta(" + venta.getId_cliente() + ", " + venta.getId_usuario() + ", " + venta.getId_estacion()
                    + ", '" + venta.getFactura_venta() + "', '" + venta.getDireccion_venta() + "',  CURRENT_TIMESTAMP(), '" + venta.getNota_venta() 
                    + "'," + venta.getPropina_venta() + ", " + venta.getId_empleado() + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en cancelar orden " + error);
        }
        acceso.cerrarConexion();
    }

    public void propina(int id_orden, float propina, float descuento, float extra, String forma) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "update venta set propina_venta = " + propina + ", descuento_venta = " + descuento +", extra_venta = " + extra + ", forma_pago_venta = '" + forma + "' where id_venta = (select id_venta from orden where id_orden = " + id_orden + ")";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarConsulta(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al cerrar la orden " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void actualizarNotas(int id_venta, String nota) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "update venta set nota_venta = '" + nota + "' where id_venta = " + id_venta;
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarConsulta(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en Actualizar la orden " + error);
        }
        acceso.cerrarConexion();
    }
    
    public Double buscarTotalVenta(int id_orden) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select sum(b.precio_item) as valor from detalle_venta as a, item as b where a.id_item = b.id_item and a.id_venta = (select c.id_venta from orden as c where c.id_orden = " + id_orden + ") and a.estado_detalle_venta = 'D'";
        Double valor = 0.0;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            while (result.next()) {
                valor = result.getDouble("valor");

            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar venta " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return valor;
    }
}