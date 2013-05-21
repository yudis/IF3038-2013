/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package clienttubesv;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chritianto Handojo
 */
public class Synchronizer implements Runnable, Serializable{
    private String username;
    private boolean needed;
    
    Synchronizer(String s) {
        username = s;
        needed = true;
    }
    
    @Override
    public void run() {
        while (true) {
            while (!needed) ;
            
            //Try to submit all the data inside the log.
            //Do it from last checkpoint.
            BufferedReader baca = null;
            try {
                baca = new BufferedReader(new FileReader("log/log_"+username+"_update.txt"));
                String s = baca.readLine();
                while (s != null && needed) {
                    Connector conn = new Connector("127.0.0.1",3400);
                    StringBuilder sb = new StringBuilder();
                    sb.append(2);
                    sb.append(" ");
                    sb.append(s);
                    conn.setRequest(sb.toString());
                    conn.sendRequest();
                    String hasil = conn.getReply();
                    //System.out.println(s + " " + hasil);
                    s = baca.readLine();
                }
                baca.close();
                
                if (needed) {
                    BufferedWriter out = new BufferedWriter(new FileWriter("log/log_"+username+"_update.txt", false));
                    out.append("");
                    out.close();
                }
            } catch(IOException ex) {
                Logger.getLogger(ClientTubesV.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                needed = false;
            }
            
            try {
                BufferedWriter out = new BufferedWriter(new FileWriter("log/log_"+username+".txt", true));
                DateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                out.append(dateformat.format(date));
                out.append(" - SYNCH CHECKPOINT");
                out.append("\n");
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(ClientTubesV.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void process() {
        needed = true;
    }
    
    public boolean busy() {
        return needed;
    }
}
