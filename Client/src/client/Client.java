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
        String message;
        Boolean running;
        
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
