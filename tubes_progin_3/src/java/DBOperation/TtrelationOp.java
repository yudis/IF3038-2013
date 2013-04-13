/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Ttrelation;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class TtrelationOp {
    private Connection connection;
    
    public TtrelationOp() {
        connection = DBUtil.getConnection();
    }
    
    public int CheckIfTtrelationExists(String taskId, String tagId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_tt FROM ttrelation WHERE id_task=? AND id_tag=?");
            ps.setString(1, taskId);
            ps.setString(2, tagId);
            ResultSet rs = ps.executeQuery();

            if (rs.isBeforeFirst()) {
                rs.close();
                return 1;
            } else {
                rs.close();
                return 0;
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return 999;
    }
    
    public void InsertTtrelation (Ttrelation ttrelation) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO ttrelation (id_task, id_tag) VALUES (?, ?)");
            ps.setString(1, ttrelation.getId_task());
            ps.setString(2, ttrelation.getId_tag());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void DeleteByTaskId (String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM ttrelation WHERE id_task=?");
            ps.setString(1, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void DeleteByTagId(String tagId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM ttrelation WHERE id_tag=?");
            ps.setString(1, tagId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
