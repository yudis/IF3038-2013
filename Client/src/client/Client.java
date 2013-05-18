/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package client;
import java.io.*;
import java.net.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
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
        String request;
        String respond;
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
                command = command.toLowerCase();
                switch (command){
                    case ("login"):
                    {
                        System.out.print("Enter username: ");
                        String username = inClient.readLine();
                        System.out.print("Enter password: ");
                        String password = inClient.readLine();
                        request = ("1,"+username+","+password);
                        
                        outServer.writeBytes(request + '\n');
                        respond = inServer.readLine();
                        if (respond.equals("200")){
                            System.out.println("Login successful");
                        } else if (respond.equals("400")){
                            System.out.println("Login failed");
                        } else {
                            System.out.println("Unrecognized respond");
                        }
                        break;
                    }
                    default:
                    {
                        break;
                    }
                }
                
                //System.out.println("From Server: " + message);
            }
            clientSocket.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
