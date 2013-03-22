// JavaScript Documentvar nKategori = 3;
function change(task_id){
	var container = document.getElementById(task_id);
	
	var value = document.getElementById('checkbox_'+task_id).checked;
	
	var xmlhttp;
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
			container.innerHTML = xmlhttp.responseText;
	 }
	xmlhttp.open("GET","checkbox.php?idcheckbox="+task_id+"&checked="+document.getElementById('checkbox_'+task_id).checked,true);
	xmlhttp.send();
}

function showfilterbox(){
    var div = document.getElementById('f_menu');
    if (div.style.display !== 'none') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function SearchmodeHeader(){
	if (document.getElementById('al').checked){
		mode = '0';
	}else if (document.getElementById('user').checked){
		mode = '1';
	}else if (document.getElementById('categ').checked){
		mode = '2';
	}else if (document.getElementById('taskname').checked){
		mode = '3';
	}else if (document.getElementById('tag').checked){
		mode = '4';
	}
	var string = document.getElementById('search').value;
	window.location="search2.php?m="+mode+"&s="+string;
}

function Searchmode(){
	if (document.getElementById('all_rb').checked){
		mode = '0';
	}else if (document.getElementById('username_rb').checked){
		mode = '1';
	}else if (document.getElementById('category_rb').checked){
		mode = '2';
	}else if (document.getElementById('taskname_rb').checked){
		mode = '3';
	}else if (document.getElementById('tag_rb').checked){
		mode = '4';
	}
	var string = document.getElementById('search_textfield').value;
	window.location="search2.php?m="+mode+"&s="+string;
}

function Search(s,m){
	var clear = document.getElementById('main-F0');
	clear.innerHTML = "";
	
	var mode = m;
	var header = "";
	var container = document.getElementById('main-F0');
	if (mode == '1'){
		header = '<h2>Username</h2>';
	}else if (mode == '2'){
		header = '<h2>Category</h2>';
	}else if (mode == '3'){
		header = '<h2>Task</h2>';
	}else if (mode == '4'){
		header = '<h2>Task</h2>';
	}
	
	var string = s;
	var outside_open = '<div class = "tugas">';
	var open = '<div class="judulhasilsearch">';
	var close = '</div>';
	var xmlhttp;
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
		if (mode!=='0'){
			container.innerHTML+=header + xmlhttp.responseText;
		}else{
			container.innerHTML+= xmlhttp.responseText;
		}
	 }
	xmlhttp.open("GET","search.php?oo="+outside_open+"&o="+open+"&c="+close+"&m="+mode+"&s="+string,true);
	xmlhttp.send();
}

function Searchbypage(s,m,page){
	var clear = document.getElementById('main-F0');
	clear.innerHTML = "";
	
	var mode = m;
	var header = "";
	var container = document.getElementById('main-F0');
	if (mode == '1'){
		header = '<h2>Username</h2>';
	}else if (mode == '2'){
		header = '<h2>Category</h2>';
	}else if (mode == '3'){
		header = '<h2>Task</h2>';
	}else if (mode == '4'){
		header = '<h2>Task</h2>';
	}
	
	var string = s;
	var outside_open = '<div class = "tugas">';
	var open = '<div class="judulhasilsearch">';
	var close = '</div>';
	var xmlhttp;
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
		if (mode!=='0'){
			container.innerHTML+=header + xmlhttp.responseText;
		}else{
			container.innerHTML+= xmlhttp.responseText;
		}
	 }
	xmlhttp.open("GET","search.php?oo="+outside_open+"&o="+open+"&c="+close+"&m="+mode+"&s="+string+"&page="+page,true);
	xmlhttp.send();
}



