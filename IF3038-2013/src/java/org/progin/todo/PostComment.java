package org.progin.todo;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Handler for core/postComment
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class PostComment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String date = Integer.toString((int)(System.currentTimeMillis() / 1000));
        PrintWriter out = response.getWriter(); 
        out.println(request.getParameter("task_id"));
        HttpSession session = request.getSession();
        String user_id = session.getAttribute("user_id").toString();
        String[] param = {request.getParameter("task_id"), user_id, request.getParameter("content"), date};
        Query.n("insert into comment (task_id, user_id, content, time) values (?, ?, ?, ?)", param);

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
