/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */

public class ObjetoItem {
    private int id_item;
    private int id_categoria;
    private String descripcion_item;
    private float costo_item;
    private float precio_item;
    private String estado_item;

    public float getCosto_item() {
        return costo_item;
    }

    public void setCosto_item(float costo_item) {
        this.costo_item = costo_item;
    }

    public String getDescripcion_item() {
        return descripcion_item;
    }

    public void setDescripcion_item(String descripcion_item) {
        this.descripcion_item = descripcion_item;
    }

    public String getEstado_item() {
        return estado_item;
    }

    public void setEstado_item(String estado_item) {
        this.estado_item = estado_item;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public float getPrecio_item() {
        return precio_item;
    }

    public void setPrecio_item(float precio_item) {
        this.precio_item = precio_item;
    }
}
