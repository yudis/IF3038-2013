<%-- 
    Document   : index
    Created on : Apr 5, 2013, 9:38:21 AM
    Author     : LCF
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if(session.getAttribute("username")!= null)
    {
        response.sendRedirect("dashboard.jsp");
    }
    else
    {
     
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Organize Homepage</title>
        <link href="styles/home.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="styles/calendar.css">
        <script src="js/home.js"></script>
        <script src="js/calendar.js" > </script>
    </head>
    
    <body onload="isAlreadyLogin()" >
		<div id="header">
        	<div class=logo id="logo">
				<a href="index.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
			</div>
			<form name=login method="post" >
			<div class="login_form">
                            <input type="button" name="submit" value="Login" onclick="logine()" >
			</div>
			<div class="login_form">
				Password: <input type="password" name="passid" id="passid" >
			</div>
			<div class="login_form">
				Username: <input type="text" name="userid" id="userid" >
			</div>
			</form>
			
			
        </div>
	<div id="container">
		<div id="left_tab">
			<img src="images/registerglass.png" alt="Register dong gan">
		</div>
		
		<div id="register_tab">
			<form name="registration" action="signup_process" method="POST" enctype="multipart/form-data" >
			<div id="formulir">
				<div class="form_field">
					<div class="field_kiri">
						Username: 
					</div>
					<div class="field_kanan">
						<input name="username" id="username" type="text" placeholder="username" maxlength="256" onkeyup="user_validating()">
                                                <img src="images/blank.png" alt="icon2" id="usericon"  />
					</div>
					<div id="v_uname">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Password: 
					</div>
					<div class="field_kanan">
						<input name="password" type="password" placeholder="password" maxlength="24" onkeyup="pass_validating()">
                                                <img src="images/blank.png" alt="icon3" id="passicon" />
                                        </div>
					<div id="v_pass">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Confirm Password: 
					</div>
					<div class="field_kanan">
                                            <input name="confirmpass" type="password" placeholder="confirm password"  maxlength="24" onkeyup="conf_validating()">
                                                <img src="images/blank.png" alt="icon4" id="conficon" />
                                        </div>
					<div id="v_cpass">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Nama lengkap: 
					</div>
					<div class="field_kanan">
                                            <input type="text" name="namaleng" placeholder="nama lengkap" maxlength="256" onkeyup="nama_validating()">
                                            <img src="images/blank.png" alt="icon5" id="nameicon" />
                                        </div>
					<div id="v_nama">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Tanggal lahir: 
					</div>
					<div class="field_kanan">
                                            <input type="text" name="tanggal" id="date" onmousedown="date_validating()"  />
                                                <script type="text/javascript">
							calendar.set("date");
						</script>
                                                <img src="images/blank.png" alt="icon8" id="dateicon"  />
					</div>
					<div id="v_lahir">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Email: 
					</div>
					<div class="field_kanan">
						<input type="text" name="email" maxlength="256" onkeyup="email_validating()">
                                                <img src="images/blank.png" alt="icon6" id="emailicon" />
                                        </div>
					<div id="v_email">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
						Avatar: 
					</div>
					<div class="field_kanan">
                                            <input type="file" name="avatar" onchange="avatar_validating()">
                                                <img src="images/blank.png" alt="icon7" id="avaicon" />
					</div>
					<div id="v_avatar">
					</div>
				</div>
				<div class="form_field">
					<div class="field_kiri">
                                            <input type="submit" id="submitb" name="submit" value="Register" onclick="localStorage.setItem('username',document.getElementById('username').value);" disabled>
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
