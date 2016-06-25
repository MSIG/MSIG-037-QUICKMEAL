/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoLoggin {
    private int id_usuario;
    private int id_estacion;
    private String descripcion_estacion;
    private String tipo_usuario;
    private String nombre_usuario;
    private String constrasenia_usuario;

    public String getConstrasenia_usuario() {
        return constrasenia_usuario;
    }

    public void setConstrasenia_usuario(String constrasenia_usuario) {
        this.constrasenia_usuario = constrasenia_usuario;
    }

    public String getDescripcion_estacion() {
        return descripcion_estacion;
    }

    public void setDescripcion_estacion(String descripcion_estacion) {
        this.descripcion_estacion = descripcion_estacion;
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

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getTipo_usuario() {
        return tipo_usuario;
    }

    public void setTipo_usuario(String tipo_usuario) {
        this.tipo_usuario = tipo_usuario;
    }
    
}
