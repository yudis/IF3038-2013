<?php
	include('config.php');
	
?>

<!DOCTYPE html>
<html>
	<head>
		<link href="style2.css" rel="stylesheet" type="text/css"></link>
		<link rel="stylesheet" href="style.css" type="text/css">
		<title>New Task</title>
	</head>
	<body>
		<header>
			<div id="header">
				<div id="topbar">
					<div id="topbar_logo">
						<a href="dashboard.html"><img id="logo" src="img/logo.png"></a>
					</div>
					<div id="topbar_logo2">
						<img id="logo2" src="img/namalogo.png">
					</div>
					<div id="topbar_border"></div>
					<div id="topbar_dashboard">
						<nav>
							<ul>
								<li> <a class="active" href="Dashboard.html"> Dashboard </a> </li>
								<li> <a href="profil.html">Profil</a> </li>
								<li> <a href="index.html">Logout</a> </li>
							</ul>
						</nav>
					</div>
					<div id="topbar_search">  
						<input type="search" results="5" placeholder="search"/>
					</div>
				</div>
			</div>
		</header>
		<script src="script.js" type="text/javascript"></script>
			<div id="inputarea">
				<center><h4>CATEGORY : </h4></center>
				<form name="input" action="addtask.php" enctype="multipart/form-data" method="POST" onsubmit="validating(bulan, tanggal, tahun);">
					<div id="metadata">
						<paa>Nama</paa><br>
						<paa>Attachment</paa><br>
						<paa>Deadline</paa><br>
						<paa>Asignee</paa><br>
						<paa>Tag</paa><br>
					</div>
					<div id="input">
						<pab><input type="text" name="nama" size="25" placeholder="Ketik nama task"></pab><br>
						<pab><input type="file" name="attach" size="25"></pab><br>
						<pab><select name="tahun">
							<option value = "2013">2013</option>
							<option value = "2014">2014</option>
							<option value = "2015">2015</option>
							<option value = "2016">2016</option>
							<option value = "2017">2017</option>
							<option value = "2018">2018</option>
							<option value = "2019">2019</option>
							<option value = "2020">2020</option>
							<option value = "2021">2021</option>
							<option value = "2022">2022</option>
							</select>
						<select name="bulan">
							<option value = "1">1</option>
							<option value = "2">2</option>
							<option value = "3">3</option>
							<option value = "4">4</option>
							<option value = "5">5</option>
							<option value = "6">6</option>
							<option value = "7">7</option>
							<option value = "8">8</option>
							<option value = "9">9</option>
							<option value = "10">10</option>
							<option value = "11">11</option>
							<option value = "12">12</option>
						</select>
							<select name="tanggal">
								<option value = "1">1</option>
								<option value = "2">2</option>
								<option value = "3">3</option>
								<option value = "4">4</option>
								<option value = "5">5</option>
								<option value = "6">6</option>
								<option value = "7">7</option>
								<option value = "8">8</option>
								<option value = "9">9</option>
								<option value = "10">10</option>
								<option value = "11">11</option>
								<option value = "12">12</option>
								<option value = "13">13</option>
								<option value = "14">14</option>
								<option value = "15">15</option>
								<option value = "16">16</option>
								<option value = "17">17</option>
								<option value = "18">18</option>
								<option value = "19">19</option>
								<option value = "20">20</option>
								<option value = "21">21</option>
								<option value = "22">22</option>
								<option value = "23">23</option>
								<option value = "24">24</option>
								<option value = "25">25</option>
								<option value = "26">26</option>
								<option value = "27">27</option>
								<option value = "28">28</option>
								<option value = "29">29</option>
								<option value = "30">30</option>
								<option value = "31">31</option>
							</select>
						</pab><br>
						<pab><input type="text" name="asignee" size="25"></pab><br>
						<pab><input type="text" name="tag" size="25"></pab><br>
					</div>
			
			<div id="tombol">
				<pac><input type="submit" id="tombolcreate" value="Create"></pac>
			</div>
			</div>
			</form>
	</body>	
</html>