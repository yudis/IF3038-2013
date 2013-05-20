package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.*;

/* 
 * TaskService memiliki URL : baseURL/task
 * TaskService merupakan sebuah service class yang menangani : 
 *      - task_detail           - done
 *      - update_task           - done
 *      - delete_comment        - done
 *      - delete_tag            - done
 *      - delete_assignee       - done
 *      - get_category_task (get list of task based on category name) - done
 *      - finish_task           - done
 *      - delete_task           - done
 */
public class TaskService extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();                
        String pathInfo = request.getPathInfo();
        dbConnection connector = new dbConnection();
        Connection conn = null;
        
        /* 
         * pathInfo             : baseURL/task/task_detail
         * requestParameter     : task_id=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        if (pathInfo.equals("/task_detail")) {
            try {                
                String task_id = request.getParameter("task_id");                
                conn = connector.getConnection ();
                PreparedStatement stmt = conn.prepareStatement("");
                ResultSet rs = null;
                
                //Get Tag List
                List<String> tagList = new ArrayList<String>();
                stmt = conn.prepareStatement("SELECT * from tag WHERE task_id=?");
                stmt.setString(1, task_id);                
                rs = stmt.executeQuery();
                rs.beforeFirst();            
                while (rs.next()) {
                    tagList.add(rs.getString("tag_name"));
                }
                
                //Get Comment List
                List<String> commentContent = new ArrayList<String>();
                List<String> commentID = new ArrayList<String>();
                List<String> commentCreator = new ArrayList<String>();
                List<String> commentTimestamp = new ArrayList<String>();
                stmt = conn.prepareStatement("SELECT comment_id, comment_content, comment_creator, comment_timestamp from comment WHERE task_id=?");
                stmt.setString(1, task_id);
                rs = stmt.executeQuery();
                rs.beforeFirst();            
                while (rs.next()) {                    
                    commentID.add(rs.getString("comment_id"));
                    commentContent.add(rs.getString("comment_content"));
                    commentCreator.add(rs.getString("comment_creator"));
                    commentTimestamp.add(rs.getString("comment_timestamp"));
                }
                
                //Get Assignee List 
                List<String> assignee = new ArrayList<String>();
                stmt = conn.prepareStatement("SELECT username from task_asignee WHERE task_id=?");
                stmt.setString(1, task_id);
                rs = stmt.executeQuery();
                rs.beforeFirst();
                while (rs.next()) {
                    assignee.add(rs.getString("username"));
                }
                
                //Get task detail
                JSONObject taskObject = new JSONObject();
                stmt = conn.prepareStatement("SELECT * FROM task WHERE task_id=?");
                stmt.setString(1, task_id);
                rs = stmt.executeQuery();
                rs.beforeFirst();
                while (rs.next()) { 
                    String task_name = rs.getString(2);
                    String task_status = rs.getString(3);
                    String task_deadline = rs.getString(4);
                    String task_category = rs.getString(5);
                    String task_creator = rs.getString(6);

                    taskObject.put("task_id", task_id);
                    taskObject.put("task_name", task_name);
                    taskObject.put("task_deadline", task_deadline);
                    taskObject.put("task_status", task_status);
                    taskObject.put("task_category", task_category);
                    taskObject.put("task_creator", task_creator);
                    taskObject.put("tag_list", tagList);
                    taskObject.put("comment_content", commentContent);
                    taskObject.put("comment_id", commentID);
                    taskObject.put("comment_creator", commentCreator);
                    taskObject.put("comment_timestamp", commentTimestamp);
                    taskObject.put("task_assignee", assignee);
                }
                out.println(taskObject);
            }      
            catch (SQLException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/update_task
         * requestParameter     : edit_task_id=&edit_task_deadline=&edit_task_assignee=&edit_task_tag=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/update_task")) {
            try {
                String taskID = request.getParameter("edit_task_id");
                String deadline = request.getParameter("edit_task_deadline");
                String assigneeList = request.getParameter("edit_task_assignee");
                String tagList = request.getParameter("edit_task_tag");

                conn = connector.getConnection ();
                Statement stmt = conn.createStatement();

                //Update task_asignee
                String[] assigneArray = assigneeList.split(",");
                for (int i = 0; i < assigneArray.length; i++) {
                    if (!("".equals(assigneArray[i]))) {
                        stmt.executeUpdate("INSERT INTO task_asignee (task_id, username) VALUES ('" + taskID + "','" + assigneArray[i] + "')");
                    }
                }
                
                //Update tag
                String[] tagArray = tagList.split(",");
                for (int i = 0; i < tagArray.length; i++) {
                    if (!("".equals(tagArray[i]))) {
                        stmt.executeUpdate("INSERT INTO tag (tag_name, task_id) VALUES ('" + tagArray[i] + "','" + taskID + "')");
                    }
                }
                
                //Update task
                stmt.executeUpdate("UPDATE task SET task_deadline='" + deadline + "' WHERE task_id='" + taskID + "'"); 
                
                out.println("task updated");
                
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * HTTP Method          : GET
         * pathInfo             : baseURL/task/delete_comment
         * requestParameter     : comment_id=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/delete_comment")) {
            try {
                String comment_id = request.getParameter("comment_id");                
                conn = connector.getConnection ();
                ResultSet rs = null;
                PreparedStatement st;
                st = conn.prepareStatement("DELETE FROM comment WHERE comment_id=?");
                st.setString(1, comment_id);
                int row = st.executeUpdate();
                out.println(row); //row = 1 berhasil, row = 0 gagal
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/delete_tag
         * requestParameter     : tag_name=&task_id=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/delete_tag")) {
            try {
                String tag_name = request.getParameter("tag_name");
                String task_id = request.getParameter("task_id");
                conn = connector.getConnection ();
                ResultSet rs = null;
                PreparedStatement st;
                st = conn.prepareStatement("DELETE FROM tag WHERE tag_name=? AND task_id=?");
                st.setString(1, tag_name);
                st.setString(2, task_id);
                st.executeUpdate();
                out.println("Tag " + tag_name + " from Task " + task_id + " has been deleted");                
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/delete_assignee
         * requestParameter     : task_id=&username=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/delete_assignee")) {
            //task_asignee
            try {
                String task_id = request.getParameter("task_id");
                String username = request.getParameter("username");
                conn = connector.getConnection ();
                ResultSet rs = null;
                PreparedStatement st;
                st = conn.prepareStatement("DELETE FROM task_asignee WHERE task_id=? AND username=?");
                st.setString(1, task_id);
                st.setString(2, username);
                st.executeUpdate();
                out.println("Assignee " + username + " from Task " + task_id + " has been deleted");                
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/get_category_task
         * requestParameter     : category_name=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/get_category_task")) {
            try {
                String category_name = request.getParameter("category_name");
                JSONArray taskArray = new JSONArray();
                List<String> tagList = new ArrayList<String>();
                conn = connector.getConnection ();
                
                //Get task detail
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM task WHERE cat_name=?");
                stmt.setString(1, category_name);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();

                String task_id = "";
                String task_name = "";
                String task_status = "";
                String task_deadline = "";
                String task_creator = "";

                while (rs.next()) { 
                    JSONObject taskObject = new JSONObject();

                    task_id = rs.getString(1);
                    task_name = rs.getString(2);
                    task_status = rs.getString(3);
                    task_deadline = rs.getString(4);
                    task_creator = rs.getString(6);
                    
                    //Get Tag List                    
                    stmt = conn.prepareStatement("SELECT * from tag WHERE task_id=?");
                    stmt.setString(1, task_id);                
                    ResultSet rs_tag = stmt.executeQuery();
                    rs_tag.beforeFirst();
                    tagList.clear();
                    while (rs_tag.next()) {
                        tagList.add(rs_tag.getString("tag_name"));
                    }
                    taskObject.put("task_id", task_id);
                    taskObject.put("task_name", task_name);
                    taskObject.put("task_status", task_status);
                    taskObject.put("task_deadline", task_deadline);
                    taskObject.put("task_category", category_name);
                    taskObject.put("task_creator", task_creator);
                    taskObject.put("tag_list", tagList);
                    
                    taskArray.put(taskObject);
                }
                out.println(taskArray);
            }       
            catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/all_task
         * requestParameter     : username=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/all_task")) {
            try {                
                String username = request.getParameter("username");
                JSONArray taskArray = new JSONArray();
                List<String> tagList = new ArrayList<String>();
                conn = connector.getConnection ();
                
                //Get task detail
                PreparedStatement stmt = conn.prepareStatement("SELECT DISTINCT task.* FROM task INNER JOIN task_asignee WHERE task.task_creator=? OR task_asignee.username=?;");
                stmt.setString(1, username);
                stmt.setString(2, username);
                ResultSet rs = stmt.executeQuery();
                rs.beforeFirst();
                
                String task_id = "";
                String task_name = "";
                String task_status = "";
                String task_deadline = "";
                String task_category = "";
                String task_creator = "";

                while (rs.next()) { 
                    JSONObject taskObject = new JSONObject();
                    task_id = rs.getString(1);
                    task_name = rs.getString(2);
                    task_status = rs.getString(3);
                    task_deadline = rs.getString(4);
                    task_category = rs.getString(5);
                    task_creator = rs.getString(6);
                    
                    //Get Tag List                    
                    stmt = conn.prepareStatement("SELECT * from tag WHERE task_id=?");
                    stmt.setString(1, task_id);                
                    ResultSet rs_tag = stmt.executeQuery();
                    rs_tag.beforeFirst();
                    tagList.clear();
                    while (rs_tag.next()) {
                        tagList.add(rs_tag.getString("tag_name"));
                    }
                    
                    taskObject.put("task_id", task_id);
                    taskObject.put("task_name", task_name);
                    taskObject.put("task_status", task_status);
                    taskObject.put("task_deadline", task_deadline);
                    taskObject.put("task_category", task_category);
                    taskObject.put("task_creator", task_creator);
                    taskObject.put("tag_list", tagList);
                    
                    taskArray.put(taskObject);
                }
                out.println(taskArray);
            }       
            catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/finish_task
         * requestParameter     : task_id=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/finish_task")) {
            try {
                conn = connector.getConnection ();
                Statement stmt = conn.createStatement();
                String taskID = request.getParameter("task_id");
                //Update task status
                int updated_row = stmt.executeUpdate("UPDATE task SET task_status=1 WHERE task_id='" + taskID + "'");
                out.println(updated_row); //return 1, berarti berhasil. 0 berarti gagal
            }       
            catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        /* 
         * pathInfo             : baseURL/task/delete_task
         * requestParameter     : task_id=
         * Notes                : baseURL adalah localhost:8084/eurilys4 ATAU http://eurilys.ap01.aws.af.cm/ 
         */
        else if (pathInfo.equals("/delete_task")) {
            try {
                String task_id = request.getParameter("task_id");
                conn = connector.getConnection ();
                PreparedStatement st = conn.prepareStatement("DELETE FROM task WHERE task_id=?");
                st.setString(1, task_id);
                int row = st.executeUpdate();
                out.println(row); //row = 1 berhasil, row = 0 gagal
            } catch (SQLException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(TaskService.class.getName()).log(Level.SEVERE, null, ex);
            }
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
