function validateLogin() {
	var user = document.login.user.value;
	var password = document.login.password.value;
	
	if (user == "admin" ){
		if (password == "admin"){
			window.location="dashboardfix.html";
		}
		else {
			alert("Password Salah!");
		}
	} else{
		alert("User Salah!");
	}
}

function checkSubmit(e) {
   if (e && e.keyCode == 13) {
	  validateLogin();
   }
}

function checkUser(){
	var Ruser = document.regis.Ruser.value;
	var Rpass = document.regis.RPass.value;
	
	if ((Ruser.length>=5) && (Ruser != Rpass)){
		<!-- benar -->
		document.getElementById("userpic").src="image/true.png";
	} else{
		document.getElementById("userpic").src="image/false.png";
	}
	
	openReg();
}

function checkPass(){
	var Ruser = document.regis.Ruser.value;
	var RPass = document.regis.RPass.value;
	var Remail = document.regis.REmail.value;
	
	if ((RPass.length>=8) && (Ruser != RPass) && (RPass != Remail)){
		<!-- benar -->
		document.getElementById("passpic").src="image/true.png";
	} else{
		document.getElementById("passpic").src="image/false.png";
	}
	
	checkConfPass();
}

function checkConfPass(){
	var Rpass = document.regis.RPass.value;
	var RConfpass = document.regis.RConfpass.value;
	
	if (Rpass ==RConfpass){
		<!-- benar -->
		document.getElementById("confpasspic").src="image/true.png";
	} else{
		document.getElementById("confpasspic").src="image/false.png";
	}
	
	openReg();
}

function checkNLengkap(){
	var RNlengkap = document.regis.RNlengkap.value;
	
	if (RNlengkap.match(/([a-zA-Z])+(\s)+([a-zA-Z])/))
	{
		<!-- benar -->
		document.getElementById("nlengkappic").src="image/true.png";
	} else{
		document.getElementById("nlengkappic").src="image/false.png";
	}
	
	openReg();
}

function checkEmail(){
	var REmail = document.regis.REmail.value;
	
	if (REmail.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))
	{
		<!-- benar -->
		document.getElementById("emailpic").src="image/true.png";
	} else{
		document.getElementById("emailpic").src="image/false.png";
	}
	
	openReg();
}

function openReg(){
	if ((document.getElementById("emailpic").src.indexOf("image/true.png")!=-1) 
	&& (document.getElementById("nlengkappic").src.indexOf("image/true.png")!=-1) 
	&& (document.getElementById("confpasspic").src.indexOf("image/true.png")!=-1) 
	&& (document.getElementById("passpic").src.indexOf("image/true.png")!=-1)
	&& (document.getElementById("userpic").src.indexOf("image/true.png")!=-1))
	{
		document.getElementById('regButton').className  = "lSubmit";
		document.getElementById('regButton').diasble = true;
	} else{
		document.getElementById('regButton').className  = "popupbutton";
	}
}

function doneReg(){
	alert("Registrasi Berhasil! (tapi bohong) ");
}