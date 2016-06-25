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
public class ObjetoServicio {
    private int     id_servicio;
    private String  descripcion_servicio;
    private double  costo_servicio;
    private double  precio_servicio;

    public double getCosto_servicio() {
        return costo_servicio;
    }

    public void setCosto_servicio(double costo_servicio) {
        this.costo_servicio = costo_servicio;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public double getPrecio_servicio() {
        return precio_servicio;
    }

    public void setPrecio_servicio(double precio_servicio) {
        this.precio_servicio = precio_servicio;
    }
    
}
