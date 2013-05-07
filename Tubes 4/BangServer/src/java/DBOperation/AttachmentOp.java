package DBOperation;

import Utility.DBUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class AttachmentOp {

    private Connection connection;

    public AttachmentOp() {
        connection = DBUtil.getConnection();
    }
    
    public void InsertAttachment (String attachmentPath) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO attachment (path) VALUES (?)");
            ps.setString(1, attachmentPath);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    public String FetchIdByPath (String attachmentPath) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_attachment FROM attachment WHERE path=?");
            ps.setString(1, attachmentPath);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return null;
    }
}
