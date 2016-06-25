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
public class ObjetoEmpleado {
    private int     id_empleado;
    private String  tipo_empleado;
    private String  nombre_empleado;
    private String  estado_empleado;
    private double  bono_empleado;
    private String  empresa_empleado;
    private String  fecha_ingreso;
    private String  fecha_salida;

    public double getBono_empleado() {
        return bono_empleado;
    }

    public void setBono_empleado(double bono_empleado) {
        this.bono_empleado = bono_empleado;
    }

    public String getEmpresa_empleado() {
        return empresa_empleado;
    }

    public void setEmpresa_empleado(String empresa_empleado) {
        this.empresa_empleado = empresa_empleado;
    }

    public String getEstado_empleado() {
        return estado_empleado;
    }

    public void setEstado_empleado(String estado_empleado) {
        this.estado_empleado = estado_empleado;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(String fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre_empleado() {
        return nombre_empleado;
    }

    public void setNombre_empleado(String nombre_empleado) {
        this.nombre_empleado = nombre_empleado;
    }

    public String getTipo_empleado() {
        return tipo_empleado;
    }

    public void setTipo_empleado(String tipo_empleado) {
        this.tipo_empleado = tipo_empleado;
    }

}
