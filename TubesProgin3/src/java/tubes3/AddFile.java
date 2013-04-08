/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tubes3;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.tomcat.dbcp.pool.impl.GenericKeyedObjectPool;
import org.apache.tomcat.util.net.SecureNioChannel;

/**
 *
 * @author Raymond
 */
@WebServlet(name = "Harapan", urlPatterns = {"/Harapan"})
@MultipartConfig
public class AddFile extends HttpServlet {

    public AddFile() {
        super();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        for (Part part : request.getParts()) {
            InputStream is = request.getPart(part.getName()).getInputStream();
            int i = is.available();
            byte[] b = new byte[i];
            is.read(b);
            String fileName = getFileName(part);
            FileOutputStream os = new FileOutputStream(getServletContext().getRealPath("/")+"uploadedFile/" + fileName);
            System.out.println(getServletContext().getRealPath("/") + fileName);
            os.write(b);
            os.close();
            is.close();
        }

    }

    private String getFileName(Part part) {
        //System.out.println("partgetContentType:" + part.getContentType());
        //System.out.println("partgetHeaderNames:" + part.getHeaderNames());


        String partHeader = part.getHeader("content-disposition");
        for (String cd : part.getHeader("content-disposition").split(";")) {
            //System.out.println("CD:" + cd);
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;

    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

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
