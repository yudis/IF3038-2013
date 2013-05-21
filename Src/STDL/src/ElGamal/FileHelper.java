package ElGamal;


import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arief
 */
public class FileHelper {
    
    public FileHelper() {
        
    }
    
    /**
     * Membuka file eksternal
     * @param pPathFile nama file
     */
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
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Read File Gagal");
        }
        return tRetval.toString();
    }
    
    public static void saveDoc(String pPathFile, String pContent) {
        File tFile = new File(pPathFile);
        try {
            FileOutputStream tFos = new FileOutputStream(tFile);
            DataOutputStream tDos = new DataOutputStream(tFos);
            tDos.write(pContent.getBytes());
            tDos.close();
            tFos.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static byte[] openByte(String pPathFile) {
        byte[] bytePool = new byte[100000000];
        int byteLength = 0;
        File tFile = new File(pPathFile);
        if (tFile.exists() && tFile.isFile()) {
            try {
                FileInputStream tFis = new FileInputStream(tFile);
                DataInputStream tDis = new DataInputStream(tFis);
                int i=0;
                while (tDis.available() != 0) {
                    //tRetval.append((char) tDis.read());
                    bytePool[i]=tDis.readByte();
                    i=i+1;
                    if((i%100000)==0){
                        System.out.println("Read Byte number : "+i);
                    }
                }
                byteLength=i;
                tDis.close();
                tFis.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] byteOut = new byte[byteLength];
            for (int i = 0; i < byteLength; i++) {
                byteOut[i]=bytePool[i];
            }
            return byteOut;
        } else {
            System.out.println("Read File Gagal");
            return bytePool;
        }
    }
    
    public static void saveByte(byte[] bytePool, String pPathFile){
        FileOutputStream tFos = null;
        try {
            File tFile = new File(pPathFile);
            tFos = new FileOutputStream(tFile,false);
            DataOutputStream tDos = new DataOutputStream(tFos);
            tFos.write(bytePool, 0, bytePool.length);
            tFos.close();
        } catch (IOException ex) {
            Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                tFos.close();
            } catch (IOException ex) {
                Logger.getLogger(FileHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
