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
@WebServlet(name = "Validator", urlPatterns = {"/Validator"})
public class Validator extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public Validator(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }
    
    public int checkUser(String username) {
        if (!username.equals("")) {
            try {
                ResultSet result = db.coba(connection, "select count(*) from pengguna where username='" + username + "'");
                result.first();
                if (result.getInt("count(*)") > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception ex) {
                System.out.println("Exception is ;" + ex);
            }
        }
        return -1;
    }

    public int checkEmail(String email) {
        if (!email.equals("")) {
            try {
                ResultSet result = db.coba(connection, "select count(*) from pengguna where email='" + email + "'");
                result.first();
                if (result.getInt("count(*)") > 0) {
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception ex) {
                System.out.println("Exception is ;" + ex);
            }
        }
        return -1;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username, email;
        if ((username = request.getParameter("username")) != null) {
            if (checkUser(username) == 1) {
                out.print("notfree");
            } else if (checkUser(username) == 0) {
                out.print("free");
            } else {
                out.print("error");
            }
        }
        if ((email = request.getParameter("email")) != null) {
            if (checkEmail(email) == 1) {
                out.print("notfree");
            } else if (checkEmail(email) == 0) {
                out.print("free");
            } else {
                out.print("error");
            }
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
