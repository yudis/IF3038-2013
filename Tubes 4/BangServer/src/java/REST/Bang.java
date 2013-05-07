/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package REST;

import DBOperation.*;
import JSON.JSONObject;
import Model.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jeremy Joseph Hanniel
 */
public class Bang extends HttpServlet {

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
    private Pattern rgShowCategoryList = Pattern.compile("^/showCategoryList/([\\w._%].*)$");
    private Pattern rgDeleteCategory = Pattern.compile("^/deleteCategory/([\\w._%].*)/([0-9]{1,})$");
    private Pattern rgShowTaskList = Pattern.compile("^/showTaskList/([\\w._%].*)/([0-9]{1,})$");
    private Pattern rgShowTaskDetail = Pattern.compile("^/showTaskDetail/([\\w._%].*)/([0-9]{1,})$");
    private Pattern rgChangeTaskStatus = Pattern.compile("^/changeTaskStatus/([0-9]{1,})/([0-1]{1})$");
    private Pattern rgDeleteTask = Pattern.compile("^/deleteTask/([\\w._%].*)/([0-9]{1,})/([0-9]{1,})$");
    private Pattern rgDeleteComment = Pattern.compile("^/deleteComment/([0-9]{1,})");
    private Pattern rgRemoveReference = Pattern.compile("^/removeReference/([\\w._%].*)/([0-9]{1,})/([0-9]{1,})");
    private Pattern rgEditTaskDetail = Pattern.compile("^/editTaskDetail/([\\w._%].*)/([0-9]{1,})");
    private Pattern rgSaveTaskDetail = Pattern.compile("^/saveTaskDetail/([0-9]{1,})/([0-9]{1,})");
    PrintWriter out;
    private Pattern rgGetAllUser = Pattern.compile("^/getAllUser/([\\w._%].*)");
    private Pattern rgGetAllTag = Pattern.compile("^/getAllTag/([\\w._%].*)");

    public Bang() {
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
        response.setContentType("text/html;charset=UTF-8");
        out = response.getWriter();
        String pathInfo = request.getPathInfo();
        Matcher matcher;

        try {
            matcher = rgShowCategoryList.matcher(pathInfo);
            if (matcher.find()) {
                showCategoryList(matcher);
                return;
            }

            matcher = rgDeleteCategory.matcher(pathInfo);
            if (matcher.find()) {
                deleteCategory(matcher);
                return;
            }

            matcher = rgShowTaskList.matcher(pathInfo);
            if (matcher.find()) {
                showTaskList(matcher);
                return;
            }

            matcher = rgShowTaskDetail.matcher(pathInfo);
            if (matcher.find()) {
                showTaskDetail(matcher);
                return;
            }

            matcher = rgChangeTaskStatus.matcher(pathInfo);
            if (matcher.find()) {
                changeTaskStatus(matcher);
                return;
            }

            matcher = rgDeleteTask.matcher(pathInfo);
            if (matcher.find()) {
                deleteTask(matcher);
                return;
            }

            matcher = rgDeleteComment.matcher(pathInfo);
            if (matcher.find()) {
                deleteComment(matcher);
                return;
            }

            matcher = rgRemoveReference.matcher(pathInfo);
            if (matcher.find()) {
                removeReference(matcher);
                return;
            }

            matcher = rgEditTaskDetail.matcher(pathInfo);
            if (matcher.find()) {
                editTaskDetail(matcher);
                return;
            }

            matcher = rgSaveTaskDetail.matcher(pathInfo);
            if (matcher.find()) {
                System.out.println("Save Task Detail");
                saveTaskDetail(matcher, request);
                return;
            }

            matcher = rgGetAllUser.matcher(pathInfo);
            if (matcher.find()) {
                getAllUser(matcher);
                return;
            }

            matcher = rgGetAllTag.matcher(pathInfo);
            if (matcher.find()) {
                getAllTag(matcher);
                return;
            }

            throw new ServletException("Invalid URL");
        } finally {
            out.close();
        }
    }

    private void getAllUser(Matcher m) {
        String code = m.group(1);

        ArrayList<User> users = new ArrayList<User>(userOp.FetchUserForSearch(code));

        JSONObject result = new JSONObject();
        for (int i = 0; i < users.size(); i++) {
            result.put("User " + Integer.toString(i), users.get(i).toJSONObject());
        }

        out.println(result);
    }

    private void getAllTag(Matcher m) {
        String code = m.group(1);

        ArrayList<String> tags = new ArrayList<String>(tagOp.FetchTagForSearch(code));

        JSONObject result = new JSONObject();
        for (int i = 0; i < tags.size(); i++) {
            result.put("Tag " + Integer.toString(i), tags.get(i));
        }

        out.println(result);
    }

    private void showCategoryList(Matcher m) {
        String username = m.group(1);
        ArrayList<Category> categories = new ArrayList<Category>(joinOp.GetCategByUsername(username));
        ArrayList<Integer> creator = new ArrayList<Integer>();

        JSONObject result = new JSONObject();
        JSONObject temp;
        for (int i = 0; i < categories.size(); i++) {
            temp = categories.get(i).toJSONObject();
            result.put("Category " + Integer.toString(i), temp);
        }

        out.println(result);
    }

    private void deleteCategory(Matcher m) {
        String username = m.group(1);
        String categId = m.group(2);
        ArrayList<String> taskIds = new ArrayList<String>(taskOp.FetchIdsByCategId(categId));

        for (String taskId : taskIds) {
            ttrelationOp.DeleteByTaskId(taskId);
            utrelationOp.DeleteByTaskId(taskId);
            tarelationOp.DeleteTarelationByTaskId(taskId);
            commentOp.DeleteCommentByTaskId(taskId);
            taskOp.DeleteById(taskId);
        }

        ucrelationOp.DeleteByCategId(categId);
        caurelationOp.DeleteCaurelationByCategId(categId);
        categoryOp.DeleteCategoryById(categId);
    }

    private void showTaskList(Matcher m) {
        String username = m.group(1);
        String categId = m.group(2);

        ArrayList<Task> tasks = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(username, categId));

        JSONObject tasksElmt = new JSONObject();
        JSONObject taskElmt;
        JSONObject tagsElmt;
        JSONObject resultElmt;
        ArrayList<String> tags;
        for (int i = 0; i < tasks.size(); i++) {
            taskElmt = tasks.get(i).toJSONObject();

            tags = new ArrayList<String>(joinOp.GetTagNamesByTaskId(tasks.get(i).getId_task()));
            tagsElmt = new JSONObject();
            for (int j = 0; j < tags.size(); j++) {
                tagsElmt.put("Tag " + Integer.toString(j), tags.get(j));
            }

            if (username.equals(taskOp.FetchCreatorById(tasks.get(i).getId_task()))) {
                taskElmt.put("IsCreator", true);
            } else {
                taskElmt.put("IsCreator", false);
            }

            resultElmt = new JSONObject();
            resultElmt.put("Task Detail", taskElmt);
            resultElmt.put("Tags", tagsElmt);

            tasksElmt.put("Task " + Integer.toString(i), resultElmt);
        }

        JSONObject result = new JSONObject();
        result.put("Tasks", tasksElmt);

        if (Integer.parseInt(categId) > 0) {
            if (caurelationOp.FetchAuthUsersByCategId(categId).contains(username)) {
                result.put("IsAuthUser", true);
            } else {
                result.put("IsAuthUser", false);
            }
        } else {
            result.put("IsAuthUser", false);
        }

        out.println(result);
    }

    private void changeTaskStatus(Matcher m) {
        String taskId = m.group(1);
        String newStatus = m.group(2);

        if (newStatus.equals("0")) {
            taskOp.UpdateStatusWithId("F", taskId);
        } else if (newStatus.equals("1")) {
            taskOp.UpdateStatusWithId("T", taskId);
        }

        out.println(taskOp.SelectById(taskId).getStatus());
    }

    private void deleteTask(Matcher m) {
        String username = m.group(1);
        String curCategId = m.group(2);
        String taskId = m.group(3);

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
                    ucrelationOp.DeleteByCategIdAndUsername(curCategId, username);
                }
            }
        }
    }

    private void deleteComment(Matcher m) {
        String commentId = m.group(1);
        commentOp.DeleteCommentByCommentId(commentId);
    }

    private void removeReference(Matcher m) {
        String username = m.group(1);
        String curCategId = m.group(2);
        String taskId = m.group(3);

        utrelationOp.DeleteByTaskIdAndUsername(taskId, username);
        ArrayList<Task> temp = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(username, curCategId));
        if (temp.isEmpty()) {
            ucrelationOp.DeleteByCategIdAndUsername(curCategId, username);
        }
    }

    private void showTaskDetail(Matcher m) {
        String username = m.group(1);
        String taskId = m.group(2);

        Task task = taskOp.SelectById(taskId);

        ArrayList<Attachment> attachments = joinOp.GetAttachmentFromId_task(task.getId_task());
        ArrayList<String> assignees = utrelationOp.FetchAssigneeByTaskId(task.getId_task());
        ArrayList<Comment> comments = commentOp.FetchCommentByTaskId(task.getId_task());
        ArrayList<String> tags = joinOp.GetTagNamesByTaskId(task.getId_task());

        JSONObject taskElmt = task.toJSONObject();
        JSONObject attachmentElmt = new JSONObject();
        for (int i = 0; i < attachments.size(); i++) {
            attachmentElmt.put("Attachment " + Integer.toString(i), attachments.get(i).toJSONObject());
        }
        JSONObject assigneesElmt = new JSONObject();
        for (int i = 0; i < assignees.size(); i++) {
            assigneesElmt.put("Assignee " + Integer.toString(i), assignees.get(i));
        }
        JSONObject commentsElmt = new JSONObject();
        for (int i = 0; i < comments.size(); i++) {
            commentsElmt.put("Comment " + Integer.toString(i), comments.get(i).toJSONObject());
        }
        JSONObject tagsElmt = new JSONObject();
        for (int i = 0; i < tags.size(); i++) {
            tagsElmt.put("Tag " + Integer.toString(i), tags.get(i));
        }

        ArrayList<String> tempArray = new ArrayList<String>();
        JSONObject avatarElmt = new JSONObject();
        JSONObject temp;
        String tempUser;
        for (int i = 0; i < comments.size(); i++) {
            tempUser = (userOp.SelectUserInfoByUsername(comments.get(i).getUsername())).getAvatar();
            if (!tempArray.contains(comments.get(i).getUsername())) {
                tempArray.add(comments.get(i).getUsername());
                temp = new JSONObject();
                temp.put("Username", comments.get(i).getUsername());
                temp.put("Avatar", tempUser);
                avatarElmt.put("User " + Integer.toString(i), temp);
            }
        }

        JSONObject result = new JSONObject();
        result.put("Task Detail", taskElmt);
        result.put("Assignees", assigneesElmt);
        result.put("Attachments", attachmentElmt);
        result.put("Comments", commentsElmt);
        result.put("Tags", tagsElmt);
        result.put("User Avatars", avatarElmt);

        if (utrelationOp.IsTaskEditable(username, taskId)) {
            result.put("IsEditable", true);
        }

        out.println(result);
    }

    private void editTaskDetail(Matcher m) {
        String username = m.group(1);
        String taskId = m.group(2);

        Task task = taskOp.SelectById(taskId);
        ArrayList<String> assignees = new ArrayList<String>(utrelationOp.FetchAssigneeByTaskId(taskId));
        ArrayList<String> tags = new ArrayList<String>(joinOp.GetTagNamesByTaskId(task.getId_task()));

        JSONObject taskElmt = task.toJSONObject();
        JSONObject assigneesElmt = new JSONObject();
        for (int i = 0; i < assignees.size(); i++) {
            assigneesElmt.put("Assignee " + Integer.toString(i), assignees.get(i));
        }
        JSONObject tagsElmt = new JSONObject();
        for (int i = 0; i < tags.size(); i++) {
            tagsElmt.put("Tag " + Integer.toString(i), tags.get(i));
        }

        JSONObject result = new JSONObject();
        result.put("Task", taskElmt);
        result.put("Assignees", assigneesElmt);
        result.put("Tags", tagsElmt);

        if (username.equals(taskOp.FetchCreatorById(task.getId_task()))) {
            result.put("creator", true);
            result.put("assignee", false);
        } else if (utrelationOp.FetchAssigneeByTaskId(task.getId_task()).contains(username)) {
            result.put("creator", false);
            result.put("assignee", true);
        }

        out.println(result);
    }

    private void saveTaskDetail(Matcher m, HttpServletRequest request) {
        String taskId = m.group(1);
        String categId = m.group(2);

        System.out.println("taskId = " + taskId);
        System.out.println("categId = " + categId);
        
        StringBuilder jb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null) {
                jb.append(line);
            }
        } catch (Exception e) { //report an error }
            e.getMessage();
        }

        ArrayList<String> newAssignees = new ArrayList<String>();
        ArrayList<String> newTags = new ArrayList<String>();
        
        JSONObject jsonObject = new JSONObject(jb.toString());
        String deadline = jsonObject.getString("Deadline");
        JSONObject assignees = jsonObject.getJSONObject("Assignees");
        for (int i = 0; i < assignees.length(); i++) {
            newAssignees.add(assignees.getString("Assignee " + Integer.toString(i)));
        }
        JSONObject tags = jsonObject.getJSONObject("Tags");
        for (int i = 0; i < tags.length(); i++) {
            newTags.add(tags.getString("Tag " + Integer.toString(i)));
        }

        System.out.println("newAssignees = " + newAssignees.toString());
        System.out.println("newTags = " + newTags.toString());
        System.out.println("deadline = " + deadline);
        
        ArrayList<String> oldAssignees = new ArrayList<String>(utrelationOp.FetchAssigneeByTaskId(taskId));
        ArrayList<String> oldTagIds = new ArrayList<String>(joinOp.GetTagsIdByTaskId(taskId));
        
        taskOp.UpdateDeadlineById(deadline, taskId);
        
        for (String oldAssignee : oldAssignees) {
            utrelationOp.DeleteByTaskIdAndUsername(taskId, oldAssignee);
        }

        for (String oldTagId : oldTagIds) {
            ttrelationOp.DeleteByTagId(oldTagId);
        }

        for (String newAssignee : newAssignees) {
            if (userOp.ListAllUsernames().contains(newAssignee.trim())) {
                utrelationOp.InsertUtrelation(new Utrelation("", taskId, newAssignee.trim()));
                if (ucrelationOp.CheckIfUcrelationExists(newAssignee.trim(), categId) == 0) {
                    ucrelationOp.InsertUcrelation(new Ucrelation("", newAssignee.trim(), categId));
                }
            }
        }

        for (String newTag : newTags) {
            if (tagOp.CheckIfTagExists(newTag.trim()) == 0) {
                tagOp.InsertTag(newTag.trim());
            }
            ttrelationOp.InsertTtrelation(new Ttrelation("", taskId, tagOp.SelectIdByName(newTag.trim())));
        }

        ArrayList<Task> temp;
        for (String oldAssignee : oldAssignees) {
            temp = new ArrayList<Task>(joinOp.GetTasksByUsernameAndCategoryId(oldAssignee, categId));
            if (temp.isEmpty()) {
                ucrelationOp.DeleteByCategIdAndUsername(categId, oldAssignee);
            }
        }

        for (String oldTagId : oldTagIds) {
            if (joinOp.CheckTtrelationByTagId(oldTagId) == 0) {
                tagOp.DeleteById(oldTagId);
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
