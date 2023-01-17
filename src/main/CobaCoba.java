/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author riqqi
 */
public class CobaCoba {

//   harian => tgl = 00.00.00
//mingguan => tgl = Minggu, 12
//bulanan => tgl =  12  januaari
//   default = Sab, 11:44:09 WIB 07/Jan/23
   public static void main(String[] args) throws ParseException {
      Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", new Locale("id", "ID")).parse("2022-12-09 13:26:00");
      String tglOke = new SimpleDateFormat("EE, hh:mm:ss z dd/MMM/yy", new Locale("id", "ID")).format(date);
      String mingguan = new SimpleDateFormat("EEEE, dd MMMM", new Locale("id", "ID")).format(date);
      String bulanan = new SimpleDateFormat(" dd MMMM", new Locale("id", "ID")).format(date);
      String harian = new SimpleDateFormat("hh:mm:ss z ", new Locale("id", "ID")).format(date);

      System.out.println("default : " + tglOke);
      System.out.println("mingguan : " + mingguan);
      System.out.println("bulanan : " + bulanan);
      System.out.println("harian : " + harian);
   }
}
