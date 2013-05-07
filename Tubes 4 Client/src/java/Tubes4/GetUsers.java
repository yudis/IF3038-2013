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
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 *
 * @author Devin
 */
public class GetUsers extends HttpServlet {

    String charset = "UTF-8";
    
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
        String username;
        response.setContentType("text/xml");
        URLConnection connection = new URL("http://tubes4progin.ap01.aws.af.cm/GetUsers").openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        OutputStream output = null;
        
        try {
            String query = null;
            if ((username = request.getParameter("nama")) != null) {
                query = String.format("nama=%s", URLEncoder.encode(username, charset));
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
                NodeList users = doc.getElementsByTagName("username");
                if(users != null) { 
                    for(int i = 0; i < users.getLength(); i++) {
                        out.print(users.item(i).getFirstChild().getNodeValue() + '\n');
                    }
                }
            }
            catch(Exception e) {
                out.write(e.getMessage());
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
