/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ConnectDB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author LCF
 */
public class Search extends HttpServlet {

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
            out.println("<title>Servlet Search</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Search at " + request.getContextPath() + "</h1>");
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("aksi").equals("cari")) {
            if (request.getParameter("key").equals("username")) {
                try {
                    hasil_username(request.getParameter("value"), 0, 10, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("key").equals("kategori")) {
                try {
                    hasil_kategori(request.getParameter("value"), 0, 10, response);
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (request.getParameter("key").equals("task")) {
            } else if (request.getParameter("key").equals("email")) {
            } else if (request.getParameter("key").equals("komentar")) {
            } else if (request.getParameter("key").equals("semua")) {
            }
        }
        else{
            if(request.getParameter("key").equals("username")){
                try {
                    suggest_username(request.getParameter("value"), response);
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(request.getParameter("key").equals("kategori")){
                try {
                    suggest_kategori(request.getParameter("value"), response);
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(request.getParameter("key").equals("task")){
                try {
                    suggest_task(request.getParameter("value"), response);
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(request.getParameter("key").equals("semua")){
                try {
                    suggest_all(request.getParameter("value"), response);
                } catch (SQLException ex) {
                    Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
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

    private void hasil_username(String input, int limit1, int limit2, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();
        String query = "SELECT idaccounts, username, nama_lengkap, avatar FROM accounts WHERE username like '%" + input + "%' LIMIT " + limit1 + ", " + limit2;

        String[][] hasil = ConnectDB.getHasilQuery(query);
        for (int i = 0; i < hasil.length; i++) {
            out.println("<div class='hasil_username'>");

            out.println("<div class='user_ava'>");
            out.println("<img src=''>");
            out.println("</div>");

            out.println("<div class='username'>");
            out.println(hasil[i][1]);
            out.println("</div>");

            out.println("<div class='fullname'>");
            out.println(hasil[i][2]);
            out.println("</div>");


            out.println("</div>");
        }
    }

    private void hasil_kategori(String input, int limit1, int limit2, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();
        String query = "SELECT nama FROM kategori WHERE nama like '%" + input + "%' LIMIT " + limit1 + ", " + limit2;
        String[][] hasil = ConnectDB.getHasilQuery(query);
        for (int i = 0; i < hasil.length; i++) {
            out.println("<div class='category_block'>");
            out.println("<div class='category_pic'>");
            out.println("<img src='images/Book-icon.png' alt=''/>");
            out.println("</div>");

            out.println("<div class='category_name'>");
            out.println(hasil[i][0]);
            out.println("</div>");
            out.println("</div>");
        }
    }

    private void hasil_task(String input, int limit1, int limit2, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();
        String query = "SELECT DISTINCT idtugas, tugas.nama, deadline, status_selesai FROM tag, tugas, tugas_has_tag WHERE (tugas_idtugas = idtugas AND tag.nama like '%" + input + "%' AND tag_idtag = idtag) OR (tugas.nama LIKE '%" + input + "%') LIMIT " + limit1 + ", " + limit2;
        String[][] hasil = ConnectDB.getHasilQuery(query);
        for (int i = 0; i < hasil.length; i++) {
        }
    }

    private void suggest_username(String input, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();

        String query = "SELECT idaccounts, username, avatar FROM accounts WHERE username like '%" + input + "%' LIMIT 0,10";
        String[][] hasil = ConnectDB.getHasilQuery(query);
        for (int i = 0; i < hasil.length; i++) {
            out.println("<div class='hasil_suggest'>");
            out.println(hasil[i][1]);
            out.println("</div>");
        }
    }

    private void suggest_kategori(String input, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();

        String query = "SELECT idkategori, nama FROM kategori WHERE nama like '%" + input + "%' LIMIT 0,10";
        String[][] hasil = ConnectDB.getHasilQuery(query);
        for (int i = 0; i < hasil.length; i++) {
            out.println("<div class='hasil_suggest'>");
            out.println(hasil[i][1]);
            out.println("</div>");
        }
    }

    private void suggest_task(String input, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();

        String query = "SELECT DISTINCT idtugas, tugas.nama FROM tag, tugas, tugas_has_tag WHERE (tugas_idtugas = idtugas AND tag.nama like '%" + input + "%' AND tag_idtag = idtag) OR (tugas.nama LIKE '%" + input + "%') LIMIT 0, 10";
        String[][] hasil = ConnectDB.getHasilQuery(query);
        for (int i = 0; i < hasil.length; i++) {
            out.println("<div class='hasil_suggest'>");
            out.println(hasil[i][1]);
            out.println("</div>");
        }
    }

    //masih error
    private void suggest_all(String input, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<fieldset>");
        out.println("<legend>Username</legend>");
        suggest_username(input, response);
        out.println("</fieldset>");

        out.println("<fieldset>");
        out.println("<legend>Kategori</legend>");
        suggest_kategori(input, response);
        out.println("</fieldset>");

        out.println("<fieldset>");
        out.println("<legend>Task</legend>");
        suggest_task(input, response);
        out.println("</fieldset>");
    }
}
