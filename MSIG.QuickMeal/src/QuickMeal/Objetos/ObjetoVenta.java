/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoVenta {
    private int id_venta;
    private int id_cliente;
    private int id_usuario;
    private int id_estacion;
    private String factura_venta;
    private String direccion_venta;
    private String fecha_venta;
    private String nota_venta;
    private double propina_venta;
    private int id_empleado;

    public String getDireccion_venta() {
        return direccion_venta;
    }

    public void setDireccion_venta(String direccion_venta) {
        this.direccion_venta = direccion_venta;
    }

    public String getFactura_venta() {
        return factura_venta;
    }

    public void setFactura_venta(String factura_venta) {
        this.factura_venta = factura_venta;
    }

    public String getFecha_venta() {
        return fecha_venta;
    }

    public void setFecha_venta(String fecha_venta) {
        this.fecha_venta = fecha_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(int id_estacion) {
        this.id_estacion = id_estacion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }
    
    public String getNota_venta() {
        return nota_venta;
    }

    public void setNota_venta(String nota_venta) {
        this.nota_venta = nota_venta;
    }

    public double getPropina_venta() {
        return propina_venta;
    }

    public void setPropina_venta(double propina_venta) {
        this.propina_venta = propina_venta;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

}
