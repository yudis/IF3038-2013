/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.rest;

import id.ac.itb.todolist.dao.CommentDao;
import id.ac.itb.todolist.model.Comment;
import id.ac.itb.todolist.model.User;
import id.ac.itb.todolist.util.Helper;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Raymond
 */
public class CommentRest extends HttpServlet {

    private Pattern regexCommentCount = Pattern.compile("^/([\\d]+)$");
    private Pattern regexComment = Pattern.compile("^/([\\d]+)/([\\d]+)/([\\d]+)$");
    private Pattern regexCommentById = Pattern.compile("^/byid/([\\d]+)$");
    private Pattern regexDeleteComment = Pattern.compile("^/delete/([\\d]+)$");

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

        matcher = regexCommentCount.matcher(pathInfo);
        if (matcher.find()) {
            CommentDao commentDao = new CommentDao();
            out.print(commentDao.getCommentsCount(Integer.parseInt(matcher.group(1))));
            return;
        }
        matcher = regexComment.matcher(pathInfo);
        if (matcher.find()) {
            CommentDao commentDao = new CommentDao();

            List<Comment> result = commentDao.getComments(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)));
            out.print(new JSONArray(result));
            return;
        }
        matcher = regexCommentById.matcher(pathInfo);
        if (matcher.find()) {
            CommentDao commentDao = new CommentDao();
            Comment comment = commentDao.getCommentById(Integer.parseInt(matcher.group(1)));
            if (comment != null) {
                out.print(comment.toJsonObject());
            } else {
                out.print(Helper.QUERY_RESULT_NOT_FOUND);
            }
            return;
        }
        matcher = regexDeleteComment.matcher(pathInfo);
        if (matcher.find()) {
            CommentDao commentDao = new CommentDao();
            out.print(commentDao.deleteComment(Integer.parseInt(matcher.group(1))));
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
