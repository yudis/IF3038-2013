/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author Compaq
 */
public class SOAPAddCategory extends HttpServlet {

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        dbConnection connector = new dbConnection();
        Connection conn_category = null;
        ResultSet rs_category = null;
            
        
            //GET REQUEST BODY.       
            ServletInputStream i = request.getInputStream();
            int c = 0;
            String xmlrpc = "";
            while((c = i.read()) != -1 )
            { 
                xmlrpc += (char)c; 
            }
            
            //EXTRACT PARAMETER.
            int startTag = xmlrpc.indexOf("<cname>");
            int endTag   = xmlrpc.indexOf("</cname>");
            String categoryName = xmlrpc.substring(startTag,endTag).replaceAll("<cname>","");
            startTag = xmlrpc.indexOf("<cassignee>");
            endTag   = xmlrpc.indexOf("</cassignee>");
            String categoryAssigne = xmlrpc.substring(startTag,endTag).replaceAll("<cassignee>","");
            startTag = xmlrpc.indexOf("<ccreator>");
            endTag   = xmlrpc.indexOf("</ccreator>");
            String categoryCreator = xmlrpc.substring(startTag,endTag).replaceAll("<ccreator>","");
            
            //CALCULATING
            String categoryID = "";
            try{
                conn_category = connector.getConnection();
                Statement st = conn_category.createStatement();
                st.executeUpdate("INSERT INTO category (cat_name, cat_creator) VALUES ('" + categoryName + "','" + categoryCreator + "')");
                
                PreparedStatement stmt_category = conn_category.prepareStatement("SELECT cat_id FROM category WHERE cat_name=?");
                stmt_category.setString(1, categoryName);
                rs_category = stmt_category.executeQuery();
                rs_category.beforeFirst();
                while (rs_category.next()) {
                    categoryID = rs_category.getString("cat_id");
                }
                String[] assigneArray = categoryAssigne.split(",");
                for (int j = 0; j < assigneArray.length; j++) {
                    st.executeUpdate("INSERT INTO cat_asignee (cat_id, username) VALUES ('" + categoryID + "','" + assigneArray[j] + "')");
                }
            } catch (Exception e){
            } finally {
                try {
                    conn_category.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SOAPAddCategory.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection");
                }
            }
            //PREPARE OUTPUT
            String  xml = "";      
                    xml += "<?xml version=\"1.0\"?> \n";
                    xml += "<SOAP-ENV:Envelope>     \n";
                    xml += "  <SOAP-ENV:Body>       \n";
                    xml += "    <result>            \n";
                    xml += "      "+categoryID+    "\n";
                    xml += "    </result>           \n";
                    xml += "  </SOAP-ENV:Body>      \n";
                    xml += "</SOAP-ENV:Envelope>    \n";
            
            //RETURN RESPONSE.    
            response.getWriter().println(xml);
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
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
