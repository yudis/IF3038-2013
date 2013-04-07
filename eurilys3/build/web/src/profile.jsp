<!DOCTYPE html>
<html>
	<head>
            <link href='../css/mobile.css' rel='stylesheet' type='text/css'>
            <link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
            <link href='../css/wide.css' rel='stylesheet' type='text/css'>
            <link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
            <script type="text/javascript" src="../js/edit_task.js"> </script> 
            <script type="text/javascript" src="../js/animation.js"> </script> 
            <script type="text/javascript" src="../js/catselector.js"> </script> 
            <meta http-equiv="Content-Type" content="text/html;charset=utf-8" >		
            <meta name="viewport" content="width=device-width; initial-scale=1.0">
            <title> Eurilys </title>
	</head>
	
	<body>
            <!-- Web Header -->
            <header>
                <div id="header_container"> 
                    <div class="left">
                            <a href="dashboard.php"> <img src="../img/logo.png" alt="logo"> </a>
                    </div>
                    <input id="search_box" type="text" placeholder="search...">
                    <select id="search_box_filter">
                            <option> All </option>
                            <option> User </option> <!-- username, email, nama lengkap, birthdate -->
                            <option> Category </option>
                            <option> Task </option> <!-- task name, tag, comment -->
                    </select>
                    <div class="header_menu"> 
                            <div id="menu_dashboard" class="header_menu_button"> <a href="dashboard.jsp"> DASHBOARD </a>  </div>
                            <div id="menu_profile" class="header_menu_button current_header_menu">  <a href="profile.jsp"> PROFILE </a> </div>
                            <div id="menu_logout" class="header_menu_button"> <a id="logout" href="../index.jsp"> LOGOUT </a> </div>
                    </div>
                </div>
                <div class="thin_line"></div>
            </header>	

<!-- Web Content -->
<section>
        <%@include file="navigation_bar.jsp"%>
	
	<div id="dynamic_content">
		<div class="half_div">
			<div id="upperprof">
				<img id="mainpp" width="225" src="" alt=""/>
				<div id="namauser"> FULL - NAME </div>
			</div>
			<br/><br/>
                        EMAIL
			<br/>
                        BIRTH DATE

		</div>
		<div class="half_div">
			<div class="half_tall">
                            <div class="headsdeh">Current Tasks</div>
                            <div class='cursorPointer darkBlueLink' onclick=''> current task 1 </div> 
                            <div class='cursorPointer darkBlueLink' onclick=''> current task 2 </div>
			</div>
			<div class="half_tall">
                            <div class="headsdeh">Finished Tasks</div>
                            <div class='cursorPointer darkBlueLink' onclick=''> finished task 1 </div>
                            <div class='cursorPointer darkBlueLink' onclick=''> finished task 2 </div>
                            <div class='cursorPointer darkBlueLink' onclick=''> finished task 3 </div>
			</div>
		</div>
		
	</div>
</section>
		
<%@include file="footer.jsp"%>	