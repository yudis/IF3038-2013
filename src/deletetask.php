<?php
	if (isset($_POST['deletetask'])){
		session_start();
		include('config.php');
		$namatugas = $_SESSION['namatugas'];
		$username = $_SESSION['username'];
		$sql = "DELETE FROM asigner WHERE username='$username' AND namatugas='".$namatugas."'";
		$result = mysql_query($sql, $bd);
		$sql = "DELETE FROM attachment WHERE username='$username' AND namatugas='".$namatugas."'";
		$result = mysql_query($sql, $bd);
		$sql = "DELETE FROM comment WHERE username='$username' AND namatugas='".$namatugas."'";
		$result = mysql_query($sql, $bd);
		$sql = "DELETE FROM tag WHERE username='$username' AND namatugas='".$namatugas."'";
		$result = mysql_query($sql, $bd);
		$sql = "DELETE FROM tugas WHERE username='$username' AND namatugas='".$namatugas."'";
		$result = mysql_query($sql, $bd);
		mysql_close($bd);
	}
	echo "<html>";
	echo "<head>";
	echo "<script type='text/javascript'>";
	echo "function redirect(){";
	echo "alert('Task edited');";
	echo "window.location = 'dashboard.php';";
	echo "}";
	echo "redirect();";
	echo "</script>";
	echo "</head>";
	echo "<body>";
	echo "</body>";
	echo "</html>";
?>