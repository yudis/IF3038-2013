var $loginform_submit;
var $loginform_username;
var $loginform_password;
var $registerform_submit;

function load() {
	$loginform_submit = document.getElementById("loginform-submit");
	$loginform_submit.disabled = true;
	$loginform_username = document.getElementById("loginform-username");
	$loginform_password = document.getElementById("loginform-password");
	
	$registerform_submit = document.getElementById("registerform-submit");
	$registerform_submit.disabled = true;
}

function login_check() {
	var $a = $loginform_username.value.length != 0;
	var $b = $loginform_password.value.length != 0;
	if($a && $b) {
		$loginform_submit.disabled = false;
	} else {
		$loginform_submit.disabled = true;
	}
}

function register_check() {
	
}
