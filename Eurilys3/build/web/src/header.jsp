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
      <script type="text/javascript" src="../js/header.js"> </script>
      <script type="text/javascript" src="../js/animation.js"> </script>
      <script type="text/javascript" src="../js/navbar.js"> </script>
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
            <input id="search_box" list="suggestion_header" type="text" placeholder="Search..." onkeydown="if (event.keyCode == 13) search()" onkeyup="autoCompleteHeader(this.value)">
            <datalist id="suggestion_header">
            </datalist>
            <select class="left" id="search_box_filter">
               <option value="1" />All
               <option value="2" />User
               <option value="3" />Category
               <option value="4" />Task
            </select>
            <div id="header_menu"> 
               <div class="header_menu_button" id="dashboard_header"> <a href="../index.jsp"> DASHBOARD </a>   </div>
               <div class="header_menu_button" id="profile_header">
                  <%
                     out.print("<img id='header_img' src='../" + session.getAttribute("avatar") + "' />");
                  %>
                  <a href="profile.jsp">
                     <div id="header_profile">
                        &nbsp;&nbsp;<% out.print(session.getAttribute("username"));%>
                     </div>					 
                  </a> 
               </div>
               <div class="header_menu_button"> <a id="logout" href="logout.jsp"> LOGOUT </a> </div>
            </div>

         </div>
      </header>
      <script>currentHeader()</script>