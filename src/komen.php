<?php
  session_start();
	require("dbfunc.php");
	require("datefunc.php");
	
	$konten = $_GET['kid'];
	$user = $_GET['user'];
	$isi = $_GET['isi'];
	$jml = $_GET['jml'];
	$jenis = $_GET['jenis'];
	$tgl = date("Y-m-d H:i:s");
	//jenis 1=komen, 2=hapus
	if ($jenis==1){
		//insert
		$qres = "INSERT INTO komentar (id,username,konten_id,tgl,isi) VALUES (NULL,'$user','$konten','$tgl','$isi')";
		if (!mysql_query($qres,$con))
		{
			die('Error1: ' . mysql_error());
		}
		$qres = "UPDATE konten SET jmlKom = '$jml' WHERE id = '$konten'";
		if (!mysql_query($qres,$con))
		{
			die('Error2: ' . mysql_error());
		}
		$qres = mysql_query("SELECT * FROM user WHERE username = '$user'");
		$row = mysql_fetch_array($qres);
		$kom = $row['jmlKom']+1;
		$qres = "UPDATE user SET jmlKom = '$kom' WHERE username = '$user'";
		if (!mysql_query($qres,$con))
		{
			die('Error3: ' . mysql_error());
		}
		if ($kom==1){
			$qres="INSERT INTO achievement (user,jenis) VALUES ('$user','3')";
			if (!mysql_query($qres,$con))
			{
				die('Error: ' . mysql_error());
			}
		}
		if ($kom==20){
			$qres="INSERT INTO achievement (user,jenis) VALUES ('$user','4')";
			if (!mysql_query($qres,$con))
			{
				die('Error: ' . mysql_error());
			}
		}
		//result
		$sres = mysql_query("SELECT * FROM komentar WHERE konten_id = '$konten' AND tgl ='$tgl'");
		if($sres){
			while ($brow = mysql_fetch_array($sres)){
				echo "<div id='komen".$brow['id']."'>";
					if (isset($_SESSION['username'])){
						if (strcmp($_SESSION['username'],$brow['username'])==0)
							echo "<div class='komen-hapus'><a href=\"javascript:deleting(".$brow['id'].",'".$_SESSION['username']."');\">(X)</a></div>";
					}
					echo "<div class='komen-ava'><img class='komen-img' src=\"ava/".$brow['username'].".jpg\" /></div>";
					echo "<div class='komen-nama'>".$brow['username']."</div>";
					echo "<div class='komen-tgl'>".diffDate($brow['tgl'],date('d-m-Y H:i:s'))."</div>";
					echo "<div class='komen-isi'>".$brow['isi']."</div>";
					echo "<div class='line-konten'></div>";
				echo "</div>";
			}
		}
	}else{
		//hapus
		$res = mysql_query("DELETE FROM komentar WHERE id = '$konten'");
		$qres = "UPDATE konten SET jmlKom = '$jml' WHERE judul = '$isi'";
		if (!mysql_query($qres,$con))
		{
			die('Error2: ' . mysql_error());
		}
		$qres = mysql_query("SELECT * FROM user WHERE username = '$user'");
		$row = mysql_fetch_array($qres);
		$kom = $row['jmlKom']-1;
		$qres = "UPDATE user SET jmlKom = '$kom' WHERE username = '$user'";
		if (!mysql_query($qres,$con))
		{
			die('Error3: ' . mysql_error());
		}
	}
?>
