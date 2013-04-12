<div id="navbar">
   <div id="short_profile">
      <%
         out.print("<img class='left' id='profile_picture' src='../"
                 + session.getAttribute("avatar") + "' />");
      %>
      <div id="profile_info" class="left">
         Welcome,
         <br>
         <a href="profile.php"><% out.print(session.getAttribute("name"));%></a>
      </div>
   </div>
   <div id="category_list">
      <div class="link_blue_rect" id="category_title"><a href="#" onclick="allCategory('<% out.print(session.getAttribute("username")); %>')">All Categories </a></div>
      <ul id="category_item">
      </ul>
      <div id="add_task_link"><a href='javascript:void(0)' onclick="getCategoryURL()"> + new task </a> </div>
      <div id="delete_category" onclick="deleteCategory()">- delete category</a></div>
      <div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
      <div id="category_form">
         <div id="category_form_inner">	
            Category name : <br>
            <input type="text" id="add_category_name" value="">
            <br><br>
            Assignee(s) : <br>
            <input type="text" id="add_category_asignee_name" value="">
            <br><br>
            <div id="add_category_button" class="link_red" onclick="addCategory('<% out.print(session.getAttribute("username")); %>')"> Add </div>
         </div>
      </div>
   </div>
</div>