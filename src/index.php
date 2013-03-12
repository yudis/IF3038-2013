<?php 
	session_start();
	if (isset($_SESSION["userlistapp"]))
		header("Location: page/dashboard.php");
?>
<!DOCTYPE html>
<html>
	<head>
		<title>Shared to-do List</title>
		<link rel="stylesheet" type="text/css" href="page/style.css">
		<script type="text/javascript" src="page/index.js"></script>
		<script src="page/calendar.js"></script>
		<link href="page/calendar.css" rel="stylesheet">
	</head>
	<body onLoad="hide_submit_button();show_register_form();">
		<div id="index-body">
		<div id="left-body">
			<!--Website logo + tagline-->
			<div id="header-title"><img src="image/title.png" width="250px" height="80px"/></div><br />
			<!--Site explanantion (image, video, etc)-->
			<div id="web-description">Browse, Manage and Share Your Task!<br /></div>
		</div>
		<div id="right-body">
			<div id="big-name">Get Started<br /></div>
			<!--Login form-->
			<div id="login-form">
			<div id="input-description">
				Username: <br>
				Password: <br>
			</div>
			<form>
				<input type="text" id="login1" /><br />
				<input type="password" id="login2" /><br />
				<input type="button" value="Sign In" onClick="login()">
			</form>
			</div>
			<!--Register form-->
			<div id="register-form">
			<div id="input-description">
				Username: <br>
				Password: <br>
				Confirm: <br>
				Full name: <br>
				BirthDate&nbsp; <br>
				Email: <br>
				Your Avatar: <br>
			</div>
			<form enctype="multipart/form-data" method="post" action="php/registration.php">
				<!--Username (min. 5 characters != password)-->
				<input type="text" id="username" name="textUsername" onKeyUp="check_username()"/><br />
				<!--Password (min. 8 characters) != username and email-->
				<input type="password" id="password" name="textPassword" onKeyUp="check_password()"/><br />
				<!--Confirm password == password-->
				<input type="password" id="password2" name="textPassword2" onKeyUp="confirm_password()"/><br />
				<!--Full name (min. 2 spaces between 2 characters)-->
				<input type="text" id="fullname" name="textFullName" onKeyUp="check_full_name()"/><br />
				<!--Birth date (drop down, year >= 1955)-->
				<input type="text" class="calendarSelectDate" name="textBirthday"/><div id="calendarDiv"></div>
				<br />
				<!--email, validation:
					min. 1 character before @
					min. 1 character between @ and .
					min. 2 characters after .
					!= password-->
				<input type="text" id="email" name="textEmail" onKeyUp="check_email()"/><br />
				<!--Avatar, input file, extension == .jpg or .jpeg-->
				<input type="file" id="avatar" name="textAvatar" onChange="check_avatar()"/><br />
				<!--Register button, disabled if invalid input exists-->
				<input id="submit" type="submit" value="Sign Up"/>
				<div id="warning-message"></div>
			</form><br /></div>
			<div id="login-bottom">Don't have an account? <a href="#" onCLick="show_register_form()">Register</a></div>
			<div id="register-bottom">Already have a account? <a href="#" onClick="show_login_form()">Sign in</a></div>
		</div>
		<div id="big-logo"><a href="dashboard.html"><img src="image/logo.png" width="400px" height="240px"/></a></div>
		</div>
	</body>
</html>
