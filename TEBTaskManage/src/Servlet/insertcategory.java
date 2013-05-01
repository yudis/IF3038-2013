/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Date;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;

/**
 *
 * @author User
 */
@SuppressWarnings("serial")
public class insertcategory extends HttpServlet {

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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
		
		DatastoreService iseng = DatastoreServiceFactory.getDatastoreService();
		
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
        try {
		
            String username = request.getParameter("username");
            
            String namecategory = request.getParameter("newCategory");//$_POST['newCategory'];
            String listAssignee = request.getParameter("listAssignee");//$_POST['listAssignee'];
 
			Date dateFormat = new Date();
            Date date = new Date();	
            String [] Assignee = listAssignee.split(",");
            
			// insert categori
			Category cat = new Category(1, namecategory, username, date);
			try {
				pm.makePersistent(cat);
			}
			catch (Exception e) {
				out.print("Data Insert FAIL");
			} finally {
				
			}
            			
			Query q = pm.newQuery("select categoryid from "+Category.class.getName());
			List<String> result = (List<String>)q.execute();
			for (String s : result) {
				Responsibility res = new Responsibility(username, Integer.parseInt(s));
				try {
					pm.makePersistent(res);
				}
				catch (Exception e) {
					out.print("Data Insert FAIL (2)");
				} finally {
					
				}
				// insert assigne
				for(int i = 0; i < Assignee.length ; i++){
					Responsibility res2 = new Responsibility(Assignee[i], Integer.parseInt(s));
					try {
						pm.makePersistent(res2);
					}
					catch (Exception e) {
						out.print("Data Insert FAIL (2)");
					} finally {
						
					}
				}
			}
			q.closeAll();
			pm.close();		
            
        } catch(Exception exc){
            System.out.println(exc.toString());
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
