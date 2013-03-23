<?php
	session_start();
	include('config.php');
	$username = $_SESSION['username'];
	if(!isset($_SESSION['username'])) {
		header("Location:index.php");
	}
?>
<!DOCTYPE html>
<html>

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
	xmlhttp.open("GET", "setDone.php?t="+namaTugas, true);
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
	xmlhttp.open("GET", "setToUndone.php?t="+namaTugas, true);
	xmlhttp.send();
}
</script>

<head>
<title>Dashboard - TargET</title>
<link rel="stylesheet" type="text/css" href="dashboard.css">
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon">
<link rel="icon" href="favicon.ico" type="image/x-icon">
</head>

<body>
	
	<header>
		<div id="header">
			<div id="topbar">
				<div id="topbar_logo">
					<a href="dashboard.php"><img id="logo" src="img/logo.png"></a>
				</div>
				<div id="topbar_logo2">
					<img id="logo2" src="img/namalogo.png">
				</div>
				<div id="topbar_border"></div>
				<div id="topbar_dashboard">
					<nav>
						<ul>
							<li> <a class="active" href="#"> Dashboard </a> </li>
							<li> <a href="profil.php">Profil</a> </li>
							<li> <a href="index.php">Logout</a> </li>
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
								
				<div id="avatar">
					<a href="profil.php">
					<img id="ava" src="img/fotoprofil.jpg">
					Username</a>
				</div>
			</div>
		</div>
	</header>

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
	
	<section>
	<div id="content">
		<div id="categoryMenu">
			<p id="cattitle">Category</p></br>
			<div id="catList">
				<?php
					getAllCat();
				?>
			</div>
			
			<div id="newcat">
				<a href="newtask.php" onclick="return popitup('newcat.php')"> <img id="newtaskbutton" src="img/plus.png" alt="plusbutton" width="32" height="32" ></img>  </a>
				<p id="newtasktext">NEW CATEGORY</p>
			</div>
		</div>
		
		<div id="taskSpace">
			<div id="alltask">
				<?php
					getAllTask();
				?>
			</div>
		</div>
	</div>
	</section>
	
	<footer id="footer_wrap">
		<hr>TargET &#169 2013
	</footer>
	
	
</body>

</html>