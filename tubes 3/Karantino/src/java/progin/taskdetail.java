/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package progin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author chidx
 */
@WebServlet(name = "taskdetail", urlPatterns = {"/taskdetail"})
public class taskdetail extends HttpServlet {

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
            out.println("<title>Servlet taskdetail</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet taskdetail at " + request.getContextPath() + "</h1>");
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
        int tanggal, bulan, tahun;
        String asigner, tag, username, namatugas;
        PrintWriter out = response.getWriter();
        String url = "jdbc:mysql://localhost:3306/progin_405_13510074";
        Connection conn;
        try {
            HttpSession session = request.getSession(false);
            //username = (String) session.getAttribute("user");
            username = "dummy";
            namatugas = (String) session.getAttribute("namatask");
            tanggal = Integer.parseInt(request.getParameter("tanggal"));
            bulan = Integer.parseInt(request.getParameter("bulan"));
            tahun = Integer.parseInt(request.getParameter("tahun"));
            asigner = request.getParameter("asignee");
            tag = request.getParameter("tag");
            String kalender = ""+tahun+"-"+bulan+"-"+tanggal;
            Date myDate = java.sql.Date.valueOf(kalender);
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, "root", "");
            // update deadline
            PreparedStatement pst = conn.prepareStatement("UPDATE tugas SET deadline=? WHERE username=? AND namatugas=?");
            pst.setDate(1, myDate);
            pst.setString(2, username);
            pst.setString(3, namatugas);
            pst.executeUpdate();
            // update assigner
            pst = conn.prepareStatement("SELECT asignee FROM asigner WHERE username=? AND namatugas=?");
            pst.setString(1, username);
            pst.setString(2, namatugas);
            ResultSet rs = pst.executeQuery();
            String asigner_temp = "";
            while (rs.next()){
                asigner_temp += rs.getString(1);
                asigner_temp += ",";
            }
            if (asigner_temp.indexOf(',') >= 0)
                asigner_temp = asigner_temp.substring(0, asigner_temp.length()-1);
            if (asigner.compareTo(asigner_temp) != 0){
                // delete asigner lama, insert asigner baru
                pst = conn.prepareStatement("SELECT asignee FROM asigner WHERE username=? AND namatugas=?");
                pst.setString(1, username);
                pst.setString(2, namatugas);
                rs = pst.executeQuery();
                while (rs.next()){ // deleting old asigner
                    //String sql = "DELETE FROM asigner WHERE username="+username+" AND namatugas=" + namatugas + " AND asignee=" + rs.getString(1);
                    //pst.executeUpdate(sql);
                    pst = conn.prepareStatement("DELETE FROM asigner WHERE username=? AND namatugas=? AND asignee=?");
                    pst.setString(1, username);
                    pst.setString(2, namatugas);
                    pst.setString(3, rs.getString(1));
                    pst.executeUpdate();
                }
                String temp = asigner;
                while (asigner.indexOf(',') >= 0){ // inserting new asigner
                    temp = asigner.substring(0, asigner.indexOf(','));
                    //String sql = "INSERT INTO asigner VALUES ("+username+ "," + namatugas + "," +temp +")";
                    pst = conn.prepareStatement("INSERT INTO asigner VALUES (?,?,?)");
                    pst.setString(1, username);
                    pst.setString(2, namatugas);
                    pst.setString(3, temp);
                    pst.executeUpdate();
                    asigner = asigner.substring(asigner.indexOf(',')+1, asigner.length());
                }
                pst = conn.prepareStatement("INSERT INTO asigner VALUES (?,?,?)");
                pst.setString(1, username);
                pst.setString(2, namatugas);
                pst.setString(3, asigner);
                pst.executeUpdate();
            }  
            
            // update tag
            pst = conn.prepareStatement("SELECT tag FROM tag WHERE username=? AND namatugas=?");            
            pst.setString(1, username);
            pst.setString(2, namatugas);
            rs = pst.executeQuery();
            String tag_temp = "";
            while (rs.next()){
                tag_temp += rs.getString(1);
                tag_temp += ",";
            }
            if (tag_temp.indexOf(',') >= 0)
                tag_temp = tag_temp.substring(0, tag_temp.length()-1);
            if (tag.compareTo(tag_temp) != 0){
                // delete tag lama, insert tag baru
                pst = conn.prepareStatement("SELECT tag FROM tag WHERE username=? AND namatugas=?");
                pst.setString(1, username);
                pst.setString(2, namatugas);
                rs = pst.executeQuery();
                while (rs.next()){ // deleting old asigner
                    //String sql = "DELETE FROM tag WHERE username="+username+" AND namatugas=" + namatugas + " AND tag=" + rs.getString(1);
                    //pst.executeUpdate(sql);
                    pst = conn.prepareStatement("DELETE FROM tag WHERE username=? AND namatugas=? AND tag=?");
                    pst.setString(1, username);
                    pst.setString(2, namatugas);
                    pst.setString(3, rs.getString(1));
                    pst.executeUpdate();
                }
                String temp = tag;
                while (tag.indexOf(',') >= 0){ // inserting new asigner
                    temp = tag.substring(0, tag.indexOf(','));
                    //String sql = "INSERT INTO tag VALUES ("+username+ "," + namatugas + "," +temp +")";
                    //pst.executeUpdate(sql);
                    pst = conn.prepareStatement("INSERT INTO tag VALUES (?,?,?)");
                    pst.setString(1, username);
                    pst.setString(2, namatugas);
                    pst.setString(3, temp);
                    pst.executeUpdate();
                    tag = tag.substring(tag.indexOf(',')+1, tag.length());
                }
                pst = conn.prepareStatement("INSERT INTO tag VALUES (?,?,?)");
                pst.setString(1, username);
                pst.setString(2, namatugas);
                pst.setString(3, tag);
                pst.executeUpdate();
            }
            rs.close();
            pst.close();
            //conn.commit();
            conn.close();
             out.println("<html>");
             out.println("<head>");
             out.println("<script type='text/javascript'>");
             out.println("function redirect(){");
             out.println("alert('Task edited');");
             out.println("window.location = 'rinciantugas.jsp';");
             out.println("}");
             out.println("redirect();");
             out.println("</script>");
             out.println("</head>");
             out.println("<body>");
             out.println("</body>");
             out.println("</html>");
        } catch (Exception e){
            e.printStackTrace();
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
