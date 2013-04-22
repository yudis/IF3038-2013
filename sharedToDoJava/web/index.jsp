<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" %>
<%	
	if(session.getAttribute("loggedin") != null) {
		response.sendRedirect("dashboard.jsp");
	}
%>    
   
<!DOCTYPE HTML>
<html>
	<head>
		<link href="css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/jsDatePick_ltr.min.css" />
		<script type="text/javascript" src="js/jsDatePick.min.1.3.js"></script>
        <script type="text/javascript" src="js/main.js"></script>
	</head>
	<body onload="kalender(); loadRegex();">
        <div id="signin">
            <form onkeydown="enterButton(this.signin_button)">
                <div id="kolom_usrnm">
                    <input id="usrnm" class="input_field" type="text" placeholder="Username">
                    <div><input type="checkbox">Tetap sign in</div>
                </div>
                <div id="kolom_psswrd">
                    <input id="psswrd" class="input_field" type="password" placeholder="Password">
                    <div>Lupa password?</div>
                </div>
                <input id="signin_button" class="input_button" type="button" value="Sign in" onclick="checkSignIn(this.form.usrnm.value, this.form.psswrd.value)">
            </form>
            <br>
            <strong id="error_signin" class="error_warning"></strong>
        </div>
        <br><br>
        <form action="register.php" method="post">
            <div>
                <input id="email_signup" name="email" class="input_field" type="text" placeholder="Email" onkeydown="validate_email_exist(this.value); validate_email()">
                &nbsp;
                <strong id="error_email" class="error_warning"></strong>
                <strong id="error_email_exist" class="error_warning"></strong>
            </div>
            <div>
                <input id="usrnm_signup" name="username" class="input_field" type="text" placeholder="Username" onkeydown="validate_username_exist(this.value); validate_username()">
                &nbsp;
                <strong id="error_username" class="error_warning"></strong>
                <strong id="error_username_exist" class="error_warning"></strong>
            </div>
            <div>
                <input id="psswrd_signup" name="password" class="input_field" type="password" placeholder="Password" onkeydown="validate_password()">
                &nbsp;
                <strong id="error_password" class="error_warning"></strong>
            </div>
            <div>
                <input id="cnfrm_psswrd_signup" class="input_field" type="password" placeholder="Confirm password" onkeydown="confirm_password()">
                &nbsp;
                <strong id="error_confirm_password" class="error_warning"></strong>
            </div>
            <div>
                <input id="nama_signup" name="full_name" class="input_field" type="text" placeholder="Nama lengkap" onkeydown="validate_nama()">
                &nbsp;
                <strong id="error_nama" class="error_warning"></strong>
            </div>
            <div>
                <input id="fileUpload" name="avatar" class="input_field" type="file">
                &nbsp;
                <strong id="error_upload_avatar" class="error_warning"></strong>
            </div>
            <div>
                <input id="tgl_lahir" name="birth_date" class="input_field" type="text" placeholder="Tanggal lahir" onchange="validate_tanggal_lahir()">
                &nbsp;
                <strong id="error_tanggal_lahir" class="error_warning"></strong>
            </div>        
                <input id="signup_button" class="input_button" type="submit" value="Sign up" onclick="window.location='dashboard.php'">
        </form>
        
    </body>
</html>