/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DBOperation;

import Model.Comment;
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
public class CommentOp {

    private Connection connection;

    public CommentOp() {
        connection = DBUtil.getConnection();
    }

    public ArrayList<Comment> FetchCommentByTaskId(String taskID) {
        ArrayList<Comment> result = new ArrayList<Comment>();
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM comment WHERE id_task=?");
            ps.setString(1, taskID);
            ResultSet rs = ps.executeQuery();

            Comment temp;
            while (rs.next()) {
                temp = new Comment(rs.getString("id_comment"), rs.getString("id_task"), rs.getString("username"), rs.getString("timestamp"), rs.getString("content"));
                result.add(temp);
            }

            rs.close();
            return result;
        } catch (SQLException e) {
            e.getMessage();
        }
        return result;
    }

    public void InsertComment(String id_task, String username, String timestamp, String content) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO comment (id_task, username, timestamp, content) VALUES (?, ?, ?, ?)");
            ps.setString(1, id_task);
            ps.setString(2, username);
            ps.setString(3, timestamp);
            ps.setString(4, content);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void DeleteCommentByCommentId(String commentId) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM comment WHERE id_comment=?");
            ps.setString(1, commentId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void DeleteCommentByTaskId(String taskID) {
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "DELETE FROM comment WHERE id_task=?");
            ps.setString(1, taskID);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.getMessage();
        }
    }
    
    //Timo, 12 April 2013
    public String GetIdByOtherAttributes(String id_task, String username, String content){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT id_comment FROM comment WHERE id_task=? AND username=? AND content=?");
            ps.setString(1, id_task);
            ps.setString(2, username);
            ps.setString(3, content);
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
