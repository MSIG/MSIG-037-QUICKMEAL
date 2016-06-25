/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoVentaConsolidado {
    private int codigo_cliente;
    private String cc_cliente;
    private String cuenta_cliente;
    private String nombre_cliente;
    private String empresa_cliente;
    private int no_venta;
    private String fecha_venta;
    private Double propina_venta;
    private Double total_venta;
    private Double descuento_venta;
    private Double extra_venta;
    private Double total;
    private String pago;
    private String empleado;

    public String getCc_cliente() {
        return cc_cliente;
    }

    public void setCc_cliente(String cc_cliente) {
        this.cc_cliente = cc_cliente;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getCuenta_cliente() {
        return cuenta_cliente;
    }

    public void setCuenta_cliente(String cuenta_cliente) {
        this.cuenta_cliente = cuenta_cliente;
    }

    public Double getDescuento_venta() {
        return descuento_venta;
    }

    public void setDescuento_venta(Double descuento_venta) {
        this.descuento_venta = descuento_venta;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getEmpresa_cliente() {
        return empresa_cliente;
    }

    public void setEmpresa_cliente(String empresa_cliente) {
        this.empresa_cliente = empresa_cliente;
    }

    public Double getExtra_venta() {
        return extra_venta;
    }

    public void setExtra_venta(Double extra_venta) {
        this.extra_venta = extra_venta;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getNo_venta() {
        return no_venta;
    }

    public void setNo_venta(int no_venta) {
        this.no_venta = no_venta;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public Double getPropina_venta() {
        return propina_venta;
    }

    public void setPropina_venta(Double propina_venta) {
        this.propina_venta = propina_venta;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getTotal_venta() {
        return total_venta;
    }

    public void setTotal_venta(Double total_venta) {
        this.total_venta = total_venta;
    }
    
}
