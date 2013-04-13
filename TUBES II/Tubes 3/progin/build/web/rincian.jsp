
<%
if (session != null) {
    String username = (String)session.getAttribute("userid");
}
if (session.getAttribute("taskid") == null || session.getAttribute("taskid").equals("") || (
        request.getParameter("idtask") != null && !request.getParameter("idtask").equals(""))) {
    session.setAttribute("taskid", request.getParameter("idtask"));
}
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>Rincian Tugas | DTD</title>
<link href="css/rincian.css" rel="stylesheet" type="text/css" />
<link href="css/search.css" rel="stylesheet" type="text/css" />
<link href="css/mediaqueries.css" rel="stylesheet" type ="text/css" />
<link href='http://fonts.googleapis.com/css?family=Merienda' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Skranji' rel='stylesheet' type='text/css'>
    <!--<script type="text/javascript" language="javascript" src="js/pagination.js"></script>-->
    <script type="text/javascript" language="javascript" src="js/rincian.js"></script>
    
	
</head>
    <!--loadComment(1,'<?php  echo $idtask; ?>')-->
    <body onload="onload()">
    
    
		<div class="header">
    <a href="Dashboard.jsp"><img align="left" src="images/logo.png" width="150" height="50" />
	<h6>Dashboard</a> <p>|</p> <a href="profile.jsp">Profile</a> <p>|</p> <a href="logout.jsp">Logout</a>
        <form id="searchform" onsubmit="return searchByFilter()" method="post" >
   | Search:<input type="search" id="searchquery" name="searchquery" /> 
   Filter: <select id="filtertype" name="filtertype">
        <option value="All" selected>All</option>
        <option value="Username">Username</option>
        <option value="Category">Category</option>
        <option value="Task">Task</option>
   </select>
   <input type="submit" value="GO" />
   
        </form>
        <div class="user">Welcome, 
      <%
      String login = (String) session.getAttribute("userid");
      out.print(login);
      %>
  </div>
	</div>
	<div id ="contentdashboard" class="container"><br>
			<div class="rincian" id="rincian">
                            <p>Curr ID Task: </p> <%= request.getParameter("idtask") %>

			</div>

			<div class="comment">
				<h1 class="judul">Komentar</h1>
                                <textarea name="commentarea" id="commentarea" rows="4" cols="45"></textarea> <br> 
                                <form id="formkomentar"  > 
				
                                    <input type="button" onclick="saveComment()" value="SUBMIT" /> 
                                    <br><br>
                                </form>
                                                <div id="commentpage">
<!--                                <?php
                                $resultcomment = mysqli_query($con, "SELECT * FROM komentar INNER JOIN task ON task.ID=komentar.IDTask");
                                ChromePhp::log($con);
                                ChromePhp::log($resultcomment);
                                while ($rowcomment = mysqli_fetch_array($resultcomment)) {
                                    echo " ".$rowcomment['IDUser']." ".$rowcomment['Waktu']." ".$rowcomment['Isi']." <br>";
                                }
                                ?>-->
				
                                        
                                        </div>
			</div>
		</div>
<script>
function edit(){
	document.getElementById("tag").readOnly=false;
	document.getElementById("deadline").readOnly=false;
	document.getElementById("assignee").readOnly=false;
}
function save()
{
	document.getElementById("tag").readOnly=true;
	document.getElementById("deadline").readOnly=true;
	document.getElementById("assignee").readOnly=true;
}
</script>
<script type="text/javascript" language="javascript" src="js/autocomplete.js"></script>
<script type="text/javascript" src="js/datepickr.min.js"></script>
<script type="text/javascript">			
			new datepickr('datepick2', {
				'dateFormat': 'm/d/y'
			});
</script>
<!--<script type="text/javascript" language="javascript" src="js/pagination.js"></script>-->
</body>
</html>
