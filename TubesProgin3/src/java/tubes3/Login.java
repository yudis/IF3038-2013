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
import javax.servlet.http.Cookie;

/**
 *
 * @author Devin
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public Login(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }
    
    public int canLogin(String username, String pass) {
        try {
            ResultSet result = db.coba(connection, "select count(*) from pengguna where username='" + username + "' and password='" + pass + "'");
            result.first();
            if (result.getInt("count(*)") > 0)
                return 1;
            else
                return 0;
        } catch (Exception ex) {
            System.out.println("Exception is ;" + ex);
        }
        return -1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/plain");
        String username, pass;
        if ((username = request.getParameter("user")) != null && (pass = request.getParameter("pass")) != null) {
            if (canLogin(username, pass) == 1) {
                request.getSession().setAttribute("bananauser", username);
                Cookie bananauser = new Cookie("bananauser", username);
                bananauser.setMaxAge(60 * 60 * 24 * 30);
                response.addCookie(bananauser);
                out.print("success");
            }
            else if (canLogin(username, pass) == 0)
                out.print("notsuccess");
            else
                out.print("error");
        }
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