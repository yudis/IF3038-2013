package org.progin.todo;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Handler for core/auth
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class Auth extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        HttpSession session = request.getSession();
        String[] param = {request.getParameter("username"),request.getParameter("password")};
        HashMap<String,Object> r = Query.one("select * from user where username = ? and password = ?",param);
        JSONObject json = new JSONObject();
        boolean success = (r != null);
        try {
            json.put("success",success);
        } catch (JSONException ex) {
            Logger.getLogger(Auth.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (success)
            session.setAttribute("user_id", r.get("user_id"));
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
