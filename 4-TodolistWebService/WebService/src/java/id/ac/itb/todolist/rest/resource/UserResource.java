package id.ac.itb.todolist.rest.resource;

import id.ac.itb.todolist.dao.UserDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 * Servlet implementation class UserResource
 */
public class UserResource extends HttpServlet {

    private Pattern regexAll = Pattern.compile("^/$");
    private Pattern regexUser = Pattern.compile("^/([\\w._%].*)$");

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexUser.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            out.print(userDao.getUser(matcher.group(1)).toJsonObject());
            return;
        }
        
        matcher = regexAll.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            out.print(new JSONArray(userDao.getUsers()));
            return;
        }       

        throw new ServletException("Invalid URI");
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.print("lalala");
        out.close();
    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
