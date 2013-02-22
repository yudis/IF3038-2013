// settings
var FILE_ENCODING = 'utf-8',
    EOL = '\n';
 

// function
var error = false;
var usr = document.getElementById("username").value;
var pwd = document.getElementById("password").value;
var email = document.getElementById("email").value;
var kpwd = document.getElementById("password_k").value;
function init(){
	usr = document.getElementById("username").value;
	pwd = document.getElementById("password").value;
	email = document.getElementById("email").value;
	kpwd = document.getElementById("password_k").value;
}
function change1(){
	if (pwd != null && usr == pwd){
		alert("Username tidak boleh sama dengan password");
		document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password\n";
		error = true;
	}
}
function check2(){
	if (usr != null && usr == pwd){
		document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password";
		error = true;
	}
	if (email != null && email == pwd){
		document.getElementById("errMsg2").innerHTML="Email tidak boleh sama dengan password";
		error = true;
	}
	if (kpwd != null && kpwd == pwd){
		document.getElementById("errMsg3").innerHTML="Password harus sama";
		error = true;
	}
}
function check3(){
	var patt=/.+@.+\.{2,}/
	if(patt.match!=null){
		document.getElementById("errMsg2").innerHTML="Email error";
		error = true;
	}
	if (email != null && email == pwd){
		document.getElementById("errMsg2").innerHTML="Email tidak boleh sama dengan password";
		error = true;
	}
}
