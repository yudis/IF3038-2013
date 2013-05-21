/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.UnsupportedEncodingException;

/**
 *
 * @author FUJITSU
 */
public class TextEncryption {
    public static void main(String[] args) throws UnsupportedEncodingException {
        String message = "password";
        byte[] byteMsg = message.getBytes("UTF8");
        
        for (byte b : byteMsg) {
            System.out.println("byte : " + b);
        }
        
//        enkripsi
        for (int i=0; i<byteMsg.length; i++) {
            byteMsg[i] = (byte) (byteMsg[i] + i);
        }
        
        String result = new String(byteMsg);
        System.out.println("byte message : " + byteMsg);
        System.out.println("result : " + result);
    }
}
