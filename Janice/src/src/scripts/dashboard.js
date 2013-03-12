var nKategori = 3;
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
    updateAddButtonVisibility();
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
    updateAddButtonVisibility();
    return false;
}

function Search(){
	var search_word = document.getElementById('search').value;
	
	window.location.href = './search.html';
	
	var newdiv = document.createElement('div');
	newdiv.innerHTML = search_word;
	div.setAttribute('class','tugas');
	document.getElementById('main-F0').add(newdiv,testtt);
}

