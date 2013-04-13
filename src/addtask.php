<!DOCTYPE html>
<?php
	session_start();
	$user = $_SESSION["login"];
	if ($user == ""){
		header("Location:index.php");
	}
?>
<html dir="ltr" lang="en-US">
<head>
	<script>
		function autocompleteuser()
		{
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
			document.getElementById("completeasignee").innerHTML=xmlhttp.responseText;
			}
		  }
		xmlhttp.open("POST","autocompleteuser.php?username="+document.getElementById("assignee1").value,true);
		xmlhttp.send();
		}
	</script>
	<!--[if lt IE 9]>
	<script src="html5.js" type="text/javascript"></script>
	<![endif]-->
	<title>ToDo</title>
	<script src="myscript.js"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css.css" />
</head>

<body>
<?php
	require_once("database.php");
	$con = connectDatabase();

	$resultavatar = mysqli_query($con,"SELECT * FROM user
			WHERE username='$user'");

	$rowavatar = mysqli_fetch_array($resultavatar);
?>
<header>	
			<div id="tes">
			<br></br>
			<a href="profil.php"><img id="avatar" src="<?php echo $rowavatar['avatar']?>"></a>
			<h3 id="username"><a href="profil.php"><?php echo "$user"?></a>
			<h1 id="logo"><a href="dashboard.php"><img src="images/logo2.png"/></a>
			<form name="formsearch" action="search-result.php" method="get">
			<input name="searchquery" size="30" type="text" maxlength="30">
			<select name="filtersearch">
			<option value="0" selected="selected">Semua</option>
			<option value="1">Username</option>
			<option value="2">Judul Kategori</option>
			<option value="3">Task</option></select><img src="images/search-icon.png" onclick='SearchDatabase();'>
			</form>
			<h3 id="logout"><a href="logout.php">Logout</a>
			</div>
	</header>
<div id="page" >
	<header id="branding">
		<hgroup>
			<h1 id="site-title">              <a href="dashboard.php"></a></h1>
			<h2 id="site-description">            </h2>
		</hgroup>

		<nav id="access" role="navigation">
		<ul class="menu">
			<li class="menu-item"><a href="dashboard.php">Dashboard</a></li>
			<li class="menu-item"><a href="profil.php">Profile</a></li>
			
				<ul>
					<li class="menu-item"><a href="index.html">Subpage 1</a></li>
					<li class="menu-item"><a href="index.html">Subpage 2</a></li>
					<li class="menu-item"><a href="index.html">Subpage 3</a></li>
				</ul></li>
		</ul>
		</nav>
		
		
		
	</header>

			<div id="main">
		<div id="primary">
			<div id="content" role="main">
					<article class="post">	
						
						
							<form id="form" action="insertdatabasetask.php" method="post" enctype='multipart/form-data'>
							<strong>New Task </strong> <br />
							<input id="namatask" name="newtask" size="30" type="text" onkeyup="validasinamatask(this.value)" >
							<div id="namatask_salah"></div>
							<br/><br/>
						
							<strong>Deadline</strong> <br />
							<input name="deadline" type="date" >
							<br/><br/>
							
							<strong>Assignee </strong> <br />
							<input id="assignee1" name="assignee1" type="text" size="30" maxlength="20">
							<br/><br/>
							
							<strong>Comment </strong> <br />
							<input name="comment" type="text" size="30" maxlength="20"> 
							<br/><br/>
							
							<strong>Tag </strong> <br />
							<input name="tag" type="text" size="30" maxlength="20" >
							<br/><br/>
							
							
							<p>Upload file:
							<input id="file1" name="file1" type="file" size="40">
							</p>
							<p>Upload file:
							<input id="file2" name="file2" type="file" size="40">
							</p>
							<p>Upload file:
							<input id="file3" name="file3" type="file" size="40">
							</p>
							<input id="btnsubmit" type="submit" name="btnsubmit" value="Add Task">
							</form>
									
					</article>		
				</div>			
			</div>
					
		

		<footer id="colophon">
			<div id="site-generator">
				<p>&copy; <a href="#">AAA</a>-IF3038 Pemrograman Internet 2013 <br />
				</p>
			</div>
		</footer>
	</div>
	
</div>	
</body>
</html>