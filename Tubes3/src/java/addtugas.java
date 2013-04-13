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
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import javax.servlet.http.*;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
public class addtugas extends HttpServlet {
    private Connection connection;
    private String query = null;
    private Statement statement;
    private final static Logger LOGGER = Logger.getLogger(adduser.class.getCanonicalName());
    
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

                        String username = request.getParameter("uname");
                        String category = request.getParameter("cat");
			String taskname = request.getParameter("namatask");
                        String deadline = request.getParameter("deadline");
                        String assignee = request.getParameter("assignee");
                        String tag = request.getParameter("tag");
                        String path = request.getParameter("path");
                        int n = 4;
		   
                        String[] listassignee = assignee.split(",");
                        String[] listtags = tag.split(",");
                        
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter outs = response.getWriter();
		   
			query = "SELECT * FROM task";

			try {
				statement = connection.createStatement();
				ResultSet rs = statement.executeQuery(query);
                                
                                int maxID = 0;
                                while (rs.next()) {
                                    if (Integer.parseInt(rs.getString("task_id")) > maxID) {
                                        maxID = Integer.parseInt(rs.getString("task_id"));
                                    }
                                }
                                maxID++;
                                
                                query = "SELECT * FROM tag";
                                statement = connection.createStatement();
				ResultSet rs2 = statement.executeQuery(query);
                                
                                int maxtagID = 0;
                                while (rs2.next()) {
                                    if (Integer.parseInt(rs2.getString("tag_id")) > maxtagID) {
                                        maxtagID = Integer.parseInt(rs2.getString("tag_id"));
                                    }
                                }
                                maxtagID++;
                                
                                query = "INSERT into task VALUES('"+maxID+"','"+taskname+"',0,'"+deadline+"','"+category+"')";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
                                
                                query = "INSERT into task_creator VALUES('"+maxID+"','"+username+"')";
                                statement = connection.createStatement();
				statement.executeUpdate(query);
                                
                                int i = 0;
                                while (i < listassignee.length) {
                                    query = "INSERT into task_incharge VALUES('"+maxID+"','"+listassignee[i]+"')";
                                    statement = connection.createStatement();
                                    statement.executeUpdate(query);
                                    i++;
                                }
                                i = 0;
                                while (i < listtags.length) {
                                    query = "INSERT into tag VALUES('"+maxtagID+"','"+listtags[i]+"')";
                                    statement = connection.createStatement();
                                    statement.executeUpdate(query);
                                    
                                    query = "INSERT into tasktag VALUES('"+maxID+"','"+maxtagID+"')";
                                    statement = connection.createStatement();
                                    statement.executeUpdate(query);
                                    i++;
                                    maxtagID++;
                                }
                                int maxattID = 0;
                                for (int j=1; j<=n; j++) {
                                    String namafile = "files"+j;
                                    
                                    Part filePart = request.getPart(namafile); // Retrieves <input type="file" name="file">
                                    String filename = getFileName(filePart);
                                    byte bytes[] = new byte[1024 * 4];

                                    if (!filename.isEmpty()) {
                                        String link = path + "attachment/" + filename;
                                        FileOutputStream output = new FileOutputStream(link);
                                        try {
                                            
                                            query = "SELECT * FROM attachment";
                                            statement = connection.createStatement();
                                            ResultSet rs3 = statement.executeQuery(query);
                                            
                                            while (rs3.next()) {
                                                if (Integer.parseInt(rs3.getString("attachment_id")) > maxattID) {
                                                    maxattID = Integer.parseInt(rs3.getString("attachment_id"));
                                                }
                                            }
                                            maxattID++;
                                            
                                            InputStream input = filePart.getInputStream();
                                            try {
                                                while (true) {
                                                    int count = input.read(bytes);
                                                    if (count == -1)
                                                        break;
                                                    output.write(bytes, 0, count);
                                                }
                                            } finally {
                                                input.close();
                                            }
                                            
                                            query = "INSERT into attachment VALUES('"+maxattID+"','"+maxID+"','"+link+"')";
                                            statement = connection.createStatement();
                                            statement.executeUpdate(query);
                                            
                                        } finally {
                                            output.close();
                                        }
                                    }
                                }
                                
			}
			catch (SQLException e) {
			}
                        
                        RequestDispatcher view = request.getRequestDispatcher("/dashboard.jsp");
                        view.forward(request, response);
	}
   
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    
    private String getFileName(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        LOGGER.log(Level.INFO, "Part Header = {0}", partHeader);
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
   