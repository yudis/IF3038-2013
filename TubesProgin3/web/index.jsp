<%
    Cookie[] cookies = ((HttpServletRequest)request).getCookies();
    int i = 0;
    boolean found = false;
    while(i < cookies.length && !found) {
        if(cookies[i].getName().equals("bananauser"))
            found = true;
        else
            i++;
    }
    if (found) {
        ((HttpServletRequest)request).getSession().setAttribute("bananauser", cookies[i].getValue());
        ((HttpServletResponse) response).sendRedirect("home.jsp");
    }
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>Banana Board - Your Online Do List</title>		
		<link rel="stylesheet" href="style.css" type="text/css">
		<script src="index.js" type="text/javascript" language="javascript"></script>
		<script src="datetimepicker_css.js" type="text/javascript" language="javascript"> </script>
	</head>
	
	<body onload='initindex()'>
		<div id="indexheader">
			<img src="image/logo.png">					
			<div id="loginform" class="login">		
				<ul>
					<h1 align="left">Login</h1>
					<li>
						<label for="username">Username:</label>
						<input class="loginbox" id="userheader" type="text" onkeyup='loginPressed(event.keyCode)'/>
					</li>
					<li>
						<label for="password">Password:</label>
						<input class="loginbox" id="passheader" type="password" onkeyup='loginPressed(event.keyCode)'/>
					</li>
					<li>
						<button class="loginbutton" onclick='login()'><b>Login</b></button>
					</li>
				</ul>
			</div>	
		</div>		
		
		<div id="register">			
			<form class="reg" action='Register' method="post" name="registerform" enctype="multipart/form-data">
				<ul>
					<p><b>NEW TO BANANA BOARD?</b></p>
					<h1 align="left">Register now!</h1>
					<li>
						<label for="username">Username:</label>
						<input type="text" name='username' id="txUsername" onkeyup="vdUsername()" oninput="vdUsername()" onpaste="vdUsername()"/>
						<img src="image/false.png" id="icoUsername" alt="Not Accepted" /><br />
						<span>* Minimal 5 karakter</span>
					</li>
					<li>
						<label for="password">Password:</label>
						<input type="password" name='pass' id="txPassword" onkeyup="vdPassword()" oninput="vdPassword()" onpaste="vdPassword()"/>
						<img src="image/false.png" id="icoPassword" alt="Not Accepted" /><br />
						<span>* Minimal 8 karakter, tidak boleh sama dengan username maupun email</span>
					</li>		
					<li>
						<label for="confirmpass">Confirmed Password:</label>
						<input type="password" id="txConfirmPassword" onkeyup="vdConfirmPassword()" oninput="vdConfirmPassword()" onpaste="vdConfirmPassword()"/>
						<img src="image/false.png" id="icoConfirmPassword" alt="Not Accepted" /><br />
						<span>* Harus sama dengan password</span>
					</li>
					<li>
						<label for="namalengkap">Nama Lengkap:</label>
						<input type="text" name='name' id="txName" onkeyup="vdName()" oninput="vdName()" onpaste="vdName()"/>
						<img src="image/false.png" id="icoName" alt="Not Accepted" /><br />
						<span>* Minimal 2 kata (nama depan dan nama belakang)</span>
					</li>	
					<li>
						<label for="tgllahir">Tanggal Lahir:</label>
						<input type="text" id="tgllahir" name='birth' size="25"/ readonly>
						<a href="javascript:NewCssCal('tgllahir','yyyymmdd')"><img src="image/cal.gif" alt="Pick a date"/></a>
					</li>
					<li>
						<label for="email">Email:</label>
						<input type="text" name='email' id="txEmail" required onkeyup="vdEmail()" oninput="vdEmail()" onpaste="vdEmail()"/>
						<img src="image/false.png" id="icoEmail" alt="Not Accepted" /><br />
						<span>* Sesuai dengan format alamat surel (x@x.xx)</span>
					</li>
					<li>
						<label for="avatar">Avatar:</label>
						<input type="file" name='avatar' id="avatar" onchange='vdAvatar()'/> &nbsp;&nbsp;&nbsp;
						<img src="image/true.png" id="icoAvatar" alt="Accepted" /><br />
						<span>* Hanya menerima berkas berekstensi .jpg atau .jpeg</span>
					</li>
					<li>
						<button type="submit" id="registerbutton">Register</button>
					</li>
				</ul>
			</form>
		</div>
		
		<div id="featuresbar">
			<ul id="features">
				<li>
					<b>Banana is here to remind you of your tasks</b>
				</li>
				<li>
					<b>Banana lets you synchronize your tasks with members</b>
				</li>
			</ul>
		</div>
		
		<div id="footer">
			<p>&copy Copyright 2013. All rights reserved<br>
			Chalkz Team<br>
			Yulianti - Raymond - Devin</p>			
		</div>
	</body>
</html>