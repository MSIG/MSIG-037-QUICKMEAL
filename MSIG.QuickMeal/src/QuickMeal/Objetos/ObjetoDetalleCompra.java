/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Objetos;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */

public class ObjetoDetalleCompra {
    
    private int id_detalle_compra;
    private int id_compra;
    private String id_producto;
    private float cantidad_compra;
    private float precio_compra;
    
    public int getId_detalle_compra(){
        int vId_d_compra = 0;
        try{
            vId_d_compra = id_detalle_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:getId_detalle_compra "+error);
        }
        return vId_d_compra;
    }
    
    public void setId_detalle_compra(int pId_d_compra){
        try{
            id_detalle_compra =pId_d_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:setId_detalle_compra "+error);
        }
    }
    
    public int getId_compra(){
        int vId_compra = 0;
        try{
            vId_compra = id_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:GETID_COMPRA "+error);
        }
        return vId_compra;
    }
    
    public void setId_compra(int pId_compra){
        try{
            id_compra = pId_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:SETID_COMPRA "+error);
        }
    }
    
    public String getId_producto(){
        String vId_producto = null;
        try{
            vId_producto = id_producto;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:GETID_PRODUCTO "+error);
        }
        return vId_producto;
    }
    
    public void setId_producto(String pId_producto){
        try{
            id_producto = pId_producto;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:SETID_COMPRA "+error);
        }
    }
    
    public float getCantidad_compra(){
        float vCant_d_compra = 0;
        try{
            vCant_d_compra = cantidad_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:getCantidad_compra "+error);
        }
        return vCant_d_compra;
    }
    
    public void setCantidad_compra(float pCant_d_compra){
        try{
            cantidad_compra = pCant_d_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:setCantidad_compra "+error);
        }
    }
    
    public float getPrecio_compra(){
        float vPrecio_d_compra = 0;
        try{
            vPrecio_d_compra = precio_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:GETPRECIO_D_COMPRA "+error);
        }
        return vPrecio_d_compra;
    }
    
    public void setPrecio_compra(float pPrecio_d_compra){
        try{
            precio_compra = pPrecio_d_compra;
        }catch(Error error){
            System.out.println("EC DETALLE_COMPRA:SETPRECIO_D_COMPRA "+error);
        }
    }
}
