/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DBOperation.*;
import Model.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dell
 */
public class BangServlet extends HttpServlet {

    AttachmentOp attachmentOp;
    CategoryOp categoryOp;
    CaurelationOp caurelationOp;
    CommentOp commentOp;
    JoinOp joinOp;
    TagOp tagOp;
    TarelationOp tarelationOp;
    TaskOp taskOp;
    TtrelationOp ttrelationOp;
    UcrelationOp ucrelationOp;
    UserOp userOp;
    UtrelationOp utrelationOp;

    public BangServlet() {
        super();
        attachmentOp = new AttachmentOp();
        categoryOp = new CategoryOp();
        caurelationOp = new CaurelationOp();
        commentOp = new CommentOp();
        joinOp = new JoinOp();
        tagOp = new TagOp();
        tarelationOp = new TarelationOp();
        taskOp = new TaskOp();
        ttrelationOp = new TtrelationOp();
        ucrelationOp = new UcrelationOp();
        userOp = new UserOp();
        utrelationOp = new UtrelationOp();
    }

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
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String curCategId = (String) session.getAttribute("curCategId");

        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();

        System.out.println("Action = " + action);
        
        if (action.equalsIgnoreCase("showCategory")) {
            ArrayList<Category> categories = new ArrayList<Category>(joinOp.GetCategByUsername(username));

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

        } else if (action.equalsIgnoreCase("addCategory")) {
            String categName = request.getParameter("name");
            String[] authUsers = request.getParameter("authUsers").split(",");

            categoryOp.InsertNewCategory(new Category("", categName, username));

            String newCategId = categoryOp.FetchIdByName(categName);
            caurelationOp.InsertCaurelation(new Caurelation("", newCategId, username));
            ucrelationOp.InsertUcrelation(new Ucrelation("", username, newCategId));

            if (authUsers.length != 0) {
                for (String authUser : authUsers) {
                    if (userOp.ListAllUsernames().contains(authUser)) {
                        ucrelationOp.InsertUcrelation(new Ucrelation("", authUser.trim(), newCategId));
                        caurelationOp.InsertCaurelation(new Caurelation("", newCategId, authUser));
                    }
                }
            }

        } else if (action.equalsIgnoreCase("removeCategory")) {
            String categId = request.getParameter("code");
            ArrayList<String> taskIds = new ArrayList<String>(taskOp.FetchIdsByCategId(categId));

            for (String taskId : taskIds) {
                ttrelationOp.DeleteByTaskId(taskId);
                utrelationOp.DeleteByTaskId(taskId);
                tarelationOp.DeleteTarelationByTaskId(taskId);
                commentOp.DeleteCommentByTaskId(taskId);
                taskOp.DeleteById(taskId);
            }

            ucrelationOp.DeleteByCategId(categId, username);
            caurelationOp.DeleteCaurelationByCategId(categId);
            categoryOp.DeleteCategoryById(categId);

        } else if (action.equalsIgnoreCase("showTaskList")) {
            String categId = request.getParameter("code");

            session.setAttribute("curCategId", categId);

            ArrayList<Task> tasks = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(username, categId));
            ArrayList<String> tags;
            int i = 0;
            for (Task task : tasks) {
                i = 0;
                out.println("<div class='listTugas'>");
                out.println("<a class='listname' id='task" + task.getId_task() + "'"
                        + "onclick='showRinci(" + task.getId_task() + ");'>" + task.getName() + "</a>");
                out.println("<div class='listdeadline'>Deadline: " + task.getDeadline() + "</div>");

                tags = new ArrayList<String>(joinOp.GetTagNamesByTaskId(task.getId_task()));
                out.println("<div class='listtag'>Tag: ");

                while (i < tags.size()) {
                    out.print(tags.get(i));
                    i++;
                    if (i < tags.size()) {
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

                if (username.equals(taskOp.FetchCreatorById(task.getId_task()))) {
                    out.println("<div class='removeTask'><input type='submit' id='removeTaskBtn" + task.getId_task() + "' onclick='removeTask(" + curCategId + "," + task.getId_task() + ");' value='Remove Task'/></div>");
                }
                out.println("</div>");

            }

            if (caurelationOp.FetchAuthUsersByCategId(categId).contains(username)) {
                out.println("<a onclick='showBuat();' class='addTask'>+task</a>");
            }
        } else if (action.equalsIgnoreCase("showTaskDetail")) {
            String taskId = request.getParameter("code");
            Task task = taskOp.SelectById(taskId);
            
            out.println("<div id='taskdetail'>");
                //NAME
                out.println("<div id='taskdetail_name'>"+task.getName()+"</div><br>");
                //STATUS
                out.println("<br><div>=======================================================</div>");
                if (task.getStatus().equalsIgnoreCase("T")){
                    out.println("<div id='taskdetail_status'><em>Status : </em>Done "
                    +"<input type='checkbox' onclick='changeTaskStatus(" + task.getId_task() + ", this.checked);' checked/>"
                    +"</div>");
                } else {
                    out.println("<div id='taskdetail_status'>Status : Done "
                    +"<input type='checkbox' onclick='changeTaskStatus(" + task.getId_task() + ", this.checked);'/>"
                    +"</div>");
                }
                //ATTACHMENT
                ArrayList<Attachment> attachments = joinOp.GetAttachmentFromId_task(task.getId_task());
                int extensionIdx;
                String extension, filename;
                out.println("<br><div>=======================================================</div>");
                out.println("<div><em>Attachment: </em></div>");
                out.println("<div id='taskdetail_attachment'>");
                for (Attachment item : attachments) {
                    extensionIdx = item.getPath().lastIndexOf('.');
                    if (extensionIdx > 0){
                        filename = item.getPath().substring(item.getPath().lastIndexOf('/')+1);
                        extension = item.getPath().substring(extensionIdx+1);
                        System.out.println(item.getPath()+" has extension "+extension);
                        //jika gambar
                        if (extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif")){
                            System.out.println(filename);
                            out.println("<img src='"+item.getPath()+"' height=\"100\" width=\"100\"/><br>");
                            out.println("<a href='"+item.getPath()+"'>"+filename+"</a><br>");
                        //jika video
                        } else if (extension.equalsIgnoreCase("mp4") || extension.equalsIgnoreCase("webm") || extension.equalsIgnoreCase("ogg")){
                            out.println("<video width=\"320\" height=\"240\" controls>");
                                out.println("<source src='"+item.getPath()+"' type='video/"+extension+"'>");
                            out.println("</video><br>");
                            out.println("<a href='"+item.getPath()+"'>"+filename+"</a><br>");
                        } else {
                            out.println("<a href='"+item.getPath()+"'>"+filename+"</a><br>");
                        }
                    }
                }
                out.println("</div>");
                //DEADLINE
                out.println("<br><div>=======================================================</div>");
                out.println("<div id='taskdetail_deadline'><em>Deadline: </em>"+task.getDeadline()+"</div>");
                //ASSIGNEE
                out.println("<br><div>=======================================================</div>");
                ArrayList<String> assignees = utrelationOp.FetchAssigneeByTaskId(task.getId_task());
                out.println("<div id='taskdetail_assignee'><em>Assignee: </em>");
                int i = 0;
                while (i < assignees.size()) {
                    out.println("<a href='profile.jsp?userprofile="+assignees.get(i)+"'>"+assignees.get(i)+"</a>");
                    i++;
                    if (i < assignees.size()){
                        out.print("; ");
                    }
                }
                out.println("</div>");
                //KOMENTAR
                out.println("<br><div>=======================================================</div>");
                out.println("<div><em>Comment: </em></div>");
                User user;
                String[] timesplit;
                ArrayList<Comment> comments = commentOp.FetchCommentByTaskId(task.getId_task());
                out.println("<div id='ctotal'>"+comments.size()+" comment(s)</div>");
                out.println("<div id='taskdetail_comment'>");
                    out.println("<div id='commentlist'>");
                        for (Comment item : comments) {
                            out.println("<div id='comment"+item.getId_comment()+"'>");
                                user = userOp.SelectUserInfoByUsername(item.getUsername());
                                timesplit = item.getTimestamp().split(":");
                                System.out.println("LALALALALA"+timesplit.length);
                                out.println("<img class='cavatar' src='"+user.getAvatar()+"' style='width:30px; height:30px;'/>");
                                out.println("<div class='ctimestamp'>"+timesplit[3]+":"+timesplit[4]+" - "+timesplit[2]+"/"+timesplit[1]+"</div>");
                                out.println("<div class='ccontent'>"+item.getContent()+"</div>");
                                if (username.equals(user.getUsername())){
                                    out.println("<input type='button' value='Delete' class='cdelete' onclick='delComment("+item.getId_comment()+");'/>");
                                }
                            out.println("</div>");
                        }
                    out.println("</div>");
                    out.println("<div id='commentbox'>");
                        out.println("<textarea id='cbox'></textarea></br>");
                        out.println("<input type='button' value='Submit' onclick='addComment("+taskId+");'/>");
                    out.println("</div>");
                out.println("</div>");
                //TAG
                out.println("<br><div>=======================================================</div>");
                ArrayList<Tag> tags = joinOp.GetTagFromId_task(task.getId_task());
                out.println("<div id='taskdetail_tag'><em>Tag: </em>");
                i = 0;
                while (i < tags.size()) {
                    out.println(tags.get(i).getName());
                    i++;
                    if (i < tags.size()){
                        out.print("; ");
                    }
                }
                out.println("</div>");
                if (utrelationOp.IsTaskEditable(username, taskId)){
                    out.println("<input type='button' value='Edit task' onclick='editTaskDetail(" + task.getId_task() + ");'/>");
                }
                out.println("<input type='button' value='Back' onclick='restore2();'/>");
            out.println("</div>");
        } else if (action.equalsIgnoreCase("editTaskDetail")) {
            String taskId = request.getParameter("code");
            Task task = taskOp.SelectById(taskId);
            out.println("<div id='editdetail'>");
            out.println("<form>");
            out.println("<big style='font-size: 20pt;'>" + task.getName() + "</big><br/>");
            out.println("<br/>Deadline: <input type='date' id='editDeadline' name='editDeadline' value='" + task.getDeadline() + "'><br/>");
            out.println("<br/><div class='assignee'>Assignee: <input type='text' id='editAssignee'"
                    + "name='editAssignee' value='");

            ArrayList<String> authUsers = new ArrayList<String>(utrelationOp.FetchAssigneeByTaskId(taskId));
            int i = 0;
            while (i < authUsers.size()) {
                out.print(authUsers.get(i));
                i++;
                if (i < authUsers.size()) {
                    out.print(",");
                }
            }

            out.print("' onkeyup=\"multiAutocomp(this, 'getAllUser.jsp');\" onfocusin='multiAutocompClearAll();'></div><br/>");

            out.println("<div class='tag'>Tag: <input type='text' id='editTag' name='editTag' value='");

            ArrayList<String> tags = new ArrayList<String>(joinOp.GetTagNamesByTaskId(task.getId_task()));
            i = 0;
            while (i < tags.size()) {
                out.print(tags.get(i));
                i++;
                if (i < tags.size()) {
                    out.print(",");
                }
            }

            out.print("' onkeyup=\"multiAutocomp(this, 'getAllTag.jsp');\" onfocusin='multiAutocompClearAll();'></div><br/>");

            out.println("</form><br/>");

            if (username.equals(taskOp.FetchCreatorById(task.getId_task()))) {
                out.println("<input type='button' id='editremove' onclick='removeTask(" + curCategId + ", " + task.getId_task() + ");' class='button' value='Remove Task'/><br>");
            } else if (utrelationOp.FetchAssigneeByTaskId(task.getId_task()).contains(username)) {
                out.println("<input type='button' id='editremove' onclick='removeReference(" + curCategId + ", " + task.getId_task() + ");' class='button' value='Remove me from this task'/><br>");
            }
            out.println("<input type='button' id='editsave' onclick='saveTaskDetail(" + task.getId_task() + ");' class='button' value='Save'/>");
            out.println("<input type='button' id='editback' onclick='restore4();' class='button' value='Back'/>");
            out.println("</div>");
        } else if (action.equalsIgnoreCase("changeTaskStatus")) {
            String taskId = request.getParameter("code");
            String newStatus = request.getParameter("chkYesNo");

            if (newStatus.equals("0")) {
                taskOp.UpdateStatusWithId("F", taskId);
            } else if (newStatus.equals("1")) {
                taskOp.UpdateStatusWithId("T", taskId);
            }

            out.println("<div>");
            if (newStatus.equals("0")) {
                out.println("<input type='checkbox' id='checkboxTask" + taskId + "' "
                        + "onclick='changeTaskStatus(" + taskId + ", this.checked);' checked>");
            } else if (newStatus.equals("1")) {
                out.println("<input type='checkbox' id='checkboxTask" + taskId + "' "
                        + "onclick='changeTaskStatus(" + taskId + ", this.checked);'>");
            }
            out.println("Done</div>");

        } else if (action.equalsIgnoreCase("saveTaskDetail")) {
            String taskId = request.getParameter("code");
            String deadline = request.getParameter("newDeadline");
            String[] assignees = request.getParameter("newAssignees").split(",");
            String[] tags = request.getParameter("newTags").split(",");

            ArrayList<String> oldAssignees = new ArrayList<String>(utrelationOp.FetchAssigneeByTaskId(taskId));
            ArrayList<String> oldTagIds = new ArrayList<String>(joinOp.GetTagsIdByTaskId(taskId));

            taskOp.UpdateDeadlineById(deadline, taskId);
            for (String oldAssignee : oldAssignees) {
                utrelationOp.DeleteByTaskIdAndUsername(taskId, oldAssignee);
            }

            for (String oldTagId : oldTagIds) {
                ttrelationOp.DeleteByTagId(oldTagId);
            }

            for (String assignee : assignees) {
                if (userOp.ListAllUsernames().contains(assignee.trim())) {
                    utrelationOp.InsertUtrelation(new Utrelation("", taskId, assignee.trim()));
                    if (ucrelationOp.CheckIfUcrelationExists(assignee.trim(), curCategId) == 0) {
                        ucrelationOp.InsertUcrelation(new Ucrelation("", assignee.trim(), curCategId));
                    }
                }
            }

            for (String tag : tags) {
                if (tagOp.CheckIfTagExists(tag.trim()) == 0) {
                    tagOp.InsertTag(tag.trim());
                }
                ttrelationOp.InsertTtrelation(new Ttrelation("", taskId, tagOp.SelectIdByName(tag.trim())));
            }

            ArrayList<Task> temp;
            for (String oldAssignee : oldAssignees) {
                temp = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(oldAssignee, curCategId));
                if (temp.isEmpty()) {
                    ucrelationOp.DeleteByCategId(curCategId, oldAssignee);
                }
            }

            for (String oldTagId : oldTagIds) {
                if (joinOp.CheckTtrelationByTagId(oldTagId) == 0) {
                    tagOp.DeleteById(oldTagId);
                }
            }
        } else if (action.equalsIgnoreCase("addTask")) {
            String[] assignees = request.getParameter("newTaskAssignee").split(",");
            String[] tags = request.getParameter("newTaskTags").split(",");
            String name = request.getParameter("newTaskName");


        } else if (action.equalsIgnoreCase("removeTask")) {
            String taskId = request.getParameter("code");
            tarelationOp.DeleteTarelationByTaskId(taskId);
            ttrelationOp.DeleteByTaskId(taskId);
            commentOp.DeleteCommentByTaskId(taskId);
            utrelationOp.DeleteByTaskId(taskId);
            taskOp.DeleteById(taskId);

            ArrayList<String> allUsers = new ArrayList<String>(userOp.ListAllUsernames());
            ArrayList<Task> temp;
            for (String user : allUsers) {
                if (!user.equals(username)) {
                    temp = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(user, curCategId));
                    if (temp.isEmpty()) {
                        ucrelationOp.DeleteByCategId(curCategId, username);
                    }
                }
            }
        } else if (action.equalsIgnoreCase("removeReference")) {
            String taskId = request.getParameter("code");

            utrelationOp.DeleteByTaskId(taskId);
            ArrayList<Task> temp = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(username, curCategId));
            if (temp.isEmpty()) {
                ucrelationOp.DeleteByCategId(curCategId, username);
            }
        
        } else if (action.equalsIgnoreCase("addComment")) {
            String idtask = request.getParameter("it");
            String content = request.getParameter("c");
            
            DateFormat dateFormat = new SimpleDateFormat("yyyy:MM:dd:HH:mm");
            Date date = new Date();
            String timestamp = dateFormat.format(date).toString();
            
            String avatar = userOp.SelectUserInfoByUsername(username).getAvatar();
            
            commentOp.InsertComment(idtask, username, dateFormat.format(date).toString(), content);
            String idcomment = commentOp.GetIdByOtherAttributes(idtask, username, content);
            
            String message = avatar+":"+timestamp+":"+content+":"+idcomment;
            out.println(message);
        } else if (action.equalsIgnoreCase("delComment")) {
            String idcomment = request.getParameter("ic");
            
            commentOp.DeleteCommentByCommentId(idcomment);
            out.println(idcomment);
        } else {
            System.out.println("No command existed");
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
