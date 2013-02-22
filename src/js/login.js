/*----- Bagian Login ----*/
var login_area = document.getElementById("login_area");
login_area.onmouseover = function()
{
	document.getElementById("login_form_wrap").className = "appear";
	document.getElementById("login_button").className = "login_button_appear";
	document.getElementById("login_link").className = "login_link_appear";
	document.getElementById("border_login").className = "appear";
}

login_area.onmouseout = function()
{
	document.getElementById("login_form_wrap").className = "";
	document.getElementById("login_button").className = "";
	document.getElementById("login_link").className = "";
	document.getElementById("border_login").className = "";
}

var form_login = document.getElementById("login_form");
form_login.onsubmit = function()
{
	var userlist =  JSON.parse(localStorage.MOA_userList);
	for (i=0;i<userlist.length;++i)
	{
		if ((document.getElementById("login_username").value ==userlist[i].userName) &&
		(document.getElementById("login_password").value ==userlist[i].passWord))
		{
			sessionStorage.MOA_userSession = document.getElementById("login_password")+" "+document.getElementById("login_username");
			sessionStorage.MOA_userId = i;
			return true;
		}
	}
	alert("Username dan password yang dimasukkan salah.");
	document.getElementById("login_username").value = "";
	document.getElementById("login_password").value = "";
	return false;
}