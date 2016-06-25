/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoReportePropinas;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoReportePropinas {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoReportePropinas> ejecutar(String fecha_inicial, String fecha_final) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select x.id_empleado as empleado, y.venta as valor_ventas, "
                + " x.propina as valor_propinas from   ( select b.id_empleado, sum(b.propina_venta) as propina "
                + " from venta as b, cliente as c, orden as d where str_to_date(date_format(b.fecha_venta, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha_inicial + "', '%d-%m-%Y') and str_to_date('" + fecha_final + "', '%d-%m-%Y') "
                + " and b.id_cliente = c.id_cliente and c.cc_cliente = '0' and b.id_venta = d.id_venta "
                + " and d.estado_orden = 'E' group by b.id_empleado ) as x, ( "
                + " select  b.id_empleado, sum(e.precio_item) as venta from    detalle_venta as a, "
                + " venta as b, cliente as c, orden as d, item as e where   a.id_venta = b.id_venta "
                + " and a.id_venta = d.id_venta and b.id_venta = d.id_venta "
                + " and a.id_item = e.id_item and b.id_cliente = c.id_cliente and c.cc_cliente = '0'"
                + " and d.estado_orden = 'E' and a.estado_detalle_venta = 'D' "
                + " and str_to_date(date_format(b.fecha_venta, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha_inicial + "', '%d-%m-%Y') and str_to_date('" + fecha_final + "', '%d-%m-%Y') "
                + "group by b.id_empleado ) as y where x.id_empleado = y.id_empleado";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoReportePropinas registro;
            while (result.next()) {
                registro = new ObjetoReportePropinas();
                registro.setId_empleado(result.getInt("empleado"));
                registro.setValor_ventas(result.getDouble("valor_ventas"));
                registro.setValor_propinas(result.getDouble("valor_propinas"));
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