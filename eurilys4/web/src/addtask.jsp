<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="logged_in_header.jsp"%>

<!-- Web Content -->
<section>
    <%@include file="navigation_bar.jsp"%>
    <div id="dynamic_content">
        <%
            String cat_name = (String) request.getParameter("cat_name");
        %>
        <div id="add_task_container">
                <div id="add_task_header" class="left top30 dynamic_content_head">
                        Add New Task
                </div>
                <form id="addtask_form" method="POST" action="../ServletHandler?type=add_task">                    
                    <div id="row1_addtask" class="left top30 dynamic_content_row">
                            <div id="task_name_lat" class="left dynamic_content_left">Task Name</div>
                            <div id="task_name_rat" class="left dynamic_content_right">
                                    <input id="task_name_input" onkeydown="javascript:checkTaskName();" type="text" name="task_name_input" class="left">
                                    <img src="../img/yes.png" id="taskname_validation" class="left signup_form_validation" alt="validation image"/>
                            </div>               
                    </div>             

                    <div id="row2_addtask" class="left top10 dynamic_content_row">
                            <div id="attachment_lat" class="left dynamic_content_left">Attachment</div>
                            <div id="attachment_rat" class="left dynamic_content_right">
                                <input id="attachment_file1" type="file" name="attachment_file1" onchange="javascript:checkTaskAttachment();"  class="left">
                                <img src="../img/yes.png" id="task_attachment_validation" class="left signup_form_validation" alt="validation image"/>
                            </div>
                    </div>

                    <div id="row2_addtask" class="left top10 dynamic_content_row">
                            <div id="attachment_lat" class="left dynamic_content_left">Add more attachment</div>
                            <div id="attachment_rat" class="left dynamic_content_right">
                                <input id="attachment_file2" type="file" name="attachment_file2" onchange="javascript:checkTaskAttachment();"  class="left">
                                <img src="../img/yes.png" id="task_attachment_validation" class="left signup_form_validation" alt="validation image"/>
                            </div>
                    </div>

                    <div id="row2_addtask" class="left top10 dynamic_content_row">
                            <div id="attachment_lat" class="left dynamic_content_left">Add more attachment</div>
                            <div id="attachment_rat" class="left dynamic_content_right">
                                <input id="attachment_file3" type="file" name="attachment_file3" onchange="javascript:checkTaskAttachment();"  class="left">
                                <img src="../img/yes.png" id="task_attachment_validation" class="left signup_form_validation" alt="validation image"/>
                            </div>
                    </div>


                    <div id="row3_addtask" class="left top10 dynamic_content_row">
                            <div id="deadline_lat" class="left dynamic_content_left">Deadline</div>
                            <div id="deadline_rat" class="left dynamic_content_right">
                                    <input id="deadline_input" type="date" name="deadline_input">
                            </div>
                    </div>

                    <div id="row4_addtask" class="left top10 dynamic_content_row">
                            <div id="assignee_lat" class="left dynamic_content_left">Assignee</div>
                            <div id="assignee_rat" class="left dynamic_content_right">                                
                                <input id="add_task_assignee_input" type="text" name="assignee_input"> <br>
                                <!--
                                <input id="add_task_assignee_auto" type="text" onkeyup="AddTaskAssigneHint(this.value)" Placeholder="Type here..." value="">
                                <div id="add_task_asignee_autocomplete"></div> -->
                            </div>
                    </div>

                    <div id="row5_addtask" class="left top10 dynamic_content_row">
                            <div id="tag_lat" class="left dynamic_content_left">Tag</div>
                            <div id="tag_rat" class="left dynamic_content_right">
                                    <input id="tag_input" type="text" name="tag_input" >
                            </div>
                    </div>

                    <input id="cat_name" type="hidden" name="addtask_cat_name"  value="<%=cat_name%>" >			

                    <div id="row6_addtask" class="left top10 dynamic_content_row">
                            <input id="add_task_button" name="add_task_button" type="submit" onclick="javascript:addCatName();" value="Add Task" class="link_blue_rect">
                    </div>
                </form>
            </div>
    </div>
</section>

<%@include file="footer.jsp"%>        
