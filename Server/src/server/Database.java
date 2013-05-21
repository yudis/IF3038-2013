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
    //private Statement statement = null;
    //private PreparedStatement preparedStatement = null;
    //private ResultSet resultSet = null;

    public Database() {
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
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086", "root", "");
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

    public String Login(String username, String password) {
        String message = "400";
        try {
            PreparedStatement preparedStatement1;
            preparedStatement1= connection.prepareStatement("SELECT * FROM user WHERE username = ? and password = ?");
            preparedStatement1.setString(1, username);
            preparedStatement1.setString(2, password);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet1.next()) {    //jika username dan password terdaftar di database
                message = "200," + resultSet1.getLong("last_update");
                /*//if success piggy back all task
                PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM task JOIN task_asignee WHERE task.task_id = task_asignee.task_id AND username = ?");
                preparedStatement2.setString(1, username);
                ResultSet resultSet2 = preparedStatement2.executeQuery();
                int rowcount = 0;
                if (resultSet2.last()) {
                    rowcount = resultSet2.getRow();
                    resultSet2.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
                }
                if (rowcount != 0) { //jika ada hasilnya
                    System.out.println("row(s) affected: " + rowcount);
                    while (resultSet2.next()) {
                        //Data yang wajib diterima dan ditampilkan pada sisi klien adalah 
                        //Nama tugas, deadline, daftar assignee, tag, status, dan nama kategori dari tugas tersebut.
                        String task_id = resultSet2.getString("task_id");   //<-- ini tetap perlu aja biar mudah refer nya waktu di check/uncheck
                        String task_name = resultSet2.getString("task_name");
                        String deadline = resultSet2.getString("task_deadline");
                        //belum tag assigne
                        String task_status = resultSet2.getString("task_status");
                        String cat_name = resultSet2.getString("cat_name");
                        
                        message = message + task_id + "," + task_status + ",";
                        // System.out.println("task_id: " + task_id + ", task_status; "+ task_status +", username: "+ _username +", timestamp: " + timestamp);
                    }
                    message = message + "\n";
                }*/
            } else {
                //message tetap 400\n
            }
        } catch (Exception e) {
            System.out.println("Login Error: " + e.getMessage());
        }
        return message;
    }
    
    public String GetUserTasks(String username){
        String message = ""; //inisialisasi, siapa tahu gada yang bisa di return
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM task JOIN task_asignee WHERE task.task_id = task_asignee.task_id AND username = ?");
            preparedStatement1.setString(1, username);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            int rowcount1 = 0;
            if (resultSet1.last()) {
                rowcount1 = resultSet1.getRow();
                resultSet1.beforeFirst();
            }
            if (rowcount1 != 0) { //jika ada hasilnya
                System.out.println("row(s) affected: " + rowcount1);
                while (resultSet1.next()) {
                    //Data yang wajib diterima dan ditampilkan pada sisi klien adalah 
                    //Nama tugas, deadline, daftar assignee, tag, status, dan nama kategori dari tugas tersebut.
                    String task_id = resultSet1.getString("task_id");   //<-- ini tetap perlu aja biar mudah refer nya waktu di check/uncheck
                    message = message + task_id + ",";
                    String task_name = resultSet1.getString("task_name");
                    message = message + task_name + ",";
                    String task_deadline = resultSet1.getString("task_deadline");
                    message = message + task_deadline + ",";
                    try {
                        PreparedStatement preparedStatement2 = connection.prepareStatement("SELECT * FROM task_asignee WHERE task_id = ?");
                        preparedStatement2.setString(1, task_id);
                        ResultSet resultSet2 = preparedStatement2.executeQuery();
                        int rowcount2 = 0;
                        if (resultSet2.last()) {
                            rowcount2 = resultSet2.getRow();
                            resultSet2.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
                        }
                        if (rowcount2 != 0){ //ada assignee nya
                            while (resultSet2.next()){
                                String task_assignees = resultSet2.getString("username");
                                message = message + task_assignees + ":";
                            }
                            message = message.substring(0,message.length()-1);  //hilangkan : terakhir
                        }
                        message = message + ",";
                    } catch (Exception e){
                        System.out.println("GetUserTasks Fetching Assignee Error: "+e.getMessage());
                    }
                    try {
                        PreparedStatement preparedStatement3 = connection.prepareStatement("SELECT * FROM tag WHERE task_id = ?");
                        preparedStatement3.setString(1, task_id);
                        ResultSet resultSet3 = preparedStatement3.executeQuery();
                        int rowcount3 = 0;
                        if (resultSet3.last()) {
                            rowcount3 = resultSet3.getRow();
                            resultSet3.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
                        }
                        if (rowcount3 != 0){ //ada tag nya
                            while (resultSet3.next()){
                                String tag_name = resultSet3.getString("tag_name");
                                message = message + tag_name + ":";
                            }
                            message = message.substring(0,message.length()-1);  //hilangkan : terakhir
                        }
                        message = message + ",";
                    } catch (Exception e){
                        System.out.println("GetUserTasks Fetching Tags Error: "+e.getMessage());
                    }
                    String task_status = resultSet1.getString("task_status");
                    message = message + task_status + ",";
                    String cat_name = resultSet1.getString("cat_name");
                    message = message + cat_name + ",";
                }
            }
        } catch (Exception e){
            System.out.println("GetUserTasks Error: "+e.getMessage());
        }
        if (message.length() > 0){
            message = message.substring(0, message.length()-1); //hilangkan koma terakhir
        }
        return message;
    }
    
    public long GetMaxTimestamp(String username){
        long max_timestamp = 0;
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT MAX(timestamp) AS mt FROM task JOIN task_asignee WHERE task.task_id = task_asignee.task_id AND username = ?");
            preparedStatement1.setString(1, username);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet1.next()) {
                max_timestamp = resultSet1.getLong("mt");
            }
        } catch (Exception e){
            System.out.println("GetMaxTimestamp Error: "+e.getMessage());
        }
        return max_timestamp;
    }
    
    public long GetMaxTimestampAbsolute(){
        //mirip GetMaxTimestamp tapi ini untuk semua task biarpun bukan user tsb. Ini untuk cari tau timestamp terbesar global untuk server last update
        long max_timestamp_absolute = 0;
        try{
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT MAX(timestamp) AS mt FROM task JOIN task_asignee WHERE task.task_id = task_asignee.task_id");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            if (resultSet1.next()) {
                max_timestamp_absolute = resultSet1.getLong("mt");
            }
        } catch (Exception e){
            System.out.println("GetMaxTimestampAbsolute Error: "+e.getMessage());
        }
        return max_timestamp_absolute;
    }
    
    public String Check(String task_id) {
        String message = "400";
        long now = System.currentTimeMillis();
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE task SET task_status = 1, timestamp = " + now + " WHERE task_id = ?");
            preparedStatement1.setString(1, task_id);
            if (preparedStatement1.executeUpdate() != 0) {
                message = "200," + now;    //success
            }
        } catch (Exception e) {
            System.out.println("Check Error: " + e.getMessage());
        }
        return message;
    }

    public String Uncheck(String task_id) {
        String message = "400";
        long now = System.currentTimeMillis();
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE task SET task_status = 0, timestamp = " + now + " WHERE task_id = ?");
            preparedStatement1.setString(1, task_id);
            if (preparedStatement1.executeUpdate() != 0) {
                message = "200," + now;    //success
            }
        } catch (Exception e) {
            System.out.println("Uncheck Error: " + e.getMessage());
        }
        return message;
    }
    
    public String Synchronize(String task_id, String status, Long lastUpdate) {
        String message = "400";
        try {
            //update hanya bila timestamp task-X di database lebih usang (<) daripada timestamp task-X di log
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE task SET task_status = ?, timestamp = ? WHERE task_id = ? AND timestamp < ?");
            preparedStatement1.setString(1, status);
            preparedStatement1.setLong(2, lastUpdate);
            preparedStatement1.setString(3, task_id);
            preparedStatement1.setLong(4, lastUpdate);
            if (preparedStatement1.executeUpdate() != 0) {
                message = "200";    //success
            }
        } catch (Exception e) {
            System.out.println("Synchronize Error: " + e.getMessage());
        }
        return message;
    }
    
    public String FetchUpdates (String username, Long lastUpdate){
        String message = "";
        try{
            //ambil semua row yang timestamp nya lebih besar dari lastUpdate (lebih baru), ambil task_id sama task_status nya
            PreparedStatement preparedStatement1 = connection.prepareStatement("SELECT * FROM task JOIN task_asignee WHERE task.task_id = task_asignee.task_id AND username = ? AND timestamp > ? ");
            preparedStatement1.setString(1, username);
            preparedStatement1.setLong(2, lastUpdate);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            int rowcount1 = 0;
            if (resultSet1.last()) {
                rowcount1 = resultSet1.getRow();
                resultSet1.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            if (rowcount1 != 0){
                System.out.println("row(s) affected: "+rowcount1);
                while (resultSet1.next()){
                    String task_id = resultSet1.getString("task_id");
                    message = message + task_id + ",";
                    String task_status = resultSet1.getString("task_status");
                    message = message + task_status + ",";
                }
            }
        } catch (Exception e){
            System.out.println("SynchronizeAll Error: " + e.getMessage());
        }
        if (message.length() > 0){
            message = message.substring(0, message.length()-1); //hilangkan koma terakhir
        }
        return message;
    }
    
    public String UpdateLastUpdate(String username, Long lastUpdate) {
        String message = "400";
        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement("UPDATE user SET last_update = ? WHERE username = ?");
            preparedStatement1.setLong(1, lastUpdate);
            preparedStatement1.setString(2, username);
            if (preparedStatement1.executeUpdate() != 0) {
                message = "200";
            }
        } catch (Exception e) {
            System.out.println("UpdateLastUpdate Error: " + e.getMessage());
        }
        return message;
    }
}
