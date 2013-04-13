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
public class adduser extends HttpServlet {
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
                        String password = request.getParameter("pwd");
                        String fullname = request.getParameter("name");
                        String email = request.getParameter("email");
                        String path = request.getParameter("path");
                        String unparsed = request.getParameter("bday");
                        
                        String[] list = unparsed.split("-");
                        String birthday = list[2]+"-"+list[1]+"-"+list[0];
		   
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter outs = response.getWriter();
		   
			query = "INSERT INTO user VALUES ('"+username+"', '"+password+"', '"+fullname+"','"+email+"', '"+birthday+"')";

			try {
				statement = connection.createStatement();
				statement.executeUpdate(query);
                                
                                Part filePart = request.getPart("ava"); // Retrieves <input type="file" name="file">
                                String filename = getFileName(filePart);
                                byte bytes[] = new byte[1024 * 4];
                                
                                
                                
                                if (!filename.isEmpty()) {
                                    FileOutputStream output = new FileOutputStream(path + "avatar/" + username + ".jpg");
                                    try {
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
                                    } finally {
                                        output.close();
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
   