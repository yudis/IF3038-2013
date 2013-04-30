/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Caurelation;
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
public class CaurelationOp {

    private Connection connection;

    public CaurelationOp() {
        connection = DBUtil.getConnection();
    }

    public void InsertCaurelation(Caurelation caurelation) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO caurelation (id_category, authorized_user) VALUES (?, ?)");
            ps.setString(1, caurelation.getId_category());
            ps.setString(2, caurelation.getAuthorized_user());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public ArrayList<String> FetchAuthUsersByCategId(String categId) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT authorized_user FROM caurelation WHERE id_category=?");
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

    public void DeleteCaurelationByCategId(String categID) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM caurelation WHERE id_category=?");
            ps.setString(1, categID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
