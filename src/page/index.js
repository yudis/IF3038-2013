// JavaScript Document
function login() {
				if (document.getElementById("login1").value == 'meckyr' && document.getElementById("login2").value == 'meckyr') {
					self.location="page/dashboard.php";
				}
			}
			var chkusername = false;
			var chkpassword = false;
			var cnfrmpassword = false;
			var chkfullname = false;
			var chkemail = false;
			var chkavatar = false;
			function hide_submit_button() {
				document.getElementById("submit").style.display = 'none';
			}
			function show_submit_button() {
				if(chkusername == true && chkpassword == true && cnfrmpassword == true && chkfullname == true && chkemail == true && chkavatar == true) {
					document.getElementById("submit").style.display = 'block';
				}
			}
			function check_username() {
				if (document.getElementById("username").value.length > 4 && document.getElementById("username").value != document.getElementById("password").value) {
					chkusername = true;	
					show_submit_button();
				} else {
					chkusername = false;
					hide_submit_button();
				}
			}
			function check_password() {
				if (document.getElementById("password").value.length > 7 && document.getElementById("password").value != document.getElementById("username").value && document.getElementById("password").value != document.getElementById("email").value) {
					chkpassword = true;
					show_submit_button();
				} else {
					chkpassword = false;
					hide_submit_button();
				}
			}
			function confirm_password() {
				if (document.getElementById("password").value == document.getElementById("password2").value) {
						cnfrmpassword = true;
						show_submit_button();
				} else {
					cnfrmpassword = false;
					hide_submit_button();
				}
			}
			function check_full_name() {
				var str = document.getElementById("fullname").value;
				var idx = str.indexOf(' ');
				if (idx > 0 && idx < str.length - 1) {
					var chr1 = str.charAt(idx - 1);
					var chr2 = str.charAt(idx + 1);
					if (chr1 != ' ' && chr2 != ' ') {
						chkfullname = true;
						show_submit_button();
					} else {
						chkfullname = false;
						hide_submit_button();
					}
				} else {
					chkfullname = false;
					hide_submit_button();
				}
			}
			function check_email() {
				var str = document.getElementById("email").value;
				var at = str.indexOf('@');
				var dot = str.lastIndexOf('.');
				if (at > 0 && (dot - at) > 1 && dot < str.length - 2) {
					chkemail = true;
					show_submit_button();
				} else {
					chkemail = false;
					hide_submit_button();
				}
			}
			function check_avatar() {
				var str = document.getElementById("avatar").value;
				var ext = str.substring(str.lastIndexOf('.') + 1, str.length);
				if (ext.toLowerCase() == "jpeg" || ext.toLowerCase() == "jpg") {
					chkavatar = true;
					show_submit_button();
				} else {
					chkavatar = false;
					hide_submit_button();
				}
			}
			function show_register_form() {
				document.getElementById("login-form").style.display = "none";
				document.getElementById("login-bottom").style.display = "none";
				document.getElementById("register-form").style.display = "block";
				document.getElementById("register-bottom").style.display = "block";
			}
			function show_login_form() {
				document.getElementById("register-form").style.display = "none";
				document.getElementById("register-bottom").style.display = "none";
				document.getElementById("login-form").style.display = "block";
				document.getElementById("login-bottom").style.display = "block";
			}