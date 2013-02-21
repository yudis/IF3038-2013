// settings
var FILE_ENCODING = 'utf-8',
    EOL = '\n';
 

// function
var error = false;
function compare(){
	var usr = document.getElementById("username").value;
	var pwd = document.getElementById("password").value;
	if (pwd != null && usr == pwd){
		alert("Username tidak boleh sama dengan password");
		document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password\n";
		error = true;
	}
}
function check2(){
	var usr = document.getElementById("username").value;
	var pwd = document.getElementById("password").value;
	var email = document.getElementById("email").value;
	var kpwd = document.getElementById("password_k").value;
	if (usr != null && usr == pwd){
		document.getElementById("errMsg1").innerHTML="Username tidak boleh sama dengan password";
		error = true;
	}
	if (email != null && email == pwd){
		document.getElementById("errMsg2").innerHTML="Username tidak boleh sama dengan password";
		error = true;
	}
	if (kpwd != null && kpwd == pwd){
		document.getElementById("errMsg3").innerHTML="Username tidak boleh sama dengan password";
		error = true;
	}
}
