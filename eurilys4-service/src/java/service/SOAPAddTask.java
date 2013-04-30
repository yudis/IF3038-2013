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
public class SOAPAddTask extends HttpServlet {

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
        Connection conn = null;
        
        //if (pathInfo.equals("/add_task")) {
            //GET REQUEST BODY.       
            ServletInputStream i = request.getInputStream();
            int c = 0;
            String xmlrpc = "";
            while((c = i.read()) != -1 )
            { 
                xmlrpc += (char)c; 
            }
            //EXTRACT PARAMETER.
            int startTag = xmlrpc.indexOf("<tname>");
            int endTag   = xmlrpc.indexOf("</tname>");
            String task_name = xmlrpc.substring(startTag,endTag).replaceAll("<tname>","");
            startTag = xmlrpc.indexOf("<tdeadline>");
            endTag   = xmlrpc.indexOf("</tdeadline>");
            String task_deadline = xmlrpc.substring(startTag,endTag).replaceAll("<tdeadline>","");
            startTag = xmlrpc.indexOf("<tassignee>");
            endTag   = xmlrpc.indexOf("</tassignee>");
            String assigneeList = xmlrpc.substring(startTag,endTag).replaceAll("<tassignee>","");
            startTag = xmlrpc.indexOf("<ttag>");
            endTag   = xmlrpc.indexOf("</ttag>");
            String tagList = xmlrpc.substring(startTag,endTag).replaceAll("<ttag>","");
            startTag = xmlrpc.indexOf("<tcategory>");
            endTag   = xmlrpc.indexOf("</tcategory>");
            String catName = xmlrpc.substring(startTag,endTag).replaceAll("<tcategory>","");
            startTag = xmlrpc.indexOf("<tcreator>");
            endTag   = xmlrpc.indexOf("</tcreator>");
            String taskCreator = xmlrpc.substring(startTag,endTag).replaceAll("<tcreator>","");

            //CALCULATING
            String taskID = "";
            try{
                conn = connector.getConnection ();
                Statement st = conn.createStatement();
                st.executeUpdate("INSERT INTO task(task_name, task_status, task_deadline, cat_name, task_creator) VALUES ('" + task_name + "','0','" + task_deadline + "','" + catName + "','" + taskCreator + "')");


                PreparedStatement stmt = conn.prepareStatement("SELECT task_id FROM task WHERE task_name=? AND cat_name=?");
                stmt.setString(1, task_name);
                stmt.setString(2, catName);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    taskID = rs.getString("task_id");
                }

                //Insert Task Attachment
                /*Part filePart = req.getPart("attachment_file1"); // Retrieves <input type="file" name="file">
                String filename = "";
                filename = getFilename(filePart);
                String dir = "uploads/" + filename;
                byte buf[] = new byte[1024 * 4];
                if (!filename.isEmpty()) {
                    Statement st2 = conn.createStatement();
                    st2.executeUpdate("INSERT INTO `attachment` (`att_content`, `att_task_id`) VALUES ('" + filename + "','" + taskID + "')");
                    FileOutputStream output = new FileOutputStream(getServletContext().getRealPath("/") + "uploads/" + filename);
                    try {
                        InputStream input = filePart.getInputStream();
                        try {
                            while (true) {
                                int count = input.read(buf);
                                if (count == -1) {
                                    break;
                                }
                                output.write(buf, 0, count);
                            }
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                }*/

                //Insert Task Assignee
                String[] assigneArray = assigneeList.split(",");
                for (int j = 0; j < assigneArray.length; j++) {
                    st.executeUpdate("INSERT INTO task_asignee (task_id, username) VALUES ('" + taskID + "','" + assigneArray[j] + "')");
                }

                //Insert Task Tag
                String[] tagArray = tagList.split(",");
                for (int j = 0; j < tagArray.length; j++) {
                    st.executeUpdate("INSERT INTO tag(tag_name, task_id) VALUES ('" + tagArray[j] + "','" + taskID + "')");
                }
            } catch (Exception e){
            } finally {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SOAPAddTask.class.getName()).log(Level.SEVERE, null, ex);
                    System.out.println("Can not close connection - Add Task");
                }
            }
            //PREPARE OUTPUT
            String  xml = "";      
                    xml += "<?xml version=\"1.0\"?> \n";
                    xml += "<SOAP-ENV:Envelope>     \n";
                    xml += "  <SOAP-ENV:Body>       \n";
                    xml += "    <result>            \n";
                    xml += "      "+taskID+         "\n";
                    xml += "    </result>           \n";
                    xml += "  </SOAP-ENV:Body>      \n";
                    xml += "</SOAP-ENV:Envelope>    \n";
            
            //RETURN RESPONSE.    
            response.getWriter().println(xml);
        //}
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
