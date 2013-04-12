<!DOCTYPE html>
<%
   if (session.getAttribute("username") == null) {
      response.sendRedirect("../index.jsp");
   }
%>
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
               <a href="dashboard.jsp"> <img src="../img/logo.png" alt=""> </a>
            </div>
            <input id="search_box" type="text" placeholder="Type here to search">
            <div id="header_menu"> 
               <div class="header_menu_button current_header_menu"> <a href="dashboard.jsp"> DASHBOARD </a>   </div>
               <div class="header_menu_button">
                  <%
                     out.print("<img id='header_img' src='../" + session.getAttribute("avatar") + "' />");
                  %>
                  <a href="profile.php">
                     <div id="header_profile">
                        &nbsp;&nbsp;<% out.print(session.getAttribute("username"));%>
                     </div>					 
                  </a> 
               </div>
               <div class="header_menu_button"> <a id="logout" href="../clear.php"> LOGOUT </a> </div>
            </div>

         </div>
      </header>	