/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package progin5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author Krisna Dibyo Atmojo <krisnadibyo@gmail.com>
 */
public class ClientHandler {
    private Client client;

    public ClientHandler(String id) {
        client = new Client(id);
    }

    public Client getClient() {
        return client;
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

   public void PushUpdate(String idtask,String stat) {
        client.UpdateTask(idtask, ConvertBoolean(stat),GetCurrentTime());
   }

    boolean ConvertBoolean(String inp) {
        if (inp.equals("1")) {
            return true;
        }
        else {
            return false;
        }
    }

    String GetCurrentTime() {
        Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        java.util.Date utilDate = cal.getTime();
        java.sql.Date sqlDate = new Date(utilDate.getTime());
        String hsl = sqlDate.toString()+" "+sdf.format(cal.getTime());
        return hsl;
    }

}

