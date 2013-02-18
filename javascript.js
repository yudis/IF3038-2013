var loginform_submit;
var loginform_username;
var loginform_password;
var registerform_submit;
var registerform_username;
var registerform_password;
var registerform_password2;
var registerform_email;
var registerform_fullname;
var registerform_birthdate;
var registerform_avatar;

window.onload = function() {
	loginform_submit = document.getElementById("loginform-submit");
	loginform_submit.disabled = true;
	loginform_username = document.getElementById("loginform-username");
	loginform_password = document.getElementById("loginform-password");
	
	registerform_submit = document.getElementById("registerform-submit");
	registerform_submit.disabled = true;
	registerform_username = document.getElementById("registerform-username");
	registerform_password = document.getElementById("registerform-password");
	registerform_password2 = document.getElementById("registerform-password2");
	registerform_email = document.getElementById("registerform-email");
	registerform_fullname = document.getElementById("registerform-fullname");
	registerform_birthdate = document.getElementById("registerform-birthdate");
	registerform_avatar = document.getElementById("registerform-avatar");
}

function login_check() {
	var a = loginform_username.value.length != 0;
	var b = loginform_password.value.length != 0;
	if($a && b) {
		loginform_submit.disabled = false;
	} else {
		loginform_submit.disabled = true;
	}
}

function register_check() {
	console.log("check()");
	var regex_username = /^[A-Za-z0-9_]{5,}$/;
	var regex_password = /^[A-Za-z0-9!@()$%^&*#_]{8,}$/;
	var regex_email = /^[A-Za-z][A-Za-z0-9_.]*\@[A-Za-z0-9_.-]+\.[A-Za-z][A-Za-z]+$/;
	var regex_fullname = /^[A-Za-z]+ [A-Za-z \.]+$/;
	var avatar_ext = registerform_avatar.value.split('.').pop();
	var registerform_validation = [];

	registerform_validation[0] = regex_username.test(registerform_username.value);
	registerform_validation[1] = regex_password.test(registerform_password.value);
	registerform_validation[2] = (registerform_password.value != registerform_username.value) && (registerform_password.value != registerform_email.value);
	registerform_validation[3] = (registerform_password.value == registerform_password2.value);
	registerform_validation[4] = regex_fullname.test(registerform_fullname.value);
	registerform_validation[5] = (parseInt(registerform_birthdate.value.split('-')[0]) >= 1955);
	registerform_validation[6] = regex_email.test(registerform_email.value);
	registerform_validation[7] = (avatar_ext == "jpg") || (avatar_ext == "jpeg");
	for(i = 0; i < registerform_validation.length; i++) {
		if(!registerform_validation[i]) {
			registerform_submit.disabled = true;
			return;
		}
	}
	registerform_submit.disabled = false;
}
