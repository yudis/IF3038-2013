/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javax.servlet.http.HttpSession;
import org.apache.catalina.Session;

/**
 *
 * @author M Afif Al Hawari
 */
@WebServlet(name = "generatecomment", urlPatterns = {"/generatecomment"})
public class generatecomment extends HttpServlet {

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
        
        Connection conn = null;
        PrintWriter out = response.getWriter();
        int IDTask = Integer.parseInt(request.getParameter("IDTask"));
        int pages = Integer.parseInt(request.getParameter("page"));
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            
            PreparedStatement ps = conn.prepareStatement(
                    "SELECT * FROM comment WHERE IDTask=?");
            ps.setInt(1, IDTask);

            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                count++;
            }
            int pagecount = count / 10;
            if (count % 10 > 0) {
                pagecount++;
            }
            out.print("<br/>Comment ('" + count + "') :<br/>");
            out.print("<div class=\"komentar\" id=\"isikomentar\">");
            out.print("<hr>");

            ps = conn.prepareStatement(
                    "SELECT * FROM comment WHERE IDTask=?");
            ps.setInt(1, IDTask);
            rs = ps.executeQuery();

            int countpage = pages;
            if (countpage > 1) {
                countpage--;
                for (int x = 0; x < 10; x++) {
                    rs.next();
                }
            }

            int x = 0;
            while (rs.next()) {
                if (x < 0) {
                    x++;
                    out.print("<div class=\"commentyeah\">");
                    out.print("<div class=\"commentcontent\">" + rs.getString("Content") + "</div>");

                    ps = conn.prepareStatement(
                            "SELECT * FROM user WHERE username=?");
                    ps.setString(1, rs.getString("Username"));
                    ResultSet res = ps.executeQuery();
                    res.next();
                    String[] ArrayTime = (rs.getString("Timestamp")).split(" ");
                    String[] ArrayDate = ArrayTime[0].split("-");
                    String[] ArrayTimeHour = ArrayTime[1].split(":");
                    
                    String time = ArrayTimeHour[0]+":"+ArrayTimeHour[1]+" - "+ArrayDate[2]+"/"+ArrayDate[1];
                    
                    out.print("<div class=\"commentinfo\"> <img width=30px height=30px src=\""+res.getString("Avatar")+"\" /> <a href=\"profile.php?user=\""+rs.getString("Username")+"\">");
                    HttpSession session = request.getSession(true);
                    if(res.getString("Username").compareTo((String)session.getAttribute("username"))==0){
                        out.print("<br/><input type=\"button\" class=\"remove\" onclick=\"removeComment("+rs.getString("IDComment")+")\" value=\"remove\">");
                    }
                    out.print("</div></div><hr>");
                }
            }
            out.print("</div><br/>");
            out.print("<div id=\"commentpage\" >");
            x=1;
            out.print("1");
            for(x=1;x<=pagecount;x++){
                if(x==pagecount){
                    out.print("<a class=\"commentselected\" onclick=\"setPage("+x+")\">["+x+"]</a>");
                }else{
                    out.print("<a onclick=\"setPage("+x+")\">["+x+"]</a>");
                }
            }
            out.print("</div></div>");
        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
        } finally {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Can not close connection");
            }
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
