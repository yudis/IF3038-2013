package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Comment;
import id.ac.itb.todolist.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends DataAccessObject {
    public int getCommentsCount(int idTugas) {
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT COUNT(*) as `n` FROM `comments` WHERE `id_tugas`=?");
            preparedStatement.setInt(1, idTugas);

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            
            return rs.getInt("n");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    public List<Comment> getComments(int idTugas, int startIndex, int count) {
        ArrayList<Comment> comments = null;
        
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT c.`id` AS `id`, c.`user` AS `user`, u.`full_name` AS `full_name`,  u.`avatar` AS `avatar`,  c.`time` AS `time`, c.`content` AS `content` " +
	    	        "FROM `comments` c, `users` u " +
	    	        "WHERE `id_tugas`=? AND c.`user`=u.`username` ORDER BY c.`time` DESC LIMIT ?, ?");
            preparedStatement.setInt(1, idTugas);
            preparedStatement.setInt(2, startIndex);
            preparedStatement.setInt(3, count);
            

            ResultSet rs = preparedStatement.executeQuery();
            
            comments = new ArrayList<Comment>();
            while (rs.next()) {
                Comment c = new Comment();
                c.setContent(rs.getString("content"));
                c.setId(rs.getInt("id"));
                c.setIdTugas(idTugas);
                c.setTime(rs.getTimestamp("time"));
                
                User u = new User();
                u.setUsername(rs.getString("user"));
                u.setAvatar(rs.getString("avatar"));
                u.setFullName(rs.getString("full_name"));
                c.setUser(u);
                
                comments.add(c);
            }
            
            return comments;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public Comment getCommentById(int idComment) {
        Comment comment = null;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("SELECT * FROM `comments` WHERE `id`=?");
            preparedStatement.setInt(1, idComment);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                Comment c = new Comment();
                c.setContent(rs.getString("content"));
                c.setId(rs.getInt("id"));
                c.setIdTugas(rs.getInt("id_tugas"));
                c.setTime(rs.getTimestamp("time"));
                
                User u = new User();
                u.setUsername(rs.getString("user"));
                c.setUser(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return comment;
    }
    
    public int addComment(Comment c) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("INSERT INTO comments (id_tugas, user, content) VALUES (?, ?, ?);");
            preparedStatement.setInt(1, c.getIdTugas());
            preparedStatement.setString(2, c.getUser().getUsername());
            preparedStatement.setString(3, c.getContent());
            
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    public int deleteComment(int commentId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("DELETE FROM `comments` WHERE `id`=?");
            preparedStatement.setInt(1, commentId);
            
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
