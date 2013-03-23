<!-- catatan: var lokasi untuk fungsi loging pada registrasi masih harus dibenerin-->


<html>
    <head>
        <title> Next : You Can't Do it Alone</title>
		<link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar.js" > </script>
        <link rel="stylesheet" href="css/css.css">
        <script>
			
			function isLogin(){	/*---------------------------JO---cek apakah sudah login----------------- */
				if (localStorage.userLogin!= null)	{
					window.location="dashboard.html";		
				}
			}
		
		
			function validateLogin(form){					/*---------------------------JO---validasi form login--phpnya:login.php----------------- */

				if (window.XMLHttpRequest){
					xmlhttp = new XMLHttpRequest();				
				}else{
					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
				}
				
				xmlhttp.onreadystatechange = function(){
					if (xmlhttp.readyState==4 && xmlhttp.status==200)	{				
						if(xmlhttp.responseText==1){
							localStorage.userLogin=form.userId.value;		/*----simpan user yg login ke local storage ------*/
							localStorage.tglLogin= new Date().getTime();	/*----simpan waktu login ke local storage ------*/
							window.location="dashboard.html";
						}else{
							alert("error password or username");
						}						
					}

				}
					
				xmlhttp.open("GET","login.php?user="+form.userId.value+"&pwd="+form.password.value,true);
				xmlhttp.send();
			}												
		
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
				
			function check(form)											/*-------------------------------udah ngga kepake----------------------------------- */
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

					var lokasi = window.location.href.substring(0,window.location.href.lastIndexOf("/")) + "/pict/centang.png";	/*------------masih harus diedit-------------*/		

					if ((uicon == lokasi) && (picon == lokasi) && (cicon == lokasi) && (nicon == lokasi) && (eicon == lokasi) && (aicon == lokasi) && (dicon == lokasi))
							{
								document.getElementById("submitb").disabled = false;
							}
					else{
								document.getElementById("submitb").disabled = true;
					}
				}
			
			function masuk(form)							/*------------masuk ke dashboard & masukin form ke db-php:insertReg.php---------- */
				{											
					if (window.XMLHttpRequest){
						xmlhttp = new XMLHttpRequest();				
					}else{
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
					}									
						
					xmlhttp.open("POST","insertReg.php",true);
					xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
					xmlhttp.send("user="+form.username.value+"&pwd="+form.password.value+"&nama="+form.namaleng.value+"&tgl="+form.tanggal.value+"&email="+form.email.value+"&avatar="+form.avatar.value);
					localStorage.userLogin=form.username.value;/*----simpan user yg baru register ke local storage ------*/									
					localStorage.tglLogin= new Date().getTime();	/*----simpan waktu register ke local storage ------*/					
					window.location="dashboard.html";
				}
			
			function user_validating()			/*----------------------------JO---validasi buat registrasi user-----registeruser.php---------------------- */
				{
					var userid = document.registration.username.value;
					var userpass = document.registration.password.value;
					
						if (window.XMLHttpRequest){
							xmlhttp = new XMLHttpRequest();				
						}else{
							xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
						}
						
						xmlhttp.onreadystatechange = function(){
							if (xmlhttp.readyState==4 && xmlhttp.status==200)	{				
								if((userid.length >= "5") && (userid != userpass) && (xmlhttp.responseText==1)){
										document.getElementById("usericon").src="pict/centang.png";
								}else{
										document.getElementById("usericon").src="pict/canceled.png";
								}				
							}		
						}
							
						xmlhttp.open("GET","registeruser.php?user="+userid,true);
						xmlhttp.send();									
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
			

			function email_validating()				/*-----------------------------JO--validasi buat email--registeremail.php--------------------------- */
				{
					var emails = document.registration.email.value;

					if (window.XMLHttpRequest){
						xmlhttp = new XMLHttpRequest();				
					}else{
						xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
					}
					
					xmlhttp.onreadystatechange = function(){
						if (xmlhttp.readyState==4 && xmlhttp.status==200)	{				
							if(emails.match(/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i) && xmlhttp.responseText==1){
									document.getElementById("emailicon").src="pict/centang.png";
							}else{
									document.getElementById("emailicon").src="pict/canceled.png";
							}
						}		
					}
						
					xmlhttp.open("GET","registeremail.php?email="+emails,true);
					xmlhttp.send();														
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
    <body onLoad="isLogin()">		<!-----------------------panggil isLogin------------------------->
        <div class="header">
            <div id="logo">
                <img src="pict/logo.png">
            </div>
            <div id="login">  
                <section class="loginform cf">
		<form name="login" action="index_submit" method="get" accept-charset="utf-8">
			<ul>
				<li>
					
					<input class="small" type="userID" name="userId" placeholder="yourID" required>
				</li>
				<li>
					
					<input class="small" type="password" name="password" placeholder="password" required></li>
				<li>
					<input class="xsmall" type="button" onClick="validateLogin(this.form)" value="Login">
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
                    <div id="poctreg" onClick="ShowAkhir()" >
                    </div>
                    <div id="textreg" on>
                        try and be awesome 
                    </div>
                </div>
                 <div id="regakhir" style="DISPLAY: none">
                    <div id="formreg">
                    <form name="registration">
                        <label>username</label>
                        <input name="username" type="text" placeholder="username"  onkeyup="user_validating()" onChange="logingg()" />
						<img src="pict/blank.png" alt="icon2" id="usericon" onchange="isformvalid()" />
                        <label>password</label>
                        <input name="password" type="password" placeholder="password" onKeyUp="pass_validating()" onChange="logingg()" />
                        <img src="pict/blank.png" alt="icon3" id="passicon" />
						<label>confirm password</label>
                        <input name="confirmpass" type="password" placeholder="confirm password" onKeyUp="conf_validating()" onChange="logingg()"  />
						<img src="pict/blank.png" alt="icon4" id="conficon" />
                        <label>nama lengkap</label>
                        <input name="namaleng" placeholder="nama lengkap" onKeyUp="nama_validating()" onChange="logingg()" />
						<img src="pict/blank.png" alt="icon5" id="nameicon" />
                        <label>tanggal lahir</label>
						<input type="text" name="tanggal" id="date" onMouseDown="date_validating()" onChange="logingg()" />
						<img src="pict/blank.png" alt="icon8" id="dateicon"  />
						<script type="text/javascript">
							calendar.set("date");
						</script>
                        <label>email</label>
                        <input name="email" type="email" placeholder="email" onKeyUp="email_validating()" onChange="logingg()" />
						<img src="pict/blank.png" alt="icon6" id="emailicon" />
                            <br><br>
                        <label>avatar</label>
						<input type="file" name="avatar" onChange="avatar_validating();logingg()" />
						<img src="pict/blank.png" alt="icon7" id="avaicon" />
						<br>
                        <input class= "submitreg" id="submitb" name="submit" type="button" value="Submit" onClick="masuk(this.form)" disabled />
                        <input class= "submitreg" name="cancel" type="cancel" onClick="ShowAwal()" value="Cancel">
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