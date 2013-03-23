function createAJAX()
{
	var xmlhttp;
	if(window.XMLHttpRequest){
		xmlhttp = new XMLHttpRequest();
	}
	else{
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	return xmlhttp;
}

function postAJAX(xmlhttp, url, parameters)
{
	xmlhttp.open("POST", url, true);
	xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
	xmlhttp.send(parameters);
}
