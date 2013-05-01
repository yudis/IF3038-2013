<%@include file="header.jsp"%>
<script type="text/javascript" src="../js/profile.js"> </script>
<!-- Web Content -->
<section>

   <div id="profile_left">
      <div id="profile_avatar">
         <img id="avatar_container">
         <div id="profile_avatar_username"></div>
      </div>
      <div id="profile_information">
         <form name="updateProfileForm" id="updateProfileForm" action="../ServletHandler?type=SaveEditProfile" method="post" enctype="multipart/form-data">
            <div class="profile_information_row left">
               <div class ="profile_information_left">Username</div>
               <div class="profile_information_right"><div id="username_edit"></div></div>
               <input id="username_submit" name="username_edit" type="hidden">
            </div>

            <div class="profile_information_row left">
               <div class ="profile_information_left">Full Name</div>
               <div class="profile_information_right"><div id="name_edit"></div></div>
            </div>

            <div class="profile_information_row left">
               <div class ="profile_information_left">Email</div>
               <div class="profile_information_right"><div id="email_edit"></div></div>
               <input id="email_submit" name="email_edit" type="hidden">
            </div>

            <div class="profile_information_row left">
               <div class ="profile_information_left">Birth Date</div>
               <div class="profile_information_right"><div id="birthdate_edit"></div></div>
            </div>

            <div id ="change_avatar" class="profile_information_row left">
               <div class="profile_information_left">Avatar</div>
               <div class="profile_information_right"><input id="avatar_edit" type="file" name="avatar_edit"></div>
            </div>

            <div id ="change_password" class="profile_information_row left">
               <div class="profile_information_left">Password</div>
               <div class="profile_information_right"><input type="password" name="password_edit" placeholder="Change Password"></div>
            </div>

            <div id ="confirm_password" class="profile_information_row left">
               <div class="profile_information_left">Confirm Password</div>
               <div class="profile_information_right"><input type="password" name="confirm_password" placeholder="Confirm Change Password"></div>
            </div>

            <div class="profile_information_row left">
               <input id="edit_profile_button" type="button" value="Edit Profile" onclick="editProfileMode()" class="link_blue_rect">
               <input id="save_profile_button" type="submit" value="Save" class="link_blue_rect">
            </div>
         </form>
      </div>
   </div>
   <div id="profile_right">
      <div class="half_div">
         <div class="headsdeh">Current Tasks</div>
         <ul id="currentTask" class="ul_none">
         </ul>
      </div>
      <div class="half_div">
         <div class="headsdeh">Finished Tasks</div>
         <ul id="finishedTask" class="ul_none">
         </ul>
      </div>
   </div>
</section>

<%@include file="footer.jsp"%>
<script>
   <%!
      public String getParameter(HttpServletRequest request, HttpSession session) {
         if (request.getParameter("username") != null) {
            return (request.getParameter("username"));
         } else {
            return (session.getAttribute("username").toString());
         }
      }
   %>
   checkViewMode("<% out.print(session.getAttribute("username")); %>","<% out.print(getParameter(request, session)); %>");
   getProfileTask("<% out.print(getParameter(request, session));%>");
   getProfileContent("<% out.print(getParameter(request, session));%>");
   setInterval(function(){getProfileTask("<% out.print(getParameter(request, session));%>")}, 1000);
</script>
</body>
</html>