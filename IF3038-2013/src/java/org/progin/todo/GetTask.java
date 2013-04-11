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
 * Handler for core/getTask
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class GetTask extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String user_id = request.getParameter("user_id");
        JSONArray json;
        JSONArray tags;
        List l;
        if (request.getParameter("category_id")==null || "0".compareTo(request.getParameter("category_id")) == 0) {
            String[] param = {user_id,user_id};
            l = Query.all("select * from task WHERE (user_id = ? or task_id in (select task_id from assign where user_id = ?))", param);
        } else {
            String category_id = request.getParameter("category_id");
            String[] param = {user_id,user_id,category_id};
            l = Query.all("select * from task WHERE (user_id = ? or task_id in (select task_id from assign where user_id = ?))and category_id = ?", param);
        }
        for (Iterator it = l.iterator(); it.hasNext();) {
            HashMap<String, Object> r = (HashMap<String, Object>) it.next();
            String[] w = {r.get("task_id").toString()};
            tags = JSON.Array(Query.all("select name from tags natural join tag where task_id = ?", w));
            r.put("tags",tags);
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
    
    public static void main(String[] args) {
        String user_id = "1";
        JSONArray json;
        JSONArray tags;
        List l;
        if (false) {
            String[] param = {user_id,user_id};
            l = Query.all("select * from task WHERE (user_id = ? or task_id in (select task_id from assign where user_id = ?))", param);
        } else {
            String category_id = "1";
            String[] param = {user_id,user_id,category_id};
            l = Query.all("select * from task WHERE (user_id = ? or task_id in (select task_id from assign where user_id = ?))and category_id = ?", param);
        }
        for (Iterator it = l.iterator(); it.hasNext();) {
            HashMap<String, Object> r = (HashMap<String, Object>) it.next();
            String[] w = {r.get("task_id").toString()};
            tags = JSON.Array(Query.all("select name from tags natural join tag where task_id = ?", w));
            r.put("tags",tags);
        }
        json = JSON.Array(l);
        System.out.println(json.toString());
    }
}
