var regValid = 1;
function editProfileCheck() {
	var username = document.getElementById("edit_username").value;
	var email = document.getElementById("edit_email").value;
	var password = document.getElementById("edit_password").value;
	var confirm = document.getElementById("edit_password_confirm").value;
	var name = document.getElementById("fullname").value;
	var birthdate = document.getElementById("birthdate").value;
	var avatar = document.getElementById("avatar").value;
	//alert(password+ " " + confirm + " " + name + " " + birthdate + " " +avatar);
	
	//check password
	if ((password.length > 7) && (password != username) && (password != email)) {
		document.getElementById("password_validation").src = "../img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("password_validation").src = "../img/no.png";
		regValid = 0;
	}
	document.getElementById("password_validation").style.display = "block";

	//check confirm
	if ((confirm != password) || (confirm == '') || (confirm == null)) {
		document.getElementById("confirm_validation").src = "../img/no.png";
		regValid = 0;
	}
	else
	if ( (confirm == password) && (password.length > 7) ) {
		document.getElementById("confirm_validation").src = "../img/yes.png";
		regValid = 1;
	}
	document.getElementById("confirm_validation").style.display = "block";
	
	if ((password == confirm) && (password.length == 0)) {
		regValid = 1; 
		document.getElementById("password_validation").src = "../img/yes.png";
		document.getElementById("confirm_validation").src = "../img/yes.png";
	}
	
	//check name
	if (name.indexOf(' ') >= 0) {
		document.getElementById("name_validation").src = "../img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("name_validation").src = "../img/no.png";
		regValid = 0;
	}
	document.getElementById("name_validation").style.display = "block";
	
	//check birthday
	/*
	if (birthdate != "") {
		document.getElementById("birthdate_validation").src = "../img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("birthdate_validation").src = "../img/no.png";
		regValid = 0;
	}
	document.getElementById("birthdate_validation").style.display = "block"; */
	
	//check avatar
	/*
	var extension = avatar.split('.');
	if ( (extension[1] == "jpg") || (extension[1] == "jpeg") ) {
		document.getElementById("avatar_validation").src = "../img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("avatar_validation").src = "../img/no.png";
		regValid = 0;
	}
	document.getElementById("avatar_validation").style.display = "block"; */
	
	if (regValid == 1) {
		document.getElementById('edit_profile_submit').removeAttribute("disabled");
	}
	else {
		document.getElementById('edit_profile_submit').disabled = "disabled";
	}
}