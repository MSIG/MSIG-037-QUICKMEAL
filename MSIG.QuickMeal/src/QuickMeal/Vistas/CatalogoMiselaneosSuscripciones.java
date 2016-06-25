/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;


import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoSuscripcion;
import QuickMeal.Accesos.AccesoSuscripcion;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class CatalogoMiselaneosSuscripciones extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form CatalogoAdmonProductos
     */
    Mensaje Mensaje = new Mensaje();
    AccesoSuscripcion nuevaSuscripcion = new AccesoSuscripcion();
    ObjetoSuscripcion suscripcion = new ObjetoSuscripcion();
    String seleccion = "";

//AccesoProveedor nuevoProveedor = new AccesoProveedor();
    
    
    public CatalogoMiselaneosSuscripciones() {
        initComponents();
        cargaServicios();
    }
    
    private void cargaServicios(){
        ArrayList<ObjetoSuscripcion> lSuscripcion = new ArrayList();
            
        try{
            lSuscripcion = nuevaSuscripcion.retornaDescripcionServicio();
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda de usuarios " + error);
        }
        
        // Lleno el combobox con los registros que existen para proveedores
        for(ObjetoSuscripcion xServicio : lSuscripcion){
            cbxIdServicio.addItem(xServicio.getDescripcion_servicio());
        }
        
    }
    
    private void guardarRegistro(){
        // Si se ingresan todos los datos del producto asigno valor al objeto
        if(verificarDatos()){
            String estado = estadoSuscripcion(cbxEstado.getSelectedItem().toString());
            
            suscripcion.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
            suscripcion.setFecha_inicio_detalle_servicio(txtFechaInicio.getText());
            suscripcion.setFecha_final_detalle_servicio(txtFechaFin.getText());
            suscripcion.setEstado_detalle_servicio(estado);
            suscripcion.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        }
        
        try{
            nuevaSuscripcion.insertarSuscripcion(suscripcion);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar los datos de la suscripcion " + error);
        }
    }
        
    private boolean verificarDatos(){
        boolean vDatosCorrectos = true;
        
        if (txtIdCliente.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese el ID del cliente");
            vDatosCorrectos = false;
        }
        if (txtFechaInicio.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese la fecha de inicio");
            vDatosCorrectos = false;
        }
        if (txtFechaFin.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese la fecha fin");
            vDatosCorrectos = false;
        }
        if(cbxIdServicio.getSelectedItem().equals("Seleccionar")){
            Mensaje.manipulacionExcepciones("critico", "Seleccione el nombre el servicio");
            vDatosCorrectos = false;
        }
        
        return vDatosCorrectos;
    }
    
    public void limpiarCampos(){
        txtIdDetalleServicio.setText("");
        txtIdCliente.setText("");
        txtFechaInicio.setText("");
        txtFechaFin.setText("");
        cbxEstado.setSelectedItem("Activo");
        txtBuscar.setText("");
        
        cbxIdServicio.setSelectedItem("Administrador");
    }
    
    private void buscarEnRegistrosGuardados(){
        String busqueda = txtBuscar.getText();
        
        if(busqueda != null && !busqueda.isEmpty()){
            
            DefaultTableModel tabla = new DefaultTableModel();
            ArrayList<ObjetoSuscripcion> listaSuscripciones = new ArrayList();
            int cProveedor = 0;
            
            try{
                listaSuscripciones = nuevaSuscripcion.seleccionarSuscripcion(busqueda);
                System.out.println("SE CONSULTARON LOS DATOS DE LAS SUSCRIPCIONES");
                
            }catch(Exception error){
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }
            
            if(listaSuscripciones.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existen datos de suscripciones almacenados");
            }else{
                tabla.addColumn("ID CLIENTE");
                tabla.addColumn("CODIGO");
                tabla.addColumn("ESTADO");
                tabla.addColumn("SERVICIO");
                tabla.addColumn("FECHA INICIO");
                tabla.addColumn("FECHA FIN");
                tabla.setRowCount(listaSuscripciones.size());
                
                for(ObjetoSuscripcion xUsuario : listaSuscripciones){  
                    tabla.setValueAt(xUsuario.getId_cliente(), cProveedor, 0);
                    tabla.setValueAt(String.valueOf(xUsuario.getCodigo_cliente()), cProveedor, 1);
                    tabla.setValueAt(estadoSuscripcion(xUsuario.getEstado_detalle_servicio()), cProveedor, 2);
                    tabla.setValueAt(xUsuario.getDescripcion_servicio(), cProveedor, 3);
                    tabla.setValueAt(xUsuario.getFecha_inicio_detalle_servicio(), cProveedor, 4);
                    tabla.setValueAt(xUsuario.getFecha_final_detalle_servicio(), cProveedor, 5);
                    cProveedor++;
                }
                
                tblDetalleServicio.setModel(tabla);
            }
        }else{
            System.out.println("B");
            mostrarRegistrosGuardados();
        }
    }
    
    public void mostrarRegistrosGuardados(){
         DefaultTableModel tabla = new DefaultTableModel();
         ArrayList<ObjetoSuscripcion> listaSuscripciones = new ArrayList();
         int cProveedor = 0;
         
         try{
             listaSuscripciones = nuevaSuscripcion.retornaSuscripcion();
         }catch(Exception error){
             Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
         }
         
         if(listaSuscripciones.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existen datos de suscripciones almacenados");
            }else{
                tabla.addColumn("ID CLIENTE");
                tabla.addColumn("CODIGO");
                tabla.addColumn("ESTADO");
                tabla.addColumn("SERVICIO");
                tabla.addColumn("FECHA INICIO");
                tabla.addColumn("FECHA FIN");
                tabla.setRowCount(listaSuscripciones.size());
                
                for(ObjetoSuscripcion xUsuario : listaSuscripciones){
                    tabla.setValueAt(xUsuario.getId_cliente(), cProveedor, 0);
                    tabla.setValueAt(String.valueOf(xUsuario.getCodigo_cliente()), cProveedor, 1);
                    tabla.setValueAt(estadoSuscripcion(xUsuario.getEstado_detalle_servicio()), cProveedor, 2);
                    tabla.setValueAt(xUsuario.getDescripcion_servicio(), cProveedor, 3);
                    tabla.setValueAt(xUsuario.getFecha_inicio_detalle_servicio(), cProveedor, 4);
                    tabla.setValueAt(xUsuario.getFecha_final_detalle_servicio(), cProveedor, 5);
                    cProveedor++;
                }
                
                tblDetalleServicio.setModel(tabla);
            }
    }
    
    private int obtenerIdDetalleServicio(String pId_servicio, String pId_cliente, String pFecha_i, String pFecha_f, String pEstado){
        int vId = 0;
        ArrayList<ObjetoSuscripcion> BuscarSuscripcion = new ArrayList();
        
        try{
            BuscarSuscripcion = nuevaSuscripcion.retornaIdSuscripcion(pId_servicio, pId_cliente, pFecha_i, pFecha_f, pEstado);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el id de la suscripcion " + error);
        }
        
        if(BuscarSuscripcion.isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe crear la suscripcion en el sistema");
        }else{
            if(BuscarSuscripcion.size() > 1){
                System.out.println("EXISTEN " + BuscarSuscripcion.size() + " SUSCRIPCIONES CON ESTOS DATOS");
                Mensaje.manipulacionExcepciones("critico", "Este ID esta repetido");
            }else{
                vId = BuscarSuscripcion.get(0).getId_detalle_servicio();
            }
        }
        
        return vId;
        
    }
    
    private void actualizarRegistro(){
        String estado = estadoSuscripcion(cbxEstado.getSelectedItem().toString());
        
        suscripcion.setId_detalle_servicio(Integer.parseInt(txtIdDetalleServicio.getText()));
        suscripcion.setId_servicio(Integer.parseInt(txtIdServicio.getText()));
        suscripcion.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
        suscripcion.setFecha_inicio_detalle_servicio(txtFechaInicio.getText());
        suscripcion.setFecha_final_detalle_servicio(txtFechaFin.getText());
        suscripcion.setEstado_detalle_servicio(estado);
        
        try{
            nuevaSuscripcion.actualizarSuscripcion(suscripcion);
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error al actualizar la suscripcion " + error);
        }
    }
    
    private String estadoSuscripcion(String pEstado){
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
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEncabezado = new javax.swing.JPanel();
        lblIdDetalleServicio = new javax.swing.JLabel();
        txtIdDetalleServicio = new javax.swing.JTextField();
        lblIdCliente = new javax.swing.JLabel();
        lblFechaInicio = new javax.swing.JLabel();
        txtFechaInicio = new javax.swing.JTextField();
        lblFechaFin = new javax.swing.JLabel();
        txtFechaFin = new javax.swing.JTextField();
        lblIdServicio = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        cbxIdServicio = new javax.swing.JComboBox();
        lblEstado = new javax.swing.JLabel();
        txtIdServicio = new javax.swing.JTextField();
        cbxEstado = new javax.swing.JComboBox();
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
        tblDetalleServicio = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Suscripcion");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/suscripcion.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdDetalleServicio.setText("ID Detalle Servicio");
        lblIdDetalleServicio.setToolTipText("");

        txtIdDetalleServicio.setEditable(false);
        txtIdDetalleServicio.setToolTipText("Ingrese el codigo del producto");

        lblIdCliente.setText("ID Cliente");

        lblFechaInicio.setText("Fecha Inicio");
        lblFechaInicio.setToolTipText("");

        txtFechaInicio.setToolTipText("Ingrese la descripcion del producto");

        lblFechaFin.setText("Fecha Fin");

        txtFechaFin.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        lblIdServicio.setText("ID Servicio");

        cbxIdServicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbxIdServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxIdServicioActionPerformed(evt);
            }
        });

        lblEstado.setText("Estado");
        lblEstado.setToolTipText("");

        txtIdServicio.setEditable(false);
        txtIdServicio.setToolTipText("Ingrese el precio de compra, no puede ingresar letras");

        cbxEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Inactivo" }));

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdDetalleServicio)
                            .addComponent(lblFechaInicio))
                        .addGap(18, 18, 18))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblEstado)
                        .addGap(68, 68, 68)))
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(txtIdDetalleServicio, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtFechaInicio)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.Alignment.LEADING, 0, 166, Short.MAX_VALUE))
                .addGap(46, 46, 46)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblIdServicio)
                    .addComponent(lblFechaFin)
                    .addComponent(lblIdCliente))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtFechaFin, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbxIdServicio, javax.swing.GroupLayout.Alignment.LEADING, 0, 156, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdDetalleServicio)
                    .addComponent(txtIdDetalleServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblIdCliente)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEstado)
                    .addComponent(lblIdServicio)
                    .addComponent(cbxIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaInicio)
                    .addComponent(txtFechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaFin)
                    .addComponent(txtFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        txtFechaFin.getAccessibleContext().setAccessibleName("txtPrecioCompra");

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

        txtBuscar.setToolTipText("Ingrese el nombre del producto que desea buscar");

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

        tblDetalleServicio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDetalleServicio.getTableHeader().setReorderingAllowed(false);
        tblDetalleServicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleServicioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleServicio);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
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
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       guardarRegistro();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Mensaje.manipulacionExcepciones("interrogante", "¿Esta seguro que desea salir?");
        if (Mensaje.valor == 0) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblDetalleServicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleServicioMouseClicked
        if(String.valueOf(tblDetalleServicio.getSelectedColumn()).equals("0")){
            int fila_seleccionada = tblDetalleServicio.getSelectedRow();
            int columna_seleccionada = tblDetalleServicio.getSelectedColumn();
            
            System.out.println("LLEGA " + estadoSuscripcion(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 1))));
            String estado = estadoSuscripcion(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 1)));
            System.out.println("LLEGA 2 " + estado);
            
            String estado2 = estado;
            
            estado = estadoSuscripcion(estado);
            System.out.println("LLEGA 3 " + estado);
            
            txtIdCliente.setText(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 0)));
            //txtEstado.setText(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 1)));
            cbxIdServicio.setSelectedItem(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 2)));
            txtFechaInicio.setText(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 3)));
            txtFechaFin.setText(String.valueOf(tblDetalleServicio.getValueAt(fila_seleccionada, columna_seleccionada + 4)));
            cbxEstado.setSelectedItem(estado);
            
            // Ya seleccionado el producto busco el ID
            txtIdDetalleServicio.setText(String.valueOf(obtenerIdDetalleServicio(txtIdServicio.getText(), txtIdCliente.getText(), txtFechaInicio.getText(), txtFechaFin.getText(), estado2)));           
        }
        
    }//GEN-LAST:event_tblDetalleServicioMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtFechaInicio.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe seleccionar el registro a modificar");
        }else{
            actualizarRegistro();
            mostrarRegistrosGuardados();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void cbxIdServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxIdServicioActionPerformed
        seleccion = "";
        seleccion = cbxIdServicio.getSelectedItem().toString();
        
        ArrayList<ObjetoSuscripcion> lSuscripcion = new ArrayList();
            
        try{
            lSuscripcion = nuevaSuscripcion.retornaDescripcionServicio();
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error al obtener codigo de proveedor seleccionado " + error);
        }
        
        // Recorro la lista de proveedores para obtener el id del proveedor seleccionado
        for(ObjetoSuscripcion xSuscripcion : lSuscripcion){
            if(xSuscripcion.getDescripcion_servicio().equals(seleccion)){
                txtIdServicio.setText(String.valueOf(xSuscripcion.getId_servicio()));
            }
        }
    }//GEN-LAST:event_cbxIdServicioActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEstado;
    private javax.swing.JComboBox cbxIdServicio;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblIdDetalleServicio;
    private javax.swing.JLabel lblIdServicio;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblDetalleServicio;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFechaFin;
    private javax.swing.JTextField txtFechaInicio;
    private javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtIdDetalleServicio;
    private javax.swing.JTextField txtIdServicio;
    // End of variables declaration//GEN-END:variables
}
