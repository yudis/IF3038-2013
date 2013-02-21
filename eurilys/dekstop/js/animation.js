function toggle_visibility(id) {
	var e = document.getElementById(id);
	if(e.style.display == 'block')
		e.style.display = 'none';
	else
		e.style.display = 'block';
}

var loginForm = document.getElementById("login_form");
function logincheck()  {
	if ((document.getElementById("login_username").value =="admin") && (document.getElementById("login_password").value =="adminadmin"))
		window.location.href = "src/Dashboard.html";
	else
	{
		alert("Wrong username or password");
		document.getElementById("login_username").value = "";
		document.getElementById("login_password").value = "";
		return false;
	}
}