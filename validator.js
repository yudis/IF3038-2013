var v_uname, v_pass, v_cpass, v_name, v_dob, v_email, v_ava, v_gender, v_agree = false;

function isValidForm(){
	if (v_uname && v_pass && v_cpass && v_name && v_dob && v_email && v_ava && v_gender && v_agree){
		document.getElementById("register").disabled=false;
	}else{
		document.getElementById("register").disabled=true;
	}
}

function validUname(str){
	var patt = /^[-a-zA-Z0-9_\.]{5,}$/;
	if (patt.test(str) && str!=document.getElementById("pass").value){
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
}

function validGender(str){
	if (str!=""){
		v_gender = true;
		//alert("valid");
	}else{
		v_gender = false;
	}
	isValidForm();
}

function validAgree(obj){
	if (obj.checked){
		v_agree = true;
		//alert("valid");
	}else{
		v_agree = false;
	}
	isValidForm();
      
}