function validating(month, day, year){
	var name = document.forms["input"]["nama"].value;
	var tag = document.forms["input"]["tag"].value;
	var file = document.forms["input"]["attach"].value;
	var asignee = document.forms["input"]["asignee"].value;
	if (name == ""){
		alert("Nama task tidak boleh kosong");
		return false;
	}
	else if (name.length > 25){
		alert("Nama task salah");
		return false;
	}
	else if (!name.match(/[a-zA-Z0-9]+$/i)){
		//document.getElementById("nameicon").src="pict/centang.png";
		alert("Nama task salah");
		return false;
	}
	else if(file == ""){
		alert("Attachment tidak boleh kosong");
		return false;
	}
	else if (!file.match("^.+(\.(?i)(jpg|jpeg|png|gif|bmp|wmv|avi|flv|doc|docx))$")){
		alert("File tidak valid");
		return false;
	}
	else if (asignee == ""){
		alert("Asignee tidak boleh kosong");
		return false;
	}
	else if (tag == ""){
		alert("Tag tidak boleh kosong");
		return false;
	}
}

//callback function AJAX
var xmlhttp;
var url;
function loadXMLDoc(url,cfunc)
{
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.open("GET",url,true);
xmlhttp.onreadystatechange=cfunc;
xmlhttp.send();
}

function edituser()
{
	document.getElementById("editresponse").innerHTML = "LOADING";
	
	var id = document.getElementById('epusername').value;
	var x = document.getElementById("edname").value;
	var i = document.getElementById("edmail").value;
	var j =  document.getElementById("edpass").value;
	var k =  document.getElementById("edcpass").value;
	
	var y = document.getElementById('tahun').value;
	var m = document.getElementById('bulan').value;
	var d = document.getElementById('tanggal').value;
	
	if (j!=k)
	{
		document.getElementById("editresponse").innerHTML = "confirm password tidak sama";
	}
	else
	{
		loadXMLDoc("euser.php?edname="+x+"&edmail="+i+"&edpass="+j+"&edcpass="+k+
						   "&tahun="+y+"&bulan="+m+"&tanggal="+d+"&username="+id
		
		,function()
		{
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
			{
				if (xmlhttp.responseText == 1)
				{
					document.getElementById("editresponse").innerHTML = "email sudah terpakai";
				}
				else if (xmlhttp.responseText == 2)
				{
					document.getElementById("editresponse").innerHTML = "data sudah diubah";
					document.getElementById("biodata3").style.display = "none";
					document.getElementById("biodata2").style.display = "block";
					document.getElementById("donepedit").style.display = "none";
					document.getElementById("pedit").style.display = "block";
					window.location = "index.php";
				}
			}
		});
	}
}

var pIdActiveTask;
function pdisplay(pid){
	if (pIdActiveTask == null) {
		pIdActiveTask = "biodata2";
	}
	document.getElementById(pIdActiveTask).style.display = "none";
	pIdActiveTask = pid;
	document.getElementById(pid).style.display = "block";
}

var IdActiveTask;
function display(id){
	if (IdActiveTask == null) {
		IdActiveTask = "biodata3";
	}
	document.getElementById(IdActiveTask).style.display = "none";
	IdActiveTask = id;
	document.getElementById(id).style.display = "block";
}
