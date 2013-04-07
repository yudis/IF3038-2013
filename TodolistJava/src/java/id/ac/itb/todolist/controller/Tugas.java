/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.controller;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.User;
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
 * @author Edward Samuel
 */
public class Tugas extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        User user = (User) session.getAttribute("user");
        if (user == null)
        {
            // user sudah login, dialihkan ke halaman lain
            response.sendRedirect("./");
        }
        else
        {
            String idTugasStr = request.getParameter("id");
            if (idTugasStr != null)
            {
                int idTugas = Integer.parseInt(idTugasStr);
                
                TugasDao tugasDao = new TugasDao();
                
                RequestDispatcher dispathcer;
                if (!tugasDao.isAvailable(idTugas))
                {
                    dispathcer = request.getRequestDispatcher("/WEB-INF/views/tugas/error.jsp");
                }
                else if (tugasDao.isPemilik(idTugas, user.getUsername()))
                {
                    dispathcer = request.getRequestDispatcher("/WEB-INF/views/tugas/creator.jsp");
                }
                else if (tugasDao.isAssignee(idTugas, user.getUsername()))
                {
                    dispathcer = request.getRequestDispatcher("/WEB-INF/views/tugas/assignee.jsp");
                }
                else
                {
                    dispathcer = request.getRequestDispatcher("/WEB-INF/views/tugas/default.jsp");
                }
                
                request.setAttribute("headTags", "<link rel=\"stylesheet\" type=\"text/css\" href=\"styles/tugas.css\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/autosuggest.css\" /><script src=\"./scripts/tugas.js\" type=\"application/javascript\"></script><script type=\"text/javascript\" src=\"./scripts/autosuggest2.js\"></script><script type=\"text/javascript\" src=\"./scripts/assigneesSuggestion.js\"></script>");
                request.setAttribute("bodyAttrs", "onload=\"onload(" + idTugas + ");\"");
                request.setAttribute("title", "Todolist | Rincian Tugas");  
                dispathcer.forward(request, response);
            }
            else
            {
                response.sendRedirect("./dashboard.jsp");
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
