package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class IndexHandler extends HttpServlet{
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //PrintWriter p = resp.getWriter();
        //p.write("type = " + req.getParameter("type"));
        
        Connection conn = null;
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
                    //p.write(", username : " + rs.getString(1) +  ", fullname : " + fullname );
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
                //p.write("<br> Connection Failed... ");
                System.out.println("Connection Failed! Check output console");
            }
            finally { 
                try { 
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(IndexHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
        else
        if (req.getParameter("type").equalsIgnoreCase("signup")) {
            System.out.println("Sign Up Handler... ");
            String username = req.getParameter("signup_username");
            String password = req.getParameter("signup_password");
            //String password_confirm = req.getParameter("signup_confirm_password");
            String fullname = req.getParameter("signup_long_name");
            String email = req.getParameter("signup_email");
            String birthdate = req.getParameter("signup_birth_date");
            //String avatar = req.getParameter("signup_avatar_upload");
            
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
                    Logger.getLogger(IndexHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
    }
}
