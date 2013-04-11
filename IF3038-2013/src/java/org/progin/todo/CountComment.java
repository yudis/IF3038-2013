package org.progin.todo;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler for core/countComment
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class CountComment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String task_id = request.getParameter("task_id");
        HashMap<String,Object> hasil;
        String[] param = {task_id};
        hasil = Query.one("select count(*) from comment WHERE task_id = ?", param);
        response.setContentType("application/json");
        response.getWriter().write(hasil.get("count(*)").toString());

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
