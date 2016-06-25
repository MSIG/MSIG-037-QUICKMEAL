/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoReporteAreas;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoReporteAreas {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoReporteAreas> ejecutar(String fecha_inicial, String fecha_final) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select nombre_cliente, cc_cliente, cuenta_cliente, sum(total) as total "
                + "from vw_historial_compras_x_cliente_consolidado "
                + "where str_to_date(date_format(fecha_venta, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha_inicial + "', '%d-%m-%Y') and str_to_date('" + fecha_final + "', '%d-%m-%Y') "
                + "and pago = 'Credito' and cc_cliente <> '0' group by nombre_cliente, cc_cliente, cuenta_cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoReporteAreas registro;
            while (result.next()) {
                registro = new ObjetoReporteAreas();
                registro.setDescripcion(result.getString("nombre_cliente"));
                registro.setCentro_costo(result.getString("cc_cliente"));
                registro.setCuenta(result.getString("cuenta_cliente"));
                registro.setTotal(result.getDouble("total"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al ejecutar sql del reporte " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

}