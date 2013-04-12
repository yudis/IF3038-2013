/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.List;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import javax.servlet.http.Cookie;

/**
 *
 * @author Devin
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    private Tubes3Connection db;
    private Connection connection;

    public Register(){
        db = new Tubes3Connection();
        connection = db.getConnection();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            HashMap<String,String> parameters = new HashMap();
            for (FileItem item : items) {
                if (item.isFormField())
                    parameters.put(item.getFieldName(), item.getString());
                else {
                    int bytesread = 0;
                    try {
                        InputStream file = item.getInputStream();
                        bytesread = file.available();
                        if(bytesread > 0) {
                            byte[] bytes = new byte[bytesread];
                            file.read(bytes);
                            FileOutputStream out = new FileOutputStream(getServletContext().getRealPath("/") + "avatar/" + parameters.get("username") + ".jpg");
                            out.write(bytes);
                            out.close();
                        }
                        file.close();
                    }
                    catch(FileNotFoundException fnfe) {
                        System.out.println("ERROR" + fnfe.getMessage());
                    }
                    if(bytesread == 0)
                        db.nonReturnQuery(connection, "INSERT INTO pengguna VALUES('" + parameters.get("username") + "', '" + parameters.get("pass") + "', '" + parameters.get("name") + "', '" + parameters.get("birth") + "', '" + parameters.get("email") + "', 'avatar/0.jpg')");
                    else
                        db.nonReturnQuery(connection, "INSERT INTO pengguna VALUES('" + parameters.get("username") + "', '" + parameters.get("pass") + "', '" + parameters.get("name") + "', '" + parameters.get("birth") + "', '" + parameters.get("email") + "', 'avatar/" + parameters.get("username") + ".jpg')");
                }
            }
            request.getSession().setAttribute("bananauser", parameters.get("username"));
            Cookie bananauser = new Cookie("bananauser", parameters.get("username"));
            bananauser.setMaxAge(60 * 60 * 24 * 30);
            response.addCookie(bananauser);
            response.sendRedirect("home.jsp");
        }
        catch (Exception ex) {
            System.out.println("Exception is ;" + ex);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {}

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
