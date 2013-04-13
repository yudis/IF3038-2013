function postRequest(strURL){
var xmlHttp;
	if(window.XMLHttpRequest){ // For Mozilla, Safari, ...
		var xmlHttp = new XMLHttpRequest();
	}
	else if(window.ActiveXObject){ // For Internet Explorer
		var xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlHttp.open('POST', strURL, true);
	xmlHttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
	xmlHttp.onreadystatechange = function(){
		if (xmlHttp.readyState == 4){
			ajaxloginupdate(xmlHttp.responseText);
		}
	}
	xmlHttp.send(strURL);
}

function ajaxloginupdate(str){
	var temp = str;
	var temp2 = str.split("\n");
	
	if(temp2[2]=="ok"){
		alert("Welcome");
		setTimeout("location.href = 'home.jsp?link=selamatdatang'",1);
	}else if(temp2[2]!="ok"){
		alert("Username dan/atau password salah");
	}
}

function loginajax(){
	var username = window.document.form.username.value;
	var password = window.document.form.password.value;
	var url = "proseslogin.jsp?username=" + username + "&password=" +password ;
	postRequest(url);
} 