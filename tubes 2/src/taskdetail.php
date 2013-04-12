<?php
	session_start();
	include('config.php');
	$username = $_SESSION['username'];
	$namatugas = $_SESSION['namatugas'];
	if (isset($_POST['statuss'])){
		$sql = "UPDATE tugas SET status=1 WHERE username='$username' AND namatugas='$namatugas'";
		$result = mysql_query($sql, $bd);
	} else {
		$sql = "UPDATE tugas SET status=0 WHERE username='$username' AND namatugas='$namatugas'";
		$result = mysql_query($sql, $bd);
	}
	$asignee = $_POST["asignee"];
	$tag = $_POST["tag"];
	$tgl = $_POST["tanggal"];
	$bln = $_POST["bulan"];
	$thn = $_POST["tahun"];
	$tag = $_POST["tag"];
	$tanggal = $bln . "-" . $tgl  . "-" . $thn;
	$tanggal = mysql_real_escape_string($tanggal);
	$tanggal = strtotime($tanggal);
	$tanggal = date('Y-m-d',$tanggal);
	
	$sql1 = "UPDATE tugas SET deadline='".$tanggal."' WHERE username='$username' AND namatugas='$namatugas'";
	$result1 = mysql_query($sql1, $bd);
	
	$sql = "SELECT * FROM asigner WHERE username='$username' AND namatugas='$namatugas'";
	$result = mysql_query($sql, $bd);
	$asigner_awal = "";
	while ($row = mysql_fetch_array($result)){
		$asigner_awal = $asigner_awal . $row['asignee'] . ",";
	}
	substr_replace($asigner_awal, "", -1);
	if (strcmp($asignee, $asigner_awal) != 0){
		$array = explode(',', $asigner_awal);
		foreach($array as $asign){
			$sql = "DELETE FROM asigner WHERE username='$username' AND namatugas='$namatugas' AND asignee='".$asign."'";
			$result = mysql_query($sql, $bd);
		}
		$pos = strpos($asignee, ",");
		if ($pos === false){
			$sql = "INSERT INTO asigner (username, namatugas, asignee) VALUES ('$username', '$namatugas', '$asignee')";
			$result = mysql_query($sql, $bd);
		} else {
			$array = explode(',', $asignee);
			foreach ($array as $asign){
				$sql = "INSERT INTO asigner (username, namatugas, asignee) VALUES ('$username', '$namatugas', '$asign')";
				$result = mysql_query($sql, $bd);
			}
		}
	}
	
	$sql = "SELECT * FROM tag WHERE username='$username' AND namatugas='$namatugas'";
	$result = mysql_query($sql, $bd);
	$tag_awal = "";
	while ($row = mysql_fetch_array($result)){
		$tag_awal = $tag_awal . $row['tag'] . ",";
	}
	substr_replace($tag_awal, "", -1);
	if (strcmp($tag, $tag_awal) != 0){
		$array = explode(',', $tag_awal);
		foreach($array as $tagg){
			$sql = "DELETE FROM tag WHERE username='$username' AND namatugas='$namatugas' AND tag='".$tagg."'";
			$result = mysql_query($sql, $bd);
		}
		$pos = strpos($tag, ",");
		if ($pos === false){
			$sql = "INSERT INTO tag (username, namatugas, tag) VALUES ('$username', '$namatugas', '$tag')";
			$result = mysql_query($sql, $bd);
		} else {
			$array = explode(',', $tag);
			foreach ($array as $tagg){
				$sql = "INSERT INTO tag (username, namatugas, tag) VALUES ('$username', '$namatugas', '$tagg')";
				$result = mysql_query($sql, $bd);
			}
		}
	}
	
	echo "<html>";
	echo "<head>";
	echo "<script type='text/javascript'>";
	echo "function redirect(){";
	echo "alert('Task edited');";
	echo "window.location = 'rinciantugas.php';";
	echo "}";
	//echo "setTimeout('redirect()',10000);";
	echo "redirect();";
	echo "</script>";
	echo "</head>";
	echo "<body>";
	// if ($result1 && $result2 && $result3){
		// echo "berhasil";
	// } else if ($result1){
		// echo "satu";
	// } else if ($result2){
		// echo "dua";
	// } else if ($result3){
		// echo "tiga";
	// } else
	// echo "gagal";
	// echo $sql1;
	// echo $sql2;
	// echo $sql3;
	echo "</body>";
	echo "</html>";
	
	
	mysql_close($bd);
?>