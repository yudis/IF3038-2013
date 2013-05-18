/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
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
        // <editor-fold desc="Starting connection with database...">
        System.out.println("-------- MySQL JDBC Connection Testing ------------");
        try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}
        System.out.println("MySQL JDBC Driver Registered!");
	Connection connection = null;
        try {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root", "");
        } catch (SQLException e) {
		System.out.println("Connection Failed! Check output console");
		e.printStackTrace();
		return;
	}
        if (connection != null) {
		System.out.println("You made it, take control your database now!");
	} else {
		System.out.println("Failed to make connection!");
	}
        // </editor-fold>
        
        //Starts server ...
        try{
            ServerSocket socket = new ServerSocket(port);
            System.out.println("Server ready...");
            while (true) {
                Socket conn = socket.accept();
                Runnable runnable = new Server(conn, ++count);
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
