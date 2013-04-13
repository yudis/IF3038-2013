<?php
	session_start();
	$msg = "";
	if (!isset($_SESSION["infologin"])){
		$msg = "";
	} else {
		$msg = $_SESSION["infologin"];
	}
?>
<html>
<head>
	<title>ToDo</title>
	<script src="myscript.js"></script>
	<script src="ajax_framework.js" language="javascript"></script>
	<link rel="stylesheet" type="text/css" media="all" href="css(2).css" />
</head>

<body>

<header >	
			<div id="tes">
			<br></br>
			<h1 id="logo"><a href="dashboard.html"><img src="images/logo2.png"/></a>
			</div>
</header>
						<div id="logincontent">
						 <div id="formlogin">
							<form action="login.php" id="login" name="login" method="post">
							<p>Username  <input type="text" name="userid"/></p>
							<p>Password  <input type="password" name="pass"/></p>
							<input type="submit" value="Login"/>
							<input type="reset" value="Cancel"/>
							<div id="salah_login"><?php echo $msg;?></div>
							</form>
						</div>
						
					
						 
						 <div id="video">
							<img src="images/foto.png"/>
						</div>
						</div>
						
			<div id="logincontent">Not a member yet?<a href="register.php"> Register now!</a></div>		

		<footer id="colophon">
			<div id="site-generator">
				<p>&copy; <a href="#">AAA</a>-IF3038 Pemrograman Internet 2013 <br />
				</p>
			</div>
		</footer>
		
</body>
</html>