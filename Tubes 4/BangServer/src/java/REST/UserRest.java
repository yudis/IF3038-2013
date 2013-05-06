/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import DBOperation.JoinOp;
import DBOperation.UserOp;
import JSON.JSONArray;
import JSON.JSONObject;
import Model.Task;
import Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author M500-S430
 */
public class UserRest extends HttpServlet {

    private Pattern regexLogin = Pattern.compile("^/login/([\\w._%].*)$");
    private Pattern regexProfil = Pattern.compile("^/profil/([\\w._%].*)$");
    private Pattern regexEditProfil = Pattern.compile("^/editprofil/([\\w._%].*)$");
    private Pattern regexList = Pattern.compile("^/list/([\\w._%].*)$");
    private Pattern regexList2 = Pattern.compile("^/list2/([\\w._%].*)$");
    private Pattern c = Pattern.compile("");
    private UserOp userOp;

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public UserRest() {
        super();
        userOp = new UserOp();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexLogin.matcher(pathInfo);
        if (matcher.find()) {
            User user = userOp.SelectUserInfoByUsername(matcher.group(1));

            out.println(user.toJSONObject());

            return;
        }

        matcher = regexProfil.matcher(pathInfo);
        if (matcher.find()) {
            User user = userOp.SelectUserInfoByUsername(matcher.group(1));
            out.println(user.toJSONObject());

            return;
        }
        
        matcher = regexEditProfil.matcher(pathInfo);
        if (matcher.find()) {
            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }
            } catch (Exception e) { //report an error }
                e.getMessage();
            }
            JSONObject a = new JSONObject(jb.toString());
            
            User user = userOp.SelectUserInfoByUsername(matcher.group(1));
            
            userOp.UpdateUser(a.getString("username"),a.getString("fullname"),a.getString("fileName"),a.getString("date"),a.getString("password"));
            
            out.println(user.toJSONObject());

            return;
        }

        matcher = regexList.matcher(pathInfo);
        if (matcher.find()) {
            JSONObject jstask = new JSONObject();
            JoinOp JO = new JoinOp();
            ArrayList<Task> todoList;
            Task task;
            todoList = JO.GetTodoTasksByUsername(matcher.group(1));
            for (int i = 0; i < todoList.size(); i++) {
                task = todoList.get(i);
                jstask.put("Task " + Integer.toString(i), task.getName());
            }
            out.println(jstask.toString());
            return;
        }

        matcher = regexList2.matcher(pathInfo);
        if (matcher.find()) {
            JSONObject jstask = new JSONObject();
            JoinOp JO = new JoinOp();
            ArrayList<Task> todoList;
            Task task;
            todoList = JO.GetDoneTasksByUsername(matcher.group(1));
            for (int i = 0; i < todoList.size(); i++) {
                task = todoList.get(i);
                JSONObject b = new JSONObject();
                jstask.put("Task " + Integer.toString(i), task.getName());
                //out.println(task.getName()+"<br>");
                //System.out.println(task.getName());
            }
            out.println(jstask.toString());
            return;
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
