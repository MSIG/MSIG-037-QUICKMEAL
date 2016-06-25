package QuickMeal.Accesos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class Mensaje {
    
    public int valor = 0;
    
    public void manipulacionExcepciones(String pTipoVentana, String pMensaje){
        int     vTipoVentana = 0;
        int     vTipoMensaje = 0;
        String  vTituloVentana = null;
        
        //SELECCIONAR EL TIPO DE VENTANA ELEJIDA
        if(pTipoVentana.equals("critico")){
            vTipoVentana = 0;
            vTituloVentana = "Error Critico";
            vTipoMensaje = 1;
        }
        
        if(pTipoVentana.equals("advertencia")){
            vTipoVentana = 1;
            vTituloVentana = "Advertencia";
            vTipoMensaje = 1;
        }
        
        if(pTipoVentana.equals("informacion")){
            vTipoVentana = 2;
            vTituloVentana = "Informacion Importante";
            vTipoMensaje = 1;
        }
        
        if(pTipoVentana.equals("interrogante")){
            vTipoVentana = 3;
            vTituloVentana = "Confirmacion de Usuario";
            vTipoMensaje = 2;
        }
        
        //PARAMETROS: VENTANA O BOTON PADRE, MENSAJE, TITULO, TIPO VENTANA, ICONO
        if(vTipoMensaje == 1){
            javax.swing.JOptionPane.showMessageDialog(null, pMensaje, vTituloVentana, vTipoVentana, null);
        }else{
            if(vTipoMensaje == 2){
                valor = javax.swing.JOptionPane.showConfirmDialog(null, pMensaje, vTituloVentana, vTipoMensaje);
            }
        }
        
    }  
}
