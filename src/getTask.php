<?php

	//$user=$_GET["user"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if(mysqli_connect_errno($con)){
		echo "error connect getTask";	
	}	
	
	$query = "SELECT * from tugas";
	$result = mysqli_query($con,$query);
	
	while( $row = mysqli_fetch_array($result)){
		$query2 = "SELECT nama_kategori FROM kategori WHERE (id_kategori=$row[id_kategori])";
		$result2 = mysqli_query($con,$query2);
		$row2 = mysqli_fetch_row($result2);
        echo "<a href=\"rinciantugas.html?id_tugas=".$row['id_tugas']."\">";
		echo "<div id=\"listtask\">";
		echo "<div>".$row['nama_tugas']."</div>";
		echo "<div>".$row['deadline']."</div>";
		echo "<div>tag: ".$row['tag']."</div>";
		if ($row['status']) {
			echo"<input type=\"checkbox\" name=\"Status\" checked onclick=\"ubahStatus(".$row['id_tugas'].")\">Selesai";
		} else {
			echo"<input type=\"checkbox\" name=\"Status\" onclick=\"ubahStatus(".$row['id_tugas'].")\">Belum Selesai";
		}
		echo "<div><button type=\"button\">Delete</button></div>";
	
		echo "<div>".$row2[0]."</div>";
		echo "</div></a>";
	}
mysqli_close($con);	
?>