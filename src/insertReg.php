<?php
	
	$user=$_POST["user"];
	$pwd=$_POST["pwd"];
	$nama=$_POST["nama"];
	$tgl=$_POST["tgl"];
	$email=$_POST["email"];
	$avatar=$_POST["avatar"];

	$con = mysqli_connect("localhost","root","","progin_405_13510003");

	if(mysqli_connect_errno($con)){
		echo "error gan";	
	}
	
	$query = "INSERT INTO pengguna (username,password,nama_lengkap,tanggal_lahir,email,avatar) VALUES ('$user','$pwd','$nama','$tgl','$email','$avatar')";
	
	mysqli_query($con,$query);
	
	mysqli_close($con);	
	
?>