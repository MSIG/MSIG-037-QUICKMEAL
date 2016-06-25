/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package QuickMeal.Vistas;

import QuickMeal.Accesos.AccesoCliente;
import QuickMeal.Accesos.AccesoReporteVentasConsolidado;
import QuickMeal.Accesos.AccesoServicio;
import QuickMeal.Accesos.Conexion;
import QuickMeal.Accesos.Mensaje;
import QuickMeal.Objetos.ObjetoCliente;
import QuickMeal.Objetos.ObjetoServicio;
import QuickMeal.Objetos.ObjetoVentaConsolidado;
import QuickMeal.Servicios.SendMailTLS;
import QuickMeal.Servicios.Sesion;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author Megabyte Soluciones Integrales Guatemala
 */


public class BaseDeDatosHistorialCompras extends javax.swing.JInternalFrame {

    AccesoReporteVentasConsolidado acceso = new AccesoReporteVentasConsolidado();
    AccesoServicio acceso_servicio = new AccesoServicio();
    AccesoCliente acceso_cliente = new AccesoCliente();
    AccesoReporteVentasConsolidado acceso_reporte = new AccesoReporteVentasConsolidado();
    ArrayList<ObjetoVentaConsolidado> lista = new ArrayList();
    ArrayList<ObjetoServicio> lista_servicio = new ArrayList();
    ArrayList<ObjetoCliente> lista_clientes = new ArrayList();
    Mensaje mensaje = new Mensaje();
    
    /**
     * Creates new form wdwBaseDeDAtosHistorialDeVentas
     */
    public BaseDeDatosHistorialCompras() {
        initComponents();
    }
    
    public void pdf(String fecha_inicial, String fecha_final, int codigo, String carpeta) {
        Conexion Acceso = new Conexion();
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        try {
            URL url_reporte = this.getClass().getResource("/QuickMeal/Reportes/HistorialComprasPorClienteDetallado.jasper");
            JasperReport reporte = (JasperReport) JRLoader.loadObject(url_reporte);
            HashMap parametro = new HashMap();
            parametro.put("P_FECHA_INICIAL", fecha_inicial);
            parametro.put("P_FECHA_FINAL", fecha_final);
            parametro.put("P_CODIGO_CLIENTE", codigo);
            JasperPrint pantalla = JasperFillManager.fillReport(reporte, parametro, Acceso.conectar());
            //JasperViewer visualizador = new JasperViewer(pantalla, false);
            //visualizador.show();
            System.out.println("Creando archivo "+"C:/Reportes/"+carpeta+"/"+codigo+".docx");
           //JasperExportManager.exportReportToHtmlFile(pantalla, "C:/Reportes/"+carpeta+"/"+codigo+".html");
            JRDocxExporter exportar = new JRDocxExporter();
            exportar.setParameter(JRExporterParameter.JASPER_PRINT, pantalla);
            exportar.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:/Reportes/"+carpeta+"/"+codigo+".docx");
            exportar.exportReport();
        } catch (JRException ex) {
            System.err.println("Error iReport: " + ex.getMessage());
            mensaje.manipulacionExcepciones("critico", "No se pudo crear el reporte dle codigo " + codigo);
        } finally {
            Acceso.cerrarConexion();
        }
    }
    
    private void mostrarOrdenes(String inicio, String fin, String codigo){
        
        DefaultTableModel tabla = new DefaultTableModel();
        lista = acceso.ejecutar(inicio, fin, codigo);
        
        tabla.addColumn("Codigo");
        tabla.addColumn("Centro de Costo");
        tabla.addColumn("Cuenta");
        tabla.addColumn("Nombre");
        tabla.addColumn("Empresa");
        tabla.addColumn("No. Vale");
        tabla.addColumn("Fecha");
        tabla.addColumn("Propina");
        tabla.addColumn("Venta");
        tabla.addColumn("Descuento");
        tabla.addColumn("Extra");
        tabla.addColumn("Total");
        tabla.addColumn("Pago");
        tabla.addColumn("Empleado");
        tabla.addColumn("Servicio");
        tabla.addColumn("Costo");
        
        tabla.setRowCount(lista.size());
        int i = 0;
        
        for(ObjetoVentaConsolidado n : lista){
            tabla.setValueAt(n.getCodigo_cliente(), i, 0);
            tabla.setValueAt(n.getCc_cliente(), i, 1);
            tabla.setValueAt(n.getCuenta_cliente(), i, 2);
            tabla.setValueAt(n.getNombre_cliente(), i, 3);
            tabla.setValueAt(n.getEmpresa_cliente(), i, 4);
            tabla.setValueAt(n.getNo_venta(), i, 5);
            tabla.setValueAt(n.getFecha_venta(), i, 6);
            tabla.setValueAt(n.getPropina_venta(), i, 7);
            tabla.setValueAt(n.getTotal_venta(), i, 8);
            tabla.setValueAt(n.getDescuento_venta(), i, 9);
            tabla.setValueAt(n.getExtra_venta(), i, 10);
            tabla.setValueAt(n.getTotal(), i, 11);
            tabla.setValueAt(n.getPago(), i, 12);
            tabla.setValueAt(n.getEmpleado(), i, 13);
            //consultar el valor de los servicios segun el codigo
            System.out.println("Consultando el servicio");
            try {
                lista_servicio = acceso_servicio.buscarCostoServicio(n.getCodigo_cliente());
                tabla.setValueAt(lista_servicio.get(0).getDescripcion_servicio(),i,14);
                tabla.setValueAt(lista_servicio.get(0).getPrecio_servicio(),i,15);
            }catch(Exception error){
                tabla.setValueAt("No tiene servicio",i,14);
                tabla.setValueAt("0.00",i,15);
            }
            i++;
        }
        
        tblOrdenes.setModel(tabla);
        tblOrdenes.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblOrdenes.getColumnModel().getColumn(1).setPreferredWidth(50);
        tblOrdenes.getColumnModel().getColumn(2).setPreferredWidth(50);
        tblOrdenes.getColumnModel().getColumn(3).setPreferredWidth(150);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlDetalle = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblOrdenes = new javax.swing.JTable();
        pnlBotones = new javax.swing.JPanel();
        btnMostrar = new javax.swing.JButton();
        txtFechaFinal = new javax.swing.JTextField();
        lblHasta = new javax.swing.JLabel();
        txtFechaInicial = new javax.swing.JTextField();
        lblFecha = new javax.swing.JLabel();
        lblText = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btn_exportar = new javax.swing.JButton();
        btnEnviarCorreo = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventas Consolidado");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/QuickMeal/Imagenes/historialVentas.png"))); // NOI18N

        pnlDetalle.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblOrdenes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblOrdenesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblOrdenes);

        javax.swing.GroupLayout pnlDetalleLayout = new javax.swing.GroupLayout(pnlDetalle);
        pnlDetalle.setLayout(pnlDetalleLayout);
        pnlDetalleLayout.setHorizontalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlDetalleLayout.setVerticalGroup(
            pnlDetalleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDetalleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnlBotones.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnMostrar.setText("Mostrar");
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        lblHasta.setText("Fin");

        lblFecha.setText("Incio");

        lblText.setText("Codigo");

        txtCodigo.setText("0");

        btn_exportar.setText("Exportar");
        btn_exportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exportarActionPerformed(evt);
            }
        });

        btnEnviarCorreo.setText("Enviar Correos");
        btnEnviarCorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEnviarCorreoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBotonesLayout = new javax.swing.GroupLayout(pnlBotones);
        pnlBotones.setLayout(pnlBotonesLayout);
        pnlBotonesLayout.setHorizontalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblFecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblHasta)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEnviarCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btn_exportar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMostrar)
                .addContainerGap())
        );
        pnlBotonesLayout.setVerticalGroup(
            pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBotonesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBotonesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMostrar)
                    .addComponent(txtFechaFinal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblHasta)
                    .addComponent(lblFecha)
                    .addComponent(txtFechaInicial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblText)
                    .addComponent(txtCodigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_exportar)
                    .addComponent(btnEnviarCorreo))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlBotones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlDetalle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlBotones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDetalle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        mostrarOrdenes(txtFechaInicial.getText(), txtFechaFinal.getText(), txtCodigo.getText());
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void tblOrdenesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblOrdenesMouseClicked

    }//GEN-LAST:event_tblOrdenesMouseClicked

    private void btn_exportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exportarActionPerformed
        //crear carpeta para guardar los archivos
        String fecha_inicial = txtFechaInicial.getText();
        String fecha_final = txtFechaFinal.getText();
        String codigo = txtCodigo.getText();
        String carpeta = fecha_inicial+" al "+fecha_final;
        
        try {
            System.out.println("Intentando crear carpeta");
            File directorio = new File("C:/Reportes/"+carpeta);
            directorio.mkdir();
            System.out.println("Carpeta "+carpeta+" creada");
        } catch (Exception e) {
            mensaje.manipulacionExcepciones("critico", "No se pudo crear la carpeta");
        }

        //recorrer toda la lista y crear los archivos pdf
        for(int i=0; i<tblOrdenes.getRowCount(); i++){
            pdf(fecha_inicial, fecha_final, Integer.parseInt(tblOrdenes.getValueAt(i, 0).toString()), carpeta);
        }
        
        mensaje.manipulacionExcepciones("informacion", "Ha finalizado el proceso de creacion de archvivos");
    }//GEN-LAST:event_btn_exportarActionPerformed

    private void btnEnviarCorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEnviarCorreoActionPerformed
        
        //SendMailTLS correo = new SendMailTLS(); correo.send();
        int cantidad_correos_enviados = 0;
        String fecha_inicial = txtFechaInicial.getText();
        String fecha_final = txtFechaFinal.getText();
        String carpeta = "C:/Reportes/" + fecha_inicial + " al " + fecha_final + "/";
                
        try {
            //carga lista de usuarios
            lista_clientes = acceso_cliente.retornaCliente();
            //buscar si tiene algun archivo para ser enviado
            for(ObjetoCliente x : lista_clientes){
                
                String to = x.getDireccion_cliente();
                String ficha = String.valueOf(x.getCodigo_cliente());
                System.out.println("Tratando de enviar correo a " + to + " de la ficha " + ficha + " ubicada en " + carpeta + ficha + ".docx");
                //antes de enviarlo validar que exista el archivo
                File archivo = new File(carpeta + ficha + ".docx");
                if(archivo.exists()){
                    //realizar consulta del total
                    double total = acceso_reporte.total(fecha_inicial, fecha_final, ficha);
                    
                    //enviar correo al destinario con su archivo adjunto
                    Sesion mail = new Sesion("pantaleon.club@gmail.com", "Derwin2012mg", to, "Consumo Club Pantaleon", "Estimado cliente se le envia el resumen del consumo efectuado en Club Pantaleon correspondiente al periodo " +  fecha_inicial + " al " + fecha_final + " con un total de " + total + " para mas informacion consulte el archivo adjunto donde encontrara el detalle de sus vales.", carpeta + ficha + ".docx");
                    cantidad_correos_enviados++;
                } else {
                    mensaje.manipulacionExcepciones("critico", "No se encontro el archivo del cliente " + ficha);
                }
                
            }
            mensaje.manipulacionExcepciones("informacion", "Se enviaron " + cantidad_correos_enviados + " correos satisfactoriamente");
        } catch(Exception e){
            mensaje.manipulacionExcepciones("critico", "Ocurrio un error al enviar el correo: " + e.getMessage());
        }
        
    }//GEN-LAST:event_btnEnviarCorreoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEnviarCorreo;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btn_exportar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lblText;
    private javax.swing.JPanel pnlBotones;
    private javax.swing.JPanel pnlDetalle;
    private javax.swing.JTable tblOrdenes;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtFechaFinal;
    private javax.swing.JTextField txtFechaInicial;
    // End of variables declaration//GEN-END:variables
}
