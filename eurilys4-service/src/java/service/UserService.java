package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;

/* 
 * UserService memiliki URL : baseURL/user
 * UserService merupakan sebuah service class yang menangani : 
 *      - login_check
 *      - register
 *      - user_detail
 *      - update_profile
 *      - current_task
 *      - finished_task
 */

public class UserService extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();                
        String pathInfo = request.getPathInfo();
        dbConnection connector = new dbConnection();
        Connection conn = null;
        
        /* 
         * pathInfo             : baseURL/user/login_check
         * requestParameter     : login_username=&login_password=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        if (pathInfo.equals("/login_check")) {
            try {                
                String username = request.getParameter("login_username");
                String password = request.getParameter("login_password");            
                
                conn = connector.getConnection ();

                PreparedStatement stmt = conn.prepareStatement("SELECT username, full_name FROM user WHERE username=? AND password=?;");
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();

                String fullname = "";
                while (rs.next()) {          
                    fullname = rs.getString(2);
                }
                
                if (!fullname.equals("")) {
                    out.println(fullname);
                }
                else {
                    out.println("failed");
                } 
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/user/register
         * requestParameter     : signup_username=&signup_password=&signup_long_name=&signup_email=&signup_birth_date=signup_avatar_upload=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/register")) {
            try {
                String username = request.getParameter("signup_username");
                String password = request.getParameter("signup_password");
                String fullname = request.getParameter("signup_long_name");
                String email = request.getParameter("signup_email");
                String birthdate = request.getParameter("signup_birth_date");
                //Blob avatar = req.getParameter("signup_avatar_upload");

                conn = connector.getConnection ();
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO user VALUES ('" + username + "','" + password + "','" + fullname + "','" + birthdate + "','NULL','" + email + "')");

                if (conn != null) {
                    //Berhasil register
                    HttpSession session = request.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("fullname", fullname);
                    response.sendRedirect("../src/dashboard.jsp");
                } else {
                    //Gagal register
                    response.sendRedirect("../index.jsp?register_status=failed");
                }
            }            
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
        
        /* 
         * pathInfo             : baseURL/user/user_detail
         * requestParameter     : username=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/user_detail")) {
            try {                
                String username = request.getParameter("username");                
                conn = connector.getConnection ();

                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user WHERE username=?;");
                stmt.setString(1, username);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                                
                while (rs.next()) {  
                    String password = rs.getString(2);
                    String fullname = rs.getString(3);
                    String birthdate = rs.getString(4);
                    String avatar = rs.getString(5);
                    String email = rs.getString(6);
                    
                    JSONObject userObject = new JSONObject();
                    userObject.put("username", username);
                    userObject.put("password", password);
                    userObject.put("fullname", fullname);
                    userObject.put("birthdate", birthdate);
                    userObject.put("avatar", avatar);
                    userObject.put("email", email);                    
                    out.println(userObject);
                }
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/user/update_profile
         * requestParameter     : username=&password=&fullname=&birthdate=&avatar=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/update_profile")) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String fullname = request.getParameter("fullname");
                String birthdate = request.getParameter("birthdate");
                String avatar = request.getParameter("avatar");
                
                conn = connector.getConnection();
                PreparedStatement stmt = conn.prepareStatement("");

                if (!("".equals(password))) {
                    stmt = conn.prepareStatement("UPDATE user SET full_name=?, birthdate=?, password=? WHERE username=?");
                    stmt.setString(1, fullname);
                    stmt.setString(2, birthdate);
                    stmt.setString(3, password);
                    stmt.setString(4, username);
                } else {
                    stmt = conn.prepareStatement("UPDATE user SET full_name=?, birthdate=? WHERE username=?");
                    stmt.setString(1, fullname);
                    stmt.setString(2, birthdate);
                    stmt.setString(3, username);
                }
                
                int row = stmt.executeUpdate();
                out.println(row); //1 = berhasil, 0 = failed
                
            } catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        /* 
         * pathInfo             : baseURL/user/current_task
         * requestParameter     : username=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/current_task")) {
            try {                
                String username = request.getParameter("username");                
                conn = connector.getConnection ();
              
                PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT task.task_name, task.task_id, task.task_status FROM task_asignee LEFT JOIN task ON task.task_id=task_asignee.task_id WHERE username=? OR task_creator=?");
                stmt.setString(1, username);
                stmt.setString(2, username);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                JSONArray currentTaskArray = new JSONArray();
                while (rs.next()) {  
                    if ("0".equals(rs.getString("task_status"))) {
                        JSONObject currentTask = new JSONObject();
                        currentTask.put("task_name", rs.getString("task_name"));
                        currentTask.put("task_id", rs.getString("task_id"));
                        currentTaskArray.put(currentTask);
                    }
                }
                out.println(currentTaskArray);
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/user/finished_task
         * requestParameter     : username=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/finished_task")) {
            try {                
                String username = request.getParameter("username");                
                conn = connector.getConnection ();
              
                PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT task.task_name, task.task_id, task.task_status FROM task_asignee LEFT JOIN task ON task.task_id=task_asignee.task_id WHERE username=? OR task_creator=?");
                stmt.setString(1, username);
                stmt.setString(2, username);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                JSONArray finishedTaskArray = new JSONArray();
                while (rs.next()) {  
                    if ("1".equals(rs.getString("task_status"))) {
                        JSONObject finishedTask = new JSONObject();
                        finishedTask.put("task_name", rs.getString("task_name"));
                        finishedTask.put("task_id", rs.getString("task_id"));
                        finishedTaskArray.put(finishedTask);
                    }
                }
                out.println(finishedTaskArray);
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
         /* 
         * pathInfo             : baseURL/user/autocomplete
         * requestParameter     : keyword=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/autocomplete")) {
            try {                
                String keyword = request.getParameter("keyword");
                conn = connector.getConnection ();
                PreparedStatement stmt = conn.prepareStatement("");
                ResultSet rs = null;
                String HTMLresult = "";
                
                //Search User
                stmt = conn.prepareStatement("SELECT distinct full_name, username FROM user WHERE username LIKE ? OR email LIKE ? OR full_name LIKE ? OR birthdate LIKE ?;");
                stmt.setString(1, "%" + keyword + "%"); //username
                stmt.setString(2, "%" + keyword + "%"); //email
                stmt.setString(3, "%" + keyword + "%"); //full_name
                stmt.setString(4, "%" + keyword + "%"); //birthdate        
                rs = stmt.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    if (!("".equals(HTMLresult))) {
                        HTMLresult += "<br>";
                    }
                    //HTMLresult += "<div class='search_recommend' onclick=\"javascript:searchResult('"+rs.getString("username")+"','user');\">"+ rs.getString("full_name") +"</div>";
                    HTMLresult = HTMLresult + "<div class='search_recommend' id='edittask_ass_"+rs.getString("username")+"' onclick=\"javascript:addEditTaskAssigne('"+rs.getString("username")+"');\">"+rs.getString("username")+"</div>";
                }
                out.println(HTMLresult);
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
