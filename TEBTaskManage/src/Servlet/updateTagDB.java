/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Class.*;
import java.util.HashMap;
import java.util.List;
/**
 *
 * @author User
 */
public class updateTagDB extends HttpServlet {

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
            Function func = new Function();
            
            String listTag = request.getParameter("tag");
            String taskid = request.getParameter("taskid");
            
            PersistenceManager pm = PMF.get().getPersistenceManager();
            
            Query q = pm.newQuery(Task_Tag.class);
            q.setFilter("taskid == idtask");
            q.declareParameters("int idtask");
            
            Task_Tag result = (Task_Tag)q.execute(taskid);
            pm.deletePersistent(result);
            
            String [] tag = listTag.split(",");
            for(int i = 0; i < tag.length ; i++){
                String tagId = func.GetTagId(tag[i]);
                System.out.println(i);
                System.out.println(tag[i]);
                System.out.println(">>"+tagId+"<<");
                
                Task_Tag t = new Task_Tag(Integer.parseInt(taskid), Integer.parseInt(tagId));
                pm.makePersistent(t);
            }
            
            q = pm.newQuery("select tagid from " + Task_Tag.class.getName());
            q.setFilter("taskid == idtask");
            q.declareParameters("int idtask");
            
            List<Integer> results = (List<Integer>)q.execute(Integer.parseInt(taskid));
            out.print("Tag : <i>");
            for(Integer i : results){
                HashMap<String, String> tagData = func.GetTag(i.toString());
                out.print("<u>"+tagData.get("tagname") +"</u> ");
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
