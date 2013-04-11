package org.progin.todo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handler for core/assign
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class Assign extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String task_id, category_id;
        if (request.getParameterMap().containsKey("task_id")) {
            task_id = request.getParameter("task_id");
        } else {
            task_id = null;
        }
        if (request.getParameterMap().containsKey("category_id")) {
            category_id = request.getParameter("category_id");
        } else {
            category_id = null;
        }
        String type = request.getParameter("type");
        if (type.compareTo("delete") == 0) {
            Function.delAssignee(request.getParameter("name"), task_id, category_id);
        } else if (type.compareTo("add") == 0) {
            Function.addAssignee(request.getParameter("name"), task_id, category_id);
        }

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
