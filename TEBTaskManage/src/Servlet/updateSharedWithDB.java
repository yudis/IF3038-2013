/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import Class.Function;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class updateSharedWithDB extends HttpServlet {

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
        try {
            /* TODO output your page here. You may use following sample code. */
            String listAssignee = request.getParameter("listAssigne");
            String taskid = request.getParameter("taskid");
            
            PersistenceManager pm = PMF.get().getPersistenceManager();
            
            Query q = pm.newQuery(Assignee.class);
            q.setFilter("taskid == idtask");
            q.declareParameters("int idtask");
            
            Assignee results = (Assignee)q.execute(Integer.parseInt(taskid));
            pm.deletePersistent(results);

            String userActive = "";
            if(request.getSession().getAttribute("userlistapp")!=null){
                userActive = request.getSession().getAttribute("userlistapp").toString();
            }
            
            HashMap<String,String> task = (new Function()).GetTask(taskid);
            
            Assignee a = new Assignee(task.get("username"), Integer.parseInt(taskid));
            pm.makePersistent(a);
            
            String [] assignee = listAssignee.split(",");
            for(int i = 0; i < assignee.length ; i++){
            	Assignee b = new Assignee(assignee[i], Integer.parseInt(taskid));
            	pm.makePersistent(b);
            }
            
            q = pm.newQuery("select username from " + Assignee.class.getName());
            q.setFilter("taskid == idtask");
            q.declareParameters("int idtask");
            
            List<String> result = (List<String>)q.execute(Integer.parseInt(taskid));
            
            out.print("Shared with : <i>");
            for(String s : result){
                out.print("<a href=\"profile?username="+s +"\"><u>"+s+"</u></a> ");
            }
            out.print("</i>");
            q.closeAll();
            pm.close();
        } catch(Exception exc){
            System.out.println("Error : "+exc.toString());
        }finally {            
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
