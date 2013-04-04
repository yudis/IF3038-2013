package id.ac.itb.todolist.ajax;

import com.google.gson.JsonObject;
import id.ac.itb.todolist.dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AvailabilityChecker extends HttpServlet {

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        
        PrintWriter out = response.getWriter();
        
        String check = request.getParameter("check");
        String data = request.getParameter("data");
        
        JsonObject jObject = new JsonObject();
        
	if (check != null && data != null) {
            UserDao userDao = new UserDao();
            
            boolean checkResult = false;
            if (check.equals("username")) {
                checkResult = userDao.isAvailableUsername(data);
            } else if (check.equals("email")) {
                checkResult = userDao.isAvailableEmail(data);
            }
            
            if (checkResult) {
                jObject.addProperty("status", 200);
            } else {
                jObject.addProperty("status", 401);                
            }
	} else {
            jObject.addProperty("status", 401);
        }
        
        out.print(jObject);
        out.close();
    }
}
