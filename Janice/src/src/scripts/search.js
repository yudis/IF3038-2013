// JavaScript Documentvar nKategori = 3;
var selectedIndex = -1;

function showfilterbox(){
    var div = document.getElementById('f_menu');
    if (div.style.display !== 'none') {
        div.style.display = 'none';
    }
    else {
        div.style.display = 'block';
    }
}

function checkedchild(){
	var elmt = document.getElementById('task');
	var status = elmt.checked;
	
	if(status = 'true'){
		document.getElementById('taskname').checked = true;
		document.getElementById('tag').checked = true;
	}
	
}

function updateAddButtonVisibility() {
    var elmt = document.getElementById('addTask');
    if (selectedIndex < 0) {
        elmt.style.display = 'none';
    } else {
        elmt.style.display = 'inline-block';
    }
}


function Searchmode(){
	var clear = document.getElementById('main-F0');
	clear.innerHTML = "";
	
	var mode;
	var header
	var container = document.getElementById('main-F0');
	//mencari mode yang dipilih
	if (document.getElementById('all_rb').checked){
		mode = '0';
	}else if (document.getElementById('username_rb').checked){
		mode = '1';
		header = '<h2>Username</h2>';
	}else if (document.getElementById('category_rb').checked){
		mode = '2';
		header = '<h2>Category</h2>';
	}else if (document.getElementById('taskname_rb').checked){
		mode = '3';
		header = '<h2>Task</h2>';
	}else if (document.getElementById('tag_rb').checked){
		mode = '4';
		header = '<h2>Task</h2>';
	}
	
	var string = document.getElementById('search_textfield').value;
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

function Search(){
	var container = document.getElementById('main-F0');
	var string = document.getElementById('search').value;
	var newdiv = '<div class="tugas">' + string + '</div>';
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
		{
		container.innerHTML+=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","search.php?q="+newdiv,true);
	xmlhttp.send();
	/*var search_word = document.getElementById('search').innerHTML;
	
	var newdiv ="<div class="tugas">
                            <div><a href="tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm">Tugas Besar 1: Algoritma Genetik</a></div>
                            <div>Deadline: <strong>22 Februari 2013</strong></div>
                            <div>
                                Tags: 
                                <ul class="tag">
                                    <li>AI</li>
                                    <li>Genetics</li>
                                    <li>Algorithm</li>
                                </ul>
                            </div>                            
                        </div>";
	container.innerHTML += newdiv;*/
}

function NewKategori() {
    var element = document.getElementById('txtNewKategori');
    
    var newitemK = '<li><a id="K' + nKategori + '" href="#" onclick="return KategoriSelected(this)">' + element.value + '</a></li>'
    var currentK = document.getElementById('Kategori');
    currentK.innerHTML += newitemK;
    
    
    if (selectedIndex < 0) {
        var newitemC = '<section id="main-K' + nKategori + '"><h2>' + element.value + '</h2></section>'
        var currentC = document.getElementById('listTugas');
        currentC.innerHTML += newitemC;
    } else {
        var newitemC1 = '<section id="main-K' + nKategori + '" style="display: none;"><h2>' + element.value + '</h2></section>'
        var currentC1 = document.getElementById('listTugas');
        currentC1.innerHTML += newitemC1;
    }
    
    nKategori++;
    return false;
}

function NewTask() {
    if (selectedIndex == -1) {
        alert("Please select the categori first, by click on left sidebar.", "Todolist");
    } else {
        window.location = "createtugas.html";
    }
}

function RemoveFilter() {   
    for (var i=0; i<nKategori; i++) {
        var current = document.getElementById('main-K' + i);
        current.style.display = 'block';
        
        var kcurrent = document.getElementById('K' + i);
        kcurrent.style.backgroundColor = '#fff';
    }
}

function FilterSelected() {
		for (var i=0; i<2; i++) {
			var current = document.getElementById('main-F' + i);
			current.style.display = 'none';
			
			var kcurrent = document.getElementById('F' + i);
			kcurrent.style.backgroundColor = '#fff';
		}
}

function FilterIt(){
	var elmt = document.getElementById('trueicon');

	if (elmt.style.display=='none'){
		elmt.style.display='block';
	}else{
		elmt.style.display='none';
	}
}

/*function Search(){
	var search_word = document.getElementById('search').value;
	
	window.location = "./search.html";
	
	var newdiv = document.createElement('div');
	newdiv.innerHTML = search_word;
	document.getElementById('main-F0').appendChild(newdiv);
}*/
