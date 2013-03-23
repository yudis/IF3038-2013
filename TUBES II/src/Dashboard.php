<?php 

    session_start(); 
    include 'php/ChromePhp.php';
    ChromePhp::log('dashboard.php');
    $userid = $_SESSION['login'];
    $_SESSION['login'] = $userid;
    ChromePhp::log($userid);

?>

<html>
    
<head>
    <title>Dashboard | So La Si Do</title>
	
    <link href="css/dashboard.css" rel="stylesheet" type="text/css" />
    <link href="css/search.css" rel="stylesheet" type="text/css" />
    <link href="css/mediaqueries.css" rel="stylesheet" type ="text/css" />
    <!--<link href='http://fonts.googleapis.com/css?family=Merienda' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Skranji' rel='stylesheet' type='text/css'>-->

</head>

<body>
    <script type="text/javascript" language="javascript" src="js/dashboard.js"></script>
    <script type="text/javascript" language="javascript" src="js/search.js"></script>
	
<div class="header">
    <a href="dashboard.html"><img align="left" src="images/logo.png" width="150" height="50" />
	<h6>Dashboard</h6></a> <p>|</p> <a href="profile.html">Profile</a> <p>|</p> <a href="index.html">Logout</a>
        <form id="searchform"  onsubmit="return searchByFilter()" >
   | Search:<input type="search" id="searchquery" name="searchquery" /> 
   Filter: <select id="filtertype" name="filtertype">
        <option value="All" selected>All</option>
        <option value="Username">Username</option>
        <option value="Category">Category</option>
        <option value="Task">Task</option>
   </select>
   
   <input type="submit" value="GO" />
        </form>
	</div>
    <center>
		<img src="images/newcat.png" name="addCategory" vspace="20" id="addCategory" onClick="showCatPrompt()"></button>
	</center>
	
<div id="contentdashboard">
    
	<div class="kategori">
	<center><h2 class="judul">Daftar Tugas</h2>
	<table>
	<tr><td><div class="judulkat"
        onmouseover="document.getElementById('div2').style.display = 'none'; document.getElementById('addtask').style.display = 'block';"
		onmouseout="document.getElementById('div2').style.display = 'block'; document.getElementById('addtask').style.display = 'none';">Akademik
		
   <div id="div1" style="display: block;"> <div class="ganjil"><a href="rincian.html">Ngerjain Tubes <br>22-02-2013<br>akademik, capek</a></div>
   <div class="genap"><a href="rincian2.html">Belajar <br>23-02-2013<br>akademik, capek</a></div> 
	<div id="addtask" style="display: none;">
		<a href="tambah.html"><img src="images/newtask.png"></a>
	</div></div>
   </td>
   
   <td><div class="judulkat"
        onmouseover="document.getElementById('div1').style.display = 'none'; document.getElementById('addtask2').style.display = 'block';"
		onmouseout="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask2').style.display = 'none';">Hiburan
		
   <div id="div2" style="display: block;"> <div class="ganjil"><a href="rincian3.html">Futsal <br>24-02-2013<br>olahraga, capek</a></div>
   <div class="genap"><a href="rincian4.html">Nonton <br>25-02-2013<br>asik, capek</a></div>
   <div id="addtask2" style="display: none;">
		<a href="tambah.html"><img src="images/newtask.png"></a>
	</div></div>
   </td>
   
   <td><div class="judulkat"
        onmouseover="document.getElementById('div1').style.display = 'none'; document.getElementById('addtask3').style.display = 'block';"
		onmouseout="document.getElementById('div1').style.display = 'block'; document.getElementById('addtask3').style.display = 'none';"><p id="cats"></p>
		<div id="addtask3" style="display: none;">
		<a href="tambah.html"><img src="images/newtask.png"></a>
	</div>
   </td>
   
   </tr></table>  
            
</div>
       
   </div>
   
 
	
</center>
</body>
</html>