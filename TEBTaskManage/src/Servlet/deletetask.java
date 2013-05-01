/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 *
 * @author User
 */
public class deletetask extends HttpServlet {

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
        doPost(request, response);
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
        PrintWriter out = response.getWriter();
        PersistenceManager pm = PMF.get().getPersistenceManager();
        try {
            /* TODO output your page here. You may use following sample code. */
            String taskid = request.getParameter("taskid");
            Query q1 = pm.newQuery("SELECT filename FROM " + Attachment.class.getName() + " WHERE taskid=" + taskid);
            List<String> result = (List<String>) q1.execute();
            
            Iterator<String> iterator = result.iterator();
            while(iterator.hasNext()){
                String realPath = request.getRealPath("attachment");
                String filename = realPath + "/" + iterator.next();
                File n = new File(filename);
                n.delete();
            }
            
            Query q2 = pm.newQuery(Task.class);
            q2.setFilter("taskid == param");
            q2.declareParameters("int param");
            
			pm.deletePersistent((Task) q2.execute(taskid));
			
			q1.closeAll();
    		q2.closeAll();
    		pm.close();
            
            response.sendRedirect("dashboard.jsp");
        } catch(Exception e) {
        	System.out.println(e.toString());
        } finally {               
            out.close();
        }
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
