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
import java.util.ArrayList;

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
       }else if(mode.equals("0")){
            try {
                query = "Select * from user where username LIKE '%"+s+"%'";
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                num = 0;
                while (rs.next()){
                    num = num+1;
                }
                
               query = "SELECT * FROM category WHERE category_name LIKE '%"+s+"%'";
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()){
                    num = num+1;
                }
                
                query = "SELECT * FROM task WHERE task_name LIKE '%"+s+"%'";
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()){
                    num = num+1;
                }
                
                query = "SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%"+s+"%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id";
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()){
                    num = num+1;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            } 
       }
       
       int per_page = 1;
       double last_page = Math.ceil(num/per_page);
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
       
       if ((page==last_page)||(last_page==0)){
           out.println("Next ");
       }else{
           if (request.getParameter("page")==null){
               next = first_page + 1;
               out.println("<a href='?m="+mode+"&s="+s+"&page="+next+"'>Next</a> ");
           }else{
               if (num!=0){
                next = page+1;
                out.println("<a href='?m="+mode+"&s="+s+"&page="+next+"'>Next</a> ");
               }
           }
       }
       
       out.println("<a href='?m="+mode+"&s="+s+"&page="+last_page+"'>Last page</a>");
       
       int start = (page-1) * per_page;
       int calculate = (page*per_page) - per_page;
       int finish = page*per_page;
       
       String limit = "LIMIT "+start + "," + per_page;
       
       if (mode.equals("1")){
            query = "select * from user where username LIKE '%"+s+"%' " + limit;
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
           query = "SELECT * FROM category WHERE category_name LIKE '%"+s+"%' " + limit ;
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
                    out.println("<div class = 'tugas' id = '"+temp+"'>");
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
                    String parameter = "'"+temp+"'";
                    String function = "change("+parameter+")";
                    out.println("</div>");
                    out.println("<div>");
                    String status = rs.getString(3);
                    out.println("Status");
                    if (status.equals("1")){
                        out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' checked='checked' onchange="+function+">");
                        out.println("sudah selesai");
                    }else{
                         out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' onchange="+function+">");
			 out.println("belum selesai");
                    }
                    out.println("</div>");
                    out.println(isi_close);
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
       }else if (mode.equals("4")){
          query = "SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%"+s+"%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id "+limit;
           try {
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                String temp = "";
                while (rs.next()){
                    temp = rs.getString(1);
                    String taskname = rs.getString(2);
                    out.println("<div class = 'tugas' id = '"+temp+"'>");
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
                    String parameter = "'"+temp+"'";
                    String function = "change("+parameter+")";
                    out.println("</div>");
                    out.println("<div>");
                    String status = rs.getString(3);
                    out.println("Status");
                    if (status.equals("1")){
                        out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' checked='checked' onchange="+function+">");
                        out.println("sudah selesai");
                    }else{
                         out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' onchange="+function+">");
			 out.println("belum selesai");
                    }
                    out.println("</div>");
                    out.println(isi_close);
                }
            }
            catch (SQLException e) {
             e.printStackTrace();
            }
       }else if(mode.equals("0")){
           try {
               //untuk username
               query = "select * from user where username LIKE '%"+s+"%' ";
                ArrayList<String> allresult = new ArrayList<String>();
                int n = 0;
                statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()){
                    n++;
                }
                
                
                if (n>0){
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);
                    while (rs.next()){
                        String username = rs.getString(1);
                        allresult.add(username);
                    }
                }
                
               //untuk category
               query = "SELECT * FROM category WHERE category_name LIKE '%"+s+"%' ";
               // ArrayList<String> allresult = new ArrayList<String>();
                n = 0;
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()){
                    n++;
                }
                
                
                if (n>0){
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);
                    while (rs.next()){
                        String categoryid = rs.getString(1);
                        allresult.add("C"+categoryid);
                    }
                }
                
                //untuk task
                query = "SELECT * FROM task WHERE task_name LIKE '%"+s+"%'";
                // ArrayList<String> allresult = new ArrayList<String>();
                n = 0;
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()){
                    n++;
                }

                
                if (n>0){
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);
                    while (rs.next()){
                        String taskid = rs.getString(1);
                        allresult.add("T"+taskid);
                    }
                }
                
                //untuk tag
                query = "SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%"+s+"%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id ";
                // ArrayList<String> allresult = new ArrayList<String>();
                n = 0;
                statement = connection.createStatement();
                rs = statement.executeQuery(query);
                while (rs.next()){
                    n++;
                }
                
                
                if (n>0){
                    statement = connection.createStatement();
                    rs = statement.executeQuery(query);
                    while (rs.next()){
                        String tag = rs.getString(2);
                        allresult.add("G"+tag);
                    }
                }
                
                
                int i = start;
                
                while (i<finish){
                    //untuk username
                    query = "select * from user where username ='"+allresult.get(i)+"' ";
                     //ArrayList<String> allresult = new ArrayList<String>();
                     n = 0;
                     statement = connection.createStatement();
                     rs = statement.executeQuery(query);
                     while (rs.next()){
                         n++;
                     }
                     
                     if (n>0){
                         statement = connection.createStatement();
                         rs = statement.executeQuery(query);
                         out.println("<h2>Username</h2>");
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
                     
                     if (allresult.get(i).substring(0, 1).equals("C")){
                        //untuk category
                       query = "SELECT * FROM category WHERE category_id='"+allresult.get(i).substring(1)+"' ";
                       // ArrayList<String> allresult = new ArrayList<String>();
                        n = 0;
                        statement = connection.createStatement();
                        rs = statement.executeQuery(query);
                        while (rs.next()){
                            n++;
                        }


                        if (n>0){
                            statement = connection.createStatement();
                            rs = statement.executeQuery(query);
                            out.println("<h2>Category</h2>");
                            while (rs.next()){
                                String category = rs.getString(2);
                                out.println(outside);
                                out.println(isi_open);
                                out.println(category);
                                out.println(isi_close);
                                out.println(isi_close);
                            }
                        }
                     }
                     if (allresult.get(i).substring(0, 1).equals("T")){
                        //untuk category
                       query = "SELECT * FROM task WHERE task_id ='"+allresult.get(i).substring(1)+"'";
                       // ArrayList<String> allresult = new ArrayList<String>();
                        n = 0;
                        statement = connection.createStatement();
                        rs = statement.executeQuery(query);
                        while (rs.next()){
                            n++;
                        }
                        if (n>0){
                            statement = connection.createStatement();
                            rs = statement.executeQuery(query);
                            String temp = "";
                            out.println("<h2>Taskname</h2>");
                            while (rs.next()){
                                temp = rs.getString(1);
                                String taskname = rs.getString(2);
                                out.println("<div class = 'tugas' id = '"+temp+"'>");
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
                                String parameter = "'"+temp+"'";
                                String function = "change("+parameter+")";
                                out.println("</div>");
                                out.println("<div>");
                                String status = rs.getString(3);
                                out.println("Status");
                                if (status.equals("1")){
                                    out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' checked='checked' onchange="+function+">");
                                    out.println("sudah selesai");
                                }else{
                                     out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' onchange="+function+">");
                                     out.println("belum selesai");
                                }
                                out.println("</div>");
                                out.println(isi_close);
                            }
                        }
                     }
                    
                     if (allresult.get(i).substring(0, 1).equals("G")){
                        //untuk category
                       query = "SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE task.task_name = '"+allresult.get(i).substring(1)+"' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id LIMIT "+per_page;
                       // ArrayList<String> allresult = new ArrayList<String>();
                        n = 0;
                        statement = connection.createStatement();
                        rs = statement.executeQuery(query);
                        while (rs.next()){
                            n++;
                        }


                        if (n>0){
                            statement = connection.createStatement();
                            rs = statement.executeQuery(query);
                            String temp = "";
                            out.println("<h2>Tag</h2>");
                            while (rs.next()){
                                temp = rs.getString(1);
                                String taskname = rs.getString(2);
                                out.println("<div class = 'tugas' id = '"+temp+"'>");
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
                                String parameter = "'"+temp+"'";
                                String function = "change("+parameter+")";
                                out.println("</div>");
                                out.println("<div>");
                                String status = rs.getString(3);
                                out.println("Status");
                                if (status.equals("1")){
                                    out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' checked='checked' onchange="+function+">");
                                    out.println("sudah selesai");
                                }else{
                                     out.println("<input id='checkbox_"+temp+"' value = '"+temp+"' type='checkbox' onchange="+function+">");
                                     out.println("belum selesai");
                                }
                                out.println("</div>");
                                out.println(isi_close);
                            }
                        }
                     }
                     i++;
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
   