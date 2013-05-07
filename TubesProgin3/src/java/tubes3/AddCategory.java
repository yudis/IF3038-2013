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
@WebServlet(name = "AddCategory", urlPatterns = {"/AddCategory"})
public class AddCategory extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public AddCategory(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }
    
    public int addCateg(String nama, String maker, String users) {
        users = users.replaceAll("\\s", "");
        String userlist[] = users.split(",");
        int IDKategori = 0;
        try {
            db.nonReturnQuery(connection, "INSERT INTO kategori(judul, username) VALUES ('" + nama + "', '" + maker + "')");
            ResultSet qresult = db.coba(connection, "SELECT LAST_INSERT_ID()");
            qresult.first();
            IDKategori = qresult.getInt("LAST_INSERT_ID()");
            db.nonReturnQuery(connection, "INSERT INTO usercateg VALUES ('" + IDKategori + "', '" + maker + "')");
            for(String user : userlist)
                db.nonReturnQuery(connection, "INSERT INTO usercateg VALUES ('" + IDKategori + "', '" + user + "')");
            return IDKategori;
        }
        catch (Exception ex) {
            System.out.println("Exception is ;" + ex);
        }
        return -1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.write(addCateg(request.getParameter("name"), (String)request.getSession().getAttribute("bananauser"), request.getParameter("users")));
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
