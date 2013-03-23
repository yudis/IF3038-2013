<?php

	$user=$_GET["user"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if(mysqli_connect_errno($con)){
		echo "error connect getProfForm";	
	}	
	
	$query = "SELECT nama_tugas,status from tugas natural join mengerjakan WHERE username='$user'";
	$result = mysqli_query($con,$query);
	
	while( $row = mysqli_fetch_array($result)){
		echo "<div id=\"listProfilBox2\">";
		if($row['status']==1){
			echo "<div>".$row['nama_tugas']."</div>";
		}
		echo "</div>";
	}
mysqli_close($con);	
?>