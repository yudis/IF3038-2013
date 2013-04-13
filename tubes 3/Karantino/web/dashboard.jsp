<%@page import="progin.UserBean"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection" %>


<%@ page language="java" 
    contentType="text/html; charset=windows-1256"
    pageEncoding="windows-1256"
%>

<!DOCTYPE html>
<html>
<script>
    function getTask(cat){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById('taskSpace').innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "showTask?catname="+cat, true);
	xmlhttp.send();
	}
        
        function showNewCat(){            
            var url = "newcat.jsp"
            window.open(url,'ReasonWindow','height=500 width=700');
        }
        
        function setToDone(namaTugas){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById(namaTugas).innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "setDone?t="+namaTugas, true);
	xmlhttp.send();
}

function setToUndone(namaTugas){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById(namaTugas).innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "setUndone?t="+namaTugas, true);
	xmlhttp.send();
}
function delTask(taskName,catName){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById('taskSpace').innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "delTask?taskname="+taskName+"&catname="+catName, true);
	xmlhttp.send();
}
</script>
<%--
<script>
var strWindowFeatures = "menubar=no,location=no,resizable=no,scrollbars=no,status=no,height=500,width=500";
function display(id)
{
document.getElementById(id).style.display = "block";
}
function hidealltask()
{
document.getElementById("task1").style.display = "none";
document.getElementById("task2").style.display = "none";
document.getElementById("task3").style.display = "none";
}
function popitup(url) {
	newwindow=window.open(url,'name',strWindowFeatures);
	if (window.focus) {newwindow.focus()}
	return false;
}

function changeAvatar(){
	document.get.ElementById("avatar").innerhtml="coba";
}
changeAvatar();

function getTask(cat){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById('taskSpace').innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "showTask.php?t="+cat, true);
	xmlhttp.send();
	}
	
function delTask(taskName){
	var xmlhttp;
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		document.getElementById('taskSpace').innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET", "deltask.php?t="+taskName, true);
	xmlhttp.send();
}
</script>
--%>

<head>
<title>Dashboard - TargET</title>
<link rel="stylesheet" type="text/css" href="dashboard.css">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="icon" href="favicon.ico" type="image/x-icon">

                <%
                    Connection connection = null;
                    try{
                        String url = "jdbc:mysql:" + "//localhost:3306/progin_405_13510074";
                        Class.forName("com.mysql.jdbc.Driver");
                        connection = DriverManager.getConnection(url, "root", "");
                        Statement s = connection.createStatement();
                        if (s != null)
                            pageContext.setAttribute("statement", s);
                    } catch (ClassNotFoundException e1) {
                        // JDBC driver class not found, print error message to the console
                        e1.printStackTrace();
                    } catch (Exception e3){
                        e3.printStackTrace();
                    }
                %>
</head>

<body>
	
	<header>
		<div id="header">
			<div id="topbar">
				<div id="topbar_logo">
					<a href="dashboard.jsp"><img id="logo" src="img/logo.png"></a>
				</div>
				<div id="topbar_logo2">
					<img id="logo2" src="img/namalogo.png">
				</div>
				<div id="topbar_border"></div>
				<div id="topbar_dashboard">
					<nav>
						<ul>
							<li> <a class="active" href="#"> Dashboard </a> </li>
							<li> <a href="profil.jsp">Profil</a> </li>
							<li> <a href="index.jsp">Logout</a> </li>
						</ul>
					</nav>
				</div>
				<div id="topbar_search">  					
					<input type="search" results="5" placeholder="search"/>
					<select>
						<option value="all">All</option>
						<option value="username">Username</option>
						<option value="kategori">Kategori</option>
						<option value="task">Task</option>
					</select>
				</div>
				  <div id="showUser">
                                    <% 
                                        UserBean user1 = ((UserBean)session.getAttribute("currentSessionUser"));
                                    %>
                                    User: <%= user1.getUsername() %>
                                </div>
			</div>
		</div>
	</header>
    
        <%--
	<?php
		// mysql_connect("localhost","root","") or die ("Cannot connect to MySQL");
		// mysql_select_db("progin") or die ("Cannot connect to progin database");
		
		function cekAssigner($user,$taskname){
			$name = mysql_query("SELECT username FROM asigner WHERE namatugas='$taskname'");
			while($row = mysql_fetch_array($name)){
				if($row['username']==$user){
					echo "<button onclick=\"delTask('" . $taskname . "')\">Delete Task</button>";}
			}
		}		
		
		function showTag($taskName){
			$tag = mysql_query("SELECT tag FROM tag WHERE namatugas='$taskName'");
			while($row = mysql_fetch_array($tag)){
				echo $row['tag'] . "; ";
				}
		}
		
		function getAllCat(){
			$username = $_SESSION['username'];
			$result = mysql_query("SELECT *  FROM category");
			while($row = mysql_fetch_array($result)){
				if($username == $row['username']){
					echo "<p class=\"categorynormal\" onclick=\"getTask('" . $row['kategori'] ."')\" onmouseover=\"this.className='categoryhighlight'\" onmouseout=\"this.className='categorynormal'\">". $row['kategori'] ."</p>\n";
				}
			}
		}
		
		function getAllTask(){
			$username = $_SESSION['username'];
			$result = mysql_query("SELECT *  FROM tugas where username='$username'");
			while($row = mysql_fetch_array($result))  {
					echo "<div id ='taskbox'>
						<div id='taskimg'>
							<img src='img/note1.png' alt='Logo'> </br>
						</div>
						<div id='taskdesc'>
							<div id='". $row['namatugas'] . "'>
							<a href='rinciantugas.php?username=" .$username. "&namatugas=" .$row['namatugas']. "'>" . $row['namatugas'] . "</a> 
							<p> Deadline : " . $row['deadline'] ."</p>
							<p> Tag : " ; showTag($row['namatugas']) ; echo "</p>
							<p> Status : "; if ($row['status']==0) {echo "not done";} else {echo "done";} ;
							if($row['status']==0){echo "<br> <button onclick=\"setToDone('" . $row['namatugas'] . "')\">Done!</button>";} else {echo "<br> <button onclick=\"setToUndone('" . $row['namatugas'] . "')\">Set To Undone</button>";};
							cekAssigner($username,$row['namatugas']);
							echo "</div> </div>";
			}
			

		}
	?>
        --%>
	
	<section>
	<div id="content">
                <%             
                    request.getSession(false).setAttribute("user",user1.getUsername());
                    String user = (String) request.getSession(false).getAttribute("user");
                %>
		<div id="categoryMenu">
			<p id="cattitle">Category</p></br>
			<div id="catList">
                                <%
                                    try {
                                        Statement s1 = (Statement) pageContext.getAttribute("statement");
                                        String sqlCommand = "SELECT kategori FROM category WHERE username='" + user + "'";
                                        ResultSet rs = s1.executeQuery(sqlCommand);
                                        String categoryname = "";
                                        while(rs.next()){
                                            categoryname = rs.getString(1);
                                            out.print("<p class=\"categorynormal\" onclick=\"getTask('" + categoryname + "')\" onmouseover=\"this.className='categoryhighlight'\" onmouseout=\"this.className='categorynormal'\">" + categoryname + "</p>\n");
                                        }
                                        }
                                    catch(Exception e){                                        
                                    }
                                %>
			</div>
			
			<div id="newcat">
				<a href="" onclick="showNewCat()"> <img id="newtaskbutton" src="img/plus.png" alt="plusbutton" width="32" height="32" ></img>  </a>
				<p id="newtasktext">NEW CATEGORY</p>
			</div>
		</div>
		
		<div id="taskSpace">
			<div id="alltask">
                                <%
                                Statement s1 = (Statement) pageContext.getAttribute("statement");
                                String sqlCommand = "SELECT *  FROM tugas WHERE username='" + user + "'";
                                ResultSet rs = s1.executeQuery(sqlCommand);
                                String namatugas="";
                                String deadline="";
                                String status="";
                                String setToButton="";
                                String delButton="";
                                String kategori="";
                                while(rs.next()){
                                    /*
                                    String multitag="";
                                    try{
                                    //retieve multitag value
                                    
                                    Statement s2 = (Statement) pageContext.getAttribute("statement");

                                    String sqlTag = "SELECT tag FROM tag WHERE username='"+user+"' AND namatugas='"+namatugas+"'";
                                    ResultSet rsTag = s2.executeQuery(sqlTag);
                                    if(rsTag!=null){
                                        while(rsTag.next()){
                                            multitag= multitag + rsTag.getString(1) + ";";
                                    }
                                    }}
                                    catch(Exception e){
                                        out.println("masuk ke exception");
                                    }*/
                                    namatugas = rs.getString(2);
                                    deadline = rs.getString(3);
                                    kategori = rs.getString(4);
                                    delButton = "<button onclick=\"delTask('"+namatugas+"','"+kategori+"')\">delete</button>";
                                    if (rs.getInt(5)== 0){
                                        status="Not Done";
                                        setToButton="<br><button onclick=\"setToDone('"+namatugas+"')\">Done!</button>";
                                    } else {status = "Done";
                                        setToButton="<br><button onclick=\"setToUndone('"+namatugas+"')\">Set to Undone!</button>";
                                    }
                                    
                                    out.print("<div id ='taskbox'><div id='taskimg'><img src='img/note1.png' alt='Logo'> </br></div> <div id='taskdesc'><div id='"+namatugas+"'><p><a href=\"toRincianTugas?value1=" + namatugas +"\">" + namatugas + "</a></p><p>Deadline:"+ deadline +"</p><p>Status: "+ status + setToButton + delButton +"</p></div></div></div>\n");
                                }
                                %>
			</div>
		</div>               
	</div>
	</section>
	
	<footer id="footer_wrap">
		<hr>TargET &#169 2013
	</footer>
	<%
        connection.close();
        %>
	
</body>

</html>