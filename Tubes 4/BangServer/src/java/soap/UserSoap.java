/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soap;

import DBOperation.UserOp;
import JSON.JSONObject;
import JSON.JSONTokener;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author M500-S430
 */
@WebService(serviceName = "UserSoap")
public class UserSoap {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        JSONObject juser = new JSONObject(new JSONTokener(txt));
        UserOp UO = new UserOp();
        return UO.InsertUser(juser.getString("username"),juser.getString("fullname"),juser.getString("filename"),juser.getString("date"),juser.getString("email"),juser.getString("password"));
    }
}
