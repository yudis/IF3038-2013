/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
/**
 *
 * @author Timotius
 */
public class Server implements Runnable{
    
    protected static int port = 1234;
    Socket connection;
    private int ID;
    private static int count = 0;
    String message;
    String capitalizedMessage;
    
    Server(Socket socket, int id) {
        this.connection = socket;
        this.ID = id;
    }
        
    
    public static void main(String[] args) {
        
        try{
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server ready...");
            while (true) {
                Socket connection = socket.accept();
                Runnable runnable = new Server(connection, ++count);
                System.out.println("New connection with ID: "+count);
                Thread thread = new Thread(runnable);
                thread.start();
            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public void run() {
        
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            while (true){
                message = in.readLine();
                System.out.println("From Client: " + message);
                capitalizedMessage = message.toUpperCase() + '\n';
                out.writeBytes(capitalizedMessage);
                Thread.sleep(1000);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
