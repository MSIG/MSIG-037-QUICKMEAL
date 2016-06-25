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
public class ObjetoLote {
    private int     id_lote;
    private int     id_detalle_compra;
    private String  fecha_caduca_lote;
    private String  estado_lote;
    private String  nota_lote;

    public String getEstado_lote() {
        return estado_lote;
    }

    public void setEstado_lote(String estado_lote) {
        this.estado_lote = estado_lote;
    }

    public String getFecha_caduca_lote() {
        return fecha_caduca_lote;
    }

    public void setFecha_caduca_lote(String fecha_caduca_lote) {
        this.fecha_caduca_lote = fecha_caduca_lote;
    }

    public int getId_detalle_compra() {
        return id_detalle_compra;
    }

    public void setId_detalle_compra(int id_detalle_compra) {
        this.id_detalle_compra = id_detalle_compra;
    }

    public int getId_lote() {
        return id_lote;
    }

    public void setId_lote(int id_lote) {
        this.id_lote = id_lote;
    }

    public String getNota_lote() {
        return nota_lote;
    }

    public void setNota_lote(String nota_lote) {
        this.nota_lote = nota_lote;
    }

}
