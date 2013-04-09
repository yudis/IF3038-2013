package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CategoryHandler extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn_category = null;
        ResultSet rs_category = null;
        
        if (req.getParameter("type").equalsIgnoreCase("add")) {
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

                PreparedStatement stmt_category = conn_category.prepareStatement("INSERT INTO `category`(`cat_name`, `cat_creator`) VALUES (?,?)");
                stmt_category.setString(1, categoryName);
                stmt_category.setString(2, categoryCreator);
                stmt_category.executeQuery();
                
                stmt_category = conn_category.prepareStatement("SELECT cat_id FROM category WHERE cat_name=?");
                stmt_category.setString(1, categoryName);
                rs_category = stmt_category.executeQuery();
                rs_category.beforeFirst();
                while (rs_category.next()) { 
                    categoryID = rs_category.getString("cat_id");
                }
                
                String[] assigneArray = categoryAssigne.split(",");
                for (int i=0; i<assigneArray.length; i++) {
                    stmt_category = conn_category.prepareStatement("INSERT INTO `cat_asignee`(`cat_id`, `username`) VALUES (?,?)");
                    stmt_category.setString(1, categoryID);
                    stmt_category.setString(2, assigneArray[i]);
                    stmt_category.executeQuery();
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
                    Logger.getLogger(IndexHandler.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
        }
        else
        if (req.getParameter("type").equalsIgnoreCase("delete")) {
            
        }    
    }
}
