<?php

session_start();

if (ISSET($_SESSION['username']))
	{
		header("location: dashboard.php");
	}
		else
	{
		echo "<script type='text/javascript'> alert('password salah'); </script>";
	}
?>

<html>
    <head>
        <title> Next : You Can't Do it Alone</title>
		<link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar.js" > </script>
        <link rel="stylesheet" href="css/css.css">
        <script>
            function ShowAkhir()
                {
                     if(document.getElementById("regakhir").style.display == 'none')
                    {
                            document.getElementById("regakhir").style.display='block';
                            document.getElementById("regawal").style.display='none';
                    }
                    else
                     {
                            document.getElementById("regakhir").style.display = 'none';
                     }
                }
            function ShowAwal()
                {
                     if(document.getElementById("regawal").style.display == 'none')
                    {
                            document.getElementById("regawal").style.display='block';
                            document.getElementById("regakhir").style.display='none';
                    }
                    else
                     {
                            document.getElementById("regawal").style.display = 'none';
                     }
                }
				
			function check(form)
				{
					if(form.userId.value == "admin" && form.password.value == "testing")
						{
							window.location="dashboard.html";
						}
							else
						{
							alert("error password or username");
						}
				}
				
			function logingg()
				{	
					var uicon = document.getElementById("usericon").src;
					var picon = document.getElementById("passicon").src;
					var cicon = document.getElementById("conficon").src;
					var nicon = document.getElementById("nameicon").src;
					var eicon = document.getElementById("emailicon").src;
					var aicon = document.getElementById("avaicon").src;
					var dicon = document.getElementById("dateicon").src;
					var lokasi = "file:///C:/xampp/htdocs/Progin/pict/centang.png";
					
					if ((uicon == lokasi) && (picon == lokasi) && (cicon == lokasi) && (nicon == lokasi) && (eicon == lokasi) && (aicon == lokasi) && (dicon == lokasi))
							{
								document.getElementById("submitb").disabled = false;
							}
				}
			
			function masuk()
				{
					window.location="dashboard.html";
				}
			
			function user_validating()
				{
					var userid = document.registration.username.value;
					var userpass = document.registration.password.value;
					
					if((userid.length >= "5") && (userid != userpass))
						{
							document.getElementById("usericon").src="pict/centang.png";
						}
							else
						{
							document.getElementById("usericon").src="pict/canceled.png";
						}
				}
				
			function pass_validating()
				{
					var userid = document.registration.username.value;
					var userpass = document.registration.password.value;
					var usermail = document.registration.email.value;
					var confpass = document.registration.confirmpass.value;
					
					if((userpass != userid) && (userpass.length >= "8") && (userpass != usermail))
						{
							if(userpass != confpass)
								{
									document.getElementById("conficon").src="pict/canceled.png";
								}
							document.getElementById("passicon").src="pict/centang.png";
						}
							else
						{
							document.getElementById("passicon").src="pict/canceled.png";
						}
						
				}
				
			function conf_validating()
				{
					var userpass = document.registration.password.value;
					var confpass = document.registration.confirmpass.value;
					
					if(confpass == userpass)
						{
							document.getElementById("conficon").src="pict/centang.png";
						}
							else
						{
							document.getElementById("conficon").src="pict/canceled.png";
						}
				}
			
			function nama_validating()
				{
					var name = document.registration.namaleng.value;
					
					if(name.match(/([a-zA-Z])+([ \t\r\n\v\f])+([a-zA-Z])/))
						{
							document.getElementById("nameicon").src="pict/centang.png";
						}
							else
						{
							document.getElementById("nameicon").src="pict/canceled.png";
						}
				}
			

			function email_validating()
				{
					var emails = document.registration.email.value;
					
					if(emails.match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i))
						{
							document.getElementById("emailicon").src="pict/centang.png";
						}
							else
						{
							document.getElementById("emailicon").src="pict/canceled.png";
						}
				}
			
			function date_validating()
				{
					document.getElementById("dateicon").src="pict/centang.png";
				}
			
			function avatar_validating()
				{
					var ekstensi = document.registration.avatar.value;
					
					if((ekstensi.lastIndexOf(".jpg") != -1) || (ekstensi.lastIndexOf(".jpeg") != -1) )
						{
							document.getElementById("avaicon").src="pict/centang.png";
						}
							else
						{
							document.getElementById("avaicon").src="pict/canceled.png";
						}
				}
			
			function isformvalid()
				{
					var uservalid = document.getElementById("usericon").src;
					var userid = document.registration.username.value;
					var userpass = document.registration.password.value;
					
					if((userid.length >= "5") && (userid != userpass))
						{
							document.getElementsByName("submit")[0].disabled = false;
						}
				}
			
        </script>
    </head>
    <body>
        <div class="header">
            <div id="logo">
                <img src="pict/logo.png">
            </div>
            <div id="login">  
                <section class="loginform cf">
		<form name="login" action="cek.php" method="post" accept-charset="utf-8">
			<ul>
				<li>
					
					<input class="small" type="userID" name="userId" placeholder="yourID" required>
				</li>
				<li>
					
					<input class="small" type="password" name="password" placeholder="password" required></li>
				<li>
					<input class="xsmall" type="submit" value="Login">
				</li>
			</ul>
		</form>
	</section>
            </div>
        </div>
		<div class="alert"> User ID atau password salah </div>
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
                    <form name="registration" Method="POST" action="signup_process.php">
                        <label>username</label>
                        <input name="username" type="text" placeholder="username"  onkeyup="user_validating()" onchange="logingg()" />
						<img src="pict/blank.png" alt="icon2" id="usericon" onchange="isformvalid()" />
                        <label>password</label>
                        <input name="password" type="password" placeholder="password" onkeyup="pass_validating()" onchange="logingg()" />
                        <img src="pict/blank.png" alt="icon3" id="passicon" />
						<label>confirm password</label>
                        <input name="confirmpass" type="password" placeholder="confirm password" onkeyup="conf_validating()" onchange="logingg()"  />
						<img src="pict/blank.png" alt="icon4" id="conficon" />
                        <label>nama lengkap</label>
                        <input name="namaleng" placeholder="nama lengkap" onkeyup="nama_validating()" onchange="logingg()" />
						<img src="pict/blank.png" alt="icon5" id="nameicon" />
                        <label>tanggal lahir</label>
						<input type="text" name="tanggal" id="date" onmousedown="date_validating()" onchange="logingg()" />
						<img src="pict/blank.png" alt="icon8" id="dateicon"  />
						<script type="text/javascript">
							calendar.set("date");
						</script>
                        <label>email</label>
                        <input name="email" type="email" placeholder="email" onkeyup="email_validating()" onchange="logingg()" />
						<img src="pict/blank.png" alt="icon6" id="emailicon" />
                            <br><br>
                        <label>avatar</label>
						<input type="file" name="avatar" onchange="avatar_validating();logingg()" />
						<img src="pict/blank.png" alt="icon7" id="avaicon" />
						<br>
                        <input class= "submitreg" id="submitb" name="submit" type="submit" value="Submit" />
                        <input class= "submitreg" name="cancel" type="cancel" onclick="ShowAwal()" value="Cancel">
                    </form>
                </div>
                </div>
               
            </div>
        </div>
		
		<!-- slide -->
		<?php 
		
			
		
		?>
        <div class="footer">
            Copyright � Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>