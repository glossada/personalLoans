/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package presentacion;

import javax.swing.JOptionPane;
import negocio.*;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Gabriel Lossada
 */
public class FrmPrestamosM extends javax.swing.JInternalFrame {

    private final PrestamoControl CTRL;
    private final MovimientosControl CTRLM;
    private String accion;
    private String Inversion;
    private float Dolar;
    private String nomAnt;
    private int idPrestamoActual;
    private double montoApagarActual;
    private LocalDate hoy;
    private String fechaHoy;
    public DefaultTableModel modeloDetalle;
    public JFrame contenedor;

    //paginacion
    private int totalXPag = 25;
    private int numPag = 1;
    private boolean primeraCarga = true;
    private int totalRegistros;

    public FrmPrestamosM(JFrame frmP, String tipo) {
        initComponents();
        this.CTRL = new PrestamoControl();
        this.CTRLM = new MovimientosControl();
        this.hoy = LocalDate.now();
        this.fechaHoy = hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        txtFechaHoy.setText(fechaHoy);
        this.contenedor = frmP;
        this.paginar();
        if(tipo.equals("t")){
        this.listar("0","0", false);
        lblFiltroActual.setText("Filtro Actual: todo");
        this.primeraCarga = false;
        }else if(tipo.equals("v")){
            this.listarVencimientos("vencido");
            lblFiltroActual.setText("Filtro Actual: Vencidos");
            this.primeraCarga = false;
        }else if(tipo.equals("vp")){
            this.listarVencimientos("por vencer");
            lblFiltroActual.setText("Filtro Actual: vence pronto");
            this.primeraCarga = false;
        }else if(tipo.equals("vh")){
            this.listarVencimientos("vence hoy");
            lblFiltroActual.setText("Filtro Actual: vence hoy");
            this.primeraCarga = false;
        }
        panelVentas.setEnabledAt(1, false);
        this.crearDetalles();
    }

    public void ocultarColumnaPrestamos() {
        tblVentas.getColumnModel().getColumn(1).setMaxWidth(0);
        tblVentas.getColumnModel().getColumn(1).setMinWidth(0);
        tblVentas.getTableHeader().getColumnModel().getColumn(1).setMaxWidth(0);
        tblVentas.getTableHeader().getColumnModel().getColumn(1).setMinWidth(0);

        tblVentas.getColumnModel().getColumn(0).setMaxWidth(0);
        tblVentas.getColumnModel().getColumn(0).setMinWidth(0);
        tblVentas.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblVentas.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

    }
    
    public void ocultarColumnaCuotas() {
        tblDetalle.getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalle.getColumnModel().getColumn(0).setMinWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);

        tblDetalle.getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalle.getColumnModel().getColumn(5).setMinWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);
        
        tblDetalle.getColumnModel().getColumn(6).setMaxWidth(0);
        tblDetalle.getColumnModel().getColumn(6).setMinWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(6).setMaxWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(6).setMinWidth(0);
        
        tblDetalle.getColumnModel().getColumn(7).setMaxWidth(0);
        tblDetalle.getColumnModel().getColumn(7).setMinWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(7).setMaxWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(7).setMinWidth(0);
        
        tblDetalle.getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalle.getColumnModel().getColumn(5).setMinWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(5).setMaxWidth(0);
        tblDetalle.getTableHeader().getColumnModel().getColumn(5).setMinWidth(0);

    }


    public void paginar() {
        int totalPaginas;

        this.totalRegistros = this.CTRL.totalReg();
        this.totalXPag = Integer.parseInt((String) cboTotalXPag.getSelectedItem());
        totalPaginas = (int) (Math.ceil((double) this.totalRegistros / this.totalXPag));
        if (totalPaginas == 0) {
            totalPaginas = 1;
        }
        cboNumPag.removeAllItems();
        for (int i = 1; i <= totalPaginas; i++) {
            cboNumPag.addItem(Integer.toString(i));
        }
        cboNumPag.setSelectedIndex(0);
    }

    public void listar(String mes,String año, boolean paginar) {
        this.totalXPag = Integer.parseInt((String) cboTotalXPag.getSelectedItem());
        if ((String) cboNumPag.getSelectedItem() != null) {
            this.numPag = Integer.parseInt((String) cboNumPag.getSelectedItem());
        }

        if (paginar == true) {
            tblVentas.setModel(this.CTRL.listar(mes,año, this.totalXPag, this.numPag));
        } else {
            tblVentas.setModel(this.CTRL.listar(mes,año, 25, 1));
        }

        TableRowSorter orden = new TableRowSorter(tblVentas.getModel());
        tblVentas.setRowSorter(orden);
        this.ocultarColumnaPrestamos();
        lblRegistros.setText("Mostrando: " + CTRL.totalMostrados() + " registros de " + CTRL.totalReg() + " registros.");
    }
    
    public void listarBuscarCliente(String cliente){
        tblVentas.setModel(this.CTRL.listarBuscarCliente(cliente));
        TableRowSorter orden = new TableRowSorter(tblVentas.getModel());
        tblVentas.setRowSorter(orden);
        this.ocultarColumnaPrestamos();
        lblRegistros.setText("Mostrando: " + CTRL.totalMostrados() + " registros de " + CTRL.totalReg() + " registros.");
    }
    
    public void listarVencimientos(String tipo){
        int fechaHoy = CTRL.fechaStringToInt(txtFechaHoy.getText());
        if(tipo.equals("vencido")){
            tblVentas.setModel(this.CTRL.listarVencimientos(fechaHoy, "vencido"));
        }else if(tipo.equals("por vencer")){
            tblVentas.setModel(this.CTRL.listarVencimientos(fechaHoy, "por vencer"));
        }else{
            tblVentas.setModel(this.CTRL.listarVencimientos(fechaHoy, "vence hoy"));
        }
        TableRowSorter orden = new TableRowSorter(tblVentas.getModel());
        tblVentas.setRowSorter(orden);
        this.ocultarColumnaPrestamos();
        lblRegistros.setText("Mostrando: " + CTRL.totalMostrados() + " registros de " + CTRL.totalReg() + " registros.");
    }
    



    private void crearDetalles() {
        modeloDetalle = new DefaultTableModel() {

            @Override
            public boolean isCellEditable(int fila, int columna) {
                if (columna == 2) {
                    return columna == 2;
                }
                if (columna == 3) {
                    return columna == 3;
                }
                return columna == 2;
            }

//            @Override
//            public Object getValueAt(int row, int col) {
//                if (col == 4) {
//                    double cantD;
//                    try {
//                        cantD = Integer.parseInt((String) getValueAt(row, 2));
//                    } catch (Exception e) {
//                        cantD = 1.0;
//                    }
//                    double precioD = Double.parseDouble((String) getValueAt(row, 3));
//                    if (cantD != 0 && precioD != 0) {
//                        return String.valueOf(cantD * precioD);
//                    } else {
//                        return 0;
//                    }
//
//                } else {
//                    return super.getValueAt(row, col);
//                }
//
//            }

//            @Override
//            public void setValueAt(Object aObjet, int row, int col) {
//                super.setValueAt(aObjet, row, col);
//                calcularTotales();
//                fireTableDataChanged();
//            }
        };

        modeloDetalle.setColumnIdentifiers(new Object[]{"cuota", "fecha a pagar", "fecha pagado", "Valor Cuota", "Interes vencido","valor entregado","ganancia","Estado","cuota Pura"});

        tblDetalle.setModel(modeloDetalle);
    }

    public void agregarDetalle(int cuotaNro, String fechaPagar, String fechaPagado, double cuotaValor,double interes, double valorEntregado,double ganancia,String estado, double cuotaPura) {
//        boolean existe = false;
//        for (int i = 0; i < this.modeloDetalle.getRowCount(); i++) {
//            idT = String.valueOf(this.modeloDetalle.getValueAt(i, 0));
//            if (idT.equals(id)) {
//                existe = true;
//            }
//        }
//        if (existe) {
//            this.mjeError("El articulo ya fue agregado.");
//        } else {
            this.modeloDetalle.addRow(new Object[]{String.valueOf(cuotaNro), fechaPagar, fechaPagado, String.valueOf(cuotaValor), String.valueOf(interes),String.valueOf(valorEntregado),String.valueOf(ganancia),estado,String.valueOf(cuotaPura)});
//            this.calcularTotales();

    }



    private void limpiar() {
        txtCliente.setText("");
        txtNumeroComp.setText("");
        txtctCuotas.setText("");
        txtMonto.setText("");
        txtInteres.setText("");
        txtidCliente.setText("");
        txtNumeroComp.setText("");
        txtMontoTotal.setText("");
        txtMontoApagar.setText("");
        txtMontoPagado.setText("");
        
       this.crearDetalles();
//        

    }
    
    private void obtenerNumero() {
        String numComprobante = this.CTRL.ultimoNumero();
        if (numComprobante.equals("0")){
            txtNumeroComp.setText("0001");
        }else{
            int num;
            num=Integer.parseInt(numComprobante)+1;
            if(num<10){
                String newNum= "000"+String.valueOf(num);
                txtNumeroComp.setText(newNum);
            }else if(num<100){
                String newNum= "00"+String.valueOf(num);
                txtNumeroComp.setText(newNum);
            }else if(num<1000){
                String newNum= "0"+String.valueOf(num);
                txtNumeroComp.setText(newNum);
            }else{
            txtNumeroComp.setText(Integer.toString(num));
            }
        }        
    }
    

    private void mjeError(String mje) {
        JOptionPane.showMessageDialog(this, mje, "Sistema", JOptionPane.ERROR_MESSAGE);
    }

    private void mjeOK(String mje) {
        JOptionPane.showMessageDialog(this, mje, "Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelVentas = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVentas = new javax.swing.JTable();
        lblRegistros = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jToggleButton1 = new javax.swing.JToggleButton();
        btnNuevo = new javax.swing.JButton();
        cboNumPag = new javax.swing.JComboBox<>();
        cboTotalXPag = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnDetalle = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtMes = new javax.swing.JTextField();
        txtYear = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        lblFiltroActual = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtFechaHoy = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNumeroComp = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDetalle = new javax.swing.JTable();
        btnGuardarV = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        txtMonto = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtctCuotas = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        btnPagarCuota = new javax.swing.JButton();
        txtidCliente = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtMontoApagar = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtMontoPagado = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtMontoTotal = new javax.swing.JTextField();
        btnActualizar = new javax.swing.JButton();
        btnFinalizarPrestamo = new javax.swing.JButton();
        txtInteres = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtVencimiento = new javax.swing.JTextField();

        setClosable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Prestamos Mensuales");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/presentacion/images/consultas.png"))); // NOI18N

        panelVentas.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Buscar por cliente");

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });

        btnBuscar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        tblVentas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tblVentas);

        lblRegistros.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblRegistros.setText("Registros");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Agregar nuevo prestamo:");

        jToggleButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jToggleButton1.setText("Anular");
        jToggleButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jToggleButton1ActionPerformed(evt);
            }
        });

        btnNuevo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnNuevo.setText("Nueva");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        cboNumPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNumPagActionPerformed(evt);
            }
        });

        cboTotalXPag.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "25", "45", "65", "85", "105", "125" }));
        cboTotalXPag.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTotalXPagActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Registros por pagina:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel17.setText("N° de paginas:");

        btnDetalle.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnDetalle.setText("Ver detalle");
        btnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel10.setText("Buscar periodo:");

        jButton4.setText("Buscar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("/");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Cancelar prestamo");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton5.setText("Actualizar");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Filtros:");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 0, 51));
        jButton7.setText("Vencidos");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setForeground(new java.awt.Color(0, 0, 204));
        jButton8.setText("Por vencer");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 153, 0));
        jButton9.setText("Vence hoy");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton10.setText("Todo");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        lblFiltroActual.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblFiltroActual.setText("Filtro actual:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Fecha de hoy:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 220, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cboNumPag, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboTotalXPag, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(356, 356, 356)
                        .addComponent(lblRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(88, 88, 88))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(8, 8, 8)
                                .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFechaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFiltroActual)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnBuscar))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jToggleButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jLabel13)
                            .addComponent(jButton7)
                            .addComponent(jButton8)
                            .addComponent(jButton9)
                            .addComponent(jButton10)
                            .addComponent(btnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtFechaHoy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jButton4)
                            .addComponent(txtMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtYear, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblFiltroActual)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRegistros, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboNumPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel16)
                    .addComponent(cboTotalXPag, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelVentas.addTab("Prestamos", jPanel1);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Cliente(*)");

        txtCliente.setEditable(false);
        txtCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtClienteActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("Numero Prestamo(*)");

        tblDetalle.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tblDetalle);

        btnGuardarV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnGuardarV.setText("Guardar");
        btnGuardarV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarVActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Cancelar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("(*) indica que es un campo obligatorio");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Fecha");

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Catidad de cuotas:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel19.setText("Monto prestado:");

        txtctCuotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtctCuotasActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("OK");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        btnPagarCuota.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPagarCuota.setText("Pagar cuota");
        btnPagarCuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagarCuotaActionPerformed(evt);
            }
        });

        txtidCliente.setEditable(false);
        txtidCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtidClienteActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton6.setText("...");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("Monto a pagar:");

        txtMontoApagar.setEditable(false);
        txtMontoApagar.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Monto pagado:");

        txtMontoPagado.setEditable(false);
        txtMontoPagado.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Monto total:");

        txtMontoTotal.setEditable(false);
        txtMontoTotal.setFont(new java.awt.Font("Tahoma", 1, 9)); // NOI18N

        btnActualizar.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        btnActualizar.setText("ACTUALIZAR");
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });

        btnFinalizarPrestamo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnFinalizarPrestamo.setText("Finalizar Prestamo");
        btnFinalizarPrestamo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinalizarPrestamoActionPerformed(evt);
            }
        });

        txtInteres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtInteresActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("interes %:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel11.setText("Vencimiento (dia):");

        txtVencimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtVencimientoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnGuardarV)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnFinalizarPrestamo)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(txtctCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel11))
                                .addGap(24, 24, 24)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtVencimiento, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
                                    .addComponent(txtInteres, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMonto))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                                .addComponent(btnPagarCuota)
                                .addGap(10, 10, 10)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMontoApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumeroComp, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMontoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnActualizar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jScrollPane2)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnActualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(txtNumeroComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12)))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtMontoApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtMontoPagado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtMontoTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnPagarCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtidCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtctCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel19))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(11, 11, 11)
                                        .addComponent(jLabel9))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel11))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(310, 310, 310)))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFinalizarPrestamo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarV)
                    .addComponent(jButton3))
                .addGap(126, 126, 126))
        );

        panelVentas.addTab("Detalles", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelVentas)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelVentas, javax.swing.GroupLayout.PREFERRED_SIZE, 668, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (tblVentas.getSelectedRowCount() == 1) {
            int idPrestamo =Integer.parseInt(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 0)));
            int idCliente =Integer.parseInt(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 1)));
            String cliente= String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 3));
            
            if(CTRL.getEstado(idPrestamo).equals("Finalizado")){
                this.mjeError("El prestamo seleccionado ya se encuentra finalizado.");
            }else{

            FrmCancelarPrestamoM frm = new FrmCancelarPrestamoM(contenedor, this, true,idPrestamo,idCliente,cliente);
            frm.toFront();
            }

        } else {
            this.mjeError("Debe seleccionar un prestamo para cancelarlo.");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
                if(txtMes.getText().isBlank() && txtYear.getText().isBlank()){
                        this.listar("0","0", false);
                        this.ocultarColumnaCuotas();
                        return;
                    }
        
                if(txtYear.getText().isBlank()){
                        this.mjeError("el campo año no puede estar vacio");
                        return;
                    }
                
                if(txtMes.getText().isBlank()){
                        this.listar("0",txtYear.getText(), false);
                        this.ocultarColumnaCuotas();
                        return;
                    }
        
        this.listar(txtMes.getText(),txtYear.getText(), false);
        lblFiltroActual.setText("Filtro Actual: Por periodo");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        if (tblVentas.getSelectedRowCount() == 1) {
            String idPrestamo = String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 0));
            String idCliente = String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 1));
            String cliente= String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 3));
            String comprobante = String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 2));
            String monto = String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 7));
            LocalDate hoy = LocalDate.now();
            String fecha;
            fecha = hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
            this.montoApagarActual=Double.parseDouble(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 8)));

            txtidCliente.setText(idCliente);
            txtCliente.setText(cliente);
            txtNumeroComp.setText(comprobante);
            txtFecha.setText(fecha);
            this.idPrestamoActual=Integer.valueOf(idPrestamo);

            this.modeloDetalle = CTRL.listarDetalle(Integer.parseInt(idPrestamo));
            tblDetalle.setModel(modeloDetalle);
            this.ocultarColumnaCuotas();
            btnPagarCuota.setVisible(true);
            btnActualizar.setVisible(true);
            btnFinalizarPrestamo.setVisible(true);
            txtMonto.setText(monto);
            txtMontoTotal.setText(Double.toString(CTRL.sumarCuotas(Integer.valueOf(idPrestamo))));
            txtMontoApagar.setText(Double.toString(CTRL.sumarCuotasEstado(Integer.valueOf(idPrestamo), "A pagar")));
            txtMontoPagado.setText(Double.toString(CTRL.sumarCuotasEstado(Integer.valueOf(idPrestamo), "Pagado")));

            panelVentas.setEnabledAt(0, false);
            panelVentas.setEnabledAt(1, true);
            panelVentas.setSelectedIndex(1);
            btnGuardarV.setVisible(false);

        } else {
            this.mjeError("Debe seleccionar unna venta para ver el detalle.");
        }
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void cboTotalXPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTotalXPagActionPerformed
        this.paginar();
    }//GEN-LAST:event_cboTotalXPagActionPerformed

    private void cboNumPagActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNumPagActionPerformed
        if (this.primeraCarga == false) {
            this.listar("0","0", true);
        }
    }//GEN-LAST:event_cboNumPagActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        LocalDate hoy = LocalDate.now();
        String fecha;
        fecha = hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        txtFecha.setText(fecha);
        txtInteres.setText("10");
        btnPagarCuota.setVisible(false);
        btnFinalizarPrestamo.setVisible(false);
        btnActualizar.setVisible(false);
        btnGuardarV.setVisible(true);
        panelVentas.setEnabledAt(0, false);
        panelVentas.setEnabledAt(1, true);
        panelVentas.setSelectedIndex(1);
        this.obtenerNumero();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jToggleButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jToggleButton1ActionPerformed
                if (tblVentas.getSelectedRowCount() == 1) {
                        String id = String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 0));
                        int id1 = Integer.parseInt(id);
                        int idCliente = Integer.parseInt(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 1)));
                        double montoPrestado=Double.parseDouble(String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 7)));
                        String nombre = String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 3));
                        String estado = CTRL.getEstado(id1);
                        String fecha =String.valueOf(tblVentas.getValueAt(tblVentas.getSelectedRow(), 4));
                        String[] part=fecha.split("/");
                        String dia=part[0];
                        String mes=part[1];
                        String año=part[2];
                        if(estado.equals("Anulado")){
                                this.mjeError("este prestamo ya fue anulado");
                                return;
                            }
            
                        if (JOptionPane.showConfirmDialog(this, "Deseas anular prestamo: " + nombre + " ?", "Anular", JOptionPane.YES_NO_OPTION) == 0) {
                                String resp = this.CTRL.anular(id1,"Anulado");
                                if (resp.equals("OK")) {
                                    this.CTRLM.setTipo(idCliente,montoPrestado, dia, mes, año, "A");
                                        this.mjeOK("Prestamo anulado");
                                        this.listar("0","0", false);
                                    } else {
                                        this.mjeError(resp);
                                    }
                                
                            }
                    } else {
                        this.mjeError("Seleccione 1 registro a anular.");
                    }
    }//GEN-LAST:event_jToggleButton1ActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        this.listarBuscarCliente(txtBuscar.getText());
        lblFiltroActual.setText("Filtro Actual: Todos");
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.listar("0","0", false);
        lblFiltroActual.setText("Filtro Actual: Todos");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtVencimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtVencimientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtVencimientoActionPerformed

    private void txtInteresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtInteresActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtInteresActionPerformed

    private void btnFinalizarPrestamoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarPrestamoActionPerformed
        int cuotasApagar=CTRL.cantCuotasAPagar(this.idPrestamoActual);
        if(cuotasApagar>0){
            this.mjeError("Faltan pagar " + cuotasApagar +" cuotas para finalizar el prestamo");
            return;
        }
        int ultimaCuota=CTRL.cantCuotas(this.idPrestamoActual);
        String fechaFin=CTRL.getFechaPagadoCuota(this.idPrestamoActual, ultimaCuota);
        CTRL.setFechaFinPrestamoCancelar(fechaFin, this.idPrestamoActual);
        CTRL.setEstadoPrestamo("Finalizado", this.idPrestamoActual);
        CTRL.setProxCuota("1", this.idPrestamoActual);
        this.mjeOK("Prestamo Finalizado!");
        this.listar("0","0", false);
        panelVentas.setEnabledAt(1, false);
        panelVentas.setEnabledAt(0, true);
        panelVentas.setSelectedIndex(0);
    }//GEN-LAST:event_btnFinalizarPrestamoActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        this.modeloDetalle = CTRL.listarDetalle(this.idPrestamoActual);
        tblDetalle.setModel(modeloDetalle);
        this.ocultarColumnaCuotas();

        txtMontoTotal.setText(Double.toString(CTRL.sumarCuotas(this.idPrestamoActual)));
        txtMontoApagar.setText(Double.toString(CTRL.sumarCuotasEstado(this.idPrestamoActual, "A pagar")));
        txtMontoPagado.setText(Double.toString(CTRL.sumarCuotasEstado(this.idPrestamoActual, "Pagado")));
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        FrmSelecClienteM frm = new FrmSelecClienteM(contenedor, this, true);
        frm.toFront();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtidClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtidClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtidClienteActionPerformed

    private void btnPagarCuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagarCuotaActionPerformed
        if (tblDetalle.getSelectedRowCount() == 1) {
            String estado = String.valueOf(tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 8));
            if(estado.equalsIgnoreCase("Pagado")){
                this.mjeError("Esta cuota ya fue Pagada");
                return;
            }else{
                int idCuotas=Integer.valueOf(String.valueOf(tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 0)));
                String cuotaNro= String.valueOf(tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 1));
                String fechaApagar=String.valueOf(tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 2));
                String fechaPago=txtFecha.getText();
                String valorCuota=String.valueOf(tblDetalle.getValueAt(tblDetalle.getSelectedRow(), 4));
                double cuotaValor=Double.valueOf(valorCuota);
                int idCliente=Integer.valueOf(txtidCliente.getText());
                String cliente=txtCliente.getText();
                double interes=CTRL.getInteres(this.idPrestamoActual);

                FrmPagarCuotaM frm = new FrmPagarCuotaM(contenedor, this, true,idCuotas,this.idPrestamoActual,cuotaNro,fechaApagar,fechaPago,cliente,cuotaValor,idCliente,interes);
                frm.toFront();
            }
        }
    }//GEN-LAST:event_btnPagarCuotaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if(txtctCuotas.getText().isBlank()){
                this.mjeError("Ingresar cantidad de cuotas");
                return;
            }
        if(txtMonto.getText().isBlank()){
                this.mjeError("Ingresar monto a prestar");
                return;
            }
        if(txtInteres.getText().isBlank()){
                this.mjeError("Ingresar interes");
                return;
            }
        if(txtVencimiento.getText().isBlank()){
                this.mjeError("Ingresar dia de vencimiento");
                return;
            }
        
        
        int cantFilas=tblDetalle.getRowCount();
        if(cantFilas>0){
            for (int i = cantFilas-1; i >= 0; i--) {
                this.modeloDetalle.removeRow(i);
            }

        }
        String fechaVenc= txtVencimiento.getText();
        int ctCuotas=Integer.valueOf(txtctCuotas.getText());
        double montoC=Double.valueOf(txtMonto.getText());
        List<Double>lista= CTRL.calcularCuota(ctCuotas, montoC,Double.parseDouble(txtInteres.getText()));
        double ganancia=lista.get(0);
        double valorCuota=Variables.roundUp(lista.get(1),100);
        double valorApagar=Variables.roundUp(valorCuota*ctCuotas,100);
        txtMontoApagar.setText(String.valueOf(valorApagar));
        String fecha=txtFecha.getText();
        for (int i = 1; i <= ctCuotas; i++) {
            fecha=CTRL.calcularFechaCuota(fecha,fechaVenc);
            this.agregarDetalle(i,fecha , "-", valorCuota,0,0,ganancia, "a pagar",lista.get(2));
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtctCuotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtctCuotasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtctCuotasActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.limpiar();
        panelVentas.setEnabledAt(1, false);
        panelVentas.setEnabledAt(0, true);
        panelVentas.setSelectedIndex(0);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void btnGuardarVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarVActionPerformed
        if (txtidCliente.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un cliente", "sistema", JOptionPane.WARNING_MESSAGE);
            txtidCliente.requestFocus();
            return;
        }

        if (txtInteres.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Debe ingresar un porcentaje valido", "sistema", JOptionPane.WARNING_MESSAGE);
            txtInteres.requestFocus();
            return;
        }

        if (txtNumeroComp.getText().length() == 0 || txtNumeroComp.getText().length() > 10) {
            JOptionPane.showMessageDialog(this, "Debe ingresar numero de comprobante menor a 10 caracteres", "sistema", JOptionPane.WARNING_MESSAGE);
            txtNumeroComp.requestFocus();
            return;
        }
        if (txtFecha.getText().length() == 0 || txtFecha.getText().length() > 8) {
            JOptionPane.showMessageDialog(this, "debe ingresar una fecha valida con el siguiente formato: DD/M/AA o DD/MM/AA", "sistema", JOptionPane.WARNING_MESSAGE);
            txtNumeroComp.requestFocus();
            return;
        }
        if (modeloDetalle.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "no hay cuotas generadas", "sistema", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String proxCuota = String.valueOf(tblDetalle.getValueAt(0, 1));
        String[] partP=proxCuota.split("/");
        String diaP=partP[0];
        if(diaP.length()<2){
            diaP="0"+diaP;
        }
        String mesP=partP[1];
        if(mesP.length()<2){
            mesP="0"+mesP;
        }
        String añoP=partP[2];
        int proxCuotaF=Integer.parseInt(añoP+mesP+diaP);
        String fecha = txtFecha.getText();
        String[] part=fecha.split("/");
        String dia=part[0];
        String mes=part[1];
        String año=part[2];
        String resp;
        String resp2;

        resp = this.CTRL.insertar( Integer.parseInt(txtidCliente.getText()), txtCliente.getText(),txtNumeroComp.getText(), dia,mes,año,Double.parseDouble(txtMonto.getText()),Integer.parseInt(txtctCuotas.getText()),Double.parseDouble(txtMontoApagar.getText()),"Activo",Double.parseDouble(txtInteres.getText()), proxCuotaF,modeloDetalle);
        if (resp.equals("OK")) {
            resp2=this.CTRLM.insertar(Integer.parseInt(txtidCliente.getText()),txtCliente.getText(),"Credito generado",Double.parseDouble(txtMonto.getText()),Double.parseDouble(txtMontoApagar.getText()) , dia,mes,año, "S");
            if(resp2.equals("OK")){
                this.mjeOK("El registro se guardo correctamente");
                this.limpiar();
                panelVentas.setEnabledAt(1, false);
                panelVentas.setEnabledAt(0, true);
                panelVentas.setSelectedIndex(0);
                this.listar("0","0", false);
            }else{
                this.mjeError("no se pudo guardar el movimiento");
            }
        } else {
            this.mjeError(resp);

        }
    }//GEN-LAST:event_btnGuardarVActionPerformed

    private void txtClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtClienteActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        this.listarVencimientos("vencido");
        lblFiltroActual.setText("Filtro Actual: Vencidos");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        this.listarVencimientos("vence hoy");
        lblFiltroActual.setText("Filtro Actual: Vence hoy");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        this.listarVencimientos("por vencer");
        lblFiltroActual.setText("Filtro Actual: Vence pronto");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        this.listar("0","0", false);
        lblFiltroActual.setText("Filtro Actual: Todos");
    }//GEN-LAST:event_jButton10ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnDetalle;
    private javax.swing.JButton btnFinalizarPrestamo;
    private javax.swing.JButton btnGuardarV;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnPagarCuota;
    private javax.swing.JComboBox<String> cboNumPag;
    private javax.swing.JComboBox<String> cboTotalXPag;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JLabel lblFiltroActual;
    private javax.swing.JLabel lblRegistros;
    private javax.swing.JTabbedPane panelVentas;
    public javax.swing.JTable tblDetalle;
    private javax.swing.JTable tblVentas;
    private javax.swing.JTextField txtBuscar;
    public javax.swing.JTextField txtCliente;
    public javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFechaHoy;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtMes;
    private javax.swing.JTextField txtMonto;
    private javax.swing.JTextField txtMontoApagar;
    private javax.swing.JTextField txtMontoPagado;
    private javax.swing.JTextField txtMontoTotal;
    private javax.swing.JTextField txtNumeroComp;
    private javax.swing.JTextField txtVencimiento;
    private javax.swing.JTextField txtYear;
    private javax.swing.JTextField txtctCuotas;
    public javax.swing.JTextField txtidCliente;
    // End of variables declaration//GEN-END:variables
}
