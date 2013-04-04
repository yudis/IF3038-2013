/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.controller;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Edward Samuel
 */
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
        response.sendRedirect("./index");
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

        if (session.getAttribute("user") == null && ServletFileUpload.isMultipartContent(request)) {
            String uploadPath = getServletContext().getRealPath("") + File.separator + AVATARS_DIRECTORY;

            try {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                User user = new User();
                FileItem avatar = null;

                List<FileItem> fields = upload.parseRequest(request);
                for (FileItem item : fields) {
                    if (item.getFieldName().equals("uname")) {
                        user.setUsername(item.getString());
                    } else if (item.getFieldName().equals("email")) {
                        user.setEmail(item.getString());
                    } else if (item.getFieldName().equals("pwd")) {
                        user.setPassword(item.getString());
                    } else if (item.getFieldName().equals("name")) {
                        user.setFullName(item.getString());
                    } else if (item.getFieldName().equals("bday")) {
                        user.setTglLahir(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(item.getString()).getTime()));
                    } else if (!item.isFormField() && item.getSize() > 0 && item.getFieldName().equals("ava")) {
                        avatar = item;
                        user.setAvatar(user.getUsername() + "." + org.apache.commons.io.FilenameUtils.getExtension(item.getName()));
                    }
                }

                if (user.getUsername() != null && user.getEmail() != null && user.getPassword() != null && user.getFullName() != null && user.getTglLahir() != null && avatar != null) {
                    String filePath = uploadPath + File.separator + user.getAvatar();
                    File storeFile = new File(filePath);
                    // saves the file on disk
                    avatar.write(storeFile);
                    
                    UserDao userDao = new UserDao();
                    userDao.addUser(user);
                    session.setAttribute("user", user);
                    
                    response.sendRedirect("./dashboard.jsp");
                } else {
                    response.sendRedirect("./index.jsp");
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
