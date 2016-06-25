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
public class ObjetoSuscripcion {
    private int     id_detalle_servicio;
    private int     id_servicio;
    private int     id_cliente;
    private int     codigo_cliente;
    private String  fecha_inicio_detalle_servicio;
    private String  fecha_final_detalle_servicio;
    private String  estado_detalle_servicio;
    private String  descripcion_servicio;

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getDescripcion_servicio() {
        return descripcion_servicio;
    }

    public void setDescripcion_servicio(String descripcion_servicio) {
        this.descripcion_servicio = descripcion_servicio;
    }

    public String getEstado_detalle_servicio() {
        return estado_detalle_servicio;
    }

    public void setEstado_detalle_servicio(String estado_detalle_servicio) {
        this.estado_detalle_servicio = estado_detalle_servicio;
    }

    public String getFecha_final_detalle_servicio() {
        return fecha_final_detalle_servicio;
    }

    public void setFecha_final_detalle_servicio(String fecha_final_detalle_servicio) {
        this.fecha_final_detalle_servicio = fecha_final_detalle_servicio;
    }

    public String getFecha_inicio_detalle_servicio() {
        return fecha_inicio_detalle_servicio;
    }

    public void setFecha_inicio_detalle_servicio(String fecha_inicio_detalle_servicio) {
        this.fecha_inicio_detalle_servicio = fecha_inicio_detalle_servicio;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_detalle_servicio() {
        return id_detalle_servicio;
    }

    public void setId_detalle_servicio(int id_detalle_servicio) {
        this.id_detalle_servicio = id_detalle_servicio;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }
    
}
