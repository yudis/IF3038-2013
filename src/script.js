var month = document.forms["input"]["bulan"].value;
var day = document.forms["input"]["tanggal"].value;
var year = document.forms["input"]["tahun"].value;

function validDate( month, day, year) 
{  
    if ((year % 400 == 0) || (year % 4 == 0) && !(year % 100 == 0)) 
        leap = true; 
    else 
        leap = false; 
    if ((month < 1) || (month > 12) || (day < 1) || (day > 31)) 
        return false;      
    else if (((month == 4) || (month == 6) || (month == 9) || (month == 11)) && (day == 31)) 
        return false;      
    else if (month == 2 && leap && day > 29)      
        return false;          
    else if (month == 2 &&  !leap && day > 28)    
        return false;          
    else 
        return true;                              
} 

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
	else if (!validDate(month, day, year)){
		alert("Format tanggal salah");
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