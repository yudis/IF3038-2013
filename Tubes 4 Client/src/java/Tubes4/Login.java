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
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Devin
 */
public class Login extends HttpServlet {

    String charset = "UTF-8";
   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String username, pass;
        response.setContentType("text/xml");
        URLConnection connection = new URL("http://tubes4progin.ap01.aws.af.cm/Login").openConnection();
        connection.setDoOutput(true); // Triggers POST.
        connection.setRequestProperty("Accept-Charset", charset);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset);
        OutputStream output = null;
        
        
        try {
            String query = null;
            if ((username = request.getParameter("user")) != null && (pass = request.getParameter("pass")) != null) {
                query = String.format("user=%s&pass=%s", URLEncoder.encode(username, charset), URLEncoder.encode(pass, charset));
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
                for (String line; (line = reader.readLine()) != null;) {
                    out.write(line);
                    if(!line.contains("notsuccess") && !line.contains("error")) {
                        request.getSession().setAttribute("bananauser", username);
                        Cookie bananauser = new Cookie("bananauser", username);
                        bananauser.setMaxAge(60 * 60 * 24 * 30);
                        response.addCookie(bananauser);
                    }
                }
            } finally {
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
