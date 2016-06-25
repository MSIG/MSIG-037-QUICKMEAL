/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoDetalleCliente;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class AccesoDetalleCliente {

    Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoDetalleCliente> seleccionarCliente(String pValor) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select  a.id_compra, a.factura_compra, a.fecha_compra, b.id_detalle_compra, b.cantidad_compra, b.precio_compra, c.descripcion_producto, d.descripcion_proveedor from  compra a, detalle_compra b, producto c, proveedor d where a.id_compra = b.id_compra and   c.id_producto = b.id_producto and   a.id_proveedor = d.id_proveedor and a.id_compra = " + pValor;

        try {
            System.out.println("Ejecutando en ACCESO CYDC SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoDetalleCliente registros;

            while (tabla.next()) {
                registros = new ObjetoDetalleCliente();
                registros.setId_compra(tabla.getInt("id_compra"));
                registros.setFactura_compra(tabla.getString("factura_compra"));
                registros.setFecha_compra(tabla.getString("fecha_compra"));
                registros.setId_detalle_compra(tabla.getInt("id_detalle_compra"));
                registros.setCantidad_compra(tabla.getFloat("cantidad_compra"));
                registros.setPrecio_compra(tabla.getFloat("precio_compra"));
                registros.setDescripcion_producto(tabla.getString("descripcion_producto"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("Error en AccesoCyDC " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}
