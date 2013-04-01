function getXmlHttpRequest() {
	var xmlHttpObj;
	
	if(window.XMLHttpRequest)
		xmlHttpObj = new XMLHttpRequest();
	else {
		try {
			xmlHttpObj = new ActiveXObject("Msxm12.XMLHTTP");
		}
		catch(e) {
			try {
				xmlHttpObj = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch(e) {
				xmlHttpObj = false;
			}
		}
	}
	return xmlHttpObj;
}

function initindex() {
	document.getElementById('registerbutton').disabled = true;
}

function Validation(){
	var valid = "true.png";
	if(	getSrc('icoUsername') == valid && 
		getSrc('icoPassword') == valid &&
		getSrc('icoConfirmPassword') == valid &&
		getSrc('icoName') == valid &&
		getSrc('icoEmail') == valid && 
		getSrc('icoAvatar') == valid
	)
		document.getElementById('registerbutton').disabled = false;
	else
		document.getElementById('registerbutton').disabled = true;
}


function getSrc(ico){
	var	fullPath = document.getElementById(ico).src;
	var nameFile = fullPath.substring(fullPath.lastIndexOf('/') + 1);
	return nameFile;
}

function vdUsername(){
	var temporary = document.getElementById('txUsername').value;
	var temporary_password = document.getElementById('txPassword').value;
	if(temporary.length >= 5 && temporary != temporary_password){
		window.xmlhttp = getXmlHttpRequest();
		if(!window.xmlhttp)
			return;
		var username = encodeURIComponent(temporary);
		var query = 'username=' + username;
		window.xmlhttp.open('POST', 'validator.php', true);
		window.xmlhttp.onreadystatechange = validateUser;
		window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		window.xmlhttp.send(query);
	} else {
		document.getElementById('icoUsername').src="image/false.png";
		Validation();
	}
}

function validateUser() {
	if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
		var response = window.xmlhttp.responseText;
		if(response == 'free')
			document.getElementById('icoUsername').src="image/true.png";
		else {
			document.getElementById('icoUsername').src="image/false.png";
			alert('Username has been used');
		}
		Validation();
	}
}

function vdPassword(){
	var temporary = document.getElementById('txPassword').value;
	var temporary_username = document.getElementById('txUsername').value;
	var temporary_email = document.getElementById('txEmail').value;
	if(temporary.length >= 8 && temporary != temporary_username && temporary != temporary_email){
		document.getElementById('icoPassword').src="image/true.png";
	} else {
		document.getElementById('icoPassword').src="image/false.png";	
	}	
	Validation();
}

function vdConfirmPassword(){
	var temporary = document.getElementById('txConfirmPassword').value;
	var temporary_password = document.getElementById('txPassword').value;
	if(temporary.length >= 8 && temporary == temporary_password){
		document.getElementById('icoConfirmPassword').src="image/true.png";
	} else {
		document.getElementById('icoConfirmPassword').src="image/false.png";	
	}	
	Validation();
}

function vdName(){
	var temporary = document.getElementById('txName').value;
	temporary = temporary.toLowerCase();
	var regex = new RegExp("([a-z]) ([a-z])");
	if(regex.test(temporary)){
		document.getElementById('icoName').src="image/true.png";
	} else {
		document.getElementById('icoName').src="image/false.png";	
	}	
	Validation();
}

function vdEmail(){
	var temporary = document.getElementById('txEmail').value;
	var temporary_password = document.getElementById('txPassword').value;
	var pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9])+\.([a-zA-Z])+([a-zA-Z])+/;
    if(pattern.test(temporary) && temporary != temporary_password){
		window.xmlhttp = getXmlHttpRequest();
		if(!window.xmlhttp)
			return;
		var email = encodeURIComponent(temporary);
		var query = 'email=' + email;
		window.xmlhttp.open('POST', 'validator.php', true);
		window.xmlhttp.onreadystatechange = validateEmail;
		window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		window.xmlhttp.send(query);
    }
	else {   
		document.getElementById('icoEmail').src="image/false.png";
		Validation();
	}
}

function validateEmail() {
	if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
		var response = window.xmlhttp.responseText;
		if(response == 'free')
			document.getElementById('icoEmail').src="image/true.png";
		else {
			document.getElementById('icoEmail').src="image/false.png";
			alert('Email has been used');
		}
		Validation();
	}
}

function vdAvatar(){
	var fup = document.getElementById('avatar');
    var fileName = fup.value;
    var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
	ext = ext.toLowerCase();
    if(ext == "jpeg" || ext == "jpg" || fup == ''){
		document.getElementById('icoAvatar').src="image/true.png";
    } else {
		document.getElementById('icoAvatar').src="image/false.png";
        alert('Upload a JPEG images only');       
    }
	Validation();
}

function login() {
	window.xmlhttp = getXmlHttpRequest();
	if(!window.xmlhttp)
		return;
	var query = 'user=' + encodeURIComponent(document.getElementById('userheader').value) + '&pass=' + encodeURIComponent(document.getElementById('passheader').value);
	window.xmlhttp.open('POST', 'login.php', true);
	window.xmlhttp.onreadystatechange = canLogin;
	window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	window.xmlhttp.send(query);
}

function loginPressed(keyCode) {
	if(keyCode == 13) {
		window.xmlhttp = getXmlHttpRequest();
		if(!window.xmlhttp)
			return;
		var query = 'user=' + encodeURIComponent(document.getElementById('userheader').value) + '&pass=' + encodeURIComponent(document.getElementById('passheader').value);
		window.xmlhttp.open('POST', 'login.php', true);
		window.xmlhttp.onreadystatechange = canLogin;
		window.xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
		window.xmlhttp.send(query);
	}
}

function canLogin() {
	if(window.xmlhttp.readyState == 4 && window.xmlhttp.status == 200) {
		var response = window.xmlhttp.responseText;
		if(response == 'notsuccess')
			alert('Username and password are not match.');
		else if(response == 'success')
			window.location.replace('home.php');
		else
			window.location.replace('index.php');
	}
}