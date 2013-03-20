<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! Where Bounty Hunters Meet</title>
		<link rel="stylesheet" type="text/css" href="css.css">
        <link href="mediaqueries.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0">
    </head>
    <body onload="SlideShow()">
        <div id="header">
		<div id="logo">
			<img src="img\Logo_Small2.png" alt="Logo & Tagline" onclick="Redirect();"></img>
		</div>
		<div id="login">
			Username &nbsp <input type="text" id="logusername"> &nbsp &nbsp 
        		Password &nbsp <input type="password" id="logpassword"> &nbsp &nbsp 
				<div class="logbutton" onclick="Login();">Login</div>
                </div>
	</div>
        <div id="frontpage">
            <div id="register">
                <b>Sign yourself up now!</b><br>
                Username<br>
                Name<br>
                Date of birth<br>
                Password<br>
                Confirm Password<br>
                Email<br>
                Avatar<br>
                <div id="regbutton" onclick="Submit();">Register</div>
            </div>
            <div id="inputregister">
				<form action="register.php" method="post">
					<input type="text" id="regusername" pattern="^.{5,}$" required><img id="valid1" src=""><br>
					<input type="text" id="regname" pattern="^.+ .+$" required><img id="valid2" src=""><br>
					<input type="date" id="regdate"><br>
					<input type="password" id="regpassword1" pattern="^.{8,}$" required><img id="valid3" src=""><br>
					<input type="password" id="regpassword2" pattern="^.{8,}$" required><img id="valid4" src=""><br>
					<input type="text" id="regemail" pattern="^.+@.+\...+$" required><img id="valid5" src=""><br>
					<input type="file" id="regfile" onchange="checkImage();"><img id="valid6" src=""><br>
				</form>
            </div>
            <img src="img\register.png" id="fpposter" alt="Poster"></img>
            <img src="img\Logo_Big2.png" id="fplogo" alt="BigLogo"></img>
            <img src="img\parchment.jpg" id="fpbackground" alt="Background"></img>
        </div>
    <script type="text/javascript" src="script.js"></script>
	</body>
</html>
