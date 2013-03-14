// JavaScript Document
function Search(){
	var container = document.getElementById('main-F0');
	var xmlhttp;
	var search_word = document.getElementById('search').value;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange=function()
	  {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
		container.innerHTML+=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","testcase.php?q="+search_word,true);
	xmlhttp.send();
	
	var newdiv ="";
	container.innerHTML += newdiv;
}