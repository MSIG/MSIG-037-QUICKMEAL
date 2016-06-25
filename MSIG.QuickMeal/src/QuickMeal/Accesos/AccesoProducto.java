/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoProducto;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoProducto {

    Mensaje mensaje = new Mensaje();

    public void insertarProducto(ObjetoProducto objProducto) {
        String sql, fecha;
        Conexion acceso = new Conexion();

        //validar si la fecha de caducidad viene nula
        if(objProducto.getFecha_caduciad_producto().equals("null")) {
            fecha = "null";
        } else {
            fecha = "'"+objProducto.getFecha_caduciad_producto()+"'";
        }

        try {
            sql = "select insertar_producto(" + objProducto.getId_proveedor() + ",'" + objProducto.getDescripcion_producto() + "'," 
                    + objProducto.getCosto_producto() + "," + objProducto.getCantidad_producto() 
                    + ",'" + objProducto.getUnidad_medida_producto() + "'," 
                    + objProducto.getCaducidad_producto() + "," + objProducto.getMinimo_producto() 
                    + ","+objProducto.getFecha_caduciad_producto()+") as result";
            System.out.println("Ejecutando " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en producto " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarProducto(ObjetoProducto objProducto) {
        String sql, fecha;
        Conexion acceso = new Conexion();
        
        //validar si la fecha de caducidad viene nula
        if(objProducto.getFecha_caduciad_producto().equals("null")) {
            fecha = "null";
        } else {
            fecha = "'"+objProducto.getFecha_caduciad_producto()+"'";
        }

        try {
            sql = "select actualizar_producto(" + objProducto.getId_producto() 
                    + "," + objProducto.getId_proveedor() + ", '" 
                    + objProducto.getDescripcion_producto() + "'," 
                    + objProducto.getCosto_producto() + "," 
                    + objProducto.getCantidad_producto() + ",'" 
                    + objProducto.getUnidad_medida_producto() + "'," 
                    + objProducto.getCaducidad_producto() + "," 
                    + objProducto.getMinimo_producto() + ","
                    +objProducto.getFecha_caduciad_producto()+") as result";
            System.out.println("Ejecutando ACCESOPRODUCTO " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en producto " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoProducto> seleccionarProducto(String vDescripcion_producto) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select a.id_producto, a.id_proveedor, a.descripcion_producto, a.costo_producto, a.cantidad_producto, a.unidad_medida_producto, a.caducidad_producto, a.minimo_producto, b.descripcion_proveedor, a.caducidad_producto from producto a, proveedor b where a.id_proveedor = b.id_proveedor and descripcion_producto like '%" + vDescripcion_producto + "%'";

        try {
            System.out.println("Ejecutando en ACCESO PRODUCTO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoProducto registros;

            while (tabla.next()) {
                registros = new ObjetoProducto();
                registros.setId_producto(tabla.getInt("id_producto"));
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setDescripcion_producto(tabla.getString("descripcion_producto"));
                registros.setCosto_producto(tabla.getDouble("costo_producto"));
                registros.setCantidad_producto(tabla.getDouble("cantidad_producto"));
                registros.setUnidad_medida_producto(tabla.getString("unidad_medida_producto"));
                registros.setCaducidad_producto(tabla.getInt("caducidad_producto"));
                registros.setMinimo_producto(tabla.getInt("minimo_producto"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                registros.setFecha_caduciad_producto(tabla.getString("caducidad_producto"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("Error en seleccionar producto " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoProducto> retornaProductos() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from producto as a, "
                + "proveedor as b where a.id_proveedor = b.id_proveedor order by descripcion_producto";

        try {
            System.out.println("Ejecutando en ACCESO PRODUCTO SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoProducto registros;

            while (tabla.next()) {
                registros = new ObjetoProducto();
                registros.setId_producto(tabla.getInt("id_producto"));
                registros.setId_proveedor(tabla.getInt("id_proveedor"));
                registros.setDescripcion_producto(tabla.getString("descripcion_producto"));
                registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                registros.setCosto_producto(tabla.getDouble("costo_producto"));
                registros.setCantidad_producto(tabla.getDouble("cantidad_producto"));
                registros.setUnidad_medida_producto(tabla.getString("unidad_medida_producto"));
                registros.setCaducidad_producto(tabla.getInt("caducidad_producto"));
                registros.setMinimo_producto(tabla.getInt("minimo_producto"));
                registros.setFecha_caduciad_producto(tabla.getString("caducidad_producto"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("ACCESO PRODUCTO : SELECCION PRODUCTO");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoProducto> retornaProveedores() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select id_proveedor, descripcion_proveedor from proveedor;";

        try {
            System.out.println("EJECUTANDO EN ACCESO PROVEEDOR CARGAR PROVEEDORES " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoProducto Registros;

                while (tabla.next()) {
                    Registros = new ObjetoProducto();
                    Registros.setId_proveedor(tabla.getInt("id_proveedor"));
                    Registros.setDescripcion_proveedor(tabla.getString("descripcion_proveedor"));
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            System.out.println("EC ACCESO_PROVEEDOR CARGAR_PROVEEDORES " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoProducto> buscarProducto(String pDescripcion_producto, String pId_proveedor) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from producto where descripcion_producto = '" + pDescripcion_producto + "' and id_proveedor = " + pId_proveedor + ";";

        try {
            System.out.println("EJECUTANDO EN ACCESO PRODUCTO " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoProducto Registros;

                while (tabla.next()) {
                    Registros = new ObjetoProducto();
                    Registros.setId_producto(tabla.getInt("id_producto"));
                    Registros.setDescripcion_producto(tabla.getString("descripcion_producto"));
                    Registros.setCosto_producto(tabla.getDouble("costo_producto"));
                    Registros.setCantidad_producto(tabla.getDouble("cantidad_producto"));
                    Registros.setUnidad_medida_producto(tabla.getString("unidad_medida_producto"));
                    Registros.setCaducidad_producto(tabla.getInt("caducidad_producto"));
                    Registros.setMinimo_producto(tabla.getInt("minimo_producto"));

                    System.out.println("REGISTROS ");
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            System.out.println("EC ACCESO_PRODUCTO BUSCAR_PRODUCTO " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoProducto> buscar(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from producto where descripcion_producto = '" + x + "'";

        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoProducto Registros;

                while (tabla.next()) {
                    Registros = new ObjetoProducto();
                    Registros.setId_producto(tabla.getInt("id_producto"));
                    Registros.setDescripcion_producto(tabla.getString("descripcion_producto"));
                    Registros.setCosto_producto(tabla.getDouble("costo_producto"));
                    Registros.setCantidad_producto(tabla.getDouble("cantidad_producto"));
                    Registros.setUnidad_medida_producto(tabla.getString("unidad_medida_producto"));
                    Registros.setCaducidad_producto(tabla.getInt("caducidad_producto"));
                    Registros.setMinimo_producto(tabla.getInt("minimo_producto"));
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar el producto " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
    public String buscarDescripcionProducto(String id) {
        Conexion acceso = new Conexion();
        String sql = "select * from producto where id_producto = " + id;
        String descripcion = "";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {

                while (tabla.next()) {
                    descripcion = tabla.getString("descripcion_producto");
                }
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar el producto " + error);
            acceso.cerrarConexion();
            return null;
        } finally {
            acceso.cerrarConexion();
        }
        return descripcion;
    }
    
    public String buscarProductosEnMinimo() {
        Conexion acceso = new Conexion();
        String sql = "select distinct('SI') as encontro from producto where cantidad_producto <= minimo_producto";
        String resultado = "NO";
        
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            if (tabla != null) {
                while (tabla.next()) {
                    resultado = tabla.getString("encontro");
                }
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar el producto " + error);
            acceso.cerrarConexion();
            return null;
        } finally {
            acceso.cerrarConexion();
        }
        
        return resultado;
    }

    public ArrayList<ObjetoProducto> buscarDescipcionProducto(String x) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from producto where id_producto = '" + x + "'";

        try {
            System.out.println("Ejecutando " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoProducto Registros;

                while (tabla.next()) {
                    Registros = new ObjetoProducto();
                    Registros.setId_producto(tabla.getInt("id_producto"));
                    Registros.setDescripcion_producto(tabla.getString("descripcion_producto"));
                    Registros.setCosto_producto(tabla.getDouble("costo_producto"));
                    Registros.setCantidad_producto(tabla.getDouble("cantidad_producto"));
                    Registros.setUnidad_medida_producto(tabla.getString("unidad_medida_producto"));
                    Registros.setCaducidad_producto(tabla.getInt("caducidad_producto"));
                    Registros.setMinimo_producto(tabla.getInt("minimo_producto"));
                    lista.add(Registros);
                }
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al buscar el producto " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
    
}
