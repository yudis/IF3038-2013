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

@WebServlet(name = "changeStatus", urlPatterns = {"/changeStatus"})
public class changeStatus extends HttpServlet {

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
            
            Connection conn = null;
            PrintWriter out = response.getWriter();


            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510020", "root", "");
            HttpSession session = request.getSession(true);
            Statement stmt = conn.createStatement();
            if ((request.getParameter("IDTask") != null) && (request.getParameter("Status") != null)) {
                if ((request.getParameter("Status").compareToIgnoreCase("done"))==0) {
                    ResultSet rs = stmt.executeQuery("UPDATE task SET Status='undone' WHERE IDTask='"+request.getParameter("Status")+"'");
                }
                else{
                ResultSet rs = stmt.executeQuery("UPDATE task SET Status='done' WHERE IDTask='"+request.getParameter("Status")+"'");
                }
            }
            out.write(request.getParameter("Status"));

        } catch (SQLException ex) {
            Logger.getLogger(changeStatus.class.getName()).log(Level.SEVERE, null, ex);
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
        if ((request.getParameter("Status")!=null) &&(request.getParameter("IDTask")!=null)) {
            if (request.getParameter("Status").compareToIgnoreCase("done")==0){
                
            }
        }
//       if ($_GET['Status'] =="done")
//        {$queryStatus= "UPDATE task SET Status=\"undone\" WHERE IDTask=\"".$_GET['IDTask']."\"";}
//       else
//        {$queryStatus= "UPDATE task SET Status=\"done\" WHERE IDTask=\"".$_GET['IDTask']."\"";}
//        $query = mysql_query($queryStatus);
//        echo ($_GET['Status']);
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
