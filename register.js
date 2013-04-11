var u_uname, u_pass, u_cpass, u_fname, u_birth, u_email, u_ava, u_agree = false;

function isValidForm(){
	if (u_uname && u_pass && u_cpass && u_fname && u_birth && u_email && u_ava && u_agree){
		document.getElementById("register").disabled=false;
	}else{
		document.getElementById("register").disabled=true;
	}
}

function validUname(str){
	var patt = /^[-a-zA-Z0-9_\.]{5,}$/;
	if (patt.test(str) && str!=document.getElementById("pass").value){
		u_uname = true;
		//alert("valid");
	}else{
		u_uname = false;
	}
	isValidForm();
}

function validPass(str){
	var patt = /^[a-zA-Z0-9]{8,}$/;
	if (patt.test(str) && str!=document.getElementById("uname").value && str!=document.getElementById("email").value){
		u_pass = true;
		//alert("valid");
	}else{
		u_pass = false;
	}
	isValidForm();
}

function validCpass(str){
	if (str!="" && str==document.getElementById("pass").value){
		u_cpass = true;
		//alert("valid");
	}else{
		u_cpass = false;
	}
	isValidForm();
}

function validFName(str){
	var patt = /^[-a-zA-Z]+( +[-a-zA-Z]+)+$/;
	if (patt.test(str)){
		u_fname = true;
		//alert("valid");
	}else{
		u_fname = false;
	}
	isValidForm();
}

function validBirth(str){
	var patt = /^[0-9]{4}-[0-9]{2}-[0-9]{2}$/;
	if (patt.test(str)){
		u_birth = true;
		//alert("valid");
	}else{
		u_birth = false;
	}
	isValidForm();
}

function validEmail(str){
	var patt = /^[-a-zA-Z0-9_\.]+@[-a-zA-Z0-9]+(\.[a-zA-Z]{2,})+$/;
	if (patt.test(str) && str!=document.getElementById("pass").value){
		u_email = true;
		//alert("valid");
	}else{
		u_email = false;
	}
	isValidForm();
}

function validAva(str){
	var patt = /.+\.(jpg|jpeg)$/i;
	if (patt.test(str)){
		u_ava = true;
		//alert("valid");
	}else{
		u_ava = false;
	}
	isValidForm();
}

function validAgree(obj){
	if (obj.checked){
		u_agree = true;
		//alert("valid");
	}else{
		u_agree = false;
	}
	isValidForm();
      
}