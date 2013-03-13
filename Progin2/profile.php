<html>
<head>
<title>Profile</title>
<link href="styles/profile.css" rel="stylesheet" type="text/css" />
<link href="styles/header.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div id="container">
		<div id="header">
        	<div class=logo id="logo">
				<a href="dashboard.php"><img src="images/logo.png" title="Home" alt="Home"/></a>
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
			<div class="menu" id="logout" action="logout.php">
				<a href="index.html">Logout</a>
			</div>
			<div class="menu" id="profile">
				<a href="profile.php">Profile</a>
			</div>
			<div class="menu" id="home">
				<a href="dashboard.php">Home</a>
			</div>
        </div>
		<div id="profilearea">
			<div class="profilephoto">
				<?php
				session_start();
				if(!isset($_SESSION['id']))
				  header("location:index.html");
				  
				$con = mysql_connect("localhost:3306","root","");
				if (!$con)
				  {
				  die('Could not connect: ' . mysql_error());
				  }

				mysql_select_db("progin_405_13510057", $con);
				
				$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
				while($row = mysql_fetch_array($result)) {
					echo "<img alt=\"\" src=\"".$row['avatar']."\"/>";
				}
				?>
			</div>
			<div class="biodata">
				<?php
				$result = mysql_query("SELECT * FROM user WHERE username='$_SESSION[id]'");
				while($row = mysql_fetch_array($result)) {
					echo "<br>";
					echo "Username  : ".$row['username']."<br><br>";
					echo "Fullname  : ".$row['fullname']."<br><br>";
					echo "Birthdate : ".date('d F Y',strtotime($row['birthdate']))."<br><br>";
					echo "Handphone : ".$row['phonenumber']."<br><br>";
					echo "Email	  	: ".$row['email']."<br>";
				}
				mysql_close($con);
				?>
				<button type="button" id="editbutton"></button>
			</div>
		</div>
		<div id="listarea">
			<div id="undonetasktitle">
				Undone Task
			</div>
			<div id="donetasktitle">
				Done Task
			</div>
        	<div id="undonetask">
				<div class="task_block" id="k11">
					<div class="task_judul">
						Cari Buku
					</div>
					<div class="task_deadline">
						Deadline: 21 February 2013
					</div>
					<div class="task_tag">
						Tags: kuliah
					</div>
				</div>
				
				<div class="task_block" id="k12">
					<div class="task_judul">
						Tugas Intelegensia Buatan
					</div>
					<div class="task_deadline">
						Deadline: 22 February 2013
					</div>
					<div class="task_tag">
						Tags: kuliah, IB, tugas
					</div>
				</div>
		
				<div class="task_block" id="k21">
					<div class="task_judul">
						Cari Monyet
					</div>
					<div class="task_deadline">
						Deadline: 24 February 2013
					</div>
					<div class="task_tag">
						Tags: eksperimen
					</div>
				</div>
				
				<div class="task_block" id="k22">
					<div class="task_judul">
						Beli Formalin
					</div>
					<div class="task_deadline">
						Deadline: 25 February 2013
					</div>
					<div class="task_tag">
						Tags: eksperimen
					</div>
				</div>
				
				<div class="task_block" id="k13">
					<div class="task_judul">
						Tugas IMK
					</div>
					<div class="task_deadline">
						Deadline: 25 February 2013
					</div>
					<div class="task_tag">
						Tags: kuliah, IMK, tugas
					</div>
				</div>
				
				<div class="task_block" id="k14">
					<div class="task_judul">
						Kuis IMK
					</div>
					<div class="task_deadline">
						Deadline: 28 February 2013
					</div>
					<div class="task_tag">
						Tags: kuliah, IMK, kuis
					</div>
				</div>
				
				<div class="task_block" id="k31">
					<div class="task_judul">
						Latihan Silat
					</div>
					<div class="task_deadline">
						Deadline: 28 February 2013
					</div>
					<div class="task_tag">
						Tags: bela diri
					</div>
				</div>
	
			</div>
			<div id="donetask">
				<div class="task_block" id="k41">
					<div class="task_judul">
						Ngulang Pelajaran
					</div>
					<div class="task_deadline">
						Deadline: 12 February 2013
					</div>
					<div class="task_tag">
						Tags: kuliah
					</div>
				</div>
				
				<div class="task_block" id="k42">
					<div class="task_judul">
						Beli Racun
					</div>
					<div class="task_deadline">
						Deadline: 15 February 2013
					</div>
					<div class="task_tag">
						Tags: eksperimen
					</div>
				</div>
		
				<div class="task_block" id="k43">
					<div class="task_judul">
						Belajar Progin
					</div>
					<div class="task_deadline">
						Deadline: 18 February 2013
					</div>
					<div class="task_tag">
						Tags: kuliah
					</div>
				</div>
			</div>
        </div>
	</div>
</body>
</html>