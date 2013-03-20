var nKategori = 3;
var selectedIndex = -1;

function initialize() {
	if(typeof(Storage)!=="undefined") {
		if (localStorage.session) {
			//updateAddButtonVisibility
			var elmt = document.getElementById('addTask');
			if (selectedIndex < 0) {
				elmt.style.display = 'none';
			} else {
				elmt.style.display = 'inline-block';
			}
			listKategori();
		}
		else {
			window.location = "index.html";
		}
	}
	else {
		alert("uwow");
	}
}

function signout() {
	localStorage.clear();
}

function listKategori() {
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
			document.getElementById("sidebar").innerHTML = xmlhttp.responseText;
		}
	}
	
	xmlhttp.open("GET","listkategori.php?uname="+localStorage.session,true);
	xmlhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	xmlhttp.send();
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

function RemoveKategoriFilter(elmt) { 
    selectedIndex = -1;   
    for (var i=0; i<nKategori; i++) {
        var current = document.getElementById('main-K' + i);
        current.style.display = 'block';
        
        var kcurrent = document.getElementById('K' + i);
        kcurrent.style.backgroundColor = '#fff';
    }
    
    elmt.style.backgroundColor = '#e8f3df';
    initialize();
    return false;
}

function KategoriSelected(elmt) {
    selectedIndex = elmt.id.substring(1);
    document.getElementById('K-All').style.backgroundColor = '#fff';
    
    for (var i=0; i<nKategori; i++) {
        var current = document.getElementById('main-K' + i);
        current.style.display = 'none';
        
        var kcurrent = document.getElementById('K' + i);
        kcurrent.style.backgroundColor = '#fff';
    }
    document.getElementById('main-' + elmt.id).style.display = 'block';
    
    elmt.style.backgroundColor = '#e8f3df';
    initialize();
    return false;
}

