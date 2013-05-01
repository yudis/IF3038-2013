<%@include file="header.jsp"%>
<%@include file="getTaskDetail.jsp"%>
<script type="text/javascript" src="../js/editTask.js"> </script>

<!-- Web Content -->
<section>
   <%@include file="navbar.jsp"%>
   <div id="dynamic_content">
      <div id="edit_task_header" class="left top30 dynamic_content_head">
         <% out.print(category + " | " + taskname);%>
      </div>

      <input id="edit_task_button" class="left top30 link_blue_rect" 
             onclick="edit_task()" type="button" value="Edit Task">


      <input id="save_button_td" class="left top30 link_blue_rect" 
             onclick='save_edit_task("<% out.print(request.getParameter("idtask"));%>")' type="button" value="Save">

      <input id="delete_task_button" class="left top30 link_blue_rect" 
             onclick='deleteTaskRedirect("<% out.print(request.getParameter("idtask"));%>")' type="button" value="Remove Task">
      
      <div id="row1_taskdetail" class="left top30 dynamic_content_row">
         <div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
         <div id="task_name_rtd" class="left dynamic_content_right"><% out.print(taskname);%></div>
      </div>

      <div id="row2_taskdetail" class="left top10 dynamic_content_row">
         <div id="attachment_ltd" class="left dynamic_content_left">Attachment</div>
         <div id="attachment_rtd" class="left dynamic_content_right">
            <%
               String attach = "";
               for (String a : attachment) {
                  if (a.substring(a.lastIndexOf(".")+1).equalsIgnoreCase("jpg")
                          || a.substring(a.lastIndexOf(".")+1).equalsIgnoreCase("png")
                          || a.substring(a.lastIndexOf(".")+1).equalsIgnoreCase("gif")) {
                     attach += "<img class='attachment_img' src='../" + a + "' />";
                  } else if (a.substring(a.lastIndexOf(".")+1).equalsIgnoreCase("mp4")
                          || a.substring(a.lastIndexOf(".")+1).equalsIgnoreCase("ogg")) {
                     attach += "<video width='480' controls>"
                             + "<source src='../" + a + "' type='video/" + a.substring(a.lastIndexOf(".")+1) + "' />"
                             + "</video>";
                  } else{
                     attach += "<a href='../" + a + "' target='_blank'>"
                             + a.substring(a.lastIndexOf("_")+1) + "</a>";
                  }
               }
               out.print(attach);
            %>
            <!--<img id="taskdetail_img" src="../img/taskdetail_img.jpg" alt="Rikka-chan">-->
<!--                        <video controls>
                           <source src="../attachment/agahh_pdz6z7Hu_video.mp4" type="video/mp4">
                           Your browser does not support the video tag.
                        </video>-->
            <!--<a href="../attachment/afiff_IdB1f1FX_Tubes III.pdf" target="_blank">tes</a>-->
            <!--<script>window.open("../attachment/afiff_IdB1f1FX_Tubes III.pdf" , "_blank");</script>-->
         </div>
      </div>

      <div id="row3_taskdetail" class="left top10 dynamic_content_row">
         <div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
         <div id="deadline_rtd" class="left dynamic_content_right"><% out.print(deadline);%></div>
      </div>

      <div id="row4_taskdetail" class="left top10 dynamic_content_row">
         <div id="assignee_ltd" class="left dynamic_content_left">Assignee</div>
         <div id="assignee_rtd" class="left dynamic_content_right">
            <%
               String output = "";
               for (String a : assignee) {
                  output += a + ", ";
               }
               out.print(output.substring(0, output.length() - 2));
            %>
         </div>
      </div>

      <div id="row5_taskdetail" class="left top10 dynamic_content_row">
         <div id="tag_ltd" class="left dynamic_content_left">Tag</div>
         <div id="tag_rtd" class="left dynamic_content_right">
         </div>
      </div>

      <div id="row7_taskdetail" class="left top10 dynamic_content_row">
         <div id="addcomment_ltd" class="left dynamic_content_left">Comment</div>
         <div id="addcomment_rtd" class="left dynamic_content_right">
            <div id="comment_area"></div>
            <textarea placeholder="write comment here..." id="comment_textarea" class="left" name="CommentBox">
            </textarea>
            <input id="add_comment_button" class="left link_blue_rect" type="button" onclick='addComment("<% out.print(request.getParameter("idtask"));%>")' value="Add">
         </div>
      </div>
   </div>
</section>

<%@include file="footer.jsp"%>

<script>
   removeTextAreaWhiteSpace();
   getAssignee("<% out.print(request.getParameter("idtask"));%>");
   getComment("<% out.print(request.getParameter("idtask"));%>");
   getTag("<% out.print(request.getParameter("idtask"));%>");
   updateNavbar("<% out.print(session.getAttribute("username"));%>");
   setInterval(function(){updateNavbar("<% out.print(session.getAttribute("username"));%>");getComment("<% out.print(request.getParameter("idtask"));%>")}, 1000);
</script>
</body>
</html>