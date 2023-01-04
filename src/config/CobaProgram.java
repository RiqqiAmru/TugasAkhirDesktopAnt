/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 *
 * @author Riqqi
 */
public class CobaProgram {

   public static void main(String[] args) throws ParseException {
      NumberFormat id = NumberFormat.getInstance(new Locale("id", "ID"));
      String rupiah = "Rp1.000.000,00";
      String substring = rupiah.substring(2);
      Number parse = id.parse(substring);
   }
}
