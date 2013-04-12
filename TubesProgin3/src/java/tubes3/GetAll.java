/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashSet;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raymond
 */
@WebServlet(name = "GetAll", urlPatterns = {"/GetAll"})
public class GetAll extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

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
        String nama = request.getParameter("nama");
        HashSet<String> set = new HashSet<String>();
        if ((nama != null) && (!nama.equals(""))) {
            ResultSet rs;
            try {
                db = new Tubes3Connection();
                connection = db.getConnection();
                String queryUser = "SELECT DISTINCT * FROM pengguna  WHERE username LIKE '%" + nama + "%' OR email LIKE '%" + nama + "%' OR birthday LIKE '%" + nama + "%' OR fullname LIKE '%" + nama + "%'";
                System.out.println(queryUser);
                rs = db.coba(connection, queryUser);
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnsNumber = rsmd.getColumnCount();
                String temp = "";
                String temp2;

                while (rs.next()) {
                    //out.print(rs.getString("name")+"\n");
                    for (int i = 0; i < columnsNumber; i++) {
                        if (!temp.equals(temp2 = rs.getString(i+1).toLowerCase())) {
                            temp = temp2;
                            set.add(temp);
                        }
                    }
                }
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    out.println(iter.next());
                }
            } catch (Exception ex) {
                System.out.println("Exception is ;" + ex);
            } finally {
                out.close();
            }
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
        processRequest(request, response);
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
        processRequest(request, response);
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
