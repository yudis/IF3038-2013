package org.progin.todo;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 * Handler for core/updateProfile
 *
 * @author Irfan Kamil
 * @author Hanif Eridaputra
 * @author Damiann
 */
public class UpdateProfile extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        String user_id = request.getParameter("user_id");
        String value = request.getParameter("value");
        JSONArray json;
        String mode = request.getParameter("mode");
        String[] param = {value,user_id};
        if (mode.compareTo("name") == 0) {
            Query.n("update user SET name = ? WHERE user_id = ?", param);
        } else if (mode.compareTo("tanggal_lahir") == 0) {
            Query.n("update user SET birthdate = ? WHERE user_id = ?", param);
        } else if (mode.compareTo("password") == 0) {
            Query.n("update user SET password = ? WHERE user_id = ?", param);
        }

    }

    @Override
    public void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws IOException, ServletException {
        doGet(request, response);
    }
}
