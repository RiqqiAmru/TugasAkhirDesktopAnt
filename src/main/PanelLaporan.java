/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package main;

import config.CRUD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author riqqi
 */
public class PanelLaporan extends javax.swing.JPanel {

   CRUD crud;
   int idAkun;
   String namaAkunTerpilih;
   DefaultComboBoxModel cb;
   DefaultTableModel dt;

   /**
    * Creates new form PanelLaporan
    */
   public PanelLaporan(int idAkun, CRUD crud) {
      this.crud = crud;
      this.idAkun = idAkun;
      initComponents();
      insertListAkun();
   }

   public final String namaPertama() {
      ResultSet nama = crud.ambilData("SELECT * FROM akun WHERE id_akun = " + idAkun);
      try {
         if (nama.next()) {
            return nama.getString("nama");
         }
      } catch (SQLException ex) {
         Logger.getLogger(PanelLaporan.class.getName()).log(Level.SEVERE, null, ex);
      }
      return null;
   }

   public void insertListAkun() {
      ResultSet akun = crud.ambilData("SELECT * FROM akun");
      cb = new DefaultComboBoxModel<String>();
      try {
         while (akun.next()) {
            cb.addElement(akun.getString("nama"));
         }
         cbAkun.setModel(cb);
         cbAkun.setSelectedItem(namaPertama());
         namaAkunTerpilih = cbAkun.getSelectedItem().toString();
      } catch (SQLException ex) {
         Logger.getLogger(PanelLaporan.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void refreshTablePenjualan() {
      dt = (DefaultTableModel) tabelPenjualan.getModel();
      ResultSet penjualan = crud.ambilData("SELECT time(trans_jual.tgl) AS jam, pelanggan.nama AS pelanggan, trans_jual.harga AS nominal FROM `trans_jual` INNER JOIN pelanggan ON pelanggan.no_pln = trans_jual.no_pln INNER JOIN akun ON akun.id_akun= trans_jual.id_akun WHERE day(trans_jual.tgl) = day(now()) AND akun.nama = '" + namaAkunTerpilih + "'");
      try {
         dt.getDataVector().clear();
         dt.fireTableDataChanged();
         while (penjualan.next()) {
            String jam = penjualan.getString("jam");
            String pelanggan = penjualan.getString("pelanggan");
            String nominal = crud.integerToRupiah(Integer.parseInt(penjualan.getString("nominal")));
            dt.addRow(new Object[]{jam, pelanggan, nominal});
         }
         tabelPenjualan.setModel(dt);
      } catch (SQLException ex) {
         Logger.getLogger(PanelLaporan.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void refreshTableLog() {
      dt = (DefaultTableModel) tabelLog.getModel();
      ResultSet logSaldo = crud.ambilData("SELECT log_saldo.tgl, log_saldo.transaksi, log_saldo.ket, log_saldo.saldo FROM `log_saldo` INNER JOIN akun ON akun.id_akun = log_saldo.id_akun WHERE akun.nama = '" + namaAkunTerpilih + "'");
      try {
         dt.getDataVector().clear();
         dt.fireTableDataChanged();
         while (logSaldo.next()) {
            String tglOke = crud.dateToTgl(logSaldo.getString("tgl"));
            String ket = logSaldo.getString("ket");
            String transaksi = crud.integerToRupiah(Integer.parseInt(logSaldo.getString("transaksi")));
            String saldo = crud.integerToRupiah(Integer.parseInt(logSaldo.getString("Saldo")));
            dt.addRow(new Object[]{tglOke, transaksi, saldo, ket});
         }
         tabelLog.setModel(dt);
      } catch (SQLException ex) {
         Logger.getLogger(PanelLaporan.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   public void refreshTotal() {
      ResultSet total = crud.ambilData("SELECT COUNT(no_trans_jual) AS banyak_pelanggan, SUM(harga) AS total_nominal FROM `trans_jual` INNER JOIN akun ON akun.id_akun= trans_jual.id_akun WHERE day(trans_jual.tgl) = day(now()) AND akun.nama = '" + namaAkunTerpilih + "'");
      lblBanyakPelanggan.setText("Banyak Pelanggan = -");
      lblTotalNominal.setText("Total Nominal = -");
      lblLaba.setText("Laba Hari ini = -");
      try {
         if (total.next()) {
            String totalNominal = total.getString("total_nominal");
            if (totalNominal != null) {
               int banyakPelanggan = Integer.parseInt(total.getString("banyak_pelanggan"));
               totalNominal = crud.integerToRupiah(Integer.parseInt(total.getString("total_nominal")));
               lblTotalNominal.setText("Total Nominal = " + totalNominal);
               lblBanyakPelanggan.setText("Banyak Pelanggan = " + banyakPelanggan);
               int laba = banyakPelanggan * 2900;
               lblLaba.setText("Laba Hari Ini = " + crud.integerToRupiah(laba));
            }

         }
      } catch (NullPointerException | SQLException ex) {
         Logger.getLogger(PanelLaporan.class.getName()).log(Level.SEVERE, null, ex);
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

      cbAkun = new javax.swing.JComboBox();
      jScrollPane1 = new javax.swing.JScrollPane();
      tabelLog = new javax.swing.JTable();
      jLabel1 = new javax.swing.JLabel();
      jScrollPane2 = new javax.swing.JScrollPane();
      tabelPenjualan = new javax.swing.JTable();
      jLabel2 = new javax.swing.JLabel();
      lblBanyakPelanggan = new javax.swing.JLabel();
      lblTotalNominal = new javax.swing.JLabel();
      lblTotalNominal1 = new javax.swing.JLabel();
      lblTotalNominal2 = new javax.swing.JLabel();
      lblTotalNominal3 = new javax.swing.JLabel();
      lblLaba = new javax.swing.JLabel();

      addComponentListener(new java.awt.event.ComponentAdapter() {
         public void componentShown(java.awt.event.ComponentEvent evt) {
            formComponentShown(evt);
         }
      });

      cbAkun.setBorder(javax.swing.BorderFactory.createTitledBorder("Akun"));
      cbAkun.addActionListener(new java.awt.event.ActionListener() {
         public void actionPerformed(java.awt.event.ActionEvent evt) {
            cbAkunActionPerformed(evt);
         }
      });

      tabelLog.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {
            "Tanggal", "Transaksi", "Saldo", "Keterangan"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
         };

         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }
      });
      tabelLog.setToolTipText("Penjualan Hari Ini");
      jScrollPane1.setViewportView(tabelLog);

      jLabel1.setText("Penjualan Hari Ini");

      tabelPenjualan.setModel(new javax.swing.table.DefaultTableModel(
         new Object [][] {

         },
         new String [] {
            "Jam", "Pelanggan", "Nominal"
         }
      ) {
         Class[] types = new Class [] {
            java.lang.String.class, java.lang.Object.class, java.lang.Object.class
         };

         public Class getColumnClass(int columnIndex) {
            return types [columnIndex];
         }
      });
      tabelPenjualan.setToolTipText("Penjualan Hari Ini");
      jScrollPane2.setViewportView(tabelPenjualan);

      jLabel2.setText("Log Saldo");

      lblBanyakPelanggan.setText("Banyak Pelanggan : -");

      lblTotalNominal.setText("Total Nominal : -");

      lblTotalNominal1.setText("MARGIN");

      lblTotalNominal2.setText("Harga Jual = Rp3.000,00");

      lblTotalNominal3.setText("Biaya Admin = Rp100,00");

      lblLaba.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
      lblLaba.setText("Laba Hari Ini = -");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(cbAkun, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addComponent(lblBanyakPelanggan)
               .addComponent(lblTotalNominal)
               .addComponent(lblTotalNominal1)
               .addComponent(lblTotalNominal2)
               .addComponent(lblTotalNominal3)
               .addComponent(lblLaba))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(jLabel1)
               .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 87, Short.MAX_VALUE)
                  .addComponent(jLabel2)
                  .addGap(442, 442, 442))
               .addGroup(layout.createSequentialGroup()
                  .addGap(18, 18, 18)
                  .addComponent(jScrollPane1)
                  .addGap(18, 18, 18))))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(jLabel1)
                     .addComponent(jLabel2))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(cbAkun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addGap(18, 18, 18)
                  .addComponent(lblBanyakPelanggan)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblTotalNominal)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblTotalNominal1)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblTotalNominal2)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                  .addComponent(lblTotalNominal3)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                  .addComponent(lblLaba)))
            .addContainerGap(23, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents

   private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
      refreshTablePenjualan();
      refreshTotal();
      refreshTableLog();
   }//GEN-LAST:event_formComponentShown

   private void cbAkunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbAkunActionPerformed
      namaAkunTerpilih = cbAkun.getSelectedItem().toString();
      System.out.println(cbAkun.getSelectedItem().toString());
      refreshTablePenjualan();
      refreshTotal();
      refreshTableLog();
   }//GEN-LAST:event_cbAkunActionPerformed

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JComboBox cbAkun;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JLabel lblBanyakPelanggan;
   private javax.swing.JLabel lblLaba;
   private javax.swing.JLabel lblTotalNominal;
   private javax.swing.JLabel lblTotalNominal1;
   private javax.swing.JLabel lblTotalNominal2;
   private javax.swing.JLabel lblTotalNominal3;
   private javax.swing.JTable tabelLog;
   private javax.swing.JTable tabelPenjualan;
   // End of variables declaration//GEN-END:variables
}
