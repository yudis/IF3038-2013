/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Sonny Theo Thumbur
 */
public class Suggestion extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            // TODO output your page here
            String key = request.getParameter("k");
            ArrayList<String> suggestList = getSuggestionList();

            for (int i=0; i<suggestList.size(); i++){
                if (suggestList.get(i).substring(0, key.length()).equals(key)) {
                    out.println(suggestList.get(i) + " | ");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally { 
            out.close();
        }
    } 

    public ArrayList<String> getSuggestionList() throws SQLException{
        String host = "jdbc:mysql://localhost:3306/progin_405_13510027";
        String uName = "progin";
        String uPass = "progin";

        System.out.println("masuk sini");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Exception : Kelas tidak ditemukan");
        }
        Connection con = DriverManager.getConnection(host, uName, uPass);

        System.out.println("koneksi aman");

        Statement stmt = (Statement) con.createStatement();
        String SQL = "SELECT namaTask FROM task";
        ResultSet rs = stmt.executeQuery(SQL);

        ArrayList<String> suggestList = new ArrayList<String>();
        while (rs.next()){
            suggestList.add(rs.getString(1));
        }

        return suggestList;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
