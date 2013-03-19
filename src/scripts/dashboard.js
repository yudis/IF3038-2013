var chosen=0;

function updateAddButtonVisibility() {
    var elmt = document.getElementById('addTask');
    elmt.style.display = 'inline-block';
}

function setChosen(str)
{
	chosen=str
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
    window.location = "createtugas.php";
}

function deleteCategory()
{
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp=new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
	  }
	
	xmlhttp.open("GET","ajax/deletecat.php?q="+chosen,true);
	xmlhttp.send();
	
	
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