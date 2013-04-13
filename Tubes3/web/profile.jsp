<%-- 
    Document   : profile
    Created on : Apr 12, 2013, 1:42:56 PM
    Author     : Sigit Aji Nugroho
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
        <title>Next: Profile</title>
        <link rel="stylesheet" href="css/calendar.css">
		<script src="js/calendar.js" > </script>
	<link rel="stylesheet" type="text/css" href="css/css.css">
	<link rel="stylesheet" type="text/css" href="css/profile.css">
        
        <%--<script type="text/javascript" src="js/popup.js"></script>--%>
	<script type="text/javascript">
            function getUserName(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
		
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
					
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                        //alert(xmlhttp.responseText);
                        document.getElementById("username24").innerHTML=xmlhttp.responseText;					
                    }
		};
								
                xmlhttp.open("GET","getUserName?user=<%out.print((String)session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
            function getEmail(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
				
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
			document.getElementById("email").innerHTML=xmlhttp.responseText;					
                    }
		}
								
                xmlhttp.open("GET","getEmail?user=<%out.print((String)session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
	
            function getAvatar(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
				
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
                        document.getElementById("avatarnya").innerHTML=xmlhttp.responseText;	
                    }
		}
								
		xmlhttp.open("GET","getAvatar?user=<%out.print((String)session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
	
            function getNamaLengkap(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
				
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
			document.getElementById("namalengkap").innerHTML=xmlhttp.responseText;			
                    }
		}
								
		xmlhttp.open("GET","getNamaLengkap?user=<%out.print((String)session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
	
            function getTanggalLahir(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
				
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
			document.getElementById("tanggallahir").innerHTML=xmlhttp.responseText;					
                    }
		}
								
		xmlhttp.open("GET","getTanggalLahir?user=<%out.print((String)session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
	
            function getProfil1Form(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
				
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
			document.getElementById("listProfilBox").innerHTML=xmlhttp.responseText;					
                    }
		}
								
		xmlhttp.open("GET","getProfil1Form?user=<%out.print((String)session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
	
            function getTugasSelesai(){
		var xmlhttp;
		if (window.XMLHttpRequest){
                    xmlhttp = new XMLHttpRequest();				
		}else{
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");	
		}
				
		xmlhttp.onreadystatechange = function(){
		//alert(xmlhttp.readyState+" "+xmlhttp.status);
                    if (xmlhttp.readyState==4 && xmlhttp.status==200){				
                    //alert(xmlhttp.responseText);
			document.getElementById("listProfilBox2").innerHTML=xmlhttp.responseText;					
                    }
		}
								
		xmlhttp.open("GET","getTugasSelesai?user=<%out.print(session.getAttribute("userLoginSession"));%>",true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
            
            function nama_validating()
            {
		var name = document.registration.fn.value;
					
		if(name.match(/([a-zA-Z])+([ \t\r\n\v\f])+([a-zA-Z])/))
		{
                    document.getElementById("nameicon").src="pict/centang.png";
		}
		else
		{
                    document.getElementById("nameicon").src="pict/canceled.png";
		}
            }
            
            function avatar_validating()
            {
		var ekstensi = document.registration.na.value;
					
		if((ekstensi.lastIndexOf(".jpg") != -1) || (ekstensi.lastIndexOf(".jpeg") != -1) )
		{
                    document.getElementById("avaicon").src="pict/centang.png";
		}
		else
		{
                    document.getElementById("avaicon").src="pict/canceled.png";
		}
            }
            
            function date_validating()
            {
                document.getElementById("dateicon").src="pict/centang.png";
            }
            
            function pass_validating()
            {
		var userid = document.registration.username.value;
                var userpass = document.registration.cp.value;
		var usermail = document.registration.email.value;
		var confpass = document.registration.cpp.value;
					
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
		var userpass = document.registration.cp.value;
		var confpass = document.registration.cpp.value;
		
		if(confpass == userpass)
		{
                    document.getElementById("conficon").src="pict/centang.png";
		}
		else
		{
                    document.getElementById("conficon").src="pict/canceled.png";
		}
            }
            
            function logingg()
            {	
		
                var picon = document.getElementById("passicon").src;
		var cicon = document.getElementById("conficon").src;
		var nicon = document.getElementById("nameicon").src;
		var aicon = document.getElementById("avaicon").src;
		var dicon = document.getElementById("dateicon").src;

		var lokasi = window.location.href.substring(0,window.location.href.lastIndexOf("/")) + "/pict/centang.png";	/*-------------------------*/		

		if ((picon == lokasi) && (cicon == lokasi) && (nicon == lokasi) && (aicon == lokasi) && (dicon == lokasi))
		{
                    document.getElementById("saveep").disabled = false;
		}
                else{
                    document.getElementById("saveep").disabled = true;
		}
            }
                                
	</script>
    <?jsp include 'header.jsp'; ?>
    </head>
    <body onload ="getUserName();getAvatar();getNamaLengkap();getTanggalLahir();getEmail();getProfil1Form();getTugasSelesai();">
        <%@ include file="header.jsp" %>
        <div id="content">
            <div id="profheader">
		<div id="username24">
			
		</div>
		<div id="avatarnya">
			
		</div>
		<div id="namalengkap">
				
		</div>
		<div id="tanggallahir">
				
		</div>
		<div id="email">
					
		</div>
		<div id="editprof" onClick="popup('popUpDiv');">
                    EDIT
		</div>
            </div>
            <div id="profil1Form">
                <div id="listProfilBox">
		
                </div>
            </div>
            <div id="tugas_selesai">
                <div id="listProfilBox2">
				
                </div>
            </div>
            
            <div id="blanket" "></div>
            <div id="popUpDiv" ">
                <div id="newcategory">
                    <div id="closecat">
                        <a href="#" onClick="popup('popUpDiv')" >CLOSE</a>
                    </div>
                    <div id="cattitle">EDIT PROFILE</div>
                    <div id="elcategory">
                        <form action="editProfile" method "post">
                            <label>Full Name</label>
                            <input name="fn" placeholder="nama lengkap" onKeyUp="nama_validating()" onChange="logingg()">
                            <img src="pict/blank.png" alt="icon5" id="nameicon" />
                            
                            <label>Upload new avatar</label>
                            <input type="file" name="na" onChange="avatar_validating()" logingg()">
                             <img src="pict/blank.png" alt="icon7" id="avaicon" />
                            
                            <label>Change birth date</label>
                            <input type="text" name="bd" id="date" onMouseDown="date_validating()" onChange="logingg()">
                            <img src="pict/blank.png" alt="icon8" id="dateicon"  />
				<script type="text/javascript">
                                    calendar.set("date");
				</script>
                                                
                            <label>Change password</label>
                            <input name="cp" type="password" placeholder="changepassword" onKeyUp="pass_validating()" onChange="logingg()">
                            <img src="pict/blank.png" alt="icon3" id="passicon" />
                            
                            <label>Confirm change password</label>
                            <input name="ccp" type="password" placeholder="confirmpassword" onKeyUp="conf_validating()" onChange="logingg()">
                            <img src="pict/blank.png" alt="icon4" id="conficon" />
                            
                            <input class= "submitreg" id="saveep" name="save" type="save" value="SAVE">
                        </form>
                    </div>
                    <%--
                    <div id="closecat">
                        <a href="#" onClick="popup('popUpDiv')" >CLOSE</a>
                    </div>--%>
                </div>
            </div>
            
	</div>
    </body>
</html>
