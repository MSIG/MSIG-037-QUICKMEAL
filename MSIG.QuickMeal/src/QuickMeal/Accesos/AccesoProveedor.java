/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoProveedor;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoProveedor {

    Mensaje mensaje = new Mensaje();

    public void insertarProveedor(ObjetoProveedor objProveedor) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_proveedor('" + objProveedor.getNit_proveedor() + "','" 
                    + objProveedor.getDescripcion_proveedor() + "','" + objProveedor.getDireccion_proveedor() + "','" 
                    + objProveedor.getContacto_proveedor() + "'," + objProveedor.getTelefono_proveedor() + ", " 
                    + objProveedor.getDias_credito_proveedor() +") as result";
            System.out.println("Ejecutando ACCESOPROVEEDOR " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en proveedores " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarProveedor(ObjetoProveedor objProveedor) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_proveedor(" + objProveedor.getId_proveedor() + ", '" 
                    + objProveedor.getNit_proveedor() + "','" + objProveedor.getDescripcion_proveedor() 
                    + "','" + objProveedor.getDireccion_proveedor() + "','" + objProveedor.getContacto_proveedor() 
                    + "'," + objProveedor.getTelefono_proveedor() + ", " + objProveedor.getDias_credito_proveedor() + ") as result";
            System.out.println("Ejecutando ACCESOPROVEEDOR " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en proveedores " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoProveedor> seleccionarProveedor(String vDescripcion_proveedor) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from proveedor where descripcion_proveedor like '%" + vDescripcion_proveedor + "%'";

        try {
            System.out.println("Ejecutando en ACCESO PROVEEDOR SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoProveedor registros;

            while (tabla.next()) {
                registros = new ObjetoProveedor();
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setNit_proveedor(tabla.getString("nit_proveedor"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                registros.setDireccion_proveedor(tabla.getString("direccion_proveedor"));
                registros.setContacto_proveedor(tabla.getString("contacto_proveedor"));
                registros.setTelefono_proveedor(tabla.getInt("telefono_proveedor"));
                registros.setDias_credito_proveedor(tabla.getInt("dias_credito_proveedor"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO PROVEEDOR: SELECCION PROVEEDOR");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoProveedor> retornaProveedores() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from proveedor";

        try {
            System.out.println("Ejecutando en ACCESO PROVEEDOR SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoProveedor registros;

            while (tabla.next()) {
                registros = new ObjetoProveedor();
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setNit_proveedor(tabla.getString("nit_proveedor"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                registros.setDireccion_proveedor(tabla.getString("direccion_proveedor"));
                registros.setContacto_proveedor(tabla.getString("contacto_proveedor"));
                registros.setTelefono_proveedor(tabla.getInt("telefono_proveedor"));
                registros.setDias_credito_proveedor(tabla.getInt("dias_credito_proveedor"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO PROVEEDOR: SELECCION PROVEEDOR");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoProveedor> buscarProveedor(String pNitProveedor) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from proveedor where nit_proveedor = '" + pNitProveedor + "'";

        try {
            System.out.println("EJECUTANDO EN ACCESO PROVEEDOR " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoProveedor Registros;

                while (tabla.next()) {
                    Registros = new ObjetoProveedor();
                    Registros.setId_proveedor(tabla.getInt("id_proveedor"));
                    Registros.setNit_proveedor(tabla.getString("nit_proveedor"));
                    Registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                    Registros.setDireccion_proveedor(tabla.getString("direccion_proveedor"));
                    Registros.setContacto_proveedor(tabla.getString("contacto_proveedor"));
                    Registros.setTelefono_proveedor(tabla.getInt("telefono_proveedor"));
                    Registros.setDias_credito_proveedor(tabla.getInt("dias_credito_proveedor"));
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            System.out.println("EC ACCESO_PROVEEDOR BUSCAR_PROVEEDOR " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}
