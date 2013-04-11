package org.progin.todo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Handler for core/search
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class Search extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private String[] makeParam(String q, int n) {
        String[] hasil = new String[n];
        for (int i = 0; i < n; i++) {
            hasil[i] = q;
        }
        return hasil;
    }

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String q = request.getParameter("q");
        Integer mode = Integer.parseInt(request.getParameter("mode"));
        String querystring;
        Integer autoComplete = 0, equals = 0;
        if (request.getParameterMap().containsKey("autocomplete")) {
            autoComplete = Integer.parseInt(request.getParameter("autocomplete"));
        }
        if (request.getParameterMap().containsKey("equals")) {
            equals = Integer.parseInt(request.getParameter("equals"));
        }
        List hasil;
        String[] param;
        int n = 0;
        if (autoComplete + equals >= 1) {
            switch (mode) {
                case 0:
                    querystring = "select name as q from task WHERE name like ? union select name as q from tag where name like ? union select name as q from category WHERE name like ? union select username as q from user WHERE username like ? union select email as q from user where email like ? union select name as q from user where name like ? union select birthdate as q from user where birthdate like ?";
                    n = 7;
                    break;
                case 1:
                    querystring = "select username as q from user WHERE username like ? union select email as q from user where email like ? union select name as q from user where name like ? union select birthdate as q from user where birthdate like ?";
                    n = 4;
                    break;
                case 2:
                    querystring = "select name as q from category WHERE name like ?";
                    n = 1;
                    break;
                case 3:
                    querystring = "select name as q from task WHERE name like ? union select name as q from tag where name like ?";
                    n = 2;
                    break;
                case 4:
                    querystring = "select content as q from comment WHERE content like ?";
                    n = 1;
                    break;
                case 5:
                    querystring = "select name as q from user where name like ?";
                    n = 1;
                    break;
                case 6:
                    querystring = "select name as q from tag where name like ?";
                    n = 1;
                    break;
                case 7:
                    querystring = "select username as q from user where username = ?";
                    n = 1;
                    break;
                case 8:
                    querystring = "select email as q from user where email = ?";
                    n = 1;
                    break;
                default:
                    querystring = "";
                    break;
            }
            if (equals == 0) {
                param = makeParam(q + "%", n);
                hasil = Query.all(querystring + " order by q limit 3", param);
                for (Iterator it = hasil.iterator(); it.hasNext();) {
                    HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                    if (!r.keySet().contains("q")) {
                        for (Entry<String,Object> e : r.entrySet()) {
                            r.put("q",(String)e.getValue());
                        }
                    }
                }
                JSONArray json = JSON.Array(hasil);
                response.setContentType("application/json");
                response.getWriter().write(json.toString());
            } else {
                param = makeParam(q, n);
                HashMap<String,Object> h = Query.one(querystring, param);
                JSONObject json = JSON.Object(h);
                response.setContentType("application/json");
                response.getWriter().write(json.toString());
            }

        } else {
            String offset = request.getParameter("offset");

            switch (mode) {
                case 0:
                    querystring = "select distinct user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user WHERE username like ? union select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user where email like ? union select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user where name like ? union select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user where birthdate like ? union select user_id, null as username, name, null as task_id, null as deadline, null as done, category_id, 'category' as type from category WHERE name like ? union select user_id, null as username, name, task_id, deadline, done, null category_id, 'task' as type from task WHERE name like ? union select task.user_id, null as username, task.name as name, task.task_id, deadline, done, null category_id, 'task' as type from ((tag natural join tags) inner join task on(tags.task_id = task.task_id)) where tag.name like ?";
                    n = 7;
                    break;
                case 1:
                    querystring = "select distinct user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user WHERE username like ? union select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user where email like ? union select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user where name like ? union select user_id, username, name, null as task_id, null as deadline, null as done, null as category_id, 'user' as type from user where birthdate like ?";
                    n = 4;
                    break;
                case 2:
                    querystring = "select distinct user_id, null as username, name, null as task_id, null as deadline, null as done, category_id, 'category' as type from category WHERE name like ?";
                    n = 1;
                    break;
                case 3:
                    querystring = "select distinct user_id, null as username, name, task_id, deadline, done, null category_id, 'task' as type from task WHERE name like ? union select task.user_id, null as username, task.name as name, task.task_id, deadline, done, null category_id, 'task' as type from ((tag natural join tags) inner join task on(tags.task_id = task.task_id)) where tag.name like ?";
                    n = 2;
                    break;
                case 4:
                    querystring = "select distinct user_id, null as username, name, task_id, deadline, done, null category_id, 'task' as type from (comment natural join task) WHERE content like ?";
                    n = 1;
                    break;
                default:
                    querystring = "";
                    break;
            }
            param = makeParam("%" + q + "%", n);
            hasil = Query.all(querystring + "order by type desc, name asc limit " + offset + ", 10", param);
            JSONArray tags;
            for (Iterator it = hasil.iterator(); it.hasNext();) {
                HashMap<String, Object> r = (HashMap<String, Object>) it.next();
                if (r.get("type").toString().compareTo("task") == 0) {
                    String[] w = {r.get("task_id").toString()};
                    tags = JSON.Array(Query.all("select name from tags natural join tag where task_id = ?", w));
                    r.put("tags", tags);
                }
            }
            JSONArray json = JSON.Array(hasil);
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        }
    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
