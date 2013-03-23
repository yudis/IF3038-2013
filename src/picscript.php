<?php 
	$image = stripslashes($_REQUEST['imname']);
	$namatask = stripslashes($_REQUEST['task']);
	$user = stripslashes($_REQUEST['user']);
	$sql="SELECT * FROM attachment WHERE username='".addslashes($user)."' AND namatugas='".addslashes($namatask)"' AND namafile='".addslashes($image)."'";
	$result = mysql_query($sql, $bd);
	$row = mysql_fetch_assoc($result);
	$imagebytes = $row['file'];
	header("Content-type: image/jpeg");
	print $imagebytes;
?>