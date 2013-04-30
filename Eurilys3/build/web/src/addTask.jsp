<%@include file="header.jsp"%>
<script type="text/javascript" src="../js/addTask.js"> </script>
<!-- Web Content -->
<section>
   <%@include file="navbar.jsp"%>
   <div id="dynamic_content">
      <div id="add_task_container">
         <div id="add_task_header" class="left top30 dynamic_content_head">
            Add New Task
         </div>
         <form name="addtask_form" id="addtask_form" method="post" enctype="multipart/form-data" action="../ServletHandler?type=AddTask">
            <input type="hidden" name="username" value="<% out.print(session.getAttribute("username")); %>">
            <input type="hidden" name ="idcat" value="<% out.print(request.getParameter("idcat")); %>">
            
            <div id="row1_addtask" class="left top30 dynamic_content_row">
               <div id="task_name_lat" class="left dynamic_content_left">Task Name</div>
               <div id="task_name_rat" class="left dynamic_content_right">
                  <input id="task_name_input" onkeyup="javascript:checkTaskName();" onchange="checkTaskName();" type="text" name="task_name_input" class="left">
                  <img src="../img/none.png" id="taskname_validation" class="left signup_form_validation" alt="validation image"/>
               </div>
            </div>

            <div id="row2_addtask" class="left top10 dynamic_content_row">
               <div id="attachment_lat" class="left dynamic_content_left">Attachment</div>
               <div id="attachment_rat" class="left dynamic_content_right">
                  <input id="attachment_upload" type="file" onchange="checkTaskAttachment();" name="attachment_upload" class="left">
                  <img src="../img/none.png" id="task_attachment_validation" class="left signup_form_validation" alt="validation image"/>
               </div>
            </div>

            <div id="row3_addtask" class="left top10 dynamic_content_row">
               <div id="deadline_lat" class="left dynamic_content_left">Deadline</div>
               <div id="deadline_rat" class="left dynamic_content_right">
                  <input id="deadline_input" type="date" onchange="checkDeadline()" name="deadline_input" class="left">
                  <img src="../img/none.png" id="deadline_validation" class="left signup_form_validation" alt="validation image"/>
               </div>
            </div>

            <div id="row4_addtask" class="left top10 dynamic_content_row">
               <div id="assignee_lat" class="left dynamic_content_left">Assignee</div>
               <div id="assignee_rat" class="left dynamic_content_right">
                  <input id="assignee_input" list="addTask_suggestion" type="text" name="assignee_input" onkeyup="autoCompleteAddTask(this.value)">
                  <datalist id="addTask_suggestion"></datalist>
               </div>
            </div>

            <div id="row5_addtask" class="left top10 dynamic_content_row">
               <div id="tag_lat" class="left dynamic_content_left">Tag</div>
               <div id="tag_rat" class="left dynamic_content_right">
                  <input id="tag_input" type="text" name="tag_input" >
               </div>
            </div>

            <div id="row6_addtask" class="left top10 dynamic_content_row">
               <input id="add_task_button" type="submit" value="Add Task" class="link_blue_rect">
            </div>
         </form>
      </div>
   </div>
</section>

<%@include file="footer.jsp"%>
<script>
   updateNavbar("<% out.print(session.getAttribute("username")); %>");
   setInterval(function(){updateNavbar("<% out.print(session.getAttribute("username")); %>")}, 1000);
</script>
</body>
</html>
