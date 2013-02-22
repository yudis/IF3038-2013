function validating(month, day, year){
	var name = document.forms["input"]["nama"].value;
	var tag = document.forms["input"]["tag"].value;
	var file = document.forms["input"]["attach"].value;
	var asignee = document.forms["input"]["asignee"].value;
	if (name == ""){
		alert("Nama task tidak boleh kosong");
		return false;
	}
	else if (name.length > 25){
		alert("Nama task salah");
		return false;
	}
	else if (!name.match(/[a-zA-Z0-9]+$/i)){
		//document.getElementById("nameicon").src="pict/centang.png";
		alert("Nama task salah");
		return false;
	}
	else if(file == ""){
		alert("Attachment tidak boleh kosong");
		return false;
	}
	else if (!file.match("^.+(\.(?i)(jpg|jpeg|png|gif|bmp|wmv|avi|flv|doc|docx))$")){
		alert("File tidak valid");
		return false;
	}
	else if (asignee == ""){
		alert("Asignee tidak boleh kosong");
		return false;
	}
	else if (tag == ""){
		alert("Tag tidak boleh kosong");
		return false;
	}
}

var idActiveTask;
var ActiveButton;
function display(id,button){
	if (idActiveTask == null) {
		idActiveTask = "edited";
	}
	document.getElementById(idActiveTask).style.display = "none";
	idActiveTask = id;
	document.getElementById(id).style.display = "block";
	displaybutton(button);
}
			
function displaybutton(but){
	if (ActiveButton == null){
		ActiveButton = "tombol_edit";
	}
	document.getElementById(ActiveButton).style.display = "none";
	ActiveButton = but;
	document.getElementById(but).style.display = "block";
}