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
public class headervalidation extends HttpServlet {

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

            String content = request.getParameter("content");
            int mode = Integer.parseInt(request.getParameter("idx"));
            int filter = Integer.parseInt(request.getParameter("filter"));
                    
            List<String> rs;
            Query q;
            switch(mode) {
                case 1:
                	String result = "";
                	
                    q = pm.newQuery("SELECT username FROM " + User.class.getName() + " WHERE username LIKE '%" + content + "%'");
                    rs = (List<String>) q.execute();
                    Iterator<String> it1 = rs.iterator();
                    while(it1.hasNext()){
                        result += it1.next()+"|";
                    }
                    
                    q = pm.newQuery("SELECT categoryname FROM " + Category.class.getName() + " WHERE categoryname LIKE '%" + content + "%'");
                	rs = (List<String>) q.execute();
                    Iterator<String> it2 = rs.iterator();
                    while(it2.hasNext()){
                        result += it2.next()+"|";
                    }
                    
                    q = pm.newQuery("SELECT taskname FROM " + Task.class.getName() + " WHERE taskname LIKE '%" + content + "%'");
                	rs = (List<String>) q.execute();
                    Iterator<String> it3 = rs.iterator();
                    while(it3.hasNext()){
                        result += it3.next()+"|";
                    }
                    
                    System.out.println(result);
                    out.print(result);
                    q.closeAll();
                    break;
                    
                case 2:
                    switch (filter) {
                        case 1:
                        	q = pm.newQuery("SELECT username FROM " + User.class.getName() + " WHERE username LIKE '%" + content + "%'");
                            rs = (List<String>) q.execute();
                            Iterator<String> it4 = rs.iterator();
                            while(it4.hasNext()){
                                out.print(it4.next()+"|");
                            }
                            q.closeAll();
                            break;
                            
                        case 2:
                        	q = pm.newQuery("SELECT email FROM " + User.class.getName() + " WHERE email LIKE '%" + content + "%'");
                            rs = (List<String>) q.execute();
                            Iterator<String> it5 = rs.iterator();
                            while(it5.hasNext()){
                                out.print(it5.next()+"|");
                            }
                            q.closeAll();
                            break;
                            
                        case 3:
                        	q = pm.newQuery("SELECT fullname FROM " + User.class.getName() + " WHERE fullname LIKE '%" + content + "%'");
                        	rs = (List<String>) q.execute();
                            Iterator<String> it6 = rs.iterator();
                            while(it6.hasNext()){
                                out.print(it6.next()+"|");
                            }
                            q.closeAll();
                            break;
                            
                        case 4:
                        	q = pm.newQuery("SELECT birthday FROM " + User.class.getName() + " WHERE birthday LIKE '%" + content + "%'");
                        	rs = (List<String>) q.execute();
                            Iterator<String> it7 = rs.iterator();
                            while(it7.hasNext()){
                                out.print(it7.next()+"|");
                            }
                            q.closeAll();
                            break;   
                            
                    }
                    break;
                case 3:
                	q = pm.newQuery("SELECT categoryname FROM " + Category.class.getName() + " WHERE categoryname LIKE '%" + content + "%'");
                	rs = (List<String>) q.execute();
                    Iterator<String> it8 = rs.iterator();
                    while(it8.hasNext()){
                        out.print(it8.next()+"|");
                    }
                    q.closeAll();
                    break;
                    
                case 4:
                	q = pm.newQuery("SELECT taskname FROM " + Task.class.getName() + " WHERE taskname LIKE '%" + content + "%'");
                	rs = (List<String>) q.execute();
                    Iterator<String> it9 = rs.iterator();
                    while(it9.hasNext()){
                        out.print(it9.next()+"|");
                    }
                    q.closeAll();
                    break;
                    
            }
            
            pm.close();
            
        } catch(Exception exc){
            System.out.println("Error : "+exc.toString());
        }finally {            
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
