<?php

if (($_COOKIE['username'] == '') && ($_COOKIE['password'] == '')) {
    header('Location:../index.php') ; 
}

include "koneksi.php";

$curr_username = $_COOKIE['username'];
$sql_id = mysql_query("SELECT id FROM user WHERE username LIKE '".$curr_username."'");
$loginid = mysql_fetch_array($sql_id);

?>
<!DOCTYPE html>
<html>
	<head>
		<script type="text/javascript" src="../js/edit_task.js"> </script> 
        <script type="text/javascript" src="../js/animation.js"></script> 
		<script>
		window.onunload = function(){
  window.opener.location.reload();
};
		</script>
		
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >		
		<title> Eurilys </title>
	</head>
<?php

$namakat=$_POST['namakat'];
$a = $loginid['id'];

$simpan = mysql_query("insert into kategori (namakat,idcreator)
values ('$namakat','$a')");
if ($simpan){

echo "<script>alert('Berhasil'); history.go(-1); window.close();  </script>";

} else {
echo "<script>alert('Gagal'); history.go(-1)</script>";
}
?>
</html>