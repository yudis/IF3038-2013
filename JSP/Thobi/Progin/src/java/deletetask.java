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

public class deletetask extends HttpServlet {
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

			String taskid = request.getParameter("t");
		   
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
		   
			query = "DELETE FROM comment WHERE commented_task = '"+taskid+"'";

			try {
				statement = connection.createStatement();
				statement.executeUpdate(query);
                                query = "DELETE FROM comment WHERE commented_task = '"+taskid+"'";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
                                query = "DELETE FROM comment WHERE commented_task = '"+taskid+"'";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
                                query = "DELETE FROM comment WHERE commented_task = '"+taskid+"'";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
                                query = "DELETE FROM comment WHERE commented_task = '"+taskid+"'";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
                                query = "DELETE FROM comment WHERE commented_task = '"+taskid+"'";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
			}
			catch (SQLException e) {
			}
	}
   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
   