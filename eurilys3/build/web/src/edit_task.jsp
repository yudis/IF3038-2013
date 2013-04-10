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
                String tagList = "";
                ResultSet rs_taskdetail = null;
                ResultSet rs_assigne = null;
                ResultSet rs_avatar = null;

                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    System.out.println("Berhasil connect ke Mysql JDBC Driver - edit_task.jsp");
                } catch (ClassNotFoundException ex) {
                    System.out.println("Where is your MySQL JDBC Driver? - edit_task.jsp");
                }
                Connection con_edittask = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510086","root","");
                PreparedStatement stmt_edittask = con_edittask.prepareStatement("");
                
                //Get task detail
                String task_name = "";
                String task_status = "";
                String task_deadline = "";
                String task_category = "";
                stmt_edittask = con_edittask.prepareStatement("SELECT * FROM task WHERE task_id=?");
                stmt_edittask.setString(1, taskID);
                rs_taskdetail = stmt_edittask.executeQuery();
                rs_taskdetail.beforeFirst();
                while (rs_taskdetail.next()) {
                    task_name = rs_taskdetail.getString("task_name");
                    task_status = rs_taskdetail.getString("task_status");
                    task_deadline = rs_taskdetail.getString("task_deadline");
                    task_category = rs_taskdetail.getString("cat_name");
                }
                
            %>
            
            <div id='edit_task_header' class='left top30 dynamic_content_head darkBlue'>
                    <%=task_name%>
            </div>

            <form method="POST" action="../ServletHandler?type=edit_task">
                    <input type='submit' id="save_edit_task" name='edit_task_submit' class='left top30 link_blue_rect' value='Save'>

                    <input type="hidden" name="edit_task_id" value="<%=taskID%>"/>
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
                                    ATTACHMENT, gimana caranya? 
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
                            <%
                                stmt_edittask = con_edittask.prepareStatement("SELECT username from task_asignee WHERE task_id=?");
                                stmt_edittask.setString(1, taskID);
                                rs_assigne = stmt_edittask.executeQuery();
                                rs_assigne.beforeFirst();
                                while (rs_assigne.next()) { %>
                                <img src='../img/done.png' class='cursorPointer' width='8' onclick="javascript:deleteTaskAssigne('<%= taskID %>','<%= rs_assigne.getString("username") %>')"/> &nbsp;&nbsp;&nbsp;
                                <span class='userprofile_link darkBlueItalic' onclick="javascript:searchUser('<%= rs_assigne.getString("username") %>')"> <%= rs_assigne.getString("username") %> </span> 
                                <br> 
                            <% } %>                                
                            <br>
                            <input type="text" autocomplete="off" name="edit_task_assignee" id="edit_task_assignee" value=""> <br>
                            <input type="text" autocomplete="off" name="edit_task_assignee_auto" id="edit_task_assignee_auto" class="edit_task_input" onkeyup="EditTaskAssigneHint(this.value)" Placeholder="Type here..." value="">
                            <div id="edit_task_asignee_autocomplete"></div>
                        </div>
                    </div>			
                    <div class='left top20 dynamic_content_row'>
                            <div id='tag_ltd' class='left dynamic_content_left'> Tag </div>
                            <div id='tag_rtd' class='left dynamic_content_right'>
                            <%
                                //Get Tag List
                                stmt_edittask = con_edittask.prepareStatement("SELECT * from tag WHERE task_id=?");
                                stmt_edittask.setString(1, taskID);
                                rs_taskdetail = stmt_edittask.executeQuery();
                                rs_taskdetail.beforeFirst();            
                                while (rs_taskdetail.next()) { %>
                                   <img src='../img/done.png' class='cursorPointer' width='8' onclick="javascript:deleteTaskTag('<%= taskID %>','<%= rs_taskdetail.getString("tag_name") %>')"/> &nbsp;&nbsp;&nbsp;
                                   <span class='darkBlueItalic' onclick="javascript:searchUser('<%= rs_taskdetail.getString("tag_name") %>')"> <%= rs_taskdetail.getString("tag_name") %> </span> 
                                   <br> 
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