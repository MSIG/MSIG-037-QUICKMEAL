/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;


import QuickMeal.Accesos.AccesoAjusteProducto;
import QuickMeal.Accesos.AccesoProducto;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoProducto;
import QuickMeal.Objetos.ObjetoAjusteProducto;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class MovimientoAjusteProducto extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form CatalogoAdmonProductos
     */
    Mensaje Mensaje = new Mensaje();
    AccesoProducto nuevoProveedor = new AccesoProducto();
    AccesoProducto nuevoProducto = new AccesoProducto();
    AccesoAjusteProducto nuevoAjusteProducto = new AccesoAjusteProducto();
    ObjetoProducto producto = new ObjetoProducto();
    ObjetoAjusteProducto ajuste = new ObjetoAjusteProducto();
    String seleccion = "";

//AccesoProveedor nuevoProveedor = new AccesoProveedor();
    
    
    public MovimientoAjusteProducto() {
        initComponents();
    }
    
    public void limpiarCampos(){
        btnAumentar.setEnabled(true);
        txtIdProducto.setText("");
        txtDescripcionProducto.setText("");
        txtCantidadProducto.setText("");
        txtBuscar.setText("");
        txtBuscar.setText("");
        txtExistencia.setText("");
        txtMotivo.setText("");
        txtUnidadMedida.setText("");
    }
    
    private void buscarEnRegistrosGuardados(){
        String busqueda = txtBuscar.getText();
    
        if (busqueda != null) {
            DefaultTableModel tabla = new DefaultTableModel();
            ArrayList<ObjetoProducto> listaProductos = new ArrayList();
            int contadorFilas = 1;
            int cProveedor = 0;

            try {
                listaProductos = nuevoProducto.seleccionarProducto(busqueda);

            } catch (Exception error) {
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }

            if (listaProductos.isEmpty()) {
                Mensaje.manipulacionExcepciones("critico", "No existen datos de productos almacenados");
            } else {
                tabla.addColumn("ID Producto");
                tabla.addColumn("Descripcion");
                tabla.addColumn("Costo");
                tabla.addColumn("Unidad Medida");
                tabla.addColumn("Cantidad");
                tabla.addColumn("Caducidad");
                tabla.addColumn("Minimo");
                tabla.addColumn("Fecha Caducidad");

                for (ObjetoProducto xProducto : listaProductos) {
                    tabla.setRowCount(contadorFilas);
                    tabla.setValueAt(xProducto.getId_producto(), cProveedor, 0);
                    tabla.setValueAt(xProducto.getDescripcion_producto(), cProveedor, 1);
                    tabla.setValueAt(xProducto.getCosto_producto(), cProveedor, 2);
                    tabla.setValueAt(xProducto.getUnidad_medida_producto(), cProveedor, 3);
                    tabla.setValueAt(xProducto.getCantidad_producto(), cProveedor, 4);
                    tabla.setValueAt(xProducto.getCaducidad_producto(), cProveedor, 5);
                    tabla.setValueAt(xProducto.getMinimo_producto(), cProveedor, 6);
                    tabla.setValueAt(xProducto.getFecha_caduciad_producto(), cProveedor, 7);
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
        int i = 0;

        try {
            listaProductos = nuevoProducto.retornaProductos();
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
        }

        if (listaProductos.isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "No existen datos de productos almacenados");
        } else {
            tabla.addColumn("ID Producto");
            tabla.addColumn("Proveedor");
            tabla.addColumn("Costo");
            tabla.addColumn("Unidad Medida");
            tabla.addColumn("Cantidad");
            tabla.addColumn("Caducidad");
            tabla.addColumn("Minimo");
            tabla.addColumn("Fecha Caducidad");
            tabla.setRowCount(listaProductos.size());
            
            for (ObjetoProducto x : listaProductos) {
                tabla.setValueAt(x.getId_producto(), i, 0);
                tabla.setValueAt(x.getDescripcion_producto(), i, 1);
                tabla.setValueAt(x.getCosto_producto(), i, 2);
                tabla.setValueAt(x.getUnidad_medida_producto(), i, 3);
                tabla.setValueAt(x.getCantidad_producto(), i, 4);
                tabla.setValueAt(x.getCaducidad_producto(), i, 5);
                tabla.setValueAt(x.getMinimo_producto(), i, 6);
                tabla.setValueAt(x.getFecha_caduciad_producto(), i, 7);
                i++;
            }
            tblProducto.setModel(tabla);
        }
    }
    
    private int obtenerIdProducto(String pDescripcion_producto, String pId_proveedor){
        int vId_producto = 0;
        ArrayList<ObjetoProducto> BuscarProducto = new ArrayList();
        
        try{
            BuscarProducto = nuevoProducto.buscarProducto(pDescripcion_producto, pId_proveedor);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el id del producto " + error);
        }
        
        if(BuscarProducto.isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe crear el producto en el sistema");
        }else{
            if(BuscarProducto.size() > 1){
                System.out.println("EXISTEN " + BuscarProducto.size() + " PRODUCTOS CON ESTE NOMBRE");
                Mensaje.manipulacionExcepciones("critico", "Este numero de Nit esta repetido");
            }else{
                vId_producto = BuscarProducto.get(0).getId_producto();
            }
        }
        
        return vId_producto;
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblIdProducto = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        lblDescProducto = new javax.swing.JLabel();
        txtDescripcionProducto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtUnidadMedida = new javax.swing.JTextField();
        txtExistencia = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtMotivo = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnDisminuir = new javax.swing.JButton();
        btnAumentar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblProducto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ajuste de Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/balance.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdProducto.setText("ID Producto");

        txtIdProducto.setToolTipText("");
        txtIdProducto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                IdProductoLostFocus(evt);
            }
        });

        lblDescProducto.setText("Descripcion");
        lblDescProducto.setToolTipText("");

        txtDescripcionProducto.setEditable(false);
        txtDescripcionProducto.setToolTipText("");

        jLabel1.setText("Cantidad");

        jLabel2.setText("Existencia");

        txtUnidadMedida.setEditable(false);

        txtExistencia.setEditable(false);

        jLabel3.setText("Motivo");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdProducto)
                    .addComponent(lblDescProducto)
                    .addComponent(jLabel3))
                .addGap(30, 30, 30)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionProducto)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtExistencia)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtMotivo))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtUnidadMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtExistencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblBuscar.setText("Buscar");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnDisminuir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/minus.png"))); // NOI18N
        btnDisminuir.setText("Quitar");
        btnDisminuir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDisminuirActionPerformed(evt);
            }
        });

        btnAumentar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/agregar.png"))); // NOI18N
        btnAumentar.setText("Agregar");
        btnAumentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAumentarActionPerformed(evt);
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
                .addComponent(txtBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAumentar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDisminuir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(btnLimpiar)
                        .addComponent(btnDisminuir)
                        .addComponent(btnAumentar)
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
        tblProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblProductoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblProducto);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAumentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAumentarActionPerformed

       ajuste.setId_producto(Integer.parseInt(txtIdProducto.getText()));
       ajuste.setCantidad_ajusteproducto(Double.parseDouble(txtCantidadProducto.getText()));
       ajuste.setExistencia_ajusteproducto(Double.parseDouble(txtExistencia.getText()));
       ajuste.setDescripcion_ajusteproducto(txtDescripcionProducto.getText());
       ajuste.setMotivo_ajusteproducto(txtMotivo.getText());
       ajuste.setTipo_ajuste("Aumentar");
       ajuste.setId_usuario(Integer.parseInt(QuickMeal.txtCodigoUsuario.getText()));
       
       // Llamo a la clase que inserta en la tabla ajuste_producto
       nuevoAjusteProducto.insertarAjusteProducto(ajuste);
        
       mostrarRegistrosGuardados();
       limpiarCampos();
    }//GEN-LAST:event_btnAumentarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnDisminuirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDisminuirActionPerformed
       // Lleno el objeto
       ajuste.setId_producto(Integer.parseInt(txtIdProducto.getText()));
       ajuste.setCantidad_ajusteproducto(Double.parseDouble(txtCantidadProducto.getText()));
       ajuste.setExistencia_ajusteproducto(Double.parseDouble(txtExistencia.getText()));
       ajuste.setDescripcion_ajusteproducto(txtDescripcionProducto.getText());
       ajuste.setMotivo_ajusteproducto(txtMotivo.getText());
       ajuste.setTipo_ajuste("Disminuir");
       ajuste.setId_usuario(Integer.parseInt(QuickMeal.txtCodigoUsuario.getText()));
       
       // Llamo a la clase que inserta en la tabla ajuste_producto
       nuevoAjusteProducto.insertarAjusteProducto(ajuste);
        
        // nuevoProducto.disminuirProducto(txtIdProducto.getText(), txtCantidadProducto.getText());
        // disminuir();
        mostrarRegistrosGuardados();
        limpiarCampos();
    }//GEN-LAST:event_btnDisminuirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        txtDescripcionProducto.setText(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 1)));
        txtUnidadMedida.setText(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 3)));
        txtExistencia.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 4).toString());
        txtIdProducto.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 0).toString());
    }//GEN-LAST:event_tblProductoMouseClicked

    private void IdProductoLostFocus(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_IdProductoLostFocus
        txtDescripcionProducto.setText(nuevoProducto.buscarDescripcionProducto(txtIdProducto.getText()));
    }//GEN-LAST:event_IdProductoLostFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAumentar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDisminuir;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtDescripcionProducto;
    private javax.swing.JTextField txtExistencia;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtMotivo;
    private javax.swing.JTextField txtUnidadMedida;
    // End of variables declaration//GEN-END:variables
}
