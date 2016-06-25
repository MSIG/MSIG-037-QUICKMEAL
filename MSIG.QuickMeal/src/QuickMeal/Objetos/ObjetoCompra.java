/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickMeal.Objetos;

/**
 *
 * @author Gigi
 */
public class ObjetoCompra {
    private int     id_compra;
    private int     id_proveedor;
    private String  factura_compra;
    private String  fecha_compra;
    private String  estado_compra;
    private int     dias_credito_compra;
    
    private String  descripcion_proveedor;

    public String getDescripcion_proveedor() {
        return descripcion_proveedor;
    }

    public void setDescripcion_proveedor(String descripcion_proveedor) {
        this.descripcion_proveedor = descripcion_proveedor;
    }

    public int getDias_credito_compra() {
        return dias_credito_compra;
    }

    public void setDias_credito_compra(int dias_credito_compra) {
        this.dias_credito_compra = dias_credito_compra;
    }

    public String getEstado_compra() {
        return estado_compra;
    }

    public void setEstado_compra(String estado_compra) {
        this.estado_compra = estado_compra;
    }

    public String getFactura_compra() {
        return factura_compra;
    }

    public void setFactura_compra(String factura_compra) {
        this.factura_compra = factura_compra;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

}
