/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoOrden;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoOrden {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoOrden> listar(String estado) {
        
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select  a.id_orden, a.id_venta, a.estado_orden, "
                + "a.fecha_orden, a.tiempo_orden, c.nombre_cliente, b.nota_venta "
                + " from    orden as a, venta as b, cliente as c "
                + "where   a.id_venta = b.id_venta "
                + "and b.id_cliente = c.id_cliente and a.estado_orden = '" + estado 
                + "' order by a.id_orden ";
        
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoOrden registro;
            while (result.next()) {
                registro = new ObjetoOrden();
                registro.setId_orden(result.getInt("id_orden"));
                registro.setId_venta(result.getInt("id_venta"));
                registro.setEstado_orden(result.getString("estado_orden"));
                registro.setFecha_orden(result.getString("fecha_orden"));
                registro.setTiempo_orden(result.getString("tiempo_orden"));
                registro.setNombre_cliente(result.getString("nombre_cliente"));
                registro.setNota_venta(result.getString("nota_venta"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar ordenes " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoOrden> listarPorFecha(String fecha) {
        
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_orden, a.id_venta, a.estado_orden, a.fecha_orden, a.tiempo_orden, c.id_cliente, c.nombre_cliente from orden as a, venta as b, cliente as c where a.id_venta = b.id_venta and str_to_date(date_format(a.fecha_orden, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha + "', '%d-%m-%Y') and str_to_date('" + fecha + "', '%d-%m-%Y') and b.id_cliente = c.id_cliente order by a.id_orden ";
         
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoOrden registro;
            while (result.next()) {
                registro = new ObjetoOrden();
                registro.setId_orden(result.getInt("id_orden"));
                registro.setId_venta(result.getInt("id_venta"));
                registro.setEstado_orden(result.getString("estado_orden"));
                registro.setFecha_orden(result.getString("fecha_orden"));
                registro.setTiempo_orden(result.getString("tiempo_orden"));
                registro.setId_cliente(result.getInt("id_cliente"));
                registro.setNombre_cliente(result.getString("nombre_cliente"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar ordenes " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoOrden> listarPorRangoFecha(String inicio, String fin, String codigo) {
        
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_orden, a.id_venta, a.estado_orden, "
                + "a.fecha_orden, a.tiempo_orden, c.nombre_cliente "
                + "from orden as a, venta as b, cliente as c "
                + "where a.id_venta = b.id_venta and a.estado_orden = 'E' and c.codigo_cliente = " + codigo + " and "
                + "str_to_date(date_format(a.fecha_orden,'%d-%m-%Y'),'%d-%m-%Y') between str_to_date('" + inicio + "', '%d-%m-%Y') "
                + "and str_to_date('" + fin + "','%d-%m-%Y') and b.id_cliente = c.id_cliente order by a.id_orden ";
         
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoOrden registro;
            while (result.next()) {
                registro = new ObjetoOrden();
                registro.setId_orden(result.getInt("id_orden"));
                registro.setId_venta(result.getInt("id_venta"));
                registro.setEstado_orden(result.getString("estado_orden"));
                registro.setFecha_orden(result.getString("fecha_orden"));
                registro.setTiempo_orden(result.getString("tiempo_orden"));
                registro.setNombre_cliente(result.getString("nombre_cliente"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar ordenes " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public void insertarOrden(int id_venta) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_orden(" + id_venta + ", 'P', CURRENT_TIMESTAMP(), 0) as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en insertar orden " + error);
        }
        acceso.cerrarConexion();
    }

    public void cancelarOrden(String id_orden) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select cancelar_orden(" + id_orden + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en cancelar orden " + error);
        }
        acceso.cerrarConexion();
    }

    public void despacharOrden(String id_orden) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select despachar_orden(" + id_orden + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en cancelar orden " + error);
        }
        acceso.cerrarConexion();
    }

    public void entregarOrden(String id_orden) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select entregar_orden(" + id_orden + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en cancelar orden " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void guardarCuenta(String orden, String cuenta) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_cuenta_orden(" + orden + ",'" + cuenta + "') as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
            //mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en cancelar orden " + error);
        }
        acceso.cerrarConexion();
    }
    
    public void activarOrden(int id_orden) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select activar_orden(" + id_orden + ") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error en cancelar orden " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoOrden> consultarOrden(String cliente, String estacion, String usuario) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from orden where fecha_orden in (select max(a.fecha_orden)  from orden as a, venta as b where a.id_venta = b.id_venta and id_usuario = " + usuario + " and id_estacion = " + estacion + " and id_cliente in (select id_cliente from cliente where codigo_cliente = " + cliente + "))";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoOrden registro;
            while (result.next()) {
                registro = new ObjetoOrden();
                registro.setId_orden(result.getInt("id_orden"));
                registro.setId_venta(result.getInt("id_venta"));
                registro.setEstado_orden(result.getString("estado_orden"));
                registro.setFecha_orden(result.getString("fecha_orden"));
                registro.setTiempo_orden(result.getString("tiempo_orden"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar orden " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}