/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard_Handlers;

import ConnectDB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        try {
            showKategori("1", response);
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
        try {
            processRequest(request, response);

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

    private void buatKategori() {
    }

    private void showKategori(String userID, HttpServletResponse response) throws ServletException, SQLException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<div id='category_head'>");
        out.println("Kategori");
        out.println("</div>");
        String query = "SELECT nama, idkategori, pembuat FROM kategori, assignee_has_kategori WHERE idkategori = kategori_idkategori AND accounts_idaccounts = " + userID;
        String[][] hasil = ConnectDB.jalankanQuery(query);
        out.println(hasil[1][2]);
        
        //connect ke DB
//        Connection connection = null;
//        Statement statement = null;
//        ResultSet resultSet = null;
//
//        String query = "SELECT nama, idkategori, pembuat FROM kategori, assignee_has_kategori WHERE idkategori = kategori_idkategori AND accounts_idaccounts = " + userID;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            connection = DriverManager.getConnection(DBInfo.dbURL, DBInfo.dbUsername, DBInfo.dbPassword);
//            statement = connection.createStatement();
//            resultSet = statement.executeQuery(query);
//            //show hasil
//            int jumlah_hasil = 0;
//            while (resultSet.next()) {
//                out.println("<div class='category_block'>");
//                if (resultSet.getInt("pembuat") == 1) {
//                    out.println("<div class='tombol_hapus_kategori'>");
//                    out.println("X");
//                    out.println("</div>");
//                }
//                out.println("<div class='category_pic'>");
//                out.println("<img src='images/Book-icon.png' alt=''/>");
//                out.println("</div>");
//                out.println("<div class='category_name'>");
//                out.println(resultSet.getString("nama"));
//                out.println("</div>");
//                out.println("</div>");
//                jumlah_hasil++;
//            }
//            out.println("<div class=\"category_block\" id=\"tambah_kategori\" onclick=\"location.href='#category_form'\">");
//            out.println("<div class=\"category_pic\">");
//            out.println("<img src=\"images/tambah.png\" alt=''/>");
//            out.println("</div>");
//            out.println("<div class=\"category_name\">");
//            out.println("Tambah kategori...");
//            out.println("</div>");
//            out.println("</div>");
//
//            if (jumlah_hasil > 0) {
//                out.println("<div class=\"category_block\" id=\"tambah_kategori\" onclick=\"location.href='#category_form'\">");
//                out.println("<div class=\"category_pic\">");
//                out.println("<img src=\"images/hapus.png\" alt=''/>");
//                out.println("</div>");
//                out.println("<div class=\"category_name\">");
//                out.println("Hapus kategori...");
//                out.println("</div>");
//                out.println("</div>");
//            }
//        } catch (SQLException e) {
//            out.print("error");
//            throw new ServletException("Servlet Could not display records.", e);
//        } catch (ClassNotFoundException e) {
//            throw new ServletException("JDBC Driver not found.", e);
//        } finally {
//            if (resultSet != null) {
//                resultSet.close();
//                resultSet = null;
//            }
//            if (statement != null) {
//                statement.close();
//                statement = null;
//            }
//            if (connection != null) {
//                connection.close();
//                connection = null;
//            }
//        }
//        
    }

    private void addKategori(String namaKategori, String idPembuat, String assignee) throws ServletException {
        //query bikin kategori baru
        
        String query1 = "INSERT INTO kategori (nama, pembuat) VALUES('\".$_POST[\"catname\"].\"',\".$_POST[\"pembuat\"].\")";
        

        //query update assignee_has_kategori masukin pembuat sebagai assignee


        //query update assignee_has_kategori masukin semua assignee
    }

    /*
     * Fungsi bantuan untuk jalanin query.
     */
//    private Object[][] jalankanQuery(String query) throws ServletException, SQLException {
//        Connection con = null;
//        Statement st = null;
//        ResultSet rs = null;
//        ResultSetMetaData metadata;
//        int jumlah_kolom = 0;
//        ArrayList<Object[]> data = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            con = DriverManager.getConnection(ConnectDB.dbURL, ConnectDB.dbUsername, ConnectDB.dbPassword);
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            metadata = rs.getMetaData();
//            jumlah_kolom = metadata.getColumnCount();
//            data = new ArrayList<Object[]>();
//
//            while (rs.next()) {
//                Object[] row = new Object[jumlah_kolom];
//                for (int i = 0; i < jumlah_kolom; i++) {
//                    row[i] = rs.getObject(i + 1);
//                }
//                data.add(row);
//            }
//        } catch (SQLException e) {
//            throw new ServletException("Servlet Could not display records.", e);
//        } catch (ClassNotFoundException e) {
//            throw new ServletException("JDBC Driver not found.", e);
//        } finally {
//            if (rs != null) {
//                rs.close();
//                rs = null;
//            }
//            if (st != null) {
//                st.close();
//                st = null;
//            }
//            if (con != null) {
//                con.close();
//                con = null;
//            }
//        }
//        Object[][] hasil = new Object[data.size()][jumlah_kolom];
//        for (int i = 0; i < data.size(); i++) {
//            System.arraycopy(data.get(i), 0, hasil[i], 0, jumlah_kolom);
//        }
//
//        return hasil;
//    }
}
