package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

public class SearchService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    PrintWriter out = response.getWriter();                
        String pathInfo = request.getPathInfo();
        dbConnection connector = new dbConnection();
        Connection conn = null;
        
        /* 
         * pathInfo             : baseURL/search/result
         * requestParameter     : keyword=&filter=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        if (pathInfo.equals("/result")) {
            try {                
                String keyword = request.getParameter("keyword");
                String filter = request.getParameter("filter");
                conn = connector.getConnection ();
                PreparedStatement stmt = conn.prepareStatement("");
                ResultSet rs = null;
                JSONArray searchResult = new JSONArray();
                
                if (filter.equals("1")) { //Search All
                    //Search User
                    stmt = conn.prepareStatement("SELECT distinct full_name, username FROM user WHERE username LIKE ? OR email LIKE ? OR full_name LIKE ? OR birthdate LIKE ?;");
                    stmt.setString(1, "%" + keyword + "%"); //username
                    stmt.setString(2, "%" + keyword + "%"); //email
                    stmt.setString(3, "%" + keyword + "%"); //full_name
                    stmt.setString(4, "%" + keyword + "%"); //birthdate        
                    rs = stmt.executeQuery();
                    rs.beforeFirst();
                    while (rs.next()) {
                        JSONObject user = new JSONObject();
                        user.put("search_id", rs.getString("username"));
                        user.put("search_name",rs.getString("full_name"));
                        user.put("search_type", "user");
                        searchResult.put(user);
                    }
                    
                    //Search Category
                    stmt = conn.prepareStatement("SELECT distinct cat_id, cat_name FROM category WHERE cat_name LIKE ?;");
                    stmt.setString(1, "%" + keyword + "%"); //category_name
                    rs = stmt.executeQuery();
                    rs.beforeFirst();
                    while (rs.next()) {
                        JSONObject category = new JSONObject();
                        category.put("search_id", rs.getString("cat_id"));
                        category.put("search_name",rs.getString("cat_name"));
                        category.put("search_type", "category");
                        searchResult.put(category);
                    }
                    
                    //Search Task
                    stmt = conn.prepareStatement("SELECT DISTINCT task_name, task.task_id FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) WHERE task_name LIKE ? OR tag_name LIKE ? OR comment_content LIKE ?");
                    stmt.setString(1, "%" + keyword + "%"); //task_name
                    stmt.setString(2, "%" + keyword + "%"); //tag_name
                    stmt.setString(3, "%" + keyword + "%"); //comment_content
                    rs = stmt.executeQuery();
                    rs.beforeFirst();
                    while (rs.next()) {
                        JSONObject task = new JSONObject();
                        task.put("search_id", rs.getString("task_id"));
                        task.put("search_name",rs.getString("task_name"));
                        task.put("search_type", "task");
                        searchResult.put(task);
                    }
                }
                else if (filter.equals("2")) { //Search User (username, email, nama lengkap, birthdate)
                    stmt = conn.prepareStatement("SELECT distinct full_name, username FROM user WHERE username LIKE ? OR email LIKE ? OR full_name LIKE ? OR birthdate LIKE ?;");
                    stmt.setString(1, "%" + keyword + "%"); //username
                    stmt.setString(2, "%" + keyword + "%"); //email
                    stmt.setString(3, "%" + keyword + "%"); //full_name
                    stmt.setString(4, "%" + keyword + "%"); //birthdate        
                    rs = stmt.executeQuery();
                    rs.beforeFirst();
                    while (rs.next()) {
                        JSONObject user = new JSONObject();
                        user.put("search_id", rs.getString("username"));
                        user.put("search_name",rs.getString("full_name"));
                        user.put("search_type", "user");
                        searchResult.put(user);
                    }
                }
                else if (filter.equals("3")) { //Search Category
                    stmt = conn.prepareStatement("SELECT distinct cat_id, cat_name FROM category WHERE cat_name LIKE ?;");
                    stmt.setString(1, "%" + keyword + "%"); //category_name
                    rs = stmt.executeQuery();
                    rs.beforeFirst();
                    while (rs.next()) {
                        JSONObject category = new JSONObject();
                        category.put("search_id", rs.getString("cat_id"));
                        category.put("search_name",rs.getString("cat_name"));
                        category.put("search_type", "category");
                        searchResult.put(category);
                    }
                }
                else if (filter.equals("4")) { //Search Task (task name, tag, comment)
                    stmt = conn.prepareStatement("SELECT DISTINCT task_name, task.task_id FROM ((task LEFT JOIN tag ON task.task_id = tag.task_id) LEFT JOIN comment ON task.task_id = comment.task_id) WHERE task_name LIKE ? OR tag_name LIKE ? OR comment_content LIKE ?");
                    stmt.setString(1, "%" + keyword + "%"); //task_name
                    stmt.setString(2, "%" + keyword + "%"); //tag_name
                    stmt.setString(3, "%" + keyword + "%"); //comment_content
                    rs = stmt.executeQuery();
                    rs.beforeFirst();
                    while (rs.next()) {
                        JSONObject task = new JSONObject();
                        task.put("search_id", rs.getString("task_id"));
                        task.put("search_name",rs.getString("task_name"));
                        task.put("search_type", "task");
                        searchResult.put(task);
                    }
                }
                
                out.println(searchResult);
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
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
