/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoReporteSocios;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoReporteSocios {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoReporteSocios> ejecutar(String fecha_inicial, String fecha_final, String codigo) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select codigo_cliente, sum(total) + sum(propina_venta) as total "
                + "from vw_historial_compras_x_cliente_consolidado "
                + "where str_to_date(date_format(fecha_venta, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha_inicial + "', '%d-%m-%Y') and str_to_date('" + fecha_final + "', '%d-%m-%Y') "
                + "and pago = 'Credito' and cc_cliente = '0' and codigo_cliente = ifnull("+codigo+",codigo_cliente) group by codigo_cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoReporteSocios registro;
            while (result.next()) {
                registro = new ObjetoReporteSocios();
                registro.setCodigo(result.getInt("codigo_cliente"));
                registro.setTipo_prestamo("6010");
                registro.setFecha_inicio(fecha_inicial);
                registro.setFecha_fin(fecha_final);
                registro.setCodigo_prestamo("01");
                registro.setFecha_autorizacion(fecha_final);
                registro.setImporte_prestamo(result.getDouble("total"));
                registro.setCondicion_prestamo("01 Sin intereses");
                registro.setFecha_ini_amortizacion(fecha_final);
                registro.setClave_moneda("GTQ");
                registro.setCuota_amortizacion(result.getDouble("total"));
                registro.setInteres(0.0);
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