/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;


import QuickMeal.Accesos.AccesoEmpleado;
import QuickMeal.Accesos.AccesoUsuario;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoEmpleado;
import QuickMeal.Objetos.ObjetoUsuario;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */
public class CatalogoAdmonUsuarios extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form CatalogoAdmonProductos
     */
    Mensaje Mensaje = new Mensaje();
    AccesoUsuario nuevoUsuario = new AccesoUsuario();
    ObjetoUsuario usuario = new ObjetoUsuario();
    String seleccion = "";

//AccesoProveedor nuevoProveedor = new AccesoProveedor();
    
    
    public CatalogoAdmonUsuarios() {
        initComponents();
        cargaUsuarios();
    }
    
    private void cargaUsuarios(){
        ArrayList<ObjetoUsuario> lUsuarios = new ArrayList();
            
        try{
            lUsuarios = nuevoUsuario.retornaNombreEmpleados();
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda de usuarios " + error);
        }
        
        // Lleno el combobox con los registros que existen para proveedores
        for(ObjetoUsuario xUsuario : lUsuarios){
            cbxNombreEmpleado.addItem(xUsuario.getNombre_empleado());
        }
        
    }
    
    private void guardarRegistro() {
        usuario.setId_empleado(Integer.parseInt(txtIdEmpleado.getText()));
        usuario.setNombre_usuario(txtNombreUsuario.getText());
        usuario.setContrasenia_usuario(txtContraseniaUsuario.getText());
        usuario.setTipo_usuario(cbxTipoUsuario.getSelectedItem().toString());
        try {
            nuevoUsuario.insertarUsuario(usuario);
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar los datos del usuario " + error);
        }
    }
    
    public void limpiarCampos(){
        btnGuardar.setEnabled(true);
        txtIdUsuario.setText("");
        txtIdEmpleado.setText("");
        txtNombreUsuario.setText("");
        txtContraseniaUsuario.setText("");
        txtBuscar.setText("");
        
        cbxNombreEmpleado.setSelectedItem("Seleccionar");
        cbxTipoUsuario.setSelectedItem("Administrador");
    }
    
    private void buscarEnRegistrosGuardados(){
        String busqueda = txtBuscar.getText();
    
        if(busqueda != null){
            
            DefaultTableModel tabla = new DefaultTableModel();
            ArrayList<ObjetoUsuario> listaUsuarios = new ArrayList();
            
            int contadorFilas = 1;
            int cProveedor = 0;
            
            try{
                listaUsuarios = nuevoUsuario.seleccionarUsuario(busqueda);
                System.out.println("SE CONSULTARON LOS DATOS DE USUARIOS");
                
            }catch(Exception error){
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }
            
            if(listaUsuarios.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existen datos de usuarios almacenados");
            }else{
                tabla.addColumn("Usuario");
                tabla.addColumn("Contrasenia");
                tabla.addColumn("Tipo");
                tabla.addColumn("Nombre");
                
                for(ObjetoUsuario xUsuario : listaUsuarios){
                    tabla.setRowCount(contadorFilas);
                    
                    tabla.setValueAt(xUsuario.getNombre_usuario(), cProveedor, 0);
                    tabla.setValueAt(xUsuario.getContrasenia_usuario(), cProveedor, 1);
                    tabla.setValueAt(xUsuario.getTipo_usuario(), cProveedor, 2);
                    tabla.setValueAt(xUsuario.getNombre_empleado(), cProveedor, 3);
                    
                    System.out.println("Se encontro el registro " + xUsuario.getNombre_usuario());
                    cProveedor++;
                    contadorFilas++;
                }
                
                tblUsuario.setModel(tabla);
            }
        }else{
            mostrarRegistrosGuardados();
        }
    }
    
    public void mostrarRegistrosGuardados() {

        DefaultTableModel tabla = new DefaultTableModel();
        ArrayList<ObjetoUsuario> listaUsuarios = new ArrayList();

        int contadorFilas = 1;
        int fila = 0;

        try {
            listaUsuarios = nuevoUsuario.retornaUsuario();
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
        }

        if (listaUsuarios.isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "No existen datos de usuarios almacenados");
        } else {
            tabla.addColumn("Usuario");
            tabla.addColumn("Contraseña");
            tabla.addColumn("Tipo");
            tabla.addColumn("Nombre");

            for (ObjetoUsuario xUsuario : listaUsuarios) {
                tabla.setRowCount(contadorFilas);

                tabla.setValueAt(xUsuario.getNombre_usuario(), fila, 0);
                tabla.setValueAt(xUsuario.getContrasenia_usuario(), fila, 1);
                tabla.setValueAt(xUsuario.getTipo_usuario(), fila, 2);
                tabla.setValueAt(xUsuario.getNombre_empleado(), fila, 3);

                System.out.println("Se encontro el registro " + xUsuario.getNombre_usuario());
                fila++;
                contadorFilas++;
            }

            tblUsuario.setModel(tabla);
        }
    }

    private int obtenerIdUsuario(String nombre) {
        int id_usuario = 0;
        ArrayList<ObjetoUsuario> BuscarUsuario = new ArrayList();

        try {
            BuscarUsuario = nuevoUsuario.seleccionarUsuario(nombre);
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el id del usuario " + error);
        }

        if (BuscarUsuario.isEmpty()) {
            Mensaje.manipulacionExcepciones("informacion", "Debe crear el usuario en el sistema");
        } else {
            if (BuscarUsuario.size() > 1) {
                System.out.println("EXISTEN " + BuscarUsuario.size() + " USUARIOS CON ESTE NOMBRE");
                Mensaje.manipulacionExcepciones("critico", "Este ID esta repetido");
            } else {
                id_usuario = BuscarUsuario.get(0).getId_usuario();
            }
        }

        return id_usuario;

    }

    private void actualizarRegistro() {

        usuario.setId_usuario(Integer.parseInt(txtIdUsuario.getText()));
        usuario.setId_empleado(Integer.parseInt(txtIdEmpleado.getText()));
        usuario.setNombre_usuario(txtNombreUsuario.getText());
        usuario.setContrasenia_usuario(txtContraseniaUsuario.getText());
        usuario.setTipo_usuario(cbxTipoUsuario.getSelectedItem().toString());
        usuario.setNombre_empleado(cbxNombreEmpleado.getSelectedItem().toString());

        try {
            nuevoUsuario.actualizarUsuario(usuario);
        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Error al actualizar el usuario " + error);
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

        pnlEncabezado = new javax.swing.JPanel();
        lblIdUsuario = new javax.swing.JLabel();
        txtIdUsuario = new javax.swing.JTextField();
        lblEmpleado = new javax.swing.JLabel();
        lblNombreUsuario = new javax.swing.JLabel();
        txtNombreUsuario = new javax.swing.JTextField();
        lblContraseniaUsuario = new javax.swing.JLabel();
        txtContraseniaUsuario = new javax.swing.JTextField();
        lblTipoUsuario = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        cbxNombreEmpleado = new javax.swing.JComboBox();
        cbxTipoUsuario = new javax.swing.JComboBox();
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
        tblUsuario = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuarios");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/user.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdUsuario.setText("ID Usuario");

        txtIdUsuario.setEditable(false);
        txtIdUsuario.setToolTipText("");

        lblEmpleado.setText("Empleado");

        lblNombreUsuario.setText("Nombre");
        lblNombreUsuario.setToolTipText("");

        txtNombreUsuario.setToolTipText("");

        lblContraseniaUsuario.setText("Contraseña");

        txtContraseniaUsuario.setToolTipText("");

        lblTipoUsuario.setText("Tipo Usuario");

        txtIdEmpleado.setEditable(false);

        cbxNombreEmpleado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbxNombreEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxNombreEmpleadoActionPerformed(evt);
            }
        });

        cbxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Administrador", "Cajero", "Chef" }));

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdUsuario)
                            .addComponent(lblNombreUsuario))
                        .addGap(32, 32, 32)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtNombreUsuario, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtIdUsuario, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblContraseniaUsuario)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblEmpleado)
                                .addGap(30, 30, 30)
                                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                        .addComponent(cbxNombreEmpleado, 0, 252, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtContraseniaUsuario)))))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblTipoUsuario)
                        .addGap(23, 23, 23)
                        .addComponent(cbxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 416, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdUsuario)
                    .addComponent(txtIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmpleado)
                    .addComponent(cbxNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombreUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreUsuario)
                    .addComponent(lblContraseniaUsuario)
                    .addComponent(txtContraseniaUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTipoUsuario)
                    .addComponent(cbxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtContraseniaUsuario.getAccessibleContext().setAccessibleName("txtPrecioCompra");

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

        tblUsuario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblUsuario.getTableHeader().setReorderingAllowed(false);
        tblUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblUsuarioMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblUsuario);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
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
                .addGap(18, 18, 18)
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

    private void cbxNombreEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxNombreEmpleadoActionPerformed
        AccesoEmpleado acceso = new AccesoEmpleado();
        ArrayList<ObjetoEmpleado> lista = new ArrayList();
        lista = acceso.seleccionarEmpleado(cbxNombreEmpleado.getSelectedItem().toString());
        for(ObjetoEmpleado n : lista){
            txtIdEmpleado.setText(String.valueOf(n.getId_empleado()));
        }
    }//GEN-LAST:event_cbxNombreEmpleadoActionPerformed

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

    private void tblUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblUsuarioMouseClicked
        btnGuardar.setEnabled(false);
        txtNombreUsuario.setText(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 0)));
        txtContraseniaUsuario.setText(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 1)));
        cbxTipoUsuario.setSelectedItem(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 2)));
        cbxNombreEmpleado.setSelectedItem(String.valueOf(tblUsuario.getValueAt(tblUsuario.getSelectedRow(), 3)));

        // Ya el ussuario busco so id para poder actualizarlo
        txtIdUsuario.setText(String.valueOf(obtenerIdUsuario(txtNombreUsuario.getText())));
    }//GEN-LAST:event_tblUsuarioMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtNombreUsuario.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe seleccionar el registro a modificar");
        }else{
            actualizarRegistro();
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxNombreEmpleado;
    private javax.swing.JComboBox cbxTipoUsuario;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblContraseniaUsuario;
    private javax.swing.JLabel lblEmpleado;
    private javax.swing.JLabel lblIdUsuario;
    private javax.swing.JLabel lblNombreUsuario;
    private javax.swing.JLabel lblTipoUsuario;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblUsuario;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtContraseniaUsuario;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtIdUsuario;
    private javax.swing.JTextField txtNombreUsuario;
    // End of variables declaration//GEN-END:variables
}
