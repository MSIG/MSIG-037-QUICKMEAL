/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.*;
import QuickMeal.Objetos.*;
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
 * @author Megabytes Soluciones Integrales Guatemala
 */
public class MovimientoVentaDeProductos extends javax.swing.JInternalFrame {

    private Mensaje mensaje = new Mensaje();
    private int posicion = 0;
    private ObjetoCliente cliente = new ObjetoCliente();
    private float total = 0;
    int id_venta = 0;

    /**
     * Creates new form MovimientoVentaDeProductos
     */
    public MovimientoVentaDeProductos() {
        initComponents();
        mostrarCategoirasActivas();
        restablecerTablaItem();
        restablecerTablaDetalle();
    }

    private void mostrarCategoirasActivas() {
        AccesoCategoria categoria = new AccesoCategoria();
        ArrayList<ObjetoCategoria> lista = new ArrayList();

        int x = 0;
        int y = 0;

        try {
            lista = categoria.listar();
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("advertencia", "Ocurrio un error inesperado");
        }

        if (lista.isEmpty()) {
            mensaje.manipulacionExcepciones("Advertencia", "No existen categorias");
        } else {
            for (ObjetoCategoria n : lista) {
                Categoria nueva_categoria = new Categoria();
                pnlCategorias.add(nueva_categoria);
                nueva_categoria.setVisible(true);
                nueva_categoria.setTitle(String.valueOf(n.getId_categoria()));
                nueva_categoria.descripcionCategoria(n.getDescripcion_categoria());
                nueva_categoria.setLocation(x * 200, y * 100);
                x++;
                if (x == 6) {
                    x=0;
                    y++;
                }
            }
        }
    }
    
    public static void restablecerTablaItem() {
        DefaultTableModel modeloItem = new DefaultTableModel();
        modeloItem.addColumn("Codigo");
        modeloItem.addColumn("Descripcion");
        modeloItem.addColumn("Precio");
        modeloItem.setRowCount(100);
        tblItems.setModel(modeloItem);
        tblItems.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblItems.getColumnModel().getColumn(1).setPreferredWidth(700);
    }
    
    private void restablecerTablaDetalle(){
        DefaultTableModel modeloDetalle = new DefaultTableModel();
        modeloDetalle.addColumn("Codigo");
        modeloDetalle.addColumn("Descripcion");
        modeloDetalle.addColumn("Precio");
        modeloDetalle.addColumn("Cantidad");
        modeloDetalle.setRowCount(100);
        tblDetalle.setModel(modeloDetalle);
        tblDetalle.getColumnModel().getColumn(0).setPreferredWidth(100);
        tblDetalle.getColumnModel().getColumn(1).setPreferredWidth(200);
    }
    
    public void ingresaCliente(ObjetoCliente cliente){
        this.cliente = cliente;
        txtNombreEmpleado.setText(cliente.getNombre_cliente());
        txtEmpresaEmpleado.setText(cliente.getEmpresa_cliente());
        txtIdCliente.setText(Integer.toString(cliente.getId_cliente()));
    }
    
    private void calcularMonto() {
        total = 0;
        for (int i = 0; i < 100; i++) {
            try {
                total = total + Float.parseFloat(tblDetalle.getValueAt(i, 2).toString());
            } catch(Exception error) {
                total = total + 0;
            }
        }
        
        txtTotal.setText(Float.toString(total));
        
        //SOLO CUANDO EL MONTO SEA MAYOR A CERO SE PUEDE
        //GENERAR UNA ORDEN PARA LA COCINA
        
        if(txtTotal.getText().equals("0.0")){
            btnGuardar.setEnabled(false);
        } else {
            btnGuardar.setEnabled(true);
        }
        
    }
    
    private void guardarPedido(){
        if(guardarVenta()){
            if(guardarDetalleVenta()){
                guardarOrden();
                //si todo esta bien imprime el ticket
                imprimirTicket(QuickMeal.getIdEstacion());
            }
        }
    }
    
    private void imprimirTicket(int id_estacion){
        Mensaje mensaje = new Mensaje();
        try{
            Conexion acceso = new Conexion();
            URL url_reporte = this.getClass().getResource("/QuickMeal/Reportes/miniOrden.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            parametro.put("P_ESTACION", id_estacion);
            parametro.put("P_ESTADO_ORDEN", "P");
            parametro.put("P_ESTADO_DETALLE_ORDEN", "P");
            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, acceso.conectar());
            JasperViewer visualizador = new JasperViewer(pantalla, false);
            visualizador.show();
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critico", "No se puede generar el reporte");
        }
    }
    
     private void imprimirTicketAgregado(int id_orden){
        Mensaje mensaje = new Mensaje();
        try{
            Conexion acceso = new Conexion();
            URL url_reporte = this.getClass().getResource("/QuickMeal/Reportes/miniOrdenCocina.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            parametro.put("P_ORDEN", id_orden);
            parametro.put("P_ESTADO_ORDEN", "P");
            parametro.put("P_ESTADO_DETALLE_ORDEN", "P");
            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, acceso.conectar());
            JasperViewer visualizador = new JasperViewer(pantalla, false);
            visualizador.show();
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critico", "No se puede generar el reporte");
        }
    }
    
    private boolean guardarVenta(){
        
        boolean resultado = false;
        ObjetoVenta venta = new ObjetoVenta();
        AccesoVenta acceso = new AccesoVenta();
        
        //CAPTURAR DATOS DE LA VENTA
        venta.setId_estacion(QuickMeal.getIdEstacion());
        venta.setId_usuario(QuickMeal.getIdUsuario());
        venta.setId_cliente(Integer.parseInt(txtIdCliente.getText()));
        venta.setFactura_venta("S/F");
        venta.setDireccion_venta("INGENIO PANTALEON");
        venta.setFecha_venta("");
        venta.setNota_venta(txtNotas.getText());
        venta.setPropina_venta(0.0);
        venta.setId_empleado(Integer.parseInt(txtMesero.getText()));
        
        try{
            acceso.insertarVenta(venta);
            resultado = true;
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar la venta");
            resultado = false;
        }
        
        return resultado;
    }
    
    private boolean guardarDetalleVenta(){
        
        boolean resultado = true;
        int id_item = 0;
        int cantidad = 0;
        
        ArrayList<ObjetoVenta> lista = new ArrayList();
        AccesoVenta acceso_venta = new AccesoVenta();
        
        //BUSCAR EL ID DEL VENTA REALIZADA
        System.out.println("Buscar id de la venta para el detalle");
        lista = acceso_venta.buscarVenta(QuickMeal.getIdEstacion(), QuickMeal.getIdUsuario(), Integer.parseInt(txtIdCliente.getText()));
        
        //RECORRER VENTA PARA OBTENER EL ID Y GRABAR DETALLE
        if(lista.isEmpty()){
            mensaje.manipulacionExcepciones("critico", "No se encontro la venta");
            resultado = false;
        } else {
            for(ObjetoVenta x:lista) {
                id_venta = x.getId_venta();
            }
            System.err.println("Venta No. " + id_venta);
        }
        
    
        //GUARDAR DETALLE DE LA VENTA
        AccesoDetalleVenta detalle = new AccesoDetalleVenta();
        try {
            for (int i=0; i<posicion; i++) {
                id_item = Integer.parseInt(tblDetalle.getValueAt(i, 0).toString());
                cantidad = Integer.parseInt(tblDetalle.getValueAt(i, 3).toString());
                for(int x=0; x<cantidad; x++){
                    detalle.insertarDetalleVenta(id_venta, id_item, 1);
                }   
            }
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al guardar el detalle");
        }

        return resultado;
    }
    
    private void guardarOrden(){
        try{
            AccesoOrden orden = new AccesoOrden();
            orden.insertarOrden(id_venta);
        }catch(Exception error){
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al insertar la orden");
        }
    }
    
    private void agregarDetalleVenta() {

        int id_venta = 0;
        int id_item = 0;
        int cantidad = 0;

        AccesoVenta venta = new AccesoVenta();
        AccesoDetalleVenta detalle = new AccesoDetalleVenta();
        ArrayList<ObjetoDetalleVenta> lista = new ArrayList();

        //ENCONTRAR EL ID DE LA VENTA
        try {
            lista = detalle.buscarIdVenta(txtNumeroOrden.getText(), txtIdCliente.getText());
        } catch (Exception error) {
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al buscar el ID de la venta");
        }


        if (lista.isEmpty()) {
            mensaje.manipulacionExcepciones("critico", "No se encontro ninguna venta pendiente para este numnero de orden");
        } else {
            for (ObjetoDetalleVenta n : lista) {
                id_venta = n.getId_venta();
            }

            if(txtNotas.getText().isEmpty()){
                System.out.println("No ingreso nada en notas");
            } else {
                venta.actualizarNotas(id_venta, txtNotas.getText());
            }

            //INSERTAR DETALLE DE LA VENTA
            try {
                for (int i = 0; i < posicion; i++) {
                    id_item = Integer.parseInt(tblDetalle.getValueAt(i, 0).toString());
                    cantidad = Integer.parseInt(tblDetalle.getValueAt(i, 3).toString());
                    for(int x=0; x<cantidad; x++){
                        detalle.insertarDetalleVenta(id_venta, id_item, 1);
                    }
                }
                mensaje.manipulacionExcepciones("informacion", "Se modifico con exito la orden numero " + txtNumeroOrden.getText());
            } catch (Exception error) {
                mensaje.manipulacionExcepciones("critico", "Ocurrio un error al modificar el detalle");
            }
        }

        
        //mostrar el ticket para la impresora de cocina
        imprimirTicketAgregado(Integer.parseInt(txtNumeroOrden.getText()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pnlOpcionesMenu = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        txtOrden = new javax.swing.JLabel();
        txtNumeroOrden = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblItems = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAbajo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        pnlEmpleado = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtNombreEmpleado = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtEmpresaEmpleado = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtIdCliente = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        pnlCategorias = new javax.swing.JDesktopPane();
        btnBuscar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMesero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Venta de Productos");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/ventas.png"))); // NOI18N
        setMaximumSize(new java.awt.Dimension(4500, 3250));
        setMinimumSize(new java.awt.Dimension(900, 700));
        setPreferredSize(new java.awt.Dimension(900, 700));

        jLabel1.setText("Categorias del Menu");

        pnlOpcionesMenu.setBackground(new java.awt.Color(153, 255, 153));
        pnlOpcionesMenu.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Total Actual");

        txtTotal.setBackground(new java.awt.Color(0, 204, 51));
        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtTotal.setForeground(new java.awt.Color(204, 0, 0));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtTotal.setText("0");

        txtOrden.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtOrden.setText("No. Orden");

        txtNumeroOrden.setBackground(new java.awt.Color(255, 255, 102));
        txtNumeroOrden.setEditable(false);
        txtNumeroOrden.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        txtNumeroOrden.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        txtNumeroOrden.setText("0");

        javax.swing.GroupLayout pnlOpcionesMenuLayout = new javax.swing.GroupLayout(pnlOpcionesMenu);
        pnlOpcionesMenu.setLayout(pnlOpcionesMenuLayout);
        pnlOpcionesMenuLayout.setHorizontalGroup(
            pnlOpcionesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesMenuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlOpcionesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(pnlOpcionesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlOpcionesMenuLayout.createSequentialGroup()
                        .addComponent(txtOrden)
                        .addGap(0, 73, Short.MAX_VALUE))
                    .addComponent(txtNumeroOrden))
                .addContainerGap())
        );
        pnlOpcionesMenuLayout.setVerticalGroup(
            pnlOpcionesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOpcionesMenuLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlOpcionesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtOrden))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlOpcionesMenuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        tblItems.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        tblItems.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        tblItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Precio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItems.setRowHeight(50);
        jScrollPane2.setViewportView(tblItems);

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/sumar.png"))); // NOI18N
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/restar.png"))); // NOI18N
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/guardarOrden.png"))); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAbajo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/nuevaOrden.png"))); // NOI18N
        btnAbajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbajoActionPerformed(evt);
            }
        });

        tblDetalle.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Codigo", "Descripcion", "Precio", "Cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblDetalle.setRowHeight(18);
        jScrollPane1.setViewportView(tblDetalle);

        pnlEmpleado.setBackground(new java.awt.Color(255, 204, 102));
        pnlEmpleado.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setText("Nombre");

        txtNombreEmpleado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNombreEmpleado.setEnabled(false);

        jLabel4.setText("Empresa");

        txtEmpresaEmpleado.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtEmpresaEmpleado.setEnabled(false);

        jLabel5.setText("ID Cliente");

        txtIdCliente.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtIdCliente.setEnabled(false);

        jLabel7.setText("Notas");

        txtNotas.setColumns(20);
        txtNotas.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtNotas.setRows(5);
        jScrollPane3.setViewportView(txtNotas);

        javax.swing.GroupLayout pnlEmpleadoLayout = new javax.swing.GroupLayout(pnlEmpleado);
        pnlEmpleado.setLayout(pnlEmpleadoLayout);
        pnlEmpleadoLayout.setHorizontalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                        .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtEmpresaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 20, Short.MAX_VALUE))
                            .addComponent(txtIdCliente)))
                    .addComponent(txtNombreEmpleado)
                    .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                        .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlEmpleadoLayout.setVerticalGroup(
            pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEmpleadoLayout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addGap(4, 4, 4)
                .addComponent(txtNombreEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtEmpresaEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("Datos del Empleado");

        pnlCategorias.setBackground(new java.awt.Color(0, 102, 153));
        pnlCategorias.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/buscar.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        jLabel8.setText("Buscar");

        txtMesero.setText("0");

        jLabel9.setText("Codigo Empleado");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 501, Short.MAX_VALUE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnBuscar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 403, Short.MAX_VALUE))
                            .addComponent(pnlCategorias, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(pnlEmpleado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(pnlOpcionesMenu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtMesero))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlCategorias)
                    .addComponent(pnlEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel8)
                        .addComponent(btnBuscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMesero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAbajo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(pnlOpcionesMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        tblDetalle.setValueAt(tblItems.getValueAt(tblItems.getSelectedRow(), 0), posicion, 0);
        tblDetalle.setValueAt(tblItems.getValueAt(tblItems.getSelectedRow(), 1), posicion, 1);
        tblDetalle.setValueAt(tblItems.getValueAt(tblItems.getSelectedRow(), 2), posicion, 2);
        tblDetalle.setValueAt(1, posicion, 3);
        calcularMonto();
        posicion++;
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        posicion--;
        tblDetalle.setValueAt(null, posicion, 0);
        tblDetalle.setValueAt(null, posicion, 1);
        tblDetalle.setValueAt(null, posicion, 2);
        tblDetalle.setValueAt(null, posicion, 3);
        calcularMonto();
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnAbajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbajoActionPerformed
        dispose();
        CodigoEmpleado nuevo = new CodigoEmpleado();
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;  
        int x = (ancho / 2) - (nuevo.getWidth() / 2);
        int y = (alto / 2) - (nuevo.getHeight() / 2);
        QuickMeal.pnlPrincipal.add(nuevo);
        nuevo.setVisible(true);
        nuevo.setLocation(x, y);
        nuevo.toFront();
    }//GEN-LAST:event_btnAbajoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
         if(txtNumeroOrden.getText().equals("0")) {
            guardarPedido();
        } else {
            agregarDetalleVenta();
        }
        restablecerTablaDetalle();
        restablecerTablaItem(); 
        txtNumeroOrden.setText("");
        txtTotal.setText("");
        txtNotas.setText("");
        txtIdCliente.setText("");
        txtNombreEmpleado.setText("");
        txtEmpresaEmpleado.setText("");
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        
        AccesoItem item = new AccesoItem();
        ArrayList<ObjetoItem> lista = new ArrayList();
        
        int y = 0;
        
        try{
           lista = item.buscarActivo(txtBuscar.getText()); 
        } catch(Exception error) {
           mensaje.manipulacionExcepciones("critico", "Ocurrio un error inesperado");
        }
        
        if (lista.isEmpty()) {
            mensaje.manipulacionExcepciones("advertencia", "No se encotraron items para esta categoria");
            MovimientoVentaDeProductos.restablecerTablaItem();
        } else {
            MovimientoVentaDeProductos.restablecerTablaItem();
            for (ObjetoItem n : lista) {
                MovimientoVentaDeProductos.tblItems.setValueAt(n.getId_item(), y, 0);
                MovimientoVentaDeProductos.tblItems.setValueAt(n.getDescripcion_item(), y, 1);
                MovimientoVentaDeProductos.tblItems.setValueAt(n.getPrecio_item(), y, 2);
                y++;
            }
        }
    }//GEN-LAST:event_btnBuscarActionPerformed
 
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbajo;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JDesktopPane pnlCategorias;
    private javax.swing.JPanel pnlEmpleado;
    private javax.swing.JPanel pnlOpcionesMenu;
    private javax.swing.JTable tblDetalle;
    public static javax.swing.JTable tblItems;
    private javax.swing.JTextField txtBuscar;
    public static javax.swing.JTextField txtEmpresaEmpleado;
    public static javax.swing.JTextField txtIdCliente;
    private javax.swing.JTextField txtMesero;
    public static javax.swing.JTextField txtNombreEmpleado;
    private javax.swing.JTextArea txtNotas;
    public static javax.swing.JTextField txtNumeroOrden;
    private javax.swing.JLabel txtOrden;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
