<html>
<head>

<title>Dashboard</title>
<link href="styles/header.css" rel="stylesheet" type="text/css" />
<link href="styles/dashboard.css" rel="stylesheet" type="text/css" />

<script>
	function showTask(uidkategori){
		var xmlhttp;
		if (window.XMLHttpRequest) {
		  // code for IE7+, Firefox, Chrome, Opera, Safari
		  xmlhttp=new XMLHttpRequest();
		}
		else {
		  // code for IE6, IE5
		  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
		xmlhttp.onreadystatechange=function() {
		  if (xmlhttp.readyState==4 && xmlhttp.status==200) {
			document.getElementById("listtask").innerHTML=xmlhttp.responseText;
			document.getElementById("listtaskdefault").style.display = 'none';
			document.getElementById("listtask").style.display = 'block';
			document.getElementById("tambahtaskblock").innerHTML="<div class=task_block id=tambah_task onclick=\"location.href='newtask.php?q="+uidkategori+"'\"><p>Tambah Task...</p></div>";
		  }
		}
		xmlhttp.open("GET","listtask.php?q="+uidkategori,true);
		xmlhttp.send();
	}
</script>
<script>
	function showTaskDefault(){
		document.getElementById("listtaskdefault").style.display = 'block';
		document.getElementById("listtask").style.display = 'none';
		document.getElementById("tambahtaskblock").innerHTML="";
	}
</script>
		
</head>

<body>
	<div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
			</div>
			<div id="space">
			</div>
			<div class = "menu" id = "search">
				 <form name="search" method="post" action="search.php">
					 Search for: <input type="text" name="find" /> in 
					 <Select NAME="field">
					 <Option VALUE="semua">Semua</option>
					 <Option VALUE="username">Username</option>
					 <Option VALUE="namakategori">Judul Kategori</option>
					 <Option VALUE="tasktag">Task atau Tag</option>
					 </Select>
					 <input type="hidden" name="searching" value="yes" />
					 <button type="submit" id="searchbutton"></button>
				 </form>
			</div>
			<div class="menu" id="logout" action="logout.php">
				<a href="index.html">Logout</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.php">Profile</a>
			</div>
			<div class="menu" id="home">
				<a href="dashboard.php">Home</a>
			</div>
        </div>
		
		<div id="category">
        	<div id="category_head">
				Kategori
			</div>
			<div class="category_block" onclick="showTaskDefault()">
				<div class="category_name">
					<a href="dashboard.php">...</a>
				</div>
			</div>
			<?php
				$con = mysql_connect("localhost:3306","root","");
				if (!$con)
				  {
				  die('Could not connect: ' . mysql_error());
				  }
				mysql_select_db("progin_405_13510057", $con);
				// Fill up array with names
				session_start();			  
				$result = mysql_query("SELECT namakategori,hak.idkategori FROM kategori JOIN hak 
									WHERE kategori.idkategori = hak.idkategori AND hak.username = '$_SESSION[id]'");
				while($row = mysql_fetch_array($result))
					echo "<div class=category_block onclick=showTask(".$row['idkategori'].")><div class=category_name>".$row['namakategori']."</div></div>";
			?>
				
			<div class="category_block" id="tambah_kategori" onclick="location.href='#category_form'">
				<div class="category_pic">
					<img src="images/tambah.png" alt=""/>
				</div>
				<div class="category_name">
					Tambah kategori...
				</div>
			</div>
		</div>
		<div id="task">
        	<div id="task_header">
				Tasks
			</div>
			
			<div id="tambahtaskblock">
			</div>
			<div id = "listtask">
			</div>
			<div id = "listtaskdefault">
			<?php
			$response = '';
			//get the q parameter from URL
			$username=$_SESSION['id'];
			// Fill up array with names
			$tugaspribadi = mysql_query("SELECT * FROM tugas WHERE username = '$username'");
			while($row = mysql_fetch_array($tugaspribadi)) {
				$response .= "<div class=task_block><div class=task_judul>".$row['namatugas']."</div><div class=task_deadline> Deadline : ".$row['deadline']."</div><div class=task_tag>Tags: ";
				$tagpribadi = mysql_query("SELECT isitag FROM tugas JOIN tag WHERE tugas.idtugas = $row[idtugas] AND tag.idtugas = $row[idtugas]");
				$count = mysql_num_rows($tagpribadi);
				while($row = mysql_fetch_array($tagpribadi)) {
					if ($count > 1)
						$response .= $row['isitag'].", ";
					else 
						$response .= $row['isitag'];
					$count--;
				}
				$response .= "</div></div>";
			}
			$tugasassign = mysql_query("SELECT * FROM tugas JOIN assignee WHERE assignee.username = '$username' AND tugas.idtugas = assignee.idtugas");
			while($row = mysql_fetch_array($tugasassign)) {
				$response .= "<div class=task_block><div class=task_judul>".$row['namatugas']."</div><div class=task_deadline> Deadline : ".$row['deadline']."</div><div class=task_tag>Tags: ";
				$tagassign = mysql_query("SELECT isitag FROM tugas JOIN tag WHERE tugas.idtugas = $row[idtugas] AND tag.idtugas = $row[idtugas]");
				$count = mysql_num_rows($tagassign);
				while($row = mysql_fetch_array($tagassign)) {
					if ($count > 1)
						$response .= $row['isitag'].", ";
					else 
						$response .= $row['isitag'];
					$count--;
				}
				$response .= "</div></div>";
			}
			//output the response
			echo $response;
			?>
			</div>
		</div>
		
	<!--Popup bikin kategori baru -->
	<a href="#x" class="overlay" id="category_form"></a>
	<div class="popup">
	<form name="addcategory" method="post" action="addcategory.php">
		<div class="form_baris">
		
			<div class="form_kiri">
				Nama Kategori: 
			</div>
			<div class="form_kanan">
				<input name="categoryname" type="text" size="35" maxlength="25" class="inputtext">
			</div>
		</div>
		<div id="fs">
			<fieldset>
				<legend>Pengguna yang Bisa Mengubah</legend>
				<?php
				$result = mysql_query("SELECT * FROM user");
				while($row = mysql_fetch_array($result))
					echo "<div class=gambar_kecil><img src=".$row['avatar']." width=50px height=50px><input type=checkbox name=\"user_berhak[]\" value=\"".$row['username']."\"/></div>";
				mysql_close($con);
				?>
			</fieldset>
		</div>
		<div class="form_baris">
			<input type="submit" name="submit" value="Buat Kategori" id="button_buat_kategori"/>
		</div>
		<a class="close" href="#close"></a>
	</form>
	</div>
</body>
</html>
