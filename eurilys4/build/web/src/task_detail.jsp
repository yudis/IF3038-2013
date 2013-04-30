<%@page import="java.io.PrintWriter"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>

<%@include file="logged_in_header.jsp"%>	

<section>
    <%@include file="navigation_bar.jsp"%>
    <div id="dynamic_content">
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
            
            String tagList = "";
            for (int i=0; i<tag_list.length();i++) {
                tagList += tag_list.get(i);
                if (i != tag_list.length()-1) {
                    tagList += " , ";
                }
            }
        %>
        
        <div class='taskDetail'>
            
            <% if ("ok".equals(request.getParameter("update_task"))) { %>
                <div class="red"> Task has been successfully updated </div>
            <% } else if ("failed".equals(request.getParameter("update_task"))) { %>
                <div class="red"> Tasl NOT has been successfully updated. </div>
            <% } %>
            
            <div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
                <%=task_name%>
            </div>
            <a id="edit_task_button" href="edit_task.jsp?task_id=<%=task_id%>" class='left top30 link_blue_rect'> Edit Task </a>
            <div class='left top30 dynamic_content_row'>
                <div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
                <div id='task_name_rtd' class='left dynamic_content_right'> <%=task_name%> </div>
            </div>
            <div class='left top20 dynamic_content_row'>
                <div id='task_status_ltd' class='left dynamic_content_left'> Status </div>
                <% if ("0".equals(task_status)) { %>
                <div id='task_status_rtd' class='left dynamic_content_right'> Not finished yet </div>
                <% } else { %>
                <div id='task_status_rtd' class='left dynamic_content_right'> Finished </div>
                <% } %>
            </div>                    
            <div class='left top20 dynamic_content_row'>
                <div id='attachment_ltd' class='left dynamic_content_left'> Attachment</div>
                <div id='attachment_rtd' class='left dynamic_content_right'> Belum buat hahaha~ </div>
            </div>
            <div class='left top20 dynamic_content_row'>
                <div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
                <div id='deadline_rtd' class='left dynamic_content_right'> <%=task_deadline%> </div>
            </div>
            
            <div class='left top20 dynamic_content_row'>
                <div id='assignee_ltd' class='left dynamic_content_left'>Assignee</div>
                <div id='assignee_rtd' class='left dynamic_content_right'>
                    <% for (int i=0; i<task_assignee.length(); i++) { %>
                        <span class='userprofile_link darkBlueItalic' onclick="javascript:searchResult('<%=task_assignee.get(i)%>','user')"> <%=task_assignee.get(i)%> </span>  
                    <% 
                        if (i != task_assignee.length()-1)
                            out.print(" , ");
                    } %>
                </div>               
            </div>
                 
            <input type='hidden' id='hidden_ass_name' value=''/>
            <div class='left top20 dynamic_content_row'>
                <div id='tag_ltd' class='left dynamic_content_left'> Tag </div>
                <div id='tag_rtd' class='left dynamic_content_right'> <%= tagList %> </div>
            </div>
            <div class='left top45 dynamic_content_row'>
                <div id='comment_ltd' class='left dynamic_content_left'> Comment </div>
                <div id='comment_rtd' class='left dynamic_content_right'> </div>
            </div>
            
            <% for (int i=0; i<comment_id.length();i++) { %>
                <div class='left top20 dynamic_content_row' id="comment_<%=comment_id.get(i)%>">
                    <div id='comment_ltd' class='left dynamic_content_left darkBlueItalic userprofile_link' onclick="javascript:searchUser('<%= comment_creator.get(i) %>')">
                        <!-- get avatar -->
                        <img src='' width='55'/> 
                        <br> <%= comment_creator.get(i) %>
                        <br> <%= comment_timestamp.get(i) %>
                    </div>
                    <div id='comment_rtd' class='left dynamic_content_right'> <%= comment_content.get(i) %> </div> 
                    <% if (session.getAttribute("username").equals(comment_creator.get(i))) { %>
                        <img src='../img/done.png' onclick="javascript:deleteComment('<%=comment_id.get(i)%>')" class='cursorPointer' alt=''>
                    <% } %>
                </div>
            <% } %>
            <div class='left top20 dynamic_content_row'>
                <div id='addcomment_ltd' class='left dynamic_content_left'> &nbsp; </div>
                <div id='addcomment_rtd' class='left dynamic_content_right'>
                    <form autocomplete='off' method='POST' action='../ServletHandler?type=add_comment'>
                        <textarea id='comment_textarea' rows='5' cols='50' name='CommentBox'></textarea> 
                        <br>
                        <input type='hidden' id='hidden_task_id' name='comment_task_id' value='<%= taskID %>'>
                        <input type='submit' value='Add Comment' name='add_comment_button' class='link_red'>
                        <br><br><br>
                    </form>
                </div>
            </div> 
    </div>
</section>
		
<%@include file="footer.jsp"%>