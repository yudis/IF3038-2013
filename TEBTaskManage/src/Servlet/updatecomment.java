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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
/**
 *
 * @author ASUS
 */
public class updatecomment extends HttpServlet {

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
            String userActive = "";
            if(request.getSession().getAttribute("userlistapp")!=null){
                userActive = request.getSession().getAttribute("userlistapp").toString();
            }
            
            int commentid = Integer.parseInt(func.getNextCommentId());
            String taskid = request.getParameter("taskid");
            String comment = request.getParameter("comment");
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String createdate = dateFormat.format(date);
            
            PersistenceManager pm = PMF.get().getPersistenceManager();
            
            Comment c = new Comment(commentid, createdate, comment, userActive, Integer.parseInt(taskid));
            pm.makePersistent(c);
            
            int numpage;
            if (Integer.parseInt(func.GetNComment(taskid))%5 == 0) {
                numpage = (Integer.parseInt(func.GetNComment(taskid))/5);
            }
            else {
                numpage = (Integer.parseInt(func.GetNComment(taskid))/5)+1;
            }
            int index = 5*(numpage-1);
            
            Query q = pm.newQuery(Comment.class);
            q.setFilter("taskid == idtask");
            q.declareParameters("int idtask");
            q.setRange(index, index+5);
            
            List<Comment> results = (List<Comment>)q.execute(taskid);
            
            out.print("<p><b>"+func.GetNComment(taskid) +" Comment</b></p>");
            out.print("<div name=\"cmnt\" id=\"comment-list\">");
            
            for (Comment v : results){
                out.print(" <div id=\"comment\">");
                out.print(" 	<div id=\"user-info\">");
                out.print(" 		<div id=\"left-comment-body\">");
                out.print(" 			<img src=\"avatar/"+func.GetUser(v.username).get("avatar") +"\" width=\"50px\" height=\"50px\"/>");
                out.print(" 		</div>");
                out.print(" 		<div id=\"right-comment-body\">");
                out.print(" 			<b id=\"komentator\">"+v.username +"</b>");
                out.print(" 			<br>");
                out.print(" 			<b id=\"post-date\">Post at "+v.createdate +"</b>");
                out.print(" 		</div>");
                out.print(" 		<div id=\"delete-comment\">");
                        if(v.username.equals(userActive)){
                            out.print("<a href=\"#cmnt\" onClick=\"deleteComment("+v.commentid +","+taskid+","+index+")\"><i>Delete Comment</i></a>");
                        }
                out.print(" 		</div>");
                out.print(" 	</div>");
                out.print(" 	<div id=\"comment-box\">");
                out.print(" 		<p>");
                out.print(v.message);
                out.print(" 		</p>");
                out.print(" 	</div>");
                out.print(" </div>");
            }
            out.print(" </div>");
            if (numpage > 1) {
                out.print(" <a href=\"#cmnt\" onClick=\"prevPage("+taskid+","+index+")\"><i>Prev</i></a>");
            }
            q.closeAll();
            pm.close();
        } catch(Exception exc){
            System.out.println(exc.toString());
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