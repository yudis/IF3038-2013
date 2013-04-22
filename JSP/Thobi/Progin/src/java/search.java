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
public class search extends HttpServlet {
    private Connection connection;
    private String query = null;
    private Statement statement;
    private int page = 0;
    private  int num;
    
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

        String s=request.getParameter("s");
        String outside = request.getParameter("oo");
        String isi_open = request.getParameter("o");
        String isi_close = request.getParameter("c");
        String mode = request.getParameter("m");
        int previous;
        int next;
        
        if (request.getParameter("page")!=null){
           page = Integer.parseInt(request.getParameter("page"));
        }else{
           page = 1;
        }
       
       response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
       
       if (mode.equals("1")){
           query = "Select * from user where username LIKE '%"+s+"%'";
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                num = 0;
                while (rs.next()){
                    num = num+1;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
       }else if (mode.equals("2")){
           query = "SELECT * FROM category WHERE category_name LIKE '%"+s+"%'";
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                num = 0;
                while (rs.next()){
                    num = num+1;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
       }else if (mode.equals("3")){
          query = "SELECT * FROM task WHERE task_name LIKE '%"+s+"%'";
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                num = 0;
                while (rs.next()){
                    num = num+1;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            } 
       }else if (mode.equals("4")){
           query = "SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%"+s+"%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id";
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                num = 0;
                while (rs.next()){
                    num = num+1;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            } 
       }
       
       int per_page = 1;
       int last_page = num/per_page;
       int first_page = 1;
       out.println("<a href='?m="+mode+"&s="+s+"&page="+first_page+"'>First page</a> ");
       
       if (page==first_page){
           out.println("Previous ");
       }else{
           if (request.getParameter("page")==null){
               out.println("Previous ");
           }else{
               previous = page-1;
               out.println("<a href='?m="+mode+"&s="+s+"&page="+previous+"'>Previous</a> ");
           }
       }
       
       if (page == last_page){
           out.println("Next ");
       }else{
           if (request.getParameter("page")==null){
               next = first_page + 1;
               out.println("<a href='?m="+mode+"&s="+s+"&page="+next+"'>Next</a> ");
           }else{
               next = page+1;
               out.println("<a href='?m="+mode+"&s="+s+"&page="+next+"'>Next</a> ");
           }
       }
       
       out.println("<a href='?m="+mode+"&s="+s+"&page="+last_page+"'>Last page</a>");
       
       int start = (page-1) * per_page;
       int calculate = (page*per_page) - per_page;
       int finish = page*per_page;
       
       String limit = "LIMIT "+start + "," + per_page;
       
       if (mode.equals("1")){
            query = "select * from user where username LIKE '%"+s+"%' ";
            try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    String username = rs.getString(1);
                    out.println(outside);
                    out.println(isi_open);
                    out.println(username);
                    out.println(isi_close);
                    out.println("<div>");
                    out.print("Fullname : "+rs.getString(3));
                    out.println("</div>");
                    out.println(isi_close);
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
       }else if (mode.equals("2")){
           query = "SELECT * FROM category WHERE category_name LIKE '%"+s+"%' " ;
           try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    String category = rs.getString(2);
                    out.println(outside);
                    out.println(isi_open);
                    out.println(category);
                    out.println(isi_close);
                    out.println(isi_close);
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
       }else if (mode.equals("3")){
          query = "SELECT * FROM task WHERE task_name LIKE '%"+s+"%'" + limit;
           try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                String temp = "";
                while (rs.next()){
                    temp = rs.getString(1);
                    String taskname = rs.getString(2);
                    out.println(outside);
                    out.println(isi_open);
                    out.println(taskname);
                    out.println(isi_close);
                    out.println("<div>");
                    out.print("Deadline : ");
                    String deadline = rs.getString(4);
                    out.println(deadline);
                    out.println("</div>");
                    out.println("<div>");
                    out.print("Tag : ");
                    String subquery = "SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '"+temp+"' and tag.tag_id = tasktag.tag_id";
                    Statement substatement = connection.createStatement();
                    ResultSet subrs = substatement.executeQuery(subquery);
                    while(subrs.next()){
                       String tagname = subrs.getString("tag_name");
                       out.print(tagname+" ");
                    }
                    out.println("</div>");
                    out.println("<div>");
                    String status = rs.getString(3);
                    out.print("Status :");
                    if (status.equals("1")){
                       out.println(" sudah selesai");
                    }else{
                       out.println(" belum selesai");
                    }
                    out.println("</div>");
                    out.println(isi_close);
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
       }else if (mode.equals("4")){
          query = query = "SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%"+s+"%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id "+limit;
           try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                String temp = "";
                while (rs.next()){
                    temp = rs.getString(1);
                    String taskname = rs.getString(2);
                    out.println(outside);
                    out.println(isi_open);
                    out.println(taskname);
                    out.println(isi_close);
                    out.println("<div>");
                    out.print("Deadline : ");
                    String deadline = rs.getString("deadline");
                    out.println(deadline);
                    out.println("</div>");
                    out.println("<div>");
                    out.print("Tag : ");
                    String subquery = "SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '"+temp+"' and tag.tag_id = tasktag.tag_id";
                    Statement substatement = connection.createStatement();
                    ResultSet subrs = substatement.executeQuery(subquery);
                    while(subrs.next()){
                       String tagname = subrs.getString("tag_name");
                       out.print(tagname+" ");
                    }
                    out.println("</div>");
                    out.println("<div>");
                    String status = rs.getString(3);
                    out.print("Status :");
                    if (status.equals("1")){
                       out.println(" sudah selesai");
                    }else{
                       out.println(" belum selesai");
                    }
                    out.println("</div>");
                    out.println(isi_close);
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
       }
       
      }
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
   