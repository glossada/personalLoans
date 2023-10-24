/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/MDIApplication.java to edit this template
 */
package presentacion;

import java.awt.Color;
import java.awt.Graphics;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import negocio.PrestamoControl;
import negocio.PrestamoControlQuincenal;

/**
 *
 * @author Gabriel Lossada
 */
public class FrmPrincipal extends javax.swing.JFrame {

    private final PrestamoControl CTRLM;
    private final PrestamoControlQuincenal CTRLQ;
    private String FechaHoy;
    LocalDate hoy;
    /**
     * Creates new form FrmPrincipal
     */
    
    public FrmPrincipal() {
        initComponents();
        this.CTRLM = new PrestamoControl();
        this.CTRLQ = new PrestamoControlQuincenal();
        this.hoy = LocalDate.now();
        this.FechaHoy=hoy.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT));
        setExtendedState(MAXIMIZED_BOTH);
        this.setTitle("Pc Project Store");
        this.resumen();
        
    }
    
    public void resumen(){
        int vencidosM = CTRLM.vencimientosCantidad(this.FechaHoy, "vencido");
        int porvencerM= CTRLM.vencimientosCantidad(this.FechaHoy, "por vencer");
        int venceHoyM= CTRLM.vencimientosCantidad(this.FechaHoy, "pitos");
        
        int vencidosQ = CTRLQ.vencimientosCantidad(this.FechaHoy, "vencido");
        int porvencerQ= CTRLQ.vencimientosCantidad(this.FechaHoy, "por vencer");
        int venceHoyQ= CTRLQ.vencimientosCantidad(this.FechaHoy, "pitos");
        
        
        
        FrmResumen frm= new FrmResumen(vencidosM,porvencerM,venceHoyM,vencidosQ,porvencerQ,venceHoyQ);
        escritorio.add(frm);
        frm.setVisible(true);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jTextField1 = new javax.swing.JTextField();
        escritorio = new javax.swing.JDesktopPane(){
            @Override
            protected void paintComponent (Graphics g){
                super.paintComponent(g);
                g.setColor(Color.white);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        menuBar = new javax.swing.JMenuBar();
        MnuComp = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        MnuInv = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        MnuSalir = new javax.swing.JMenu();

        jMenu2.setText("File");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar1.add(jMenu3);

        jTextField1.setText("jTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        escritorio.setBackground(new java.awt.Color(102, 102, 102));

        menuBar.setMinimumSize(new java.awt.Dimension(269, 40));
        menuBar.setPreferredSize(new java.awt.Dimension(269, 40));

        MnuComp.setIcon(new javax.swing.ImageIcon("C:\\ProyectoJava\\Sistema\\iconos\\signo-de-dolar.png")); // NOI18N
        MnuComp.setText("Prestamos");
        MnuComp.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MnuComp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MnuCompMouseClicked(evt);
            }
        });
        MnuComp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnuCompActionPerformed(evt);
            }
        });

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        jMenuItem1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem1.setText("Prestamos Mensuales");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MnuComp.add(jMenuItem1);

        jMenuItem3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem3.setText("Prestamos Quincenales");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        MnuComp.add(jMenuItem3);

        jMenuItem7.setText("Resumen Prestamos");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        MnuComp.add(jMenuItem7);

        menuBar.add(MnuComp);

        MnuInv.setIcon(new javax.swing.ImageIcon("C:\\ProyectoJava\\Sistema\\iconos\\beneficios.png")); // NOI18N
        MnuInv.setText("Movimientos");
        MnuInv.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem5.setText("Balance general");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        MnuInv.add(jMenuItem5);

        jMenuItem6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem6.setText("Balance diario");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        MnuInv.add(jMenuItem6);

        menuBar.add(MnuInv);

        jMenu1.setIcon(new javax.swing.ImageIcon("C:\\ProyectoJava\\Sistema\\iconos\\account.png")); // NOI18N
        jMenu1.setText("Persona");
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N

        jMenuItem4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem4.setText("Administrar clientes");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuItem2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jMenuItem2.setText("Administrar Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        menuBar.add(jMenu1);

        MnuSalir.setIcon(new javax.swing.ImageIcon("C:\\ProyectoJava\\Sistema\\iconos\\salida-de-emergencia.png")); // NOI18N
        MnuSalir.setText("SALIR");
        MnuSalir.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        MnuSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MnuSalirMouseClicked(evt);
            }
        });
        MnuSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnuSalirActionPerformed(evt);
            }
        });
        menuBar.add(MnuSalir);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(escritorio, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        FrmCliente frm = new FrmCliente();
        escritorio.add(frm);
        frm.setVisible(true);      
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void MnuSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnuSalirActionPerformed
        //5
    }//GEN-LAST:event_MnuSalirActionPerformed

    private void MnuSalirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MnuSalirMouseClicked
        System.exit(0);
    }//GEN-LAST:event_MnuSalirMouseClicked

    private void MnuCompActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnuCompActionPerformed
        //
    }//GEN-LAST:event_MnuCompActionPerformed

    private void MnuCompMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MnuCompMouseClicked
        
    }//GEN-LAST:event_MnuCompMouseClicked

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        FrmPrestamosM frm = new FrmPrestamosM(this,"t");
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        FrmUsuario frm= new FrmUsuario();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        FrmPrestamosQ frm = new FrmPrestamosQ(this, "t");
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        FrmBalancePeriodo frm= new FrmBalancePeriodo();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        FrmBalanceDiario frm= new FrmBalanceDiario();
        escritorio.add(frm);
        frm.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        this.resumen();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MnuComp;
    private javax.swing.JMenu MnuInv;
    private javax.swing.JMenu MnuSalir;
    public static javax.swing.JDesktopPane escritorio;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables

}