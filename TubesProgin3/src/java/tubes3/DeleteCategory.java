/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Devin
 */
@WebServlet(name = "DeleteCategory", urlPatterns = {"/DeleteCategory"})
public class DeleteCategory extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public DeleteCategory(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String id = request.getParameter("id");
        try {
            db.nonReturnQuery(connection, "DELETE FROM usercateg WHERE IDKategori='" + id + "';");
            db.nonReturnQuery(connection, "DELETE FROM komentar WHERE IDTask IN (SELECT IDTask FROM tugas WHERE IDKategori='" + id + "');");
            db.nonReturnQuery(connection, "DELETE FROM pelampiran WHERE IDTugas IN (SELECT IDTask FROM tugas WHERE IDKategori='" + id + "');");
            db.nonReturnQuery(connection, "DELETE FROM penugasan WHERE IDTask IN (SELECT IDTask FROM tugas WHERE IDKategori='" + id + "');");
            db.nonReturnQuery(connection, "DELETE FROM tugas WHERE IDKategori='" + id + "';");
            db.nonReturnQuery(connection, "DELETE FROM kategori WHERE IDKategori='" + id + "';");
        }
        catch (Exception ex) {
            System.out.println("Exception is ;" + ex);
        }
        response.setContentType("text/plain");
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
