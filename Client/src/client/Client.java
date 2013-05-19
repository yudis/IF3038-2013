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

    private long lastUpdate;
    
    
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
                            System.out.println("Login successful, creating 'keep update' thread");
                            Runnable task = new MyRunnable();
                            Thread threadUpdate = new Thread(task);
                            threadUpdate.start();
                        } else if (respond.equals("400")){
                            System.out.println("Login failed");
                        } else {
                            System.out.println("Unrecognized respond");
                        }
                        break;
                    }
                    case ("check"):
                    {
                        System.out.print("Enter task_id: ");
                        String task_id = inClient.readLine();
                        request = ("2,"+task_id);
                        
                        outServer.writeBytes(request + '\n');
                        respond = inServer.readLine();
                        if (respond.equals("200")){
                            System.out.println("Check successful");
                        } else if (respond.equals("400")){
                            System.out.println("Check failed");
                        } else {
                            System.out.println("Unrecognized respond");
                        }
                        break;
                    }
                    case ("uncheck"):
                    {
                        System.out.print("Enter task_id: ");
                        String task_id = inClient.readLine();
                        request = ("3,"+task_id);
                        
                        outServer.writeBytes(request + '\n');
                        respond = inServer.readLine();
                        if (respond.equals("200")){
                            System.out.println("Uncheck successful");
                        } else if (respond.equals("400")){
                            System.out.println("Uncheck failed");
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
