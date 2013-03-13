<?php
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';
?>

<html>
<head>
<script>
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
    document.getElementById("txtHint").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","tugas.php?q="+str,true);
xmlhttp.send();
}

function loadkategori()
{

}
</script>
</head>
<body>
<button type="button" onclick="loadtugas(this.value)" value="">Semua tugas</button>
<br>
<script>
	if (window.XMLHttpRequest)
	{// code for IE7+, Firefox, Chrome, Opera, Safari
	xmlhttp2=new XMLHttpRequest();
	}
	else
	{// code for IE6, IE5
	xmlhttp2=new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp2.onreadystatechange=function()
	  {
	  if (xmlhttp2.readyState==4 && xmlhttp2.status==200)
		{
		document.getElementById("txtHint2").innerHTML=xmlhttp2.responseText;
		}
	  }
	xmlhttp2.open("GET","kategori.php",true);
	xmlhttp2.send();
</script>
<div id="txtHint2" ><b></b></div>
<div id="txtHint" ><b></b></div>

</body>