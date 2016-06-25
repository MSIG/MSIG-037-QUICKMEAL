/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */

public class ObjetoOrden {
    private int id_orden;
    private int id_venta;
    private String estado_orden;
    private String fecha_orden;
    private String tiempo_orden;
    private String nota_venta;
    
    //variable para las ordenes activas
    private int id_cliente;
    private String nombre_cliente;

    public String getEstado_orden() {
        return estado_orden;
    }

    public void setEstado_orden(String estado_orden) {
        this.estado_orden = estado_orden;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public int getId_orden() {
        return id_orden;
    }

    public void setId_orden(int id_orden) {
        this.id_orden = id_orden;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public String getTiempo_orden() {
        return tiempo_orden;
    }

    public void setTiempo_orden(String tiempo_orden) {
        this.tiempo_orden = tiempo_orden;
    }

    public String getNota_venta() {
        return nota_venta;
    }

    public void setNota_venta(String nota_venta) {
        this.nota_venta = nota_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }
    
}
