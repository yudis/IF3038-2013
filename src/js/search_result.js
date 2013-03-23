var last_viewed = 10;
var _jenis;
var _q;
var isReady = true;

function tampilkan_hasil(jenis, query_search)
{
	_jenis = jenis;
	_q = query_search;
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
	xmlhttp.open("GET", "search.php?jenis="+jenis+"&q="+query_search+"&show=1&more=10", true);
	xmlhttp.send();
}

function tampilkan_lebih(jenis, query_search)
{
	last_viewed+=10;

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
			document.getElementById("main").innerHTML+=xmlhttp.responseText;
			isReady = true;
		}
	}
	xmlhttp.open("GET", "search.php?jenis="+jenis+"&q="+query_search+"&show=1&more="+last_viewed, true);
	xmlhttp.send();
}

window.onscroll = scroll;

function scroll() {
	if(window.innerHeight + document.body.scrollTop  >= document.body.offsetHeight){
		if(isReady)
		{
			isReady = false;
			tampilkan_lebih(_jenis, _q);
		}
		//alert(last_viewed);
	}
	//alert("scroll event detected! " + window.pageXOffset + " " + window.pageYOffset);
	// note: you can use window.innerWidth and window.innerHeight to access the width and height of the viewing area
}
