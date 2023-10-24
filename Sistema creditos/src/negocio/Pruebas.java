/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author gabil
 */
public class Pruebas {
    public static void main(String[] args) {
//      var number=Variables.roundUp(100.0, 100);
//      
//        System.out.println(number);
//    String fecha="221155";
//    String dia=fecha.substring(4,6);
//    String mes=fecha.substring(2,4);
//    String año=fecha.substring(0,2);
//    String p="01";
//    int c=Integer.parseInt(p);
//    
//    
//        System.out.println(dia);
//        System.out.println(mes);
//        System.out.println(año);
//        System.out.println(fecha);
//        System.out.println(c);
//        
//String x="01";
//        System.out.println(x.length());
double pitros=200.0;
Locale locale = new Locale("es", "MX");      
NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
System.out.println(currencyFormatter.format(pitros));


    }
}
