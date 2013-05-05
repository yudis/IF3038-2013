/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tubes4;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Devin
 */
public class AddFile extends HttpServlet {

    String charset = "UTF-8";
    
    public Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/xml");
        URLConnection connection = new URL("http://tubes4progin.ap01.aws.af.cm/AddFile").openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        OutputStream output = null;
        
        String username = request.getParameter("user");
        String nama = request.getParameter("tugas");
        String tag = request.getParameter("tag");
        String deadline = request.getParameter("deadline");
        String kategori = request.getParameter("kategori");
        try {
            String files = "";
            for (Part part : request.getParts()) {
                //System.out.println("PART:" + part.getHeaderNames());
                InputStream is = request.getPart(part.getName()).getInputStream();
                int i = is.available();
                byte[] b = new byte[i];
                is.read(b);
                String fileName = getFileName(part);
                if (fileName != null && !fileName.equals("")) {
                    files = files + fileName + ';';
                    FileOutputStream os = new FileOutputStream(getServletContext().getRealPath("/") + "upload/" + fileName);
                    //System.out.println(getServletContext().getRealPath("/") + fileName);
                    os.write(b);
                    os.close();
                }
                is.close();
            }
            if(files.length() > 0)
                files = files.substring(0, files.length()-1);
            
            String[] assignee = request.getParameterValues("asignee");
            String assignees = "";
            int i = 0;
            if(assignee != null) {
                while (i < assignee.length && assignee[i] != null) {
                    if (!assignee[i].equals("")) {
                        assignees = assignees + assignee + ';';
                    }
                    i++;
                }
            }
            if(assignees.length() > 0)
                assignees = assignees.substring(0, assignees.length()-1);
            
            String query = null;
            if (username != null && nama != null && deadline != null && kategori != null) {
                query = String.format("user=%s&nama=%s&tag=%s&deadline=%s&kategori=%s&files=%s&assignees=%s",
                        URLEncoder.encode(username, charset),
                        URLEncoder.encode(nama, charset),
                        URLEncoder.encode(tag, charset),
                        URLEncoder.encode(deadline, charset),
                        URLEncoder.encode(kategori, charset),
                        URLEncoder.encode(files, charset),
                        URLEncoder.encode(assignees, charset));
            }
            output = connection.getOutputStream();
            if(query != null)
                output.write(query.getBytes(charset));
        } finally {
             if (output != null) try { output.close(); } catch (IOException logOrIgnore) {}
        }
        
        InputStream outresponse = connection.getInputStream();
        HttpURLConnection httpConnection = (HttpURLConnection)connection;
        int status = httpConnection.getResponseCode();
        BufferedReader reader = null;
        if(status == 200) {
            try {
                reader = new BufferedReader(new InputStreamReader(outresponse));
                String rsp = "";
                for (String line; (line = reader.readLine()) != null;)
                    rsp += line;
                Document doc = loadXMLFromString(rsp);
                if(doc.getElementsByTagName("status").item(0).getFirstChild().getNodeValue().length() <= 2)
                    response.sendRedirect("taskdetails.jsp?id=" + doc.getElementsByTagName("status").item(0).getFirstChild().getNodeValue());
                else
                    out.write(doc.getElementsByTagName("status").item(0).getFirstChild().getNodeValue());
            }
            catch(Exception e) {
                out.write(e.getMessage());
            }
            finally {
                if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
            }
        }
    }
    
    private String getFileName(Part part) {
        //System.out.println("partgetContentType:" + part.getContentType());
        //System.out.println("partgetHeaderNames:" + part.getHeaderNames());


        // String partHeader = part.getHeader("content-disposition");
        //if (part.getHeader("content-type") != null) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            //System.out.println("CD:" + cd);
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        //}
        return null;
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
