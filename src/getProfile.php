<?php

	$user=$_GET["user"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if(mysqli_connect_errno($con)){
		echo "error connect getProfil";	
	}	
	
	$query = "SELECT * FROM pengguna WHERE username='$user'";
	$result = mysqli_query($con,$query);
	
	$row = mysqli_fetch_array($result);
	echo "<div id=\"profheader\">";
	echo "<div>".$row['username']."</div>";
	echo "<div>".$row['nama_lengkap']."</div>";
	echo "<div>".$row['avatar']."</div>";//avatar
	echo "<div>".$row['tanggal_lahir']."</div>";
	echo "<div>".$row['email']."</div>";
	echo "</div>";
	
mysqli_close($con);	
?>