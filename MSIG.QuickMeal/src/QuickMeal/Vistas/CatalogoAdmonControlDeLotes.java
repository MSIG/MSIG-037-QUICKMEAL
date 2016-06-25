/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;
import QuickMeal.Accesos.AccesoLote;
import QuickMeal.Objetos.ObjetoLote;
import QuickMeal.Accesos.Mensaje;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class CatalogoAdmonControlDeLotes extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form CatalogoAdmonProductos
     */
    public CatalogoAdmonControlDeLotes() {
        initComponents();
    }
    
    Mensaje Mensaje = new Mensaje();
    AccesoLote nuevoLote = new AccesoLote();
    ObjetoLote lote = new ObjetoLote();
    
    private void guardarRegistro(){
        // Si se ingresan todos los datos del producto asigno valor al objeto
        if(verificarDatosLote()){
            String estado = estadoLote(cbxEstadoLote.getSelectedItem().toString());
            
            lote.setFecha_caduca_lote(txtFechaCaducaLote.getText());
            lote.setNota_lote(txtNotaLote.getText());
            lote.setEstado_lote(estado);
            
        }
        
        try{
            nuevoLote.insertarLote(lote);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar los datos del lote " + error);
        }
    }
        
    private boolean verificarDatosLote(){
        boolean vDatosCorrectos = true;
        
        if(txtIdDetalleCompra.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("critico", "Ingrese un ID de compra");
            vDatosCorrectos = false;
        }
        if (txtFechaCaducaLote.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese la fecha de caducidad para el lote");
            vDatosCorrectos = false;
        }
        if (txtNotaLote.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese una nota para el lote");
            vDatosCorrectos = false;
        }
        
        return vDatosCorrectos;
    }
    
    private void buscarEnRegistrosGuardados(){
        String busqueda = txtBuscar.getText();
    
        if(busqueda != null){
            DefaultTableModel tabla = new DefaultTableModel();
            ArrayList<ObjetoLote> listaLotes = new ArrayList();
            int contadorFilas = 1;
            int cLote = 0;
            
            try{
                listaLotes = nuevoLote.seleccionarLote(busqueda);
                System.out.println("SE CONSULTARON LOS DATOS DE LOTES");
                
            }catch(Exception error){
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }
            
            if(listaLotes.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existen datos de lotes almacenados");
            }else{
                tabla.addColumn("ID DETALLE COMPRA");
                tabla.addColumn("FECHA CADUCIDAD");
                tabla.addColumn("ESTADO");
                tabla.addColumn("NOTA");
                
                for(ObjetoLote xLote : listaLotes){
                    tabla.setRowCount(contadorFilas);
                    
                    String estado = estadoLote(cbxEstadoLote.getSelectedItem().toString());
                    
                    tabla.setValueAt(xLote.getId_detalle_compra(), cLote, 0);
                    tabla.setValueAt(xLote.getFecha_caduca_lote(), cLote, 1);
                    tabla.setValueAt(estado, cLote, 2);
                    tabla.setValueAt(xLote.getNota_lote(), cLote, 3);
                    
                    System.out.println("Se encontro el registro " + xLote.getId_detalle_compra());
                    cLote++;
                    contadorFilas++;
                }
                
                tblLotes.setModel(tabla);
            }
        }else{
            mostrarRegistrosGuardados();
        }
    }
    
    public void mostrarRegistrosGuardados(){
         DefaultTableModel tabla = new DefaultTableModel();
         ArrayList<ObjetoLote> listaLotes = new ArrayList();
         int contadorFilas = 1;
         int cLote = 0;
         
         try{
             listaLotes = nuevoLote.retornaLote();
         }catch(Exception error){
             Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
         }
         
         if(listaLotes.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existen datos de productos almacenados");
            }else{
                tabla.addColumn("ID DETALLE COMPRA");
                tabla.addColumn("FECHA CADUCIDAD");
                tabla.addColumn("ESTADO");
                tabla.addColumn("NOTA");
                
                for(ObjetoLote xLote : listaLotes){
                    tabla.setRowCount(contadorFilas);
                    
                    String estado = estadoLote(cbxEstadoLote.getSelectedItem().toString());
                    
                    tabla.setValueAt(xLote.getId_detalle_compra(), cLote, 0);
                    tabla.setValueAt(xLote.getFecha_caduca_lote(), cLote, 1);
                    tabla.setValueAt(estado, cLote, 2);
                    tabla.setValueAt(xLote.getNota_lote(), cLote, 3);
                    
                    System.out.println("Se encontro el registro " + xLote.getId_detalle_compra());
                    cLote++;
                    contadorFilas++;
                }
                
                tblLotes.setModel(tabla);
            }
    }
    
    private void actualizarRegistro(){
        String estado = estadoLote(cbxEstadoLote.getSelectedItem().toString());
        
        lote.setId_lote(Integer.parseInt(txtIdLote.getText()));
        lote.setId_detalle_compra(Integer.parseInt(txtIdDetalleCompra.getText()));
        lote.setFecha_caduca_lote(txtFechaCaducaLote.getText());
        lote.setNota_lote(txtNotaLote.getText());
        lote.setEstado_lote(estado);
        
        try{
            nuevoLote.actualizarLote(lote);
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error al actualizar el producto " + error);
        }
    }
    
    private String estadoLote(String pEstado){
        String vEstado = "";
        
        if(pEstado.equals("Activo")){
            vEstado = "A";
        }
        
        if(pEstado.equals("Vacaciones")){
            vEstado = "V";
        }
        
        if(pEstado.equals("Inactivo")){
            vEstado = "I";
        }
        
        if(pEstado.equals("A")){
            vEstado = "Activo";
        }
        
        if(pEstado.equals("V")){
            vEstado = "Vacaciones";
        }
        
        if(pEstado.equals("I")){
            vEstado = "Inactivo";
        }
        
        return vEstado;        
    }
    
    private void limpiarCampos(){
        btnGuardar.setEnabled(true);
        txtIdLote.setText("");
        txtIdDetalleCompra.setText("");
        txtFechaCaducaLote.setText("");
        txtNotaLote.setText("");
        cbxEstadoLote.setSelectedItem("Activo");
        txtBuscar.setText("");
    }
    
    private int obtenerIdLote(String pId_detalle_compra, String pFecha_caduca_lote, String pEstado_lote, String pNota_lote){
        int vId_lote = 0;
        ArrayList<ObjetoLote> BuscarLote = new ArrayList();
        
        try{
            BuscarLote = nuevoLote.buscarLote(pId_detalle_compra, pFecha_caduca_lote, pEstado_lote, pNota_lote);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el ID del lote " + error);
        }
        
        if(BuscarLote.isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe crear el lote en el sistema");
        }else{
            if(BuscarLote.size() > 1){
                System.out.println("EXISTEN " + BuscarLote.size() + " LOTES CON ESTE ID");
                Mensaje.manipulacionExcepciones("critico", "Este numero de centro de lote esta repetido");
            }else{
                vId_lote = BuscarLote.get(0).getId_lote();
            }
        }
        
        return vId_lote;
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
        txtIdLote = new javax.swing.JTextField();
        lblMarcaProducto = new javax.swing.JLabel();
        lblPrecioCompraProducto = new javax.swing.JLabel();
        txtIdDetalleCompra = new javax.swing.JTextField();
        txtFechaCaducaLote = new javax.swing.JTextField();
        cbxEstadoLote = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNotaLote = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblLotes = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Control de Lotes");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/lote.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdProducto.setText("ID Lote");

        txtIdLote.setEditable(false);
        txtIdLote.setToolTipText("");

        lblMarcaProducto.setText("Id Detalle Compra");

        lblPrecioCompraProducto.setText("Fecha Caducidad");

        cbxEstadoLote.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        jLabel1.setText("Estado");

        jLabel2.setText("Nota sobre Lote");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdProducto)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdLote)
                    .addComponent(cbxEstadoLote, 0, 128, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMarcaProducto)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtIdDetalleCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblPrecioCompraProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaCaducaLote))
                    .addComponent(txtNotaLote))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtIdLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProducto)
                    .addComponent(txtIdDetalleCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioCompraProducto)
                    .addComponent(txtFechaCaducaLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxEstadoLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtNotaLote, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        btnModificar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/editar.png"))); // NOI18N
        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
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
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                        .addComponent(btnModificar)
                        .addComponent(btnGuardar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblLotes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblLotes.getTableHeader().setReorderingAllowed(false);
        tblLotes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblLotesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblLotes);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarRegistro();
        mostrarRegistrosGuardados();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtNotaLote.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe seleccionar el registro a modificar");
        }else{
            actualizarRegistro();
            mostrarRegistrosGuardados();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Mensaje.manipulacionExcepciones("interrogante", "¿Esta seguro que desea salir?");
        if (Mensaje.valor == 0) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblLotesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblLotesMouseClicked

        btnGuardar.setEnabled(false);
        int fila_seleccionada = tblLotes.getSelectedRow();
        int columna_seleccionada = tblLotes.getSelectedColumn();

        System.out.println("LLEGA " + estadoLote(String.valueOf(tblLotes.getValueAt(fila_seleccionada, columna_seleccionada + 2))));
        String estado = estadoLote(String.valueOf(tblLotes.getValueAt(fila_seleccionada, columna_seleccionada + 2)));
        System.out.println("LLEGA 2 " + estado);

        estado = estadoLote(estado);
        System.out.println("LLEGA 3 " + estado);

        // ASIGNAR AL ENCABEZADO LOS DATOS
        txtIdDetalleCompra.setText(String.valueOf(tblLotes.getValueAt(fila_seleccionada, columna_seleccionada)));
        txtNotaLote.setText(String.valueOf(tblLotes.getValueAt(fila_seleccionada, columna_seleccionada + 1)));
        //cbxEstadoLote.setText(String.valueOf(tblLotes.getValueAt(fila_seleccionada, columna_seleccionada + 2)));
        txtFechaCaducaLote.setText(String.valueOf(tblLotes.getValueAt(fila_seleccionada, columna_seleccionada + 3)));
        cbxEstadoLote.setSelectedItem(estado);

        //YA SELECCIONADO EL NIT BUSCO EL PROVEEDOR
        txtIdLote.setText(String.valueOf(obtenerIdLote(txtIdDetalleCompra.getText(), txtFechaCaducaLote.getText(), estado, txtNotaLote.getText())));
    }//GEN-LAST:event_tblLotesMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEstadoLote;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JLabel lblPrecioCompraProducto;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblLotes;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFechaCaducaLote;
    private javax.swing.JTextField txtIdDetalleCompra;
    private javax.swing.JTextField txtIdLote;
    private javax.swing.JTextField txtNotaLote;
    // End of variables declaration//GEN-END:variables
}
