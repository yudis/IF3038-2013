/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Ucrelation;
import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class UcrelationOp {
    private Connection connection;
    
    public UcrelationOp() {
        connection = DBUtil.getConnection();
    }
    
    public void InsertUcrelation (Ucrelation ucrelation) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO ucrelation (username, id_category) VALUES (?, ?)");
            ps.setString(1, ucrelation.getUsername());
            ps.setString(2, ucrelation.getId_category());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public void DeleteByCategId (String categId, String username) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM ucrelation WHERE id_category=? AND username=?");
            ps.setString(1, categId);
            ps.setString(2, username);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public int CheckIfUcrelationExists (String username, String categId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_uc FROM ucrelation WHERE username=? AND id_category=?");
            ps.setString(1, username);
            ps.setString(2, categId);
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
}
