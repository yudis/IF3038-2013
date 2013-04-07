package id.ac.itb.todolist.controller;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Helper;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2 * 5)
public class Register extends HttpServlet {

    // location to store file uploaded
    private static final String AVATARS_DIRECTORY = "./images/avatars";

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
        response.sendRedirect("./");
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
        HttpSession session = request.getSession();

        if (session.getAttribute("user") == null) {
            String uploadPath = getServletContext().getRealPath(AVATARS_DIRECTORY);

            User user = null;

            try {
                user = new User();
                user.setUsername(request.getParameter("uname"));
                user.setEmail(request.getParameter("email"));
                user.setPassword(request.getParameter("pwd"));
                user.setFullName(request.getParameter("name"));
                user.setTglLahir(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bday")).getTime()));

                Part avatar = request.getPart("ava");
                user.setAvatar(user.getUsername() + "." + Helper.getFileExtention(Helper.getFileName(avatar)));

                if (user.getUsername() != null && user.getEmail() != null && user.getPassword() != null && user.getFullName() != null && user.getTglLahir() != null && avatar != null) {
                    String filePath = uploadPath + File.separator + user.getAvatar();
                    Helper.writeFile(avatar.getInputStream(), filePath);

                    UserDao userDao = new UserDao();
                    userDao.addUser(user);
                    session.setAttribute("user", user);

                    response.sendRedirect("./dashboard.jsp");
                } else {
                    response.sendRedirect("./");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                response.setStatus(400);
            }
        } else {
            response.sendRedirect("./dashboard.jsp");
        }
    }

}
