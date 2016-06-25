/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.AccesoProducto;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoProducto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class BuscarCodigoProducto extends javax.swing.JInternalFrame {

    /**
     * Creates new form CatalogoAdmonProductos
     */
    Mensaje Mensaje = new Mensaje();
    AccesoProducto nuevoProveedor = new AccesoProducto();
    AccesoProducto nuevoProducto = new AccesoProducto();
    ObjetoProducto producto = new ObjetoProducto();
    String seleccion = "";

    public BuscarCodigoProducto() {
        initComponents();
    }

    private void buscarEnRegistrosGuardados() {
        String busqueda = txtBuscar.getText();

        if (busqueda != null) {
            DefaultTableModel tabla = new DefaultTableModel();
            ArrayList<ObjetoProducto> listaProductos = new ArrayList();
            int contadorFilas = 1;
            int cProveedor = 0;

            try {
                listaProductos = nuevoProducto.seleccionarProducto(busqueda);
                System.out.println("Se consultaron los productos");

            } catch (Exception error) {
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }

            if (listaProductos.isEmpty()) {
                Mensaje.manipulacionExcepciones("critico", "No existen datos de productos almacenados");
            } else {
                tabla.addColumn("Codigo");
                tabla.addColumn("Descripcion");
                tabla.addColumn("Proveedor");
                tabla.addColumn("Costo");
                tabla.addColumn("Unidad");
                tabla.addColumn("Cantidad");
                tabla.addColumn("Caducidad");
                tabla.addColumn("Minimo");

                for (ObjetoProducto xProducto : listaProductos) {
                    tabla.setRowCount(contadorFilas);
                    tabla.setValueAt(xProducto.getId_producto(), cProveedor, 0);
                    tabla.setValueAt(xProducto.getDescripcion_producto(), cProveedor, 1);
                    tabla.setValueAt(xProducto.getDescripcion_proveedor(), cProveedor, 2);
                    tabla.setValueAt(xProducto.getCosto_producto(), cProveedor, 3);
                    tabla.setValueAt(xProducto.getUnidad_medida_producto(), cProveedor, 4);
                    tabla.setValueAt(xProducto.getCantidad_producto(), cProveedor, 5);
                    tabla.setValueAt(xProducto.getCaducidad_producto(), cProveedor, 6);
                    tabla.setValueAt(xProducto.getMinimo_producto(), cProveedor, 7);

                    System.out.println("Se encontro el registro " + xProducto.getDescripcion_producto());
                    cProveedor++;
                    contadorFilas++;
                }

                tblProducto.setModel(tabla);
            }
        } else {
            mostrarRegistrosGuardados();
        }
    }

    public void mostrarRegistrosGuardados() {
        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetoProducto> listaProductos = new ArrayList();
        int contadorFilas = 1;
        int cProveedor = 0;

        try {
            listaProductos = nuevoProducto.retornaProductos();
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
        }

        if (listaProductos.isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "No existen datos de productos almacenados");
        } else {
            tabla.addColumn("Codigo");
            tabla.addColumn("Descripcion");
            tabla.addColumn("Proveedor");
            tabla.addColumn("Costo");
            tabla.addColumn("Cantidad");
            tabla.addColumn("Unidad Medida");
            tabla.addColumn("Caducidad");
            tabla.addColumn("Minimo");

            for (ObjetoProducto xProducto : listaProductos) {
                tabla.setRowCount(contadorFilas);
                tabla.setValueAt(xProducto.getId_producto(), cProveedor, 0);
                tabla.setValueAt(xProducto.getDescripcion_producto(), cProveedor, 1);
                tabla.setValueAt(xProducto.getDescripcion_proveedor(), cProveedor, 2);
                tabla.setValueAt(xProducto.getCosto_producto(), cProveedor, 3);
                tabla.setValueAt(xProducto.getUnidad_medida_producto(), cProveedor, 4);
                tabla.setValueAt(xProducto.getCantidad_producto(), cProveedor, 5);
                tabla.setValueAt(xProducto.getCaducidad_producto(), cProveedor, 6);
                tabla.setValueAt(xProducto.getMinimo_producto(), cProveedor, 7);
                System.out.println("Se encontro el registro " + xProducto.getDescripcion_producto());
                cProveedor++;
                contadorFilas++;
            }

            tblProducto.setModel(tabla);
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

        pnlBotones = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Busqueda de Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/producto.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(600, 300));
        setMinimumSize(new java.awt.Dimension(600, 300));
        setPreferredSize(new java.awt.Dimension(600, 300));

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblBuscar.setText("Buscar");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/buscar.png"))); // NOI18N
        btnBuscar.setActionCommand("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        txtBuscar.setToolTipText("");

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtBuscar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(btnSalir)
                        .addComponent(btnAgregar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblProducto.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tblProducto);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 540, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        MovimientoCompraDeProductos.tblDetalleCompra.setValueAt(tblProducto.getValueAt(tblProducto.getSelectedRow(), 0), MovimientoCompraDeProductos.tblDetalleCompra.getSelectedRow(), 0);
    }//GEN-LAST:event_btnAgregarActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscar;
    // End of variables declaration//GEN-END:variables
}
