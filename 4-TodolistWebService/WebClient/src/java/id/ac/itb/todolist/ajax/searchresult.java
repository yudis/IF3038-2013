/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.CategoryDao;
import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.dao.UserDao;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Felix
 */
public class searchresult extends HttpServlet {
    
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
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String q = request.getParameter("q");
        int x = Integer.parseInt(request.getParameter("x"));
        String curTable = request.getParameter("n");
        int n = 10;
        
        UserDao userdao = new UserDao();
        TugasDao tugasdao = new TugasDao();
        CategoryDao categorydao = new CategoryDao();

        JSONObject result = new JSONObject();
        JSONArray jArray = new JSONArray();
        result.put("category", jArray);
        result.put("tugas", jArray);
        result.put("user", jArray);
        
        if (request.getParameter("filter") != null) {

            if (request.getParameter("filter").equals("All")) {

                if (curTable.equals("category") && (n > 0)) {
                    jArray = new JSONArray(categorydao.getCategorySearch(q, x, n));
                    result.put("category", jArray);
                    if (jArray.length() >= n) {
                        result.put("x", (x + n));
                        result.put("n", "category");
                        n = 0;
                    } else {
                        n -= jArray.length();
                        x = 0;
                        curTable = "tugas";
                    }
                }

                if (curTable.equals("tugas") && (n > 0)) {
                    jArray = new JSONArray(tugasdao.getTugasSearch(q, x, n));
                    result.put("tugas", jArray);
                    if (jArray.length() >= n) {
                        result.put("x", (x + n));
                        result.put("n", "tugas");
                        n = 0;
                    } else {
                        n -= jArray.length();
                        x = 0;
                        curTable = "username";
                    }
                }

                if (curTable.equals("username") && (n > 0)) {
                    jArray = new JSONArray(userdao.getUserSearch("%" + q + "%", x, n));
                    result.put("user", jArray);
                    if (jArray.length() >= n) {
                        result.put("x", (x + n));
                        result.put("n", "username");
                        n = 0;
                    } else {
                        n -= jArray.length();
                        result.put("x", 0);
                        // result.put("n", null);
                    }
                }

            } else if (request.getParameter("filter").equals("Username")) {

                jArray = new JSONArray(userdao.getUserSearch("%" + q + "%", x, n));
                result.put("user", jArray);
                if (jArray.length() >= n) {
                    result.put("x", (x + n));
                    result.put("n", "username");
                } else {
                    // result.put("x", null);
                    // result.put("n", null);
                }
            } else if (request.getParameter("filter").equals("Title")) {

                jArray = new JSONArray(categorydao.getCategorySearch(q, x, n));
                result.put("category", jArray);
                if (jArray.length() >= n) {
                    result.put("x", (x + n));
                    result.put("n", "category");
                } else {
                    // result.put("x", null);
                    // result.put("n", null);
                }

            } else if (request.getParameter("filter").equals("Task")) {

                jArray = new JSONArray(tugasdao.getTugasSearch(q, x, n));
                result.put("tugas", jArray);
                if (jArray.length() >= n) {
                    result.put("x", (x + n));
                    result.put("n", "tugas");
                } else {
                    // result.put("x", null);
                    // result.put("n", null);
                }

            }
        }
        out.print(result);
        out.close();
    }
}
