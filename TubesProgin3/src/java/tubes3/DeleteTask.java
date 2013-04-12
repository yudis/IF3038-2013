/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Devin
 */
@WebServlet(name = "DeleteTask", urlPatterns = {"/DeleteTask"})
public class DeleteTask extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public DeleteTask(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String user = (String)request.getSession().getAttribute("bananauser");
        String id = request.getParameter("id");
        try {
            ResultSet result = db.coba(connection, "SELECT username FROM tugas WHERE IDTask='" + id + "'");
            result.first();
            String maker = result.getString("username");
            db.nonReturnQuery(connection, "DELETE FROM penugasan WHERE username='" + user + "' AND IDTask='" + id + "'");
            if(user.equals(maker)) {
                db.nonReturnQuery(connection, "DELETE FROM komentar WHERE IDTask='" + id + "'");
                db.nonReturnQuery(connection, "DELETE FROM pelampiran WHERE IDTugas='" + id + "'");
                db.nonReturnQuery(connection, "DELETE FROM penugasan WHERE IDTask='" + id + "'");
                db.nonReturnQuery(connection, "DELETE FROM tugas WHERE IDTask='" + id + "'");
            }
        }
        catch (Exception ex) {
            System.out.println("Exception is ;" + ex);
        }
        out.print("1");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

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
