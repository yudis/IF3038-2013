<?php
	include('config.php');
	session_start();
	if (isset($_REQUEST['username']) && isset($_REQUEST['namatugas'])){
		$namauser = stripslashes($_REQUEST['username']);
		$namatugas = stripslashes($_REQUEST['namatugas']);
		$_SESSION['namatugas'] = $namatugas; 
		$_SESSION['namauser'] = $namauser;
	} else {
		$namauser = $_SESSION['namauser'];
		$namatugas = $_SESSION['namatugas'];
	}
?>
<!DOCTYPE html>
<html>
	<head>
		<link href="style2.css" rel="stylesheet" type="text/css"></link>
		<script src="script.js" type="text/javascript" ></script>
		<link rel="stylesheet" href="style.css" type="text/css">
		<title>Rincian Tugas</title>
	</head>
	<body>
		<header>
			<div id="header">
				<div id="topbar">
					<div id="topbar_logo">
						<a href="dashboard.php"><img id="logo" src="img/logo.png"></a>
					</div>
					<div id="topbar_logo2">
						<img id="logo2" src="img/namalogo.png">
					</div>
					<div id="topbar_border"></div>
					<div id="topbar_dashboard">
						<nav>
							<ul>
								<li> <a class="active" href="dashboard.php"> Dashboard </a> </li>
								<li> <a href="profil.php">Profil</a> </li>
								<li> <a href="index.php">Logout</a> </li>
							</ul>
						</nav>
					</div>
					<div id="topbar_search">  
						<input type="search" results="5" placeholder="search"/>
					</div>
				</div>
			</div>
		</header>
		</div>
			<div id="container">
				<div id="edited">
					<div id="rincian">
						<pab>Nama task</pab>
						<pab>Attachment</pab>
						<pab>Deadline</pab>
						<pab>Assignee</pab>
						<pab>Tag</pab>
					</div>
					<div id="isi">
						<pae id="namatugas"><?php
							echo $namatugas;?></pae><br>
						<pae id="fileattach"><?php $sql="SELECT * FROM attachment WHERE username='$namauser' AND namatugas='".$namatugas."'";
							$result = mysql_query($sql, $bd);
							$row = mysql_fetch_array($result);
							$namafile = $row['namafile'];
							if ($row['tipefile'] == "image/jpeg" || $row['tipefile'] == "image/jpg" || $row['tipefile'] == "image/pjpeg" || $row['tipefile'] == "image/gif" || $row['tipefile'] == "image/x-png" || $row['tipefile'] == "image/bmp"){
								echo "<img src=picscript.php?imname=" . $namafile . "&user=dummy&task=satu>";
							}
							?></pae><br>
						<pae id="tanggaldeadline"><?php $sql="SELECT deadline FROM tugas WHERE username='$namauser' AND namatugas='".$namatugas."'";
							  $result = mysql_query($sql, $bd);
							  while ($row = mysql_fetch_array($result)){
								echo $row['deadline'];
							  }
							  ?></pae><br>
						<pae id="listassign"><?php $sql="SELECT asignee FROM asigner WHERE username='$namauser' AND namatugas='".$namatugas."'";
							  $result = mysql_query($sql, $bd);
							  $out = "";
							  while ($row = mysql_fetch_array($result)){
								 $out = $out . $row['asignee'] . " ";
							  }
							  echo $out;?></pae><br>
						<pae id="listtag"><?php $sql="SELECT tag FROM tag WHERE username='$namauser' AND namatugas='".$namatugas."'";
							 $result = mysql_query($sql, $bd);
							 $out = "";
							 while ($row = mysql_fetch_array($result)){
								$out = $out . $row['tag'] . " ";
							 }
							 echo $out;?></pae><br>
					</div>
				</div>
				<div id="editing">
					<form name="edittugas" action="taskdetail.php" method="POST">
						<div id="rincian">
							<pab>Nama task</pab>
							<pab>Attachment</pab>
							<pab>Deadline</pab>
							<pab>Assignee</pab>
							<pab>Tag</pab>
						</div>
						<div id="isi">
							<pae><?php echo $namatugas;?></pae><br>
							<pae>help.txt</pae><br>
							<pae><select id="tahun" name="tahun">
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
								<select id="bulan" name="bulan">
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
								<select id="tanggal" name="tanggal">
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
								</pae><br>
							<pae><input type="text" id="asignee" name="asignee" size="25" value="<?php $sql="SELECT asignee FROM asigner WHERE username='$namauser' AND namatugas='".$namatugas."'";
								$result = mysql_query($sql, $bd);
								$out = "";
								while ($row = mysql_fetch_array($result)){
									$out = $out . $row['asignee'] . ",";
								}
								echo substr_replace($out ,"",-1);?>"></pae><br>
								<pae><input type="text" id="tag" name="tag" size="25" value="<?php $sql="SELECT tag FROM tag WHERE username='$namauser' AND namatugas='".$namatugas."'";
									$result = mysql_query($sql, $bd);
									$out = "";
									while ($row = mysql_fetch_array($result)){
										$out = $out . $row['tag'] . ",";
									}
									echo substr_replace($out ,"",-1);?>"></pae><br>
						</div>
				</div>
					<div id="tombol_edit">
						<input type="button" id="edit_button" value="Edit Task" onclick="display('editing','done_edit')"><br>
						<form name="Hapustask" action="deletetask.php" method="POST">
							<input type="submit" id="delete_button" name="deletetask" value="Delete">
						</form>
					</div>
					<div id="done_edit">
						<input type="submit" id="done_button" value="Done Editing"><br>
						<pai><input type="checkbox" id="status" name="statuss" value="true" <?php $sql = "SELECT status FROM tugas WHERE username='$namauser' AND namatugas='".$namatugas."'";
						$result = mysql_query($sql, $bd);
						$out = mysql_fetch_array($result);
						if ($out['status'] == 1)
							echo "checked";?>>Done</input></pai>
					</div>
					</form>
				<div id="comment">
					<div id="komentar">
						<pab>Komentar</pab>
					</div>
					<div id="isi2">
						<b>:</b>
					</div>
					<div id="tuliskomen">
						<form name="comment" action="addcomment.php" method="POST">
							<?php $sql = "SELECT penulis, komentar FROM comment WHERE username='$namauser' AND namatugas='".$namatugas."'";
							$result = mysql_query($sql, $bd);
							while ($row = mysql_fetch_array($result)){
								echo "<pag><b>";
								echo $row['penulis'];
								echo "</pag></b><br>";
								echo "<pah>";
								echo $row['komentar'];
								echo "</pah><br>";
							}
							?>
							<p>
								<textarea id="areatulis" name="areacomment" rows="4" cols="34" placeholder="Tulis komentar anda"></textarea>
							</p>
							<input type="submit" id="submitdone" value="Tulis Komentar">
						</form>
					</div>
				</div>
			</div>
			<?php mysql_close($bd); ?>
	</body>
</html>