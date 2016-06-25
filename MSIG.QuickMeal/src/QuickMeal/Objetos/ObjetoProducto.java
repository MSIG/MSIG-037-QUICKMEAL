/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickMeal.Objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class ObjetoProducto {
    
    private int     id_producto;
    private int     id_proveedor;
    private String  descripcion_producto;
    private double  costo_producto;
    private double  cantidad_producto;
    private String  unidad_medida_producto;
    private int     caducidad_producto;
    private int     minimo_producto;
    private String  fecha_caduciad_producto;
    private String  descripcion_proveedor;

    public int getCaducidad_producto() {
        return caducidad_producto;
    }

    public void setCaducidad_producto(int caducidad_producto) {
        this.caducidad_producto = caducidad_producto;
    }

    public double getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(double cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public double getCosto_producto() {
        return costo_producto;
    }

    public void setCosto_producto(double costo_producto) {
        this.costo_producto = costo_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getDescripcion_proveedor() {
        return descripcion_proveedor;
    }

    public void setDescripcion_proveedor(String descripcion_proveedor) {
        this.descripcion_proveedor = descripcion_proveedor;
    }

    public String getFecha_caduciad_producto() {
        return fecha_caduciad_producto;
    }

    public void setFecha_caduciad_producto(String fecha_caduciad_producto) {
        this.fecha_caduciad_producto = fecha_caduciad_producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public int getMinimo_producto() {
        return minimo_producto;
    }

    public void setMinimo_producto(int minimo_producto) {
        this.minimo_producto = minimo_producto;
    }

    public String getUnidad_medida_producto() {
        return unidad_medida_producto;
    }

    public void setUnidad_medida_producto(String unidad_medida_producto) {
        this.unidad_medida_producto = unidad_medida_producto;
    }
    
}
