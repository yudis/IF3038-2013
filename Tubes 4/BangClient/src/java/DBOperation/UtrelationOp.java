/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Utrelation;
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
public class UtrelationOp {
    private Connection connection;
    
    public UtrelationOp() {
        connection = DBUtil.getConnection();
    }
    
    public void InsertUtrelation (Utrelation utrelation) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO utrelation (username, id_task) VALUES (?, ?)");
            ps.setString(1, utrelation.getUsername());
            ps.setString(2, utrelation.getId_task());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void DeleteByTaskId (String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM utrelation WHERE id_task=?");
            ps.setString(1, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void DeleteByTaskIdAndUsername (String taskId, String username) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM utrelation WHERE id_task=? AND username=?");
            ps.setString(1, taskId);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public ArrayList<String> FetchAssigneeByTaskId (String taskId) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT username FROM utrelation WHERE id_task=?");
            ps.setString(1, taskId);
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
    
    //Timo, 12 April 2013
    public boolean IsTaskEditable(String username, String id_task) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM utrelation WHERE username = ? AND id_task = ?");
            ps.setString(1, username);
            ps.setString(2, id_task);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                rs.close();
                return true;
            } else {
                rs.close();
                return false;
            }
        } catch (SQLException e) {
            e.getMessage();
            return false;
        }
    }
}
