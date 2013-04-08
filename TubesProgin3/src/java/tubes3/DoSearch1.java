/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Raymond
 */
@WebServlet(name = "DoSearch1", urlPatterns = {"/DoSearch1"})
public class DoSearch1 extends HttpServlet {
int i;

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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ResultSet rs;
        //System.out.println(request.getParameter("keyword"));
        if ((request.getParameter("filter") != null)
                && (request.getParameter("keyword") != null)
                && (!request.getParameter("keyword").equals("Enter search query"))) {
            String user = request.getParameter("user");
            String filter = request.getParameter("filter");
            String keyword = request.getParameter("keyword");
            if(request.getParameter("continue").equals("true")){
                i++;
            }else{
                i=1;
            }
            int awal = 10 * (i - 1);
            Tubes3Connection tu = new Tubes3Connection();
            Connection connection = tu.getConnection();
            String queryUser = "SELECT username FROM pengguna WHERE username LIKE('%" + keyword + "%') ORDER BY username LIMIT " + awal + ", 10;";
            String queryAllTugas = "SELECT IDTask, name, deadline, stat, username, tag FROM `tugas` WHERE name LIKE '%" + keyword + "%' OR tag LIKE '%" + keyword + "%' LIMIT " + awal + ", 10;";
            System.out.println("DOOOO:" + queryAllTugas);
            String queryKategori = "SELECT judul FROM kategori WHERE judul LIKE('" + keyword + "%') ORDER BY judul LIMIT " + awal + ", 10;";
            boolean satu, dua, tiga;
            try {
                if ("semua".equals(filter)) {
                    rs = tu.coba(connection, queryAllTugas);
                    satu = rs.first();
                    if (satu) {
                        rs.previous();
                        out.print("<input type='text' id='tugas' class='hidden' value='" + keyword + "'><li class='Task1'>");
                        out.print("<div>");
                        out.print("<b class='design1'>Daftar Tugas</b>");
                        while (rs.next()) {
                            out.print("<div>");
                            if (user.equals(rs.getString("username"))) {
                                out.print("<div>");
                                out.print("<input type='checkbox' value='None' id='checklist" + rs.getString("username") + "' name='check' onchange='changevalues(" + rs.getString("username") + ")'");
                                if (rs.getString("stat").equals("1")) {
                                    out.print("checked");
                                }
                                out.print("/>");
                            }
                            out.print("<a class='list' href='taskdetails.php?id=" + rs.getString("IDTask") + "'><span>Tag:" + rs.getString("tag") + "</span>" + rs.getString("name") + "</a>");

                            out.print("<p>" + rs.getString("deadline") + "</p></div>");
                        }
                        out.print("</li>");

                    }
                    rs = tu.coba(connection, queryKategori);
                    dua = rs.first();
                    if (dua) {
                        rs.previous();
                        System.out.println("DUA");
                        out.print("<input type='text' class='hidden' id='kategori' value='" + keyword + "'><li class='Task1'>");
                        out.print("<div><b class='design1'>Daftar Kategori</b></div>");
                        while (rs.next()) {
                            out.print("<div><p>" + rs.getString("judul") + "</p></div>");
                        }
                        out.print("</li>");
                    }
                    rs = tu.coba(connection, queryUser);
                    tiga = rs.first();
                    if (tiga) {
                        rs.previous();
                        out.print("<input type='text' id='user' class='hidden' value='{$keyword}'><li class='Task1'>");
                        out.print("<div><b class='design1'>Daftar User</b></div>");
                        while (rs.next()) {
                            out.print("<div><a class='list' href='profile.php?username='" + rs.getString("username") + "'>" + rs.getString("username") + "</a></div>");
                        }
                        out.print("</li>");
                    }
                } else if ("username".equals(filter)) {
                    rs = tu.coba(connection, queryUser);
                    tiga = rs.first();
                    if (tiga) {
                        rs.previous();
                        out.print("<input type='text' id='user' class='hidden' value='{$keyword}'><li class='Task1'>");
                        out.print("<div><b class='design1'>Daftar User</b></div>");
                        while (rs.next()) {
                            out.print("<div><a class='list' href='profile.php?username='" + rs.getString("username") + "'>" + rs.getString("username") + "</a></div>");
                        }
                        out.print("</li>");
                    }
                } else if ("judul".equals(filter)) {
                    rs = tu.coba(connection, queryKategori);
                    dua = rs.first();
                    if (dua) {
                        rs.previous();
                        System.out.println("DUA");
                        out.print("<input type='text' class='hidden' id='kategori' value='" + keyword + "'><li class='Task1'>");
                        out.print("<div><b class='design1'>Daftar Kategori</b></div>");
                        while (rs.next()) {
                            out.print("<div><p>" + rs.getString("judul") + "</p></div>");
                        }
                        out.print("</li>");
                    }
                } else if ("task".equals(filter)) {
                    rs = tu.coba(connection, queryAllTugas);
                    satu = rs.first();
                    if (satu) {
                        rs.previous();
                        out.print("<input type='text' id='tugas' class='hidden' value='" + keyword + "'><li class='Task1'>");
                        out.print("<div>");
                        out.print("<b class='design1'>Daftar Tugas</b>");
                        while (rs.next()) {
                            out.print("<div>");
                            if (user.equals(rs.getString("username"))) {
                                out.print("<div>");
                                out.print("<input type='checkbox' value='None' id='checklist" + rs.getString("username") + "' name='check' onchange='changevalues(" + rs.getString("username") + ")'");
                                if (rs.getString("stat").equals("1")) {
                                    out.print("checked/>");
                                }
                            }
                            out.print("<a class='list' href='taskdetails.php?id=" + rs.getString("IDTask") + "'><span>Tag:" + rs.getString("tag") + "</span>" + rs.getString("name") + "</a>");

                            out.print("<p>" + rs.getString("deadline") + "</p></div>");
                        }
                        out.print("</li>");

                    }
                }
            } catch (Exception ex) {
                System.out.println("Exception is ;" + ex);
            } finally {
                out.close();
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
