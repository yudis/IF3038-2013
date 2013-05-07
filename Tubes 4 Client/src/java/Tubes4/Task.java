/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tubes4;

import java.io.BufferedReader;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Devin
 */
public class Task extends HttpServlet {

    private String charset = "UTF-8";
    public String name;
    public String deadline;
    public int status;
    
    public Task(String IDTask) {
        
    }
    
    public Document loadXMLFromString(String xml) throws Exception
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        return builder.parse(is);
    }
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username, email;
        response.setContentType("text/xml");
        URLConnection connection = new URL("http://tubes4progin.ap01.aws.af.cm/Task").openConnection();
        connection.setDoOutput(true);
        ((HttpURLConnection)connection).setRequestMethod("GET");
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        OutputStream output = null;
        
        try {
            String query = String.format("username=%s&ID=%s&continue=%s",
                    URLEncoder.encode((String)request.getSession().getAttribute("bananauser"), charset),
                    URLEncoder.encode(request.getParameter("ID"), charset),
                    URLEncoder.encode(request.getParameter("continue"), charset));
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
                rsp = rsp.replace("<root>", "");
                rsp = rsp.replace("</root", "");
                out.write(rsp);
            } finally {
                if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
            }
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username, email;
        response.setContentType("text/xml");
        URLConnection connection = new URL("http://tubes4progin.ap01.aws.af.cm/Task").openConnection();
        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        OutputStream output = null;
        
        try {
            String query = String.format("ID=%s", URLEncoder.encode((String)request.getSession().getAttribute("ID"), charset));
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
                response.sendRedirect("taskdetails.jsp?id="+ doc.getElementsByTagName("id").item(0).getFirstChild().getNodeValue());
            }
            catch(Exception e) {
                out.print(e.getMessage());
            }
            finally {
                if (reader != null) try { reader.close(); } catch (IOException logOrIgnore) {}
            }
        }
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
