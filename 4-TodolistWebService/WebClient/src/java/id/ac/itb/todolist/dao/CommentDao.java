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
        //url/rest/comment/[idTugas]
        
        throw new UnsupportedOperationException("Not supported yet.");

        //return 0;
    }

    public List<Comment> getComments(int idTugas, int startIndex, int count) {
        //url/rest/comment/[idTugas]/[startIndex]/[count]
        ArrayList<Comment> comments = null;

        
        throw new UnsupportedOperationException("Not supported yet.");

        //return null;
    }

    public Comment getCommentById(int idComment) {
        //url/rest/comment/idComment[idComment]
        Comment comment = null;

        
        throw new UnsupportedOperationException("Not supported yet.");

        //return comment;
    }

    public int addComment(Comment c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int deleteComment(int commentId) {
        //url/comment/[commentId]
        
        throw new UnsupportedOperationException("Not supported yet.");
        
        //return -1;
    }
}
