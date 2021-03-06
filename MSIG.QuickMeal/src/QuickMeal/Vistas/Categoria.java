/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.AccesoItem;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoItem;
import java.util.ArrayList;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class Categoria extends javax.swing.JInternalFrame {
    
    private Mensaje mensaje = new Mensaje();
    
    /**
     * Creates new form wdwAyudaAcercaDeInventario
     */
    public Categoria(){
        initComponents();
    }
    
    public void descripcionCategoria(String descripcion){
        btnDescripcionCategoria.setText(descripcion);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnDescripcionCategoria = new javax.swing.JButton();

        setTitle("Categoria");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/categoria.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(200, 100));
        setMinimumSize(new java.awt.Dimension(200, 100));
        setPreferredSize(new java.awt.Dimension(200, 100));

        btnDescripcionCategoria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnDescripcionCategoria.setText("DESCRIPCION");
        btnDescripcionCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescripcionCategoriaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDescripcionCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnDescripcionCategoria, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnDescripcionCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescripcionCategoriaActionPerformed
        // TODO add your handling code here:
        
        AccesoItem item = new AccesoItem();
        ArrayList<ObjetoItem> lista = new ArrayList();
        
        int y = 0;
        
        try{
           lista = item.listarItemPorCategoria(Integer.parseInt(this.title)); 
        } catch(Exception error) {
           mensaje.manipulacionExcepciones("critico", "Ocurrio un error inesperado");
        }
        
        if (lista.isEmpty()) {
            mensaje.manipulacionExcepciones("advertencia", "No se encotraron items para esta categoria");
            MovimientoVentaDeProductos.restablecerTablaItem();
        } else {
            MovimientoVentaDeProductos.restablecerTablaItem();
            for (ObjetoItem n : lista) {
                MovimientoVentaDeProductos.tblItems.setValueAt(n.getId_item(), y, 0);
                MovimientoVentaDeProductos.tblItems.setValueAt(n.getDescripcion_item(), y, 1);
                MovimientoVentaDeProductos.tblItems.setValueAt(n.getPrecio_item(), y, 2);
                y++;
            }
        }
    }//GEN-LAST:event_btnDescripcionCategoriaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDescripcionCategoria;
    // End of variables declaration//GEN-END:variables
}
