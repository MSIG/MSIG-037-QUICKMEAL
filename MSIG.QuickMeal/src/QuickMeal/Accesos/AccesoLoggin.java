/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Accesos;

import QuickMeal.Objetos.ObjetoLoggin;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class AccesoLoggin {

    private Mensaje mensaje = new Mensaje();

    public ArrayList<ObjetoLoggin> ingresoUsuario(String nombre, String contrasenia, int estacion) {
        ArrayList lista = new ArrayList();
        Conexion acceso = new Conexion();
        String sql = "select * from usuario, estacion where nombre_usuario = '"
                + nombre + "' and contrasenia_usuario = '" + contrasenia + "' and id_estacion = " + estacion;
        try {
            System.out.println("Ejecutando " + sql);
            ResultSet result = acceso.listarRegistros(sql);
            ObjetoLoggin registro;
            while (result.next()) {
                registro = new ObjetoLoggin();
                registro.setId_usuario(result.getInt("id_usuario"));
                registro.setNombre_usuario(result.getString("nombre_usuario"));
                registro.setId_estacion(result.getInt("id_estacion"));
                registro.setDescripcion_estacion(result.getString("descripcion_estacion"));
                registro.setTipo_usuario(result.getString("tipo_usuario"));
                lista.add(registro);
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Error al visualizar loggin " + error);
            return null;
        }
        acceso.cerrarConexion();
        return lista;
    }
}