/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.AccesoEmpleado;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoEmpleado;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class CatalogoAdmonEmpleados extends javax.swing.JInternalFrame {

    Mensaje Mensaje = new Mensaje();
    ObjetoEmpleado empleado = new ObjetoEmpleado();
    AccesoEmpleado nuevoEmpleado = new AccesoEmpleado();
    
    /**
     * Creates new form wdwIngresoClientes
     */
    public CatalogoAdmonEmpleados() {
        initComponents();
    }
    
    private String estadoEmpleado(String pEstado){
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
    
    private boolean verificarDatosEmpleado(){
        boolean vDatosCorrectos = true;
        
        if(txtNombreEmpleado.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("critico", "Ingrese el numero de NIT");
            vDatosCorrectos = false;
        }
        if(txtBonoEmpleado.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("critico", "Ingrese el numero de NIT");
            vDatosCorrectos = false;
        }
        if (txtFechaIngresoEmpleado.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese la descripcion del proveedor");
            vDatosCorrectos = false;
        }
        
        return vDatosCorrectos;
    }
    
    private void guardarRegistro(){
        
        // Si se ingresan todos los datos del proveedor asigno valor a los objetos
        if(verificarDatosEmpleado()){
            String estado = estadoEmpleado(cbxEstadoEmpleado.getSelectedItem().toString());
            
            System.out.println(txtFechaSalidaEmpleado.getText());
            
            if(txtFechaSalidaEmpleado.getText().equals("")){
                empleado.setFecha_salida(null);
            }else{
                String pipe = "'";
                empleado.setFecha_salida(pipe+txtFechaSalidaEmpleado.getText()+pipe);
            }
            
            empleado.setTipo_empleado(cbxTipoEmpleado.getSelectedItem().toString());
            empleado.setNombre_empleado(txtNombreEmpleado.getText());
            empleado.setBono_empleado(Double.parseDouble(txtBonoEmpleado.getText()));
            empleado.setEmpresa_empleado(cbxEmpresa.getSelectedItem().toString());
            empleado.setFecha_ingreso(txtFechaIngresoEmpleado.getText());
            empleado.setEstado_empleado(estado);
        }
        
        try{
            nuevoEmpleado.insertarEmpleado(empleado);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar los datos del empleado " + error);
        }
        
    }
    
    private void limpiarCampos(){
        btnGuardar.setEnabled(true);
        txtIdEmpleado.setText("");
        txtNombreEmpleado.setText("");
        txtFechaIngresoEmpleado.setText("");
        txtFechaSalidaEmpleado.setText("");
        txtBonoEmpleado.setText("");
        cbxTipoEmpleado.setSelectedItem("Gerente");
        cbxEstadoEmpleado.setSelectedItem("Activo");
        cbxEmpresa.setSelectedItem("Pantaleon S. A.");
        txtBuscar.setText("");
    }
    
    private void buscarEnRegistrosGuardados(){
        String busqueda = txtBuscar.getText();
        
        if(busqueda != null){
            
            DefaultTableModel tabla = new DefaultTableModel();
            
            ArrayList<ObjetoEmpleado> listaEmpleados = new ArrayList();
            
            int contadorFilas = 1;
            int cProveedor = 0;
            
            // Verificar algun erro al cargar la informacion de los proveedores
            try{
                listaEmpleados = nuevoEmpleado.seleccionarEmpleado(busqueda);
                
                System.out.println("SE CONSULTARON LOS DATOS DE LOS EMPLEADOS");
                
            }catch(Error error){
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar la informacion " + error);
            }
            
            // Mostrar la informacion de los proveedores en la tabla de la vista
            if(listaEmpleados.isEmpty()){
                Mensaje.manipulacionExcepciones("critico", "No existe datos almacenados de los empleados");
            }else{
                tabla.addColumn("Nombre");
                tabla.addColumn("Estado");
                tabla.addColumn("Tipo");
                tabla.addColumn("Bono");
                tabla.addColumn("Empresa");
                tabla.addColumn("Fecha Ingreso");
                tabla.addColumn("Fecha Salida");
                
                for(ObjetoEmpleado xEmpleado : listaEmpleados){
                    String estado = estadoEmpleado(xEmpleado.getEstado_empleado());
                    
                    tabla.setRowCount(contadorFilas);
                    
                    tabla.setValueAt(xEmpleado.getNombre_empleado(), cProveedor, 0);
                    tabla.setValueAt(estado, cProveedor, 1);
                    tabla.setValueAt(xEmpleado.getTipo_empleado(), cProveedor, 2);
                    tabla.setValueAt(xEmpleado.getBono_empleado(), cProveedor, 3);
                    tabla.setValueAt(xEmpleado.getEmpresa_empleado(), cProveedor, 4);
                    tabla.setValueAt(xEmpleado.getFecha_ingreso(), cProveedor, 5);
                    tabla.setValueAt(xEmpleado.getFecha_salida(), cProveedor, 6);
                    
                    System.out.println("Se encontro el registro " + xEmpleado.getNombre_empleado());
                    cProveedor++;
                    contadorFilas++;
                    
                }
                tblEmpleados.setModel(tabla);
            }
            
        }else{
            mostrarRegistrosGuardados();
        }   
    }
    
    public void mostrarRegistrosGuardados(){
        DefaultTableModel tabla = new DefaultTableModel();
        
         ArrayList<ObjetoEmpleado> listaEmpleados = new ArrayList();
         
         try{
             listaEmpleados = nuevoEmpleado.retornaEmpleado();
             System.out.println("Tamanio " + listaEmpleados.size());
             
         }catch(Exception error){
             Mensaje.manipulacionExcepciones("critico", "Error en busqueda " + error);
         }
         
         if(listaEmpleados.isEmpty()){
             Mensaje.manipulacionExcepciones("critico", "No existen datos almacenados de clientes");
         }else{
                tabla.addColumn("Nombre");
                tabla.addColumn("Estado");
                tabla.addColumn("Tipo");
                tabla.addColumn("Bonificacion");
                tabla.addColumn("Empresa");
                tabla.addColumn("Fecha de Ingreso");
                tabla.addColumn("Fecha de Salida");
                
                tabla.setRowCount(listaEmpleados.size());
                int cProveedor = 0;
                
                for(ObjetoEmpleado xEmpleado : listaEmpleados){
                    String estado = estadoEmpleado(xEmpleado.getEstado_empleado());
                    
                    tabla.setValueAt(xEmpleado.getNombre_empleado(), cProveedor, 0);
                    tabla.setValueAt(estado, cProveedor, 1);
                    tabla.setValueAt(xEmpleado.getTipo_empleado(), cProveedor, 2);
                    tabla.setValueAt(xEmpleado.getBono_empleado(), cProveedor, 3);
                    tabla.setValueAt(xEmpleado.getEmpresa_empleado(), cProveedor, 4);
                    tabla.setValueAt(xEmpleado.getFecha_ingreso(), cProveedor, 5);
                    tabla.setValueAt(xEmpleado.getFecha_salida(), cProveedor, 6);
                    
                    System.out.println("Se encontro el registro " + xEmpleado.getNombre_empleado());
                    cProveedor++;
                }
         }
         tblEmpleados.setModel(tabla);
    }
    
    private int obtenerIdEmpleado(String nombre){
        
        int vId_empleado = 0;
        ArrayList<ObjetoEmpleado> BuscarEmpleado = new ArrayList();
        
        try{
            BuscarEmpleado = nuevoEmpleado.buscarEmpleado(nombre);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el empleado " + error);
        }
        
        if(BuscarEmpleado.isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe crear el empleado en el sistema");
        }else{
            if(BuscarEmpleado.size() > 1){
                System.out.println("Existe en: " + BuscarEmpleado.size() + " con estos datos ");
                Mensaje.manipulacionExcepciones("critico", "Este empleado esta repetido");
            }else{
                vId_empleado = BuscarEmpleado.get(0).getId_empleado();
            }
        }
        
        return vId_empleado;
    }
    
    private void actualizarRegistro(){
        String estado = estadoEmpleado(cbxEstadoEmpleado.getSelectedItem().toString());
                
        empleado.setId_empleado(Integer.parseInt(txtIdEmpleado.getText()));
        empleado.setTipo_empleado(cbxTipoEmpleado.getSelectedItem().toString());
        empleado.setNombre_empleado(txtNombreEmpleado.getText());
        empleado.setEstado_empleado(estado);
        empleado.setBono_empleado(Double.parseDouble(txtBonoEmpleado.getText()));
        empleado.setEmpresa_empleado(cbxEmpresa.getSelectedItem().toString());
        empleado.setFecha_ingreso(txtFechaIngresoEmpleado.getText());
        empleado.setFecha_salida(txtFechaSalidaEmpleado.getText());
        
        try{
            nuevoEmpleado.actualizarEmpleado(empleado);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error al actualizar el empleado " + error);
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
        lblIdCliente = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JTextField();
        lblNombreCliente = new javax.swing.JLabel();
        lblTelCliente = new javax.swing.JLabel();
        lblDirCliente = new javax.swing.JLabel();
        txtBonoEmpleado = new javax.swing.JTextField();
        txtNombreEmpleado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtFechaSalidaEmpleado = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtFechaIngresoEmpleado = new javax.swing.JTextField();
        cbxEstadoEmpleado = new javax.swing.JComboBox();
        cbxEmpresa = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        cbxTipoEmpleado = new javax.swing.JComboBox();
        pnlBotones = new javax.swing.JPanel();
        lblBuscar = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnSalir = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnBuscar = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Empleados");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/empleado.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdCliente.setText("ID Empleado");

        txtIdEmpleado.setEditable(false);
        txtIdEmpleado.setToolTipText("");

        lblNombreCliente.setText("Nombre");

        lblTelCliente.setText("Estado");

        lblDirCliente.setText("Bono");

        txtBonoEmpleado.setToolTipText("");

        txtNombreEmpleado.setToolTipText("");

        jLabel1.setText("Tipo");

        jLabel2.setText("Salida");

        jLabel3.setText("Ingreso");

        cbxEstadoEmpleado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Activo", "Vacaciones", "Inactivo" }));

        cbxEmpresa.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pantaleon S. A.", "Concepcion", "Visitante", "Otra" }));

        jLabel5.setText("Empresa");

        cbxTipoEmpleado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Gerente", "Administrador", "Jefe de Cocina", "Shef", "Cocinero", "Limpieza", "Mesero", "Cajero", "Recepcionista" }));

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTelCliente)
                    .addComponent(lblIdCliente)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIdEmpleado)
                    .addComponent(txtFechaIngresoEmpleado)
                    .addComponent(cbxEstadoEmpleado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(lblDirCliente))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxTipoEmpleado, 0, 137, Short.MAX_VALUE)
                    .addComponent(txtBonoEmpleado)
                    .addComponent(txtFechaSalidaEmpleado))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNombreCliente)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNombreEmpleado)
                    .addComponent(cbxEmpresa, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdCliente)
                    .addComponent(txtIdEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreCliente)
                    .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(cbxTipoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDirCliente)
                    .addComponent(txtBonoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTelCliente)
                    .addComponent(cbxEstadoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(cbxEmpresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtFechaSalidaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(txtFechaIngresoEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblBuscar.setText("Buscar");

        txtBuscar.setToolTipText("");

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/limpiar.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.setToolTipText("");
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
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnBuscar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblBuscar)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnSalir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblEmpleados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblEmpleadosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblEmpleados);

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
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

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        Mensaje.manipulacionExcepciones("interrogante", "¿Esta seguro que desea salir?");
        if (Mensaje.valor == 0) {
            dispose();
        }
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblEmpleadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblEmpleadosMouseClicked
        btnGuardar.setEnabled(false);

        String estado = estadoEmpleado(String.valueOf(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(), 1)));
        estado = estadoEmpleado(estado);

        // ASIGNAR AL ENCABEZADO LOS DATOS
        txtNombreEmpleado.setText(String.valueOf(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(), 0)));
        cbxEstadoEmpleado.setSelectedItem(estado);
        cbxTipoEmpleado.setSelectedItem(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(), 2));
        txtBonoEmpleado.setText(String.valueOf(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(), 3)));
        cbxEmpresa.setSelectedItem(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(),4)); //.setText(String.valueOf(tblEmpleados.getValueAt(fila_seleccionada, columna_seleccionada + 4)));
        txtFechaIngresoEmpleado.setText(String.valueOf(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(),5)));
        txtFechaSalidaEmpleado.setText(String.valueOf(tblEmpleados.getValueAt(tblEmpleados.getSelectedRow(), 6)));

        //BUSCARA EL ID DEL EMPLEADO SELECCIONADO
        txtIdEmpleado.setText(String.valueOf(obtenerIdEmpleado(txtNombreEmpleado.getText())));

    }//GEN-LAST:event_tblEmpleadosMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtIdEmpleado.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("informacion", "Debe seleccionar el registro a modificar");
        }else{
            actualizarRegistro();
            mostrarRegistrosGuardados();
        }
    }//GEN-LAST:event_btnModificarActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxEmpresa;
    private javax.swing.JComboBox cbxEstadoEmpleado;
    private javax.swing.JComboBox cbxTipoEmpleado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDirCliente;
    private javax.swing.JLabel lblIdCliente;
    private javax.swing.JLabel lblNombreCliente;
    private javax.swing.JLabel lblTelCliente;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtBonoEmpleado;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtFechaIngresoEmpleado;
    private javax.swing.JTextField txtFechaSalidaEmpleado;
    private javax.swing.JTextField txtIdEmpleado;
    private javax.swing.JTextField txtNombreEmpleado;
    // End of variables declaration//GEN-END:variables
}
