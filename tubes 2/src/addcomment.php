<?php
	include('config.php');
	$username = "dummy";
	$namatugas = "satu";
	$isi = $_POST['areacomment'];
	$penulis = "dummy";
	//echo $isi;
	$sql = "INSERT INTO comment (username, namatugas, komentar, penulis) VALUES ('$username', '$namatugas', '$isi', '$penulis')";
	$result = mysql_query($sql, $bd);
	
	echo "<html>";
	echo "<head>";
	echo "<script type='text/javascript'>";
	echo "function redirect(){";
	echo "window.location = 'rinciantugas.php';";
	echo "}";
	//echo "setTimeout('redirect()',2000);";
	echo "redirect();";
	echo "</script>";
	echo "</head>";
	echo "</html>";
	mysql_close($bd);
?>