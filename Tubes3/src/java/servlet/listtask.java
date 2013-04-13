/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martha Monica
 */
@WebServlet(name = "listtask", urlPatterns = {"/listtask"})
public class listtask extends HttpServlet {

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
        try {
            String category = request.getParameter("category");
            String creator = "";
            Connection conn = null;
            PrintWriter out = response.getWriter();
            String output = "";

            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            HttpSession session = request.getSession(true);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Creator FROM category WHERE IDCategory='" + category
                    + "' UNION DISTINCT SELECT Username FROM authority WHERE IDCategory='" + category + "'");

            while (rs.next()) {
                
                if (rs.getString(1).compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
                    
                    creator = rs.getString(1);
                }
                
            }
            
            rs = stmt.executeQuery("SELECT * FROM task WHERE IDCategory='" + category + "'");
            int i = 0;

            while (rs.next()) {
                output = output + "<a class=\"listTugas\">";
                  
                if (rs.getString(6).compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
                    output = output + "<img class='deltask' src='img/delete.png' onclick='deleteTaskYeys("
                            + rs.getString(1)+");'>" ;
                
                }

                output = output + "<br><b>TaskName : </b><br><div class='showRin' onclick=' showRinciTugas("
                        + rs.getString(1) + "'>" + rs.getString(3) + "</div><br><b>Deadline : </b><br>"
                        + rs.getString(5) + "<br><b>Tag : </b><br>";

                  
                Statement statements = conn.createStatement();
                ResultSet data = statements.executeQuery(
                        "SELECT tag.* FROM tag,tasktag WHERE tag.IDTag=tasktag.IDTag AND tasktag.IDTask='"
                        + rs.getString(1) + "'");
                while (data.next()) {
                    output = output + data.getString(2) + "<br>";
                }
                output = output + "<br><b>Status : </b><br><input type='checkbox' id='taskstat" + i + "'";

                if (rs.getString(4).compareToIgnoreCase("done") == 0) {
                    output = output +" checked='checked'";
                }
                output = output + "onclick='changeStatus(this, " + rs.getString(1) + "," + i
                        + ");'> <span id='checkedvalue" + i + "'>"
                        + rs.getString(4) + "</span></a>";
                i++;
            }
            if (creator.compareToIgnoreCase(session.getAttribute("username").toString()) == 0) {
                output = output + "<a onclick='showBuatTugas(" + category
                        + ",'" + session.getAttribute("username") + "');' class='addTask'></a>";
               
            }
             out.write(output);
           

        } catch (SQLException ex) {
            Logger.getLogger(listtask.class.getName()).log(Level.SEVERE, null, ex);
        }
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
