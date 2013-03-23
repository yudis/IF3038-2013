<?php
	require_once("connectdb.php");
	
	$id = $_GET['id'];
	$komen = $_GET['komen'];
	$iduser = $_GET['iduser'];
	
	$sql = "INSERT
			INTO komentar (isi, tugas_idtugas, accounts_idaccounts, CREATED)
			VALUES ('".$komen."','".$id."',".$iduser.", NOW());";
	$result = mysql_query($sql);
	
	
	$sql = "SELECT * FROM accounts
			WHERE idaccounts='".$iduser."';";
	$hasil = mysql_query($sql);
	$fetch = mysql_fetch_array($hasil);
	$mysqldate = date("H:i - d F");
	
	mysql_close();
?>

<div class="kotakwarna daftarkomen ">
  <div>
	<div class="komenkolom">
		<a href=""><img class="komenava ava" src="<?php echo ($fetch['avatar']) ?>"/></a>
	</div>
	<div class="komenkolom komenkomen">
		<a href="#" class="komennama"><?php echo ($fetch['username'])."<BR>"; ?></a>
		<p><?php echo $komen; ?></p>
	</div>
  </div>
  <div class="komenwaktu">
	<a><?php echo $mysqldate; ?></a>
  </div>
</div>