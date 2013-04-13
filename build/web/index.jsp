<%-- 
    Document   : index
    Created on : Apr 12, 2013, 5:08:15 AM
    Author     : Yusuf Ardi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" href="css/style2.css" rel="stylesheet" />
        <link rel="stylesheet" href="styles/calendar.css">
        
        <script type="text/javascript" src="js/calendar.js" > </script>
        <script type="text/javascript" src="js/home.js"></script>
        
        <title>To Do</title>
    </head>
    
    <body onload="sedangLogin()">
        <div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="index.jsp"><img src="images/togo.png" title="Home" alt="Home"/></a>
			</div>
			
			<div class="login_form">
                            <input type="button" name="submit" value="Login" onclick="validateLogin()" >
			</div>
			<div class="login_form">
                               Password: <input type="password" name="login_pass" id="login_pass" >
			</div>
			<div class="login_form">
				Username: <input type="text" name="login_username" id="login_username" >
			</div>
			
        </div>
	<div id="container">	
		<div id="left_tab">
                    <br><br><br> 
                    
                    <div id="msgbox">
                    </div>
                    
                    <h3>
                        Have Not Any Account Yet? Just Fill and Register Yourself Up!
                        <b>---></b>
                    </h3>
		</div>
		
		<div id="register_tab">
                    <form name="registration" method="post" action="InsertNew" enctype="multipart/form-data">
			<div id="formulir">
				<div class="form_field">
					<div class="field_kiri">
						Username: 
					</div>
					<div class="field_kanan">
						<input name="username" type="text" placeholder="Username" maxlength="256" onkeyup="user_validating()">
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
						<input name="password" type="password" placeholder="Password" maxlength="24" onkeyup="pass_validating()">
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
                                            <input name="confirmpass" type="password" placeholder="Confirm Password"  maxlength="24" onkeyup="conf_validating()">
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
                                            <input type="text" name="namaleng" placeholder="Nama Lengkap" maxlength="256" onkeyup="nama_validating()">
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
                                            <input type="text" name="tanggal" placeholder="YYYY-MM-DD" id="date"  />
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
                                            <input type="text" name="email" placeholder="Email@email.com" maxlength="256" onkeyup="email_validating()">
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
