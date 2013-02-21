function Initialization(){
	document.getElementById('btSubmit').disabled = true;
	initDateofBirth();
}

function initDateofBirth(){
	initYears();
	initMonth();
	initDay();	
}
function initYears(){
	var sel = document.getElementById('cbYear');
	var opt = null;
	for(i = 1955; i<=2013; i++) { 
		opt = document.createElement('option');
		opt.value = i;
		opt.innerHTML = i;
		sel.appendChild(opt);
	}
}
function initMonth(){
	var sel = document.getElementById('cbMonth');
	var opt = null;
	for(i = 1; i<=12; i++) { 
		opt = document.createElement('option');
		opt.value = i;
		opt.innerHTML = i;
		sel.appendChild(opt);
	}
}
function initDay(){
	var sel = document.getElementById('cbDay');
	var opt = null;
	for(i = 1; i<=31; i++) { 
		opt = document.createElement('option');
		opt.value = i;
		opt.innerHTML = i;
		sel.appendChild(opt);
	}
}
function Validation(){
	var valid = "accept.png";
	if(	getSrc('icoUsername') == valid && 
		getSrc('icoPassword') == valid &&
		getSrc('icoConfirmPassword') == valid &&
		getSrc('icoName') == valid &&
		getSrc('icoEmail') == valid && 
		getSrc('icoAvatar') == valid
	)
		document.getElementById('btSubmit').disabled = false;
	else
		document.getElementById('btSubmit').disabled = true;
}

function getSrc(ico){
	var	fullPath = document.getElementById(ico).src;
	var nameFile = fullPath.substring(fullPath.lastIndexOf('/') + 1);
	return nameFile;
}
function vdUsername(){
	var temporary = document.getElementById('txUsername').value;
	var temporary_password = document.getElementById('txPassword').value;
	if(temporary.length >= 5 && temporary != temporary_password){
		document.getElementById('icoUsername').src="Image/accept.png";
	} else {
		document.getElementById('icoUsername').src="Image/notaccept.png";	
	}
	Validation();
}

function vdPassword(){
	var temporary = document.getElementById('txPassword').value;
	var temporary_username = document.getElementById('txUsername').value;
	var temporary_email = document.getElementById('txEmail').value;
	if(temporary.length >= 8 && temporary != temporary_username && temporary != temporary_email){
		document.getElementById('icoPassword').src="Image/accept.png";
	} else {
		document.getElementById('icoPassword').src="Image/notaccept.png";	
	}	
	Validation();
}

function vdConfirmPassword(){
	var temporary = document.getElementById('txConfirmPassword').value;
	var temporary_password = document.getElementById('txPassword').value;
	if(temporary.length >= 8 && temporary == temporary_password){
		document.getElementById('icoConfirmPassword').src="Image/accept.png";
	} else {
		document.getElementById('icoConfirmPassword').src="Image/notaccept.png";	
	}	
	Validation();
}

function vdName(){
	var temporary = document.getElementById('txName').value;
	temporary = temporary.toLowerCase();
	var regex = new RegExp("([a-z]) ([a-z])");
	if(regex.test(temporary)){
		document.getElementById('icoName').src="Image/accept.png";
	} else {
		document.getElementById('icoName').src="Image/notaccept.png";	
	}	
	Validation();
}

function vdEmail(){
	var temporary = document.getElementById('txEmail').value;
	var temporary_password = document.getElementById('txPassword').value;
	var pattern=/^([a-zA-Z0-9_.-])+@([a-zA-Z0-9])+\.([a-zA-Z])+([a-zA-Z])+/;
    if(pattern.test(temporary) && temporary != temporary_password){         
		document.getElementById('icoEmail').src="Image/accept.png";
    }else{   
		document.getElementById('icoEmail').src="Image/notaccept.png";
    }
	Validation();
}

function vdAvatar(){
	var fup = document.getElementById('filename');
    var fileName = fup.value;
    var ext = fileName.substring(fileName.lastIndexOf('.') + 1);
	ext = ext.toLowerCase();
    if(ext == "jpeg" || ext == "jpg"){
		document.getElementById('icoAvatar').src="Image/accept.png";
    } else {
		document.getElementById('icoAvatar').src="Image/notaccept.png";
        alert("Upload jpg or jpeg Images only");       
    }
	Validation();
}

function login(){
	var username = document.getElementById('txUsername').value;
	var password = document.getElementById('txPassword').value;
	if(username == "admin" && password == "admin")
		window.location= "Dashboard.html";
	else
		alert("Username & Password is incorrect");
}