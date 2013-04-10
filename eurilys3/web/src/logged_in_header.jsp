<!DOCTYPE html>
<% 
if (session.getAttribute("fullname") == null) {
    response.sendRedirect("../index.jsp");
} 
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >	
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href='../css/mobile.css' rel='stylesheet' type='text/css'>
        <link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
        <link href='../css/wide.css' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="../js/animation.js"> </script> 
        <title> Eurilys </title>
    </head>
	
    <body>
            <!-- Web Header -->
        <header>
            <div id="header_container"> 
                <div class="left">
                    <a href="dashboard.php"> <img id="logo" src="../img/logo.png" alt="logo"> </a>					
                </div>

                <input id="search_box" autocomplete="off" type="text" onkeyup="showSearchHint(this.value)" placeholder="Search...">
                <div id="txtHint"> </div>

                <select id="search_box_filter">
                    <option value="1"> All </option>
                    <option value="2"> User </option> <!-- username, email, nama lengkap, birthdate -->
                    <option value="3"> Category </option>
                    <option value="4"> Task </option> <!-- task name, tag, comment -->
                </select>				
                <div class="header_menu"> 
                    <div id="menu_dashboard" class="header_menu_button current_header_menu"> <a href="dashboard.jsp"> DASHBOARD </a>  </div>
                    <div id="menu_profile" class="header_menu_button">  <a href="profile.jsp"> PROFILE </a> </div>
                    <div id="menu_logout" class="header_menu_button"> <a id="logout" href="../index.jsp"> LOGOUT </a> </div>
                </div>
            </div>
            <div class="thin_line"></div>
        </header>