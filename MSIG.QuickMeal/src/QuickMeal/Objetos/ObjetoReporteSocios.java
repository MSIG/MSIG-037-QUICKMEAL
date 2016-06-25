/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoReporteSocios {
    private int codigo;
    private String tipo_prestamo;
    private String fecha_inicio;
    private String fecha_fin;
    private String codigo_prestamo;
    private String fecha_autorizacion;
    private double importe_prestamo;
    private String condicion_prestamo;
    private String fecha_ini_amortizacion;
    private String clave_moneda;
    private double cuota_amortizacion;
    private double interes;

    public String getClave_moneda() {
        return clave_moneda;
    }

    public void setClave_moneda(String clave_moneda) {
        this.clave_moneda = clave_moneda;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigo_prestamo() {
        return codigo_prestamo;
    }

    public void setCodigo_prestamo(String codigo_prestamo) {
        this.codigo_prestamo = codigo_prestamo;
    }

    public String getCondicion_prestamo() {
        return condicion_prestamo;
    }

    public void setCondicion_prestamo(String condicion_prestamo) {
        this.condicion_prestamo = condicion_prestamo;
    }

    public double getCuota_amortizacion() {
        return cuota_amortizacion;
    }

    public void setCuota_amortizacion(double cuota_amortizacion) {
        this.cuota_amortizacion = cuota_amortizacion;
    }

    public String getFecha_autorizacion() {
        return fecha_autorizacion;
    }

    public void setFecha_autorizacion(String fecha_autorizacion) {
        this.fecha_autorizacion = fecha_autorizacion;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getFecha_ini_amortizacion() {
        return fecha_ini_amortizacion;
    }

    public void setFecha_ini_amortizacion(String fecha_ini_amortizacion) {
        this.fecha_ini_amortizacion = fecha_ini_amortizacion;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public double getImporte_prestamo() {
        return importe_prestamo;
    }

    public void setImporte_prestamo(double importe_prestamo) {
        this.importe_prestamo = importe_prestamo;
    }

    public double getInteres() {
        return interes;
    }

    public void setInteres(double interes) {
        this.interes = interes;
    }

    public String getTipo_prestamo() {
        return tipo_prestamo;
    }

    public void setTipo_prestamo(String tipo_prestamo) {
        this.tipo_prestamo = tipo_prestamo;
    }

}
