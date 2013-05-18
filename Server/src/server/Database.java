/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

/**
 *
 * @author Compaq
 */
public class Database {
    private Connection connection;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
  
    public Database(){
        System.out.println("-------- MySQL JDBC Connection Testing ------------");
        try {
		Class.forName("com.mysql.jdbc.Driver");
	} catch (ClassNotFoundException e) {
		System.out.println("Where is your MySQL JDBC Driver?");
		e.printStackTrace();
		return;
	}
        System.out.println("MySQL JDBC Driver Registered!");
	connection = null;
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
    }
    
    public String Login(String username, String password){
        String message = "400\n";
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM user WHERE username = ? and password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                message = "200\n";    //success
            }
        } catch (Exception e){
            System.out.println("Login Error: " + e.getMessage());
        }
        return message;
    }
}
