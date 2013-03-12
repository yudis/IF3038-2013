<!DOCTYPE html>
<html>
	<head>
		<title>Shared to-do List</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<script>
			function login() {
				if (document.getElementById("login1").value == 'meckyr' && document.getElementById("login2").value == 'meckyr') {
					self.location='dashboard.php';
				}
			}
			var chkusername = false;
			var chkpassword = false;
			var cnfrmpassword = false;
			var chkfullname = false;
			var chkemail = false;
			var chkavatar = false;
			function hide_submit_button() {
				document.getElementById("submit").style.display = 'none';
			}
			function show_submit_button() {
				if(chkusername == true && chkpassword == true && cnfrmpassword == true && chkfullname == true && chkemail == true && chkavatar == true) {
					document.getElementById("submit").style.display = 'block';
				}
			}
			function check_username() {
				if (document.getElementById("username").value.length > 4 && document.getElementById("username").value != document.getElementById("password").value) {
					chkusername = true;	
					show_submit_button();
				} else {
					chkusername = false;
					hide_submit_button();
				}
			}
			function check_password() {
				if (document.getElementById("password").value.length > 7 && document.getElementById("password").value != document.getElementById("username").value && document.getElementById("password").value != document.getElementById("email").value) {
					chkpassword = true;
					show_submit_button();
				} else {
					chkpassword = false;
					hide_submit_button();
				}
			}
			function confirm_password() {
				if (document.getElementById("password").value == document.getElementById("password2").value) {
						cnfrmpassword = true;
						show_submit_button();
				} else {
					cnfrmpassword = false;
					hide_submit_button();
				}
			}
			function check_full_name() {
				var str = document.getElementById("fullname").value;
				var idx = str.indexOf(' ');
				if (idx > 0 && idx < str.length - 1) {
					var chr1 = str.charAt(idx - 1);
					var chr2 = str.charAt(idx + 1);
					if (chr1 != ' ' && chr2 != ' ') {
						chkfullname = true;
						show_submit_button();
					} else {
						chkfullname = false;
						hide_submit_button();
					}
				} else {
					chkfullname = false;
					hide_submit_button();
				}
			}
			function check_email() {
				var str = document.getElementById("email").value;
				var at = str.indexOf('@');
				var dot = str.lastIndexOf('.');
				if (at > 0 && (dot - at) > 1 && dot < str.length - 2) {
					chkemail = true;
					show_submit_button();
				} else {
					chkemail = false;
					hide_submit_button();
				}
			}
			function check_avatar() {
				var str = document.getElementById("avatar").value;
				var ext = str.substring(str.lastIndexOf('.') + 1, str.length);
				if (ext.toLowerCase() == "jpeg" || ext.toLowerCase() == "jpg") {
					chkavatar = true;
					show_submit_button();
				} else {
					chkavatar = false;
					hide_submit_button();
				}
			}
			function show_register_form() {
				document.getElementById("login-form").style.display = "none";
				document.getElementById("login-bottom").style.display = "none";
				document.getElementById("register-form").style.display = "block";
				document.getElementById("register-bottom").style.display = "block";
			}
			function show_login_form() {
				document.getElementById("register-form").style.display = "none";
				document.getElementById("register-bottom").style.display = "none";
				document.getElementById("login-form").style.display = "block";
				document.getElementById("login-bottom").style.display = "block";
			}
		</script>
	</head>
	<body onLoad="hide_submit_button();show_register_form();">
		<div id="index-body">
		<div id="left-body">
			<!--Website logo + tagline-->
			<div id="header-title"><a href="dashboard.html"><img src="title.png" width="250px" height="80px"/></a></div><br />
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
			<form>
				<!--Username (min. 5 characters != password)-->
				<input type="text" id="username" onKeyUp="check_username()"/><br />
				<!--Password (min. 8 characters) != username and email-->
				<input type="password" id="password" onKeyUp="check_password()"/><br />
				<!--Confirm password == password-->
				<input type="password" id="password2" onKeyUp="confirm_password()"/><br />
				<!--Full name (min. 2 spaces between 2 characters)-->
				<input type="text" id="fullname" onKeyUp="check_full_name()"/><br />
				<!--Birth date (drop down, year >= 1955)-->
				<select id="day">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
					<option value="13">13</option>
					<option value="14">14</option>
					<option value="15">15</option>
					<option value="16">16</option>
					<option value="17">17</option>
					<option value="18">18</option>
					<option value="19">19</option>
					<option value="20">20</option>
					<option value="21">21</option>
					<option value="22">22</option>
					<option value="23">23</option>
					<option value="24">24</option>
					<option value="25">25</option>
					<option value="26">26</option>
					<option value="27">27</option>
					<option value="28">28</option>
					<option value="29">29</option>
					<option value="30">30</option>
					<option value="31">31</option>
				</select>&nbsp;
				<select id="month">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
					<option value="5">5</option>
					<option value="6">6</option>
					<option value="7">7</option>
					<option value="8">8</option>
					<option value="9">9</option>
					<option value="10">10</option>
					<option value="11">11</option>
					<option value="12">12</option>
				</select>&nbsp;
				
				<?php 
					$i = 0;
					echo "<select id=\"year\">";
					for($i = 1955 ; $i < 2013 ; $i++){
						echo "<option value=\"$i\">$i</option>";
					}
					echo "</select>";
				?>
				<br />
				<!--email, validation:
					min. 1 character before @
					min. 1 character between @ and .
					min. 2 characters after .
					!= password-->
				<input type="text" id="email" onKeyUp="check_email()"/><br />
				<!--Avatar, input file, extension == .jpg or .jpeg-->
				<input type="file" id="avatar" onChange="check_avatar()"/><br />
				<!--Register button, disabled if invalid input exists-->
				<input id="submit" type="button" value="Sign Up"/>
			</form><br /></div>
			<div id="login-bottom">Don't have an account? <a href="#" onCLick="show_register_form()">Register</a></div>
			<div id="register-bottom">Already have a account? <a href="#" onClick="show_login_form()">Sign in</a></div>
		</div>
		<div id="big-logo"><a href="dashboard.html"><img src="logo.png" width="400px" height="240px"/></a></div>
		</div>
	</body>
</html>
