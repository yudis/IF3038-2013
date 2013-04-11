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

public class listtask extends HttpServlet {
    private Connection connection;
    private String query = null;
    private Statement statement;
    
    @Override
    public void init(ServletConfig config) throws ServletException {
 
        String url = "jdbc:mysql://localhost:3306/progin_405_13510029";
        String username = "progin"; 
        String password = "progin"; 
        try {
         Class.forName("com.mysql.jdbc.Driver").newInstance();

         connection = DriverManager.getConnection(url, username , password);
        }
        catch (Exception e) {
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
    
    @Override
    protected void doPost(
        HttpServletRequest request, 
        HttpServletResponse response
        ) throws ServletException, IOException {

			String uname = request.getParameter("uname");
                        String cat = request.getParameter("cat");
		   
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
                        
                        String status;
                        
			try {
				if (cat.equals("all")) {
					int iterator = 0;

					query="SELECT DISTINCT category_name, category_id FROM category, task, task_incharge WHERE category.category_id = task.task_category AND task.task_id = task_incharge.task_id AND task_incharge.people_incharge_task = '"+uname+"'";

					statement = connection.createStatement();
					ResultSet rs = statement.executeQuery(query);
					
					out.println ("<h1 class='inlineblock'>Dashboard</h1> <div id='addTask'></div>");
					
					while (rs.next()) {
						out.println ("<section id='main-K"+iterator+"'><h2>"+rs.getString("category_name")+"</h2>");

						query="SELECT * FROM task, category, task_incharge, task_creator WHERE task_incharge.people_incharge_task = '"+uname+"' AND task_category = '"+rs.getString("category_id")+"' AND task.task_category = category.category_id AND task.task_id = task_incharge.task_id AND task.task_id = task_creator.task_id";

                                                statement = connection.createStatement();
                                                
						ResultSet rs2 = statement.executeQuery(query);

						while (rs2.next()) {
							if (rs2.getString("status").equals("0")) {
								status = "Belum selesai.";
                                                        }
                                                        else {
								status = "Sudah selesai.";
                                                        }
									
							out.println ("<div class='tugas'>");
									
							if (rs2.getString("creator").equals(uname)) {
								out.println("<button id='hapustugas' onclick='deleteTask(\""+rs2.getString("task_id")+"\")'>x</button>");
                                                        }
									
							out.println("<div><a href='tugas.php?id="+rs2.getString("task_id")+"'>"+rs2.getString("task_name")+"</a></div><div>Deadline: <strong>"+rs2.getString("deadline")+"</strong></div><div>Status: <strong>"+status+"</strong></div><div>Ubah Status: <button id='editStatus' onclick='EditStatus(1,\""+rs2.getString("task_id")+"\")'>Selesai</button> <button id='editStatus' onclick='EditStatus(0,\""+rs2.getString("task_id")+"\")'>Belum</button></div><br/><div>Tags:<ul class='tag'>");

							query="SELECT DISTINCT tag.tag_name FROM tag, tasktag WHERE tasktag.task_id = '"+rs2.getString("task_id")+"' AND tag.tag_id = tasktag.tag_id";

                                                        statement = connection.createStatement();
                                                        
							ResultSet rs3 = statement.executeQuery(query);

							while (rs3.next()) {
								out.println ("<li>"+rs3.getString("tag_name")+"</li>");
							}
							out.println ("</ul></div></div>");
						}
						out.println ("</section>");
						iterator++;
					}
						}
						else {
							int iterator = 0;

							query="SELECT * FROM category WHERE category.category_id = '"+cat+"'";

							statement = connection.createStatement();
							ResultSet rs = statement.executeQuery(query);

							out.println ("<h1 class='inlineblock'>Dashboard</h1> <button id='addTask' onclick='NewTask()'>add new task...</button> <button id='delCat' onclick='DeleteCategory()'>Delete This Category</button>");

							while (rs.next()) {
						out.println ("<section id='main-K"+iterator+"'><h2>"+rs.getString("category_name")+"</h2>");

						query="SELECT * FROM task, category, task_incharge, task_creator WHERE task_incharge.people_incharge_task = '"+uname+"' AND task_category = '"+rs.getString("category_id")+"' AND task.task_category = category.category_id AND task.task_id = task_incharge.task_id AND task.task_id = task_creator.task_id";

                                                statement = connection.createStatement();
						ResultSet rs2 = statement.executeQuery(query);

						while (rs2.next()) {
							if (rs2.getString("status").equals("0")) {
								status = "Belum selesai.";
                                                        }
                                                        else {
								status = "Sudah selesai.";
                                                        }
									
									out.println ("<div class='tugas'>");
									
									if (rs2.getString("creator").equals(uname)) {
										out.println("<button id='hapustugas' onclick='deleteTask(\""+rs2.getString("task_id")+"\")'>x</button>");
                                                                        }
									
									out.println("<div><a href='tugas.php?id="+rs2.getString("task_id")+"'>"+rs2.getString("task_name")+"</a></div><div>Deadline: <strong>"+rs2.getString("deadline")+"</strong></div><div>Status: <strong>"+status+"</strong></div><div>Ubah Status: <button id='editStatus' onclick='EditStatus(1,\""+rs2.getString("task_id")+"\")'>Selesai</button> <button id='editStatus' onclick='EditStatus(0,\""+rs2.getString("task_id")+"\")'>Belum</button></div><br/><div>Tags:<ul class='tag'>");

									query="SELECT DISTINCT tag.tag_name FROM tag, tasktag WHERE tasktag.task_id = '"+rs2.getString("task_id")+"' AND tag.tag_id = tasktag.tag_id";

                                                                        statement = connection.createStatement();
									ResultSet rs3 = statement.executeQuery(query);

									while (rs3.next()) {
									out.println ("<li>"+rs3.getString("tag_name")+"</li>");
									}
									out.println ("</ul></div></div>");
								}
								out.println ("</section>");
								iterator++;
							}
						}
			}
			catch (SQLException e) {
			}
	}
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
   