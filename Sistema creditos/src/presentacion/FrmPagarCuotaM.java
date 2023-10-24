/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package presentacion;

import entidades.PrestamoMensual;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;
import negocio.*;

/**
 *
 * @author Gabriel Lossada
 */
public class FrmPagarCuotaM extends javax.swing.JDialog {

    public FrmPrestamosM vista;
    private final PrestamoControl CTRL;
    private final MovimientosControl CTRLM;
    private String nomAnt;
    private int cuotaNro;
    private String fechaApagar;
    private String fechaPago;
    private String cliente;
    private double valorCuota;
    private double interesMonto;
    private double total;
    private int idCliente;
    private int idPrestamo;
    private int idCuota;
    private double interesPorcentaje;
    //paginacion

    public FrmPagarCuotaM(java.awt.Frame parent, FrmPrestamosM frm, boolean modal, int idCuota, int idPrestamo, String cuotaNro, String fechaApagar, String fechaPago, String cliente, double valorCuota, int idCliente,double interesPorcentaje) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null);
        this.vista = frm;
        this.setTitle("Pagar cuota");
        this.CTRL = new PrestamoControl();
        this.CTRLM = new MovimientosControl();
        this.cuotaNro = Integer.parseInt(cuotaNro);
        this.fechaApagar = fechaApagar;
        this.fechaPago = fechaPago;
        this.cliente = cliente;
        this.valorCuota = valorCuota;
        this.idCliente = idCliente;
        this.idPrestamo = idPrestamo;
        this.idCuota = idCuota;
        this.interesPorcentaje=interesPorcentaje;
        if(this.interesPorcentaje<1){
            this.interesPorcentaje=11;
        }
        this.interesMonto = this.calcularInteres(this.fechaApagar, this.fechaPago, valorCuota);
        this.total = this.valorCuota + this.interesMonto;
       
        this.cargarCampos();

        this.setVisible(true);
    }

    private void cargarCampos() {
        
        String cliente = this.cliente;
        lblCliente.setText(cliente);
        txtCuotaNro.setText(String.valueOf(this.cuotaNro));
        txtFechaApagar.setText(this.fechaApagar);
        txtFechaPago.setText(this.fechaPago);
        txtValorCuota.setText(String.valueOf(this.valorCuota));
        txtInteres.setText(String.valueOf(this.interesMonto));
        txtTotal.setText(String.valueOf(this.total));
        txtEntrega.setText(String.valueOf(this.valorCuota));
    }

    private double calcularInteres(String fechaApagar, String fechaPago, double valorCuota) {
        double interes;
        int fechaAP;
        int fechaP;
        
        String[] part = fechaApagar.split("/");
        String diaAP = part[0];
        if(diaAP.length()<2){
            diaAP="0"+diaAP;
        }
        String mesAP = part[1];
        if(mesAP.length()<2){
            mesAP="0"+mesAP;
        }
        String añoAP = part[2];
        fechaAP=Integer.parseInt(añoAP+mesAP+diaAP);
        String[] partP = fechaPago.split("/");
        String diaP = partP[0];
        if(diaP.length()<2){
            diaP="0"+diaP;
        }
        String mesP = partP[1];
        if(mesP.length()<2){
            mesP="0"+mesP;
        }
        String añoP = partP[2];
        fechaP=Integer.parseInt(añoP+mesP+diaP);
        if (fechaP<=fechaAP) {
            interes = 0;

        } else {
            interes = valorCuota * (this.interesPorcentaje/100);
        }
        return Variables.roundUp(interes,100);
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

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtCuotaNro = new javax.swing.JTextField();
        txtFechaApagar = new javax.swing.JTextField();
        txtFechaPago = new javax.swing.JTextField();
        txtValorCuota = new javax.swing.JTextField();
        txtInteres = new javax.swing.JTextField();
        txtTotal = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtEntrega = new javax.swing.JTextField();
        lblCliente = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txtDebe = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 229, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Cuota Nro:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Cliente:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("fecha a pagar:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setText("fecha de pago:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setText("Valor cuota:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Interes:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("TOTAL:");

        txtCuotaNro.setEditable(false);

        txtFechaApagar.setEditable(false);

        txtInteres.setEditable(false);

        txtTotal.setEditable(false);
        txtTotal.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("Cliente entrega:");

        txtEntrega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEntregaActionPerformed(evt);
            }
        });

        lblCliente.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblCliente.setText("hacelo cuevas");

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Pagar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtDebe.setEditable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("Deuda:");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("->");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtCuotaNro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtValorCuota, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton2)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(2, 2, 2)
                                .addComponent(txtInteres))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaApagar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lblCliente))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(txtCuotaNro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaApagar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(txtValorCuota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtInteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3)
                    .addComponent(txtDebe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        double totalApagar = Double.parseDouble(txtTotal.getText());
        double entrega = Double.parseDouble(txtEntrega.getText());
        double debe = totalApagar - entrega;
        txtDebe.setText(String.valueOf(debe));
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtEntregaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEntregaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEntregaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (txtDebe.getText().isBlank()) {
            this.mjeError("Debe pagar primero");
            return;
        }

        if (Double.parseDouble(txtDebe.getText()) < 0) {
            this.mjeError("La deuda del usuario no puede ser negativa");
            return;
        }

        double valorcutaSiguiente;
        double nuevoValorCuota;
        double debe = Double.valueOf(txtDebe.getText());
        double entrega = Double.valueOf(txtEntrega.getText());
        double ganancia;
        double cuotaPura = CTRL.getCuotaPura(this.idCuota);
        String fechaPago = txtFechaPago.getText();
        String fechaCuotaProx=CTRL.getFechaCuota(idPrestamo, cuotaNro+1);
        String[] part = fechaPago.split("/");
        String dia = part[0];
        String mes = part[1];
        String año = part[2];
        double interes = CTRL.getInteres(idPrestamo);
        
        if(fechaCuotaProx.equals("nada")){
            fechaCuotaProx="-";
        }
        
        if(interes==0){
            interes = 11;
        }

        if (Double.parseDouble(txtEntrega.getText()) <= cuotaPura) {
            ganancia = 0;
        } else {
            ganancia = Double.parseDouble(txtEntrega.getText()) - cuotaPura;
        }

        if (debe <= 0) {
            CTRL.estadoCuota(this.idCuota);
            this.setVisible(false);
            CTRL.FechaPagadoCuota(fechaPago, idCuota);
            String resp = CTRL.setGanancia(this.idPrestamo, this.cuotaNro, ganancia);
            String resp2=CTRL.setProxCuota(fechaCuotaProx, idPrestamo);
            CTRLM.insertar(this.idCliente, this.cliente, "Cuota pagada", entrega, ganancia, dia, mes, año, "E");
            if (resp.equals("OK") && resp2.equals("OK")) {
                this.mjeOK("Cuota pagada en totalidad");
                this.setVisible(false);
            } else {
                this.mjeError("se rompio todo");
            }

        }
        if (debe > 0) {
            valorcutaSiguiente = CTRL.valorCuota(this.idPrestamo, Integer.valueOf(txtCuotaNro.getText()) + 1);
            if (valorcutaSiguiente > 0) {
                CTRL.editarCuotaValor(entrega, this.idPrestamo, Integer.valueOf(txtCuotaNro.getText()));
                CTRL.estadoCuota(this.idCuota);
                CTRL.FechaPagadoCuota(fechaPago, idCuota);
                nuevoValorCuota = valorcutaSiguiente + (Variables.roundUp(debe * (((interes)/100)+1),100));
                CTRL.editarCuotaValor(nuevoValorCuota, this.idPrestamo, Integer.valueOf(txtCuotaNro.getText()) + 1);
                if (Double.parseDouble(txtEntrega.getText()) < cuotaPura) {
                    CTRL.setCuotaPura(this.idPrestamo, this.cuotaNro+1, CTRL.getCuotaPuraCuotaNo(this.idPrestamo, this.cuotaNro+1)+cuotaPura );
                }
                CTRLM.insertar(this.idCliente, this.cliente, "Cuota pagada", entrega, ganancia, dia, mes, año, "E");
                String resp = CTRL.setTotalPagar(this.idPrestamo, CTRL.sumarCuotas(this.idPrestamo));
                String resp2 = CTRL.setGanancia(this.idPrestamo, this.cuotaNro, ganancia);
                String resp3=CTRL.setProxCuota(fechaCuotaProx, idPrestamo);
                if (resp.equals("OK") && resp2.equals("OK")  && resp3.equals("OK")) {
                    this.mjeOK("se sumo deuda a cuota la siguiente");
                    this.setVisible(false);
                } else {
                    this.mjeError("error al agregar nueva cuota");
                }
            } else {
                CTRL.editarCuotaValor(entrega, this.idPrestamo, Integer.valueOf(txtCuotaNro.getText()));
                CTRL.estadoCuota(this.idCuota);
                CTRL.FechaPagadoCuota(fechaPago, idCuota);
                CTRLM.insertar(this.idCliente, this.cliente, "Cuota Pagada", entrega, ganancia, dia, mes, año, "E");
                String resp2 = CTRL.setTotalPagar(this.idPrestamo, CTRL.sumarCuotas(this.idPrestamo));
                       part = fechaApagar.split("/");
                String diaAP = part[0];
                String resp = CTRL.insertarNuevaCuota(this.idPrestamo, Integer.valueOf(txtCuotaNro.getText()) + 1, CTRL.calcularFechaCuota(txtFechaPago.getText(),diaAP), Variables.roundUp(debe * ((interes/100)+1),100), 0, Variables.roundUp(debe * (interes/100),100), "a pagar");
                String resp3 = CTRL.setGanancia(this.idPrestamo, this.cuotaNro, ganancia);
                String resp4=CTRL.setProxCuota(fechaCuotaProx, idPrestamo);
                if (resp.equals("OK") && resp2.equals("OK") && resp3.equals("OK") && resp4.equals("OK")) {
                    this.mjeOK("se agrego una nueva cuota!");
                    this.setVisible(false);
                } else {
                    this.mjeError("error al agregar nueva cuota");
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        double valorCuota=Double.parseDouble(txtValorCuota.getText());
        double interes=Double.parseDouble(txtInteres.getText());
        double total=valorCuota+interes;
        txtTotal.setText(String.valueOf(total));
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JTextField txtCuotaNro;
    private javax.swing.JTextField txtDebe;
    private javax.swing.JTextField txtEntrega;
    private javax.swing.JTextField txtFechaApagar;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtInteres;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtValorCuota;
    // End of variables declaration//GEN-END:variables
}
