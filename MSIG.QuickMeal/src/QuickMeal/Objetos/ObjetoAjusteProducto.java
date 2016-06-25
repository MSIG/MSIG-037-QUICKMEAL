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
public class ObjetoAjusteProducto {
    
    private int     id_ajusteproducto;
    private int     id_producto;
    private double  cantidad_ajusteproducto;
    private double  existencia_ajusteproducto;
    private String  descripcion_ajusteproducto;
    private String  motivo_ajusteproducto;
    private String  fecha_movimiento;
    private int     id_usuario;
    private String  tipo_ajuste;

    public int getId_ajusteproducto() {
        return id_ajusteproducto;
    }

    public void setId_ajusteproducto(int pId_ajusteproducto) {
        this.id_ajusteproducto = pId_ajusteproducto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int pId_producto) {
        this.id_producto = pId_producto;
    }

    public double getCantidad_ajusteproducto() {
        return cantidad_ajusteproducto;
    }

    public void setCantidad_ajusteproducto(double pCantidad_ajusteproducto) {
        this.cantidad_ajusteproducto = pCantidad_ajusteproducto;
    }
    
    public double getExistencia_ajusteproducto() {
        return existencia_ajusteproducto;
    }

    public void setExistencia_ajusteproducto(double pExistencia_ajusteproducto) {
        this.existencia_ajusteproducto = pExistencia_ajusteproducto;
    }

    public String getDescripcion_ajusteproducto() {
        return descripcion_ajusteproducto;
    }

    public void setDescripcion_ajusteproducto(String pDescripcion_ajusteproducto) {
        this.descripcion_ajusteproducto = pDescripcion_ajusteproducto;
    }

    public String getMotivo_ajusteproducto() {
        return motivo_ajusteproducto;
    }

    public void setMotivo_ajusteproducto(String pMotivo_ajusteproducto) {
        this.motivo_ajusteproducto = pMotivo_ajusteproducto;
    }

    public String getFecha_movimiento() {
        return fecha_movimiento;
    }

    public void setFecha_movimiento(String pFecha_movimiento) {
        this.fecha_movimiento = pFecha_movimiento;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int pId_usuario) {
        this.id_usuario = pId_usuario;
    } 
    
    public String getTipo_ajuste() {
        return tipo_ajuste;
    }

    public void setTipo_ajuste(String pTipo_ajuste) {
        this.tipo_ajuste = pTipo_ajuste;
    }
}
