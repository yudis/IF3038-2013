package id.ac.itb.todolist.rest.resource;

import id.ac.itb.todolist.dao.UserDao;
import id.ac.itb.todolist.model.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Servlet implementation class UserResource
 */
public class UserResource extends HttpServlet {

    private Pattern regexAllUser = Pattern.compile("^/$");
    private Pattern regexUser = Pattern.compile("^/([\\w._%].*)$");
    private Pattern regexEmail = Pattern.compile("^/([\\w._%].*)/([\\w._%].*)$");
    private Pattern regexUserSearch = Pattern.compile("^/([\\w._%].*)/([\\w._%].*)/([\\w._%].*)$");
    private Pattern regexUpdate = Pattern.compile("^/([\\w._%].*)/([\\w._%].*)$");

    public UserResource() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexUserSearch.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            Collection<User> result = userDao.getUserSearch(matcher.group(1),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
            
            out.print(new JSONArray(result));
            return;
        }
        
        matcher = regexEmail.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            boolean result = userDao.isAvailableEmail(matcher.group(2));
                    
            out.print(new JSONArray(result));
            return;
        }
        
        matcher = regexUser.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            out.print(userDao.getUser(matcher.group(1)).toJsonObject());
            return;
        }
        
        matcher = regexAllUser.matcher(pathInfo);
        if (matcher.find()) {
            UserDao userDao = new UserDao();
            ArrayList<String> result = userDao.getUsers();
            
            out.print(new JSONArray(result));
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

        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexUpdate.matcher(pathInfo);
        if (matcher.find()) {
            User user = new User();
            user.fromJsonObject(new JSONObject(request.getParameter("user")));
            
            UserDao userDao = new UserDao();
            userDao.Update(user);
            return;
        }
        
        throw new ServletException("Invalid URI");
    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
}
