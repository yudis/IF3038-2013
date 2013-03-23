<html>
	<head>
		<title>Shared To Do List</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="favicon.ico">
	</head>
	<body>
		<div id="navigation">
			<img />
			<a>HOME</a>
			<a href="#features">FEATURES</a>
			<a href="#registerform">REGISTER</a>
			<a href="#loginform">LOGIN</a>
		</div>
		<div class="clearall container topcontainer" id="home">
			<div class="videobox">
				<video width="570" height="320" controls>
					<!-- <source src="movie.mp4" type="video/mp4"> -->
				</video>				
			</div>
			<div class="besidevideobox">
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat</p>
				<div class="centered">
					<a href="#registerform" class="cta">REGISTER NOW!</a>
				</div>
			</div>
		</div>
		<div class="clearall container" id="features">
			<h2>Smart Features</h2>
			<div class="box">
				<h3><img src="images/Address_Book.png">Community Driven</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non </p>
			</div>
			<div class="box">
				<h3><img src="images/Battery.png">Intuitive Design</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
			</div>
			<div class="box">
				<h3><img src="images/Camera.png">No Installation</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
			</div>
			<div class="box">
				<h3><img src="images/Cursor-Move.png">Cloud Powered</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non </p>
			</div>
			<div class="box">
				<h3><img src="images/Dashboard.png">Fully Customizable</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
			</div>
			<div class="box">
				<h3><img src="images/dollar.png">API For Developers</h3>
				<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magn nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolt, sunt in culpa qui officia deserunt mollit anim id est laborum</p>
			</div>			
		</div>
		<div class="clearall container" id="registerform">
			<h2>Register Now</h2>
			<form action="#" method="post">
			<fieldset>
				<p>
					<label>Full name <abbr title="Required">*</abbr></label>
					<input value="" id="fullname"
					required="required" aria-required="true"
					pattern="^[a-zA-Z]+ [a-zA-Z]+$"
					title="Your full name (first and last name)"
					type="text" spellcheck="false" size="20" />
				</p>
				<p>
					<label>Email <abbr title="Required">*</abbr></label>
					<input name="email" id="email" value=""
					required="required" aria-required="true"
					pattern="^(([-\w\d]+)(\.[-\w\d]+)*@([-\w\d]+)(\.[-\w\d]+)*(\.([a-zA-Z]{2,5}|[\d]{1,3})){1,2})$"
					title="Your email address"
					type="email" spellcheck="false" size="20" />
				</p>
				<p>
					<label>Username <abbr title="Required">*</abbr></label>
					<input value="" id="username" onchange="checkUsername()"
					required="required" aria-required="true"
					pattern="^[a-zA-Z0-9_]{5,}$"
					title="Your username"
					type="text" spellcheck="false" size="20" />
				</p>
				<p>
					<label>Password <abbr title="Required">*</abbr></label>
					<input value="" id="password" onchange="checkPassword()"
					required="required" aria-required="true"
					pattern="^[- \w\d\u00c0-\u024f]{8,}$"
					title="Your password"
					type="password" spellcheck="false" size="20" />
				</p>
				<p>
					<label>Confirm password <abbr title="Required">*</abbr></label>
					<input value=""  id="confirmpassword" onchange="checkConfirmPassword()"
					required="required" aria-required="true"
					pattern="^[- \w\d\u00c0-\u024f]{8,}$"
					title="Your password (same as above)"
					type="password" spellcheck="false" size="20" />
				</p>			
				<p>
					<label>Date of birth <abbr title="Required">*</abbr></label>
					<input value=""  id="dob" onchange="checkDOB()"
					required="required" aria-required="true"
					pattern="^(((((0[1-9])|(1\d)|(2[0-8]))\/((0[1-9])|(1[0-2])))|((31\/((0[13578])|(1[02])))|((29|30)\/((0[1,3-9])|(1[0-2])))))\/((20[0-9][0-9])|(19[0-9][0-9])))|((29\/02\/(19|20)(([02468][048])|([13579][26]))))$"
					title="Your date of birth"
					type="date" spellcheck="false" size="20" />
				</p>
				
				<output id="list"></output>
				<input type="file" id="files" name="files[]" onchange="CheckFiles()" />				
			</fieldset>
			<fieldset>
			<button id="register" type="submit" onclick="alert('Successfully registered!')">Register</button>
			</fieldset>
			</form>
		</div>
		<div class="clearall container" id="loginform">
			<h2>Member Login</h2>
			<form>
			<fieldset>
				<p>
					<label>Username <abbr title="Required">*</abbr></label>
					<input value="" id="usernamelogin"
					required="required" aria-required="true"
					pattern="^[a-zA-Z0-9_]{5,}$"
					title="Your username"
					type="text" spellcheck="false" size="20" />
				</p>
				<p>
					<label>Password <abbr title="Required">*</abbr></label>
					<input value=""  id="passwordlogin"
					required="required" aria-required="true"
					pattern="^[- \w\d\u00c0-\u024f]{8,}$"
					title="Your password"
					type="password" spellcheck="false" size="20" />
				</p>				
			</fieldset>
			<fieldset>
				<button id="login" onclick="checkLogin(); return false;">Login</button>
			</fieldset>
			</form>
		</div>
		
		<script type="text/javascript" src="validation.js"></script>		
	</body>
</html>