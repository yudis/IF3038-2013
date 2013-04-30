/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import DBOperation.UserOp;
import JSON.JSONArray;
import JSON.JSONObject;
import Utility.DBUtil;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author M500-S430
 */
public class getSearchData extends HttpServlet {

    private Pattern regexAC = Pattern.compile("^/([\\w._%].*)/([\\w._%].*)/([\\w._%].*)$");
    private Pattern regexResultCat = Pattern.compile("^/cat/([\\w._%].*)$");
    private Pattern regexResultUsr = Pattern.compile("^/usr/([\\w._%].*)$");
    private Pattern regexResultTsk = Pattern.compile("^/tsk/([\\w._%].*)/([\\w._%].*)$");
    private Pattern c = Pattern.compile("");
    
    public getSearchData(){
        super();
    }
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
        Matcher matcher;
        String query = "";
        PreparedStatement ps;
        ResultSet rs;
        Connection con;
        
        matcher = regexResultCat.matcher(pathInfo);
        if (matcher.find())
        {
            String box = matcher.group(1);
            query = "SELECT id_category,name FROM category WHERE name LIKE '%" + box + "%'";
            con = DBUtil.getConnection();
            try{
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                ArrayList<JSONObject> result = new ArrayList<JSONObject>();
                while (rs.next()) {
                    JSONObject a = new JSONObject();
                    a.put("id",rs.getString(1));
                    a.put("name",rs.getString(2));
                    result.add(a);
                }
                out.println(new JSONArray(result));
            } catch (SQLException ex) {
                Logger.getLogger(getSearchData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        matcher = regexResultUsr.matcher(pathInfo);
        if (matcher.find())
        {
            String box = matcher.group(1);
            query = "SELECT username,fullname,avatar FROM user WHERE username LIKE '%" + box + "%' OR fullname LIKE '%" + box + "%' OR email LIKE '%"
                                + box + "%' OR dob LIKE '%" + box + "%'";
            con = DBUtil.getConnection();
            try{
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                ArrayList<JSONObject> result = new ArrayList<JSONObject>();
                while (rs.next()) {
                    JSONObject a = new JSONObject();
                    a.put("username",rs.getString(1));
                    a.put("fullname",rs.getString(2));
                    a.put("avatar", rs.getString(3));
                    result.add(a);
                }
                out.println(new JSONArray(result));
            } catch (SQLException ex) {
                Logger.getLogger(getSearchData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        matcher = regexResultTsk.matcher(pathInfo);
        if (matcher.find())
        {
            String box = matcher.group(1);
            String username = matcher.group(2);
            query = "SELECT DISTINCT t.id_task, t.name, t.deadline, t.status FROM task AS t "
                            + "INNER JOIN ttrelation AS tt "
                            + "ON t.id_task = tt.id_task "
                            + "INNER JOIN tag AS ta "
                            + "ON tt.id_tag = ta.id_tag "
                            + "INNER JOIN utrelation AS u "
                            + "ON t.id_task = u.id_task "
                            + "WHERE (t.name LIKE '%" + box + "%' OR ta.name LIKE '%" + box + "%') AND u.username = '" + username + "'";
            con = DBUtil.getConnection();
            try{
                ps = con.prepareStatement(query);
                rs = ps.executeQuery();
                ArrayList<JSONObject> result = new ArrayList<JSONObject>();
                while (rs.next()) {
                    JSONObject a = new JSONObject();
                    a.put("id_task",rs.getString(1));
                    a.put("name",rs.getString(2));
                    a.put("deadline", rs.getString(3));
                    a.put("status", rs.getString(4));                    
                    result.add(a);
                }
                out.println(new JSONArray(result));
            } catch (SQLException ex) {
                Logger.getLogger(getSearchData.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        matcher = regexAC.matcher(pathInfo);
        if (matcher.find()) {
            String q = matcher.group(1);
            String opt = matcher.group(2);
            String usr = matcher.group(3);
            
            try {
                out.write("<xml>");
                con = DBUtil.getConnection();
                if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("u")) {
                    query = "SELECT username,fullname FROM user WHERE username LIKE '%" + q + "%' OR fullname LIKE '%" + q + "%' OR email LIKE '%"
                            + q + "%' OR dob LIKE '%" + q + "%'";

                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        out.write("<User>");
                        out.write("<ID>");
                        out.write(rs.getString(1));
                        out.write("</ID>");
                        out.write("<String>");
                        out.write(rs.getString(2));
                        out.write("</String>");
                        out.write("</User>");
                    }
                } 
                if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("c")) {
                    query = "SELECT id_category,name FROM category WHERE name LIKE '%" + q + "%'";
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        out.write("<Category>");
                        out.write("<ID>");
                        out.write(rs.getString(1));
                        out.write("</ID>");
                        out.write("<String>");
                        out.write(rs.getString(2));
                        out.write("</String>");
                        out.write("</Category>");
                    }
                } 
                if (opt.equalsIgnoreCase("a") || opt.equalsIgnoreCase("t")) {
                    query = "SELECT DISTINCT t.id_task, t.name FROM task AS t "
                            + "INNER JOIN ttrelation AS tt "
                            + "ON t.id_task = tt.id_task "
                            + "INNER JOIN tag AS ta "
                            + "ON tt.id_tag = ta.id_tag "
                            + "INNER JOIN utrelation AS u "
                            + "ON t.id_task = u.id_task "
                            + "WHERE (t.name LIKE '%" + q + "%' OR ta.name LIKE '%" + q + "%') AND u.username = '" + usr + "'";
                    ps = con.prepareStatement(query);
                    rs = ps.executeQuery();
                    while (rs.next()) {
                        out.write("<Task>");
                        out.write("<ID>");
                        out.write(rs.getString(1));
                        out.write("</ID>");
                        out.write("<String>");
                        out.write(rs.getString(2));
                        out.write("</String>");
                        out.write("</Task>");
                    }
                }
                out.write("</xml>");
            }
            catch (SQLException ex) {
                Logger.getLogger(getSearchData.class.getName()).log(Level.SEVERE, null, ex);
            }
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
