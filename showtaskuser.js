function getTaskUser() {
	var xmlhttp;
	if (window.XMLHttpRequest) {// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp=new XMLHttpRequest();
	} else {// code for IE6, IE5
		xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}

	//alert(url);
	xmlhttp.onreadystatechange=function() {
		if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById("ayam").innerHTML=xmlhttp.responseText;
			//alert(xmlhttp.responseText);
		}
	}	
	xmlhttp.open("GET","gettaskuser.php",true);	
	xmlhttp.send();
}