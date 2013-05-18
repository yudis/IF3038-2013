/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.net.*;
/**
 *
 * @author Timotius
 */
public class Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String command;
        String message;
        Boolean running;
        
        try{
            running = true;
            BufferedReader inClient = new BufferedReader( new InputStreamReader(System.in));
            Socket clientSocket = new Socket("localhost", 1234);
            DataOutputStream outServer = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader inServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (running){
                System.out.print(">> ");
                command = inClient.readLine();
                outServer.writeBytes(command + '\n');
                message = inServer.readLine();
                System.out.println("From Server: " + message);
            }
            clientSocket.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
