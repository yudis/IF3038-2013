(function($) {
	$.$id = function(id) {
		var ret = document.getElementById(id);
		return ret;
	}
})(window);

window.onload = function() {
	$id("loginform-submit").disabled = true;
	$id("registerform-submit").disabled = true;
	register_check();
	login_check();
}

function login_check() {
	var a = $id("loginform-username").value.length != 0;
	var b = $id("loginform-password").value.length != 0;
	if(a && b) {
		$id("loginform-submit").disabled = false;
	} else {
		$id("loginform-submit").disabled = true;
	}
}

function register_check() {
	
	var regex_username = /^[A-Za-z0-9_]{5,}$/;
	var regex_password = /^[A-Za-z0-9!@()$%^&*#_]{8,}$/;
	var regex_email = /^[A-Za-z][A-Za-z0-9_.]*\@[A-Za-z0-9_.-]+\.[A-Za-z][A-Za-z]+$/;
	var regex_fullname = /^[A-Za-z]+ [A-Za-z \.]+$/;
	var regex_birthdate = /^\d{4}\-\d{2}\-\d{2}$/;
	var avatar_ext = $id("registerform-avatar").value.split('.').pop();
	var registerform_validation = {};
	var ok = true;
	
	var registerform = {
		username: $id("registerform-username"),
		password: $id("registerform-password"),
		password2: $id("registerform-password2"),
		email: $id("registerform-email"),
		fullname: $id("registerform-fullname"),
		birthdate: $id("registerform-birthdate"),
		avatar: $id("registerform-avatar"),
		equalsUPE: $id("registerform-equalsUPE")
	}

	var error_message = {
		username: 'Minimal 5 characters, only alphabetic, numeric and underscore allowed',
		password: 'Minimal 8 characters',
		password2: 'Must same with password',
		email: 'Standard email format',
		fullname: 'At least contains firstname and lastname',
		birthdate: 'Incorrect date format',
		avatar: 'Format must be .jpg',
		equalsUPE: 'Password cannot be same with Username and Email'
	}

	registerform_validation['username'] = regex_username.test($id("registerform-username").value);
	registerform_validation['password'] = regex_password.test($id("registerform-password").value);
	registerform_validation['equalsUPE'] = ($id("registerform-password").value != $id("registerform-username").value) && ($id("registerform-password").value != $id("registerform-email").value);
	registerform_validation['password2'] = ($id("registerform-password").value == $id("registerform-password2").value);
	registerform_validation['fullname'] = regex_fullname.test($id("registerform-fullname").value);
	registerform_validation['birthdate'] = (regex_birthdate.test($id("registerform-birthdate").value)) && (parseInt($id("registerform-birthdate").value.split('-')[0]) >= 1955);
	registerform_validation['email'] = regex_email.test($id("registerform-email").value);
	registerform_validation['avatar'] = (avatar_ext == "jpg") || (avatar_ext == "jpeg");

	for(key in registerform_validation) {
		if(!registerform_validation[key]) {
			if(!hasClass(registerform[key], 'error')) {
				var e = document.createElement('div');
				addClass(e, 'errorMessage');
				e.innerHTML = error_message[key];
			
				addClass(registerform[key], 'error');
				registerform[key].parentNode.appendChild(e);
			}
			$id("registerform-submit").disabled = true;
			ok = false;
		} else if(hasClass(registerform[key], 'error')) {
			console.log(registerform[key]);
			deleteClass(registerform[key], 'error');
			registerform[key].parentNode.removeChild(registerform[key].parentNode.lastChild);
		}
	}
	if(ok)
		$id("registerform-submit").disabled = false;
}

function hasClass(x, y) {
	var classes = x.className.split(' ');
	return (classes.indexOf(y) != -1);
}

function addClass(x, y) {
	var classes = x.className.split(' ');
	if(classes.indexOf(y) != -1) {
		return;
	}
	if(x.className === '') {
		x.className += y;
	} else {
		x.className += ' ' + y;
	}
}

function deleteClass(x, y) {
	var classes = x.className.split(' ');
	var index = classes.indexOf(y);
	if(index != -1) {
		classes.splice(index, 1);
		x.className = classes.join(" ");
	}
}

