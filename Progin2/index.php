<html>
<head>
<title>Organize Homepage</title>
<link href="styles/home.css" rel="stylesheet" type="text/css" />

		<script>
			function isUsername(uname){
				var xmlhttp;
				if (uname.length==0) { 
					  document.getElementById("v_login").innerHTML="";
					  return;
				}
				if (window.XMLHttpRequest) {
				  // code for IE7+, Firefox, Chrome, Opera, Safari
				  xmlhttp=new XMLHttpRequest();
				}
				else {
				  // code for IE6, IE5
				  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
				}
				xmlhttp.onreadystatechange=function() {
				  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
					document.getElementById("v_login").innerHTML=xmlhttp.responseText;
				  }
				}
				xmlhttp.open("GET","listusername.php?q="+uname+"&t=login",true);
				xmlhttp.send();
			}
		</script>
		<script>
			function makeTgl(){
				for(var i=1; i<=31; i++){
					var isi=document.createTextNode(i);
					var opsi = document.createElement("option");
					opsi.setAttribute("value",i);
					opsi.appendChild(isi);
					document.getElementById("tgl").appendChild(opsi);
				}
			}
		</script>
		<script>
			function makeThn(){
				for(var i=1955; i<=2013; i++){
					var isi=document.createTextNode(i);
					var opsi = document.createElement("option");
					opsi.setAttribute("value",i);
					opsi.appendChild(isi);
					document.getElementById("thn").appendChild(opsi);
				}
			}
		</script>
		<script>
			function enableRegister(){
				if(checkUserName(document.register_form.username.value) &&
				checkPass(document.register_form.password.value, document.register_form.username.value, document.register_form.email.value) &&
				checkCPass(document.register_form.confirm_password.value, document.register_form.password.value) &&
				validateFullName(document.register_form.nama_lengkap.value) &&
				checkEmail(document.register_form.email.value) &&
				validateAvatar(document.register_form.avatar.value))
				{
					document.register_form.submit.disabled =false;
				}
				else
				{
					document.register_form.submit.disabled =true;
				}
			}
		</script>
		<script language="javascript">
			function checkEmail(email){	
				var pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9_.-])+\.([a-zA-Z])+([a-zA-Z])+/;
				if(pattern.test(email)){
					var xmlhttp;
					if (email.length==0) { 
						  document.getElementById("v_email").innerHTML="";
						  return;
					}
					if (window.XMLHttpRequest) {
					  // code for IE7+, Firefox, Chrome, Opera, Safari
					  xmlhttp=new XMLHttpRequest();
					}
					else {
					  // code for IE6, IE5
					  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function() {
					  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
						document.getElementById("v_email").innerHTML=xmlhttp.responseText;
					  }
					}
					xmlhttp.open("GET","listemail.php?q="+email,true);
					xmlhttp.send();
					
					return true;
				} else {   
					document.getElementById('v_email').innerHTML='<font color="red">Salah</font>';
					return false;
				}
			}			
		</script>
		<script language="javascript">
			function checkUserName(uname){	
				var pattern=/^([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+([a-zA-Z0-9_.-])+/;
				if(pattern.test(uname)){         
					var xmlhttp;
					if (uname.length==0) { 
						  document.getElementById("v_uname").innerHTML="";
						  return;
					}
					if (window.XMLHttpRequest) {
					  // code for IE7+, Firefox, Chrome, Opera, Safari
					  xmlhttp=new XMLHttpRequest();
					}
					else {
					  // code for IE6, IE5
					  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
					}
					xmlhttp.onreadystatechange=function() {
					  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
						document.getElementById("v_uname").innerHTML=xmlhttp.responseText;
					  }
					}
					xmlhttp.open("GET","listusername.php?q="+uname+"&t=reg",true);
					xmlhttp.send();
					return true;
				}else{   
					document.getElementById('v_uname').innerHTML='<font color="red">Salah</font>';
					return false;
				}
			}			
		</script>
		<script>
			function checkCPass(cpass, pass){
				if(cpass == pass){
					document.getElementById('v_cpass').innerHTML='<font color="green">Benar</font>';
					return true;					
				}else{
					document.getElementById('v_cpass').innerHTML='<font color="red">Salah</font>'; 
					return false;
				}
			}
		</script>
		<script>
			function checkPass(pass, uname, email){
				if((pass != uname) && (pass != email)){
					document.getElementById('v_pass').innerHTML='<font color="green">Benar</font>';
					return true;					
				}else if(pass == uname){
					document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username</font>'; 
					return false;
				}
				else if(pass == email){
					document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan email</font>'; 
					return false;
				}
				else{
					document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username/email</font>'; 
					return false;
				}
			}
		</script>
		<script>
		function validateFullName(nama) {
				var str = nama;
				var idx = str.indexOf(' ');
				if (idx > 0 && idx < str.length - 1) {
					var chr1 = str.charAt(idx - 1);
					var chr2 = str.charAt(idx + 1);
					if (chr1 != ' ' && chr2 != ' ') {
						document.getElementById('v_nama').innerHTML='<font color="green">Benar</font>';
						return true;	
					} else {
						document.getElementById('v_nama').innerHTML='<font color="red">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; 
					return false;
					}
				} else {
					document.getElementById('v_nama').innerHTML='<font color="red">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; 
					return false;
				}
		}
		</script>
		<script>
		function validateAvatar(avatar) {
				var str = avatar;
				var ext = str.substring(str.lastIndexOf('.') + 1, str.length);
				if (ext.toLowerCase() == "jpeg" || ext.toLowerCase() == "jpg") {
					document.getElementById('v_avatar').innerHTML='<font color="green">Benar</font>';
						return true;	
				} else {
					document.getElementById('v_avatar').innerHTML='<font color="red">Avatar harus ekstensi .jpg atau .jpeg</font>'; 
					return false;
				}
		}
		</script>
		<script>
		function checkSubmit(e) {
		   if (e && e.keyCode == 13) {
			  validateLogin();
		   }
		}
		</script>
</head>

<body onLoad="makeTgl();makeThn();">
	<div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="index.php"><img src="images/logo.png" title="Home" alt="Home"/></a>
			</div>
			<form name="login" method="post" action="login.php">
				<div class="login_form">
					<input type="submit" name="submit" value="Login">
				</div>
				<div class="login_form">
					Password: <input type="password" name="mypassword">
				</div>
				<div class="login_form">
					Username: <input type="text" name="myusername" onKeyUp="isUsername(document.login.myusername.value)">
					<div id="v_login">
					<?php
					if (isset($_GET['q']) && $_GET['q'] == 0) {
						echo "<font color=red>Login gagal</font>";
					}
					?>
					</div>
				</div>
			</form>
        </div>
		
		<div id="left_tab">
			<img src="images/registerglass.png" alt="Register dong gan">
		</div>
		
		<div id="register_tab">
			<form name="register_form" method="post" action="register.php" enctype="multipart/form-data">
			<div id="formulir">
				<div class="form_field">
					<div class="field_kiri">
						Username: 
					</div>
					<div class="field_kanan">
						<input name="username" type="text"  maxlength="256" onKeyUp="enableRegister()">
					</div>
					<div id="v_uname">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Password: 
					</div>
					<div class="field_kanan">
						<input name="password" type="password"  maxlength="24" onKeyUp="enableRegister()">
					</div>
					<div id="v_pass">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Confirm Password: 
					</div>
					<div class="field_kanan">
						<input name="confirm_password" type="password"  maxlength="24" onKeyUp="enableRegister()">
					</div>
					<div id="v_cpass">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Nama lengkap: 
					</div>
					<div class="field_kanan">
						<input type="text" name="nama_lengkap" maxlength="256" onKeyUp="enableRegister()">
					</div>
					<div id="v_nama">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Tanggal lahir: 
					</div>
					<div class="field_kanan">
						<select name="tanggal" id="tgl">
							<option>--Tanggal--</option>
						</select>
						<select name="bulan">
							<option>--Bulan--</option>
							<option value="January">January</option>
							<option value="February">February</option>
							<option value="March">March</option>
							<option value="April">April</option>
							<option value="May">May</option>
							<option value="June">June</option>
							<option value="July">July</option>
							<option value="August">August</option>
							<option value="September">September</option>
							<option value="October">October</option>
							<option value="November">November</option>
							<option value="December">December</option>
						</select>
						<select name="tahun" id="thn">
							<option>--Tahun--</option>
						</select>
					</div>
					<div id="v_lahir">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Email: 
					</div>
					<div class="field_kanan">
						<input type="text" name="email" maxlength="256" onKeyUp="enableRegister()">
					</div>
					<div id="v_email">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Avatar: 
					</div>
					<div class="field_kanan">
						<input type="file" name="avatar" onChange="enableRegister()">
					</div>
					<div id="v_avatar">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						<input type="submit" name="submit" value="Register" disabled = true>
					</div>
					<div class="field_kanan">
					
					</div>
				</div>
			</div>
		</form>
		</div>
	</div>
</body>
</html>
