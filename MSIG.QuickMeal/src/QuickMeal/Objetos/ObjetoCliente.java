/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Derwin Santa Cruz
 */
public class ObjetoCliente {
    private int id_cliente;
    private int codigo_cliente;
    private String nit_cliente;
    private String cc_cliente;
    private String cuenta_cliente;
    private String nombre_cliente;
    private String direccion_cliente;
    private int telefono_cliente;
    private String empresa_cliente;
    private String estado_cliente;

    public String getCc_cliente() {
        return cc_cliente;
    }

    public void setCc_cliente(String cc_cliente) {
        this.cc_cliente = cc_cliente;
    }

    public int getCodigo_cliente() {
        return codigo_cliente;
    }

    public void setCodigo_cliente(int codigo_cliente) {
        this.codigo_cliente = codigo_cliente;
    }

    public String getCuenta_cliente() {
        return cuenta_cliente;
    }

    public void setCuenta_cliente(String cuenta_cliente) {
        this.cuenta_cliente = cuenta_cliente;
    }

    public String getDireccion_cliente() {
        return direccion_cliente;
    }

    public void setDireccion_cliente(String direccion_cliente) {
        this.direccion_cliente = direccion_cliente;
    }

    public String getEmpresa_cliente() {
        return empresa_cliente;
    }

    public void setEmpresa_cliente(String empresa_cliente) {
        this.empresa_cliente = empresa_cliente;
    }

    public String getEstado_cliente() {
        return estado_cliente;
    }

    public void setEstado_cliente(String estado_cliente) {
        this.estado_cliente = estado_cliente;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNit_cliente() {
        return nit_cliente;
    }

    public void setNit_cliente(String nit_cliente) {
        this.nit_cliente = nit_cliente;
    }

    public String getNombre_cliente() {
        return nombre_cliente;
    }

    public void setNombre_cliente(String nombre_cliente) {
        this.nombre_cliente = nombre_cliente;
    }

    public int getTelefono_cliente() {
        return telefono_cliente;
    }

    public void setTelefono_cliente(int telefono_cliente) {
        this.telefono_cliente = telefono_cliente;
    }   
}
