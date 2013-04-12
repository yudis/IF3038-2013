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
                        alert(xmlhttp.responseText);
                        document.getElementById("username").innerHTML=xmlhttp.responseText;					
                    }
		};
								
            xmlhttp.open("GET","getUserName?user=" <%= session.getAttribute("userLoginSession") %>,true);
		xmlhttp.send();
		alert(xmlhttp.responseText);
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
								
		xmlhttp.open("GET","getEmail?user="<%= session.getAttribute("userLoginSession") %>,true);
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
								
		xmlhttp.open("GET","getAvatar?user="<%= session.getAttribute("userLoginSession") %>,true);
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
								
		xmlhttp.open("GET","getNamaLengkap?user="<%= session.getAttribute("userLoginSession") %>,true);
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
								
		xmlhttp.open("GET","getTanggalLahir?user="<%= session.getAttribute("userLoginSession") %>,true);
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
								
		xmlhttp.open("GET","getProfil1Form?user="<%= session.getAttribute("userLoginSession") %>,true);
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
								
		xmlhttp.open("GET","getTugasSelesai?user="<%= session.getAttribute("userLoginSession") %>,true);
		xmlhttp.send();
		//alert(xmlhttp.responseText);
		//alert(xmlhttp.status);
            }
	</script>
    <?jsp include 'header.jsp'; ?>
    </head>
    <body onload ="getUserName();getAvatar();getNamaLengkap();getTanggalLahir();getEmail();getProfil1Form();getTugasSelesai();">
        <%@ include file="header.jsp" %>
        <div id="content">
            <div id="profheader">
		<div id="username">
			
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
                        <form action="editProfile" method "GET">
                            <label>Full Name</label>
                            <input name="fn" placeholder="fullname">
                            <label>Upload new avatar</label>
                            <input name="na" placeholder="newavatar">
                            <label>Change birth date</label>
                            <input name="bd" placeholder="birthdate">
                            <label>Change password</label>
                            <input name="cp" placeholder="changepassword">
                            <label>Confirm change password</label>
                            <input name="ccp" placeholder="confirmpassword">
                            
                            <input class= "submitreg" name="save" type="save" value="SAVE">
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
