<html>

    
        <title> Next | Search Result </title>    
	<?php 
	include 'header.php'; 
	$tugas = $_POST["search"];
	$filter = $_POST["searchFilter"];
	
	$con = mysqli_connect("localhost","root","","progin_405_13510003");
	
	if (mysqli_connect_errno($con)){
		echo "error";	
	}

	if($filter=="semua"){
	
		$query2 = "SELECT * FROM kategori WHERE nama_kategori = '$tugas'";
		$result2 = mysqli_query($con,$query2);
		
		while($row2= mysqli_fetch_array($result2)){
			echo "<div class=\"listtaskJo\">";
			echo "kategori:";		
			echo "<div >" . $row2['nama_kategori']."</div>";
			echo "<div >" . $row2['username']."</div>";
			echo "</div>";
		}
	
		$query = "SELECT * FROM tugas NATURAL JOIN kategori WHERE nama_tugas = '$tugas'";
		$result = mysqli_query($con,$query);
		
		while($row= mysqli_fetch_array($result)){
			echo "<div class=\"listtaskJo\">";
			echo "tugas:";
			echo "<div >" . $row['nama_kategori']."</div>";		
			echo " <a href=\"rinciantugas.php?id_tugas=" . $row['id_tugas'] . "\">	";			
			echo "<div >" . $row['nama_tugas']."</div>";
			echo " </a>";
			echo "<div >" . $row['deadline']."</div>";
			echo "<div >" . "tag : " . $row['tag']."</div>";
			echo "<div >" . "status : " . $row['status']."</div>";		
			echo "<input type=\"checkbox\"> ";			
			echo "</div>";
		}
		
		$query4 = "SELECT * FROM tugas NATURAL JOIN kategori WHERE tag = '$tugas'";
		$result4 = mysqli_query($con,$query4);
		
		while($row4= mysqli_fetch_array($result4)){
			echo "<div class=\"listtaskJo\">";
			echo "tag:";
			echo "<div >" . $row4['tag']."</div>";
			echo " <a href=\"rinciantugas.php?id_tugas=" . $row4['id_tugas'] . "\">	";			
			echo "<div >" . "tugas: " .$row4['nama_tugas']."</div>";
			echo " </a>";			
			echo "<div >" . "kategori: " . $row4['nama_kategori']."</div>";
			echo "<input type=\"checkbox\"> ";						
			echo "</div>";
		}	
	
		$query3 = "SELECT * FROM pengguna WHERE username = '$tugas'";
		$result3 = mysqli_query($con,$query3);
		
		while($row3= mysqli_fetch_array($result3)){
			echo "<div class=\"listtaskJo\">";
			echo "user:";
			echo "<div >" . $row3['username']."</div>";
			echo "<div >" . $row3['tanggal_lahir']."</div>";									
			echo "<div >" . $row3['nama_lengkap']."</div>";
			echo "<div >" . "<img width=\"60px\" height=\"60px\" src=\"".$row3['avatar']."\">" ."</div>";
			echo "</div>";
		}
	
	}//end semua
	
	else if($filter=="username"){
		$query3 = "SELECT * FROM pengguna WHERE username = '$tugas'";
		$result3 = mysqli_query($con,$query3);
		
		while($row3= mysqli_fetch_array($result3)){
			echo "<div class=\"listtaskJo\">";
			echo "user:";
			echo "<div >" . $row3['username']."</div>";
			echo "<div >" . $row3['tanggal_lahir']."</div>";									
			echo "<div >" . $row3['nama_lengkap']."</div>";
			echo "<div >" . "<img width=\"60px\" height=\"60px\" src=\"".$row3['avatar']."\">" ."</div>";
			echo "</div>";
		}
	}//end username
	
	else if($filter=="email"){
		$query3 = "SELECT * FROM pengguna WHERE email = '$tugas'";
		$result3 = mysqli_query($con,$query3);
		
		while($row3= mysqli_fetch_array($result3)){
			echo "<div class=\"listtaskJo\">";
			echo "user:";
			echo "<div >" . $row3['email']."</div>";			
			echo "<div >" . $row3['username']."</div>";
			echo "<div >" . $row3['nama_lengkap']."</div>";
			echo "<div >" . "<img width=\"60px\" height=\"60px\" src=\"".$row3['avatar']."\">" ."</div>";
			echo "</div>";
		}
		
	}//end email
	
	else if($filter=="namalengkap"){
		$query3 = "SELECT * FROM pengguna WHERE nama_lengkap = '$tugas'";
		$result3 = mysqli_query($con,$query3);
		
		while($row3= mysqli_fetch_array($result3)){
			echo "<div class=\"listtaskJo\">";
			echo "user:";
			echo "<div >" . $row3['nama_lengkap']."</div>";			
			echo "<div >" . $row3['username']."</div>";
			echo "<div >" . $row3['nama_lengkap']."</div>";
			echo "<div >" . "<img width=\"60px\" height=\"60px\" src=\"".$row3['avatar']."\">" ."</div>";
			echo "</div>";
		}		
	}//end namalengkap
	
	else if($filter=="birthdate"){
		$query3 = "SELECT * FROM pengguna WHERE tanggal_lahir = '$tugas'";
		$result3 = mysqli_query($con,$query3);
		
		while($row3= mysqli_fetch_array($result3)){
			echo "<div class=\"listtaskJo\">";
			echo "user:";
			echo "<div >" . $row3['tanggal_lahir']."</div>";			
			echo "<div >" . $row3['username']."</div>";
			echo "<div >" . $row3['nama_lengkap']."</div>";
			echo "<div >" . "<img width=\"60px\" height=\"60px\" src=\"".$row3['avatar']."\">" ."</div>";
			echo "</div>";
		}		
		
	}//end birthday
	
	else if($filter=="kategori"){
		$query2 = "SELECT * FROM kategori WHERE nama_kategori = '$tugas'";
		$result2 = mysqli_query($con,$query2);
		
		while($row2= mysqli_fetch_array($result2)){
			echo "<div class=\"listtaskJo\">";
			echo "kategori:";		
			echo "<div >" . $row2['nama_kategori']."</div>";
			echo "<div >" . $row2['username']."</div>";
			echo "</div>";
		}
		
	}//end kategori

	else if($filter=="tugas"){
		$query = "SELECT * FROM tugas NATURAL JOIN kategori WHERE nama_tugas = '$tugas'";
		$result = mysqli_query($con,$query);
		
		while($row= mysqli_fetch_array($result)){
			echo "<div class=\"listtaskJo\">";
			echo "tugas:";
			echo "<div >" . $row['nama_kategori']."</div>";		
			echo " <a href=\"rinciantugas.php?id_tugas=" . $row['id_tugas'] . "\">	";			
			echo "<div >" . $row['nama_tugas']."</div>";
			echo " </a>";			
			echo "<div >" . $row['deadline']."</div>";
			echo "<div >" . "tag : " . $row['tag']."</div>";
			echo "<div >" . "status : " . $row['status']."</div>";		
			echo "<input type=\"checkbox\"> ";						
			echo "</div>";
		}			
	}//end tugas

	else if ($filter=="tag"){
		$query4 = "SELECT * FROM tugas NATURAL JOIN kategori WHERE tag = '$tugas'";
		$result4 = mysqli_query($con,$query4);
		
		while($row4= mysqli_fetch_array($result4)){
			echo "<div class=\"listtaskJo\">";
			echo "tag:";
			echo "<div >" . $row4['tag']."</div>";
			echo " <a href=\"rinciantugas.php?id_tugas=" . $row4['id_tugas'] . "\">	";			
			echo "<div >" . $row4['nama_tugas']."</div>";
			echo " </a>";			
			echo "<div >" . "kategori: " . $row4['nama_kategori']."</div>";
			echo "<input type=\"checkbox\"> ";						
			echo "</div>";
		}	
		
	}//end tag

	else if($filter=="komentar"){
		$query4 = "SELECT * FROM komentar WHERE isi = '$tugas'";
		$result4 = mysqli_query($con,$query4);
		
		while($row4= mysqli_fetch_array($result4)){
			echo "<div class=\"listtaskJo\">";
			echo "komentar:";
			echo "<div >" . $row4['isi']."</div>";
			echo "<div >" . $row4['username']."</div>";
			echo "<div >" . $row4['id_tugas']."</div>";
			echo "</div>";
		}	
		
	}//end komentar
		
	mysqli_close($con);	
	?>                 
<!----------------------------------------- body ----------------------------------------->                 

        
    <div class="main">
		       
       		<div id="divTugas">
            
            
       
            
            </div> <!-- end divTugas-->
       
    </div> <!-- end div main-->


       
       
<!----------------------------------------- footer ----------------------------------------->                      
	<body onLoad="showUserLogin() ">
        <div class="footer">
            Copyright � Ahmad Faiz - Fandi Pradana - Sigit Aji
        </div>
        
    </body>
</html>