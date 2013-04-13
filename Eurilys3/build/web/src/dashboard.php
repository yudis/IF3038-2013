<!DOCTYPE html>
<html>
   <head>
      <link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
      <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
      <script type="text/javascript" src="../js/animation.js"> </script>
      <script type="text/javascript" src="../js/catselector.js"> </script>
      <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
      <title> Eurilys </title>
   </head>

   <body>
      <!-- Web Header -->
      <header>
         <div id="header_container"> 
            <div id="logo_container">
               <a href="dashboard.php"> <img src="../img/logo.png" alt=""> </a>
            </div>
            <input id="search_box" type="text" placeholder="Type here to search">
            <div id="header_menu"> 
               <div class="header_menu_button current_header_menu"> <a href="dashboard.php"> DASHBOARD </a>   </div>
               <div class="header_menu_button">  
                  <a href="profile.php">
                     <div id="header_profile">
                        &nbsp;&nbsp;PROFILE
                     </div>					 
                  </a> 
               </div>
               <div class="header_menu_button"> <a id="logout" href="../clear.php"> LOGOUT </a> </div>
            </div>

         </div>
      </header>	


      <!-- Web Content -->
      <section>
         <div id="navbar">
            <div id="short_profile">
               <div id="profile_info" class="left">
                  Welcome,
                  <br>
                  <a href="profile.php">Anugrah Sulaeman</a>
               </div>
            </div>
            <div id="category_list">
               <div class="link_blue_rect" id="category_title"><a href="#" onclick="allCategory()">All Categories </a></div>
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
                     <div id="add_category_button" class="link_red" onclick="add_category()"> Add </div>
                  </div>
               </div>
            </div>
         </div>
         <div id="dynamic_content">
         </div>
      </section>

      <!-- Footer Section -->
      <footer>
         <div id="footer_container"> 
            About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
            <br>
            Eurilys 2013
         </div>
      </footer>
   </body>
</html>