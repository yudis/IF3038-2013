/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import id.ac.itb.todolist.json.JSONObject;

/**
 *
 * @author Edward Samuel
 */
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
//        PrintWriter out = response.getWriter();
//        
//        JsonObject jObject = new JsonObject();
//        jObject.addProperty("status", 403);
//        jObject.addProperty("message", "Bad request");
//        
//        out.print(jObject);
//        out.close();
    }
        
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
               
        JSONObject jObject = new JSONObject();
        
        if (username != null && password != null) {
            UserDao userDao = new UserDao();
            User user = userDao.getUserLogin(username, password);
            if (user != null) {
                session.setAttribute("user", user);
                jObject.put("status", 200);
            } else {
                jObject.put("status", 401);
                jObject.put("message", "Login failed, username/password does not correct.");
            }
        }
        
        out.print(jObject);
        out.close();
    }
}
