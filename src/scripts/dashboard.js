var nKategori = 5;
var selectedIndex = -1;

function NewKategori() {
    var element = document.getElementById('txtNewKategori');
    
    var newitemK = '<li><a id="K' + nKategori + '" href="#" onclick="return KategoriSelected(this)">' + element.value + '</a></li>'
    var currentK = document.getElementById('Kategori');
    currentK.innerHTML += newitemK;
        
    var newitemC = '<section id="main-K' + nKategori + '"><h2>' + element.value + '</h2></section>'
    var currentC = document.getElementById('listTugas');
    currentC.innerHTML += newitemC;
    
    nKategori++;
    return false;
}

function NewTask() {
    if (selectedIndex == -1) {
        alert("Please select the categori first, by click on left sidebar.", "Todolist");
    } else {
        window.location = "home.html";
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
    return false;
}

