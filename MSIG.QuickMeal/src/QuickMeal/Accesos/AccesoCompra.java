/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoCompra;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.text.TabableView;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala Guatemala
 */
public class AccesoCompra {

    Mensaje mensaje = new Mensaje();

    public void insertarCompra(ObjetoCompra objCompra) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select insertar_compra(" + objCompra.getId_proveedor() + "," 
                    + objCompra.getFactura_compra() + ",'" + objCompra.getFecha_compra() 
                    + "','" + objCompra.getEstado_compra() + "',"+objCompra.getDias_credito_compra()+") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en compras " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarCompra(ObjetoCompra objCompra) {
        String sql;
        Conexion acceso = new Conexion();
        try {
            sql = "select actualizar_compra(" + objCompra.getId_compra() 
                    + ", " + objCompra.getId_proveedor() + ",'" 
                    + objCompra.getFactura_compra() + "','" 
                    + objCompra.getFecha_compra() + "','" 
                    + objCompra.getEstado_compra() + "',"+objCompra.getDias_credito_compra()+") as result";
            System.out.println("Ejecutando ACCESOCOMPRA " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en compras " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoCompra> seleccionarCompra(String pDescripcion_proveedor, String pFactura_compra) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_compra, a.id_proveedor, a.factura_compra, a.fecha_compra, a.estado_compra, b.descripcion_proveedor from compra a, proveedor b where a.id_proveedor = b.id_proveedor and b.descripcion_proveedor like '%" + pDescripcion_proveedor + "%' or a.factura_compra like '%" + pFactura_compra + "%'";
        try {
            System.out.println("Ejecutando en ACCESO COMPRA SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoCompra registros;

            while (tabla.next()) {
                registros = new ObjetoCompra();
                registros.setId_compra(tabla.getInt("id_compra"));
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                registros.setFecha_compra(tabla.getString("fecha_compra"));
                registros.setEstado_compra(tabla.getString("estado_compra"));
                registros.setFactura_compra(tabla.getString("factura_compra"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO COMPRA: SELECCION COMPRA");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public ArrayList<ObjetoCompra> buscarFacturaCompra(String proveedor, String factura) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from compra where factura_compra = '" + factura + "' and id_proveedor = " + proveedor;
        try {
            System.out.println("Ejecutando en ACCESO COMPRA SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoCompra registros;
            while (tabla.next()) {
                registros = new ObjetoCompra();
                registros.setId_compra(tabla.getInt("id_compra"));
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setFactura_compra(tabla.getString("factura_compra"));
                registros.setFecha_compra(tabla.getString("fecha_compra"));
                registros.setDias_credito_compra(tabla.getInt("dias_credito_compra"));
                registros.setEstado_compra(tabla.getString("estado_compra"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO COMPRA: SELECCION COMPRA");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCompra> retornaCompras() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_compra, a.id_proveedor, a.factura_compra, a.fecha_compra, a.estado_compra, "
                + "b.descripcion_proveedor from compra a, proveedor b where a.id_proveedor = b.id_proveedor";
        try {
            System.out.println("Ejecutando en ACCESO COMPRA SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoCompra registros;
            while (tabla.next()) {
                registros = new ObjetoCompra();
                registros.setId_compra(tabla.getInt("id_compra"));
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                registros.setFecha_compra(tabla.getString("fecha_compra"));
                registros.setEstado_compra(tabla.getString("estado_compra"));
                registros.setFactura_compra(tabla.getString("factura_compra"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO COMPRA: SELECCION COMPRA");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCompra> buscarCompra(String pNum_factura) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select id_compra from compra where factura_compra = '" + pNum_factura + "'";
        try {
            System.out.println("EJECUTANDO EN ACCESO COMPRA " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoCompra Registros;

                while (tabla.next()) {
                    Registros = new ObjetoCompra();
                    Registros.setId_compra(tabla.getInt("id_compra"));
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            System.out.println("EC ACCESO_COMPRA BUSCAR_COMPRA " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCompra> retornaProveedores() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select id_proveedor, dias_credito_proveedor, descripcion_proveedor from proveedor;";

        try {
            System.out.println("EJECUTANDO EN ACCESO COMPRA CARGAR PROVEEDORES " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoCompra Registros;

                while (tabla.next()) {
                    Registros = new ObjetoCompra();
                    Registros.setId_proveedor(tabla.getInt("id_proveedor"));
                    Registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                    Registros.setDias_credito_compra(tabla.getInt("dias_credito_proveedor"));
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            System.out.println("EC ACCESO_COMPRA CARGAR_PROVEEDORES " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCompra> seleccionarIdCompra(ObjetoCompra objCompra) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();    
        String sql = "select max(id_compra) id_compra from compra where id_proveedor = " 
                + objCompra.getId_proveedor() + " and factura_compra = '" 
                + objCompra.getFactura_compra() + "' "
                + "and date_format(fecha_compra,'%d-%m-%Y') = "
                + "date_format(str_to_date('" + objCompra.getFecha_compra() + "', "
                + "'%d-%m-%Y'), '%d-%m-%Y') and estado_compra = '" + objCompra.getEstado_compra() + "';";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoCompra registros;
            while (tabla.next()) {
                registros = new ObjetoCompra();
                registros.setId_compra(tabla.getInt("id_compra"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO COMPRA : ERROR SELECCIONAR ID COMPRA");
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}
