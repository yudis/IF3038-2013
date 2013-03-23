var angka = 0;

function tampilkan_hasil(jenis, query_search)
{
	document.getElementById("main").innerHTML="";
	if(window.XMLHttpRequest)
	{
		// untuk IE7, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	}
	else
	{
		//untuk IE jadul
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	
	xmlhttp.onreadystatechange = function()
	{
		if (xmlhttp.readyState==4 && xmlhttp.status==200)
		{
			document.getElementById("main").innerHTML=xmlhttp.responseText;
		}
	}
	xmlhttp.open("GET", "search.php?jenis="+jenis+"&q="+query_search+"&show=1", true);
	xmlhttp.send();
}

function tambah()
{
	angka++;
	alert(angka);
}