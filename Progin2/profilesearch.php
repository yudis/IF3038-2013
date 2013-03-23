<html>
<head>
<title>Profile</title>
<link href="styles/profile.css" rel="stylesheet" type="text/css" />
<link href="styles/header.css" rel="stylesheet" type="text/css" />
</head>

<script>
	function makeTgl(){
		for(var i=1; i<=31; i++){
			var isi=document.createTextNode(i);
			var opsi = document.createElement("option");
			opsi.setAttribute("value",i);
			opsi.appendChild(isi);
			document.getElementById("tgl").appendChild(opsi);
		}
	}
</script>

<script>
	function makeThn(){
		for(var i=1955; i<=2013; i++){
			var isi=document.createTextNode(i);
			var opsi = document.createElement("option");
			opsi.setAttribute("value",i);
			opsi.appendChild(isi);
			document.getElementById("thn").appendChild(opsi);
		}
	}
</script>

<script>
	function validateFullName(nama) {
			var str = nama;
			var idx = str.indexOf(' ');
			if (idx > 0 && idx < str.length - 1) {
				var chr1 = str.charAt(idx - 1);
				var chr2 = str.charAt(idx + 1);
				if (chr1 != ' ' && chr2 != ' ') {
					document.getElementById('v_nama').innerHTML='<font color="green">Benar</font>';
					return true;	
				} else {
					document.getElementById('v_nama').innerHTML='<font color="red">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; 
				return false;
				}
			} else {
				document.getElementById('v_nama').innerHTML='<font color="red">Nama lengkap minimal 2 kata dipisahkan 1 spasi</font>'; 
				return false;
			}
	}
</script>

<script>
	function checkPass(pass, uname, email){
		if((pass != uname) && (pass != email)){
			document.getElementById('v_pass').innerHTML='<font color="green">Benar</font>';
			return true;					
		}else if(pass == uname){
			document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username</font>'; 
			return false;
		}
		else if(pass == email){
			document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan email</font>'; 
			return false;
		}
		else{
			document.getElementById('v_pass').innerHTML='<font color="red">Password tidak boleh sama dengan username/email</font>'; 
			return false;
		}
	}
</script>

<script>
	function checkCPass(cpass, pass){
		if(cpass == pass){
			document.getElementById('v_cpass').innerHTML='<font color="green">Benar</font>';
			return true;					
		}else{
			document.getElementById('v_cpass').innerHTML='<font color="red">Salah</font>'; 
			return false;
		}
	}
</script>

<script>
	function validateAvatar(avatar) {
		var str = avatar;
		var ext = str.substring(str.lastIndexOf('.') + 1, str.length);
		if (ext.toLowerCase() == "jpeg" || ext.toLowerCase() == "jpg") {
			document.getElementById('v_avatar').innerHTML='<font color="green">Benar</font>';
			return true;	
		} else {
			document.getElementById('v_avatar').innerHTML='<font color="red">Avatar harus ekstensi .jpg atau .jpeg</font>'; 
			return false;
		}
	}
</script>

<script>
	function check(fullname, password, cpassword, tanggal, bulan, tahun, avatar) {
		if ((fullname.length==0) &&
		(password.length==0) && 
		(cpassword.length==0) &&
		(tanggal == "1") && (bulan == "January") && (tahun == "1955") &&
		(avatar.length == 0)
		) {
			  alert("Tidak ada atribut profile yang diubah!");
			  return false;
		}
		if (password == cpassword) {
			return true;
		} else {
			return false;
		}
	}
</script>

<?php
session_start();
if(!isset($_SESSION['id']))
	header("location:index.php");
?>
<body onLoad="makeTgl();makeThn();)">
	
	<div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="dashboard.php"><img src="images/logo.png" title="Home" alt="Home"/></a>
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
				<a href="index.php">Logout</a>
			</div>
			<div class="menu" id="home">
				<a href="dashboard.php">Home</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.php">
				<img alt="" height=25 width=25 src="<?php
					$con = mysql_connect("localhost:3306","progin","progin");
					if (!$con)
					  {
					  die('Could not connect: ' . mysql_error());
					  }

					mysql_select_db("progin_439_13510057", $con);
				
					$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
					while($row = mysql_fetch_array($result)) 
						echo $row['avatar'];
				?>">
				Profile</a>
			</div>
        </div>
		<div id="profilearea">
			<div class="profilephoto">
				<?php
				$con = mysql_connect("localhost:3306","progin","progin");
				if (!$con)
				  {
				  die('Could not connect: ' . mysql_error());
				  }

				mysql_select_db("progin_439_13510057", $con);
				
				$result = mysql_query("SELECT * FROM user WHERE username='$_GET[idsearch]'");
				while($row = mysql_fetch_array($result)) {
					echo "<img alt=\"\" src=\"".$row['avatar']."\"/>";
				}
				?>
			</div>
			<div class="biodata">
				<?php
				$result = mysql_query("SELECT * FROM user WHERE username='$_GET[idsearch]'");
				while($row = mysql_fetch_array($result)) {
					echo "<br>";
					echo "Username  : ".$row['username']."<br><br>";
					echo "Fullname  : ".$row['fullname']."<br><br>";
					echo "Birthdate : ".date('d F Y',strtotime($row['birthdate']))."<br><br>";
					echo "Handphone : ".$row['phonenumber']."<br><br>";
					echo "Email	  	: ".$row['email']."<br>";
				}
				?>
			</div>
		</div>
		<div id="listarea">
			<div id="undonetasktitle">
				Undone Task
			</div>
			<div id="donetasktitle">
				Done Task
			</div>
        	<div id="undonetask">
				<?php
				$result = mysql_query("SELECT * FROM tugas WHERE username='$_GET[idsearch]' AND status='undone'");
				while($row = mysql_fetch_array($result)) {
					echo "<div class=\"task_block\">";
					echo 	"<div class=\"task_judul\">";
					echo 	$row['namatugas'];
					echo 	"</div>";
					echo 	"<div class=\"task_deadline\">";
					echo	"Deadline: ".date('d F Y',strtotime($row['deadline']));
					echo	"</div>";
					echo	"<div class=\"task_tag\">";
					echo	"Tags: ";
					$result2 = mysql_query("SELECT * FROM tag WHERE idtugas='$row[idtugas]'");
					$count = mysql_num_rows($result2);
					while($row2 = mysql_fetch_array($result2)) {
						if ($count == 1) {
							echo $row2['isitag'];
						} else {
							echo $row2['isitag'].", ";
							$count--;
						}
					}
					echo	"</div>";
					echo "</div>";
				}
				$result = mysql_query("SELECT * FROM tugas JOIN assignee WHERE assignee.username='$_GET[idsearch]' AND tugas.idtugas=assignee.idtugas AND status='undone'");
				while($row = mysql_fetch_array($result)) {
					echo "<div class=\"task_block\">";
					echo 	"<div class=\"task_judul\">";
					echo 	$row['namatugas'];
					echo 	"</div>";
					echo 	"<div class=\"task_deadline\">";
					echo	"Deadline: ".date('d F Y',strtotime($row['deadline']));
					echo	"</div>";
					echo	"<div class=\"task_tag\">";
					echo	"Tags: ";
					$result2 = mysql_query("SELECT * FROM tag WHERE idtugas='$row[idtugas]'");
					$count = mysql_num_rows($result2);
					while($row2 = mysql_fetch_array($result2)) {
						if ($count == 1) {
							echo $row2['isitag'];
						} else {
							echo $row2['isitag'].", ";
							$count--;
						}
					}
					echo	"</div>";
					echo "</div>";
				}
				?>
			</div>
			<div id="donetask">
				<?php
				$result = mysql_query("SELECT * FROM tugas WHERE username='$_GET[idsearch]' AND status='done'");
				while($row = mysql_fetch_array($result)) {
					echo "<div class=\"task_block\">";
					echo 	"<div class=\"task_judul\">";
					echo 	$row['namatugas'];
					echo 	"</div>";
					echo 	"<div class=\"task_deadline\">";
					echo	"Deadline: ".date('d F Y',strtotime($row['deadline']));
					echo	"</div>";
					echo	"<div class=\"task_tag\">";
					echo	"Tags: ";
					$result2 = mysql_query("SELECT * FROM tag WHERE idtugas='$row[idtugas]'");
					$count = mysql_num_rows($result2);
					while($row2 = mysql_fetch_array($result2)) {
						if ($count == 1) {
							echo $row2['isitag'];
						} else {
							echo $row2['isitag'].", ";
							$count--;
						}
					}
					echo	"</div>";
					echo "</div>";
				}
				$result = mysql_query("SELECT * FROM tugas JOIN assignee WHERE assignee.username='$_GET[idsearch]' AND tugas.idtugas=assignee.idtugas AND status='done'");
				while($row = mysql_fetch_array($result)) {
					echo "<div class=\"task_block\">";
					echo 	"<div class=\"task_judul\">";
					echo 	$row['namatugas'];
					echo 	"</div>";
					echo 	"<div class=\"task_deadline\">";
					echo	"Deadline: ".date('d F Y',strtotime($row['deadline']));
					echo	"</div>";
					echo	"<div class=\"task_tag\">";
					echo	"Tags: ";
					$result2 = mysql_query("SELECT * FROM tag WHERE idtugas='$row[idtugas]'");
					$count = mysql_num_rows($result2);
					while($row2 = mysql_fetch_array($result2)) {
						if ($count == 1) {
							echo $row2['isitag'];
						} else {
							echo $row2['isitag'].", ";
							$count--;
						}
					}
					echo	"</div>";
					echo "</div>";
				}
				?>
			</div>
		</div>
		
</body>
</html>