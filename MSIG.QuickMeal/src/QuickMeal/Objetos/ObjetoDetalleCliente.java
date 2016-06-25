/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package QuickMeal.Objetos;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class ObjetoDetalleCliente {
    private int     id_detalle_compra;
    private int     id_compra;
    private String  id_producto;
    private float   cantidad_compra;
    private float   precio_compra;
    private String  descripcion_producto;
    private int     id_proveedor;
    private String  factura_compra;
    private String  fecha_compra;
    private String  estado_compra;
    private String  descripcion_proveedor;
    
    public int getId_detalle_compra(){
        int vId_d_compra = 0;
        try{
            vId_d_compra = id_detalle_compra;
        }catch(Error error){
            System.out.println("EC CyDC:getId_detalle_compra "+error);
        }
        return vId_d_compra;
    }
    
    public void setId_detalle_compra(int pId_d_compra){
        try{
            id_detalle_compra =pId_d_compra;
        }catch(Error error){
            System.out.println("EC CyDC:setId_detalle_compra "+error);
        }
    }
    
    public int getId_compra(){
        int vId_compra = 0;
        try{
            vId_compra = id_compra;
        }catch(Error error){
            System.out.println("EC CyDC:GETID_COMPRA "+error);
        }
        return vId_compra;
    }
    
    public void setId_compra(int pId_compra){
        try{
            id_compra = pId_compra;
        }catch(Error error){
            System.out.println("EC CyDC:SETID_COMPRA "+error);
        }
    }
    
    public String getId_producto(){
        String vId_producto = null;
        try{
            vId_producto = id_producto;
        }catch(Error error){
            System.out.println("EC CyDC:GETID_PRODUCTO "+error);
        }
        return vId_producto;
    }
    
    public void setId_producto(String pId_producto){
        try{
            id_producto = pId_producto;
        }catch(Error error){
            System.out.println("EC CyDC:SETID_COMPRA "+error);
        }
    }
    
    public float getCantidad_compra(){
        float vCant_d_compra = 0;
        try{
            vCant_d_compra = cantidad_compra;
        }catch(Error error){
            System.out.println("EC CyDC:getCantidad_compra "+error);
        }
        return vCant_d_compra;
    }
    
    public void setCantidad_compra(float pCant_d_compra){
        try{
            cantidad_compra = pCant_d_compra;
        }catch(Error error){
            System.out.println("EC CyDC:setCantidad_compra "+error);
        }
    }
    
    public float getPrecio_compra(){
        float vPrecio_d_compra = 0;
        try{
            vPrecio_d_compra = precio_compra;
        }catch(Error error){
            System.out.println("EC CyDC:GETPRECIO_D_COMPRA "+error);
        }
        return vPrecio_d_compra;
    }
    
    public void setPrecio_compra(float pPrecio_d_compra){
        try{
            precio_compra = pPrecio_d_compra;
        }catch(Error error){
            System.out.println("EC CyDC:SETPRECIO_D_COMPRA "+error);
        }
    }
    
    public String getDescripcion_producto(){
        String vdescripcion_producto = null;
        try{
            vdescripcion_producto = descripcion_producto;
        }catch(Error error){
            System.out.println("EC CyDC:getDescripcion_producto "+error);
        }
        return vdescripcion_producto;
    }
    
    public void setDescripcion_producto(String pdescripcion_producto){
        try{
            descripcion_producto = pdescripcion_producto;
        }catch(Error error){
            System.out.println("EC CyDC:setDescripcion_producto "+error);
        }
    }
    
    public int getId_proveedor(){
        int vId_proveedor = 0;
        try{
            vId_proveedor = id_proveedor;
        }catch(Error error){
            System.out.println("OBJETO CyDC: GETID_PROVEEDOR" + error);
        }
        return vId_proveedor;
    }
    
    public void setId_proveedor(int pId_proveedor){
        try{
            id_proveedor = pId_proveedor;
        }catch(Error error){
            System.out.println("OBJETO CyDC: SETID_PROVEEDOR " + error);
        }
    }
    
    public String getFactura_compra(){
        String vFactura_compra = "";
        try{
            vFactura_compra = factura_compra;
        }catch(Error error){
            System.out.println("OBJETO CyDC: GETFACTURA_COMPRA " + error);
        }
        return vFactura_compra;
    }
    
    
    public void setFactura_compra(String pFactura_compra){
        try{
            factura_compra = pFactura_compra;
        }catch(Error error){
            System.out.println("OBJETO PROVEEDOR: SETFACTURA_COMPRA " + error);
        }
    }
    
    public String getFecha_compra(){
        String vFecha_compra = "";
        try{
            vFecha_compra = fecha_compra;
        }catch(Error error){
            System.out.println("OBJETO CyDC: GETFECHA_COMPRA " + error);
        }
        return vFecha_compra;
    }
    
    public void setFecha_compra(String pFecha_compra){
        try{
            fecha_compra = pFecha_compra;
        }catch(Error error){
            System.out.println("OBJETO CyDC: SETFECHA_COMPRA " + error);
        }
    }
    
    public String getEstado_compra(){
        String vEstado_compra = "";
        try{
            vEstado_compra = estado_compra;
        }catch(Error error){
            System.out.println("OBJETO CyDC: GETESTADO_COMPRA " + error);
        }
        return vEstado_compra;
    }
    
    public void setEstado_compra(String pEstado_compra){
        try{
            estado_compra = pEstado_compra;
        }catch(Error error){
            System.out.println("OBJETO CyDC: SETESTADO_COMPRA" + error);
        }
    }
    
    public String getDescripcion_proveedor(){
        String vDescripcion_proveedor = "";
        try{
            vDescripcion_proveedor = descripcion_proveedor;
        }catch(Error error){
            System.out.println("OBJETO CyDC: GETDESCRIPCION_PROVEEDOR " + error);
        }
        return vDescripcion_proveedor;
    }
    
    public void setDescripcion_proveedor(String pDescripcion_proveedor){
        try{
            descripcion_proveedor = pDescripcion_proveedor;
        }catch(Error error){
            System.out.println("OBJETO PROVEEDOR: SETDESCRIPCIONPROVEEDOR " + error);
        }
    }
   
    
}
