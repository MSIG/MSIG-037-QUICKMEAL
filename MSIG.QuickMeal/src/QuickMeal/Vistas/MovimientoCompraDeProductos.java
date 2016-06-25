/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.*;
import QuickMeal.Objetos.ObjetoCompra;
import QuickMeal.Objetos.ObjetoDetalleCompra;
import QuickMeal.Objetos.ObjetoProducto;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
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
public class MovimientoCompraDeProductos extends javax.swing.JInternalFrame {

    /**
     * Creates new form MovimientoCompraDeProductos
     */
    
    AccesoCompra lProveedores = new AccesoCompra();
    AccesoCompra NuevaCompra = new AccesoCompra();
    AccesoProducto NuevoProducto = new AccesoProducto();
    AccesoDetalleCompra NuevoDetalleCompra = new AccesoDetalleCompra();
    
    ObjetoCompra Compra = new ObjetoCompra();
    ObjetoDetalleCompra Detalle = new ObjetoDetalleCompra();
    
    Mensaje Mensaje = new Mensaje();
    String seleccion = "";
    ObjetoCompra compra = new ObjetoCompra();
    AccesoCompra nuevaCompra = new AccesoCompra();
    
    Date fecha = new Date();
    String producto_actual;
    float total_compra = 0;
    float cantidad = 0;
    float precio = 0;
    int fila_seleccionada = 0;
    int columna_seleccionada = 0;
    
    public MovimientoCompraDeProductos() {
        initComponents();
        nuevoDetalle();
        cargaProveedores();
    }
    
    private void nuevoDetalle() {
        tblDetalleCompra.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblDetalleCompra.getColumnModel().getColumn(1).setPreferredWidth(300);
        tblDetalleCompra.getColumnModel().getColumn(2).setPreferredWidth(100);
        tblDetalleCompra.getColumnModel().getColumn(3).setPreferredWidth(100);
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
        
        cbxEstadoCompra.setVisible(true);
        lblEstado.setVisible(true);
        
    }
    
    private boolean verificarDatosCompra(){
        boolean vDatosCorrectos = true;
        
        if(cbxDescripcionProveedor.getSelectedItem().toString().equals("Seleccionar")){
            Mensaje.manipulacionExcepciones("critico", "Seleccione el proveedor");
            vDatosCorrectos = false;
        }
       
        if (txtFechaCompra.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese la fecha de la compra");
            vDatosCorrectos = false;
        }
        if (txtFacturaCompra.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese el numero de factura");
            vDatosCorrectos = false;
        }
        
        if(cbxDescripcionProveedor.getSelectedItem().equals("Seleccionar")){
            Mensaje.manipulacionExcepciones("critico", "Seleccione un proveedor");
            vDatosCorrectos = false;
        }
        
        if(txtIdProveedor.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("critico", "Ingrese el numero ID del proveedor");
            vDatosCorrectos = false;
        }
        
        return vDatosCorrectos;
    }
    
    
    private void guardarRegistros(){
        
        boolean error_grabar = false;

        /**
         * *******************************************************************
         * GUARDAR LOS DATOS DE LA COMPRA SOLAMENTE SI ESTA, AUN NO TIENE UN
         * NUMERO DE ID ASIGNADO. DE LO CONTRARIO DEBERA CAMBIAR EL NUMERO DE ID
         * PRECIONANDO DEL BOTON DE GUARDAR NUEVAMENTE LA COMPRA, PERO ESTO
         * LIMPIARA EL NUMERO DE FACTURA LA SERIE Y EL FOLIO DEL ENCABEZADO PARA
         * QUE EL USUARIO LO INGRESE NUEVAMENTE Y ASI PUEDA GUARDARLO
        *********************************************************************
         */
        if (txtIdCompra.getText().equals("")) {
            try {
                Compra.setId_proveedor(Integer.parseInt(txtIdProveedor.getText()));
                Compra.setFecha_compra(txtFechaCompra.getText());
                Compra.setFactura_compra(txtFacturaCompra.getText());
                Compra.setEstado_compra(cbxEstadoCompra.getSelectedItem().toString());
                Compra.setDias_credito_compra(Integer.parseInt(txtDiasCredito.getText()));
            } catch (Error error) {
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al leer los datos de Factura -> " + error);
            }

            if (verificarDatosCompra()) {
                NuevaCompra.insertarCompra(Compra);
            } else {
                Mensaje.manipulacionExcepciones("critico", "Por favor verifique los datos de la compra");
            }

            //OBTENER EL ID DE LA COMPRA INGRESADA PARA EL DETALLE DE LA COMPRA
            try {
                ArrayList<ObjetoCompra> BuscarCompra = new ArrayList();
                BuscarCompra = NuevaCompra.seleccionarIdCompra(Compra);
                System.out.println("ID COMPRA " + BuscarCompra.get(0).getId_compra());
                
                if (BuscarCompra.isEmpty()) {
                    Mensaje.manipulacionExcepciones("critico", "No existe este numero de compra en la Base de Datos");
                } else {
                    if (BuscarCompra.size() > 1) {
                        Mensaje.manipulacionExcepciones("critico", "Existen mas Facturas de Compra con los mismos Datos");
                    } else {
                        txtIdCompra.setText(String.valueOf(BuscarCompra.get(0).getId_compra()));
                    }
                }
            } catch (Exception error) {
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el ID de la Compra -> " + error);
            }

            //ALMACENAR EL DETALLE DE LA COMPRA Y ACTUALIZAR EL CATALOGO DE PRODUCTOS
            try {
                for (int cFilas = 0; cFilas < tblDetalleCompra.getRowCount(); cFilas++) {
                    //INSERTAR EN D_COMPRA LOS PRODUCTOS COMPRADOS
                    if (tblDetalleCompra.getValueAt(cFilas, 0) != null) {
                        try {
                            Detalle.setId_compra(Integer.parseInt(txtIdCompra.getText()));
                            Detalle.setId_producto(tblDetalleCompra.getValueAt(cFilas, 0).toString());
                            Detalle.setCantidad_compra(Float.parseFloat(tblDetalleCompra.getValueAt(cFilas, 2).toString()));
                            Detalle.setPrecio_compra(Float.parseFloat(tblDetalleCompra.getValueAt(cFilas, 3).toString()));
                            NuevoDetalleCompra.insertarDetalleCompra(Detalle);
                        } catch (Exception error) {
                            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error en la Fila " + cFilas + " -> " + error);
                            error_grabar = true;
                        }
                    }
                }

                if (error_grabar == false) {
                    Mensaje.manipulacionExcepciones("informacion", "Registros guardados exitosamente para la Compra No. " + txtIdCompra.getText());
                }

            } catch (Exception error) {
                Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al almacenar le detalle -> " + error);
            }
        } else {
            Mensaje.manipulacionExcepciones("critico", "Esta facutra ya fue guardada con aterioridad");
        }
       
    }
    
    private ObjetoProducto obtenerDatosProducto(String pId_producto){
        
        ArrayList<ObjetoProducto> BuscarProducto = new ArrayList();
        ObjetoProducto Producto = new ObjetoProducto();
        
        //CONSULTAR EL ID DE PRODUCTO PARA OBTENER LOS DATOS
        try{
            BuscarProducto = NuevoProducto.buscarDescipcionProducto(pId_producto);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al consultar el codigo del producto");
        }
        
        //EVALUAR EL RESULTADO DE LA BUSQUEDA
        if(BuscarProducto.isEmpty()){
            System.out.println("No existe este producto");
            Mensaje.manipulacionExcepciones("informacion", "Este Codigo de Producto no existe en su Base de Datos");
        } else {
            if(BuscarProducto.size()>1){
                System.out.println("EXISTEN "+BuscarProducto.size()+"PRODUCTOS CON ESTE ID");
                Mensaje.manipulacionExcepciones("critico", "Existe mas de un producto con este codigo");
            } else {
                Producto.setId_producto(BuscarProducto.get(0).getId_producto());
                Producto.setDescripcion_producto(BuscarProducto.get(0).getDescripcion_producto());
                Producto.setId_proveedor(BuscarProducto.get(0).getId_proveedor());
                Producto.setDescripcion_proveedor(BuscarProducto.get(0).getDescripcion_proveedor());
                Producto.setCosto_producto(BuscarProducto.get(0).getCosto_producto());
                Producto.setUnidad_medida_producto(BuscarProducto.get(0).getUnidad_medida_producto());
                Producto.setCaducidad_producto(BuscarProducto.get(0).getCaducidad_producto());
                Producto.setCantidad_producto(BuscarProducto.get(0).getCaducidad_producto());
                Producto.setMinimo_producto(BuscarProducto.get(0).getMinimo_producto());
            }
        }
        
        return Producto;
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
        lblIdCompra = new javax.swing.JLabel();
        txtIdCompra = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        txtFechaCompra = new javax.swing.JTextField();
        lblNumFacturaCompra = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txtIdProveedor = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFacturaCompra = new javax.swing.JTextField();
        cbxDescripcionProveedor = new javax.swing.JComboBox();
        lblEstado = new javax.swing.JLabel();
        cbxEstadoCompra = new javax.swing.JComboBox();
        lblEstado1 = new javax.swing.JLabel();
        txtDiasCredito = new javax.swing.JTextField();
        pnlBotones = new javax.swing.JPanel();
        btnSalir = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalleCompra = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        btnImprimir = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Compra de Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/compras.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(750, 600));
        setMinimumSize(new java.awt.Dimension(750, 600));
        setPreferredSize(new java.awt.Dimension(750, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdCompra.setText("ID Compra");

        txtIdCompra.setEditable(false);
        txtIdCompra.setToolTipText("El numero de compra se genera automaticamente");

        lblFecha.setText("Fecha");

        txtFechaCompra.setToolTipText("Ingresar la fecha de compra del producto");

        lblNumFacturaCompra.setToolTipText("Ingrese el numero de factura (no es requerido)");

        jLabel1.setText("Proveedor");

        txtIdProveedor.setEditable(false);

        jLabel2.setText("No. Factura");

        cbxDescripcionProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbxDescripcionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDescripcionProveedorActionPerformed(evt);
            }
        });

        lblEstado.setText("Estado");

        cbxEstadoCompra.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pagada", "Credito" }));
        cbxEstadoCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxEstadoCompraActionPerformed(evt);
            }
        });

        lblEstado1.setText("Dias Credito");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFecha)
                    .addComponent(lblIdCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtFechaCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(txtIdCompra))
                .addGap(18, 18, 18)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(cbxDescripcionProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtFacturaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxEstadoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblEstado1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtDiasCredito, javax.swing.GroupLayout.DEFAULT_SIZE, 103, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblNumFacturaCompra)
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdCompra)
                    .addComponent(txtIdCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumFacturaCompra)
                    .addComponent(jLabel1)
                    .addComponent(cbxDescripcionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFecha)
                    .addComponent(txtFechaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtFacturaCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado)
                    .addComponent(cbxEstadoCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEstado1)
                    .addComponent(txtDiasCredito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/guardar.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/eliminar.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSalir)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEliminar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jScrollPane1.setMinimumSize(new java.awt.Dimension(750, 600));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(750, 600));

        tblDetalleCompra.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Precio", "Sub Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalleCompra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleCompraMouseClicked(evt);
            }
        });
        tblDetalleCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tblDetalleCompraKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tblDetalleCompra);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 315, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/buscar.png"))); // NOI18N
        btnBuscar.setActionCommand("buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        btnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/impresora.png"))); // NOI18N
        btnImprimir.setActionCommand("btnReporte");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnImprimir)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnImprimir)
                    .addComponent(btnBuscar))
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
                    .addComponent(pnlBotones, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlEncabezado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlEncabezado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarRegistros();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void cbxDescripcionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDescripcionProveedorActionPerformed
        seleccion = "";
        seleccion = cbxDescripcionProveedor.getSelectedItem().toString();
        
        ArrayList<ObjetoCompra> listaProveedores = new ArrayList();
            
        try{
            listaProveedores = lProveedores.retornaProveedores();
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error al obtener codigo de proveedor seleccionado " + error);
        }
        
        // Recorro la lista de proveedores para obtener el id del proveedor seleccionado
        for(ObjetoCompra xProducto : listaProveedores){
            if(xProducto.getDescripcion_proveedor().equals(seleccion)){
                txtIdProveedor.setText(String.valueOf(xProducto.getId_proveedor()));
                txtDiasCredito.setText(String.valueOf(xProducto.getDias_credito_compra()));
            }
        }
    }//GEN-LAST:event_cbxDescripcionProveedorActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        BuscarCodigoProducto buscar = new BuscarCodigoProducto();
        QuickMeal.pnlPrincipal.add(buscar);
        buscar.setVisible(true);
        buscar.toFront();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void tblDetalleCompraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tblDetalleCompraKeyPressed
        ObjetoProducto Producto = new ObjetoProducto();

        //VERIFICAR SI SURGE ALGUN EVENTO SOBRE CUALQUIER CELDA DE LA TABLA
        if (evt.getKeyCode() == 9) {

            fila_seleccionada = tblDetalleCompra.getSelectedRow();
            columna_seleccionada = tblDetalleCompra.getSelectedColumn();

            //VERIFICAR LA OPERACION A REALIZAR SEGUN SU POSICION
            System.out.println("POSICIONADO EN LA FILA: " + fila_seleccionada + " COLUMNA: " + columna_seleccionada);

            //BUSCAR LA INFORMACION DEL PRODUCTO SELECCIONADO
            if (columna_seleccionada == 1) {
                
                Producto = obtenerDatosProducto(tblDetalleCompra.getValueAt(fila_seleccionada, columna_seleccionada-1).toString());
                System.out.println("PRODUCTO " + Producto.getId_producto() + " ENCONTRADO: " + Producto.getDescripcion_producto()+ " EXISTENCIAS: " + Producto.getCantidad_producto());
                tblDetalleCompra.setValueAt(Producto.getDescripcion_producto().toString()+" ("+Producto.getUnidad_medida_producto().toString()+")", fila_seleccionada, columna_seleccionada);
            }
            
            if(columna_seleccionada == 4){
                precio = (float) tblDetalleCompra.getValueAt(fila_seleccionada, columna_seleccionada - 1);
                cantidad = (float) tblDetalleCompra.getValueAt(fila_seleccionada, columna_seleccionada - 2);
                tblDetalleCompra.setValueAt(cantidad * precio, fila_seleccionada, columna_seleccionada);
            }

        }
    }//GEN-LAST:event_tblDetalleCompraKeyPressed

    private void tblDetalleCompraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleCompraMouseClicked
        //CAPTURAR LA FILA SELECCIONADA PAR LA ELIMINACION
        fila_seleccionada = tblDetalleCompra.getSelectedRow();
        columna_seleccionada = tblDetalleCompra.getSelectedColumn();
        
    }//GEN-LAST:event_tblDetalleCompraMouseClicked

    private void cbxEstadoCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxEstadoCompraActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxEstadoCompraActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        try{
            DefaultTableModel tabla = new DefaultTableModel();
            tabla.addColumn("Codigo");
            tabla.addColumn("Descripcion");
            tabla.addColumn("Cantidad");
            tabla.addColumn("Precio");
            tabla.addColumn("Subtotal");
            
            DefaultTableModel modelo = (DefaultTableModel) tblDetalleCompra.getModel();
            Object nueva_fila[] = {null,null,null,null,null,null};
            modelo.addRow(nueva_fila);
            modelo.removeRow(fila_seleccionada);
  

        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Se ha eliminado una fila");
        }
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        Mensaje.manipulacionExcepciones("interrogante", "¿Esta seguro que desea salir de la compra actual y abrir una nueva?");
        if (Mensaje.valor == 0) {
            this.dispose();
            
            MovimientoCompraDeProductos Compras = new MovimientoCompraDeProductos();
            Compras.setVisible(true);
            QuickMeal.pnlPrincipal.add(Compras);
            Compras.toFront();
        }
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        try{
            Conexion acceso = new Conexion();
            URL url_reporte = this.getClass().getResource("/QuickMeal/Reportes/DetalleCompra.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            parametro.put("P_ID_COMPRA", Integer.parseInt(txtIdCompra.getText()));
            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, acceso.conectar());
            JasperViewer visualizador = new JasperViewer(pantalla, false);
            visualizador.show();
        }catch(Exception error){
            
        }
    }//GEN-LAST:event_btnImprimirActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnSalir;
    private javax.swing.JComboBox cbxDescripcionProveedor;
    private javax.swing.JComboBox cbxEstadoCompra;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblEstado1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblIdCompra;
    private javax.swing.JLabel lblNumFacturaCompra;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    public static javax.swing.JTable tblDetalleCompra;
    private javax.swing.JTextField txtDiasCredito;
    private javax.swing.JTextField txtFacturaCompra;
    private javax.swing.JTextField txtFechaCompra;
    private javax.swing.JTextField txtIdCompra;
    private javax.swing.JTextField txtIdProveedor;
    // End of variables declaration//GEN-END:variables
}