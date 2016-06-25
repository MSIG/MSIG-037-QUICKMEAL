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
public class CatalogoAdmonProductos extends javax.swing.JInternalFrame {
    
    /**
     * Creates new form CatalogoAdmonProductos
     */
    Mensaje Mensaje = new Mensaje();
    AccesoProducto nuevoProveedor = new AccesoProducto();
    AccesoProducto nuevoProducto = new AccesoProducto();
    ObjetoProducto producto = new ObjetoProducto();
    String seleccion = "";

//AccesoProveedor nuevoProveedor = new AccesoProveedor();
    
    
    public CatalogoAdmonProductos() {
        initComponents();
        cargaProveedores();
    }
    
    private void cargaProveedores(){
        ArrayList<ObjetoProducto> lProveedores = new ArrayList();
            
        try{
            lProveedores = nuevoProveedor.retornaProveedores();
            
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error en busqueda de proveedores " + error);
        }
        
        // Lleno el combobox con los registros que existen para proveedores
        for(ObjetoProducto xProducto : lProveedores){
            cbxDescripcionProveedor.addItem(xProducto.getDescripcion_proveedor());
        }
        
    }
    
    private void guardarRegistro(){
        // Si se ingresan todos los datos del producto asigno valor al objeto
        if(verificarDatosProducto()){
            producto.setDescripcion_producto(txtDescripcionProducto.getText());
            producto.setId_proveedor(Integer.parseInt(txtIdProveedor.getText()));
            producto.setCosto_producto(Double.parseDouble(txtCostoProducto.getText()));
            producto.setUnidad_medida_producto(cbxUnidadMedidaProducto.getSelectedItem().toString());
            producto.setCantidad_producto(Double.parseDouble(txtMinimoProducto.getText()));
            producto.setCaducidad_producto(Integer.parseInt(txtCaducidadProducto.getText()));
            
            //validar se el campo de texto de la fecha vienen nulo
            if(txtFechaCaducidadProducto.getText().isEmpty()){
                producto.setFecha_caduciad_producto("null");
            } else {
                producto.setFecha_caduciad_producto(txtFechaCaducidadProducto.getText());
        
            }
        }
        
        try{
            nuevoProducto.insertarProducto(producto);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar los datos del producto " + error);
        }
    }
        
    private boolean verificarDatosProducto(){
        boolean vDatosCorrectos = true;
        
        if(txtDescripcionProducto.getText().isEmpty()){
            Mensaje.manipulacionExcepciones("critico", "Ingrese una descripcion para el producto");
            vDatosCorrectos = false;
        }
        if (txtCostoProducto.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese el costo del producto");
            vDatosCorrectos = false;
        }
        if (txtMinimoProducto.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese el minimo a configurar para el producto");
            vDatosCorrectos = false;
        }
        if (txtCaducidadProducto.getText().isEmpty()) {
            Mensaje.manipulacionExcepciones("critico", "Ingrese la caducidad del producto");
            vDatosCorrectos = false;
        }
        
        if(cbxDescripcionProveedor.getSelectedItem().equals("Seleccionar")){
            Mensaje.manipulacionExcepciones("critico", "Seleccione el proveedor");
            vDatosCorrectos = false;
        }
        
        return vDatosCorrectos;
    }
    
    public void limpiarCampos(){
        btnGuardar.setEnabled(true);
        txtIdProveedor.setText("");
        txtIdProducto.setText("");
        txtDescripcionProducto.setText("");
        txtCantidadProducto.setText("");
        txtCostoProducto.setText("");
        txtCaducidadProducto.setText("");
        txtMinimoProducto.setText("");
        txtBuscar.setText("");
        cbxDescripcionProveedor.setSelectedItem("Seleccionar");
        cbxUnidadMedidaProducto.setSelectedItem("Arroba");
        txtBuscar.setText("");
        txtFechaCaducidadProducto.setText("");
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
                tabla.addColumn("Descripcion");
                tabla.addColumn("Proveedor");
                tabla.addColumn("Costo");
                tabla.addColumn("Unidad Medida");
                tabla.addColumn("Cantidad");
                tabla.addColumn("Caducidad");
                tabla.addColumn("Minimo");
                tabla.addColumn("Fecha Caducidad");

                for (ObjetoProducto xProducto : listaProductos) {
                    tabla.setRowCount(contadorFilas);
                    tabla.setValueAt(xProducto.getDescripcion_producto(), cProveedor, 0);
                    tabla.setValueAt(xProducto.getDescripcion_proveedor(), cProveedor, 1);
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
            tabla.addColumn("Descripcion");
            tabla.addColumn("Proveedor");
            tabla.addColumn("Costo");
            tabla.addColumn("Cantidad");
            tabla.addColumn("Unidad Medida");
            tabla.addColumn("Caducidad");
            tabla.addColumn("Minimo");
            tabla.addColumn("Fecha Caducidad");
            tabla.setRowCount(listaProductos.size());
            
            for (ObjetoProducto x : listaProductos) {
                tabla.setValueAt(x.getDescripcion_producto(), i, 0);
                tabla.setValueAt(x.getDescripcion_proveedor(), i, 1);
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
    
    private void actualizarRegistro(){
        
        producto.setId_producto(Integer.parseInt(txtIdProducto.getText()));
        producto.setDescripcion_producto(txtDescripcionProducto.getText());
        producto.setId_proveedor(Integer.parseInt(txtIdProveedor.getText()));
        producto.setCosto_producto(Double.parseDouble(txtCostoProducto.getText()));
        producto.setUnidad_medida_producto(cbxUnidadMedidaProducto.getSelectedItem().toString());
        producto.setCantidad_producto(Double.parseDouble(txtCantidadProducto.getText()));
        producto.setCaducidad_producto(Integer.parseInt(txtCaducidadProducto.getText()));
        producto.setMinimo_producto(Integer.parseInt(txtMinimoProducto.getText()));
        //validar se el campo de texto de la fecha vienen nulo
        if (txtFechaCaducidadProducto.getText().isEmpty()) {
            producto.setFecha_caduciad_producto("null");
        } else {
            producto.setFecha_caduciad_producto(txtFechaCaducidadProducto.getText());

        }
        
        try{
            nuevoProducto.actualizarProducto(producto);
        }catch(Exception error){
            Mensaje.manipulacionExcepciones("critico", "Error al actualizar el producto " + error);
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
        lblIdProducto = new javax.swing.JLabel();
        txtIdProducto = new javax.swing.JTextField();
        lblMarcaProducto = new javax.swing.JLabel();
        lblDescProducto = new javax.swing.JLabel();
        txtDescripcionProducto = new javax.swing.JTextField();
        lblPrecioCompraProducto = new javax.swing.JLabel();
        txtCostoProducto = new javax.swing.JTextField();
        lblPrecioEstProducto = new javax.swing.JLabel();
        txtMinimoProducto = new javax.swing.JTextField();
        lblExiProducto = new javax.swing.JLabel();
        txtCaducidadProducto = new javax.swing.JTextField();
        lblUnidadMedidaProducto = new javax.swing.JLabel();
        txtIdProveedor = new javax.swing.JTextField();
        cbxDescripcionProveedor = new javax.swing.JComboBox();
        cbxUnidadMedidaProducto = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        txtCantidadProducto = new javax.swing.JTextField();
        lblExiProducto1 = new javax.swing.JLabel();
        txtFechaCaducidadProducto = new javax.swing.JTextField();
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
        tblProducto = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/producto.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(700, 600));
        setMinimumSize(new java.awt.Dimension(700, 600));
        setPreferredSize(new java.awt.Dimension(700, 600));

        pnlEncabezado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lblIdProducto.setText("ID Producto");

        txtIdProducto.setEditable(false);
        txtIdProducto.setToolTipText("");

        lblMarcaProducto.setText("Desc. Proveedor");

        lblDescProducto.setText("Descripcion");
        lblDescProducto.setToolTipText("");

        txtDescripcionProducto.setToolTipText("");

        lblPrecioCompraProducto.setText("Precio Compra");

        txtCostoProducto.setToolTipText("");

        lblPrecioEstProducto.setText("Cantidad Minima");

        lblExiProducto.setText("Tiempo Caducidad");

        txtCaducidadProducto.setToolTipText("");

        lblUnidadMedidaProducto.setText("Unidad de Medida");

        txtIdProveedor.setEditable(false);

        cbxDescripcionProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar" }));
        cbxDescripcionProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxDescripcionProveedorActionPerformed(evt);
            }
        });

        cbxUnidadMedidaProducto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Arroba", "Caja", "Galon", "Gramo", "Metro", "Mililitro", "Lasca", "Libra", "Porcion", "Quintal", "Onza", "Onza Fluida", "Sobre", "Kilo", "Rollo", "Unidad" }));

        jLabel1.setText("Cantidad");

        lblExiProducto1.setText("Fecha Caducidad");

        txtFechaCaducidadProducto.setToolTipText("");

        javax.swing.GroupLayout pnlEncabezadoLayout = new javax.swing.GroupLayout(pnlEncabezado);
        pnlEncabezado.setLayout(pnlEncabezadoLayout);
        pnlEncabezadoLayout.setHorizontalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblIdProducto)
                            .addComponent(lblDescProducto)
                            .addComponent(jLabel1))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(lblPrecioCompraProducto)
                        .addGap(18, 18, 18)))
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDescripcionProducto)
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblMarcaProducto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cbxDescripcionProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtCostoProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(txtCantidadProducto))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblPrecioEstProducto)
                                .addGap(18, 18, 18)
                                .addComponent(txtMinimoProducto))
                            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                                .addComponent(lblUnidadMedidaProducto)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbxUnidadMedidaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(12, 12, 12)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblExiProducto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblExiProducto1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCaducidadProducto, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(txtFechaCaducidadProducto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlEncabezadoLayout.setVerticalGroup(
            pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEncabezadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdProducto)
                    .addComponent(txtIdProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMarcaProducto)
                    .addComponent(cbxDescripcionProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDescripcionProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDescProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCostoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUnidadMedidaProducto)
                    .addComponent(cbxUnidadMedidaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExiProducto)
                    .addComponent(txtCaducidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioCompraProducto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEncabezadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMinimoProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPrecioEstProducto)
                    .addComponent(jLabel1)
                    .addComponent(txtCantidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblExiProducto1)
                    .addComponent(txtFechaCaducidadProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        txtCostoProducto.getAccessibleContext().setAccessibleName("txtPrecioCompra");

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 331, Short.MAX_VALUE)
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
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

    private void cbxDescripcionProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxDescripcionProveedorActionPerformed
        seleccion = "";
        seleccion = cbxDescripcionProveedor.getSelectedItem().toString();
        
        ArrayList<ObjetoProducto> lProveedores = new ArrayList();
            
        try{
            lProveedores = nuevoProveedor.retornaProveedores();

        } catch (Exception error) {
            Mensaje.manipulacionExcepciones("critico", "Error al obtener codigo de proveedor seleccionado " + error);
        }

        // Recorro la lista de proveedores para obtener el id del proveedor seleccionado
        for (ObjetoProducto xProducto : lProveedores) {
            if (xProducto.getDescripcion_proveedor().equals(seleccion)) {
                txtIdProveedor.setText(String.valueOf(xProducto.getId_proveedor()));
            }
        }
    }//GEN-LAST:event_cbxDescripcionProveedorActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarEnRegistrosGuardados();
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tblProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblProductoMouseClicked
        btnGuardar.setEnabled(false);
        txtDescripcionProducto.setText(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 0)));
        cbxDescripcionProveedor.setSelectedItem(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 1)));
        txtCostoProducto.setText(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 2)));
        cbxUnidadMedidaProducto.setSelectedItem(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 3)));
        txtCantidadProducto.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(), 4).toString());
        txtCaducidadProducto.setText(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 5)));
        txtMinimoProducto.setText(String.valueOf(tblProducto.getValueAt(tblProducto.getSelectedRow(), 6)));
        txtIdProducto.setText(String.valueOf(obtenerIdProducto(txtDescripcionProducto.getText(), txtIdProveedor.getText())));
        txtFechaCaducidadProducto.setText(tblProducto.getValueAt(tblProducto.getSelectedRow(),7).toString());
    }//GEN-LAST:event_tblProductoMouseClicked

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        if(txtDescripcionProducto.getText().isEmpty()){
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
    private javax.swing.JComboBox cbxDescripcionProveedor;
    private javax.swing.JComboBox cbxUnidadMedidaProducto;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBuscar;
    private javax.swing.JLabel lblDescProducto;
    private javax.swing.JLabel lblExiProducto;
    private javax.swing.JLabel lblExiProducto1;
    private javax.swing.JLabel lblIdProducto;
    private javax.swing.JLabel lblMarcaProducto;
    private javax.swing.JLabel lblPrecioCompraProducto;
    private javax.swing.JLabel lblPrecioEstProducto;
    private javax.swing.JLabel lblUnidadMedidaProducto;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JPanel pnlEncabezado;
    private javax.swing.JTable tblProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCaducidadProducto;
    private javax.swing.JTextField txtCantidadProducto;
    private javax.swing.JTextField txtCostoProducto;
    private javax.swing.JTextField txtDescripcionProducto;
    private javax.swing.JTextField txtFechaCaducidadProducto;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdProveedor;
    private javax.swing.JTextField txtMinimoProducto;
    // End of variables declaration//GEN-END:variables
}
