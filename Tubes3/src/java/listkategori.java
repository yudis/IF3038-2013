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

public class listkategori extends HttpServlet {
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
		   
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
		   
			query = "SELECT category_name, category.category_id FROM category, task, task_incharge, category_incharge WHERE (category.category_id = task.task_category AND task.task_id = task_incharge.task_id AND task_incharge.people_incharge_task = '"+uname+"') OR (category.category_id = category_incharge.category_id AND category_incharge.people_incharge = '"+uname+"') GROUP BY category_name";

                        out.println ("<ul id='Kategori' class='nav'><li><a id='all' href='dashboard.jsp' onclick='return RemoveKategoriFilter(this)'>All</a></li>");
                        
			try {
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					out.println ("<li><a id='"+rs.getString("category_id")+"' href='#' onclick='return KategoriSelected(this)'>"+rs.getString("category_name")+"</a></li>");
				}
			}
			catch (SQLException e) {
			}
                        out.println ("</ul><ul class='nav'><li><a href='#' onclick=\"popup('popUpDiv','blanket',300,600)\">Tambah Kategori...</a></li></ul>");
	}
   
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
   