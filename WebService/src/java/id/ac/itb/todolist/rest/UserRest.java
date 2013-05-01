/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.rest;

import id.ac.itb.todolist.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Helper;
import java.io.DataInputStream;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import org.json.JSONArray;

/**
 *
 * @author Raymond
 */
@MultipartConfig(location = "/tmp", fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 2, maxRequestSize = 1024 * 1024 * 2 * 5)
public class UserRest extends HttpServlet {

    private Pattern regexUserNames = Pattern.compile("^/$");
    private Pattern regexUpdate = Pattern.compile("^/update/([\\w._%].*)$");
    private Pattern regexUserDetail = Pattern.compile("^/detil/([\\w._%].*)$");
    private Pattern regexEmail = Pattern.compile("^/email/([\\w._%].*)$");
    private Pattern regexSearchUser = Pattern.compile("^/search/([\\w._%].*)/([\\d]{1,})/([\\d]{1,})$");

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
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexUserDetail.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            User user = userDao.getUser(matcher.group(1));
            if (user != null) {
                out.print(user.toJsonObject());
            } else {
                out.print(Helper.QUERY_RESULT_NOT_FOUND);
            }
            return;
        }
        
        matcher = regexEmail.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            boolean b = userDao.isAvailableEmail(matcher.group(1));
            out.println(new JSONArray(b));
            return;
        }
        
        matcher = regexSearchUser.matcher(pathInfo);

        if (matcher.find()) {
            UserDao userDao = new UserDao();
            Collection<User> result = userDao.getUserSearch(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
//            if (result.size() > 0) {
                out.print(new JSONArray(result));
//            } else {
//                out.print(Helper.QUERY_RESULT_NOT_FOUND);
//            }
            return;
        }
        matcher = regexUserNames.matcher(pathInfo);

        if (matcher.find()) {
            UserDao userDao = new UserDao();
            ArrayList<String> result = userDao.getUsers();
            out.print(new JSONArray(result));
            return;
        }

        throw new ServletException(
                "Invalid URI");
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
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        Matcher matcher;
        matcher = regexUpdate.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            User usrBaru = userDao.getUser(matcher.group(1));

            if (request.getParameter("fname") != null) {
                usrBaru.setFullName(request.getParameter("fname"));
            }
            if (request.getParameter("Bday") != null) {
                try {
                    usrBaru.setTglLahir(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Bday")).getTime()));
                } catch (ParseException ex) {
                    Logger.getLogger(UserRest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (request.getParameter("pwd") != null) {
                usrBaru.setPassword(request.getParameter("pwd"));
            }
            if (request.getParameter("ava") != null) { //TODO RYMD
                Part avatar = request.getPart("ava");
                String uploadPath = getServletContext().getRealPath("./images/avatars");
                usrBaru.setAvatar(usrBaru.getUsername() + "." + Helper.getFileExtention(Helper.getFileName(avatar)));
                Helper.writeFile(avatar.getInputStream(), uploadPath + File.separator + usrBaru.getUsername() + "." + Helper.getFileExtention(Helper.getFileName(avatar)));
            }
            userDao.Update(usrBaru);
            out.print("Update Berhasil");
            return;

        }
        throw new ServletException("Invalid URI");
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
