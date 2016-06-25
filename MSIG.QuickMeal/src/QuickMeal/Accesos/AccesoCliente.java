/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoCliente;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoCliente {

    private Mensaje mensaje = new Mensaje();

    public ObjetoCliente consultaCliente(String codigo_cliente) {
        ObjetoCliente cliente = new ObjetoCliente();
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from cliente where estado_cliente = 'A' and codigo_cliente = " + codigo_cliente;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoCliente registro;
            while (result.next()) {
                registro = new ObjetoCliente();
                registro.setId_cliente(result.getInt("id_cliente"));
                registro.setCodigo_cliente(result.getInt("codigo_cliente"));
                registro.setNit_cliente(result.getString("nit_cliente"));
                registro.setCc_cliente(result.getString("cc_cliente"));
                registro.setCuenta_cliente(result.getString("cuenta_cliente"));
                registro.setNombre_cliente(result.getString("nombre_cliente"));
                registro.setDireccion_cliente(result.getString("direccion_cliente"));
                registro.setTelefono_cliente(result.getInt("telefono_cliente"));
                registro.setEmpresa_cliente(result.getString("empresa_cliente"));
                registro.setEstado_cliente(result.getString("estado_cliente"));
                lista.add(registro);
                cliente = registro;
            }

            //VERIFICAR QUE NO DEVULVA MAS DE UN REGISTRO
            if (lista.size() > 1) {
                mensaje.manipulacionExcepciones("critico", "Este codigo de empleado esta duplicado, por favor verifique");
            } else {
                if (lista.size() == 0) {
                    mensaje.manipulacionExcepciones("critico", "Este empledo no esta activo o no ha sido ingresado, verifique!");
                }
            }

        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error consultar el codigo del cliente " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return cliente;
    }

    public ObjetoCliente buscar(String id_orden) {
        ObjetoCliente cliente = new ObjetoCliente();
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select c.* from orden as a, venta as b, cliente as c where a.id_orden = " + id_orden + " and a.id_venta = b.id_venta and b.id_cliente = c.id_cliente";
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoCliente registro;
            while (result.next()) {
                registro = new ObjetoCliente();
                registro.setId_cliente(result.getInt("id_cliente"));
                registro.setCodigo_cliente(result.getInt("codigo_cliente"));
                registro.setNit_cliente(result.getString("nit_cliente"));
                registro.setCc_cliente(result.getString("cc_cliente"));
                registro.setCuenta_cliente(result.getString("cuenta_cliente"));
                registro.setNombre_cliente(result.getString("nombre_cliente"));
                registro.setDireccion_cliente(result.getString("direccion_cliente"));
                registro.setTelefono_cliente(result.getInt("telefono_cliente"));
                registro.setEmpresa_cliente(result.getString("empresa_cliente"));
                registro.setEstado_cliente(result.getString("estado_cliente"));
                lista.add(registro);
                cliente = registro;
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error consultar al buscar cliente " + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return cliente;
    }

    public void insertarCliente(ObjetoCliente objCliente) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select insertar_cliente(" + objCliente.getCodigo_cliente() + ",'" + objCliente.getNit_cliente() + "','" + objCliente.getCc_cliente() + "','" + objCliente.getCuenta_cliente() + "','" + objCliente.getNombre_cliente() + "','" + objCliente.getDireccion_cliente() + "'," + objCliente.getTelefono_cliente() + ",'" + objCliente.getEmpresa_cliente() + "','" + objCliente.getEstado_cliente() + "') as result";
            System.out.println("Ejecutando ACCESOCLIENTE " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critica", "Error al insertar en clientes " + error);
        }
        acceso.cerrarConexion();
    }

    public void actualizarCliente(ObjetoCliente objCliente) {
        String sql;
        Conexion acceso = new Conexion();

        try {
            sql = "select actualizar_cliente(" + objCliente.getId_cliente() + "," + objCliente.getCodigo_cliente() + ",'" + objCliente.getNit_cliente() + "','" + objCliente.getCc_cliente() + "','" + objCliente.getCuenta_cliente() + "','" + objCliente.getNombre_cliente() + "','" + objCliente.getDireccion_cliente() + "'," + objCliente.getTelefono_cliente() + ",'" + objCliente.getEmpresa_cliente() + "','" + objCliente.getEstado_cliente() + "') as result";
            System.out.println("Ejecutando ACCESOCLIENTE " + sql);
            mensaje.manipulacionExcepciones("informacion", acceso.ejecutarFuncion(sql));
        } catch (Exception error) {
            acceso.cerrarConexion();
            mensaje.manipulacionExcepciones("critica", "Error al insertar en clientes " + error);
        }
        acceso.cerrarConexion();
    }

    public ArrayList<ObjetoCliente> seleccionarCliente(String nombre) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from cliente where nombre_cliente like '%" + nombre + "%'";

        try {
            System.out.println("Ejecutando en ACCESO CLIENTE SQL " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoCliente registros;

            while (tabla.next()) {
                registros = new ObjetoCliente();
                registros.setId_cliente(tabla.getInt("id_cliente"));
                registros.setCodigo_cliente(tabla.getInt("codigo_cliente"));
                registros.setNit_cliente(tabla.getString("nit_cliente"));
                registros.setCc_cliente(tabla.getString("cc_cliente"));
                registros.setCuenta_cliente(tabla.getString("cuenta_cliente"));
                registros.setNombre_cliente(tabla.getString("nombre_cliente"));
                registros.setDireccion_cliente(tabla.getString("direccion_cliente"));
                registros.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                registros.setEmpresa_cliente(tabla.getString("empresa_cliente"));
                registros.setEstado_cliente(tabla.getString("estado_cliente"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("Error al seleccionar el cliente");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCliente> retornaCliente() {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from cliente";

        try {
            System.out.println("Ejecutando: " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);
            ObjetoCliente registros;

            while (tabla.next()) {
                registros = new ObjetoCliente();
                registros.setId_cliente(tabla.getInt("id_cliente"));
                registros.setCodigo_cliente(tabla.getInt("codigo_cliente"));
                registros.setNit_cliente(tabla.getString("nit_cliente"));
                registros.setCc_cliente(tabla.getString("cc_cliente"));
                registros.setCuenta_cliente(tabla.getString("cuenta_cliente"));
                registros.setNombre_cliente(tabla.getString("nombre_cliente"));
                registros.setDireccion_cliente(tabla.getString("direccion_cliente"));
                registros.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                registros.setEmpresa_cliente(tabla.getString("empresa_cliente"));
                registros.setEstado_cliente(tabla.getString("estado_cliente"));
                lista.add(registros);
            }
        } catch (Exception error) {
            System.out.println("Error en la funcion Retornar Cliente en Acceso Cliente");
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }

    public ArrayList<ObjetoCliente> buscarCliente(String codigo) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from cliente where codigo_cliente = '" + codigo + "'";

        try {
            System.out.println("Ejecutando: " + sql);
            ResultSet tabla = acceso.listarRegistros(sql);

            if (tabla != null) {
                ObjetoCliente registros;

                while (tabla.next()) {
                    registros = new ObjetoCliente();
                    registros.setId_cliente(tabla.getInt("id_cliente"));
                    registros.setCodigo_cliente(tabla.getInt("codigo_cliente"));
                    registros.setNit_cliente(tabla.getString("nit_cliente"));
                    registros.setCc_cliente(tabla.getString("cc_cliente"));
                    registros.setCuenta_cliente(tabla.getString("cuenta_cliente"));
                    registros.setNombre_cliente(tabla.getString("nombre_cliente"));
                    registros.setDireccion_cliente(tabla.getString("direccion_cliente"));
                    registros.setTelefono_cliente(tabla.getInt("telefono_cliente"));
                    registros.setEmpresa_cliente(tabla.getString("empresa_cliente"));
                    registros.setEstado_cliente(tabla.getString("estado_cliente"));
                    lista.add(registros);
                }
            }
        } catch (Exception error) {
            System.out.println("Error en funcion Buscar Cliente en clase Acceso Cliente" + error);
            acceso.cerrarConexion();
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}
