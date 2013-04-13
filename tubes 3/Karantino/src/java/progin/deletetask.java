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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chidx
 */
@WebServlet(name = "deletetask", urlPatterns = {"/deletetask"})
public class deletetask extends HttpServlet {

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
            out.println("<title>Servlet deletetask</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet deletetask at " + request.getContextPath() + "</h1>");
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
        PrintWriter out = response.getWriter();
        //String namatask = request.getParameter("task");
        String namatask = (String) request.getSession(false).getAttribute("namatask");
        //String user = request.getParameter("user");
        String user = (String) request.getSession(false).getAttribute("user");
        String url = "jdbc:mysql://localhost:3306/progin_405_13510074";
        Connection conn;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            // delete assign
            PreparedStatement pst = conn.prepareStatement("DELETE FROM asigner WHERE username=? AND namatugas=?");
            pst.setString(1, user);
            pst.setString(2, namatask);
            pst.executeUpdate();
            // delete komen
            pst = conn.prepareStatement("DELETE FROM comment WHERE username=? AND namatugas=?");
            pst.setString(1, user);
            pst.setString(2, namatask);
            pst.executeUpdate();
            // delete tag
            pst = conn.prepareStatement("DELETE FROM tag WHERE username=? AND namatugas=?");
            pst.setString(1, user);
            pst.setString(2, namatask);
            pst.executeUpdate();
            // delete attachment
            
            // delete task
            pst = conn.prepareStatement("DELETE FROM tugas WHERE username=? AND namatugas=?");
            pst.setString(1, user);
            pst.setString(2, namatask);
            pst.executeUpdate();
            
            // send response
            //out.println(user+" "+namatask);
            out.println("<html>");
            out.println("<head>");
            out.println("<script type='text/javascript'>");
            out.println("function redirect(){");
            out.println("alert('Task deleted');");
            out.println("window.location = 'dashboard.jsp';");
            out.println("}");
            out.println("redirect();");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            //out.println(user+" "+namatask);
            out.println("</body>");
            out.println("</html>");
            pst.close();
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
