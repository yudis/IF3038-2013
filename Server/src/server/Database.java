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
                message = "200,"+resultSet.getLong("last_update")+"\n";    //success
            }
        } catch (Exception e){
            System.out.println("Login Error: " + e.getMessage());
        }
        return message;
    }
    
    public String Check(String task_id){
        String message = "400\n";
        long now = System.currentTimeMillis();
        try{
            preparedStatement = connection.prepareStatement("UPDATE task SET task_status = 1, timestamp = "+ now +" WHERE task_id = ?");
            preparedStatement.setString(1, task_id);
            if (preparedStatement.executeUpdate() != 0){
                message = "200,"+now+"\n";    //success
            }
        } catch (Exception e){
            System.out.println("UpdateTaskStatus Error: " + e.getMessage());
        }
        return message;
    }
    
    public String Uncheck(String task_id){
        String message = "400\n";
        long now = System.currentTimeMillis();
        try{
            preparedStatement = connection.prepareStatement("UPDATE task SET task_status = 0, timestamp = "+ now +" WHERE task_id = ?");
            preparedStatement.setString(1, task_id);
            if (preparedStatement.executeUpdate() != 0){
                message = "200,"+now+"\n";    //success
            }
        } catch (Exception e){
            System.out.println("UpdateTaskStatus Error: " + e.getMessage());
        }
        return message;
    }
    
    public String SynchronizeUponConnection(String username, Long lastUpdate){
        String message = "400\n";
        try{
            preparedStatement = connection.prepareStatement("SELECT * FROM task JOIN task_asignee WHERE task.task_id = task_asignee.task_id AND username = ? AND timestamp > ? ");
            preparedStatement.setString(1, username);
            preparedStatement.setLong(2, lastUpdate);
            resultSet = preparedStatement.executeQuery();
            int rowcount = 0;
            if (resultSet.last()) {
                rowcount = resultSet.getRow();
                resultSet.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            if (rowcount != 0){ //jika ada hasilnya
                message = "200,";
            
                System.out.println("row(s) affected: "+rowcount);
                while (resultSet.next()){
                    String task_id = resultSet.getString("task_id");
                    String _username = resultSet.getString("username");
                    String task_status = resultSet.getString("task_status");
                    long timestamp = resultSet.getLong("timestamp");
                    message += task_id+":"+task_status+",";
                    System.out.println("task_id: " + task_id + ", task_status; "+ task_status +", username: "+ _username +", timestamp: " + timestamp);
                }
                message = message.substring(0, message.length()-1);
                message += "\n";
            }
        } catch (Exception e){
            System.out.println("SynchronizedUponConnection Error: " + e.getMessage());
        }
        System.out.println(message);
        return message;
    }
    
    public String UpdateLastUpdate(String username, Long lastUpdate){
        String message = "400\n";
        try{
            preparedStatement = connection.prepareStatement("UPDATE user SET last_update = ? WHERE username = ?");
            preparedStatement.setLong(1, lastUpdate);
            preparedStatement.setString(2, username);
            if (preparedStatement.executeUpdate() != 0){
                message = "200\n";    //success
            }
        } catch (Exception e){
            System.out.println("UpdateTaskStatus Error: " + e.getMessage());
        }
        return message;
    }
}
