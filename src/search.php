<?php
	require_once("database.php");
	$con = connectDatabase();
	$search = $_GET["searchquery"];
	$filter = $_GET["filtersearch"];
	
	if($filter == 0){
		$result = mysqli_query($con,"SELECT * FROM user
			WHERE username='$search'");

			while($row = mysqli_fetch_array($result))
			  {
			  echo $row['username'] . " " . $row['email'];
			  echo "<br>";
			  }
	} else if ($filter == 1){
		$result = mysqli_query($con,"SELECT * FROM user
			WHERE username='$search'");

			while($row = mysqli_fetch_array($result))
			  {
			  echo $row['username'] . " " . $row['email'];
			  echo "<br>";
			  }
	} else if ($filter == 2){
		$result = mysqli_query($con,"SELECT * FROM kategori
			WHERE namaKategori='$search'");

			while($row = mysqli_fetch_array($result))
			  {
			  echo $row['namaKategori'] . " " . $row['creatorKategoriName'];
			  echo "<br>";
			  }
	} else if ($filter == 3){
		$result = mysqli_query($con,"SELECT * FROM task
			WHERE namaTask='$search'");

			while($row = mysqli_fetch_array($result))
			  {
			  echo $row['namaTask'] . " " . $row['status'];
			  echo "<br>";
			  }
			  
		$result = mysqli_query($con,"SELECT * FROM tagging
			WHERE tag='$search'");

			while($row = mysqli_fetch_array($result))
			  {
			  echo $row['namaTask'] . " " . $row['tag'];
			  echo "<br>";
			  }
			  
	}
?>