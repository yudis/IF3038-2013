/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.rest.resource;

import id.ac.itb.todolist.dao.CommentDao;

import java.io.IOException;
import java.io.PrintWriter;
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
 * @author Yulianti Oenang
 */
public class CommentResource extends HttpServlet {

	private Pattern regexId_tugas = Pattern.compile("^/([0-9]{1,})$");
        private Pattern regexId_comment = Pattern.compile("^/idComment/([0-9]{1,})$");
        private Pattern regexGetComment=Pattern.compile("^/([0-9]{1,})/([0-9]{1,})/([0-9]{1,})$");
         //private Pattern regexAddComment=Pattern.compile("^/([0-9]{1,})/([0-9]{1,})/([a-z].*)/");
        private Pattern regexDeleteComment=Pattern.compile("^/([0-9]{1,})$");
        public CommentResource(){
            super();
        }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		Matcher matcher;

		matcher = regexId_tugas.matcher(pathInfo);
		if (matcher.find()) {
			CommentDao commentDao = new CommentDao();
			out.print((Integer)commentDao.getCommentsCount(Integer.parseInt(matcher.group(1))));
			return;
		}
		matcher = regexGetComment.matcher(pathInfo);
                if (matcher.find()) {
			CommentDao commentDao = new CommentDao();
                        out.print(new JSONArray(commentDao.getComments(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)))));
			return;      
		}       
                matcher = regexId_comment.matcher(pathInfo);
                if (matcher.find()) {
			CommentDao commentDao = new CommentDao();
			out.print(commentDao.getCommentById(Integer.parseInt(matcher.group(1))).toJsonObject());
			return;
		}
              
		throw new ServletException("Invalid URI");
	}

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String pathInfo = req.getPathInfo();
        Matcher matcher;

        matcher = regexDeleteComment.matcher(pathInfo);
        if (matcher.find()) {
                    CommentDao commentDao = new CommentDao();
                    out.print(commentDao.deleteComment(Integer.parseInt(matcher.group(1))));
                    return;
        }
    }
        

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		out.print("lalala");
		out.close();
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
}
