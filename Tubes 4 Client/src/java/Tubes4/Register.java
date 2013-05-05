/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Tubes4;

import java.io.BufferedInputStream;
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
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

/**
 *
 * @author Devin
 */
public class Register extends HttpServlet {

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
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            List<FileItem> items = upload.parseRequest(request);
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
                    URL server = new URL("http://tubes4progin.ap01.aws.af.cm/Register");  
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
                    pw.write("<username>" + parameters.get("username") + "</username>");
                    pw.write("<pass>" + parameters.get("pass") + "</pass>");
                    pw.write("<name>" + parameters.get("name") + "</name>");
                    pw.write("<birth>" + parameters.get("birth") + "</birth>");
                    pw.write("<email>" + parameters.get("email") + "</email>");
                    if(bytesread == 0)
                        pw.write("<avatar>avatar/0.jpg</avatar>");
                    else
                        pw.write("<avatar>avatar/" + parameters.get("username") + ".jpg</avatar>");
                    pw.write("</SOAP-ENV:Body></SOAP-ENV:Envelope>");
                    pw.close();
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String rsp;
                    int status = conn.getResponseCode();
                    if(status == 200) {
                        String resp = "";
                        while ((rsp = br.readLine()) != null)
                            resp += rsp;
                        Document doc = loadXMLFromString(resp);
                        if(doc.getElementsByTagName("register").item(0).getFirstChild().getNodeValue().equals("success")) {
                            request.getSession().setAttribute("bananauser", parameters.get("username"));
                            Cookie bananauser = new Cookie("bananauser", parameters.get("username"));
                            bananauser.setMaxAge(60 * 60 * 24 * 30);
                            response.addCookie(bananauser);
                            response.sendRedirect("home.jsp");
                        }
                    }
                }
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
