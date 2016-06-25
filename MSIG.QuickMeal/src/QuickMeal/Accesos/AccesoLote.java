/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoLote;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoLote {

    Mensaje mensaje = new Mensaje();

    public void insertarLote(ObjetoLote objLote) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_lote(" + objLote.getId_detalle_compra() + ",'" + objLote.getEstado_lote() + "','" + objLote.getNota_lote() + "') as result";
            System.out.println("Ejecutando ACCESOLOTE" + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en lotes " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarLote(ObjetoLote objLote) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_producto(" + objLote.getId_lote() + "," + objLote.getId_detalle_compra() + ", '" + objLote.getFecha_caduca_lote() + "','" + objLote.getEstado_lote() + "','" + objLote.getNota_lote() + "') as result";
            System.out.println("Ejecutando ACCESOLOTE " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al actualizar en lotes " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoLote> seleccionarLote(String vDescripcion_producto) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from lote;";

        try {
            System.out.println("Ejecutando en ACCESO LOTE SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoLote registros;

            while (tabla.next()) {
                registros = new ObjetoLote();
                registros.setId_lote(tabla.getInt("id_lote"));
                registros.setId_detalle_compra(tabla.getInt("id_detalle_compra"));
                registros.setFecha_caduca_lote(tabla.getString("fecha_caduca_lote"));
                registros.setEstado_lote(tabla.getString("estado_lote"));
                registros.setNota_lote(tabla.getString("nota_lote"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO LOTE: SELECCION LOTE");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoLote> retornaLote() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from lote;";

        try {
            System.out.println("Ejecutando en ACCESO LOTE SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoLote registros;

            while (tabla.next()) {
                registros = new ObjetoLote();
                registros = new ObjetoLote();
                registros.setId_lote(tabla.getInt("id_lote"));
                registros.setId_detalle_compra(tabla.getInt("id_detalle_compra"));
                registros.setFecha_caduca_lote(tabla.getString("fecha_caduca_lote"));
                registros.setEstado_lote(tabla.getString("estado_lote"));
                registros.setNota_lote(tabla.getString("nota_lote"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO LOTE: SELECCION LOTE");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoLote> buscarLote(String pId_detalle_compra, String pFecha_caduca_lote, String pEstado_lote, String pNota_lote) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from lote where id_detalle_compra = " + pId_detalle_compra + " and fecha_caduca_lote = '" + pFecha_caduca_lote + "' and estado_lote = '" + pEstado_lote + "' and nota_lote = '" + pNota_lote + "';";

        try {
            System.out.println("EJECUTANDO EN ACCESO LOTE " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoLote Registros;

                while (tabla.next()) {
                    Registros = new ObjetoLote();
                    Registros.setId_lote(tabla.getInt("id_lote"));
                    Registros.setId_detalle_compra(tabla.getInt("id_detalle_compra"));
                    Registros.setFecha_caduca_lote(tabla.getString("fecha_caduca_lote"));
                    Registros.setEstado_lote(tabla.getString("estado_lote"));
                    Registros.setNota_lote(tabla.getString("nota_lote"));

                    System.out.println("REGISTROS ");
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            System.out.println("EC ACCESO_LOTE BUSCAR_LOTE " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}
