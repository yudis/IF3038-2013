/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import DBOperation.CommentOp;
import JSON.JSONObject;
import JSON.JSONTokener;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author M500-S430
 */
@WebService(serviceName = "CommentSoap")
public class CommentSoap {
    CommentOp commentOp = new CommentOp();
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        JSONObject jcom = new JSONObject(new JSONTokener(txt));
        String idtask = jcom.getString("idtask");
        String content = jcom.getString("content");
        String username = jcom.getString("username");
        String timeStamp = jcom.getString("time");
        String avatar = jcom.getString("avatar");
        
        commentOp.InsertComment(idtask, username, timeStamp, content);
        String idcomment = commentOp.GetIdByOtherAttributes(idtask, username, content);
        String message = avatar+":"+timeStamp+":"+content+":"+idcomment;
        
        return message;
    }
}
