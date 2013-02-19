(function($) {
	$.$id = function(id) {
		var ret = document.getElementById(id);
		return ret;
	}
})(window);

window.onload = function() {
	$id("loginform-submit").disabled = true;
	$id("registerform-submit").disabled = true;
}

function login_check() {
	var a = $id("loginform-username").value.length != 0;
	var b = $id("loginform-password").value.length != 0;
	if($a && b) {
		$id("loginform-submit").disabled = false;
	} else {
		$id("loginform-submit").disabled = true;
	}
}

function register_check() {
	console.log("check()");
	var regex_username = /^[A-Za-z0-9_]{5,}$/;
	var regex_password = /^[A-Za-z0-9!@()$%^&*#_]{8,}$/;
	var regex_email = /^[A-Za-z][A-Za-z0-9_.]*\@[A-Za-z0-9_.-]+\.[A-Za-z][A-Za-z]+$/;
	var regex_fullname = /^[A-Za-z]+ [A-Za-z \.]+$/;
	var avatar_ext = $id("registerform-avatar").value.split('.').pop();
	var registerform_validation = [];

	registerform_validation[0] = regex_username.test($id("registerform-username").value);
	registerform_validation[1] = regex_password.test($id("registerform-password").value);
	registerform_validation[2] = ($id("registerform-password").value != $id("registerform-username").value) && ($id("registerform-password").value != $id("registerform-email").value);
	registerform_validation[3] = ($id("registerform-password").value == $id("registerform-password2").value);
	registerform_validation[4] = regex_fullname.test($id("registerform-fullname").value);
	registerform_validation[5] = (parseInt($id("registerform-birthdate").value.split('-')[0]) >= 1955);
	registerform_validation[6] = regex_email.test($id("registerform-email").value);
	registerform_validation[7] = (avatar_ext == "jpg") || (avatar_ext == "jpeg");
	for(i = 0; i < registerform_validation.length; i++) {
		if(!registerform_validation[i]) {
			registerform_submit.disabled = true;
			return;
		}
	}
	registerform_submit.disabled = false;
}
