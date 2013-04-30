<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@include file="logged_in_header.jsp"%>	
<section>
    <!-- Navigation Bar -->
    <%@include file="navigation_bar.jsp"%>

    <div id="dynamic_content">
        <div class='taskDetail'>
            <%
                String taskID = (String) request.getParameter("task_id");                            
                URL taskDetailURL = new URL("http://localhost:8084/eurilys4-service/task/task_detail?task_id=" + taskID);
                //URL userDetailURL = new URL("http://eurilys.ap01.aws.af.cm/task/task_detail?task_id=" + taskID);
                HttpURLConnection taskDetailConn = (HttpURLConnection) taskDetailURL.openConnection();
                taskDetailConn.setRequestMethod("GET");
                taskDetailConn.setRequestProperty("Accept", "application/json");
                if (taskDetailConn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + taskDetailConn.getResponseCode());
                }
                BufferedReader taskDetailBr = new BufferedReader(new InputStreamReader((taskDetailConn.getInputStream())));
                String taskDetailOutput;
                String taskDetailJSONObject = "";
                while ((taskDetailOutput = taskDetailBr.readLine()) != null) {
                    taskDetailJSONObject += taskDetailOutput;
                } 
                taskDetailConn.disconnect();
                //Parse userDetailJSONObject 
                JSONTokener taskDetailTokener = new JSONTokener(taskDetailJSONObject);
                JSONObject taskDetailroot = new JSONObject(taskDetailTokener);

                String task_name = taskDetailroot.getString("task_name");
                String task_id = taskDetailroot.getString("task_id");
                String task_deadline = taskDetailroot.getString("task_deadline");
                String task_status = taskDetailroot.getString("task_status");
                String task_category = taskDetailroot.getString("task_category");
                String task_creator = taskDetailroot.getString("task_creator");

                JSONArray comment_id = taskDetailroot.getJSONArray("comment_id");
                JSONArray comment_timestamp = taskDetailroot.getJSONArray("comment_timestamp");
                JSONArray comment_content = taskDetailroot.getJSONArray("comment_content");
                JSONArray comment_creator = taskDetailroot.getJSONArray("comment_creator");
                
                JSONArray tag_list = taskDetailroot.getJSONArray("tag_list");
                JSONArray task_assignee = taskDetailroot.getJSONArray("task_assignee");
            %>
            
            <div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
                    <%=task_name%>
            </div>

            <form method="POST" action="../ServletHandler?type=edit_task">
                    <input type='submit' id="save_edit_task" name='edit_task_submit' class='left top30 link_blue_rect' value='Save'>

                    <input type="hidden" name="edit_task_id" value="<%=task_id%>"/>
                    <div class='left top30 dynamic_content_row'>
                            <div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
                            <div id='task_name_rtd' class='left dynamic_content_right'> <%=task_name%> </div>
                    </div>
                    <div class='left top20 dynamic_content_row'>
                            <div id='task_status_ltd' class='left dynamic_content_left'> Status </div>
                            <div id='task_status_rtd' class='left dynamic_content_right'> 
                                <% if (task_status.equals("0")) { %>
                                    Not Finished Yet
                                <% } else { %>
                                    Finished
                                <% }  %>
                            </div>
                    </div>
                    <div class='left top20 dynamic_content_row'>
                            <div id='attachment_ltd' class='left dynamic_content_left'>Attachment</div>
                            <div id='attachment_rtd' class='left dynamic_content_right'>
                                    Belom dibikin. Hahaha. 
                            </div>
                    </div>

                    <div class='left top20 dynamic_content_row'>
                            <div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
                            <div id='deadline_rtd' class='left dynamic_content_right'> 
                                    <input class="edit_task_input" id="edit_task_deadline" name="edit_task_deadline" type="date" name="deadline_td" value="<%=task_deadline%>"/>
                            </div>
                    </div>

                    <div class='left top20 dynamic_content_row'>
                        <div id='assignee_ltd' class='left dynamic_content_left'>Assignee</div>
                        <div id='assignee_rtd' class='left dynamic_content_right'>
                            <% for (int i=0; i<task_assignee.length(); i++) { %>                                
                                <div id="user_<%=task_assignee.get(i)%>">
                                    <img src='../img/done.png' class='cursorPointer' width='8' onclick="javascript:deleteTaskAssigne('<%= task_id %>','<%= task_assignee.get(i) %>')"/> &nbsp;&nbsp;&nbsp;
                                    <span class='userprofile_link darkBlueItalic' onclick="javascript:searchUser('<%= task_assignee.get(i) %>')"> <%= task_assignee.get(i) %> </span> 
                                    <br> 
                                </div>
                            <% } %>
                            <br>
                            <input type="text" autocomplete="off" name="edit_task_assignee" id="edit_task_assignee" value=""> <br>
                            <input type="text" autocomplete="off" name="edit_task_assignee_auto" id="edit_task_assignee_auto" class="edit_task_input" onkeyup="EditTaskAssigneHint(this.value)" Placeholder="Type here..." value="">
                            <div id="edit_task_asignee_autocomplete"></div>
                        </div>
                    </div>			
                    <div class='left top20 dynamic_content_row'>
                            <div id='tag_ltd' class='left dynamic_content_left'> Tag </div>
                            <div id='tag_rtd' class='left dynamic_content_right' id="edit_task_tag">
                            <% for (int i=0; i<tag_list.length();i++) { %>
                                <div id="tag_<%=tag_list.get(i)%>">
                                    <img src='../img/done.png' class='cursorPointer' width='8' onclick="javascript:deleteTaskTag('<%= task_id %>','<%= tag_list.get(i) %>')"/> &nbsp;&nbsp;&nbsp;
                                    <span class='darkBlueItalic' onclick="javascript:searchUser('<%=tag_list.get(i)%>')"> <%=tag_list.get(i)%> </span> 
                                    <br>
                                </div>
                             <% } %>
                                    <br>
                                    <input class="edit_task_input" id="edit_task_tag" name="edit_task_tag" type="text" name="tag_td" value=""/> 
                            </div>
                    </div>
            </form>
        </div>				
    </div>
</section>
<%@include file="footer.jsp"%>