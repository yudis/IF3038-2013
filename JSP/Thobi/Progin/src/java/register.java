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

public class register extends HttpServlet {
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
                        String email = request.getParameter("email");
		   
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
		   
			query = "SELECT * FROM USER where username = '"+uname+"' OR email = '"+email+"'";

			try {
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
				if (rs.next()) {
					out.println ("false");
				} else {
                                    out.println ("true");
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
   