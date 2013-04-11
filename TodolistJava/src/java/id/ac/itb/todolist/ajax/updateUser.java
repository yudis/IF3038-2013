/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.ajax;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Helper;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;


/**
 *
 * @author Felix
 */
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2 * 5)
public class updateUser extends HttpServlet {

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
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
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
        try {
            processRequest(request, response);
        } catch (ParseException ex) {
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            PrintWriter out = response.getWriter();
            
            //processRequest(request, response);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            if (!"".equals(request.getParameter("pwd_confirm"))){
                user.setPassword(request.getParameter("pwd_confirm"));
            }
            if (!"".equals(request.getParameter("fname"))){
                user.setFullName(request.getParameter("fname"));          
            }
            if (!"".equals(request.getParameter("Bday"))){
                user.setTglLahir(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Bday")).getTime()));
            }
            if (!"".equals(request.getParameter("ava"))){
          
                Part avatar = request.getPart("ava");
                String uploadPath = getServletContext().getRealPath("./images/avatars");
                user.setAvatar(Helper.getFileName(avatar)); 
                Helper.writeFile(avatar.getInputStream(), uploadPath + File.separator + user.getAvatar());                
            }

            UserDao userDao = new UserDao();
            userDao.Update(user);
            
            session.setAttribute("user", user);
            response.sendRedirect("../profile.jsp");               
            
        } catch (Exception ex) {
            Logger.getLogger(updateUser.class.getName()).log(Level.SEVERE, null, ex);
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
