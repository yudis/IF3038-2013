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
public class signup_process extends HttpServlet {

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
            throws ServletException, IOException{
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        Statement s = null;
        ResultSet rs =null;
        Connection con = null;
        // get your absolute path
        String uploadTo = getServletContext().getRealPath("/") + "images/";

        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
                        Scanner scan;
                        Part p;
                        
                        p = request.getPart("username");
                        scan = new Scanner(p.getInputStream());
                        String name;
                        if (scan.hasNext())
                        {
                            name = scan.nextLine();
                        }else
                        {
                            name = "null";
                        }
                        scan.close();
                        
                        p = request.getPart("password");
                        scan = new Scanner(p.getInputStream());
                        String pass;
                        if (scan.hasNext())
                        {
                            pass = scan.nextLine();
                        }else
                        {
                            pass = "null";
                        }
                        scan.close();
                        
                        p = request.getPart("namaleng");
                        scan = new Scanner(p.getInputStream());
                        String namalengkap;
                        if (scan.hasNext())
                        {
                            namalengkap = scan.nextLine();
                        }else
                        {
                            namalengkap = "null";
                        }
                        scan.close();
                        
                        p = request.getPart("tanggal");
                        scan = new Scanner(p.getInputStream());
                        String tanggal;
                        if (scan.hasNext())
                        {
                            tanggal = scan.nextLine();
                        }else
                        {
                            tanggal = "null";
                        }
                        scan.close();
                        
                        p = request.getPart("email");
                        scan = new Scanner(p.getInputStream());
                        String email;
                        if (scan.hasNext())
                        {
                            email = scan.nextLine();
                        }else
                        {
                            email = "null";
                        }
                        scan.close();
                        
                        //Ambil data avatar
                        p = request.getPart("avatar");
                        InputStream is = p.getInputStream();
                        //Ambil file name
                        String avatar;
                        avatar = getFilename(p);

                        Class.forName("com.mysql.jdbc.Driver");
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin","progin","progin");
                        s = con.createStatement();
                        s.executeUpdate("insert into accounts(username,password,email,nama_lengkap,tgl_lahir,avatar)  values('"+name+"','"+pass+"','"+email+"','"+namalengkap+"','"+tanggal+"','upload/"+avatar+"')");
                        // get filename to use on the server
                        
                        String outputfile = this.getServletContext().getRealPath("./upload/"+avatar);  // get path on the server
                        FileOutputStream os = new FileOutputStream (outputfile);

                        // write bytes taken from uploaded file to target file
                        int ch = is.read();
                        while (ch != -1) {
                             os.write(ch);
                             ch = is.read();
                        }
                        os.close();
                        
                        HttpSession session = request.getSession(true);
                        session.setAttribute("username", name);
                        RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
                        view.forward(request, response);
           
            
/*
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet signup_process</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet signup_process at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");*/
            

        }catch (Exception ex) {
            ex.printStackTrace();
        }  finally {            
            out.close();
            if (con != null) {
                try {
                    con.close();
                    con = null;
                } catch (SQLException ex) {
                    Logger.getLogger(login2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (s != null) {
                try {
                    s.close();
                    s = null;
                } catch (SQLException ex) {
                    Logger.getLogger(login2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(login2.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            throws ServletException, IOException 
    {
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

    private String getFilename(Part part) {
         for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
