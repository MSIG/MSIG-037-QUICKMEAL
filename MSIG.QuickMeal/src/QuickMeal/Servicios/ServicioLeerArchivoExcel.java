/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Servicios;

import QuickMeal.Accesos.Mensaje;
import QuickMeal.Librerias.CsvReader;
import QuickMeal.Objetos.ObjetoProducto;
import java.io.*;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.TableModel;

/**
 *
 * @author  Megabytes Soluciones Integrales Guatemala
 */
public class ServicioLeerArchivoExcel {

    public ArrayList leer_archivo_excel(String archivo){
        
        Mensaje Mensaje = new Mensaje();
        ArrayList lista_productos = new ArrayList();
        CsvReader LectorDeArchivo = null;

        try {
            LectorDeArchivo = new CsvReader(archivo,',');
        } catch (FileNotFoundException error) {
            Mensaje.manipulacionExcepciones("critico", "Error al leer el Archivo -> "+error);
        }

        try {
            LectorDeArchivo.readHeaders();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try { 
 
            ObjetoProducto producto;
            
            while (LectorDeArchivo.readRecord()){
                System.out.println("Tratando de ingresar los datos a la tabla");
                producto = new ObjetoProducto();
                producto.setId_producto(Integer.parseInt(LectorDeArchivo.get("Id Producto")));
                producto.setId_proveedor(Integer.parseInt(LectorDeArchivo.get("Id Proveedor")));
                producto.setDescripcion_producto(String.valueOf(LectorDeArchivo.get("Descripcion")));
                producto.setCosto_producto(Float.parseFloat(LectorDeArchivo.get("Costo")));
                producto.setCantidad_producto(Integer.parseInt(LectorDeArchivo.get("Cantidad")));
                producto.setUnidad_medida_producto(String.valueOf(LectorDeArchivo.get("Unidad Medida")));
                producto.setCaducidad_producto(Integer.parseInt(LectorDeArchivo.get("Caducidad")));
                producto.setMinimo_producto(Integer.parseInt(LectorDeArchivo.get("Minimo")));
                lista_productos.add(producto);
            }
            
        } catch (IOException error) {
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al ingresar los registros -> "+error);
        }

        LectorDeArchivo.close();

        return lista_productos;
    }
    
    public void escribir_archivo_excel(JTable pTabla, File pArchivo) throws IOException{
        
        TableModel modelo = pTabla.getModel();
        FileWriter archivo = new FileWriter(pArchivo);
        BufferedWriter contenido = new BufferedWriter(archivo);
        
        for(int cFila = 0; cFila<modelo.getColumnCount(); cFila++){
            contenido.write(modelo.getColumnName(cFila).toString()+"\t");
        }
        
        contenido.write("\n");
        
        for(int cFila = 0; cFila<modelo.getRowCount(); cFila++){
            for(int cColumna = 0; cColumna<modelo.getColumnCount(); cColumna++){
                contenido.write(modelo.getValueAt(cFila, cColumna).toString()+"\t");
            }
            contenido.write("\n");
        }
        
        contenido.close();
    }
}