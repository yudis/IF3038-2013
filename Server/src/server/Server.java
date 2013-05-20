/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

/**
 *
 * @author ASUS
 */
public class Server {
    static ServerSocket socket;
    static int port = 20000;
    static Connection conn;
      
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        // Binding Port
        try {
                socket = new ServerSocket(port);
                System.out.println("Bound to port: " + port);
        } catch (Exception e) {
                System.out.println("Cannot bind to port: " + port);
                System.exit(0);
        }
        
        int id = 1;
        while (true) {
            try {
                    Socket s = socket.accept();
                    
                    System.out.println("--------------------------------------------");
                    System.out.println("\t\tNew Client");
                    System.out.println("--------------------------------------------");
                    System.out.println("Address : "+s.getInetAddress().toString());
                    System.out.println("ID : "+id);
                    System.out.println("--------------------------------------------");
                    (new Thread(new ClientHandler(s,id))).start();
                    
                    id++;
            } catch (Exception e) {
                    System.out.println("Failed to accept client");
            }
        }
    }
}
