/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.soap;

import id.ac.itb.todolist.dao.CommentDao;
import id.ac.itb.todolist.model.Comment;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Raymond
 */
@WebService(serviceName = "CommentSoap")
public class CommentSoap {

    @WebMethod(operationName = "addComment")
    public int addComment(@WebParam(name = "comment") String jsonComment) {
        Comment newComment = new Comment();
        newComment.fromJsonObject(new JSONObject(new JSONTokener(jsonComment)));
        CommentDao commentDao = new CommentDao();
        return commentDao.addComment(newComment);
    }
}