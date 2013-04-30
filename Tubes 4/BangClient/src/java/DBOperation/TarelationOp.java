/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Tarelation;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class TarelationOp {
    private Connection connection;
    
    public TarelationOp() {
        connection = DBUtil.getConnection();
    }
    
    public void InsertTarelation (Tarelation tarelation) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO tarelation (id_task, id_attachment) VALUES (?, ?)");
            ps.setString(1, tarelation.getId_task());
            ps.setString(2, tarelation.getId_attachment());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void DeleteTarelationByTaskId (String taskId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM tarelation WHERE id_task=?");
            ps.setString(1, taskId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
