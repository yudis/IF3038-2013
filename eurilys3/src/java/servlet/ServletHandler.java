package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ServletHandler extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        
        //Login
        if (req.getParameter("type").equalsIgnoreCase("login")) {
            System.out.println("Login Handler...");
            
            String username = req.getParameter("login_username");
            String password = req.getParameter("login_password");
            String fullname = "";
            
            try {                
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver?");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");

                PreparedStatement stmt = conn.prepareStatement("SELECT username, full_name FROM user WHERE username=? AND password=?;");
                stmt.setString(1, username);
                stmt.setString(2, password);
                ResultSet rs = stmt.executeQuery();

                //Result set is not empty
                rs.beforeFirst();
                while (rs.next()) {          
                    fullname = rs.getString(2);
                }
                
                //berhasil login
                if (!fullname.equals("")) {
                    // Set Session 
                    HttpSession session = req.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("fullname", fullname);
                    // Redirect
                    resp.sendRedirect("src/dashboard.jsp");
                }
                else {
                    //gagal login
                    req.setAttribute("result", "failed_login");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);    
                }
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            }
            finally { 
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
        
        //Sign Up
        else if (req.getParameter("type").equalsIgnoreCase("signup")) {
            System.out.println("Sign Up Handler... ");
            String username = req.getParameter("signup_username");
            String password = req.getParameter("signup_password");
            //String password_confirm = req.getParameter("signup_confirm_password");
            String fullname = req.getParameter("signup_long_name");
            String email = req.getParameter("signup_email");
            String birthdate = req.getParameter("signup_birth_date");
            //Blob avatar = req.getParameter("signup_avatar_upload");
            
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver?");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");                 
                Statement st = conn.createStatement(); 
                st.executeUpdate("INSERT INTO user VALUES ('"+username+"','"+password+"','"+fullname+"','"+birthdate+"','NULL','"+email+"')");

                //berhasil sign up
                if (conn != null) {                
                    // Set Session 
                    HttpSession session = req.getSession(true);
                    session.setAttribute("username", username);
                    session.setAttribute("fullname", fullname);
                    // Redirect
                    resp.sendRedirect("src/dashboard.jsp");
                }
                else {
                    //gagal sign up
                    req.setAttribute("result", "failed_register");
                    req.getRequestDispatcher("index.jsp").forward(req, resp);
                }
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            } 
            finally { 
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }

        //Add Category
        else if (req.getParameter("type").equalsIgnoreCase("add_category")) {
            Connection conn_category = null;
            ResultSet rs_category = null;
            String categoryID       = "";
            String categoryName     = req.getParameter("add_category_name");
            String categoryAssigne  = req.getParameter("add_category_asignee_name");
            HttpSession session = req.getSession(true);
            String categoryCreator = (String) session.getAttribute("username");
            try {                
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver?");
                }
                conn_category = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
                
                Statement st = conn_category.createStatement(); 
                st.executeUpdate("INSERT INTO category (cat_name, cat_creator) VALUES ('"+categoryName+"','"+categoryCreator+"')");
                
                PreparedStatement stmt_category = conn_category.prepareStatement("SELECT cat_id FROM category WHERE cat_name=?");
                stmt_category.setString(1, categoryName);
                rs_category = stmt_category.executeQuery();
                rs_category.beforeFirst();
                while (rs_category.next()) { 
                    categoryID = rs_category.getString("cat_id");
                }                
                String[] assigneArray = categoryAssigne.split(",");
                for (int i=0; i<assigneArray.length; i++) {
                    st.executeUpdate("INSERT INTO cat_asignee (cat_id, username) VALUES ('"+categoryID+"','"+assigneArray[i]+"')");
                }                
                //Redirect
                resp.sendRedirect("src/dashboard.jsp");
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            }
            finally {                 
                try { 
                    conn_category.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
        
        //Delete Category
        else if (req.getParameter("type").equalsIgnoreCase("delete_category")) {
            Connection conn_category = null;
            ResultSet rs_category = null;
            String categoryID       = req.getParameter("delete_category_id");            
            String categoryName     = req.getParameter("delete_category_name");
            HttpSession session     = req.getSession(true);
            List<String> deleteTaskId = new ArrayList<String>();
            
            /*
            PrintWriter out = resp.getWriter();            
            String confirm_script = "";
            confirm_script += "<script>";
                confirm_script += "var deleteCategoryConfirm = confirm('Are you sure you want to delete this CATEGORY and all the TASKS related?');";
                confirm_script += "if (deleteCategoryConfirm == false) {";
                        confirm_script += "window.location.href='src/dashboard.jsp';";
                confirm_script += "}";
            confirm_script += "</script>";
            out.println(confirm_script); */
            
            /* The following will be executed if confirmed */    
            try {                
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - ServletHandler.java delete_category");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - ServletHandler.java delete_category");
                }
                conn_category = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
            
                PreparedStatement stmt_category = conn_category.prepareStatement("SELECT task_id FROM task WHERE cat_name=?");
                stmt_category.setString(1, categoryName);
                rs_category = stmt_category.executeQuery();
                
                rs_category.beforeFirst();                
                //Ada Task pada category tersebut
                while (rs_category.next()) {
                    deleteTaskId.add(rs_category.getString("task_id"));
                    
                    //Delete Task Assignee
                    stmt_category = conn_category.prepareStatement("DELETE FROM task_asignee WHERE task_id=?");
                    stmt_category.setString(1, rs_category.getString("task_id"));
                    stmt_category.executeUpdate();                    
                }

                //Delete Task
                if (deleteTaskId.size() > 0 ) {
                    for (int i=0; i<deleteTaskId.size(); i++) {
                        stmt_category = conn_category.prepareStatement("DELETE FROM task WHERE task_id=?");
                        stmt_category.setString(1, deleteTaskId.get(i));
                        stmt_category.executeUpdate(); 
                    }
                }
                
                //Delete Category Assignee
                stmt_category = conn_category.prepareStatement("DELETE FROM cat_asignee WHERE cat_id=?");
                stmt_category.setString(1, categoryID);
                stmt_category.executeUpdate();  
                
                //Delete Category
                stmt_category = conn_category.prepareStatement("DELETE FROM category WHERE cat_id=?");
                stmt_category.setString(1, categoryID);
                stmt_category.executeUpdate();  
                
                //Redirect
                resp.sendRedirect("src/dashboard.jsp");
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            }
            finally {                 
                try { 
                    conn_category.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
        
        //add comment
        else if (req.getParameter("type").equalsIgnoreCase("add_comment")) {
            String comment          = req.getParameter("CommentBox");
            String taskID           = req.getParameter("comment_task_id");
            HttpSession session     = req.getSession(true);
            String username         = (String) session.getAttribute("username");
            
            System.out.println("comment : " + comment);
            System.out.println("taskID :" + taskID);
            System.out.println("username " + username);
            
            try {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - add_comment ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - add_comment");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");                 
                Statement st = conn.createStatement(); 
                st.executeUpdate("INSERT INTO `comment`(`comment_content`,`task_id`,`comment_creator`) VALUES ('"+comment+"','"+taskID+"','"+username+"')");
                resp.sendRedirect("src/task_detail.jsp?task_id="+taskID);
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console - add_comment");
            } 
            finally { 
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - add_comment");
                }
            }
        }
        
        //other
        else if (req.getParameter("type").equalsIgnoreCase("")) {
            
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        
        //Finish Task
        if (req.getParameter("type").equalsIgnoreCase("finish_task")) {
            String taskID = req.getParameter("task_id");
            try {                
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver -- ServletHandler.java - finish_task ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? -- ServletHandler.java - finish_task");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
                
                Statement st = conn.createStatement(); 
                st.executeUpdate("UPDATE task SET task_status='1' WHERE task_id='"+taskID+"';");
                
                //Redirect
                resp.sendRedirect("src/dashboard.jsp");
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            }
            finally {                 
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
        else
        
        //Delete Task
        if (req.getParameter("type").equalsIgnoreCase("delete_task")) {
            String taskID = req.getParameter("task_id");
            try {                
                // Make connection to database
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver -- ServletHandler.java - delete_task ");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? -- ServletHandler.java - delete_task");
                }
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
                
                PreparedStatement st;
                st = conn.prepareStatement("DELETE FROM tag WHERE task_id=?");
                st.setString(1, taskID);
                st.executeUpdate();
                st = conn.prepareStatement("DELETE FROM task WHERE task_id=?");
                st.setString(1, taskID);
                st.executeUpdate();
                
                //Redirect
                resp.sendRedirect("src/dashboard.jsp");
            }
            catch (SQLException e) {
                System.out.println("Connection Failed! Check output console");
            }
            finally {                 
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ServletHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
    }
    
    
}
