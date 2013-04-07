/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Class;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class Function {

    public Function() {
    }
    
    public HashMap<String, String> GetUser(String username){
        try {
            HashMap<String, String> user = new HashMap<String, String>();
            GetConnection connection = new GetConnection();
            Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM user WHERE username='"+username+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            user.put("username", rs.getString(1));
            user.put("password", rs.getString(2));
            user.put("fullname", rs.getString(3));
            user.put("birthday", rs.getString(4));
            user.put("email", rs.getString(5));
            user.put("join", rs.getString(6));
            user.put("aboutme", rs.getString(7));
            user.put("avatar", rs.getString(8));
            return user;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
    
    public HashMap<String, String> GetTask(String taskId){
        try {
            HashMap<String, String> task = new HashMap<String, String>();
            GetConnection connection = new GetConnection();
            Connection conn = connection.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM task WHERE taskid='"+taskId+"'";
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            task.put("taskid", rs.getString(1));
            task.put("taskname", rs.getString(2));
            task.put("username", rs.getString(3));
            task.put("createddate", rs.getString(4));
            task.put("deadline", rs.getString(5));
            task.put("status", rs.getString(6));
            task.put("categoryid", rs.getString(7));
            return task;
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
