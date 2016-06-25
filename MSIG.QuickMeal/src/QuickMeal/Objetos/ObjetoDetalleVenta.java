/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoDetalleVenta {
    private int id_detalle_venta;
    private int id_venta;
    private int id_item;
    private int cantidad_detalle_venta;

    public int getCantidad_detalle_venta() {
        return cantidad_detalle_venta;
    }

    public void setCantidad_detalle_venta(int cantidad_detalle_venta) {
        this.cantidad_detalle_venta = cantidad_detalle_venta;
    }

    public int getId_detalle_venta() {
        return id_detalle_venta;
    }

    public void setId_detalle_venta(int id_detalle_venta) {
        this.id_detalle_venta = id_detalle_venta;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }   
}
