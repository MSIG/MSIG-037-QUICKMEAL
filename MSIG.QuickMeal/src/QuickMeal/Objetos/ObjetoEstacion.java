/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoEstacion {
    private int id_estacion;
    private String descripcion_estacion;
    private String ubiciacion_estacion;
    private String estado_estacion;

    public String getDescripcion_estacion() {
        return descripcion_estacion;
    }

    public void setDescripcion_estacion(String descripcion_estacion) {
        this.descripcion_estacion = descripcion_estacion;
    }

    public String getEstado_estacion() {
        return estado_estacion;
    }

    public void setEstado_estacion(String estado_estacion) {
        this.estado_estacion = estado_estacion;
    }

    public int getId_estacion() {
        return id_estacion;
    }

    public void setId_estacion(int id_estacion) {
        this.id_estacion = id_estacion;
    }

    public String getUbiciacion_estacion() {
        return ubiciacion_estacion;
    }

    public void setUbiciacion_estacion(String ubiciacion_estacion) {
        this.ubiciacion_estacion = ubiciacion_estacion;
    }
    
}
