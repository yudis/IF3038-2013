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

function add_category() {
	var t = "<li>";
	t += document.getElementById("add_category_name").value;
	t += "</li>";
	document.getElementById("category_item").innerHTML += t;
}

function regCheck() {
	var username = document.getElementById("reg_username").value;
	var password = document.getElementById("reg_password").value;
	var confirm = document.getElementById("reg_confirm").value;
	var name = document.getElementById("reg_name").value;
	var email = document.getElementById("reg_email").value;
	var birthdate = document.getElementById("reg_birthdate").value;
	var avatar = document.getElementById("avatar_upload").value;
	//alert(username + " " + password+ " " + confirm + " " +email + " " + birthdate + " " +avatar);
	
	//check username
	if ((username.length > 4) && (username != password)) {
		document.getElementById("username_validation").src = "img/yes.png";
	}
	else {
		document.getElementById("username_validation").src = "img/no.png";
	}
	document.getElementById("username_validation").style.display = "block";
	
	//check password
	if ((password.length > 7) && (password != username) && (password != email)) {
		document.getElementById("password_validation").src = "img/yes.png";
	}
	else {
		document.getElementById("password_validation").src = "img/no.png";
	}
	document.getElementById("password_validation").style.display = "block";

	//check confirm
	if (confirm != password) {
		document.getElementById("confirm_validation").src = "img/no.png";
	}
	else {
		document.getElementById("confirm_validation").src = "img/yes.png";
	}
	document.getElementById("confirm_validation").style.display = "block";
	
	//check name
	if (name.indexOf(' ') >= 0) {
		document.getElementById("name_validation").src = "img/yes.png";
	}
	else {
		document.getElementById("name_validation").src = "img/no.png";
	}
	document.getElementById("name_validation").style.display = "block";
	
	//check email
	var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	if ((email==password) || (email.search(emailRegEx) == -1)) {
		document.getElementById("email_validation").src = "img/no.png";
	}
	else {
		document.getElementById("email_validation").src = "img/yes.png";
	}
	document.getElementById("email_validation").style.display = "block";
	
	//check birthday
	
	//check avatar
	var extension = avatar.split('.');
	if ( (extension[1] == "jpg") || (extension[1] == "jpeg") ) {
		document.getElementById("avatar_validation").src = "img/yes.png";
	}
	else {
		document.getElementById("avatar_validation").src = "img/no.png";
	}
	document.getElementById("avatar_validation").style.display = "block";
}
