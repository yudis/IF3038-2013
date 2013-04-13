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
	$resultavatar = mysqli_query($con,"SELECT avatar FROM user
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
			
		</ul>
		</nav>
		
		
		
	</header>

			<div id="main">
		<div id="primary">
			<div id="content" role="main">
					<article class="post">	
					<form action="editprofil.php" method="post">
					<?php
						require_once("database.php");
							$con= connectDatabase();
						  
						$result = mysqli_query($con,"SELECT * FROM user WHERE username = '".$user."'");

						while($row = mysqli_fetch_array($result))
						  {
							  echo "USERNAME	: ";
							  echo $row['username'];
							 echo "<PRE></PRE>";
							  echo "EMAIL		: ";
							  echo $row['email'];
							  echo "<PRE></PRE>";
							  echo "FULLNAME	: ";
							  echo $row['fullname'];
							  echo "<PRE></PRE>";
							  echo "BIRTHDATE	: ";
							  echo $row['tanggalLahir'];
							 echo "<PRE></PRE>";
							  echo "<img src=".$row['avatar'].">";
							  echo "<PRE></PRE>";
							
							echo "UNCOMPLETED TASKS	:";
							 echo "<PRE></PRE>";
							$result2 = mysqli_query($con,"SELECT task.namaTask FROM task INNER JOIN usertotask ON task.namaTask = usertotask.namaTask WHERE task.status = 'undone'");
							while($row2 = mysqli_fetch_array($result2)){
								$undonetask=$row2[0];
								echo $undonetask;
								echo "<PRE></PRE>";
							}
							
							echo "COMPLETED TASKS	:";
							 echo "<PRE></PRE>";
							
							$result3 = mysqli_query($con,"SELECT task.namaTask FROM task INNER JOIN usertotask ON task.namaTask = usertotask.namaTask WHERE task.status = 'done'");
							while($row3 = mysqli_fetch_array($result3)){
								$donetask=$row3[0];
								echo $donetask;
								echo "<PRE></PRE>";
							}
						  }
							
			
					?>
					
					
					<input type="submit" name="btnsubmit" value="EDIT PROFILE">
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