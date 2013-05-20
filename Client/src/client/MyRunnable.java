/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Compaq
 */
public class MyRunnable implements Runnable {
    private Client client;
    private int count;
    public MyRunnable(Client c, int servercount){
        this.client = c;
        this.count = servercount;
    }
    public void run(){
        try{
            Socket askSocket = new Socket("localhost", 5000 + count);
            DataOutputStream out = new DataOutputStream(askSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(askSocket.getInputStream()));
            while (client.getRunning()){
                out.writeBytes("11,"+client.getUsername()+","+client.getLastUpdate()+"\n"); //11,kennyazrina,<lastupdate>
                String respond1 = in.readLine();
                //System.out.println("myrunnable :: respond1: " + respond1);
                String[] responds1 = respond1.split(",");
                if (responds1[0].equals("13")){
                    client.setLastUpdate(Long.parseLong(responds1[1]));
                    //responds1[2] berisi task_id, responds1[3] berisi task-status, dst
                    int i = 2;
                    ArrayList<UpdateElm> elms = new ArrayList<>();
                    while (i < responds1.length){
                        UpdateElm elm = new UpdateElm();
                        elm.setTask_id(responds1[i]);
                        elm.setTask_status(responds1[i+1]);
                        elms.add(elm);
                        i = i + 2;
                    }
                    for (int j = 0; j < elms.size(); j++){
                        System.out.println("Elms "+j+": ");
                        System.out.println(":: task_id: "+elms.get(j).getTask_id());
                        System.out.println(":: task_status: "+elms.get(j).getTask_status());
                    }
                } else{
                    //System.out.println("myrunnable: no updates");
                }
                Thread.sleep(1000);
            }
            out.writeBytes("5\n");
        } catch (Exception e){
            System.out.println("MyRunnable: "+e.getMessage());
            //saat koneksi diputus
            /*boolean reconnected = false;
            while (!reconnected){
                try{
                    client.setClientSocket(new Socket("localhost", 1234));
                    reconnected = true;
                } catch (Exception ex){
                    System.out.println("Reconnecting: "+ex.getMessage());
                }
            }*/
        }
    }
}
