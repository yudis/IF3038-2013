/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author Compaq
 */
public class MyRunnable implements Runnable {
    public void run(){
        try{
            Socket askSocket = new Socket("localhost", 5678);
            DataOutputStream out = new DataOutputStream(askSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(askSocket.getInputStream()));
            while (true){
                out.writeBytes("11\n"); //is there any updates?
                String confirm = in.readLine();
                if (confirm.equals("12")){
                    //System.out.println("no update yet...");
                }
                Thread.sleep(1000);
            }
        } catch (Exception e){
            System.out.println("MyRunnable: "+e.getMessage());
        }
    }
}
