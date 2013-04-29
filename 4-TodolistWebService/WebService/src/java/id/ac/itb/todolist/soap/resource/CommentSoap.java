/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.soap.resource;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

import org.json.JSONObject;
import org.json.JSONTokener;

import id.ac.itb.todolist.model.Comment;

import id.ac.itb.todolist.dao.CommentDao;
/**
 *
 * @author Yulianti Oenang
 */
@WebService(serviceName = "CommentSoap")
public class CommentSoap {

    /** This is a sample web service operation */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
     @WebMethod(operationName = "addComment")
    public int addComment(@WebParam(name = "comment") String jsonComment) {
          Comment newComment = new Comment();
        newComment.fromJsonObject(new JSONObject(new JSONTokener(jsonComment)));
        CommentDao commentDao = new CommentDao();
        return commentDao.addComment(newComment);
    }
}
