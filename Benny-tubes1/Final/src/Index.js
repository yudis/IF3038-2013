function validateLogin(e) {
	e.preventDefault();
	var user = document.login.user.value;
	var password = document.login.password.value;
	
	if (user == "admin" ){
		if (password == "admin"){
			alert("Berhasil");
			window.location="./dashboardfix.html";
		}
		else {
			alert("Password Salah!");
		}
	} else{
		alert("User Salah!");
	}
}

function checkSubmit(e) {
   if (e && e.keyCode == 13) {
	  validateLogin();
   }
}

function goToDash(){
	window.location="dashboardfix.html";
}

function checkUser(){
	var Ruser = document.regis.Ruser.value;
	var Rpass = document.regis.RPass.value;
	
	if ((Ruser.length>=5) && (Ruser != Rpass)){
		<!-- benar -->
		document.getElementById("userpic").src="../image/true.png";
	} else{
		document.getElementById("userpic").src="../image/false.png";
	}
	
	openReg();
}

function checkPass(){
	var Ruser = document.regis.Ruser.value;
	var RPass = document.regis.RPass.value;
	var Remail = document.regis.REmail.value;
	
	if ((RPass.length>=8) && (Ruser != RPass) && (RPass != Remail)){
		<!-- benar -->
		document.getElementById("passpic").src="../image/true.png";
	} else{
		document.getElementById("passpic").src="../image/false.png";
	}
	
	checkConfPass();
}

function checkConfPass(){
	var Rpass = document.regis.RPass.value;
	var RConfpass = document.regis.RConfpass.value;
	
	if (Rpass ==RConfpass){
		<!-- benar -->
		document.getElementById("confpasspic").src="../image/true.png";
	} else{
		document.getElementById("confpasspic").src="../image/false.png";
	}
	
	openReg();
}

function checkNLengkap(){
	var RNlengkap = document.regis.RNlengkap.value;
	
	if (RNlengkap.match(/([a-zA-Z])+(\s)+([a-zA-Z])/))
	{
		<!-- benar -->
		document.getElementById("nlengkappic").src="../image/true.png";
	} else{
		document.getElementById("nlengkappic").src="../image/false.png";
	}
	
	openReg();
}

function checkEmail(){
	var REmail = document.regis.REmail.value;
	
	if (REmail.match(/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/))
	{
		<!-- benar -->
		document.getElementById("emailpic").src="../image/true.png";
	} else{
		document.getElementById("emailpic").src="../image/false.png";
	}
	
	openReg();
}

function checkAvatar(){
	var extensions = new Array("jpg","jpeg");
	var image_file = document.regis.Ravatar.value;
	var image_length = document.regis.Ravatar.value.length;
	var pos = image_file.lastIndexOf('.') + 1;
	var ext = image_file.substring(pos, image_length);
	var final_ext = ext.toLowerCase();
	var flag = 0;

	for (i = 0; i < extensions.length && flag==0; i++)
	{
		if(extensions[i] == final_ext)
		{
			flag = 1;
		}
	}
	
	if (flag==1){
		document.getElementById("avatarpic").src="../image/true.png";
	}else{
		document.getElementById("avatarpic").src="../image/false.png";
	}
	
	openReg();
}

function openReg(){
	if ((document.getElementById("emailpic").src.indexOf("image/true.png")!=-1) 
	&& (document.getElementById("nlengkappic").src.indexOf("image/true.png")!=-1) 
	&& (document.getElementById("confpasspic").src.indexOf("image/true.png")!=-1) 
	&& (document.getElementById("passpic").src.indexOf("image/true.png")!=-1)
	&& (document.getElementById("userpic").src.indexOf("image/true.png")!=-1)
	&& (document.getElementById("ntanggalpic").src.indexOf("image/true.png")!=-1)
	&& (document.getElementById("avatarpic").src.indexOf("image/true.png")!=-1))
	{
		document.getElementById('regButton').className  = "lSubmit";
	} else{
		document.getElementById('regButton').className  = "popupbutton";
	}
}

function checkTanggal(){
	var Rtanggal = document.regis.Rtanggal.value;
	
	if (Rtanggal.match(/([0-9])+(-)+([0-9])+(-)+([0-9])/))
	{
		<!-- benar -->
		document.getElementById("ntanggalpic").src="../image/true.png";
	} else{
		document.getElementById("ntanggalpic").src="../image/false.png";
	}
	
	openReg();
}

function doneReg(){
	alert("Registrasi Berhasil! (tapi bohong) ");
	window.location = "./Index.html";
}

function doneAdd(e){
	e.preventDefault();
	alert("Penambahan Tugas Berhasil! (tapi bohong) ");
	
	window.location = "./dashboardfix.html"; 	
}

function checkpostal(){
	var jpg=/\.jpg$/
	var png=/\.png$/
	var pdf=/\.pdf$/
	var mp4=/\.mp4$/
	if (document.getElementById("ali").value.search(jpg)!=-1)  
	{
		alert("File yang dipilih berformat jpg.");
	}
	else if (document.getElementById("ali").value.search(png)!=-1)
	{
		alert("File yang dipilih berformat png.");
	}
	else if (document.getElementById("ali").value.search(pdf)!=-1)
	{
		alert("File yang dipilih berformat pdf.");
	}
	else if (document.getElementById("ali").value.search(mp4)!=-1)
	{
		alert("File yang dipilih berformat mp4.");
	}
	else{
		alert("File yang dipilih harus berformat gambar, video, atau file!");
		document.getElementById("ali").value = "";
	}
}

function editing(){
	if(document.getElementById("changeOrEdit").value =="Edit"){
		document.getElementById("deadline").style.display="none";
		document.getElementById("dua").removeAttribute("disabled");
		document.getElementById("tiga").removeAttribute("disabled");
		document.getElementById("tag").removeAttribute("disabled");
		document.getElementById("tanggal").style.display = "block";
		document.getElementById("changeOrEdit").value ="Save";
	}
	else{
		document.getElementById("deadline").style.display="block";
		document.getElementById("dua").setAttribute("disabled");
		document.getElementById("tiga").setAttribute("disabled");
		document.getElementById("tag").setAttribute("disabled");
		document.getElementById("tanggal").style.display = "none";
		document.getElementById("changeOrEdit").value ="Edit";
	}
}
function gantiNama(){
	var parame = window.top.location.search.substring(1);
	
	var n=parame.split("^");
	document.getElementById("namaTugas").innerHTML =  decodeURI(n[1]);
	document.getElementById("deadline").value =   decodeURI(n[2]);
	document.getElementById("tag").value =   decodeURI(n[3]);
	
}
function changeEdit(){
	if(document.getElementById("changeOrEdit").value =="Edit"){
		document.getElementById("changeOrEdit").value ="Save";
		alert("ddd");
	}
	else{
	document.getElementById("changeOrEdit").value ="Edit";
	}
		
}



