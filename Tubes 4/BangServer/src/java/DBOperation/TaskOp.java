/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Task;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author dell
 */
public class TaskOp {
    private Connection connection;
    
    public TaskOp() {
        connection = DBUtil.getConnection();
    }
    
    public void InsertTask (Task task) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO task (name, deadline, status, id_category, creator) VALUES (?, ?, ?, ?, ?)");
            ps.setString(1, task.getName());
            ps.setString(2, task.getDeadline());
            ps.setString(3, task.getStatus());
            ps.setString(4, task.getId_category());
            ps.setString(5, task.getCreator());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public String FetchIdByNameAndCreator (String taskName, String creator) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_task FROM task WHERE name=? AND creator=?");
            ps.setString(1, taskName);
            ps.setString(2, creator);
            ResultSet rs = ps.executeQuery();
           
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        
        return null;
    }
    
    public void UpdateStatusWithId (String newStatus, String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE task SET status=? WHERE id_task=?");
            ps.setString(1, newStatus);
            ps.setString(2, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public Task SelectById (String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM task WHERE id_task=?");
            ps.setString(1, taskId);
            ResultSet rs = ps.executeQuery();
            
            Task temp;
            if (rs.next()) {
                temp = new Task(rs.getString("id_task"), rs.getString("name"), rs.getString("deadline"), rs.getString("status"), rs.getString("id_category"), rs.getString("creator"));
                rs.close();
                return temp;
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        
        return null;
    }
    
    public void DeleteById (String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM task WHERE id_task=?");
            ps.setString(1, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void UpdateDeadlineById (String newDeadline, String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE task SET deadline=? WHERE id_task=?");
            ps.setString(1, newDeadline);
            ps.setString(2, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public String FetchCreatorById (String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT creator FROM task WHERE id_task=?");
            ps.setString(1, taskId);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        
        return null;
    }
    
    public ArrayList<String> FetchIdsByCategId (String categId) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_task FROM task WHERE id_category=?");
            ps.setString(1, categId);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                result.add(rs.getString(1));
            }
            
            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        
        return null;
    }
}
