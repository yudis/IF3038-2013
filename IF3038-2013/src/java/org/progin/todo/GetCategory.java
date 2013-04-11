package org.progin.todo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 * Handler for core/getCategory
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class GetCategory extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String user_id = request.getParameter("user_id");
        JSONArray json;
        List l;
        String[] param = {user_id,user_id,user_id,user_id};
        l = Query.all("select * from category WHERE user_id = ? or category_id in (select category_id from assign where user_id = ? union (select category_id from task where user_id = ? or task_id in (select task_id from assign where user_id = ?)))", param);
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
