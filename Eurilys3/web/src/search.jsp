<%@include file="header.jsp"%>
<script type="text/javascript" src="../js/search.js"> </script>

<!-- Web Content -->
<section>
   <div id="navbar">
      <div id="short_profile">
         <%
            out.print("<img class='left' id='profile_picture' src='../"
                    + session.getAttribute("avatar") + "' />");
         %>
         <div id="profile_info" class="left">
            Welcome,
            <br>
            <a href="profile.jsp"><% out.print(session.getAttribute("name"));%></a>
         </div>
      </div>
      <div id="nav_search">
         <input placeholder="Search..." type="text" class="left" id="searchBox" list="suggestion" onkeyup="autoComplete(this.value)" onkeydown="if (event.keyCode == 13) document.getElementById('searchBox_button').click()">
         <div class="link_blue_rect" id="searchBox_button" class="left" onclick="getResult()">Search</div>
         <datalist id="suggestion">
         </datalist>
         <br><br>
         <div id="filter_label"><b>Filter Search</b></div>
         <input type="checkbox" id="username_search" checked="checked">User Name<br>
         <input type="checkbox" id="category_search" checked="checked">Judul Kategori<br>
         <input type="checkbox" id="task_search" checked="checked">Judul Task dan Tag<br> 					
      </div>
   </div>
   <div id="dynamic_content">
      <div class="search_result">
         <div class="search_title">Category</div>
         <div class="search_view left">
            <div class="left dynamic_content_left">Name</div>
            <div class="left dynamic_content_right">Himpunan</div>
         </div>
      </div>
      <div class="search_result">
         <div class="search_title">Task</div>
      </div>
   </div>

</div>
</section>

<%@include file="footer.jsp"%>

</body>
<script>
   window.onpopstate = function(){
      onPopState();
   }
</script>
</html>