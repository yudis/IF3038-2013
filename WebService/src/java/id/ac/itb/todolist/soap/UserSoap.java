/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.soap;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author Raymond
 */
@WebService(serviceName = "UserSoap")
public class UserSoap {

    @WebMethod(operationName = "addUser")
    public int addUser(@WebParam(name = "addUser") String jsonUser) {
        User user = new User();
        user.fromJsonObject(new JSONObject(new JSONTokener(jsonUser)));
        UserDao userDao = new UserDao();
        return userDao.addUser(user);
    }
}
