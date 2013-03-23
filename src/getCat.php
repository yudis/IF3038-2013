
<?php

	//$user=$_GET["user"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if(mysqli_connect_errno($con)){
		echo "error connect getCat";	
	}	
	
	$query = "SELECT * from kategori";
	$result = mysqli_query($con,$query);
	
	while( $row = mysqli_fetch_array($result)){
		echo "<div id=\"categ\" onclick=\"showAddTask();catTask($row[id_kategori]);  \">";
		echo "<div>".$row['nama_kategori']."</div>";
		echo "</div>";
	}
mysqli_close($con);	
?>