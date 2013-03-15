<?php

final class DBConnection {
	public static $dbh;

	public function openDBconnection() {
		// Create connection
		$con=mysqli_connect('localhost', 'progin', 'progin', 'progin_405_13510033');
		// Check connection
		if (mysqli_connect_errno($con)) 
		{
			echo 'Failed to connect to MySQL:' . mysqli_connect_error();
		}
	}
	
	public function closeDBconnection(){
		// Create connection
		$con=mysqli_connect('localhost', 'progin', 'progin', 'progin_405_13510033');
		// Check connection
		if (mysqli_connect_errno($con)) 
		{
			echo 'Failed to connect to MySQL:' . mysqli_connect_error();
		}
		mysqli_close($con);
	}
}