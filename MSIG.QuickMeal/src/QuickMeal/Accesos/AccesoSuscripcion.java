/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoSuscripcion;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoSuscripcion {

    Mensaje mensaje = new Mensaje();

    public void insertarSuscripcion(ObjetoSuscripcion objSuscripcion) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_detalle_servicio(" + objSuscripcion.getId_servicio() + "," + objSuscripcion.getId_cliente() + ",'" + objSuscripcion.getFecha_inicio_detalle_servicio() + "','" + objSuscripcion.getFecha_final_detalle_servicio() + "','" + objSuscripcion.getEstado_detalle_servicio() + "') as result";
            System.out.println("Ejecutando ACCESOSUSCRIPCION " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en suscripciones " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarSuscripcion(ObjetoSuscripcion objSuscripcion) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_detalle_servicio(" + objSuscripcion.getId_detalle_servicio() + "," + objSuscripcion.getId_servicio() + ",'" + objSuscripcion.getId_cliente() + "','" + objSuscripcion.getFecha_inicio_detalle_servicio() + "','" + objSuscripcion.getFecha_final_detalle_servicio() + "','" + objSuscripcion.getEstado_detalle_servicio() + "') as result";
            System.out.println("Ejecutando ACCESOSUSCRIPCION " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en suscripcion " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoSuscripcion> seleccionarSuscripcion(String pIdCliente) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_detalle_servicio, c.codigo_cliente, a.id_servicio, a.id_cliente, a.fecha_inicio_detalle_servicio, a.fecha_final_detalle_servicio, a.estado_detalle_servicio, b.descripcion_servicio from detalle_servicio a, servicio b, cliente c where c.id_cliente = a.id_cliente and a.id_servicio = b.id_servicio and id_cliente = " + pIdCliente;

        try {
            System.out.println("Ejecutando en ACCESO SUSCRIPCION SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoSuscripcion registros;

            while (tabla.next()) {
                registros = new ObjetoSuscripcion();
                registros.setId_detalle_servicio(tabla.getInt("id_detalle_servicio"));
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setId_cliente(tabla.getInt("id_cliente"));
                registros.setCodigo_cliente(tabla.getInt("codigo_cliente"));
                registros.setFecha_inicio_detalle_servicio(tabla.getString("fecha_inicio_detalle_servicio"));
                registros.setFecha_final_detalle_servicio(tabla.getString("fecha_final_detalle_servicio"));
                registros.setEstado_detalle_servicio(tabla.getString("estado_detalle_servicio"));
                registros.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SUSCRIPCION: SELECCION SUSCRIPCION");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoSuscripcion> retornaSuscripcion() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_detalle_servicio, c.codigo_cliente, a.id_servicio, a.id_cliente, a.fecha_inicio_detalle_servicio, a.fecha_final_detalle_servicio, a.estado_detalle_servicio, b.descripcion_servicio "
                + "from detalle_servicio a, servicio b, cliente c "
                + "where c.id_cliente = a.id_cliente and a.id_servicio = b.id_servicio";
        try {
            System.out.println("Ejecutando en ACCESO USUARIO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoSuscripcion registros;

            while (tabla.next()) {
                registros = new ObjetoSuscripcion();
                registros.setId_detalle_servicio(tabla.getInt("id_detalle_servicio"));
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setId_cliente(tabla.getInt("id_cliente"));
                registros.setCodigo_cliente(tabla.getInt("codigo_cliente"));
                registros.setFecha_inicio_detalle_servicio(tabla.getString("fecha_inicio_detalle_servicio"));
                registros.setFecha_final_detalle_servicio(tabla.getString("fecha_final_detalle_servicio"));
                registros.setEstado_detalle_servicio(tabla.getString("estado_detalle_servicio"));
                registros.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SUSCRIPCION: SELECCION SUSCRIPCION");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoSuscripcion> retornaDescripcionServicio() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select descripcion_servicio, id_servicio from servicio";

        try {
            System.out.println("Ejecutando en ACCESO USUARIO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoSuscripcion registros;

            while (tabla.next()) {
                System.out.println("Ejecutando WHILE ");
                registros = new ObjetoSuscripcion();
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SUSCRIPCIO: SELECCION SUSCRIPCION");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoSuscripcion> retornaIdSuscripcion(String pId_servicio, String pId_cliente, String pFecha_i, String pFecha_f, String pEstado) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from detalle_servicio where id_servicio = " + pId_servicio + " and id_cliente = " + pId_cliente + " and fecha_inicio_detalle_servicio = '" + pFecha_i + "' and fecha_final_detalle_servicio = '" + pFecha_f + "' and estado_detalle_servicio = '" + pEstado + "';";

        try {
            System.out.println("Ejecutando en ACCESO SUSCRIPCION SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoSuscripcion registros;

            while (tabla.next()) {
                registros = new ObjetoSuscripcion();
                registros.setId_detalle_servicio(tabla.getInt("id_detalle_servicio"));
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setId_cliente(tabla.getInt("id_cliente"));
                registros.setFecha_inicio_detalle_servicio(tabla.getString("fecha_inicio_detalle_servicio"));
                registros.setFecha_final_detalle_servicio(tabla.getString("fecha_final_detalle_servicio"));
                registros.setEstado_detalle_servicio(tabla.getString("estado_detalle_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SUSCRIPCION: SELECCION SUSCRIPCION");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}
