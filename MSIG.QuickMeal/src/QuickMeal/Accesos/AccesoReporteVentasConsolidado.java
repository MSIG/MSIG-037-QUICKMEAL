/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoVentaConsolidado;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoReporteVentasConsolidado {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoVentaConsolidado> ejecutar(String fecha_inicial, String fecha_final, String codigo) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        
        if(codigo.equals("0")){
            codigo = "null";
        }
        
        
        String sql = "select  * "
                + " from    vw_historial_compras_x_cliente_consolidado"
                + " where   codigo_cliente = ifnull("+codigo+",codigo_cliente) and str_to_date(date_format(fecha_venta, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha_inicial + "', '%d-%m-%Y') and str_to_date('" + fecha_final + "', '%d-%m-%Y')";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoVentaConsolidado registro;
            while (result.next()) {
                registro = new ObjetoVentaConsolidado();
                registro.setCodigo_cliente(result.getInt("codigo_cliente"));
                registro.setCc_cliente(result.getString("cc_cliente"));
                
                //buscar si exite algun regsitro guardado de cuenta para el vale
                //si no exite tomar el que esta en el maestro de clientes
                String str_consulta = "select max(cuenta_cliente) cuenta from cuenta_orden where id_orden = (select max(id_orden) from orden where id_venta = " + result.getInt("no_venta") + " )";
                System.out.println("Ejecutando sub consulta: " +  str_consulta);
                ResultSet rs_cuenta = acceso.listarRegistros(str_consulta);
                registro.setCuenta_cliente(result.getString("cuenta_cliente"));
                while(rs_cuenta.next()){
                    System.out.println("Se encontro una cuenta asociada a esta venta");
                    registro.setCuenta_cliente(rs_cuenta.getString("cuenta"));
                }
                rs_cuenta.close();
                acceso.cerrarConexion();
                
                registro.setNombre_cliente(result.getString("nombre_cliente"));
                registro.setEmpresa_cliente(result.getString("empresa_cliente"));
                registro.setNo_venta(result.getInt("no_venta"));
                registro.setFecha_venta(result.getString("fecha_venta"));
                registro.setPropina_venta(result.getDouble("propina_venta"));
                registro.setTotal_venta(result.getDouble("total_venta"));
                registro.setDescuento_venta(result.getDouble("descuento_venta"));
                registro.setExtra_venta(result.getDouble("extra_venta"));
                registro.setTotal(result.getDouble("total"));
                registro.setPago(result.getString("pago"));
                registro.setEmpleado(result.getString("empleado"));
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
    
    public double total(String fecha_inicial, String fecha_final, String codigo) {
        double valor = 0;
        Conexion acceso = new Conexion();
        
        if(codigo.equals("0")){
            codigo = "null";
        }
        
        
        String sql = "select sum(total) total"
                + " from    vw_historial_compras_x_cliente_consolidado"
                + " where   codigo_cliente = ifnull("+codigo+",codigo_cliente) and str_to_date(date_format(fecha_venta, '%d-%m-%Y'), '%d-%m-%Y') between str_to_date('" + fecha_inicial + "', '%d-%m-%Y') and str_to_date('" + fecha_final + "', '%d-%m-%Y')";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoVentaConsolidado registro;
            while (result.next()) {
                registro = new ObjetoVentaConsolidado();
                registro.setTotal(result.getDouble("total"));
                valor = registro.getTotal();
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al ejecutar sql del total " + error);
            acceso.cerrarConexion();
            return 0;
        }
        acceso.cerrarConexion();
        return valor;
    }

}