package id.ac.itb.todolist.rest.resource;

import id.ac.itb.todolist.dao.CategoryDao;
import id.ac.itb.todolist.model.Category;

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

/**
 * Servlet implementation class UserResource
 */
public class CategoryResource extends HttpServlet {
    
    private Pattern regexCategorySearch = Pattern.compile("^/([\\w._%].*)/([\\w._%].*)/([\\w._%].*)$");
    private Pattern regexAssignee = Pattern.compile("^/assignee/([\\w._%].*)$");
    private Pattern regexUser = Pattern.compile("^/user/([\\w._%].*)$");
    private Pattern regexCategory = Pattern.compile("^/([\\w._%].*)$");
    private Pattern regexAllCategory = Pattern.compile("^/$");
    private Pattern regexDelete = Pattern.compile("^/([\\w._%].*)$");

    public CategoryResource() {
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

        matcher = regexCategorySearch.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            Collection<Category> result = categoryDao.getCategorySearch(matcher.group(1),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
            
            out.print(new JSONArray(result));
            return;
        }
        
        matcher = regexAssignee.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            ArrayList<Category> result = categoryDao.getAssigneeCat(matcher.group(1));
            
            out.print(new JSONArray(result));
            return;
        }
        
        matcher = regexUser.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            ArrayList<String> result = categoryDao.getUser(Integer.parseInt(matcher.group(1)));
            
            out.print(new JSONArray(result));
            return;
        }
        
        matcher = regexCategory.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            
            out.print(categoryDao.getCategory(Integer.parseInt(matcher.group(1))).toJsonObject());
            return;
        }
        
        matcher = regexAllCategory.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            ArrayList<Category> result = categoryDao.getAllCategory();
            
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

    }

    /**
     * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
     */
    protected void doPut(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }
    
    protected void doDelete(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexDelete.matcher(pathInfo);
        if (matcher.find()) {
            CategoryDao categoryDao = new CategoryDao();
            out.print(categoryDao.DeleteKategori(Integer.parseInt(matcher.group(1))));
            return;
        }
        
        throw new ServletException("Invalid URI: " + pathInfo);
    }
}
