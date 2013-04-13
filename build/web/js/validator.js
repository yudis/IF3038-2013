function toggle_visibility(id) {
	var e = document.getElementById(id);
	if(e.style.display == 'block')
		e.style.display = 'none';
	else
		e.style.display = 'block';
}

var v_agree = false;
var regValid = 0;
function reCheck() {
	var username = document.getElementById("uname").value;
	var password = document.getElementById("pass").value;
	var cpassword = document.getElementById("cpass").value;
	var fname = document.getElementById("name").value;
	var mail = document.getElementById("email").value;
	var d_o_b = document.getElementById("dob").value;
	var avatar = document.getElementById("ava").value;
	
	//username
	if ((username.length > 4) && (username != password)){
		document.getElementById("uname_validation").src="images/yes.png";
		regValid = 1;
	}
	else{
		document.getElementById("uname_validation").src="images/no.png";
		regValid = 0;
	}
	document.getElementById("uname_validation").style.display="block";
	
	//password
	if((password.length>7) && (password!=username) &&(password!=email)){
		document.getElementById("pass_validation").src="images/yes.png";
		regValid=1;
	}
	else{
		document.getElementById("pass_validation").src="images/no.png";
		regValid=0;
	}
	document.getElementById("password_validation").style.display="block";
	
	//confirm
	if((cpassword!=password){
		document.getElementById("cpass_validation").src="images/no.png";
		regValid=0;
	}
	else{
		document.getElementById("cpass_validation").src="images/yes.png";
		regValid=1;
	}
	document.getElementById("cpass_validation").style.display="block";
	
	//name
	if((fname.indexOf(' ')>=0){
		document.getElementById("name_validation").src="images/yes.png";
		regValid=1;
	}
	else{
		document.getElementById("name_validation").src="images/no.png";
		regValid=0;
	}
	document.getElementById("name_validation").style.display="block";
	
	//email
	var emailRegEx=/^[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}$/i;
	if((mail==password) || (mail.search(emailRegEx)==-1){
		document.getElementById("email_validation").src="images/no.png";
		regValid=0;
	}
	else{
		document.getElementById("email_validation").src="images/yes.png";
		regValid=1;
	}
	document.getElementById("email_validation").style.display="block";
	
	//date of birth
	if(d_o_b!=""){
		document.getElementById("dob_validation").src="images/yes.png";
		regValid=1;
	}
	else{
		document.getElementById("dob_validation").src="images/no.png";
		regValid=0;
	}
	document.getElementById("dob_validation").style.display="block";
	
	//avatar
	var extension=avatar.split('.');
	if((extension[1]=="jpg") || (extension[1]=="jpeg"){
		document.getElementById("ava_validation").src="images/yes.png";
		regValid=1;
	}
	else{
		document.getElementById("ava_validation").src="images/no.png";
		regValid=0;
	}
	document.getElementById("ava_validation").style.display="block";
}

/*function validUname(str){
	var patt = /^[-a-zA-Z0-9_\.]{5,}$/;
	if (patt.test(str) && str!=document.getElementById("password_validation").value){
		v_uname = true;
		//alert("valid");
	}else{
		v_uname = false;
	}
	isValidForm();
}

function validPass(str){
	var patt = /^[a-zA-Z0-9]{8,}$/;
	if (patt.test(str) && str!=document.getElementById("uname").value && str!=document.getElementById("email").value){
		v_pass = true;
		//alert("valid");
	}else{
		v_pass = false;
	}
	isValidForm();
}

function validCpass(str){
	if (str!="" && str==document.getElementById("pass").value){
		v_cpass = true;
		//alert("valid");
	}else{
		v_cpass = false;
	}
	isValidForm();
}

function validName(str){
	var patt = /^[-a-zA-Z]+( +[-a-zA-Z]+)+$/;
	if (patt.test(str)){
		v_name = true;
		//alert("valid");
	}else{
		v_name = false;
	}
	isValidForm();
}

function validDOB(str){
	var patt = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	if (patt.test(str)){
		v_dob = true;
		//alert("valid");
	}else{
		v_dob = false;
	}
	isValidForm();
}

function validEmail(str){
	var patt = /^[-a-zA-Z0-9_\.]+@[-a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/;
	if (patt.test(str) && str!=document.getElementById("pass").value){
		v_email = true;
		//alert("valid");
	}else{
		v_email = false;
	}
	isValidForm();
}

function validAva(str){
	var patt = /.+\.(jpg|jpeg)$/i;
	if (patt.test(str)){
		v_ava = true;
		//alert("valid");
	}else{
		v_ava = false;
	}
	isValidForm();
}*/

function validAgree(obj){
	if (obj.checked){
		v_agree = true;
		//alert("valid");
	}else{
		v_agree = false;
	}
	isValidForm(); 
}