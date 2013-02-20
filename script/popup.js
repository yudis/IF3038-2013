// JavaScript Document

function login(){
	var username = "username";
	var password = "pass";
	if(username=="admin" && password=="admin")
   	window.location="Dashboard.html";
}

/*function formValidation(){
	var uid = document.registration.userid;
	var passid = document.registration.passid;
	var confpass = document.registration.confirmpass;
	var fullname = document.registration.fullname;
	var birth = document.registration.birth;
	var email = document.registration.email;
	var avatar = document.registration.avatar;
	if (username_validation(uid,5)){
		if (passid_validation(passid,8)){
			if (confpass_validation(passid,confpass)){
				if (fullname_validation(fullname)){
					if (email_validation(email)){
						if(avatar_validation(avatar)){
							
						}
					}
				}
			}
		}
	}
	return false;
}*/

/*function username_validation(){
	validToWrite = '<span class="valid">' + validToWrite + '</span>';
	errorToWrite = '<span class="error">' + errorToWrite + '</span>';
	var uid = document.getElementById('userid').value
	var uid_len = uid.value.length;
	if (uid_len <= 4){
		return false;
		document.getElementById('userid').innerHTML = errorToWrite;
	}
	return true;
	document.getElementById(idToWrite).innerHTML = validToWrite;
}*/

function validate_Output(regexp, input, syntaxerror, syntaxvalid, idToWrite) {
	var valid = (regexp.test(input));	
	syntaxvalid = '<span class="valid">' + syntaxvalid + '</span>';
	syntaxerror = '<span class="error">' + syntaxerror + '</span>';
	document.getElementById(idToWrite).innerHTML = valid ? syntaxvalid : syntaxerror;
	return valid;
}

function validate_userid() {
	var userid = document.getElementById('userid').value;
	var passid = document.getElementById('passid').value;
	if(userid = passid)
		document.getElementById('userid').innerHTML = '<span class="error">ERROR!</span>';
	else
		validate_Output(/^.{5,}$/, document.getElementById('userid').value,'ERROR!','OK','name_info');
}

function validate_passid() {
	validate_Output(/^.{8,}$/, document.getElementById('passid').value,'ERROR!','OK','pass_info');
}

function validate_confirmpass() {
	var pw1 = document.getElementById('passid').value;
	var pw2 = document.getElementById('confirmpass').value;
	if(pw2 != pw1)	document.getElementById('confpass_info').innerHTML = '<span class="error">ERROR!</span>';
	else			document.getElementById('confpass_info').innerHTML = '<span class="valid">OK</span>';
}

function validate_fullname() {
	validate_Output(/^[a-zA-Z ]{1,}$/,document.getElementById('fullname').value,'ERROR!','OK','fullname_info');
}

function validate_email() {
	validate_Output(/^\w+@(\w+\.)+\w{2,}$/, document.getElementById('email').value,'ERROR!','OK','email_info');
}

function validate_avatar() {
	validate_Output(/\.(jpg|jpeg)$/, document.getElementById('avatar').value,'ERROR!','OK','avatar_info');
}




/*


function validateBirthdate() {
	if(!validateAndOutput(/^\d{4}(-\d{2}){2}$/, document.getElementById('bd').value,'ERROR!','OK','bd_info')) return null;
	date = document.getElementById('bd').value.split('-');
	validDate = new Date(date[0], date[1], date[2]);
	valid = validDate.getFullYear() == date[0] && validDate.getMonth() == date[1] && validDate.getDate() == date[2];
	validateAndOutput(/^.$/, valid ? 'a' : 'asdf','ERROR!','OK','bd_info');
}

function validateDesc() {
	validateAndOutput(/^.{5,}$/, document.getElementById('desc').value,'ERROR!','OK','desc_info');
}

function validateKeyword() {
	validateAndOutput(/^.{3,}$/, document.getElementById('keyword').value,'ERROR!','OK','keyword_info');
}*/