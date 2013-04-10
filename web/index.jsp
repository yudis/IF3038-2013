<%-- 
    Document   : index
    Created on : Apr 5, 2013, 9:38:21 AM
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organize Homepage</title>
        <link href="styles/home.css" rel="stylesheet" type="text/css" />
    </head>
    
    <body onload="makeTgl();makeThn();">
	<div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="index.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
			</div>
			<form name=login>
			<div class="login_form">
				<input type="button" name="submit" value="Login" onClick="validateLogin()">
			</div>
			<div class="login_form">
				Password: <input type="password" name="login_pass" onKeyPress="checkSubmit(event)">
			</div>
			<div class="login_form">
				Email: <input type="text" name="login_email" onKeyPress="checkSubmit(event)">
			</div>
			</form>
			
			
        </div>
		
		<div id="left_tab">
			<img src="images/registerglass.png" alt="Register dong gan">
		</div>
		
		<div id="register_tab">
			<form name="register_form">
			<div id="formulir">
				<div class="form_field">
					<div class="field_kiri">
						Username: 
					</div>
					<div class="field_kanan">
						<input name="username" type="text"  maxlength="256" onkeyup="checkUserName(document.register_form.username.value)">
					</div>
					<div id="v_uname">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Password: 
					</div>
					<div class="field_kanan">
						<input name="password" type="password"  maxlength="24" onkeyup="checkPass(document.register_form.password.value, document.register_form.username.value, document.register_form.email.value)">
					</div>
					<div id="v_pass">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Confirm Password: 
					</div>
					<div class="field_kanan">
						<input name="confirm_password" type="password"  maxlength="24" onkeyup="checkCPass(document.register_form.confirm_password.value, document.register_form.password.value)">
					</div>
					<div id="v_cpass">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Nama lengkap: 
					</div>
					<div class="field_kanan">
						<input type="text" name="nama_lengkap" maxlength="256">
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
							<option value="Januari">Januari</option>
							<option value="Februari">Februari</option>
							<option value="Maret">Maret</option>
							<option value="April">April</option>
							<option value="Mei">Mei</option>
							<option value="Juni">Juni</option>
							<option value="Juli">Juli</option>
							<option value="Agustus">Agustus</option>
							<option value="September">September</option>
							<option value="Oktober">Oktober</option>
							<option value="November">November</option>
							<option value="Desember">Desember</option>
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
						<input type="text" name="email" maxlength="256" onkeyup="checkEmail(document.register_form.email.value)">
					</div>
					<div id="v_email">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Avatar: 
					</div>
					<div class="field_kanan">
						<input type="file" name="avatar">
					</div>
					<div id="v_avatar">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						<input type="submit" name="submit" value="Register">
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
