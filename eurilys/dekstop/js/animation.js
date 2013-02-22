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
	t += "<a href ='#' onclick='catchange(10)' id='newCategoryAdded'>";
	t += document.getElementById("add_category_name").value;
	t += "</a>";
	t += "</li>";
	document.getElementById("category_item").innerHTML += t;
}

var regValid = 0;
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
		regValid = 1;
	}
	else {
		document.getElementById("username_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("username_validation").style.display = "block";
	
	//check password
	if ((password.length > 7) && (password != username) && (password != email)) {
		document.getElementById("password_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("password_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("password_validation").style.display = "block";

	//check confirm
	if (confirm != password) {
		document.getElementById("confirm_validation").src = "img/no.png";
		regValid = 0;
	}
	if (password.length > 7) {
		document.getElementById("confirm_validation").src = "img/yes.png";
		regValid = 1;
	}
	document.getElementById("confirm_validation").style.display = "block";
	
	//check name
	if (name.indexOf(' ') >= 0) {
		document.getElementById("name_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("name_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("name_validation").style.display = "block";
	
	//check email
	var emailRegEx = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	if ((email==password) || (email.search(emailRegEx) == -1)) {
		document.getElementById("email_validation").src = "img/no.png";
		regValid = 0;
	}
	else {
		document.getElementById("email_validation").src = "img/yes.png";
		regValid = 1;
	}
	document.getElementById("email_validation").style.display = "block";
	
	//check birthday
	if (birthdate != "") {
		document.getElementById("birthdate_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("birthdate_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("birthdate_validation").style.display = "block";
	
	//check avatar
	var extension = avatar.split('.');
	if ( (extension[1] == "jpg") || (extension[1] == "jpeg") ) {
		document.getElementById("avatar_validation").src = "img/yes.png";
		regValid = 1;
	}
	else {
		document.getElementById("avatar_validation").src = "img/no.png";
		regValid = 0;
	}
	document.getElementById("avatar_validation").style.display = "block";
}

function signup() {
	if (regValid == 1) {
		window.location.href = "src/Dashboard.html";
	}
	else {
		alert("Fill your registration form correctly");
	}
}

function finishTask(i) {
	if (i == 1) {
		document.getElementById("curtask1").style.opacity = "0";
		document.getElementById("curtask2").style.top = "20px";
		document.getElementById("curtask3").style.top = "200px";
		document.getElementById("curtask4").style.top = "380px";
		document.getElementById("curtask5").style.top = "560px";
		}
		
		else {if (i == 2) {
			document.getElementById("curtask2").style.opacity = "0";
			document.getElementById("curtask3").style.top = "200px";
			document.getElementById("curtask4").style.top = "380px";
			document.getElementById("curtask5").style.top = "560px";
		}
		else {
			if (i==3) {
				document.getElementById("curtask3").style.opacity = "0";
				document.getElementById("curtask4").style.top = "380px";
				document.getElementById("curtask5").style.top = "560px";
			}
			else if (i == 4) {
				document.getElementById("curtask4").style.opacity = "0";
				document.getElementById("curtask5").style.top = "560px";
			}
			else if (i == 5){
				document.getElementById("curtask5").style.opacity = "0";
			}
		}
	}
}

function checkTaskName() {
	var taskName = document.getElementById('task_name_input').value;
	var taskNameValid = 0;
	var iChars = "~=-_^&.\\|*|,\":<>[]{}`\';()@&$#%";
	for (var i = 0; i < taskName.length; i++) {
		if (iChars.indexOf(taskName.charAt(i)) != -1){
			taskNameValid = 1; 
			break;
		}
	}
	
	if ((taskName.length > 25) || (taskNameValid == 1) || (taskName == "")) {
		//tidak valid
		document.getElementById('taskname_validation').src = "../img/no.png";
	}
	else {
		//valid
		document.getElementById('taskname_validation').src = "../img/yes.png";
	}
	document.getElementById('taskname_validation').style.display = "block";
}

function checkTaskAttachment() {
	var attachmentName = document.getElementById('attachment_upload').value;
	var dot = ".";
	if (attachmentName.indexOf(dot) != -1) {
		//valid
		document.getElementById('task_attachment_validation').src = "../img/yes.png";
	}
	else {
		//not valid
		document.getElementById('task_attachment_validation').src = "../img/no.png";
	}
	document.getElementById('task_attachment_validation').style.display = "block";
}