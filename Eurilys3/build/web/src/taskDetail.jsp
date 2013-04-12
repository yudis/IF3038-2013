<%@include file="header.jsp"%>
<%@include file="getTaskDetail.jsp"%>
<script type="text/javascript" src="../js/editTask.js"> </script>

<!-- Web Content -->
<section>
   <%@include file="navbar.jsp"%>
   <div id="dynamic_content">
      <div id="edit_task_header" class="left top30 dynamic_content_head">
         <% out.print(category + " | " + taskname); %>
      </div>

      <input id="edit_task_button" class="left top30 link_blue_rect" 
             onclick="edit_task()" type="button" value="Edit Task">


      <input id="save_button_td" class="left top30 link_blue_rect" 
             onclick='save_edit_task("<% out.print(request.getParameter("idtask")); %>")' type="button" value="Save">


      <div id="row1_taskdetail" class="left top30 dynamic_content_row">
         <div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
         <div id="task_name_rtd" class="left dynamic_content_right"><% out.print(taskname); %></div>
      </div>

      <div id="row2_taskdetail" class="left top10 dynamic_content_row">
         <div id="attachment_ltd" class="left dynamic_content_left">Attachment</div>
         <div id="attachment_rtd" class="left dynamic_content_right">
            <img id="taskdetail_img" src="../img/taskdetail_img.jpg" alt="Rikka-chan">
         </div>
      </div>

      <div id="row3_taskdetail" class="left top10 dynamic_content_row">
         <div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
         <div id="deadline_rtd" class="left dynamic_content_right"><% out.print(deadline); %></div>
      </div>

      <div id="row4_taskdetail" class="left top10 dynamic_content_row">
         <div id="assignee_ltd" class="left dynamic_content_left">Assignee</div>
         <div id="assignee_rtd" class="left dynamic_content_right">
            <% 
               String output = "";
               for (String a : assignee){
                  output += a + ", ";
               }
               out.print(output.substring(0, output.length()-2));
            %>
         </div>
      </div>

      <div id="row5_taskdetail" class="left top10 dynamic_content_row">
         <div id="tag_ltd" class="left dynamic_content_left">Tag</div>
         <div id="tag_rtd" class="left dynamic_content_right">
            <% 
               output = "";
               for (String t : tag){
                  output += t + ", ";
               }
               out.print(output.substring(0, output.length()-2));
            %>
         </div>
      </div>

      <div id="row7_taskdetail" class="left top10 dynamic_content_row">
         <div id="addcomment_ltd" class="left dynamic_content_left">Add Comment</div>
         <div id="addcomment_rtd" class="left dynamic_content_right">
            <form autocomplete="off">
               <textarea id="comment_textarea" rows="5" cols="50" name="CommentBox">
               </textarea><br>
               <input type="submit" value="Add">
            </form>
         </div>
      </div>

      <div id="row6_taskdetail" class="left top10 dynamic_content_row">
         <div id="comment_ltd" class="left dynamic_content_left">Comment</div>
         <div id="comment_rtd" class="left dynamic_content_right">Lorem ipsum dolor sit amet, 
            ea per meliore copiosae gubergren, ei latine iracundia euripidis sed, 
            ut odio lorem qui. Mea ne nobis feugait, sea iuvaret pertinax at, 
            harum commodo ne per. Per timeam dolorum no. Ne nam utroque percipitur, 
            augue partem imperdiet in pri. At est omnes habemus. Atqui antiopam 
            indoctum quo cu, no purto antiopam vis. Agam vidit ea nam, audiam alterum 
            appellantur mel ad. Copiosae erroribus vulputate no est, eos affert 
            minimum atomorum ei. Id nec choro luptatum, oblique suscipit nam ex.
            Sit elitr legere postulant te. Id putent dolorem usu, in sanctus 
            scripserit est. Has at zril quaeque sensibus, at case elaboraret vis, 
            nec natum noluisse salutandi ut. Putent habemus adversarium vim et, 
            has platonem hendrerit eu, vim at urbanitas pertinacia. Id pri partem 
            malorum aliquando, ne eum veniam animal probatus.
         </div>
      </div>
   </div>
</section>

<%@include file="footer.jsp"%>

<script>
   updateNavbar("<% out.print(session.getAttribute("username"));%>");
   setInterval(function(){updateNavbar("<% out.print(session.getAttribute("username"));%>")}, 1000);
</script>
</body>
</html>