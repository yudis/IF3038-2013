/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.servlet.http.*;
import ConnectDB.ConnectDB;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
/**
 *
 * @author Yusuf Ardi
 */
@WebServlet(name = "editProfile", urlPatterns = {"/editProfile"})
public class editProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ConnectDB connect;

    public editProfile() {
        super();
        connect = new ConnectDB();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = session.getAttribute("username").toString();
        
        if (!request.getParameter("username").isEmpty()) {
            // query ganti fullname
        }
        
        if (!request.getParameter("password").isEmpty()) {
            // query ganti pass
        }
        
        if (!((request.getParameter("tanggal").equals("1")) && (request.getParameter("bulan").equals("1")) && (request.getParameter("tahun").equals("1955")))) {
            try {
                String createdate = request.getParameter("bulan")+"/"+request.getParameter("tanggal")+"/"+request.getParameter("tahun");
                Date birthdate = new SimpleDateFormat("MM/dd/yyyy").parse(createdate);
                
                // query ganti tanggal lahir
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        //InputStream filecontent = filePart.getInputStream();
        //FileOutputStream os = new FileOutputStream(getServletContext().getRealPath("/") + "upload/" + filename);
        
        Part filePart = request.getPart("avatar"); // Retrieves <input type="file" name="file">
        String filename = getFilename(filePart);
        String dir = "upload/" + filename;
        byte buf[] = new byte[1024 * 4];
        if (!filename.isEmpty()) {
            FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + "upload/" + filename);
            try {
                InputStream input = filePart.getInputStream();
                try {
                    while (true) {
                        int count = input.read(buf);
                        if (count == -1)
                            break;
                        output.write(buf, 0, count);
                    }
                } finally {
                    input.close();
                }
            } finally {
                output.close();
            }
                    
            // query ganti file attachment
        }

        RequestDispatcher view = request.getRequestDispatcher("/profile.jsp");
        view.forward(request, response);
    }
    
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }

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
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet editProfile</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet editProfile at " + request.getContextPath() + "</h1>");
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
