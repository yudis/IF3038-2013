<html xmlns="http://www.w3.org/1999/xhtml">

<?php
	session_start();
?>

<head>
	<title>To-Do: Make a To-Do List!</title>
	<link href="css/style2.css" rel="stylesheet" type="text/css" />
	<meta charset="utf-8" />
	<script type="text/javascript" src="login.js"></script>
</head>

<body>
	<header>
		
		<a href="index.php" class="stuts"><img src="images/todolistss.jpg"></a>
		
		<?php
			if(!isset($SESSION['uname'])){
			echo"
				<div class='panel'>
					<ul>
						<li><label for='username'>Username: </label>
						<input type='text' id='username' value=''/></li>	
						<li><label for='password'>Password: </label>
						<input type='password' id='password' value=''/></li>
						<li><input id='loginbut' name='login' type='submit' value='Log In' onclick='Login();'/></li>
					</ul>
				</div>";
			} else {
				echo "";
			}
		?>
		
				
		<div id="sign">
		<?php
			if (!isset($_SESSION['uname'])){
				echo "Hello, Stranger! Do you want to <h2><a href='registrasi.php'>Register?</a></h2>";
			}
			else{
				echo "Welcome home <a href='halamanprofil.php'>".$_SESSION['uname']. "</a>! Make a To-Do List!<a href='logout.php'> Logout</a>";
			}
		?>
		</div>
	
	</header>

	<div class="main">
		<a href="registrasi.php" id="register">Sign Up For Free </a>
	</div>

</body>
</html>