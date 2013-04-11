package org.progin.todo;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 * Handler for core/getComment
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class GetComment extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String task_id = request.getParameter("task_id");
        String offset = request.getParameter("offset");
        JSONArray json;
        List l;
        String[] param = {task_id};
        l = Query.all("select * from comment WHERE task_id = ? order by time asc limit "+offset+", 10", param);

        for (Iterator it = l.iterator(); it.hasNext();) {
            HashMap<String, Object> r = (HashMap<String, Object>) it.next();
            r.put("user_name",Function.getUserName(r.get("user_id").toString()));
        }
        json = JSON.Array(l);
        response.setContentType("application/json");
        response.getWriter().write(json.toString());

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
