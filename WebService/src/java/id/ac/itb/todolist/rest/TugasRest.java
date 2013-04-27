/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package id.ac.itb.todolist.rest;

import id.ac.itb.todolist.dao.TugasDao;
import id.ac.itb.todolist.model.Tugas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author User
 */
public class TugasRest extends HttpServlet {
    
    private Pattern regexTugas = Pattern.compile("^/([\\d]{1,})/([\\w._%].*)/([\\w._%].*)/([\\w._%].*)$");
    private Pattern regexDeleteT = Pattern.compile("^/delete/([\\d]{1,})$");
    private Pattern regexisUpdated = Pattern.compile("^/isu/([\\d]{1,})/([\\w._%].*)/(-?\\d{1,19})$");
    private Pattern regexisAvailable = Pattern.compile("^/isa/([\\d]{1,})$");
    private Pattern regexisPemilik = Pattern.compile("^/isp/([\\d]{1,})/([\\w._%].*)$");
    private Pattern regexisAssignee = Pattern.compile("^/isass/([\\d]{1,})/([\\w._%].*)$");
    private Pattern regexUpdateTimeStamp = Pattern.compile("^/utime/([\\d]{1,})$");
    private Pattern regexsetStatus = Pattern.compile("^/sets/([\\d]{1,})/([\\w._%].*)$");
    private Pattern regexsetdeadline = Pattern.compile("^/setd/([\\d]{1,})/([\\d]{1,})/([\\d]{1,})/([\\d]{1,})$");
    private Pattern regexremoveAssignee = Pattern.compile("^/remove/assignee/([\\d]{1,})/([\\w._%].*)$");
    private Pattern regexSuggestionAssignees = Pattern.compile("^/suggestion/assignee/([\\d]{1,})/([\\w._%].*)/([\\d]{1,})$");
    private Pattern regexremoveTag = Pattern.compile("^/remove/tag/([\\d]{1,})/([\\w._%].*)$");
    private Pattern regexSuggestionTags = Pattern.compile("^/suggestion/tag/([\\d]{1,})/([\\w._%].*)/([\\d]{1,})$");
    private Pattern regexgetAllTugas = Pattern.compile("^/$");
    private Pattern regexgetTugas = Pattern.compile("^/username/([\\w._%].*)$");
    private Pattern regexTugasSearch = Pattern.compile("^/search/([\\w._%].*)/([\\d]{1,})/([\\d]{1,})$");
    
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String pathInfo = request.getPathInfo();
        try {
            Matcher matcher;
            
            matcher = regexTugas.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                Tugas tugas = tugasDao.getTugas(Integer.parseInt(matcher.group(1)),Boolean.valueOf(matcher.group(2)),Boolean.valueOf(matcher.group(3)),Boolean.valueOf(matcher.group(4)));
                out.print(tugas.toJsonObject());
                return;
            }
            
            matcher = regexDeleteT.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                out.print(tugasDao.deleteTugas(Integer.parseInt(matcher.group(1))));
                return;
            }
            
            matcher = regexisUpdated.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.isUpdated(Integer.parseInt(matcher.group(1)),Long.getLong(matcher.group(2))));
                return;
            }
            
            matcher = regexisAvailable.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.isAvailable(Integer.parseInt(matcher.group(1))));
                return;
            }
            
            matcher = regexisPemilik.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.isPemilik(Integer.parseInt(matcher.group(1)),matcher.group(2)));
                return;
            }
            
            matcher = regexisAssignee.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.isAssignee(Integer.parseInt(matcher.group(1)),matcher.group(2)));
                return;
            }
            
            matcher = regexUpdateTimeStamp.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.updateTimestamp(Integer.parseInt(matcher.group(1))));
                return;
            }
            
            matcher = regexsetStatus.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.setStatus(Integer.parseInt(matcher.group(1)),Boolean.valueOf(matcher.group(2))));
                return;
            }
            
            matcher = regexsetdeadline.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(tugasDao.setDeadline(Integer.parseInt(matcher.group(1)),Date.valueOf(matcher.group(2)+"-"+matcher.group(3)+"-"+matcher.group(4))));
                return;
            }
            
            matcher = regexremoveAssignee.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                out.print(tugasDao.removeAssignee(Integer.parseInt(matcher.group(1)),matcher.group(2)));
                return;
            }
            
            matcher = regexSuggestionAssignees.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                out.println(matcher.group(1)+" "+matcher.group(2)+" "+matcher.group(3));
                List<String> result = tugasDao.getSuggestionAssignees(Integer.parseInt(matcher.group(1)),matcher.group(2),Integer.parseInt(matcher.group(3)));
                out.print(result);
                return;
            }
            
            matcher = regexremoveTag.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                out.print(tugasDao.removeTag(Integer.parseInt(matcher.group(1)),matcher.group(2)));
                return;
            }
            
            matcher = regexSuggestionTags.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                List<String> result = tugasDao.getSuggestionTags(Integer.parseInt(matcher.group(1)),matcher.group(2),Integer.parseInt(matcher.group(3)));
                out.print(result);
                return;
            }
            
            matcher = regexgetAllTugas.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(JSONObject.wrap(tugasDao.getAllTugas()).toString());
                return;
            }
            
            matcher = regexgetTugas.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                
                out.print(JSONObject.wrap(tugasDao.getTugas(matcher.group(1))).toString());
                return;
            }
            
            matcher = regexTugasSearch.matcher(pathInfo);
            if (matcher.find()) {
                TugasDao tugasDao = new TugasDao();
                Collection<Tugas> result = tugasDao.getTugasSearch(matcher.group(1),Integer.parseInt(matcher.group(2)),Integer.parseInt(matcher.group(3)));
                out.print(JSONObject.wrap(result).toString());
                return;
            }
            
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
