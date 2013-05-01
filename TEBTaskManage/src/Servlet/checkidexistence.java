/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
/**
 *
 * @author ASUS
 */
public class checkidexistence extends HttpServlet {

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
        doPost(request, response);
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
    	PrintWriter out = response.getWriter();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            String username = request.getParameter("id");
            String pass = request.getParameter("pass");
            
            Query q = pm.newQuery("SELECT FROM " + User.class.getName() + " WHERE username == '" + username + "' AND password='" +pass + "'");
            List<User> rs = (List<User>) q.execute();

            if(rs.isEmpty()){
                out.print("false");
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("userlistapp", username);
                session.setMaxInactiveInterval(30*24*60*60);
                //System.out.println("session : "+session.getAttribute("userlistapp"));
                out.print("true");
            }
            q.closeAll();
            pm.close();
        } catch(Exception exc){
            out.println("Error : "+exc.toString());
        }finally {            
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
}
