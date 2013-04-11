package org.progin.todo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.*;

/**
 * Handler for core/updateTask
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class UpdateTask extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String mode = request.getParameter("mode");
        if (mode.compareTo("deadline") == 0) {
            String [] param = {request.getParameter("value"),request.getParameter("task_id")};
            Query.n("update task set deadline = ? where task_id = ?", param);
        } else if (mode.compareTo("addassignee") == 0) {
            Function.addAssignee(request.getParameter("value"), request.getParameter("task_id"), null);
        } else if (mode.compareTo("addtag") == 0) {
            Function.addTag(request.getParameter("task_id"), request.getParameter("value"));
        } else if (mode.compareTo("delassignee") == 0) {
            String [] param = {request.getParameter("value")};
            Query.n("delete from assign where id = ?", param);
        } else if (mode.compareTo("deltag") == 0) {
            String [] param = {request.getParameter("value")};
            Query.n("delete from tags where id = ?", param);
        }

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
