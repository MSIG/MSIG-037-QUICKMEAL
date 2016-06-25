/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.AccesoCompra;
import QuickMeal.Accesos.Conexion;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoCompra;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */


public class BaseDeDatosHistorialCompraProductos extends javax.swing.JInternalFrame {

    AccesoCompra lProveedores = new AccesoCompra();
    Mensaje Mensaje = new Mensaje();
    AccesoCompra acceso_compra = new AccesoCompra();
    ArrayList<ObjetoCompra> lista = new ArrayList();
    Mensaje mensaje = new Mensaje();
    
    /**
     * Creates new form wdwBaseDeDAtosHistorialDeVentas
     */
    public BaseDeDatosHistorialCompraProductos() {
        initComponents();
        cargaProveedores();
    }
    
    private void mostrarOrdenes(String factura, String proveedor){
        
        DefaultTableModel tabla = new DefaultTableModel();
        lista = acceso_compra.buscarFacturaCompra(proveedor, factura);
        
        tabla.addColumn("ID Compra");
        tabla.addColumn("ID Proveedor");
        tabla.addColumn("Factura");
        tabla.addColumn("Fecha");
        tabla.addColumn("Estado");
        tabla.addColumn("Dias Credito");

        tabla.setRowCount(lista.size());
        int i = 0;
        
        for(ObjetoCompra n : lista){
            tabla.setValueAt(n.getId_compra(), i, 0);
            tabla.setValueAt(n.getId_proveedor(), i, 1);
            tabla.setValueAt(n.getFactura_compra(), i, 2);
            tabla.setValueAt(n.getFecha_compra(), i, 3);
            tabla.setValueAt(n.getEstado_compra(), i, 4);
            tabla.setValueAt(n.getDias_credito_compra(), i, 5);
            i++;
        }
        
        tblOrdenes.setModel(tabla);
    }
    
    private void cargaProveedores(){
        ArrayList<ObjetoCompra> listaProveedores = new ArrayList();
            
        try{
            listaProveedores = lProveedores.retornaProveedores();
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda de proveedores " + error);
        }
        
        // Lleno el combobox con los registros que existen para proveedores
        for(ObjetoCompra xProducto : listaProveedores){
            cbxDescripcionProveedor.addItem(xProducto.getDescripcion_proveedor());
        }
       
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrdenes = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnMostrar = new javax.swing.JButton();
        txtFactura = new javax.swing.JTextField();
        lblFactura = new javax.swing.JLabel();
        btnMostrar1 = new javax.swing.JButton();
        btAnular = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        cbxDescripcionProveedor = new javax.swing.JComboBox();
        txtIdProveedor = new javax.swing.JTextField();
        txtIdCompra = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Consultar Compra");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/historialVentas.png"))); // NOI18N

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrdenes);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        lblFactura.setText("Factura");

        btnMostrar1.setText("Pagada");
        btnMostrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrar1ActionPerformed(evt);
            }
        });

        btAnular.setText("Anulada");
        btAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAnularActionPerformed(evt);
            }
        });

        jLabel1.setText("Proveedor");

        cbxDescripcionProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbxDescripcionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDescripcionProveedorActionPerformed(evt);
            }
        });

        txtIdProveedor.setEditable(false);

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFactura)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbxDescripcionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btAnular)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMostrar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnMostrar)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostrar)
                    .addComponent(lblFactura)
                    .addComponent(txtFactura, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxDescripcionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btAnular)
                    .addComponent(btnMostrar1))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/impresora.png"))); // NOI18N
        btnImprimir.setActionCommand("btnReporte");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnImprimir)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnImprimir))
                .addGap(16, 16, 16))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        mostrarOrdenes(txtFactura.getText(), txtIdProveedor.getText());
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void tblOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdenesMouseClicked
        txtIdCompra.setText(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblOrdenesMouseClicked

    private void btnMostrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrar1ActionPerformed
        ObjetoCompra compra = new ObjetoCompra();
        compra.setId_compra(Integer.parseInt(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 0).toString()));
        compra.setId_proveedor(Integer.parseInt(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 1).toString()));
        compra.setFactura_compra(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 2).toString());
        compra.setFecha_compra(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 3).toString());
        compra.setEstado_compra("Pagada");
        compra.setDias_credito_compra(Integer.parseInt(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 5).toString()));
        acceso_compra.actualizarCompra(compra);
        mostrarOrdenes(compra.getFactura_compra(),String.valueOf(compra.getId_proveedor()));
        
    }//GEN-LAST:event_btnMostrar1ActionPerformed

    private void btAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAnularActionPerformed
        ObjetoCompra compra = new ObjetoCompra();
        compra.setId_compra(Integer.parseInt(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 0).toString()));
        compra.setId_proveedor(Integer.parseInt(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 1).toString()));
        compra.setFactura_compra(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 2).toString());
        compra.setFecha_compra(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 3).toString());
        compra.setEstado_compra("Anulada");
        compra.setDias_credito_compra(Integer.parseInt(tblOrdenes.getValueAt(tblOrdenes.getSelectedRow(), 5).toString()));
        acceso_compra.actualizarCompra(compra);
        mostrarOrdenes(compra.getFactura_compra(),String.valueOf(compra.getId_proveedor()));
    }//GEN-LAST:event_btAnularActionPerformed

    private void cbxDescripcionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDescripcionProveedorActionPerformed
        String seleccion = "";
        seleccion = cbxDescripcionProveedor.getSelectedItem().toString();

        ArrayList<ObjetoCompra> listaProveedores = new ArrayList();

        try {
            listaProveedores = lProveedores.retornaProveedores();

        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Error al obtener codigo de proveedor seleccionado " + error);
        }

        // Recorro la lista de proveedores para obtener el id del proveedor seleccionado
        for (ObjetoCompra xProducto : listaProveedores) {
            if (xProducto.getDescripcion_proveedor().equals(seleccion)) {
                txtIdProveedor.setText(String.valueOf(xProducto.getId_proveedor()));
            }
        }
       
    }//GEN-LAST:event_cbxDescripcionProveedorActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try {
            Conexion acceso = new Conexion();
            URL url_reporte = this.getClass().getResource("/QuickMeal/Reportes/DetalleCompra.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            parametro.put("P_ID_COMPRA", Integer.parseInt(txtIdCompra.getText()));
            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, acceso.conectar());
            JasperViewer visualizador = new JasperViewer(pantalla, false);
            visualizador.show();
        } catch (Exception error) {
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAnular;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnMostrar1;
    private javax.swing.JComboBox cbxDescripcionProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFactura;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JTable tblOrdenes;
    private javax.swing.JTextField txtFactura;
    private javax.swing.JTextField txtIdCompra;
    private javax.swing.JTextField txtIdProveedor;
    // End of variables declaration//GEN-END:variables
}
