function Login() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	var username = encodeURIComponent(document.getElementById('username').value);
	var password = encodeURIComponent(document.getElementById('password').value);
	var qryusername = 'uname='+username;
	var qrypassword = 'pass='+password;
	var url = 'login.php?'+qryusername+'&';
	var url = url+qrypassword;
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			//document.getElementById("ratinglike").innerHTML=xmlhttp.responseText;
			if (xmlhttp.responseText=="gagal"){
				alert("Username or Password was invalid!");
			}
			else if (xmlhttp.responseText=="berhasil"){
				window.location="dashboard.php";
			}
		}
	}
	xmlhttp.open("GET",url,true);
	xmlhttp.send();
}