package id.ac.itb.todolist.rest.resource;

import id.ac.itb.todolist.dao.SearchAutoCompleteDao;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

/**
 * Servlet implementation class UserResource
 */
public class SearchAutoCompleteResource extends HttpServlet {
    
    private Pattern regexSearchAC = Pattern.compile("^/([\\w._%].*)/([\\w._%].*)$");

    public SearchAutoCompleteResource() {
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

        matcher = regexSearchAC.matcher(pathInfo);
        if (matcher.find()) {
            SearchAutoCompleteDao searchAutoCompleteDao = new SearchAutoCompleteDao();
            
            ArrayList<String> result = new ArrayList<String>();
            result=searchAutoCompleteDao.getSearchAC(matcher.group(2), matcher.group(1));

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
        
    }
}
