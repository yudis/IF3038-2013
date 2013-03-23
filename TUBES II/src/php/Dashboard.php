<html>
<head>
<script>
var getkat="";

function hapuskategori(){
if(getkat == ""){
	alert("Pilih kategori terlebih dahulu !!!");
}else {
	var conf = confirm("Anda yakin menghapus kategori ini ?\nSeluruh task akan dihapus");
	//alert(getkat);
	if(conf == true){
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
			alert(xmlhttp.responseText);
			location.reload();
			}
		  }
	xmlhttp.open("GET","hapuskategori.php?hkategori=" + getkat ,true);
	xmlhttp.send();
	}else{
		alert("check");
	}
}
}

function addkategori(){
var inputkategori = prompt("Masukkan nama kategori","");

	if(inputkategori=="") {
	alert("kolom harus diisi !");
	}else {
		callkategori(inputkategori);
	}
}

function callkategori(ckt){
if (ckt=="")
  {
  return;
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
    alert(xmlhttp.responseText);
	location.reload();
    }
  }
xmlhttp.open("GET","addkategori.php?tkategori=" + ckt ,true);
xmlhttp.send();

}

function taketask(ktg)
{
//alert ("in");
getkat=ktg;

if (ktg=="")
  {
  document.getElementById("div1").innerHTML="";
  return;
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
    document.getElementById("div1").innerHTML=xmlhttp.responseText;
    }
  }
xmlhttp.open("GET","gettask.php?kategori=" + ktg ,true);
xmlhttp.send();
}
</script>
<title>Dashboard | So La Si Do</title>
	
<link href="css/dashboard.css" rel="stylesheet" type="text/css" />
<link href="css/mediaqueries.css" rel="stylesheet" type ="text/css" />
<link href='http://fonts.googleapis.com/css?family=Merienda' rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Skranji' rel='stylesheet' type='text/css'>

</head>
<body>
<script type="text/javascript" language="javascript" src="js/dashboard.js"></script>
	
<div class="header">
	<a href="dashboard.php"><img align="left" src="images/logo.png" width="150" height="50" />
	<h6>Dashboard</a> | <a href="profile.php">Profile</a> | <a href="logout.php">Logout</a>
	
   | Search: <input type="search">
  <input type="submit" value="GO">
	</div>
	<br>
    <center>Pilih Kategori	:
		<form>
		<select name="users" onChange="taketask(this.value)">
		<option value=""></option>;
		<?php
		$con = mysql_connect('localhost', 'progin', 'progin');
		if (!$con)
		  {
		  die('Could not connect: ' . mysql_error());
		  }

		mysql_select_db("progin", $con);

		$usr="SELECT kat FROM kategori";
		
		$qusr = mysql_query($usr);
		while ($row = mysql_fetch_array($qusr))
		{
		$dt=$row["kat"];
		echo "<option value=\"$dt\">$dt</option>";	
		}		
		?>
		</select>
		</form>
		<button onclick="addkategori()">Tambah Kategori</input>
		<button onclick="hapuskategori()">Hapus Kategori</input>
	</center>
	<div class="kategori">
	<center><h2 class="judul">Daftar Tugas</h2>
	<table>
	<tr><td><div class="judulkat"
        onmouseover="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask').style.display = 'block';"
		onmouseout="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask').style.display = 'block';">
		
   <div id="div1" style="display: block;">
	
	</div>
	</div>
   </td>
   
   <td><div class="judulkat"
        onmouseover="document.getElementById('div1').style.display = 'none'; document.getElementById('addtask3').style.display = 'block';"
		onmouseout="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask3').style.display = 'none';"><p id="cats"></p>
		<div id="addtask3" style="display: none;">
		<a href="tambah.html"><img src="images/newtask.png"></a>
	</div>
   </td>
   
   </tr></table>  
   
 
	
</center>
</body>
</html>