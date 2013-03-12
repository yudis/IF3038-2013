<?php
$con = mysql_connect("localhost:3306","root","");
if (!$con)
  {
  die('Could not connect: ' . mysql_error());
  }

mysql_select_db("progin_405_13510057", $con);

//This is only displayed if they have submitted the form 
$searching = $_POST['searching']; 
$find = $_POST['find'];
$field = $_POST['field'];
 if ($searching =="yes") 
 { 
 echo "<h2>Hasil Pencarian</h2><p>"; 
  echo "<b>Anda mencari : </b> " .$find; 
  echo "<br></br>";
 //If they did not enter a search term we give them an error 
 if ($find == "") 
 { 
 echo "<p>Tolong masukkan data yang ingin anda cari"; 
 exit; 
 } 
 
 
 // We preform a bit of filtering 
 $find = strtoupper($find); 
 $find = strip_tags($find); 
 $find = trim ($find); 
 
 $anymatches=0; 
 
 if ($field == "semua" )
 {
	//Pencarian Username
	 $data = mysql_query("SELECT * FROM user WHERE upper(username) LIKE'%$find%'"); 
	 while($result = mysql_fetch_array( $data )) 
	 { 
		 echo "Username : ";
		 echo $result['username']; 
		 echo " "; 
		 echo " "; 
		 echo $result['fullname']; 
		 echo " "; 
		 echo " "; 
		 echo "avatarnya"; 
		 echo "<br>"; 
		 echo "<br>";
	 } 
	  $anymatches=mysql_num_rows($data); 
	  
	 //Pencarian nama kategori
	 $data = mysql_query("SELECT * FROM kategori WHERE upper(namakategori) LIKE'%$find%'"); 
	 while($result = mysql_fetch_array( $data )) 
	 { 
		echo "Nama kategori : ";
		 echo $result['namakategori']; 
		 echo "<br>"; 
		 echo "<br>"; 
	 } 
	  $anymatches=$anymatches + mysql_num_rows($data); 
	  
	//Pencarian task
	 //Now we search for our search term, in the field the user specified 
	$data = mysql_query("SELECT * FROM tag,tugas WHERE upper(isitag) LIKE'%$find%' AND tag.idtugas = tugas.idtugas"); 
	 
	 //And we display the results 
	while($result = mysql_fetch_array( $data )) 
	{ 
		echo "Tugas : ";
		 echo $result['namatugas']; 
		 echo " ";
		 echo "Deadline : ";
		 echo $result['deadline']; 
		 echo " ";
		echo "Tag : ";
		 echo $result['isitag']; 
		 echo "<br>"; 
		echo "<br>"; 
	} 
	  $anymatches= $anymatches + mysql_num_rows($data); 
 }
 
  if ($field == "username" )
 {
	 //Now we search for our search term, in the field the user specified 
	 $data = mysql_query("SELECT * FROM user WHERE upper($field) LIKE'%$find%'"); 
	 
	 //And we display the results 
	 while($result = mysql_fetch_array( $data )) 
	 { 
		 echo $result['username']; 
		 echo " "; 
		 echo " "; 
		 echo $result['fullname']; 
		 echo " "; 
		 echo " "; 
		 echo "avatarnya"; 
		 echo "<br>"; 
	 } 
	  $anymatches=mysql_num_rows($data); 
 }
  if ($field == "namakategori" )
 {
	//Now we search for our search term, in the field the user specified 
	 $data = mysql_query("SELECT * FROM kategori WHERE upper($field) LIKE'%$find%'"); 
	 
	 //And we display the results 
	 while($result = mysql_fetch_array( $data )) 
	 { 
		 echo $result['namakategori']; 
		 echo "<br>"; 
		 echo "<br>"; 
	 } 
	  $anymatches=mysql_num_rows($data); 
 }
  if ($field == "tasktag" )
 {
	//Now we search for our search term, in the field the user specified 
	$data = mysql_query("SELECT * FROM tag,tugas WHERE upper(isitag) LIKE'%$find%' AND tag.idtugas = tugas.idtugas"); 
	 
	 //And we display the results 
	while($result = mysql_fetch_array( $data )) 
	{ 
		echo "Tugas : ";
		 echo $result['namatugas']; 
		 echo " ";
		 echo "Deadline : ";
		 echo $result['deadline']; 
		 echo " ";
		echo "Tag : ";
		 echo $result['isitag']; 
		 echo "<br>"; 
		echo "<br>"; 
	} 
	  $anymatches=mysql_num_rows($data); 
 }
 
 //This counts the number or results - and if there wasn't any it gives them a little message explaining that 

 if ($anymatches == 0) 
 { 
 echo "Maaf data yang anda cari tidak terdaftar<br><br>"; 
 } 
 echo "Hasil pencarian : ".$anymatches;
 } 

mysql_close($con);
?>