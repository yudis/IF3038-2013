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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/* 
 * CategoryService memiliki URL : baseURL/category
 * CategoryService merupakan sebuah service class yang menangani : 
 *      - get_list  - done
 *      - delete    - done
 */

@WebServlet(name = "CategoryService", urlPatterns = {"/category/*"})
public class CategoryService extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();                
        String pathInfo = request.getPathInfo();
        dbConnection connector = new dbConnection();
        Connection conn = null;
        
        /* 
         * pathInfo             : baseURL/category/get_list
         * requestParameter     : username=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        if (pathInfo.equals("/get_list")) {
            try {
                /*
                HttpSession session = request.getSession(true);
                String username = (String) session.getAttribute("username");
                */
                String username = request.getParameter("username");
                JSONObject categoryObject = new JSONObject();
                JSONArray categoryArray = new JSONArray();
                conn = connector.getConnection ();
                PreparedStatement stmt = conn.prepareStatement("");
                ResultSet rs = null;
                
                /* Search Category by Creator */
                stmt = conn.prepareStatement("SELECT * FROM category WHERE cat_creator=?;");
                stmt.setString(1, username);
                rs = stmt.executeQuery();
                
                String category_name = "";
                String category_id = "";
                //Result set is not empty
                rs.beforeFirst();
                while (rs.next()) {
                    category_name = rs.getString("cat_name");
                    category_id = rs.getString("cat_id");
                    categoryObject.put("category_id", category_id);
                    categoryObject.put("category_name", category_name);  
                    categoryArray.put(categoryObject);
                }
                
                 /* Search Category by Assignee */
                stmt = conn.prepareStatement("SELECT cat_id FROM cat_asignee WHERE username =?;");
                stmt.setString(1, username);
                rs = stmt.executeQuery();
                rs.beforeFirst();
                String categoryId = "";
                while (rs.next()) {
                    categoryId = rs.getString("cat_id"); 
                    PreparedStatement stmt2 = conn.prepareStatement("SELECT * FROM category WHERE cat_id =?;");
                    stmt2.setString(1, categoryId);
                    ResultSet rs2 = stmt2.executeQuery();
                    rs2.beforeFirst();
                    while (rs2.next()) {
                        categoryObject.put("category_id", categoryId);
                        categoryObject.put("category_name", rs2.getString("cat_name"));  
                        categoryArray.put(categoryObject);
                    }
                }
                
                out.println(categoryArray);
               
            } catch (SQLException ex) {
                Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/category/delete
         * requestParameter     : category_id=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/delete")) {
            try {
                String category_id = request.getParameter("category_id");
                conn = connector.getConnection ();
                PreparedStatement st = conn.prepareStatement("DELETE FROM category WHERE cat_id=?");
                st.setString(1, category_id);
                st.executeUpdate();
                out.println("Category with id : " + category_id + " has been deleted!");                
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
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
