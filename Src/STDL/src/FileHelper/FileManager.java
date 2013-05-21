/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package FileHelper;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Toshiba L645
 */
public class FileManager {
    public static String openDoc(String pPathFile) {
        StringBuilder tRetval = new StringBuilder();
        File tFile = new File(pPathFile);
        if (tFile.exists() && tFile.isFile()) {
            try {
                FileInputStream tFis = new FileInputStream(tFile);
                DataInputStream tDis = new DataInputStream(tFis);
                while (tDis.available() != 0) {
                    tRetval.append((char) tDis.read());
                }
                tDis.close();
                tFis.close();
            } catch (IOException ex) {
                 Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } else {
            System.out.println("Gagal");
        }
        return tRetval.toString();
    }

    public static void saveDocNew(String pPathFile, String pContent) {
        File tFile = new File(pPathFile);
        try {
            FileOutputStream tFos = new FileOutputStream(tFile);
            DataOutputStream tDos = new DataOutputStream(tFos);
            tDos.write(pContent.getBytes());
            tDos.close();
            tFos.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    

    public static void saveDocAppend(String pPathFile, String pContent) {
        File tFile = new File(pPathFile);
        try {
            FileOutputStream tFos = new FileOutputStream(tFile,true);
            DataOutputStream tDos = new DataOutputStream(tFos);
            tDos.write(pContent.getBytes());
            tDos.close();
            tFos.close();
        } catch (IOException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
