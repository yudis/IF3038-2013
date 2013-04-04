/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
public class Dashboard extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        //if (session.getAttribute("user") != null)
        //{
            // user sudah login, dialihkan ke halaman lain
            request.setAttribute("title", "Todolist | Dashboard");
            request.setAttribute("headTags", "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/default.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"styles/mediaqueries.css\" /><script src=\"scripts/helper.js\" type=\"application/javascript\"></script><script src=\"scripts/popup.js\" type=\"application/javascript\"></script><script src=\"scripts/dashboard.js\" type=\"application/javascript\"></script><script src=\"scripts/kategori.js\" type=\"application/javascript\"></script>");
            request.setAttribute("bodyAttrs", "<body onload=\"updateAddButtonVisibility();updateDelButtonVisibility();loadtugas(\'\');\"><div id=\"blanket\"></div><div id=\"popUpDiv\"><h1>Create new category</h1><div class=\"padding12px\"><label for=\"txtNewKategori\">Name</label>:<br /><input id=\"txtNewKategori\" type=\"text\" placeholder=\"eg: IF40XX\" /></div><br /><div class=\"padding12px\">Priviledge users:<br /><ul id=\"userList\" class=\"tag\"></ul><br><input id=\"userL\" name=\"userL\" onfocus=\"showCoordinator()\" type=\"text\" tabindex=\"4\" list=\"user\" /><datalist id=\"user\" ></datalist><button onclick=\"return addCoordinator();\">Add</button></div><br /><div class=\"rightalign padding12px\"><button onclick=\"popup(\'popUpDiv\',\'blanket\',300,600); NewKategori()\">OK</button> <button onclick=\"popup(\'popUpDiv\',\'blanket\',300,600)\">Cancel</button></div><br /></div>");
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/dashboard/dashboard.jsp");
            view.forward(request, response);
        //}
        //else
        //{
        //    response.sendRedirect("./dashboard.jsp");
        //}
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