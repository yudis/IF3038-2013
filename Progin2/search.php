

<html>
<head>

<title>Dashboard</title>
<link href="styles/header.css" rel="stylesheet" type="text/css" />
<link href="styles/dashboard.css" rel="stylesheet" type="text/css" />

<script>
	function showK1(){
		document.getElementById('k1').style.borderColor = '#836fff';
		document.getElementById('k2').style.borderColor = '#d9d9d9';
		document.getElementById('k3').style.borderColor = '#d9d9d9';
		document.getElementById('k11').style.display = 'block';
		document.getElementById('k12').style.display = 'block';
		document.getElementById('k13').style.display = 'block';
		document.getElementById('k14').style.display = 'block';
		document.getElementById('k21').style.display = 'none';
		document.getElementById('k22').style.display = 'none';
		document.getElementById('k31').style.display = 'none';
	}
</script>

<script>
	function showK2(){
		document.getElementById('k1').style.borderColor = '#d9d9d9';
		document.getElementById('k2').style.borderColor = '#836fff';
		document.getElementById('k3').style.borderColor = '#d9d9d9';
		document.getElementById('k11').style.display = 'none';
		document.getElementById('k12').style.display = 'none';
		document.getElementById('k13').style.display = 'none';
		document.getElementById('k14').style.display = 'none';
		document.getElementById('k21').style.display = 'block';
		document.getElementById('k22').style.display = 'block';
		document.getElementById('k31').style.display = 'none';
	}
</script>

<script>
	function showK3(){
		document.getElementById('k1').style.borderColor = '#d9d9d9';
		document.getElementById('k2').style.borderColor = '#d9d9d9';
		document.getElementById('k3').style.borderColor = '#836fff';
		document.getElementById('k11').style.display = 'none';
		document.getElementById('k12').style.display = 'none';
		document.getElementById('k13').style.display = 'none';
		document.getElementById('k14').style.display = 'none';
		document.getElementById('k21').style.display = 'none';
		document.getElementById('k22').style.display = 'none';
		document.getElementById('k31').style.display = 'block';
	}
</script>
</head>

<body>
	<div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="dashboard.html"><img src="images/logo.png" title="Home" alt="Home"/></a>
			</div>
			<div id="space">
			</div>
			<div class = "menu" id = "search">
				 <form name="search" method="post" action="search.php">
					 Search for: <input type="text" name="find" /> in 
					 <Select NAME="field">
					 <Option VALUE="semua">Semua</option>
					 <Option VALUE="username">Username</option>
					 <Option VALUE="namakategori">Judul Kategori</option>
					 <Option VALUE="tasktag">Task atau Tag</option>
					 </Select>
					 <input type="hidden" name="searching" value="yes" />
					 <button type="submit" id="searchbutton"></button>
				 </form>
			</div>
			<div class="menu" id="logout">
				<a href="index.html">Logout</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.html">Profile</a>
			</div>
			<div class="menu" id="home">
				<a href="dashboard.html">Home</a>
			</div>
        </div>
		
		<div id="category">
        	<div id="category_head">
				Kategori
			</div>
			
			<div class="category_block" id="k1" onclick="showK1()">
				<div class="category_pic">
					<img src="images/Book-icon.png" alt=""/>
				</div>
				<div class="category_name">
					Kuliah
				</div>
			</div>
			
			<div class="category_block" id="k2" onclick="showK2()">
				<div class="category_pic">
					<img src="images/Book-icon.png" alt=""/>
				</div>
				<div class="category_name">
					Eksperimen
				</div>
			</div>
			
			<div class="category_block" id="k3" onclick="showK3()">
				<div class="category_pic">
					<img src="images/menguasai-dunia.png" alt=""/>
				</div>
				<div class="category_name">
					Menguasai Dunia
				</div>
			</div>
			
			<div class="category_block" id="tambah_kategori" onclick="location.href='#category_form'">
				<div class="category_pic">
					<img src="images/tambah.png" alt=""/>
				</div>
				<div class="category_name">
					Tambah kategori...
				</div>
			</div>
		</div>
		<div id="task">
        	
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
			 echo "<h2>Hasil Pencarian</h2>";
			 echo "<p style='margin-left: 1em;'><b>Anda mencari : </b> " .$find . "</p>";
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
					 echo $result['fullname']; 
					 echo " "; 
					 echo "<img src=\"images/" . $result['avatar'] . "\" alt=\"\" />";
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
				 
				 
				 
				 
				 //Bila pagenum belum diset di parameter maka akan di set menjadi 1
				 if (!isset($pagenum)) 

				 { 
					$pagenum = 1; 
				 } 
				// jika parameter sudah diset maka dilakukan pengisian pageum dengna parameter
				if(empty($_GET['pagenum']))
				{
					
				}
				else
				{
					$pagenum = $_GET['pagenum'];
				}
				 //Now we search for our search term, in the field the user specified 
				 $data = mysql_query("SELECT * FROM user WHERE upper($field) LIKE'%$find%'"); 
				 $rows = mysql_num_rows($data); 
				 
				 //Jumlah hasil tiap page
				 $page_rows = 10; 

				 //Page terakhir
				 $last = ceil($rows/$page_rows); 

				 //Memastikan pagenum ada di range 1 sampai last
				 if ($pagenum < 1) 
				 { 
					$pagenum = 1; 
				 } 
				 elseif ($pagenum > $last) 	
				 { 
					$pagenum = $last; 
				 } 

				 //Melakukan set fungsi LIMIT untuk melakukan query selanjutnya
				 $max = 'LIMIT ' .($pagenum - 1) * $page_rows .',' .$page_rows; 
				 
				//Melakukan Query dengna menambahkan fungsi limit
				 $data_p = mysql_query("SELECT * FROM user WHERE upper($field) LIKE'%$find%' $max") or die(mysql_error()); 
				 
				 //Menampilkan hasil query
				 while($info = mysql_fetch_array( $data_p )) 
				 { 
					 echo "<p style='margin-left: 1em;'> Username : " . $info['username'] . "</p>";
					 echo "<p style='margin-left: 3em;'> Nama Lengkap : " . $info['fullname'] . "</p>";
					 echo "<p style='margin-left: 3em;'> Avatar : ";
					 echo "<img src=\"images/" . $info['avatar'] . "\" alt=\"\" />";
					 echo "</p>";
					 echo "<br>"; 
				 } 
				 
				 // Menunjukkan halaman pencarian
				 echo "<p>";
				 echo " --Page $pagenum of $last-- <p>";

				 // Jika pagenum bukan 1 maka ditampilkan link untuk ke First yaitu pagenum 1 dan previous
				 if ($pagenum == 1) 
				 {
							 
				 } 
				 else {
					 echo " <a href='{$_SERVER['PHP_SELF']}?pagenum=1'> <<-First</a> ";
					 echo " ";
					 $previous = $pagenum-1;
					 echo " <a href='{$_SERVER['PHP_SELF']}?pagenum=$previous'> <-Previous</a> ";
				 } 

				 echo " ---- ";


				 //Jika pagenum bukan last maka ditampilkan next dan last

				 if ($pagenum == $last) 
				 {
						
				 } 
				 else {
						 $next = $pagenum+1;
						 echo " <a href='{$_SERVER['PHP_SELF']}?pagenum=$next'>Next -></a> ";
						 echo " ";
						 echo " <a href='{$_SERVER['PHP_SELF']}?pagenum=$last'>Last ->></a> ";
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
					 echo " ";
					 echo "Status : ";
					 echo $result['status']; 
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
				echo "<br>";
			  echo "Hasil pencarian : ".$anymatches;
			 } 

			mysql_close($con);
			?>
		</div>
    </div>
</body>
</html>