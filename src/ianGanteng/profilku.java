/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ianGanteng;

/**
 *
 * @author windows
 */
public class profilku extends javax.swing.JPanel {

   /**
    * Creates new form profilku
    */
   public profilku() {
      initComponents();
   }

   /**
    * This method is called from within the constructor to initialize the form.
    * WARNING: Do NOT modify this code. The content of this method is always
    * regenerated by the Form Editor.
    */
   @SuppressWarnings("unchecked")
   // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   private void initComponents() {

      tNama = new javax.swing.JTextField();
      tNoHp = new javax.swing.JTextField();
      tAlamat = new javax.swing.JTextField();
      tPassword = new javax.swing.JPasswordField();
      chkSee = new javax.swing.JCheckBox();
      btnSimpan = new javax.swing.JButton();
      lblSaldo = new javax.swing.JLabel();
      btnBatal = new javax.swing.JButton();

      tNama.setBorder(javax.swing.BorderFactory.createTitledBorder("Nama"));

      tNoHp.setBorder(javax.swing.BorderFactory.createTitledBorder("No Hp"));

      tAlamat.setBorder(javax.swing.BorderFactory.createTitledBorder("Alamat"));

      tPassword.setBorder(javax.swing.BorderFactory.createTitledBorder("Password"));

      btnSimpan.setText("Simpan");

      lblSaldo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
      lblSaldo.setText("Saldo : Rp");

      btnBatal.setText("Batal");

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
      this.setLayout(layout);
      layout.setHorizontalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                     .addComponent(tNoHp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(tNama, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(tPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                     .addComponent(btnSimpan)
                     .addComponent(btnBatal)
                     .addComponent(chkSee)
                     .addComponent(lblSaldo)))
               .addComponent(tAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
      layout.setVerticalGroup(
         layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
         .addGroup(layout.createSequentialGroup()
            .addContainerGap()
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addGroup(layout.createSequentialGroup()
                  .addComponent(lblSaldo)
                  .addGap(18, 18, 18)
                  .addComponent(btnSimpan))
               .addGroup(layout.createSequentialGroup()
                  .addComponent(tNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                  .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                     .addComponent(tNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                     .addComponent(btnBatal))))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(tAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
               .addComponent(tPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                  .addComponent(chkSee)
                  .addGap(10, 10, 10)))
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
      );
   }// </editor-fold>//GEN-END:initComponents

   // Variables declaration - do not modify//GEN-BEGIN:variables
   private javax.swing.JButton btnBatal;
   private javax.swing.JButton btnSimpan;
   private javax.swing.JCheckBox chkSee;
   private javax.swing.JLabel lblSaldo;
   private javax.swing.JTextField tAlamat;
   private javax.swing.JTextField tNama;
   private javax.swing.JTextField tNoHp;
   private javax.swing.JPasswordField tPassword;
   // End of variables declaration//GEN-END:variables
}
