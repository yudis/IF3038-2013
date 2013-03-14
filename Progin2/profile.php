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
	function check(password, cpassword) {
		if (password == cpassword) {
			return true;
		} else {
			return false;
		}
	}
</script>

<body onLoad="makeTgl();makeThn();">
	<?php
	session_start();
	?>
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
				<a href="index.html">Logout</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.php">Profile</a>
			</div>
			<div class="menu" id="home">
				<a href="dashboard.php">Home</a>
			</div>
        </div>
		<div id="profilearea">
			<div class="profilephoto">
				<?php
				if(!isset($_SESSION['id']))
					header("location:index.html");
	
				$con = mysql_connect("localhost:3306","root","");
				if (!$con)
				  {
				  die('Could not connect: ' . mysql_error());
				  }

				mysql_select_db("progin_405_13510057", $con);
				
				$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
				while($row = mysql_fetch_array($result)) {
					echo "<img alt=\"\" src=\"".$row['avatar']."\"/>";
				}
				?>
			</div>
			<div class="biodata">
				<?php
				$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
				while($row = mysql_fetch_array($result)) {
					echo "<br>";
					echo "Username  : ".$row['username']."<br><br>";
					echo "Fullname  : ".$row['fullname']."<br><br>";
					echo "Birthdate : ".date('d F Y',strtotime($row['birthdate']))."<br><br>";
					echo "Handphone : ".$row['phonenumber']."<br><br>";
					echo "Email	  	: ".$row['email']."<br>";
				}
				?>
				<a href="#editprofile_form"><button type="button" id="editbutton"></button></a>
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
				$result = mysql_query("SELECT * FROM tugas WHERE username='$_SESSION[id]' AND status='undone'");
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
				$result = mysql_query("SELECT * FROM tugas WHERE username='$_SESSION[id]' AND status='done'");
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
		
		<!--Popup edit profile -->
		<a href="#x" class="overlay" id="editprofile_form"></a>
		<div class="popup">
			<form name="editprofile" method="post" onSubmit="return check(document.editprofile.password.value, document.editprofile.confirm_password.value)" action="editprofile.php" enctype="multipart/form-data">
				Fullname : <input name="nama_lengkap" id="nama_lengkap" type="text" maxlength="256" onKeyUp="validateFullName(document.editprofile.nama_lengkap.value)"><br>
				<div id="v_nama">
				</div>
				Password : <input name="password" type="password" maxlength="24" onKeyUp="checkPass(document.editprofile.password.value,
					<?php
					echo "'".$_SESSION['id']."'";
					?>
					,
					<?php
					$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
					while($row = mysql_fetch_array($result)) {
						echo "'".$row['email']."'";
					}
					?>
				)"> <br>
				<div id="v_pass">
				</div>
				Confirm Password : <input name="confirm_password" type="password" maxlength="24" onKeyUp="checkCPass(document.editprofile.confirm_password.value, document.editprofile.password.value)"><br>
				<div id="v_cpass">
				</div>
				Birthdate : 
					<select name="tanggal" id="tgl">
						<option>--Tanggal--</option>
					</select>
					<select name="bulan">
						<option>--Bulan--</option>
						<option value="January">January</option>
							<option value="February">February</option>
							<option value="March">March</option>
							<option value="April">April</option>
							<option value="May">May</option>
							<option value="June">June</option>
							<option value="July">July</option>
							<option value="August">August</option>
							<option value="September">September</option>
							<option value="October">October</option>
							<option value="November">November</option>
							<option value="December">December</option>
					</select>
					<select name="tahun" id="thn">
						<option>--Tahun--</option>
					</select>
				<br>
				Avatar : <input type="file" name="avatar"><br>
				<div id="v_avatar">
				</div>
				<input type="submit" name="submit" value="Edit">
			<a class="close" href="#"></a>
			</form>
		</div>
</body>
</html>