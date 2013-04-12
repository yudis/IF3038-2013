<%-- 
    Document   : profile
    Created on : Apr 12, 2013, 2:52:14 PM
    Author     : Sonny Theo Thumbur
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SharedToDoList | profil</title>
        <link rel="stylesheet" type="text/css" href="style/style.css"></link>
        <script LANGUAGE="Javascript" src="script/script.js"></script>
    </head>
    <body>
        <!--<h1>Hello World!</h1>-->
        <form action="Suggestion" method="get">
    	<div id="header">
            <a href="dashboard.jsp"><img id="logo" src="res/logo1.png" alt="to-do list"></img></a>
            <a id="dashboardLink" href="dashboard.jsp">dashboard</a>
            <input id="searchForm" type="text" name="keyword" onkeyup="showHint(this.value)" placeholder="search"></input>
            <select id="filter" name="filter">
                <!--<option selected>Select Filter ...</option>-->
                <option>all</option>
                <option>user</option>
                <option>category</option>
                <option>task</option>
            </select>
            <input id="submitForm" type="submit" name="search" value="search" onclick="toSearchResult(searchForm.value,filter.value)">
	    <a href="profile.jsp"><img id="profile" src="res/profileLogo.png" onclick="keProfil()";/></a>
	    <a id="logout" href="logout.php">Log Out</a>
	    <div class="suggest">Suggestion : <span id="textHint"></span></div>
        </div>
	</form>
        
        <div id="spasi">
        </div>
        
        <div id="photoSpace">
        	<div id="userPhoto">
                <%
                    String curUser = (String) session.getAttribute("username");
//                        out.println(curUser);
                %>
                <img id="user" src="server/<%= curUser %>.png" alt="userPhoto"/>
		<div id="photoUploader">
		    <label><em>Upload new Avatar : </em></label>
		    <form method="post" action="php/UploadFile.php" enctype="multipart/form-data" name="uploadImage">
			<input id="fileUpload" type="file" name="image"></input>
			<input type="hidden" name="MAX_FILE_SIZE" value="<?php echo MAX_FILE_SIZE; ?>"/>
			<input type="submit" value="Upload new Avatar"/>
		    </form>
		</div>
            </div>
        </div>
    </body>
</html>
