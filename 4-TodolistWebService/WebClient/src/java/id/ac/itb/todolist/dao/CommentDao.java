package id.ac.itb.todolist.dao;

import id.ac.itb.todolist.model.Comment;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class CommentDao extends DataAccessObject {

    public int getCommentsCount(int idTugas) {
        // GET
        // /rest/comment/[idTugas]
        
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/comment/" + idTugas);
            htc.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return 0;
    }

    public List<Comment> getComments(int idTugas, int startIndex, int count) {
        // GET
        // /rest/comment/[idTugas]/[startIndex]/[count]
        
        ArrayList<Comment> comments = null;
        
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/comment/" + idTugas + "/" + startIndex +"/" + count);
            htc.setRequestMethod("GET");

            comments=new ArrayList<Comment>();
            JSONArray jArray = new JSONArray(new JSONTokener(htc.getInputStream()));
            for (int i = 0, len = jArray.length(); i < len; i++) {
                Comment comment = new Comment();
                comment.fromJsonObject(jArray.getJSONObject(i));
                comments.add(comment);
            }
            
            return comments;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public Comment getCommentById(int idComment) {
        // GET
        // /rest/comment/[idComment]
        
        Comment comment = null;
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/comment/" + idComment);
            htc.setRequestMethod("GET");

            Comment tmpComment = new Comment();
            tmpComment.fromJsonObject(new JSONObject(new JSONTokener(htc.getInputStream())));
            
            comment = tmpComment;
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return comment;
    }

    public int deleteComment(int commentId) {
        // DELETE
        // /comment/[commentId]
        
        try {
            HttpURLConnection htc = getHttpURLConnection("/rest/comment/" + commentId);
            htc.setRequestMethod("DELETE");

            BufferedReader br = new BufferedReader(new InputStreamReader(htc.getInputStream()));
            return Integer.parseInt(br.readLine());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return -1;
    }

    public int addComment(Comment comment) {
        id.ac.itb.todolist.soap.comment.CommentSoap_Service service = new id.ac.itb.todolist.soap.comment.CommentSoap_Service();
        id.ac.itb.todolist.soap.comment.CommentSoap port = service.getCommentSoapPort();
        return port.addComment(comment.toJsonObject().toString());
    }
}
