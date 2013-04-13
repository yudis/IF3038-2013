/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.xml.internal.bind.v2.model.core.ID;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class AddTask extends HttpServlet {

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
    String htmlresponse;
    private DBConnector con;
    private ResultSet resultSet = null;
    public int ID;
    
    @Override
    public void init() {
        con = new DBConnector();
    }
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            con.Init();
            
            String taskname = request.getParameter("namatask");
            String deadline = request.getParameter("deadline");
            String[] assignee = request.getParameterValues("assignee");
            String[] tag = request.getParameterValues("tag");
            String user = (String) request.getSession().getAttribute("userid");
            String cat = (String) request.getSession().getAttribute("category");
            
            ResultSet set = con.ExecuteQuery("SELECT COUNT(*) FROM task");
            while (set.next()){
                ID = set.getInt(1);
            }
            ID =+ 1;
            
            ResultSet data = con.ExecuteQuery("INSERT INTO task VALUES '"+ID+"','"+user+"','"+taskname+"','"+cat+"','0','"+deadline+"'");
            
            
            for( int i = 0; i < tag.length; i++ )  
            {  
                ResultSet rs = con.ExecuteQuery("INSERT INTO tags VALUES '"+ID+"','"+tag[i]+"'");
            }  
            
            for( int i = 0; i < assignee.length; i++ )  
            {  
                ResultSet rs2 = con.ExecuteQuery("INSERT INTO assignee VALUES '"+ID+"','"+assignee[i]+"'");
            } 

            out.print("Tugas baru telah disimpan");

            
        
            con.Close();
            response.sendRedirect("dashboard.jsp");
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
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
