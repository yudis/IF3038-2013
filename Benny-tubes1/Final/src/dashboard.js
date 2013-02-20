// JavaScript Document

function popitup() {
	document.getElementById("buttonadd").style.color= "red";
	document.getElementById("popup").style.display = "block";
}

function closepopup(){
	document.getElementById("buttonadd").style.color= "purple";
	document.getElementById("popup").style.display = "none";
}

function initializekategori(){
	document.getElementById("buttonhome").style = "normal";
	document.getElementById("buttonoffice").style = "normal";
	document.getElementById("buttonshopping").style = "normal";
	document.getElementById("buttonpersonal").style = "normal";
}

function clickalltask(){
	document.getElementById("buttonalltask").style.fontWeight = "bold";
	document.getElementById("buttonalltask").style.backgroundColor = "rgba(255,255,255,0.6)";
	document.getElementById("buttonhome").style.fontWeight = "normal";
	document.getElementById("buttonhome").style.backgroundColor = "transparent";
	document.getElementById("buttonoffice").style.fontWeight = "normal";
	document.getElementById("buttonoffice").style.backgroundColor = "transparent";
	document.getElementById("buttonshopping").style.fontWeight = "normal";
	document.getElementById("buttonshopping").style.backgroundColor = "transparent";
	document.getElementById("buttonpersonal").style.fontWeight = "normal";
	document.getElementById("buttonpersonal").style.backgroundColor = "transparent";
	document.getElementById("buttonhome").style = "normal";
	document.getElementById("buttonshopping").style = "normal";
	document.getElementById("buttonpersonal").style = "normal";
	document.getElementById("addtaskbutton").style.display = "none";
	document.getElementById("hometask").style.display = "block";
	document.getElementById("officetask").style.display = "block";
	document.getElementById("personaltask").style.display = "block";
	document.getElementById("shoppingtask").style.display = "block";
	document.getElementById("addtaskbutton").style.display = "block";
}

function clickhome(){
	document.getElementById("buttonalltask").style.fontWeight = "normal";
	document.getElementById("buttonalltask").style.backgroundColor = "transparent";
	document.getElementById("buttonoffice").style.fontWeight = "normal";
	document.getElementById("buttonoffice").style.backgroundColor = "transparent";
	document.getElementById("buttonshopping").style.fontWeight = "normal";
	document.getElementById("buttonshopping").style.backgroundColor = "transparent";
	document.getElementById("buttonpersonal").style.fontWeight = "normal";
	document.getElementById("buttonpersonal").style.backgroundColor = "transparent";
	document.getElementById("buttonhome").style.fontWeight = "bold";
	document.getElementById("buttonhome").style.backgroundColor = "rgba(255,255,255,0.6)";
	document.getElementById("addtaskbutton").style.display = "block";
	document.getElementById("hometask").style.display = "block";
	document.getElementById("officetask").style.display = "none";
	document.getElementById("personaltask").style.display = "none";
	document.getElementById("shoppingtask").style.display = "none";
	document.getElementById("addtaskbutton").style.display = "block";
}

function clickoffice(){
	document.getElementById("buttonalltask").style.fontWeight = "normal";
	document.getElementById("buttonalltask").style.backgroundColor = "transparent";
	document.getElementById("buttonhome").style.fontWeight = "normal";
	document.getElementById("buttonhome").style.backgroundColor = "transparent";
	document.getElementById("buttonshopping").style.fontWeight = "normal";
	document.getElementById("buttonshopping").style.backgroundColor = "transparent";
	document.getElementById("buttonpersonal").style.fontWeight = "normal";
	document.getElementById("buttonpersonal").style.backgroundColor = "transparent";
	document.getElementById("buttonoffice").style.fontWeight = "bold";
	document.getElementById("buttonoffice").style.backgroundColor = "rgba(255,255,255,0.6)";
	document.getElementById("buttonhome").style = "normal";
	document.getElementById("buttonshopping").style = "normal";
	document.getElementById("buttonpersonal").style = "normal";
	document.getElementById("addtaskbutton").style.display = "block";
	document.getElementById("hometask").style.display = "none";
	document.getElementById("officetask").style.display = "block";
	document.getElementById("personaltask").style.display = "none";
	document.getElementById("shoppingtask").style.display = "none";
	document.getElementById("addtaskbutton").style.display = "block";
}

function clickpersonal(){
	document.getElementById("buttonalltask").style.fontWeight = "normal";
	document.getElementById("buttonalltask").style.backgroundColor = "transparent";
	document.getElementById("buttonhome").style.fontWeight = "normal";
	document.getElementById("buttonhome").style.backgroundColor = "transparent";
	document.getElementById("buttonoffice").style.fontWeight = "normal";
	document.getElementById("buttonoffice").style.backgroundColor = "transparent";
	document.getElementById("buttonshopping").style.fontWeight = "normal";
	document.getElementById("buttonshopping").style.backgroundColor = "transparent";
	document.getElementById("buttonpersonal").style.fontWeight = "bold";
	document.getElementById("buttonpersonal").style.backgroundColor = "white";
	document.getElementById("buttonhome").style = "normal";
	document.getElementById("buttonoffice").style = "normal";
	document.getElementById("buttonshopping").style = "normal";
	document.getElementById("addtaskbutton").style.display = "block";
	document.getElementById("hometask").style.display = "none";
	document.getElementById("officetask").style.display = "none";
	document.getElementById("personaltask").style.display = "block";
	document.getElementById("shoppingtask").style.display = "none";
	document.getElementById("addtaskbutton").style.display = "block";
}

function clickshopping(){
	document.getElementById("buttonalltask").style.fontWeight = "normal";
	document.getElementById("buttonalltask").style.backgroundColor = "transparent";
	document.getElementById("buttonhome").style.fontWeight = "normal";
	document.getElementById("buttonhome").style.backgroundColor = "transparent";
	document.getElementById("buttonoffice").style.fontWeight = "normal";
	document.getElementById("buttonoffice").style.backgroundColor = "transparent";
	document.getElementById("buttonpersonal").style.fontWeight = "normal";
	document.getElementById("buttonpersonal").style.backgroundColor = "transparent";
	document.getElementById("buttonshopping").style.fontWeight = "bold";
	document.getElementById("buttonshopping").style.backgroundColor = "rgba(255,255,255,0.6)";
	document.getElementById("buttonhome").style = "normal";
	document.getElementById("buttonoffice").style = "normal";
	document.getElementById("buttonpersonal").style = "normal";
	document.getElementById("addtaskbutton").style.display = "block";
	document.getElementById("hometask").style.display = "none";
	document.getElementById("officetask").style.display = "none";
	document.getElementById("personaltask").style.display = "none";
	document.getElementById("shoppingtask").style.display = "block";
	document.getElementById("addtaskbutton").style.display = "block";
}

function validateCategoryName(){
	if(document.getElementById("username").value != ""){
		document.getElementById("false").src = "../image/true.png";
		document.getElementById("addCategoryButton").disabled = false;
	}else{
		document.getElementById("false").src = "../image/false.png";
		document.getElementById("addCategoryButton").disabled = true;
	}
}

function addingit(){
	if(document.getElementById("false").src.indexOf("image/true.png")!=-1) {
		window.location.href = "#close";
		document.getElementById("addCategoryButton").disabled = false;
		document.getElementById("familyelement").style.display = "block";
	}
}

function activeit(){
	window.location.href = "#addCategory_form";
	document.getElementById("username").value = "";
	document.getElementById("pengguna").value = "";
	document.getElementById("false").src = "../image/false.png";
	document.getElementById("addCategoryButton").disabled = true;
}