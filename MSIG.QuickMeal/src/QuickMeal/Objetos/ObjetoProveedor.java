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
public class ObjetoProveedor {
    private int     id_proveedor;
    private String  nit_proveedor;
    private String  descripcion_proveedor;
    private String  direccion_proveedor;
    private String  contacto_proveedor;
    private int     telefono_proveedor;
    private int     dias_credito_proveedor;

    public String getContacto_proveedor() {
        return contacto_proveedor;
    }

    public void setContacto_proveedor(String contacto_proveedor) {
        this.contacto_proveedor = contacto_proveedor;
    }

    public String getDescripcion_proveedor() {
        return descripcion_proveedor;
    }

    public void setDescripcion_proveedor(String descripcion_proveedor) {
        this.descripcion_proveedor = descripcion_proveedor;
    }

    public String getDireccion_proveedor() {
        return direccion_proveedor;
    }

    public void setDireccion_proveedor(String direccion_proveedor) {
        this.direccion_proveedor = direccion_proveedor;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getNit_proveedor() {
        return nit_proveedor;
    }

    public void setNit_proveedor(String nit_proveedor) {
        this.nit_proveedor = nit_proveedor;
    }

    public int getTelefono_proveedor() {
        return telefono_proveedor;
    }

    public void setTelefono_proveedor(int telefono_proveedor) {
        this.telefono_proveedor = telefono_proveedor;
    }

    public int getDias_credito_proveedor() {
        return dias_credito_proveedor;
    }

    public void setDias_credito_proveedor(int dias_credito_proveedor) {
        this.dias_credito_proveedor = dias_credito_proveedor;
    }
 
}
