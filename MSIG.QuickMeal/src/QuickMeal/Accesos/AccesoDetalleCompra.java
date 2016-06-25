/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoDetalleCompra;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoDetalleCompra {

    Mensaje mensaje = new Mensaje();

    public void insertarDetalleCompra(ObjetoDetalleCompra objDetalleCompra) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_detalle_compra(" + objDetalleCompra.getId_compra() + ",'" + objDetalleCompra.getId_producto() + "','" + objDetalleCompra.getCantidad_compra() + "','" + objDetalleCompra.getPrecio_compra() + "') as result";
            System.out.println("Ejecutando " + sql);
            acceso.ejecutarFuncion(sql);
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en detalle compra " + error);
        }
        acceso.cerrarConexion();
    }
}
