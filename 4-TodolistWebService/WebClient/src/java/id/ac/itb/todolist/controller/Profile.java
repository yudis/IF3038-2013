package id.ac.itb.todolist.controller;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile extends HttpServlet {

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
/*    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        
        if (session.getAttribute("user") != null)
        {
            // user sudah login, dialihkan ke halaman lain
            request.setAttribute("title", "Todolist | Profile");
            request.setAttribute("user", session.getAttribute("user"));
            request.setAttribute("isUser", "false");
            request.setAttribute("headTags", "<script src=\"scripts/profile.js\" type=\"application/javascript\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/profile.css\" />");
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/profile/profile.jsp");
            view.forward(request, response);
        }
        else
        {
           response.sendRedirect("./index.jsp");
        }
    }
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
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        UserDao userdao = new UserDao();
        
        if (session.getAttribute("user") != null)
        {
            User loginedUser = (User) session.getAttribute("user");
            User targetUser = request.getParameter("id") == null ? null : userdao.getUser(request.getParameter("id"));
            
            // user sudah login, dialihkan ke halaman lain
            request.setAttribute("title", "Todolist | Profile");
            if (targetUser != null) {
                if (loginedUser.getUsername().equals(targetUser.getUsername())){
                    request.setAttribute("isUser", "true");
                } else {
                    request.setAttribute("isUser", "false");
                }
                
                request.setAttribute("user", targetUser);     
                request.setAttribute("bodyAttrs", "onload=\"onload('" + targetUser.getUsername() + "');\"");
            } else {
                request.setAttribute("isUser", "true");
                request.setAttribute("user", loginedUser);        
                request.setAttribute("bodyAttrs", "onload=\"onload('" + loginedUser.getUsername() + "');\"");
            }
            
            request.setAttribute("headTags", "<script src=\"scripts/profile.js\" type=\"application/javascript\"></script><link rel=\"stylesheet\" type=\"text/css\" href=\"./styles/profile.css\" />");
            
            RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/profile/profile.jsp");
            view.forward(request, response);
        }
        else
        {
           response.sendRedirect("./index.jsp");
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
        //processRequest(request, response);
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
