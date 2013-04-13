<?php
	function connectDatabase(){
			//create connection
			$con = mysqli_connect("localhost","root","","sharedtodolist");
			
			//check the connection
			if (mysqli_connect_errno($con)) {
			echo "Gagal melakukan koneksi ke MySQL : " . mysqli_connect_error();
			}
		return $con;
	}
	
	function closeDatabase(){
		mysqli_close($con);
	}
?>