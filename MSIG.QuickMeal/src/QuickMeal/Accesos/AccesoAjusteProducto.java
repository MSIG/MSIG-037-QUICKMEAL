/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoAjusteProducto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoAjusteProducto {

    Mensaje mensaje = new Mensaje();

    public void insertarAjusteProducto(ObjetoAjusteProducto objProducto) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_ajuste_producto(" + objProducto.getId_producto() + ", " + objProducto.getCantidad_ajusteproducto() + ", " 
                    + objProducto.getExistencia_ajusteproducto() + ", '" + objProducto.getDescripcion_ajusteproducto() 
                    + "', '" + objProducto.getMotivo_ajusteproducto() + "', " 
                    + objProducto.getId_usuario() + ", '" + objProducto.getTipo_ajuste() + "') as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en insertarAjusteProducto " + error);
        }
        acceso.cerrarConexion();
    }
}
