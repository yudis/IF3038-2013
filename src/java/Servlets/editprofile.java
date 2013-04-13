/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.*;
import java.sql.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author user
 */
public class editprofile extends HttpServlet {

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
            out.println("<title>Servlet login2</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet login2 at " + request.getContextPath() + "</h1>");
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
            throws ServletException, IOException 
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Connection con = null;
        ResultSet rs = null;
        Statement s = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin","progin","progin");
            s = con.createStatement();
            HttpSession session = request.getSession();
            String usernama = (String) session.getAttribute("username");
            
            Scanner scan;
            Part p;
            
            p = request.getPart("namaleng");
            scan = new Scanner(p.getInputStream());
            String namalengkap;
            if (scan.hasNext())
                {
                    namalengkap = scan.nextLine();
                }
                    else
                {
                    namalengkap = "null";
                }
            scan.close();
            
            p = request.getPart("password");
            scan = new Scanner(p.getInputStream());
            String pass;
            if (scan.hasNext())
                {
                    pass = scan.nextLine();
                }
                    else
                {
                    pass = "null";
                }
            scan.close();
            
            p = request.getPart("tanggal");
            scan = new Scanner(p.getInputStream());
            String tgl;
            if (scan.hasNext())
                {
                    tgl = scan.nextLine();
                }
                    else
                {
                    tgl = "null";
                }
            scan.close();
            
            //Ambil data avatar
            p = request.getPart("avatar");
            InputStream is = p.getInputStream();
            //Ambil file name
            String ava;
            ava = getFilename(p);
            rs = s.executeQuery("SELECT * FROM accounts WHERE username='"+usernama+"'");
            
            if (rs.next())
            {
                int a,b,c,d;
                if (namalengkap.equals("null") || namalengkap.equals(rs.getString("nama_lengkap")))
                {
                    a = 0;
                }
                    else
                {
                    Statement stemp = con.createStatement();
                    stemp.executeUpdate("UPDATE accounts SET nama_lengkap='"+namalengkap+"'WHERE username='"+usernama+"'");
                    a = 1;
                }
                
                if(pass.equals("null") || pass.equals(rs.getString("password")))
                {
                    b = 0;
                }
                    else
                {
                    Statement stemp = con.createStatement();
                    stemp.executeUpdate("UPDATE accounts SET password='"+pass+"'WHERE username='"+usernama+"'");
                    b = 1;
                }
                
                if (tgl.equals("null") || tgl.equals(rs.getString("tgl_lahir"))) 
                {
                    c = 0;
                } 
                    else 
                {
                    Statement stemp = con.createStatement();
                    stemp.executeUpdate("UPDATE accounts SET tgl_lahir='" + tgl + "'WHERE username='" + usernama + "'");
                    c = 1;
                }

                if (ava.equals("null") || ava.equals(rs.getString("avatar"))){
                        d = 0;
                    }else{
                        
                        Statement stemp = con.createStatement();
                        stemp.executeUpdate("UPDATE accounts SET avatar='upload/"+ava+"'WHERE username='"+usernama+"'");
                        
                        // get filename to use on the server
                        String outputfile = this.getServletContext().getRealPath("./upload/"+ava);  // get path on the server
                        FileOutputStream os = new FileOutputStream (outputfile);

                        // write bytes taken from uploaded file to target file
                        int ch = is.read();
                        while (ch != -1) {
                             os.write(ch);
                             ch = is.read();
                        }
                        os.close();
                        out.println("<h3>File uploaded successfully!</h3>");
                        d=1;
                    }
                 response.sendRedirect("profile.jsp?a="+a+"&b="+b+"&c="+c+"&d="+d+"");
                con.close();
                s.close();
                rs.close();
            }
             
      }catch (Exception ex)
        {
            out.println("Exception : " + ex.getMessage());
        }finally
        {
            out.close();
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

    private String getFilename(Part part) {
         for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
