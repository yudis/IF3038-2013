// JavaScript Document

function login(){
	var username = document.getElementById('userlogin').value;
	var password = document.getElementById('passlogin').value;
	if((username=="hanif" && password=="hanif") || (username=="patrick" && password=="patrick") || (username=="novry" && password=="novry"))
   		window.location="Dashboard.html";
	else alert("Invalid Username or Password!")
}

function validate_Output(regexp, input, syntaxerror, syntaxvalid, idToWrite) {
	var valid = (regexp.test(input));	
	syntaxvalid = '<span class="valid">' + syntaxvalid + '</span>';
	syntaxerror = '<span class="error">' + syntaxerror + '</span>';
	document.getElementById(idToWrite).innerHTML = valid ? syntaxvalid : syntaxerror;
	return valid;
	if (valid == syntaxvalid) {}
}

function validate_userid() {
		validate_Output(/^.{5,}$/, document.getElementById('userid').value,'INVALID','VALID','name_info');
}

function halo(){
	if (validate_userid==true) return 1;
}

function validate_passid() {
	validate_Output(/^.{8,}$/, document.getElementById('passid').value,'INVALID','VALID','pass_info');
}

function validate_confirmpass() {
	var pw1 = document.getElementById('passid').value;
	var pw2 = document.getElementById('confirmpass').value;
	if(pw2 != pw1)	document.getElementById('confpass_info').innerHTML = '<span class="error">INVALID</span>';
	else			document.getElementById('confpass_info').innerHTML = '<span class="valid">VALID</span>';
}

function validate_fullname() {
	validate_Output(/^[a-zA-Z ]{1,}$/,document.getElementById('fullname').value,'INVALID','VALID','fullname_info');
}

function validate_email() {
	validate_Output(/^\w+@(\w+\.)+\w{2,}$/, document.getElementById('email').value,'INVALID','VALID','email_info');
}

function validate_avatar() {
	validate_Output(/\.(jpg|jpeg)$/, document.getElementById('avatar').value,'INVALID','VALID','avatar_info');
}

function validate_register(){
	document.getElementsByName("register")[0].disabled = true;
}


/*


function validateBirthdate() {
	if(!validateAndOutput(/^\d{4}(-\d{2}){2}$/, document.getElementById('bd').value,'ERROR!','OK','bd_info')) return null;
	date = document.getElementById('bd').value.split('-');
	validDate = new Date(date[0], date[1], date[2]);
	valid = validDate.getFullYear() == date[0] && validDate.getMonth() == date[1] && validDate.getDate() == date[2];
	validateAndOutput(/^.$/, valid ? 'a' : 'asdf','ERROR!','OK','bd_info');
}

function validateKeyword() {
	validateAndOutput(/^.{3,}$/, document.getElementById('keyword').value,'ERROR!','OK','keyword_info');
}*/