/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoReportePropinas {
    private int id_empleado;
    private double valor_ventas;
    private double valor_propinas;

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public double getValor_propinas() {
        return valor_propinas;
    }

    public void setValor_propinas(double valor_propinas) {
        this.valor_propinas = valor_propinas;
    }

    public double getValor_ventas() {
        return valor_ventas;
    }

    public void setValor_ventas(double valor_ventas) {
        this.valor_ventas = valor_ventas;
    }
    
}
