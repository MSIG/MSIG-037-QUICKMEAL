/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoIngrediente {
    private int id_ingrediente;
    private int id_producto;
    private String descripcion_producto;
    private int id_item;
    private String descripcion_item;
    private float proporcion_ingrediente;
    private String unidad_medida_producto;

    public String getDescripcion_item() {
        return descripcion_item;
    }

    public void setDescripcion_item(String descripcion_item) {
        this.descripcion_item = descripcion_item;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public int getId_ingrediente() {
        return id_ingrediente;
    }

    public void setId_ingrediente(int id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public float getProporcion_ingrediente() {
        return proporcion_ingrediente;
    }

    public void setProporcion_ingrediente(float proporcion_ingrediente) {
        this.proporcion_ingrediente = proporcion_ingrediente;
    }

    public String getUnidad_medida_producto() {
        return unidad_medida_producto;
    }

    public void setUnidad_medida_producto(String unidad_medida_producto) {
        this.unidad_medida_producto = unidad_medida_producto;
    }
    
}
