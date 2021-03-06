/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.AccesoCliente;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoCliente;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class BuscarClientes extends javax.swing.JInternalFrame {

    Mensaje Mensaje = new Mensaje();
    ObjetoCliente cliente = new ObjetoCliente();
    AccesoCliente nuevoCliente = new AccesoCliente();
    
    /**
     * Creates new form wdwIngresoClientes
     */
    public BuscarClientes() {
        initComponents();
    }
    
    private void buscarCliente() {

        boolean cliente_encontrado = false;

        ArrayList<ObjetoCliente> BuscarCliente = new ArrayList();

        try {
            BuscarCliente = nuevoCliente.buscarCliente(txtCCCliente.getText());
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un erro al buscar informacion sobre este cliente " + error);
        }

        if (BuscarCliente.isEmpty()) {
            cliente_encontrado = false;
        } else {
            if(BuscarCliente.size() > 1){
                cliente_encontrado = false;
                System.out.println("EXISTEN " + BuscarCliente.size() + " CLIENTES CON ESTE CODIGO");
                Mensaje.manipulacionExcepciones("critico", "Este codigo esta repetido");
            }else{
                cliente_encontrado = true;
                
                String estado = BuscarCliente.get(0).getEstado_cliente();
                
                txtIdCliente.setText(String.valueOf(BuscarCliente.get(0).getId_cliente()));
                txtNitCliente.setText(BuscarCliente.get(0).getNit_cliente());
                txtCCCliente.setText(BuscarCliente.get(0).getCc_cliente());
                txtNombreCliente.setText(BuscarCliente.get(0).getNombre_cliente());
                cbxEmpresaCliente.setSelectedItem(BuscarCliente.get(0).getEmpresa_cliente());
            }
        }
    }
    
    private void buscarEnRegistrosGuardados(){
        String busqueda = txtBuscar.getText();
        
        if(busqueda != null){
            DefaultTableModel tabla = new DefaultTableModel(); 
            ArrayList<ObjetoCliente> listaClientes = new ArrayList();
            
            int contadorFilas = 1;
            int c = 0;
            
            // Verificar algun erro al cargar la informacion de los proveedores
            try{
                listaClientes = nuevoCliente.seleccionarCliente(busqueda);      
                System.out.println("SE CONSULTARON LOS DATOS DE CLIENTESS");
            }catch(Error error){
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }
            
            // Mostrar la informacion de los proveedores en la tabla de la vista
            if(listaClientes.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existe datos almacenados de los clientes");
            }else{
                tabla.addColumn("CODIGO");
                tabla.addColumn("NIT");
                tabla.addColumn("C.C.");
                tabla.addColumn("NOMBRE");
                tabla.addColumn("EMPRESA");
                tabla.addColumn("ESTADO");
                
                for(ObjetoCliente xCliente : listaClientes){
                    String estado = estadoCliente(xCliente.getEstado_cliente());
                    tabla.setRowCount(contadorFilas);
                    tabla.setValueAt(xCliente.getCodigo_cliente(), c, 0);
                    tabla.setValueAt(xCliente.getNit_cliente(),    c, 1);
                    tabla.setValueAt(xCliente.getCc_cliente(),     c, 2);
                    tabla.setValueAt(xCliente.getNombre_cliente(), c, 3);
                    tabla.setValueAt(xCliente.getEmpresa_cliente(),c, 4);
                    tabla.setValueAt(xCliente.getEstado_cliente(), c, 5);
                    c++;
                    contadorFilas++;
                    
                }
               
                tblCliente.setModel(tabla);
                tblCliente.getColumnModel().getColumn(1).setPreferredWidth(150);
                tblCliente.getColumnModel().getColumn(2).setPreferredWidth(150);
                tblCliente.getColumnModel().getColumn(3).setPreferredWidth(400);
                tblCliente.getColumnModel().getColumn(4).setPreferredWidth(150);
                tblCliente.getColumnModel().getColumn(5).setPreferredWidth(150);
            }
        }else{
            mostrarRegistrosGuardados();
        }   
    }
    
    public void mostrarRegistrosGuardados(){
        DefaultTableModel tabla = new DefaultTableModel();
        
         ArrayList<ObjetoCliente> listaClientes = new ArrayList();
         
         try{
             listaClientes = nuevoCliente.retornaCliente();
             
         }catch(Exception error){
             Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
         }
         
         if(listaClientes.isEmpty()){
             Mensaje.manipulacionExcepciones("critico", "No existen datos almacenados de clientes");
         }else{
                tabla.addColumn("CODIGO");
                tabla.addColumn("NIT");
                tabla.addColumn("NOMBRE");
                tabla.addColumn("EMPRESA");
                
                tabla.setRowCount(listaClientes.size());
                int c = 0;
                
                for(ObjetoCliente xCliente : listaClientes){
                    String estado = estadoCliente(xCliente.getEstado_cliente());
                    
                    tabla.setValueAt(xCliente.getCodigo_cliente(), c, 0);
                    tabla.setValueAt(xCliente.getNit_cliente(), c, 1);
                    tabla.setValueAt(xCliente.getCc_cliente(), c, 2);
                    tabla.setValueAt(xCliente.getNombre_cliente(), c, 4);
                    tabla.setValueAt(xCliente.getEmpresa_cliente(), c, 7);
                    
                    System.out.println("Se encontro el registro " + xCliente.getNombre_cliente());
                    c++;
                }
                
         }
         tblCliente.setModel(tabla);
         tblCliente.getColumnModel().getColumn(2).setPreferredWidth(400);
    }

    private int obtenerIdCliente(String codigo){
        
        int vId_cliente = 0;
        ArrayList<ObjetoCliente> BuscarCliente = new ArrayList();
        
        try{
            BuscarCliente = nuevoCliente.buscarCliente(codigo);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar centro de costto del cliente " + error);
        }
        
        if(BuscarCliente.isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe crear el cliente en el sistema");
        }else{
            if(BuscarCliente.size() > 1){
                System.out.println("EXISTEN " + BuscarCliente.size() + " CLIENTES CON ESTE CENTRO DE COSTO");
                Mensaje.manipulacionExcepciones("critico", "Este numero de centro de costo esta repetido");
            }else{
                vId_cliente = BuscarCliente.get(0).getId_cliente();
            }
        }
        
        return vId_cliente;
    }
    
    private String estadoCliente(String pEstado){
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
        txtIdCliente.setText("");
        txtCodigoCliente.setText("");
        txtNitCliente.setText("");
        txtCCCliente.setText("");
        txtNombreCliente.setText("");
        cbxEmpresaCliente.setSelectedItem("Pantaleon S.A.");
        cbxEstadoCliente.setSelectedItem("Activo");
        txtBuscar.setText("");
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
        lblIdCliente = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        lblNombreCliente = new javax.swing.JLabel();
        lblNitCliente = new javax.swing.JLabel();
        txtNitCliente = new javax.swing.JTextField();
        lblDirCliente = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        txtCCCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoCliente = new javax.swing.JTextField();
        cbxEstadoCliente = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        cbxEmpresaCliente = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        pnlBotones = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Clientes");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/clientes.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 400));
        setMinimumSize(new java.awt.Dimension(700, 400));
        setPreferredSize(new java.awt.Dimension(700, 400));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdCliente.setText("ID Clente");

        txtIdCliente.setEditable(false);
        txtIdCliente.setToolTipText("");

        lblNombreCliente.setText("Centro de Costo");

        lblNitCliente.setText("NIT");

        txtNitCliente.setEditable(false);
        txtNitCliente.setToolTipText("");

        lblDirCliente.setText("Nombre");

        txtNombreCliente.setEditable(false);
        txtNombreCliente.setToolTipText("");

        txtCCCliente.setEditable(false);
        txtCCCliente.setToolTipText("");

        jLabel1.setText("Codigo");

        txtCodigoCliente.setEditable(false);
        txtCodigoCliente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCodigoClienteFocusLost(evt);
            }
        });

        cbxEstadoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Vacaciones", "Inactivo" }));

        jLabel4.setText("Estado");

        cbxEmpresaCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pantaleon S. A.", "Concepcion", "Visitante", "Otra" }));

        jLabel5.setText("Empresa");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdCliente)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblDirCliente)
                        .addGap(16, 16, 16))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombreCliente)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblNitCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNitCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreCliente)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtCCCliente, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(cbxEmpresaCliente, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdCliente)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreCliente)
                    .addComponent(txtCCCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNitCliente)
                    .addComponent(txtNitCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtCodigoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblDirCliente)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4)
                        .addComponent(cbxEstadoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(cbxEmpresaCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblBuscar.setText("Buscar");

        txtBuscar.setToolTipText("Ingreseel nombre dle cliente a buscar");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/buscar.png"))); // NOI18N
        btnBuscar.setActionCommand("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/agregar.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

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
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
   
        String estado = estadoCliente(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 4)));
        estado = estadoCliente(estado);

        //ASIGNAR AL ENCABEZADO LOS DATOS
        txtCodigoCliente.setText(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 0)));
        txtNitCliente.setText(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 1)));
        txtCCCliente.setText(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 2)));
        txtNombreCliente.setText(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 3)));
        cbxEmpresaCliente.setSelectedItem(String.valueOf(tblCliente.getValueAt(tblCliente.getSelectedRow(), 4)));
        cbxEstadoCliente.setSelectedItem(estado);

        //YA SELECCIONADOS LOS DATOS BUSCO EL CODIGO DEL CLIENTE
        txtIdCliente.setText(String.valueOf(obtenerIdCliente(txtCodigoCliente.getText())));
    }//GEN-LAST:event_tblClienteMouseClicked

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Mensaje.manipulacionExcepciones("interrogante", "¿Esta seguro que desea salir?");
        if (Mensaje.valor == 0) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       CodigoEmpleado.txtCodigoEmpleado.setText(txtCodigoCliente.getText());
       dispose();
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void txtCodigoClienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCodigoClienteFocusLost
        buscarCliente();
    }//GEN-LAST:event_txtCodigoClienteFocusLost
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEmpresaCliente;
    private javax.swing.JComboBox cbxEstadoCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDirCliente;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblNitCliente;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCCCliente;
    private javax.swing.JTextField txtCodigoCliente;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtNitCliente;
    private javax.swing.JTextField txtNombreCliente;
    // End of variables declaration//GEN-END:variables
}
