<?php

session_start();

if (ISSET($_SESSION['username']))
	{
		header("location: dashboard.php");
	}
	
?>

<html>
    <head>
        <title> Next : You Can't Do it Alone</title>
		<link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar.js" > </script>
		<script src="js/utama.js"></script>
        <link rel="stylesheet" href="css/css.css">
    </head>
    <body onload="isAlreadyLogin()">
        <div class="header">
            <div id="logo">
                <img src="pict/logo.png">
            </div>
            <div id="login">  
                <section class="loginform cf">
		<form name="login"  method="post" accept-charset="utf-8" >
			<ul>
				<li>
					
					<input class="small" id="username" type="userID" name="userId" placeholder="yourID" required>
				</li>
				<li>
					<input class="small" id="password" type="password" name="password" placeholder="password" required></li>
				<li>
					<input class="xsmall" type="button" value="Login" onclick="logine()">
				</li>
			</ul>
		</form>
	</section>
            </div>
        </div>
        <div class="main">
            <div id="slider">
                <img src="pict/task.png">
            </div>
            <div id="registrasi">
                <div id="regawal">
                    <div id="were">
                    </div>
                    <div id="poctreg" onclick="ShowAkhir()" >
                    </div>
                    <div id="textreg" on>
                        try and be awesome 
                    </div>
                </div>
                 <div id="regakhir" style="DISPLAY: none">
                    <div id="formreg">
                    <form name="registration" Method="POST" enctype="multipart/form-data" action="signup_process.php">
                        <label>username</label>
                        <input name="username" type="text" placeholder="username"  onkeyup="user_validating()"  />
						<img src="pict/blank.png" alt="icon2" id="usericon"  />
                        <label>password</label>
                        <input name="password" type="password" placeholder="password" onkeyup="pass_validating()"  />
                        <img src="pict/blank.png" alt="icon3" id="passicon" />
						<label>confirm password</label>
                        <input name="confirmpass" type="password" placeholder="confirm password" onkeyup="conf_validating()"   />
						<img src="pict/blank.png" alt="icon4" id="conficon" />
                        <label>nama lengkap</label>
                        <input name="namaleng" placeholder="nama lengkap" onkeyup="nama_validating()"  />
						<img src="pict/blank.png" alt="icon5" id="nameicon" />
                        <label>tanggal lahir</label>
						<input type="text" name="tanggal" id="date" onmousedown="date_validating()" />
						<img src="pict/blank.png" alt="icon8" id="dateicon"  />
						<script type="text/javascript">
							calendar.set("date");
						</script>
                        <label>email</label>
                        <input name="email" type="email" placeholder="email" onkeyup="email_validating()" onchange="logingg()" />
						<img src="pict/blank.png" alt="icon6" id="emailicon" />
                            <br><br>
                        <label>avatar</label>
						<input type="file" name="avatar" onchange="avatar_validating()" />
						<img src="pict/blank.png" alt="icon7" id="avaicon" />
						<br>
                        <button class= "submitreg" disabled id="submitb" name="submit" type="submit"> Submit</button>
                        <input class= "submitreg" name="cancel" type="cancel" onclick="ShowAwal()" value="Cancel">
                    </form>
                </div>
                </div>
               
            </div>
        </div>
		
		<!-- slide -->
        <div class="footer">
            Copyright © Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
		
    </body>
</html>