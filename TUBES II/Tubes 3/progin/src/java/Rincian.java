/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Compaq
 */
public class Rincian extends HttpServlet {

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
    
    String htmlresponse = "";
    private DBConnector con;
    private ResultSet resultSet = null;
    private ResultSet resultSet2 = null;
    
    @Override
    public void init() {
        con = new DBConnector();
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String requesttype = request.getParameter("requesttype");
            if (requesttype.equals("load")) {
                htmlresponse = doLoadRincian(request);
            } else if (requesttype.equals("save")) {
                htmlresponse = doSaveRincian(request);
            }
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println(htmlresponse);
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private String doLoadRincian(HttpServletRequest request) throws Exception {
        String out = "";
        con.Init();
        String username = (String) request.getSession().getAttribute("userid");
        String taskid = (String) request.getSession().getAttribute("taskid");
        String query = "SELECT * FROM task LEFT JOIN assignee "+
                        "ON task.ID=assignee.IDTask "+
                        "WHERE task.ID='"+taskid+"' AND (task.IDCreator='"+username+"' OR assignee.IDUser='"+username+"')";
        resultSet = con.ExecuteQuery(query);
        resultSet2 = con.ExecuteQuery(query);
        if (resultSet.next()) {
            out+= "<form name='rincian'>\n";
            out+= "<h1 class='judul'>Rincian Tugas</h1>\n";
            out+= "Nama Task : <input type='text' id='namatask' value='"+resultSet.getString("Nama")+"' border='0' /> <br>";
            out+= "Attachment : <br> \n";
            out+= "<video width='320' height='240' controls='controls'>\n";
            out+= "<source src='assets/mov_bbb.ogg' type='video/ogg'>\n";
            out+= "Your browser does not support the video tag.\n";
            out+= "</video>\n";
            out+= "<br>\n";
            out+= "Deadline : <input  type='text' id='deadline' value='"+resultSet.getString("Deadline")+"' border='0' /><br>\n";
            
            String listassignee = "";
            String listassignee1 = "";
            while (resultSet2.next()) {
                if (resultSet2.getString("IDUser") != null) {
                    listassignee += resultSet2.getString("IDUser")+",";
                }
            }
            if (!listassignee.equals("")) {
                listassignee1 = listassignee.substring(0, listassignee.length()-1);
            }
            out+= "Assignee : <input type='text' id='listassignee' value='"+listassignee1+"' border='0' />";
            
            query = "SELECT * FROM task LEFT JOIN tags "+
                    "ON task.ID=tags.IDTask "+
                    "WHERE task.ID='"+resultSet.getString("ID") +"'";
            resultSet2 = con.ExecuteQuery(query);
            String listtag = "";
            while (resultSet2.next()) {
                listtag += resultSet2.getString("Tag")+",";
            }
            String listtag1 = listtag.substring(0, listtag.length()-1);
            out+= "Tag : <input  type='text' id='listtag' value='"+listtag1+"' border='0' /> <br> \n";
            
            out+= "<img src='images/edit.png' width='150' id='edit-button' value='EDIT' onClick='edit()' />\n";
            out+= "<img src='images/save.png' width='150' id='save-button' value='SAVE' onClick='saveTaskDetails()' /><br><br>\n";
        } else {
            query = "SELECT * FROM task LEFT JOIN assignee "+
                    "ON task.ID=assignee.IDTask "+
                    "WHERE task.ID='"+taskid+"'";
            resultSet = con.ExecuteQuery(query);
            resultSet2 = con.ExecuteQuery(query);
            if (resultSet.next()) {
                out+= "<form name='rincian'>\n";
                out+= "<h1 class='judul'>Rincian Tugas</h1>\n";
                out+= "Nama Task : <input type='text' id='namatask' value='"+resultSet.getString("Nama")+"' border='0' /> <br>";
                out+= "Attachment : <br> \n";
                out+= "<video width='320' height='240' controls='controls'>\n";
                out+= "<source src='assets/mov_bbb.ogg' type='video/ogg'>\n";
                out+= "Your browser does not support the video tag.\n";
                out+= "</video>\n";
                out+= "<br>\n";
                out+= "Deadline : <input  type='text' id='deadline' value='"+resultSet.getString("Deadline")+"' border='0' /><br>\n";

                String listassignee = "";
                String listassignee1 = "";
                while (resultSet2.next()) {
                    if (resultSet2.getString("IDUser") != null) {
                        listassignee += resultSet2.getString("IDUser")+",";
                    }
                }
                if (!listassignee.equals("")) {
                    listassignee1 = listassignee.substring(0, listassignee.length()-1);
                }
                out+= "Assignee : <input type='text' id='listassignee' value='"+listassignee1+"' border='0' />";

                query = "SELECT * FROM task LEFT JOIN tags "+
                        "ON task.ID=tags.IDTask "+
                        "WHERE task.ID='"+resultSet.getString("ID") +"'";
                resultSet2 = con.ExecuteQuery(query);
                String listtag = "";
                while (resultSet2.next()) {
                    listtag += resultSet2.getString("Tag")+",";
                }
                String listtag1 = listtag.substring(0, listtag.length()-1);
                out+= "Tag : <input  type='text' id='listtag' value='"+listtag1+"' border='0' /> <br> \n";
            }
        }
        con.Close();
        return out;
    }
    
    private String doSaveRincian(HttpServletRequest request) throws SQLException, Exception {
        if (request.getParameter("tabletype").equals("taskdetails")) {
            return doSaveTaskDetails(request);
        } else if (request.getParameter("tabletype").equals("komentar")) {
            return doSaveComment(request);
        } else {
            return "";
        }
    }
    
    private String doSaveTaskDetails(HttpServletRequest request) throws SQLException, Exception {
        String out = "";
        con.Init();
        String idtask = (String) request.getSession().getAttribute("taskid");
        String namatask = request.getParameter("namatask");
        //$attachment = $_GET['attachment'];
        String deadline = request.getParameter("deadline");
        String listassignee = request.getParameter("listassignee");
        String listtag = request.getParameter("listtag");
        
        String assignees[] = listassignee.split(",");
        String tags[] = listtag.split(",");
        
        String query = "UPDATE task SET NAMA='"+namatask+"', Deadline='"+deadline+"' WHERE ID='"+idtask+"'";
        
        if (con.ExecuteUpdate(query)!=0)
        {
            out+= "Record task updated";
        }
        else 
        {
            out+= "Error updating record task ";
        }
        
        query = "DELETE FROM tags WHERE IDTask='"+idtask+"'";
        if (con.ExecuteUpdate(query)!=0)
        {
            out+= "Record tags deleted";
        }
        else 
        {
            out+= "Error deleting record tags ";
        }
        
        int i = 0;
        while (i < tags.length) {
            query = "INSERT INTO tags VALUES ('"+idtask+"','"+tags[i]+"')";
            if (con.ExecuteUpdate(query)!=0)
            {
                out+= "Record tags updated";
            }
            else 
            {
                out+= "Error updating record tags ";
            }
            i++;
        }
        
        query = "DELETE FROM assignee WHERE IDTask='"+idtask+"'";
        if (con.ExecuteUpdate(query)!=0)
        {
            out+= "Record assignee deleted";
        }
        else 
        {
            out+= "Error deleting record assignee ";
        }
        
        i = 0;
        while (i < assignees.length) {
            query = "INSERT INTO assignee VALUES ('"+idtask+"','"+assignees[i]+"')";
            if (con.ExecuteUpdate(query)!=0)
            {
                out+= "Record assignee updated";
            }
            else 
            {
                out+= "Error updating record assignee ";
            }
            i++;
        }
        con.Close();
        return out;
    }
    
    private String doSaveComment(HttpServletRequest request) throws SQLException, Exception {
        String out = "";
        con.Init();
        String idtask = request.getParameter("idtask");
        String iduser = (String) request.getSession().getAttribute("userid");
        String comment = request.getParameter("commentarea");
        String query = "SELECT COUNT(*) as num FROM komentar";
        resultSet = con.ExecuteQuery(query);
        if (resultSet.next()) {
            String nextidstring;
            int nextid = resultSet.getInt("num")+1;
            if (nextid < 10) {
                nextidstring = "K00"+nextid;
            } else if (nextid < 100) {
                nextidstring = "K0"+nextid;
            } else {
                nextidstring = "K"+nextid;
            }
            query = "INSERT INTO komentar (ID,IDTask,IDUser,Waktu,Isi) VALUES ('"+nextidstring+"','"+idtask+"','"+iduser+"',now(),'"+comment+"')";

            if (con.ExecuteUpdate(query)!=0)
            {
                out+= "Record inserted";
            }
            else 
            {
                out+= "Error inserting record ";
            }
        }
        con.Close();
        return out;
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
