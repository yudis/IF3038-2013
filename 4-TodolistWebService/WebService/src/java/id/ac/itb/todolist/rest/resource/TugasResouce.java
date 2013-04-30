package id.ac.itb.todolist.rest.resource;

import id.ac.itb.todolist.dao.TugasDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;

public class TugasResouce extends HttpServlet {

    private Pattern regexAll = Pattern.compile("^/$");
    private Pattern regexId = Pattern.compile("^/([0-9]{1,})$");
    private Pattern regexIdInt = Pattern.compile("^/([0-9]{1,})/([0-9]{1,})$");
    private Pattern regexSuggestionTags = Pattern.compile("^/suggestion/tags/([0-9]{1,})/([\\w%]{1,})/([0-9]{1,})$");
    private Pattern regexSuggestionAssignees = Pattern.compile("^/suggestion/assignees/([0-9]{1,})/([\\w%]{1,})/([0-9]{1,})$");
    private Pattern regexAssignee = Pattern.compile("^/([0-9]{1,})/assignee/([\\w%]{1,})$");
    private Pattern regexPemilik = Pattern.compile("^/([0-9]{1,})/pemilik/([\\w%]{1,})$");
    private Pattern regexTag = Pattern.compile("^/([0-9]{1,})/tag/([\\w%]{1,})$");
    private Pattern regexIsUpdate = Pattern.compile("^/([0-9]{1,})/u/([0-9]{1,})$");
    private Pattern regexTimestamp = Pattern.compile("^/([0-9]{1,})/timestamp$");
    private Pattern regexDeadline = Pattern.compile("^/([0-9]{1,})/deadline/([0-9]{4,4})/([0-9]{1,2})/([0-9]{1,2})$");
    private Pattern regexUsername = Pattern.compile("^/username/([\\w%]{1,})$");
    private Pattern regexSearch = Pattern.compile("^/search/([\\w%]{1,})/([0-9]{1,})/([0-9]{1,})$");
    
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexId.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.deleteTugas(Integer.parseInt(matcher.group(1))));
            return;
        }

        matcher = regexAssignee.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.removeAssignee(Integer.parseInt(matcher.group(1)), matcher.group(2)));
            return;
        }

        matcher = regexTag.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.removeTag(Integer.parseInt(matcher.group(1)), matcher.group(2)));
            return;
        }

        throw new ServletException("Invalid URI");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexIdInt.matcher(pathInfo);
        if (matcher.find()) {

            int id = Integer.parseInt(matcher.group(1));
            int tmp = Integer.parseInt(matcher.group(2));

            boolean retTags = (tmp & 4) > 0;
            boolean retAttachment = (tmp & 2) > 0;
            boolean retAssignees = (tmp & 1) > 0;

            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.getTugas(id, retTags, retAttachment, retAssignees).toJsonObject());
            return;
        }

        matcher = regexSuggestionTags.matcher(pathInfo);
        if (matcher.find()) {
            int idTugas = Integer.parseInt(matcher.group(1));
            String keyword = matcher.group(2);
            int limit = Integer.parseInt(matcher.group(3));

            TugasDao tugasDao = new TugasDao();
            out.print(new JSONArray(tugasDao.getSuggestionTags(idTugas, keyword, limit)));
            return;
        }

        matcher = regexSuggestionAssignees.matcher(pathInfo);
        if (matcher.find()) {
            int idTugas = Integer.parseInt(matcher.group(1));
            String keyword = matcher.group(2);
            int limit = Integer.parseInt(matcher.group(3));

            TugasDao tugasDao = new TugasDao();
            out.print(new JSONArray(tugasDao.getSuggestionAssignees(idTugas, keyword, limit)));
            return;
        }
                
        matcher = regexAssignee.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.isAssignee(Integer.parseInt(matcher.group(1)), matcher.group(2)));
            return;
        }
                  
        matcher = regexPemilik.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.isPemilik(Integer.parseInt(matcher.group(1)), matcher.group(2)));
            return;
        }
        
        matcher = regexIsUpdate.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.isUpdated(Integer.parseInt(matcher.group(1)), Long.parseLong(matcher.group(2))));
            return;
        }
        
        matcher = regexUsername.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(new JSONArray(tugasDao.getTugas(matcher.group(1))));
            return;
        }
        
        matcher = regexSearch.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(new JSONArray(tugasDao.getTugasSearch(matcher.group(1), Integer.parseInt(matcher.group(2)), Integer.parseInt(matcher.group(3)))));
            return;
        }       

        matcher = regexAll.matcher(pathInfo);
        if (matcher.find()) {
            TugasDao tugasDao = new TugasDao();
            out.print(new JSONArray(tugasDao.getAllTugas()));
            return;
        }

        throw new ServletException("Invalid URI");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        matcher = regexIdInt.matcher(pathInfo);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            int tmp = Integer.parseInt(matcher.group(2));

            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.setStatus(id, tmp > 0));
            return;
        }
        
        matcher = regexTimestamp.matcher(pathInfo);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));

            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.updateTimestamp(id));
            return;
        }
        
        
        matcher = regexDeadline.matcher(pathInfo);
        if (matcher.find()) {
            int id = Integer.parseInt(matcher.group(1));
            
            java.sql.Date date = new java.sql.Date(Integer.parseInt(matcher.group(2)) - 1900, Integer.parseInt(matcher.group(3)), Integer.parseInt(matcher.group(4)));

            TugasDao tugasDao = new TugasDao();
            out.print(tugasDao.setDeadline(id, date));
            return;
        }   
    }
}
