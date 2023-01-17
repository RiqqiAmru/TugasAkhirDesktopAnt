/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main;

import config.CRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Riqqi
 */
public class PanelTransJual extends javax.swing.JPanel {

   /**
    * Creates new form PanelTransJual
    */
   CRUD crud;
   DefaultTableModel dt;
   DefaultListModel list;
   boolean success[] = new boolean[2];
   long bayar;
   long nominal;
   String namaPelanggan;
   int idAkun;
   DefaultComboBoxModel cb;

   public PanelTransJual(int idAkun, CRUD crud) {
      this.idAkun = idAkun;
      initComponents();
      this.crud = crud;
      refreshTableTransJual();
   }

   //   --FUNCTION TRANSJUAL--
   public void refreshTableTransJual() {
      dt = (DefaultTableModel) tabelTransJual.getModel();
      ResultSet transJual = crud.ambilData("SELECT trans_jual.no_trans_jual, trans_jual.no_pln, pelanggan.nama AS pelanggan, trans_jual.harga, trans_jual.bayar, trans_jual.id_akun, trans_jual.tgl, akun.nama AS admin FROM `trans_jual` INNER JOIN akun ON trans_jual.id_akun = akun.id_akun INNER JOIN pelanggan ON trans_jual.no_pln = pelanggan.no_pln ORDER BY tgl DESC;");
      try {
         int no = 1;
         dt.getDataVector().clear();
         dt.fireTableDataChanged();
         while (transJual.next()) {
            String tglOke = crud.dateToTgl(transJual.getString("tgl"), "default");
            String noPln = transJual.getString("no_pln");
            String pelanggan = transJual.getString("pelanggan");
            String nominal = crud.integerToRupiah(Integer.parseInt(transJual.getString("harga")));
            String bayar = crud.integerToRupiah(Integer.parseInt(transJual.getString("bayar")));
            String admin = transJual.getString("admin");
            dt.addRow(new Object[]{no++, tglOke, noPln + " (" + pelanggan + ")", nominal, bayar, admin});
         }
         tabelTransJual.setModel(dt);
         reset();
      } catch (SQLException ex) {
         Logger.getLogger(PanelTransJual.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void reset() {
      cbNominal.setSelectedIndex(0);
      cbPelanggan.setSelectedIndex(0);
      Arrays.fill(success, false);
      crud.validasiAND(success, bTambah);
      crud.comboDefault(cbNominal, "Nominal");
      crud.comboDefault(cbPelanggan, "Pelanggan");
      lblBayar.setText("Bayar : ");
      lblNama.setText("Nama  : ");
   }

   public void insertListPelanggan() {
      ResultSet pelanggan = crud.ambilData("SELECT * FROM pelanggan");
      cb = new DefaultComboBoxModel<String>();
      try {
         cb.addElement("---pilih---");
         while (pelanggan.next()) {
            cb.addElement(pelanggan.getString("no_pln"));
         }
         cbPelanggan.setModel(cb);
      } catch (SQLException ex) {
         Logger.getLogger(PanelTransJual.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void insertListNominal() {
      ResultSet nominal = crud.ambilData("SELECT * FROM token_listrik");
      cb = new DefaultComboBoxModel<String>();
      try {
         cb.addElement("---pilih---");
         while (nominal.next()) {
            String nominalRp = crud.integerToRupiah(Integer.parseInt(nominal.getString("harga")));
            cb.addElement(nominalRp);
         }
         cbNominal.setModel(cb);
      } catch (SQLException ex) {
         Logger.getLogger(PanelTransJual.class.getName()).log(Level.SEVERE, null, ex);
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

        cbPelanggan = new javax.swing.JComboBox();
        lblBayar = new javax.swing.JLabel();
        bReset = new javax.swing.JButton();
        bTambah = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelTransJual = new javax.swing.JTable();
        cbNominal = new javax.swing.JComboBox();
        lblNama = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        cbPelanggan.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---PILIH---" }));
        cbPelanggan.setBorder(javax.swing.BorderFactory.createTitledBorder("No PLN"));
        cbPelanggan.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbPelangganFocusLost(evt);
            }
        });
        cbPelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbPelangganActionPerformed(evt);
            }
        });

        lblBayar.setText("Bayar :");

        bReset.setText("Reset");
        bReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bResetActionPerformed(evt);
            }
        });

        bTambah.setText("Tambah");
        bTambah.setEnabled(false);
        bTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTambahActionPerformed(evt);
            }
        });

        tabelTransJual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "No", "Tanggal", "No_PLN", "Nominal", "Bayar", "Akun"
            }
        ));
        jScrollPane2.setViewportView(tabelTransJual);

        cbNominal.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "---PILIH---" }));
        cbNominal.setBorder(javax.swing.BorderFactory.createTitledBorder("Nominal"));
        cbNominal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                cbNominalFocusLost(evt);
            }
        });
        cbNominal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNominalActionPerformed(evt);
            }
        });

        lblNama.setText("Nama : ");

        lblSaldo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSaldo.setText("Saldo : Rp--");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(bReset)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(bTambah))
                        .addComponent(cbNominal, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblBayar)
                    .addComponent(lblNama)
                    .addComponent(lblSaldo))
                .addGap(35, 35, 35)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 840, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbPelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(lblNama)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbNominal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblBayar)
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(bReset)
                            .addComponent(bTambah))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
   private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
      refreshTableTransJual();
      crud.refreshSaldo(idAkun, lblSaldo);
      insertListPelanggan();
      insertListNominal();
   }//GEN-LAST:event_formComponentShown

   private void cbPelangganFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbPelangganFocusLost
      if (cbPelanggan.getSelectedIndex() == 0) {
         success[0] = false;
         crud.comboDefault(cbPelanggan, "No PLN");
         lblNama.setText("Nama : ");
      } else {
         success[0] = true;
         crud.comboSuccess(cbPelanggan, "No PLN");
         ResultSet pelanggan = crud.ambilData("SELECT nama FROM pelanggan WHERE no_pln = '" + cbPelanggan.getSelectedItem().toString() + "'");
         try {
            if (pelanggan.first()) {
               namaPelanggan = pelanggan.getString("nama");
            }
         } catch (SQLException ex) {
            Logger.getLogger(PanelTransJual.class.getName()).log(Level.SEVERE, null, ex);
         }
         lblNama.setText("Nama : " + namaPelanggan);
      }
      crud.validasiAND(success, bTambah);
   }//GEN-LAST:event_cbPelangganFocusLost

   private void cbNominalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cbNominalFocusLost
      if (cbNominal.getSelectedIndex() == 0) {
         success[1] = false;
         crud.comboDefault(cbNominal, "Nominal");
         lblBayar.setText("Bayar  : ");
      } else {
         success[1] = true;
         crud.comboSuccess(cbNominal, "Nominal");
         nominal = (long) crud.rupiahToNumber(cbNominal.getSelectedItem().toString());
         bayar = nominal + 3000;
         lblBayar.setText("Bayar : " + crud.integerToRupiah((int) bayar));
      }
      crud.validasiAND(success, bTambah);
   }//GEN-LAST:event_cbNominalFocusLost

   private void bResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bResetActionPerformed
      reset();
   }//GEN-LAST:event_bResetActionPerformed

   private void bTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTambahActionPerformed
      try {

         String noPLN = cbPelanggan.getSelectedItem().toString();
         crud.insertData("INSERT INTO `trans_jual`( `no_pln`, `harga`, `bayar`, `id_akun`) VALUES ('" + noPLN + "','" + nominal + "','" + bayar + "','" + idAkun + "')", "Transaksi Berhasil");
         reset();
         refreshTableTransJual();
         crud.refreshSaldo(idAkun, lblSaldo);
      } catch (Exception e) {
         Logger.getLogger(PanelTransJual.class.getName()).log(Level.SEVERE, null, e);
      }
   }//GEN-LAST:event_bTambahActionPerformed

    private void cbPelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbPelangganActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_cbPelangganActionPerformed

    private void cbNominalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNominalActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_cbNominalActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bReset;
    private javax.swing.JButton bTambah;
    private javax.swing.JComboBox cbNominal;
    private javax.swing.JComboBox cbPelanggan;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblBayar;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblSaldo;
    private javax.swing.JTable tabelTransJual;
    // End of variables declaration//GEN-END:variables
}
