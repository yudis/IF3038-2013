<?php
	include('config.php');
	session_start();
	
	$table1 = "tugas";
	$table2 = "attachment";
	$table3 = "asigner";
	$table4 = "tag";
	
	$namatugas = $_POST["nama"];
	$asignee = $_POST["asignee"];
	$tgl = $_POST["tanggal"];
	$bln = $_POST["bulan"];
	$thn = $_POST["tahun"];
	$tag = $_POST["tag"];
	$tanggal = $bln . "-" . $tgl  . "-" . $thn;
	$tanggal = mysql_real_escape_string($tanggal);
	$tanggal = strtotime($tanggal);
	$tanggal = date('Y-m-d',$tanggal);
	
	$result3 = false;
	if ($_FILES['attach']['size'] > 0){
		$filename = $_FILES['attach']['name'];
		$tempname = $_FILES['attach']['tmp_name'];
		$filesize = $_FILES['attach']['size'];
		$filetype = $_FILES['attach']['type'];
		
		$fp = fopen($tempname, 'r');
		$content = fread($fp, filesize($tempname));
		$content = addslashes($content);
		fclose($fp);
		
		if (!get_magic_quotes_gpc()){
			$filename = addslashes($filename);
		}
		$sql = "INSERT INTO $table2 (username, namatugas, namafile, tipefile, size, file) VALUES ('dummy', '$namatugas', '$filename', '$filetype', '$filesize', '$content')";
		$result3 = mysql_query($sql, $bd);
	}
	
	$sql = "INSERT INTO $table1 (username, namatugas, deadline, kategori, status) VALUES ('dummy', '$namatugas', '$tanggal', 'dummy', 0)";
	$result = mysql_query($sql, $bd);
	
	$pos = strpos($tag, ',');
	if ($pos === false){
		$sql = "INSERT INTO $table4 (username, namatugas, tag) VALUES ('dummy', '$namatugas', '$tag')";
		$result1 = mysql_query($sql, $bd);
	} else {
		$array = explode(',', $tag);
		foreach($array as $tags){
			$sql = "INSERT INTO $table4 (username, namatugas, tag) VALUES ('dummy', '$namatugas', '$tags')";
			$result1 = mysql_query($sql, $bd);
		}
	}
	
	$pos = strpos($asignee, ',');
	if ($pos === false){
		$sql = "INSERT INTO $table3 (username, namatugas, asignee) VALUES ('dummy', '$namatugas', '$asignee')";
		$result2 = mysql_query($sql, $bd);
	} else {
		$array = explode(',', $asignee);
		foreach($array as $asign){
			$sql = "INSERT INTO $table3 (username, namatugas, asignee) VALUES ('dummy', '$namatugas', '$asign')";
			$result2 = mysql_query($sql, $bd);
		}
	}
	$final = $result && $result1 && $result2 && $result3;
	if ($final){
		echo "success";
	} else {
		echo "fail";
	}
	mysql_close($bd);
?>

<html>
	<script type="text/javascript">
		function redirect(){
			window.location = "dashboard.html";
		}
		setTimeout('redirect()',3000);
	</script>
</html>