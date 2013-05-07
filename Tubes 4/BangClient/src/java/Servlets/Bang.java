/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONObject;
import Model.*;
import java.io.OutputStream;
import java.util.HashMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import soapcategoryClient.CategorySoap_Service;
import soaptaskClient.TaskSoap_Service;


/**
 *
 * @author Jeremy Joseph Hanniel
 */
public class Bang extends HttpServlet {

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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String curCategId = (String) session.getAttribute("curCategId");

        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();

//        String domain = "http://progin4.ap01.aws.af.cm/Bang/";
        String domain = "http://localhost:8080/BangServer/Bang";

        // =========================== S H O W   C A T E G O R Y ===========================
        if (action.equalsIgnoreCase("showCategory")) {
            URL url = new URL(domain + "/showCategoryList/" + username);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("GET");
            htc.setRequestProperty("Accept", "application/json");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((htc.getInputStream())));
            StringBuilder output = new StringBuilder();
            String test;
            while ((test = br.readLine()) != null) {
                output.append(test);
            }

            JSONObject result = new JSONObject(output.toString());

            JSONObject temp;
            ArrayList<Category> categories = new ArrayList<Category>();
            for (int i = 0; i < result.length(); i++) {
                temp = result.getJSONObject("Category " + Integer.toString(i));
                categories.add(new Category(temp.getString("id_category"), temp.getString("name"), temp.getString("categ_creator")));
            }

            out.println("<div class='kategori'>");
            out.println("<div id='categoryAll' onclick='showTaskList(0);'>All</div>");
            out.println("</div>");

            for (Category categ : categories) {
                out.println("<div class='kategori'>");
                out.println("<div id='category" + categ.getId_category() + "'"
                        + "onclick='showTaskList(" + categ.getId_category() + ");'>" + categ.getName() + "</div>");
                if (username.equals(categ.getCateg_creator())) {
                    out.println("<div class='removeCategory' id='removeCateg" + categ.getId_category()
                            + "' onclick='return removeCategory(" + categ.getId_category() + ");'>x</div>");
                }
                out.println("</div>");
            }

            // =========================== A D D   C A T E G O R Y ===========================
        } else if (action.equalsIgnoreCase("addCategory")) {
            String categName = request.getParameter("name");
            String authUsers = request.getParameter("authUsers");
            
            JSONObject newcat = new JSONObject();
            newcat.put("username",username);
            newcat.put("name", categName);
            newcat.put("authUsers",authUsers);
            
            try {
                soapcategoryClient.CategorySoap_Service service = new soapcategoryClient.CategorySoap_Service();
                soapcategoryClient.CategorySoap port = service.getCategorySoapPort();
                java.lang.String result = port.hello(newcat.toString());
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
            
            // =========================== R E M O V E   C A T E G O R Y ===========================
        } else if (action.equalsIgnoreCase("removeCategory")) {
            String categId = request.getParameter("code");

            URL url = new URL(domain + "/deleteCategory/" + username + "/" + categId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("POST");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }

            // =========================== S H O W   T A S K   L I S T ===========================
        } else if (action.equalsIgnoreCase("showTaskList")) {
            String categId = request.getParameter("code");
            session.setAttribute("curCategId", categId);

            URL url = new URL(domain + "/showTaskList/" + username + "/" + categId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("GET");
            htc.setRequestProperty("Accept", "application/json");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((htc.getInputStream())));
            StringBuilder output = new StringBuilder();
            String test;
            while ((test = br.readLine()) != null) {
                output.append(test);
            }

            JSONObject result = new JSONObject(output.toString());

            JSONObject jsonTasks = result.getJSONObject("Tasks");
            JSONObject tempTask;
            JSONObject tempTag;
            JSONObject tempTaskDetail;
            Task task;
            ArrayList<String> tags;
            int k = 0;
            for (int i = 0; i < jsonTasks.length(); i++) {
                tempTask = jsonTasks.getJSONObject("Task " + Integer.toString(i));
                tempTag = tempTask.getJSONObject("Tags");
                tempTaskDetail = tempTask.getJSONObject("Task Detail");

                task = new Task(tempTaskDetail.getString("id_task"), tempTaskDetail.getString("name"), tempTaskDetail.getString("deadline"), tempTaskDetail.getString("status"), "", "");

                tags = new ArrayList<String>();
                for (int j = 0; j < tempTag.length(); j++) {
                    tags.add(tempTag.getString("Tag " + Integer.toString(j)));
                }

                out.println("<div class='listTugas'>");
                out.println("<a class='listname' id='task" + task.getId_task() + "'"
                        + "onclick='showRinci(" + task.getId_task() + ");'>" + task.getName() + "</a>");
                out.println("<div class='listdeadline'>Deadline: " + task.getDeadline() + "</div>");

                out.println("<div class='listtag'>Tag: ");
                k = 0;
                while (k < tags.size()) {
                    out.print(tags.get(k));
                    k++;
                    if (k < tags.size()) {
                        out.print(", ");
                    }
                }
                out.println("</div>");

                out.println("<div class='liststatus' id='statusTask'>");
                if (task.getStatus().equals("T")) {
                    out.println("<input type='checkbox' id='checkboxTask" + task.getId_task() + "' onclick='changeTaskStatus(" + task.getId_task() + ", this.checked);' checked>");
                } else if (task.getStatus().equals("F")) {
                    out.println("<input type='checkbox' id='checkboxTask" + task.getId_task() + "' onclick='changeTaskStatus(" + task.getId_task() + ", this.checked);'>");
                }
                out.println("Done</div>");

                if (tempTaskDetail.getBoolean("IsCreator") == true) {
                    out.println("<div class='removeTask'><input type='submit' id='removeTaskBtn" + task.getId_task() + "' onclick='removeTask(" + curCategId + "," + task.getId_task() + ");' value='Remove Task'/></div>");
                }
                out.println("</div>");
            }

            if (result.getBoolean("IsAuthUser") == true) {
                out.println("<a onclick='showBuat();' class='addTask'>+task</a>");
            }

            // =========================== S H O W   T A S K   D E T A I L ===========================
        } else if (action.equalsIgnoreCase("showTaskDetail")) {
            String taskId = request.getParameter("code");

            URL url = new URL(domain + "/showTaskDetail/" + username + "/" + taskId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("GET");
            htc.setRequestProperty("Accept", "application/json");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((htc.getInputStream())));
            StringBuilder output = new StringBuilder();
            String test;
            while ((test = br.readLine()) != null) {
                output.append(test);
            }

            JSONObject result = new JSONObject(output.toString());
            JSONObject jsonTaskDetail = result.getJSONObject("Task Detail");
            JSONObject jsonAssignees = result.getJSONObject("Assignees");
            JSONObject jsonTags = result.getJSONObject("Tags");
            JSONObject jsonAvatars = result.getJSONObject("User Avatars");

            Task task = new Task(jsonTaskDetail);
            ArrayList<String> assignees = new ArrayList<String>();
            for (int i = 0; i < jsonAssignees.length(); i++) {
                assignees.add(jsonAssignees.getString("Assignee " + Integer.toString(i)));
            }
            ArrayList<String> tags = new ArrayList<String>();
            for (int i = 0; i < jsonTags.length(); i++) {
                tags.add(jsonTags.getString("Tag " + Integer.toString(i)));
            }

            ArrayList<Attachment> attachments = new ArrayList<Attachment>();
            JSONObject jsonAttachments = result.getJSONObject("Attachments");
            JSONObject tempAttachment;
            for (int i = 0; i < jsonAttachments.length(); i++) {
                tempAttachment = jsonAttachments.getJSONObject("Attachment " + Integer.toString(i));
                attachments.add(new Attachment(tempAttachment));
            }

            ArrayList<Comment> comments = new ArrayList<Comment>();
            JSONObject jsonComments = result.getJSONObject("Comments");
            JSONObject tempComment;
            for (int i = 0; i < jsonComments.length(); i++) {
                tempComment = jsonComments.getJSONObject("Comment " + Integer.toString(i));
                comments.add(new Comment(tempComment));
            }

            JSONObject tempAvatar;
            HashMap<String, String> avatars = new HashMap<String, String>();
            for (int i = 0; i < jsonAvatars.length(); i++) {
                tempAvatar = jsonAvatars.getJSONObject("User " + Integer.toString(i));
                avatars.put(tempAvatar.getString("Username"), tempAvatar.getString("Avatar"));
            }

            out.println("<div id='taskdetail'>");
            // Name
            out.println("<div id='taskdetail_name'>" + task.getName() + "</div><br>");
            // Status
            out.println("<br><div>=======================================================</div>");
            if (task.getStatus().equalsIgnoreCase("T")) {
                out.println("<div id='taskdetail_status'><em>Status : </em>Done "
                        + "<input type='checkbox' onclick='changeTaskStatus(" + task.getId_task() + ", this.checked);' checked/>"
                        + "</div>");
            } else {
                out.println("<div id='taskdetail_status'>Status : Done "
                        + "<input type='checkbox' onclick='changeTaskStatus(" + task.getId_task() + ", this.checked);'/>"
                        + "</div>");
            }

            // Attachment
            int extensionIdx;
            String extension, filename;
            out.println("<br><div>=======================================================</div>");
            out.println("<div><em>Attachment: </em></div>");
            out.println("<div id='taskdetail_attachment'>");
            for (Attachment item : attachments) {
                extensionIdx = item.getPath().lastIndexOf('.');
                if (extensionIdx > 0) {
                    filename = item.getPath().substring(item.getPath().lastIndexOf('/') + 1);
                    extension = item.getPath().substring(extensionIdx + 1);
                    // If image
                    if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif")) {
                        out.println("<img src='" + item.getPath() + "' height=\"100\" width=\"100\"/><br>");
                        out.println("<a href='" + item.getPath() + "'>" + filename + "</a><br>");
                        // if video
                    } else if (extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("webm") || extension.equalsIgnoreCase("ogg")) {
                        out.println("<video width=\"320\" height=\"240\" controls>");
                        out.println("<source src='" + item.getPath() + "' type='video/" + extension + "'>");
                        out.println("</video><br>");
                        out.println("<a href='" + item.getPath() + "'>" + filename + "</a><br>");
                    } else {
                        out.println("<a href='" + item.getPath() + "'>" + filename + "</a><br>");
                    }
                }
            }
            out.println("</div>");

            // Deadline
            out.println("<br><div>=======================================================</div>");
            out.println("<div id='taskdetail_deadline'><em>Deadline: </em>" + task.getDeadline() + "</div>");

            // Assignee
            out.println("<br><div>=======================================================</div>");
            out.println("<div id='taskdetail_assignee'><em>Assignee: </em>");
            int i = 0;
            while (i < assignees.size()) {
                out.println("<a href='profile.jsp?userprofile=" + assignees.get(i) + "'>" + assignees.get(i) + "</a>");
                i++;
                if (i < assignees.size()) {
                    out.print("; ");
                }
            }
            out.println("</div>");

            // Comments
            out.println("<br><div>=======================================================</div>");
            out.println("<div><em>Comment: </em></div>");
            String[] timesplit;
            out.println("<div id='ctotal'>" + comments.size() + " comment(s)</div>");
            out.println("<div id='taskdetail_comment'>");
            out.println("<div id='commentlist'>");
            for (Comment item : comments) {
                out.println("<div id='comment" + item.getId_comment() + "'>");
                timesplit = item.getTimestamp().split(":");
                out.println("<img class='cavatar' src='" + avatars.get(item.getUsername()) + "' style='width:30px; height:30px;'/>");
                out.println("<div class='ctimestamp'>" + timesplit[3] + ":" + timesplit[4] + " - " + timesplit[2] + "/" + timesplit[1] + "</div>");
                out.println("<div class='ccontent'>" + item.getContent() + "</div>");
                if (username.equals(item.getUsername())) {
                    out.println("<input type='button' value='Delete' class='cdelete' onclick='delComment(" + item.getId_comment() + ");'/>");
                }
                out.println("</div>");
            }
            out.println("</div>");
            out.println("<div id='commentbox'>");
            out.println("<textarea id='cbox'></textarea></br>");
            out.println("<input type='button' value='Submit' onclick='addComment(" + taskId + ");'/>");
            out.println("</div>");
            out.println("</div>");

            // Tags
            out.println("<br><div>=======================================================</div>");
            out.println("<div id='taskdetail_tag'><em>Tag: </em>");
            i = 0;
            while (i < tags.size()) {
                out.println(tags.get(i));
                i++;
                if (i < tags.size()) {
                    out.print("; ");
                }
            }
            out.println("</div>");

            // Buttons
            if (result.getBoolean("IsEditable") == true) {
                out.println("<input type='button' value='Edit task' onclick='editTaskDetail(" + task.getId_task() + ");'/>");
            }
            out.println("<input type='button' value='Back' onclick='restore2();'/>");
            out.println("</div>");

            // =========================== E D I T   T A S K   D E T A I L ===========================
        } else if (action.equalsIgnoreCase("editTaskDetail")) {
            String taskId = request.getParameter("code");

            URL url = new URL(domain + "/editTaskDetail/" + username + "/" + taskId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("GET");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((htc.getInputStream())));
            StringBuilder output = new StringBuilder();
            String test;
            while ((test = br.readLine()) != null) {
                output.append(test);
            }

            JSONObject result = new JSONObject(output.toString());
            JSONObject jsonTags = result.getJSONObject("Tags");
            JSONObject jsonTask = result.getJSONObject("Task");
            JSONObject jsonAssignees = result.getJSONObject("Assignees");

            Task task = new Task(jsonTask);
            ArrayList<String> assignees = new ArrayList<String>();
            for (int i = 0; i < jsonAssignees.length(); i++) {
                assignees.add(jsonAssignees.getString("Assignee " + Integer.toString(i)));
            }
            ArrayList<String> tags = new ArrayList<String>();
            for (int i = 0; i < jsonTags.length(); i++) {
                tags.add(jsonTags.getString("Tag " + Integer.toString(i)));
            }

            out.println("<div id='editdetail'>");
            out.println("<form>");
            out.println("<big style='font-size: 20pt;'>" + task.getName() + "</big><br/>");
            out.println("<br/>Deadline: <input type='date' id='editDeadline' name='editDeadline' value='" + task.getDeadline() + "'><br/>");
            out.println("<br/><div class='assignee'>Assignee: <input type='text' id='editAssignee'"
                    + "name='editAssignee' value='");

            int i = 0;
            while (i < assignees.size()) {
                out.print(assignees.get(i));
                i++;
                if (i < assignees.size()) {
                    out.print(",");
                }
            }

            out.print("' onkeyup=\"multiAutocomp(this, 'getAllUser.jsp');\" onfocusin='multiAutocompClearAll(); autocomplete='off''></div><br/>");

            out.println("<div class='tag'>Tag: <input type='text' id='editTag' name='editTag' value='");

            i = 0;
            while (i < tags.size()) {
                out.print(tags.get(i));
                i++;
                if (i < tags.size()) {
                    out.print(",");
                }
            }

            out.print("' onkeyup=\"multiAutocomp(this, 'getAllTag.jsp');\" onfocusin='multiAutocompClearAll();' autocomplete='off'></div><br/>");

            out.println("</form><br/>");

            if (result.getBoolean("creator")) {
                out.println("<input type='button' id='editremove' onclick='removeTask(" + curCategId + ", " + task.getId_task() + ");' class='button' value='Remove Task'/><br>");
            } else if (result.getBoolean("assignee")) {
                out.println("<input type='button' id='editremove' onclick='removeReference(" + curCategId + ", " + task.getId_task() + ");' class='button' value='Remove me from this task'/><br>");
            }

            out.println("<input type='button' id='editsave' onclick='saveTaskDetail(" + task.getId_task() + ");' class='button' value='Save'/>");
            out.println("<input type='button' id='editback' onclick='restore4();' class='button' value='Back'/>");
            out.println("</div>");


            // =========================== C H A N G E   T A S K   S T A T U S ===========================
        } else if (action.equalsIgnoreCase("changeTaskStatus")) {
            String taskId = request.getParameter("code");
            String newStatus = request.getParameter("chkYesNo");

            URL url = new URL(domain + "/changeTaskStatus/" + taskId + "/" + newStatus);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("GET");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }
            
            BufferedReader br = new BufferedReader(new InputStreamReader((htc.getInputStream())));
            StringBuilder output = new StringBuilder();
            String test;
            while ((test = br.readLine()) != null) {
                output.append(test);
            }

            out.println("<div>");
            if (output.equals("0")) {
                out.println("<input type='checkbox' id='checkboxTask" + taskId + "' "
                        + "onclick='changeTaskStatus(" + taskId + ", this.checked);' checked>");
            } else if (output.equals("1")) {
                out.println("<input type='checkbox' id='checkboxTask" + taskId + "' "
                        + "onclick='changeTaskStatus(" + taskId + ", this.checked);'>");
            }
            out.println("Done</div>");

            // =========================== S A V E   T A S K   D E T A I L ===========================
        } else if (action.equalsIgnoreCase("saveTaskDetail")) {
            String taskId = request.getParameter("code");
            String deadline = request.getParameter("newDeadline");
            String[] assignees = request.getParameter("newAssignees").split(",");
            String[] tags = request.getParameter("newTags").split(",");

            URL url = new URL(domain + "/saveTaskDetail/" + taskId + "/" + curCategId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setDoOutput(true);
            htc.setRequestMethod("POST");
            htc.setRequestProperty("Content-Type", "application/json");
            
            JSONObject a = new JSONObject();
            JSONObject newAssignees = new JSONObject();
            JSONObject newTags = new JSONObject();
            
            for (int i = 0; i < assignees.length; i++) {
                newAssignees.put("Assignee " + Integer.toString(i), assignees[i]);
            }
            for (int i = 0; i < tags.length; i++) {
                newTags.put("Tag " + Integer.toString(i), tags[i]);
            }
            
            a.put("Deadline", deadline);
            a.put("Assignees", newAssignees);
            a.put("Tags", newTags);
            
            String updateData = a.toString();
            
            OutputStream os = htc.getOutputStream();
            os.write(updateData.getBytes());
            os.flush();

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }
            
            
            // =========================== A D D   T A S K ===========================
        } else if (action.equalsIgnoreCase("addTask")) {
            System.out.println("Add Task Bang Client");
            
            response.sendRedirect("dashboard.jsp");
            
            // =========================== R E M O V E   T A S K ===========================
        } else if (action.equalsIgnoreCase("removeTask")) {
            String taskId = request.getParameter("code");

            URL url = new URL(domain + "/deleteTask/" + username + "/" + curCategId + "/" + taskId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("POST");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }
            // =========================== R E M O V E   R E F E R E N C E ===========================
        } else if (action.equalsIgnoreCase("removeReference")) {
            String taskId = request.getParameter("code");

            URL url = new URL(domain + "/removeReference/" + username + "/" + curCategId + "/" + taskId);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("POST");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }
            // =========================== A D D   C O M M E N T ===========================
        } else if (action.equalsIgnoreCase("addComment")) {
            String idtask = request.getParameter("it");
            String content = request.getParameter("c");
            DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
            Date date = new Date();
            String timestamp = dateFormat.format(date).toString();
            String avatar = (String) session.getAttribute("avatar");
            
            JSONObject newcom = new JSONObject();
            newcom.put("username",username);
            newcom.put("idtask", idtask);
            newcom.put("content",content);
            newcom.put("time",timestamp);
            newcom.put("avatar",avatar);
            
            try {
                soapcommentClient.CommentSoap_Service service = new soapcommentClient.CommentSoap_Service();
                soapcommentClient.CommentSoap port = service.getCommentSoapPort();
                java.lang.String result = port.hello(newcom.toString());
                out.println(result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }
            
        } else if (action.equalsIgnoreCase("delComment")) {
            String idComment = request.getParameter("ic");
            
            URL url = new URL(domain + "/deleteComment/" + idComment);
            HttpURLConnection htc = (HttpURLConnection) url.openConnection();
            htc.setRequestMethod("POST");

            if (htc.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + htc.getResponseCode());
            }
        } else {
            System.out.println("Unknown command");
        }

        out.close();
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
