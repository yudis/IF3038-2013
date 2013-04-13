/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

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
public class TagOp {

    private Connection connection;

    public TagOp() {
        connection = DBUtil.getConnection();
    }

    public int CheckIfTagExists(String tagName) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM tag WHERE name=?");
            ps.setString(1, tagName);
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

    public void InsertTag(String tagName) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO tag (name) VALUES (?)");
            ps.setString(1, tagName);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public ArrayList<String> FetchTagForSearch(String searchParam) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT name FROM tag WHERE name LIKE '%" + searchParam + "%'");
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

    public String SelectIdByName(String tagName) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_tag FROM tag WHERE name=?");
            ps.setString(1, tagName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.getMessage();
        }

        return null;
    }

    public void DeleteById (String tagId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM tag WHERE id_tag=?");
            ps.setString(1, tagId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
}
