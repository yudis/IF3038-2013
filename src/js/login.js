window.onload = function() {
	window.registerform = {
		username: $id("registerform-username"),
		password: $id("registerform-password"),
		password2: $id("registerform-password2"),
		email: $id("registerform-email"),
		fullname: $id("registerform-fullname"),
		birthdate: $id("registerform-birthdate"),
		avatar: $id("registerform-avatar"),
		//equalsUPE: $id("registerform-equalsUPE")
	}
	window.registerform_error_message = {
		username: 'Minimal 5 characters, only alphabetic, numeric and underscore allowed',
		password: 'Minimal 8 characters',
		password2: 'Must same with password',
		email: 'Standard email format',
		fullname: 'At least contains firstname and lastname',
		birthdate: 'Incorrect date format',
		avatar: 'Format must be .jpg',
		equalsUPE: 'Password cannot be same with Username and Email'
	}
	window.registerform_validation = {};
	register_check();
	login_check();

	for(key in window.registerform) {
		window.registerform[key].onkeyup = function(e) {
			register_check();
			var key = $id(this.id).id.split('-')[1];
			
			if(!window.registerform_validation[key]) {
				if(!hasClass(window.registerform[key], 'error')) {
					var e = document.createElement('div');
					addClass(e, 'errorMessage');
					e.innerHTML = registerform_error_message[key];
			
					addClass(window.registerform[key], 'error');
					window.registerform[key].parentNode.appendChild(e);
				}
			} else if(hasClass(window.registerform[key], 'error')) {
				deleteClass(window.registerform[key], 'error');
				window.registerform[key].parentNode.removeChild(window.registerform[key].parentNode.lastChild);
			}
		}
	}
	
	//init_date();
	
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
	
	var ok = true;

	window.registerform_validation['username'] = regex_username.test($id("registerform-username").value);
	window.registerform_validation['password'] = regex_password.test($id("registerform-password").value);
	window.registerform_validation['equalsUPE'] = ($id("registerform-password").value != $id("registerform-username").value) && ($id("registerform-password").value != $id("registerform-email").value);
	window.registerform_validation['password2'] = ($id("registerform-password").value == $id("registerform-password2").value);
	window.registerform_validation['fullname'] = regex_fullname.test($id("registerform-fullname").value);
	window.registerform_validation['birthdate'] = (regex_birthdate.test($id("registerform-birthdate").value)) && (parseInt($id("registerform-birthdate").value.split('-')[0]) >= 1955);
	window.registerform_validation['email'] = regex_email.test($id("registerform-email").value);
	window.registerform_validation['avatar'] = (avatar_ext == "jpg") || (avatar_ext == "jpeg");

	for(key in registerform_validation) {
		if(!registerform_validation[key]) {
			
			$id("registerform-submit").disabled = true;
			ok = false;
		} 
	}
	if(ok)
		$id("registerform-submit").disabled = false;
}

function init_date() {
	// init date
	var year = $id('registerform-birthdate-year');
	var month = $id('registerform-birthdate-month');
	var date = $id('registerform-birthdate-date');
	window.tanggal = [];
	month.style.visibility = 'hidden';
	date.style.visibility = 'hidden';
	for(i = 1955; i <= 2013; i++) {
		var e = document.createElement('option');
		e.setAttribute('value', i);
		e.innerHTML = i;
		year.appendChild(e);
	}
	for(i = 1; i <= 12; i++) {
		var e = document.createElement('option');
		e.setAttribute('value', i);
		e.innerHTML = i;
		month.appendChild(e);
	}
	for(i = 1; i <= 31; i++) {
		var e = document.createElement('option');
		e.setAttribute('value', i);
		e.innerHTML = i;
		date.appendChild(e);
		window.tanggal.push(e);
	}
	year.onchange = function() {
		month.style.visibility = 'visible';
	}
	month.onchange = function() {
		date.style.visibility = 'visible';
		var limit = [31,29,31,30,31,30,31,31,30,31,30,31];
		var lim = limit[month.value];
		if(month.value == 2) {
			lim = ((year.value % 4 == 0) && (year.value % 100 != 0)) || (year.value % 400 == 0) ? 29 : 28;
		}
		for(i = 1; i <= 31; i++) {
			if(i <= lim) {
				window.tanggal[i].style.visibility = 'visible';
			} else {
				window.tanggal[i].style.visibility = 'hidden';
			}
		}
	}
}
/*
function try_login() {
	if (!Session.login(
		$id("loginform-username").value,
		$id("loginform-password").value
	)) {
		alert("Incorrect username or password");
	} else{
		//alert("Login success");
		//window.location = "Profil.html"
		redirect();
	}
}
*/

