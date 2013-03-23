<?php

	$id=$_GET["id"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if(mysqli_connect_errno($con)){
		echo "error connect catTask";	
	}	
	
	$query = "SELECT * from tugas WHERE id_kategori='$id'";
	$result = mysqli_query($con,$query);
	$temp=0;
	while( $row = mysqli_fetch_array($result)){
		$temp+=1;
        echo "<a href=\"rinciantugas.php?id_tugas=".$row['id_tugas']."\">";
		echo "<div id=\"listtask\">";
		echo "<div>".$row['nama_tugas']."</div>";
		echo "<div>".$row['deadline']."</div>";
		echo "<div>tag: ".$row['tag']."</div>";
		
		if ($row['status']) {
			echo"<input type=\"checkbox\" name=\"Status\" checked onclick=\"ubahStatus(".$row['id_tugas'].")\">Selesai";
		} else {
			echo"<input type=\"checkbox\" name=\"Status\" onclick=\"ubahStatus(".$row['id_tugas'].")\">Belum Selesai";
		}
		
		echo "</div></a>";
	}
mysqli_close($con);	
?>