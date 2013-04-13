/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import ConnectDB.ConnectDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ASUS
 */
@WebServlet(name = "checkUser", urlPatterns = {"/checkUser"})
public class checkUser extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        ResultSet rs = null;
        Statement s = null;
        Connection con = null;
        try
        {
            String name = request.getParameter("id");
            String pass = request.getParameter("pass");
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510100","root","");
            s = con.createStatement();
            rs = s.executeQuery("select* from user where username='"+name+"'");
            
            if(rs.next())
                {
                    if((name.trim().equals(rs.getString("username").trim())) && (pass.trim().equals(rs.getString("password").trim())))
                    {
                        //New Session Creation
                        HttpSession session = request.getSession(true);
                        
                        //Setting atrribute on session
                        session.setAttribute("username", name);
                        
                        //send request to next page
                        //RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
                        //view.forward(request, response);
                        out.print("true");
                    }
                        else
                    {
                        //RequestDispatcher view = request.getRequestDispatcher("index.jsp");
                        //view.include(request, response);
                        out.print("false");
                        //out.println("<script> alert('Password salah ');</script>");
                    }
                }
                    else
                {
                    //RequestDispatcher view=request.getRequestDispatcher("index.jsp");
                    //view.include(request, response);
                    //out.println("<script> alert('Username tidak ditemukan ');</script>");
                    out.print("false");
                }
        }catch(SQLException e) {
            throw new ServletException("Servlet Could not display records.", e);
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBC Driver not found.", e);
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                    rs = null;
                } catch (SQLException ex) {
                    Logger.getLogger(checkUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (con != null) {
                try {
                    con.close();
                    con = null;
                } catch (SQLException ex) {
                    Logger.getLogger(checkUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            out.close();
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
}
