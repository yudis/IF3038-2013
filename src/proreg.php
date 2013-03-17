<?php
	$_uname = $_POST['username'];
	$_pass = $_POST['password'];
	$_cpass = $_POST['cpass'];
	$_nama = $_POST['nama'];
	$_tgl = $_POST['tgl'];
	$_email = $_POST['email'];
	if (isset($_POST['avatar'])) $_ava = $_FILES["avatar"]["name"];
		else $_ava=$_FILES["avatar"]["name"];
	if (isset($_POST['sex'])) $_sex = $_POST["sex"];
		else $_sex="";
	$_about = $_POST['about'];
	$erruname="";
	$errpass="";
	$errcpass="";
	$errnama="";
	$errtgl="";
	$erremail="";
	$errava="";
	$errsex="";
	$uunik=true;
	$eunik=true;
	require("dbfunc.php");
	
	$qres = mysql_query("SELECT * FROM user");
	while($row = mysql_fetch_array($qres))
	{
		if (strcmp($row['username'],$_uname)==0) $uunik=false;
		if (strcmp($row['email'],$_email)==0) $eunik=false;
	}
	if (strlen($_uname)<5||strlen($_uname)>20) {
		 if (strlen($_uname)==0)
			$errname="Isi dahulu username";
		 else
			$errname="Panjang username salah (min 5 karakter, max 20 karakter";
	}else if (!$uunik){
		$errname="Username sudah terdaftar";
	}
	if (strlen($_pass)<8||strlen($_uname)>20) {
		if (strlen($_pass)==0)
			$errpass="Isi dahulu password";
		 else
			$errpass="Panjang password salah (min 8 karakter, max 20 karakter)";
	}else if (strcmp($_uname,$_pass)==0){
		$errpass="Username dan password tak boleh sama";
	}else if (strcmp($_email,$_pass)==0){
		$errpass="Email dan password tak boleh sama";
	}
	if (strlen($_cpass)==0){
		$errcpass="Isi dahulu confirm password";
	}else if (strcmp($_cpass,$_pass)!=0){
		$errcpass="Password dan confirm password tak boleh tidak sama";
	}
	if (strlen($_nama)==0){
		$errnama="Nama harus diisi";
	}else if (strlen($_nama)>30){
		$errnama="Panjang nama harus kurang dari 30 karakter";
	}else if (strrpos($_nama, " ")<1||strrpos($_nama, " ")+1>=strlen($_nama)){
		$errnama="Format nama tak sesuai";
	}
	if (strlen($_tgl)==0){
		$errtgl="Tanggal lahir harus diisi";
	}else if (!preg_match('/^\d{4}\-\d{2}\-\d{2}$/', $_tgl)){
		$errtgl="Format tanggal tak sesuai";
	}
	if (strlen($_email)==0){
		$erremail="Email harus diisi";
	}else if (!preg_match('/^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,})$/', $_email)){
		$erremail="Email tidak sesuai dengan ketentuan";
	}else if (!$eunik) {
		$erremail="Email sudah terdaftar";
	}
	if (strlen($_ava)==0){
		$errava="Belum memilih gambar";
	}else{
		if (($_FILES["avatar"]["type"] == "image/jpeg")||($_FILES["avatar"]["type"] == "image/pjpeg"))
			$errava="";
		else
			$errava="Jenis file tidak sesuai";
	}
	if (strlen($_sex)==0){
		$errsex="Pilih gender dahulu";
	}
	
	if ($errname!=""||$errpass!=""||$errcpass!=""||$errnama!=""||$errtgl!=""||$erremail!=""||$errava!=""||$errsex!=""){
		header('Location: register.php?euname='.$errname.'&epass='.$errpass.'&ecpass='.$errcpass.'&enama='.$errnama.'&etgl='.$errtgl.'&eemail='.$erremail.'&eava='.$errava.'&esex='.$errsex.'&uname='.$_uname.'&pass='.$_pass.'&cpass='.$_cpass.'&nama='.$_nama.'&tgl='.$_tgl.'&email='.$_email.'&about='.$_about.'&sex='.$_sex);
	}else{
		//masukin database
		if ($_FILES['avatar']['error'] > 0) {
			echo "Error: " . $_FILES['avatar']['error'] . "<br />";
			header('Location: register.php');
		}else{
			move_uploaded_file($_FILES['avatar']['tmp_name'],"ava/".$_uname.".jpg");
			if (strcmp($_sex, "female")==0){
				$_sex=0;
			}else{
				$_sex=1;
			}
			$_tgl = date("Y-m-d H:i:s");
			$qres="INSERT INTO user (username, email, password, nama_lengkap, gender, tgl_lahir, about, jmlPost, jmlKom, djoin) VALUES ('$_uname','$_email','$_pass','$_nama','$_sex','$_tgl','$_about','0','0','$_tgl')";
			if (!mysql_query($qres,$con))
			{
				die('Error: ' . mysql_error());
			}
			$qres="INSERT INTO achievement (user,jenis) VALUES ('$_uname','9')";
			if (!mysql_query($qres,$con))
			{
				die('Error: ' . mysql_error());
			}
			echo "Selamat, ".$_nama." Anda telah terdaftar di catatan binder kami";
			sleep(1);
			header("Location: profile.php?uid=$_uname");
		}
	}
	
	mysql_close($con);
?>