/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.app.dmsftpdownload;

/**
 *
 * @author wilson
 */
public class SbtrTest {
     public static void main(String args[]) {
      String Str = new String("BANK WOORI SAUDARA INDONESIA 1906 TBK");
      String strcom = new String("WOORI SAUDARA");

      if(Str.contains(strcom)){
        System.out.print("true");
      } else {
          System.out.println("false");   
      }
   }
}
