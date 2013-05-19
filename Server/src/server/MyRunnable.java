/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import static server.Server.port;

/**
 *
 * @author Compaq
 */
public class MyRunnable implements Runnable {
    public void run(){
        try{
            ServerSocket confirmSocket = new ServerSocket(5678);
            Socket askSocket = confirmSocket.accept();
            DataOutputStream out = new DataOutputStream(askSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(askSocket.getInputStream()));
            while (true){
                String ask = in.readLine();
                if (ask.equals("11")){
                    //System.out.println("is there any update?");
                    out.writeBytes("12\n"); //no updates yet
                }
                Thread.sleep(1000);
            }
        } catch (Exception e){
            System.out.println("MyRunnable: "+e.getMessage());
        }
    }
}
