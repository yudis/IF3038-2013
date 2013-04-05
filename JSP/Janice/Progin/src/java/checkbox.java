/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

/**
 *
 * @author TOSHIBA
 */
public class checkbox extends HttpServlet {
    private Connection connection;
    private String query = null;
    private Statement statement;
    
    public void init(ServletConfig config) throws ServletException {
 
        String url = "jdbc:mysql://localhost:3306/progin_405_13510029";
        String username = "progin"; 
        String password = "progin"; 
        try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();

         connection = DriverManager.getConnection(url, username , password);
        }
        catch (Exception e) {

         e.printStackTrace();
        }

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
    
     protected void doPost(
        HttpServletRequest request, 
        HttpServletResponse response
        ) throws ServletException, IOException {
        
        String taskid = request.getParameter("idcheckbox");
        String value = request.getParameter("checked");
        String valuenum = "";
        if (value.equals("true")){
            valuenum = "1";
        }else{
            valuenum = "0";
        }
       
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
      
       
            query = "UPDATE task SET status= '$valuenum' WHERE task_id = '$taskid'";
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    String task_id = rs.getString(1);
                    out.println("<div class = 'judulhasilsearch'>");
                    String task_name = rs.getString(2);
                    out.println("</div>");
                    out.println("<div>");
                    String deadline = rs.getString(4);
                    out.print("Deadline : ");
                    out.println(deadline);
                    out.println("</div>");
                    out.println("<div>");
                    out.print("Tag : ");
                    String subquery = "SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '"+task_id+"' and tag.tag_id = tasktag.tag_id";
                    Statement substatement = connection.createStatement();
                    ResultSet subrs = substatement.executeQuery(subquery);
                    while(subrs.next()){
                       String tagname = subrs.getString("tag_name");
                       out.print(tagname+" ");
                    }
                    String parameter = "'"+task_id+"'";
                    String function = "change("+parameter+")";
                    out.println("</div>");
                    out.print("<div>");
                    String status = rs.getString(3);
                    out.println("Status");
                    if (status.equals("1")){
                        out.println("<input id='checkbox_"+task_id+"' value = '"+task_id+"' type='checkbox' checked='checked' onchange="+function+">");
                        out.println("sudah selesai");
                    }else{
                         out.println("<input id='checkbox_"+task_id+"' value = '"+task_id+"' type='checkbox' onchange="+function+">");
			 out.println("belum selesai");
                    }
                    out.println("</div>");
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
    
      }
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
   