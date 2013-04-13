/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package BuatTask;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Christianto
 */
@WebServlet(name = "addTugas", urlPatterns = {"/addTugas"})
@MultipartConfig(location = "")
public class addTugas extends HttpServlet {
    private Connection conn;
    private Statement query;
    int status;
    
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
            /* TODO output your page here. You may use following sample code. */            
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003", "root", "");
            query = conn.createStatement();
            
            ResultSet result = query.executeQuery("SELECT MAX(id_tugas) from tugas");
            result.first();
            int id_tugas = result.getInt(1)+1;
            result.close();
            
            String attachment="";
            Part file = request.getPart("file");
            if (file != null) {
                String filename="";
                for (String cd : file.getHeader("content-disposition").split(";")) {
                    if (cd.trim().startsWith("filename")) {
                        filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                        filename = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
                    }
                }            

                out.print(filename+"\n");
                InputStream masuk = file.getInputStream();
                out.print(getServletContext().getRealPath("/attach/"+filename)+"\n");
                File f = new File(getServletContext().getRealPath(""),"/attach/"+filename);
                FileOutputStream tulis = new FileOutputStream(f);
                out.print(f.getAbsolutePath()+"\n");
                
                byte[] baca = new byte[1024];
                int sem;
                while ((sem = masuk.read(baca)) != -1) {
                    //out.println(sem+" ");
                    tulis.write(baca, 0, sem);
                }
                tulis.close();
                attachment = "attach/"+filename;
            }
            
            //out.println(id_tugas);
            String mau = "INSERT INTO tugas"
                    + "(status,id_kategori,deadline,tag,username,nama_tugas,id_tugas,attachment)"
                    + " VALUES(0,"+request.getParameter("id_kategori")+",'"
                    + request.getParameter("date")+" "+request.getParameter("hour")+":"+request.getParameter("minute")+":00"
                    + "','"+ request.getParameter("tag")+"','"+request.getParameter("username")
                    + "','"+ request.getParameter("task_name")+"',"+id_tugas+",'"+attachment+"')";
            
            out.println(mau);
            //int hasil = query.executeUpdate(mau);

            //status = 0;
            
            //String[] orang = request.getParameter("Assignee").split("/");
            //for (int i=0;i<orang.length;++i) {
            //    hasil = query.executeUpdate("INSERT INTO mengerjakan('username',id_tugas'','status_tugas')"
            //            + " VALUES ('"+orang[i]+"',"+id_tugas+","+status+")");
            //}

            //response.sendRedirect("rinciantugas.jsp?id_tugas="+id_tugas);
        } catch (ClassNotFoundException ex) {
            out.println("Failed to create connection");
        } catch (SQLException ex) {
            out.println("Failed to execute SQL query");
            out.println(ex.getLocalizedMessage());
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
