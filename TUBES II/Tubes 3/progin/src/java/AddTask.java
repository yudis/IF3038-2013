/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author user
 */
public class AddTask extends HttpServlet {

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
    String htmlresponse;
    private DBConnector con;
    private ResultSet resultSet = null;
    public int ID;
    
    @Override
    public void init() {
        con = new DBConnector();
    }
    
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /*
             * TODO output your page here. You may use following sample code.
             */
            
            String taskname = null;
            String deadline0 = null;
            String deadline = null;
            //String deadline = "20"+deadline0.substring(0,2)+"-"+deadline0.substring(3,5)+"-"+deadline0.substring(6,8)+" 00:00:00";
            String assignee = null;
            String tag = null;
            String user = (String) request.getSession().getAttribute("userid");
            String cat = (String) request.getSession().getAttribute("kategori");
            String attach = null;
            
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
   if (!isMultipart) {
   
   } else {
    
    FileItemFactory factory = new DiskFileItemFactory();
   ServletFileUpload upload = new ServletFileUpload(factory);
   
   List<FileItem> items = null;
   try {
   items = upload.parseRequest(request);
   
    Iterator itr = items.iterator();
    while (itr.hasNext()) {
    FileItem item = (FileItem) itr.next();
    
    if (item.isFormField())
           {
              String name1 = item.getFieldName();
                  String value = item.getString();
                  if(name1.equals("namatask"))
                     {
                     taskname = value;       
                                 
                     } 
                  else
                  if(name1.equals("deadline"))
                  {
                      deadline0 = value;
                      deadline = "20"+deadline0.substring(0,2)+"-"+deadline0.substring(3,5)+"-"+deadline0.substring(6,8)+" 00:00:00";
                  }
                  else
                  if (name1.equals("assignee"))
                  {
                      assignee = value;
                  }
                  else
                  if (name1.equals("tag"))
                  {
                      tag = value;
                  }
                         }
                            else
                                {
                                    try {
                                    String itemName = item.getName();
                                    attach = itemName;
                                    File savedFile = new File(this.getServletConfig().getServletContext().getRealPath("/")
                                    +"attachment\\"+attach);
                                    item.write(savedFile);

                                    } catch (Exception e) {
                                    e.printStackTrace();
                                    }
    
                               }
       }
   } catch (FileUploadException e) {
   e.printStackTrace();
   }
   }
            con.Init();
            
            ResultSet set = con.ExecuteQuery("SELECT * FROM task");
            String lastid = "";
            int lastidnum = 0;
            while (set.next()){
                lastid = set.getString("ID");
            }
            if (!lastid.equals("")) {
                lastidnum = Integer.parseInt(lastid.substring(1));
            }
            ID = lastidnum+1;
            String nextid;
            if (ID < 10) {
                nextid = "T00"+ID;
            } else if (ID < 100) {
                nextid = "T0"+ID;
            } else {
                nextid = "T"+ID;
            }
            
            if (con.ExecuteUpdate("INSERT INTO task (ID,IDCreator,Nama,Category,Status,Deadline) VALUES ('"+nextid+"','"+user+"','"+taskname+"','"+cat+"',0,'"+deadline+"')")!=0) {
                
            }
            
            String[] tags = tag.split(",");
            for( int i = 0; i < tags.length; i++ )  
            {  
                if (con.ExecuteUpdate("INSERT INTO tags (IDTask,Tag) VALUES ('"+nextid+"','"+tags[i]+"')")!=0) {
                    
                }
            }  
            
            String[] assignees = assignee.split(",");
            for( int i = 0; i < assignees.length; i++ )  
            {  
                if(con.ExecuteUpdate("INSERT INTO assignee (IDtask,IDUser) VALUES ('"+nextid+"','"+assignees[i]+"')")!=0) {
                    
                }
            } 
            
            if (con.ExecuteUpdate("INSERT INTO attachment (IDTask,Attachment) VALUES ('"+nextid+"','"+attach+"')")!=0) {
                    
                }

            //out.print("Tugas baru telah disimpan");

            con.Close();
            response.sendRedirect("Dashboard.jsp");
        } finally {            
            out.close();
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(AddTask.class.getName()).log(Level.SEVERE, null, ex);
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
