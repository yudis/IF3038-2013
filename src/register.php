<?php
	/*require "validation.php";*/
?>
<!DOCTYPE html>
<html dir="ltr" lang="en-US">
<head>
	<script>
		function isUsernameExist()
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
			document.getElementById("used_username").innerHTML=xmlhttp.responseText;
			}
		  }
		xmlhttp.open("GET","validationusername.php?username="+document.forms["form"].elements["username"].value,true);
		xmlhttp.send();
		}
		
		function isEmailExist()
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
			document.getElementById("used_email").innerHTML=xmlhttp.responseText;
			}
		  }
		xmlhttp.open("GET","validationemail.php?email="+document.forms["form"].elements["email"].value,true);
		xmlhttp.send();
		}
	</script>
	<title>ToDo</title>
	<link rel="stylesheet" type="text/css" media="all" href="css.css" />
</head>
<body>
<header>	
			
			<div id="tes">
			<br></br>
			<h1 id="logo"><a href="dashboard.html"><img src="images/logo2.png"/></a>
			<input name="search" size="30" type="text" maxlength="20"><img src="images/search-icon.png"/>
			</div>
	
</header>
		
<script>

	var user = document.getElementById("username_login").value;
	var password = document.getElementById("password").value;
	var confpassword = document.getElementById("confpassword").value;
	var fullname = document.getElementById("fullname").value;
	var email = document.getElementById("email_login").value;
	var avatar = document.getElementById("avatar").value;
	var birth = document.getElementById("datebirth").value;
	
	function functionusername($input){
		validateusername($input);
		isUsernameExist();
	}
	function validateusername($input){
		var text =""+$input;
		var regex_username = /^[A-Za-z0-9_]{5,}$/;
		if (!regex_username.test(text)){
		document.getElementById("salah username").innerHTML="Minimal 5 karakter dan tidak menggunakan spesial karakter";
		document.getElementById("submitbutton").disabled = true;
	} else
	{
		document.getElementById("salah username").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
	}
	}
	
	function validatepassword($input){
		var text =""+$input;
		var regex_password = /^[A-Za-z0-9!@()$%^&*#_]{8,}$/;
		if (!regex_password.test(text) || text.length==0){
		document.getElementById("salah password").innerHTML="Minimal 8 karakter dan hanya menggunakan $ % ^ & * #";
		document.getElementById("submitbutton").disabled = true;
	} else {
		document.getElementById("salah password").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
	}	
	}
	
	function confirmpassword($input){
		var text =""+$input;
		var textpassword = document.getElementById("password").value;
		if(text != textpassword){
		document.getElementById("salah tidaksama").innerHTML="Password Tidak Sama";
		document.getElementById("submitbutton").disabled = true;		
		} else {
		document.getElementById("salah tidaksama").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
		}	
	}
	
	function validatefullname($input){
		var text =""+$input;
		var regex_fullname = /^[A-Za-z]+ [A-Za-z \.]+$/;
		if (!regex_fullname.test(text) || text.length==0){
		document.getElementById("salah_fullname").innerHTML="Minimal 2 kata";
		document.getElementById("submitbutton").disabled = true;
	} else {
		document.getElementById("salah_fullname").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
	}	
	}
	
	function validateemail($input){
		var text =""+$input;
		var regex_email = /^[A-Za-z][A-Za-z0-9_.]*\@[A-Za-z0-9_.-]+\.[A-Za-z][A-Za-z]+$/;
		if (!regex_email.test(text)|| text.length==0){
		document.getElementById("salah email").innerHTML="format xx@xx.yy";
		document.getElementById("submitbutton").disabled = true;
	} else {
		document.getElementById("salah email").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
	}	
	}
	
	function validatesamewithpassword($input){
		var text =""+$input;
		var textpassword = document.getElementById("password").value;
		if (text == textpassword || text.length==0){
		document.getElementById("salah samapassword").innerHTML="Email tidak boleh sama dengan Password";
		document.getElementById("submitbutton").disabled = true;
	} else {
		document.getElementById("salah samapassword").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
	}	
	}

	function functionemail($input){
		validatesamewithpassword($input);
		isEmailExist();
		validateemail($input);
	}
	
	function validateavatar($input){
		var filename = document.getElementById("avatar").value;
		var ext = filename.substring(filename.lastIndexOf('.') + 1);

		alert(filename);
		if (!(ext == "jpg" || ext == "JPG" || ext == "jpeg" || ext == "JPEG")){
		document.getElementById("salah foto").innerHTML="File harus jpg atau jpeg";
		document.getElementById("submitbutton").disabled = true;
	} else {
		document.getElementById("salah foto").innerHTML="";
		document.getElementById("submitbutton").disabled = false;
	}
	}
	</script>

		<div id="elemenkiri">
		<div id="primary">
			<div id="content" role="main">
					<article class="post">	
						
							<form name="form" action="insertdatabase.php" method="post"  enctype='multipart/form-data'>
							
							<strong >Username </strong> <br />
							<input id="username_login" name="username" size="30" type="text" maxlength="20" onkeyup="functionusername(this.value)">
							<div id="salah username"> </div>
							<div id="used_username"> </div>
							<br/>
							
							<strong>Password </strong> <br />
							<input id="password" name="password" type="password" size="30" maxlength="20" onkeyup="validatepassword(this.value)">
							<div id="salah password"> </div>
							<div id="salah sama"> </div>
							<br/>
							
							<strong>Confirm Password </strong> <br />
							<input id="confpassword" name="confirm" type="password" size="30" maxlength="20" onkeyup="confirmpassword(this.value)"> 
							<div id="salah tidaksama"> </div>
							<br/>
							
							<strong>Full Name </strong> <br />
							<input id="fullname" name="fullname" type="text" size="30" maxlength="30" onkeyup="validatefullname(this.value)">
							<div id="salah_fullname"> </div>							
							<br/>
							
							<strong>Email </strong> <br />
							<input id="email_login" name="email" type="text" size="30" maxlength="50" onkeyup="functionemail(this.value)"> 
							<div id="salah email"> </div>
							<div id="used_email"> </div>
							<div id="salah samapassword"> </div>
							<br/>
							
							<strong>Birth</strong> <br />
							<input id="datebirth" name="birth" type="date" placeholder="YYYY-MM-DD">
							<div id="salah tanggal"> </div>
							<br/>
							
							
							<strong>Upload Avatar</strong> <br />
							<input id="avatar" name="foto" type="file" size="30" onchange="validateavatar(this.value)">
							<div id="salah foto"> </div>
							
							<br/>
							
							<div id="field"> </div>
							
							<input id="submitbutton" name="submit" type="submit" value="Register Me!">
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