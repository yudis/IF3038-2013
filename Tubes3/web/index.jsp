
<!DOCTYPE html>

<!-- catatan: -->


<html>
    
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>              

    <head>
        
        <%
        //HttpSession session = request.getSession(false);// don't create if it doesn't exist
        if((String)session.getAttribute("userLoginSession") != null) {            
            response.sendRedirect("dashboard.jsp");            
        }      
        %> 
        
        <title> Next : You Can't Do it Alone</title>
		<link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar2.js" > </script>
        <link rel="stylesheet" href="css/css.css">
        <script>
			
//			function isLogin(){	/*---------------------------JO---cek apakah sudah login----------------- */
//				if (localStorage.userLogin!= null)	{
//					window.location="dashboard.php";		
//				}
//			}
		
		
//			function validateLogin(form){					/*---------------------------JO---validasi form login--phpnya:login.php----------------- */
//                              var xmlhttp;
//				if (window.XMLHttpRequest){
//					xmlhttp = new XMLHttpRequest();				
//				}else{
//					xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
//				}
//				
//				xmlhttp.onreadystatechange = function(){
//					if (xmlhttp.readyState==4 && xmlhttp.status==200)	{				
//						if(xmlhttp.responseText==1){
//							localStorage.userLogin=form.userId.value;		/*----simpan user yg login ke local storage ------*/
//							localStorage.tglLogin= new Date().getTime();	/*----simpan waktu login ke local storage ------*/
//							window.location="dashboard.php";
//						}else{
//							alert("error password or username");
//						}						
//					}
//
//				}
//					
//				xmlhttp.open("GET","login.php?user="+form.userId.value+"&pwd="+form.password.value,true);
//				xmlhttp.send();
//			}												
		
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
				
				
			function logingg()
				{	
					var uicon = document.getElementById("usericon").src;
					var picon = document.getElementById("passicon").src;
					var cicon = document.getElementById("conficon").src;
					var nicon = document.getElementById("nameicon").src;
					var eicon = document.getElementById("emailicon").src;
					var aicon = document.getElementById("avaicon").src;
					var dicon = document.getElementById("dateicon").src;

					var lokasi = window.location.href.substring(0,window.location.href.lastIndexOf("/")) + "/pict/centang.png";	/*-------------------------*/		

					if ((uicon == lokasi) && (picon == lokasi) && (cicon == lokasi) && (nicon == lokasi) && (eicon == lokasi) && (aicon == lokasi) && (dicon == lokasi))
							{
								document.getElementById("submitb").disabled = false;
							}
					else{
								document.getElementById("submitb").disabled = true;
					}
				}
						
			function user_validating()			/*----------------------------JO---validasi buat registrasi user-----registeruser.php---------------------- */
				{
					var userid = document.registration.username.value;
					var userpass = document.registration.password.value;
					var xmlhttp;
                                        
                                        //alert("xmlhttp.responseText");
						if (window.XMLHttpRequest){
							xmlhttp = new XMLHttpRequest();				
						}else{
							xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
						}
						
						xmlhttp.onreadystatechange = function(){
							if (xmlhttp.readyState==4 && xmlhttp.status==200)	{				
                                                            //alert(xmlhttp.responseText);
								if((userid.length >= "5") && (userid != userpass) && (xmlhttp.responseText==1)){
										document.getElementById("usericon").src="pict/centang.png";
								}else{
										document.getElementById("usericon").src="pict/canceled.png";
								}				
							}		
						}
							
						xmlhttp.open("GET","registeruser?user="+userid,true);
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
                                        var xmlhttp;
                                        
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
						
					xmlhttp.open("GET","registeremail?email="+emails,true);
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
		<form name="login" method="POST" accept-charset="utf-8">
			<ul>
				<li>					
					<input class="loginFormBaru" type="userID" name="userIdLogin" placeholder="yourID" required>
				</li>
				<li>    
					
					<input class="loginFormBaru" type="password" name="passwordLogin" placeholder="password" required>
                                </li>
				
                                <li>
					<input class="xsmall" type="submit" value="Login">
				</li>
			</ul>                                        
		</form>

<%  //-------------------------login---------------------------------
    String isiLogin = request.getParameter("userIdLogin");
    String isiPwd = request.getParameter("passwordLogin");
    
    Class.forName("com.mysql.jdbc.Driver");
    java.sql.Connection conLogin = DriverManager.getConnection("jdbc:mysql://localhost:3306/progin_405_13510003","root","");
    
    Statement stLogin= conLogin.createStatement(); 
    ResultSet rsLogin=stLogin.executeQuery("select * from pengguna where username='"+isiLogin+"'");     
    
    if(rsLogin.next()) { 
        if(rsLogin.getString(2).equals(isiPwd)) { 
            session.setAttribute("userLoginSession", isiLogin);     
            response.sendRedirect("dashboard.jsp");            
        } 
        else {            
 //out.println("Welcome "+session.getAttribute("userLoginSession"));                   
%>
            <script language="javascript">
                alert("Invalid username or password, try again");
            </script>
<%                                 
        } 
    }
    
%>
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
                
                        <form name="registration" method="post" action="registerUser.jsp">
                            
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
                            <input class= "submitreg" id="submitb" name="submit" type="submit" value="Submit" disabled />
                            <input class= "submitreg" name="cancel" type="cancel" onClick="ShowAwal()" value="Cancel"/>
                        
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