<html>

		<title>Next: You Can't Do it Alone</title>
		<link rel="stylesheet" type="text/css" href="css/css.css">
		<link rel="stylesheet" type="text/css" href="css/profile.css">
		<script>
			
			function getProfile(){<!--SIGIT-->
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
						document.getElementById("content").innerHTML=xmlhttp.responseText;					
					}
				}
								
				xmlhttp.open("GET","getProfile.php?user="+localStorage.userLogin,true);
				xmlhttp.send();
				//alert(xmlhttp.responseText);
				//alert(xmlhttp.status);
			}
			function getProfHeader(){<!--SIGIT-->
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
						document.getElementById("profil1Form").innerHTML=xmlhttp.responseText;					
					}
				}
								
				xmlhttp.open("GET","getProfHeader.php?user="+localStorage.userLogin,true);
				xmlhttp.send();
				//alert(xmlhttp.responseText);
				//alert(xmlhttp.status);
			}
			function getProfForm(){<!--SIGIT-->
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
						document.getElementById("tugas_selesai").innerHTML=xmlhttp.responseText;					
					}
				}
								
				xmlhttp.open("GET","getProfForm.php?user="+localStorage.userLogin,true);
				xmlhttp.send();
				//alert(xmlhttp.responseText);
				//alert(xmlhttp.status);
			}
		</script>

	<?php include 'header.php'; ?>
    
	<body onload ="getProfile();getProfHeader();getProfForm();showUserLogin();">
		<!--====================================================================-->
		<div id="content">
			<div>
				<div id="profheader">
						
				</div>
			</div>
			<div id="profil1Form">
				
			</div>
			<div id="tugas_selesai">
			</div>
		</div>
		<!-- ==================================================================-->
		
	</body>
</html>