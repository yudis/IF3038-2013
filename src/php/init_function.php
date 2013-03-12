<?php 
	function getConnection(){
		// Create connection
		$con=mysqli_connect("localhost","root","","progin_405_13511601");
		// Check connection
		if (mysqli_connect_errno($con))
		{
	  		echo "Failed to connect to MySQL: " . mysqli_connect_error();
		}
		return $con;
	}
	
	function upload_file($file,$username){
		if ($file["error"] > 0)
	  {
		  echo "Error: " . $file["error"] . "<br>";
	  }
	else
	  {
		  $extension = end(explode(".", $file["name"]));
		  move_uploaded_file ($file['tmp_name'],"avatar/$username.$extension");
	  }
	}
?>