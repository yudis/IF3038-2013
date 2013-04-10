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
            String tagList = "";
            Blob user_avatar = null;
            
            List<String> commentContent = new ArrayList<String>();
            List<String> commentID = new ArrayList<String>();
            List<String> commentCreator = new ArrayList<String>();
            List<String> commentTimestamp = new ArrayList<String>();
            
            ResultSet rs_taskdetail = null;
            ResultSet rs_assigne = null;
            ResultSet rs_avatar = null;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println("Berhasil connect ke Mysql JDBC Driver ... ");
            } catch (ClassNotFoundException ex) {
                System.out.println("Where is your MySQL JDBC Driver?");
            }
            Connection con_taskdetail = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
            PreparedStatement stmt_taskdetail = con_taskdetail.prepareStatement("");
            
            //Get Tag List
            stmt_taskdetail = con_taskdetail.prepareStatement("SELECT * from tag WHERE task_id=?");
            stmt_taskdetail.setString(1, taskID);
            rs_taskdetail = stmt_taskdetail.executeQuery();
            rs_taskdetail.beforeFirst();            
            while (rs_taskdetail.next()) {
                tagList = tagList + rs_taskdetail.getString("tag_name") + " , ";
            }
            
            //Get Comment List
            stmt_taskdetail = con_taskdetail.prepareStatement("SELECT comment_id, comment_content, comment_creator, comment_timestamp from comment WHERE task_id=?");
            stmt_taskdetail.setString(1, taskID);
            rs_taskdetail = stmt_taskdetail.executeQuery();
            rs_taskdetail.beforeFirst();            
            while (rs_taskdetail.next()) {
                commentID.add(rs_taskdetail.getString("comment_id"));
                commentContent.add(rs_taskdetail.getString("comment_content"));
                commentCreator.add(rs_taskdetail.getString("comment_creator"));
                commentTimestamp.add(rs_taskdetail.getString("comment_timestamp"));
            }
            
            //Get task detail
            stmt_taskdetail = con_taskdetail.prepareStatement("SELECT * FROM task WHERE task_id=?");
            stmt_taskdetail.setString(1, taskID);
            rs_taskdetail = stmt_taskdetail.executeQuery();
            rs_taskdetail.beforeFirst();
            
            while (rs_taskdetail.next()) { %>
                <div class='taskDetail'>
                    <div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
                        <%=rs_taskdetail.getString("task_name")%>
                    </div>
                    <a id="edit_task_button" href="edit_task.jsp?task_id=<%=taskID%>" class='left top30 link_blue_rect'> Edit Task </a>
                    <div class='left top30 dynamic_content_row'>
                        <div id='task_name_ltd' class='left dynamic_content_left'> Task Name </div>
                        <div id='task_name_rtd' class='left dynamic_content_right'> <%=rs_taskdetail.getString("task_name")%> </div>
                    </div>
                    <div class='left top20 dynamic_content_row'>
                        <div id='task_status_ltd' class='left dynamic_content_left'> Status </div>
                        <% if (rs_taskdetail.getString("task_status").equals("0")) { %>
                        <div id='task_status_rtd' class='left dynamic_content_right'> Not finished yet </div>
                        <% } else { %>
                        <div id='task_status_rtd' class='left dynamic_content_right'> Finished </div>
                        <% } %>
                    </div>                    
                    <div class='left top20 dynamic_content_row'>
                        <div id='attachment_ltd' class='left dynamic_content_left'>Attachment</div>
                        <div id='attachment_rtd' class='left dynamic_content_right'> ??? </div>
                    </div>
                    <div class='left top20 dynamic_content_row'>
                        <div id='deadline_ltd' class='left dynamic_content_left'>Deadline</div>
                        <div id='deadline_rtd' class='left dynamic_content_right'> <%=rs_taskdetail.getString("task_deadline")%> </div>
                    </div>
                    <div class='left top20 dynamic_content_row'>
                        <div id='assignee_ltd' class='left dynamic_content_left'>Assignee</div>
                        <div id='assignee_rtd' class='left dynamic_content_right'>
                            <%
                            //Get assignee name list
                            stmt_taskdetail = con_taskdetail.prepareStatement("SELECT username from task_asignee WHERE task_id=?");
                            stmt_taskdetail.setString(1, taskID);
                            rs_assigne = stmt_taskdetail.executeQuery();
                            rs_assigne.beforeFirst();
                            while (rs_assigne.next()) { %>
                                <span class='userprofile_link darkBlueItalic' onclick="javascript:searchUser('<%= rs_assigne.getString("username") %>')"> <%= rs_assigne.getString("username") %> </span> , 
                            <% }
                            %>
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
                    <% for (int i=0; i<commentContent.size(); i++) { %>
                    <div class='left top20 dynamic_content_row'>
                        <div id='comment_ltd' class='left dynamic_content_left darkBlueItalic userprofile_link' onclick="javascript:searchUser('<%= commentCreator.get(i) %>')"> 
                            <%
                            stmt_taskdetail = con_taskdetail.prepareStatement("SELECT avatar FROM user WHERE username=?");
                            stmt_taskdetail.setString(1, (String)session.getAttribute("username"));
                            rs_avatar = stmt_taskdetail.executeQuery();
                            rs_avatar.beforeFirst();
                            while (rs_avatar.next()) {
                                user_avatar = rs_avatar.getBlob("avatar");
                            }
                            %>
                            <img src='<%= user_avatar %>' width='55'/> 
                            <br> <%= commentCreator.get(i) %>
                            <br> <%= commentTimestamp.get(i) %>
                        </div>
                        <div id='comment_rtd' class='left dynamic_content_right'> <%= commentContent.get(i) %> </div> 
                        <% if (session.getAttribute("username").equals(commentCreator.get(i))) { %>
                            <img src='../img/done.png' onclick="javascript:deleteComment('<%=taskID%>','<%=commentID.get(i)%>')" class='cursorPointer' alt=''>
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
             <% }
        %>
    </div>
</section>
		
<%@include file="footer.jsp"%>