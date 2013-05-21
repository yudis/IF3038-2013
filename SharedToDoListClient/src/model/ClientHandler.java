/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class ClientHandler {
    private Client client;
    private String LOG;


    public ClientHandler(String id) {
        client = new Client(id);
        LOG = "";
    }

    public Client getClient() {
        return client;
    }
    public String getLog() {
        return LOG;
    }

    public void AppendLog(String idtask,boolean stat) {
        String msg = "<"+idtask+","+stat+","+GetCurrentTime()+">";
        LOG = LOG + msg;
        PrintWriter writer;
        try {
            writer = new PrintWriter("LOG.txt", "UTF-8");
            writer.println(LOG);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void GetUpdate(String namafile) {
        try {
          client.DisposeTaskList();
         
          FileInputStream fstream = new FileInputStream(namafile);
          DataInputStream in = new DataInputStream(fstream);
          BufferedReader br = new BufferedReader(new InputStreamReader(in));
          String strLine;
          
          while ((strLine = br.readLine()) != null)   {
            System.out.println (strLine);
           
            String[] split = strLine.split("_");
            Task buff = new Task(split[0], split[1], split[2],ConvertBoolean(split[3]), split[4], split[5], split[6],split[7]);
            client.AddTask(buff);
          }
          in.close();
        }
        catch (Exception e){//Catch exception if any
           System.err.println("Error: " + e.getMessage());
        }
   }

   public void PushUpdate(String idtask,boolean stat) {
        client.UpdateTask(idtask, stat,GetCurrentTime());
   }

    boolean ConvertBoolean(String inp) {
        if (inp.equals("1")) {
            return true;
        }
        else {
            return false;
        }
    }
    public String ConvertBooelantoString(boolean inp) {
        if (inp) {
            return "1";
        }
        else {
            return "0";
        }
    }

    public String GetCurrentTime() {
        Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());
        String hsl = sqlDate.toString()+" "+sdf.format(cal.getTime());
        return hsl;
    }

}

