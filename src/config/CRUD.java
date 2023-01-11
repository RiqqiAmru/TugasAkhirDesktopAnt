/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import main.MainMenu;

/**
 *
 * @author Riqqi
 */
public class CRUD {

   protected String driver = "com.mysql.cj.jdbc.Driver";
   protected String url = "jdbc:mysql://localhost:3306/pulsa_ali";
   protected String user = "root";
   protected String pass = "";
   protected Connection cn;
   protected Statement st;
   DecimalFormat IDR = (DecimalFormat) DecimalFormat.getCurrencyInstance(new Locale("id", "ID"));
   NumberFormat id = NumberFormat.getInstance(new Locale("id", "ID"));

   public CRUD() {
      koneksi();
   }

//    ---DATABASE---
   public void koneksi() {

      try {
         Class.forName(driver);
         cn = DriverManager.getConnection(url, user, pass);
         st = cn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 0);
         System.out.println("koneksi berhasil");
      } catch (ClassNotFoundException | SQLException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
      }

   }

   public ResultSet ambilData(String query) {
      ResultSet rs = null;
      try {
         rs = st.executeQuery(query);
      } catch (SQLException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
      }
      return rs;
   }

   public void insertData(String query, String message) {
      try {
         st.executeUpdate(query);
         JOptionPane.showMessageDialog(null, message);
      } catch (SQLException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
         JOptionPane.showMessageDialog(null, ex.getMessage());
      }
   }

   public void updateData(String query, String message) {
      insertData(query, message);
   }

   public void refreshSaldo(int idAkun, javax.swing.JLabel l) {
      ResultSet akun = ambilData("SELECT * FROM  akun WHERE id_akun = '" + idAkun + "'");
      String saldo;
      try {
         if (akun.first()) {
            saldo = integerToRupiah(Integer.parseInt(akun.getString("saldo")));
            l.setText("Saldo : " + saldo);
         }
      } catch (SQLException ex) {
         Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

//    ---FUNCTIONAL---
   public void textError(javax.swing.JTextField j, String text) {
      j.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 0, 0)), text, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 0, 0)));
   }

   public void textSucces(javax.swing.JTextField j, String text) {
      j.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)), text, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 255, 0)));
   }

   public void defaultText(javax.swing.JTextField ara, String text) {
      ara.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), text));
   }

   public void comboSuccess(javax.swing.JComboBox j, String text) {
      j.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 255, 0)), text, javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 13), new java.awt.Color(0, 255, 0))); // NOI18N

   }

   public void comboDefault(javax.swing.JComboBox j, String text) {
      j.setBorder(javax.swing.BorderFactory.createTitledBorder(text));
   }

   public void togglePassword(javax.swing.JPasswordField p, javax.swing.JCheckBox c) {
      if (c.isSelected()) {
         p.setEchoChar((char) 0);
      } else {
         p.setEchoChar('*');
      }
   }

   public String StringToMD5(String Password) throws NoSuchAlgorithmException {
      MessageDigest m = MessageDigest.getInstance("MD5");
      m.update(Password.getBytes(), 0, Password.length());
      return new BigInteger(1, m.digest()).toString(16);
   }

   public String integerToRupiah(int nominal) {
      return IDR.format(nominal);
   }

   public Number rupiahToNumber(String rupiah) {
      try {
         String substring = rupiah.substring(2);
         Number parse = id.parse(substring);
         return parse;
      } catch (ParseException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
         return 0;
      }
   }

   public String dateToTgl(String tgl) {
      try {
         Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("id", "ID")).parse(tgl);
         String tglOke = new SimpleDateFormat("EE, hh:mm:ss z dd/MMM/yy", new Locale("id", "ID")).format(date);
         return tglOke;
      } catch (ParseException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
         return "error cannot parse tgl ";
      }
   }

//  ---VALIDASI---
   public void validasiAND(boolean[] t, javax.swing.JButton a) {
      boolean hasil = true;
      for (boolean succes : t) {
         hasil = succes & hasil;
         a.setEnabled(hasil);
      }
   }

   public void validasiOR(boolean[] t, javax.swing.JButton a) {
      boolean hasil = false;
      for (boolean succes : t) {
         hasil = succes | hasil;
         a.setEnabled(hasil);
      }
   }

//to validate button register
   public void reverseValidasiAND(boolean[] t, javax.swing.JButton a) {
      boolean hasil = true;
      for (boolean succes : t) {
         hasil = succes & hasil;
         a.setVisible(!hasil);
      }
   }

   public boolean ValidasiTextHarusDiIsi(javax.swing.JTextField j, String text, javax.swing.JButton b) {
      if (j.getText().isEmpty()) {
         textError(j, text + " Harus Di Isi");
         return false;
      } else {
         textSucces(j, text);
         return true;
      }
   }

   public boolean validasiDataTabel(javax.swing.JTextField j, String text, ResultSet query) {
      try {
         if (query.next()) {
            textSucces(j, text);
            return true;
         } else {
            textError(j, text + " Salah");
            return false;
         }
      } catch (SQLException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      }
   }

   public boolean validasiDataSudahTerdaftar(javax.swing.JTextField j, String text, ResultSet query) {
      try {
         if (query.next()) {
            textError(j, text + " sudah terdaftar");
            return false;
         } else {
            textSucces(j, text);
            return true;
         }
      } catch (SQLException ex) {
         Logger.getLogger(CRUD.class.getName()).log(Level.SEVERE, null, ex);
         return false;
      }
   }

   public boolean validasiPasswordBeda(String pass1, String pass2, javax.swing.JTextField j, String text) {
      if (pass1.equals(pass2)) {
         textSucces(j, text);
         return true;
      } else {
         textError(j, text + " Incorrect");
         return false;
      }
   }

   public boolean validasiPasswordBedaDariPassLama(String passBaru, String passLama, javax.swing.JTextField j, String text) {
      if (!passLama.equals(passBaru)) {
         textSucces(j, text);
         return true;
      } else {
         textError(j, text + " Baru Harus Beda Dari Password Lama");
         return false;
      }
   }
}
