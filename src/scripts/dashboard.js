function updateAddButtonVisibility() {
    var elmt = document.getElementById('addTask');
    elmt.style.display = 'inline-block';
}

function NewTask() {
     window.location = "createtugas.html";
}

function NewKategori() {
    var q = document.getElementById('txtNewKategori').value;
	if(q=="")
	{
		return false;
	}
	
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
		document.getElementById("nama_k").innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","ajax/createkategori.php?q="+q,true);
	xmlhttp.send();
	
	return false;
}

function updateStatus(n,str) {
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
		document.getElementById("stats").innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","ajax/updateStatus.php?q="+str+"&n="+n,true);
	xmlhttp.send();
	
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

function loadtugas(str)
{
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
		document.getElementById("tugasT").innerHTML=xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","ajax/tugas.php?q="+str,true);
	xmlhttp.send();
	
	return false;
}