/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tubes4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Devin
 */
public class AddCategory extends HttpServlet {

    private String charset = "UTF-8";
    
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
        PrintWriter output = response.getWriter();
        try {
            URL server = new URL("http://tubes4progin.ap01.aws.af.cm/AddCategory");  
            URLConnection uc = server.openConnection();
            HttpURLConnection conn = (HttpURLConnection)uc;
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-type", "application/soap+xml");
            PrintWriter pw = new PrintWriter(conn.getOutputStream());
            pw.write("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");

            pw.write("<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">");
            pw.write("<SOAP-ENV:Header/><SOAP-ENV:Body>");
            pw.write("<judul>" + request.getParameter("name") + "</judul>");
            pw.write("<maker>" + (String)request.getSession().getAttribute("bananauser") + "</maker>");
            String userlist[] = request.getParameter("users").replaceAll("\\s", "").split(",");
            for(int i = 0; i < userlist.length; i++)
                pw.write("<users>" + userlist[i] + "</users>");
            pw.write("</SOAP-ENV:Body></SOAP-ENV:Envelope>");
            pw.close();
            int status = conn.getResponseCode();
            if(status == 200) {
                InputStream outresponse = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(outresponse));
                for (String line; (line = reader.readLine()) != null;)
                    output.write(line);
            }
            
        }
        catch (Exception ex) {
            System.out.println("Exception is " + ex.getMessage());
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
