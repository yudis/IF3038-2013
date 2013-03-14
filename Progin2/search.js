function searchWords1(pagenum){
	var find = document.search.find.value;
	var searching = document.search.searching.value;
	var field = document.search.field.value;
	var xmlhttp;
	document.getElementById("task1").innerHTML="";
	if (find.length==0) { 
		  document.getElementById("task1").innerHTML="";
		  return;
	}
	if (window.XMLHttpRequest) {
	  // code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else {
	  // code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function() {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
		document.getElementById("task1").innerHTML=xmlhttp.responseText;
	  }
	  
	}
	
	xmlhttp.open("GET","functionsearch.php?pagenum="+pagenum+"&find="+find+"&searching="+searching+"&field="+field,true);
	xmlhttp.send();
}

function searchWords2(pagenum){
	var find = document.search.find.value;
	var searching = document.search.searching.value;
	var field = document.search.field.value;
	var xmlhttp;
	document.getElementById("task2").innerHTML="";
	if (find.length==0) { 
		  document.getElementById("task2").innerHTML="";
		  return;
	}
	if (window.XMLHttpRequest) {
	  // code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else {
	  // code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function() {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
		document.getElementById("task2").innerHTML=xmlhttp.responseText;
	  }
	  
	}
	
	xmlhttp.open("GET","functionsearch2.php?pagenum="+pagenum+"&find="+find+"&searching="+searching+"&field="+field,true);
	xmlhttp.send();
}

function searchWords3(pagenum){
	var find = document.search.find.value;
	var searching = document.search.searching.value;
	var field = document.search.field.value;
	var xmlhttp;
	document.getElementById("task3").innerHTML="";
	if (find.length==0) { 
		  document.getElementById("task3").innerHTML="";
		  return;
	}
	if (window.XMLHttpRequest) {
	  // code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	}
	else {
	  // code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange=function() {
	  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
		document.getElementById("task3").innerHTML=xmlhttp.responseText;
	  }
	  
	}
	
	xmlhttp.open("GET","functionsearch3.php?pagenum="+pagenum+"&find="+find+"&searching="+searching+"&field="+field,true);
	xmlhttp.send();
}