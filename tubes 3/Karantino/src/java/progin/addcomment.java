/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chidx
 */
@WebServlet(name = "addcomment", urlPatterns = {"/addcomment"})
public class addcomment extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet addcomment</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet addcomment at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {            
            out.close();
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
        //processRequest(request, response);
        
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
        //processRequest(request, response);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String url = "jdbc:mysql://localhost:3306/progin_405_13510074";
        Connection conn;
        String comment = request.getParameter("areacomment");
//        String penulis = request.getParameter("penulis");
//        String namatask = request.getParameter("namatask");
        String penulis = (String) request.getSession(false).getAttribute("user");
        String namatask = (String) request.getSession(false).getAttribute("namatask");
        System.out.println("comment "+comment);
        System.out.println("penulis "+penulis);
        System.out.println("namatask "+namatask);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            PreparedStatement ps = conn.prepareStatement("SELECT username FROM tugas WHERE namatugas=?");
            ps.setString(1, namatask);
            ResultSet rs = ps.executeQuery();
            System.out.println("hasil query : "+rs.toString());
            String user = "dummy1";
            System.out.println("user "+user);
            ps = conn.prepareStatement("INSERT INTO comment VALUES (?,?,?,?)");
            ps.setString(1, user);
            ps.setString(2, namatask);
            ps.setString(3, comment);
            ps.setString(4, penulis);
            ps.executeUpdate();
            ps = conn.prepareStatement("SELECT penulis, komentar FROM comment WHERE username=? AND namatugas=?");
            ps.setString(1, user);
            ps.setString(2, namatask);
            rs = ps.executeQuery();
//            while (rs.next()){
//                out.print("<pag><b>");
//                out.print(rs.getString(1));
//                out.print("</pag></b><br>");
//                out.print("<pah>");
//                out.print(rs.getString(2));
//                out.print("</pah><br>");
//            }
//            out.print("<form name='comment'>");
//            out.print("<p>");
//            out.print("<textarea id='areatulis' name='areacomment' rows='4' cols='34' placeholder='Tulis komentar anda'></textarea>");
//            out.print("</p>");
//            out.print("<input type='submit' id='submitdone' value='Tulis Komentar' onclick='addcomment('<% out.print(namatask);%>','<% out.print(user);%>)''>");
//            out.print("</form>");
            out.println("<html>");
            out.println("<head>");
            out.println("<script type='text/javascript'>");
            out.println("function redirect(){");
            //out.println("alert('Task edited');");
            out.println("window.location = 'rinciantugas.jsp';");
            out.println("}");
            out.println("redirect();");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("</body>");
            out.println("</html>");
            rs.close();
            ps.close();
            conn.close();
        } catch (Exception e){
            e.printStackTrace();
        }
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
