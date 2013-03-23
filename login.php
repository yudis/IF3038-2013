<?php
	require ('connect.php');
	session_start();
	if(isset($_SESSION['loggedin'])){
		die("You are already logged in!");
	}
	else{
	   $uname = mysql_real_escape_string($_GET["uname"]);
	   $pass = mysql_real_escape_string($_GET["pass"]); 
	   $mysql = mysql_query("SELECT * FROM user WHERE username = '$uname' AND password = '$pass'") or die(mysql_error());
	   if(mysql_num_rows($mysql) < 1){
		 echo("gagal");
	   }
	   else{
			$hq=mysql_fetch_array($mysql);
		   
			$name=$hq['name'];
			$dob=$hq['dob'];
			$email=$hq['email'];
			$loc=$hq['avatar'];
			$iduser=$hq['iduser'];
			$jmltask=$hq['jmltask'];

			$_SESSION['uname'] = $uname;
			$_SESSION['name'] = $name;
			$_SESSION['dob'] = $dob;
			$_SESSION['email'] = $email;
			$_SESSION['iduser'] = $iduser;
			$_SESSION['avatar'] = $loc;
			$_SESSION['jmltask'] = $jmltask;
			echo("berhasil");
		}
	}
	?>