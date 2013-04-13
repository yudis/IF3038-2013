/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import ConnectDB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
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
public class Kategori extends HttpServlet {

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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Kategori</title>");
            out.println("</head>");
            out.println("<body>");

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
        HttpSession session = request.getSession();
        try {
            if (request.getParameter("aksi").equals("lihat")) {
                showKategori(request.getParameter("uid"), response);
            } else if (request.getParameter("aksi").equals("tambah")) {
                addKategori(request.getParameter("namaKategori"), (String) session.getAttribute("id"), request.getParameter("assignee"), response);
            } else if (request.getParameter("aksi").equals("hapus")) {
                deleteKategori((String) session.getAttribute("id"), response);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            addKategori(request.getParameter("nama"), (String) session.getAttribute("id"), request.getParameter("assignees"), response);
        } catch (SQLException ex) {
            Logger.getLogger(Kategori.class.getName()).log(Level.SEVERE, null, ex);
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

    private void showKategori(String userID, HttpServletResponse response) throws IOException, ServletException, SQLException {
        PrintWriter out = response.getWriter();
        out.println("<div id='category_head'>");
        out.println("Kategori");
        out.println("</div>");
        String query = "SELECT nama, idkategori, pembuat FROM kategori, assignee_has_kategori WHERE idkategori = kategori_idkategori AND accounts_idaccounts = " + userID;
        String[][] hasil = ConnectDB.getHasilQuery(query);

        for (int i = 0; i < hasil.length; i++) {
            out.println("<div class='category_block' onclick='showListTask(\"" + userID + "\", \"" + hasil[i][1] + "\")'>");
            if (hasil[i][2].equals(userID)) {
                out.println("<a href='Kategori?aksi=hapus&uid=" + hasil[i][1] + "'>");
                out.println("<div class='tombol_hapus_kategori'>");
                out.println("X");
                out.println("</div>");
                out.println("</a>");
            }
            out.println("<div class='category_pic'>");
            out.println("<img src='images/Book-icon.png' alt=''/>");
            out.println("</div>");
            out.println("<div class='category_name'>");
            out.println(hasil[i][0]);
            out.println("</div>");
            out.println("</div>");
        }
        out.println("<div class=\"category_block\" id=\"tambah_kategori\" onclick=\"location.href='#category_form' ; document.getElementById('input_assignees').value='';\">");
        out.println("<div class=\"category_pic\">");
        out.println("<img src=\"images/tambah.png\" alt=''/>");
        out.println("</div>");
        out.println("<div class=\"category_name\">");
        out.println("Tambah kategori...");
        out.println("</div>");
        out.println("</div>");
        if (hasil.length > 0) {
            out.println("<div class=\"category_block\" id=\"hapus_kategori\" onclick=\"show_del_cat()\">");
            out.println("<div class=\"category_pic\">");
            out.println("<img src=\"images/hapus.png\" alt=''/>");
            out.println("</div>");
            out.println("<div class=\"category_name\">");
            out.println("Hapus kategori...");
            out.println("</div>");
            out.println("</div>");
        }
    }

    private void addKategori(String namaKategori, String idPembuat, String assignee, HttpServletResponse response) throws ServletException, SQLException, IOException {
        //query bikin kategori baru

        String query1 = "INSERT INTO kategori (nama, pembuat) VALUES('" + namaKategori + "'," + idPembuat + ")";
        ConnectDB.jalankanQuery(query1);

        //query update assignee_has_kategori masukin pembuat sebagai assignee
        String query2 = "INSERT INTO assignee_has_kategori (accounts_idaccounts, kategori_idkategori) VALUES(" + idPembuat + ",(SELECT idkategori from kategori where nama = '" + namaKategori + "' and pembuat = " + idPembuat + "))";
        ConnectDB.jalankanQuery(query2);

        //query update assignee_has_kategori masukin semua assignee
        if (assignee.contains(", ")) {
            String[] list_assignee = assignee.split(", ");
            for (int i = 0; i < list_assignee.length; i++) {
                String query3 = "INSERT INTO assignee_has_kategori (accounts_idaccounts, kategori_idkategori) VALUES((SELECT idaccounts FROM accounts WHERE username = '" + list_assignee[i] + "'),(SELECT idkategori from kategori where nama = '" + namaKategori + "' and pembuat = " + idPembuat + "))";
                ConnectDB.jalankanQuery(query3);
            }
        }
        response.sendRedirect("dashboard.jsp");
    }

    private void deleteKategori(String idkategori, HttpServletResponse response) throws ServletException, SQLException, IOException {
        String query1 = "DELETE FROM accounts_has_tugas WHERE tugas_idtugas in (SELECT idtugas FROM tugas WHERE kategori_idkategori = " + idkategori + ")";
        String query2 = "DELETE FROM tugas_has_tag WHERE tugas_idtugas in (SELECT idtugas FROM tugas WHERE kategori_idkategori =" + idkategori + ")";
        String query3 = "DELETE FROM tugas WHERE kategori_idkategori = " + idkategori;
        String query4 = "DELETE FROM assignee_has_kategori WHERE kategori_idkategori = " + idkategori;
        String query5 = "DELETE FROM kategori WHERE idkategori = " + idkategori;

        ConnectDB.jalankanQuery(query1);
        ConnectDB.jalankanQuery(query2);
        ConnectDB.jalankanQuery(query3);
        ConnectDB.jalankanQuery(query4);
        ConnectDB.jalankanQuery(query5);
        response.sendRedirect("dashboard.jsp");
    }
}
