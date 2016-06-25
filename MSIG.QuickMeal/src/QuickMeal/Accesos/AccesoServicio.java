/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoServicio;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Gigi
 */
public class AccesoServicio {

    Mensaje mensaje = new Mensaje();

    public void insertarServicio(ObjetoServicio objServicio) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_servicio('" + objServicio.getDescripcion_servicio() + "'," + objServicio.getCosto_servicio() + "," + objServicio.getPrecio_servicio() + ") as result";
            System.out.println("Ejecutando ACCESOSERVICIO " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en servicios " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarServicio(ObjetoServicio objServicio) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_servicio(" + objServicio.getId_servicio() + ", '" + objServicio.getDescripcion_servicio() + "'," + objServicio.getCosto_servicio() + "," + objServicio.getPrecio_servicio() + ") as result";
            System.out.println("Ejecutando ACCESOSERVICIO " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en servicios " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoServicio> seleccionarServicio(String vDescripcion_servicio) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from servicio where descripcion_servicio like '%" + vDescripcion_servicio + "%'";

        try {
            System.out.println("Ejecutando en ACCESO servicio SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoServicio registros;

            while (tabla.next()) {
                registros = new ObjetoServicio();
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registros.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registros.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SERVICIO: SELECCION SERVICIO");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoServicio> retornaServicios() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from servicio;";

        try {
            System.out.println("Ejecutando en ACCESO servicio SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoServicio registros;

            while (tabla.next()) {
                registros = new ObjetoServicio();
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registros.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registros.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SERVICIO: SELECCION SERVICIO");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoServicio> buscarCostoServicio(int codigo_cliente) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select b.* "
                + "from detalle_servicio a, servicio b, cliente c "
                + "where a.id_servicio = b.id_servicio and a.id_cliente = c.id_cliente and c.codigo_cliente = " + codigo_cliente;

        try {
            System.out.println("Ejecutando: " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoServicio registros;

            while (tabla.next()) {
                registros = new ObjetoServicio();
                registros.setId_servicio(tabla.getInt("id_servicio"));
                registros.setDescripcion_servicio(tabla.getString("descripcion_servicio"));
                registros.setCosto_servicio(tabla.getDouble("costo_servicio"));
                registros.setPrecio_servicio(tabla.getDouble("precio_servicio"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO SERVICIO: SELECCION SERVICIO");
            acceso.cerrarConexion();
            return null;
        } finally {
            acceso.cerrarConexion();
        }
        return lista;
    }
}
