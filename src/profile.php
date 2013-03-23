<?php
session_start();

if (ISSET($_SESSION['username']))
	{

	}
		else
	{
		header("location: index.php");
	}

?>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>Next: You Can't Do it Alone</title>
		<link rel="stylesheet" type="text/css" href="css/profile.css">
		<script type="text/javascript" src="js/popup.js"></script>
		<script src="js/utama.js"></script>
	</head>
	<body>
	<?
		include "connectdb.php";
	?>
		<!-- header.............................................................................................................-->
		<div class="header">
			<?php require_once("header.php"); ?>
		  </div>
		<!--====================================================================-->
		
		
		
		<div id="content">
		<?
			$perintah = "SELECT * FROM accounts WHERE username='".$_SESSION['username']."'";
			$result = mysql_query($perintah);
			$row = mysql_fetch_array($result);
			
			$perintah2 = "select nama from tugas natural join accounts_has_tugas natural join accounts where accounts.username='".$_SESSION['username']."' and tugas.status_selesai='1'";
			$result2 = mysql_query($perintah2);
			$row2 = mysql_fetch_array($result2);
			
			$perintah3 = "select nama from tugas natural join accounts_has_tugas natural join accounts where accounts.username='".$_SESSION['username']."' and tugas.status_selesai='0'";
			$result3 = mysql_query($perintah3);
			$row3 = mysql_fetch_array($result3);
		?>
			<div>
				<div class="profheader">
						<div id="avatarnya">
							<img id="avat" src="<?echo $row['avatar']?>" >
						</div>
						<div id="username">
							<?echo $row['username']?>
						</div>
						<div id="namalengkap">
							Nama : <?echo $row['nama_lengkap']?>
						</div>
						<div id="tanggallahir">
							Tanggal Lahir : <?echo $row['tgl_lahir']?>
						</div>
						<div id="email">
							Email : <?echo $row['email'] ?>
						</div>
						<div id="editprof" onclick="popup('popUpDiv')">
							Edit
						</div>
				</div>
				
					
			</div>
			<div>
				<section class="profil1Form cf">
				<ul>
					Tugas yang sudah selesai
					<br></br>
						<?
						if (count($row2['nama'])==0)
						{
							echo "-";
						}
							else
						{
							while (count($row2['nama'])!=0)
								{
									echo "- ".$row2['nama']."<br>";
									$row2 = mysqli_fetch_array($result2);
								}
						}
						?>
					<br></br>
					Tugas On Progress
					<br></br>
					<?
							if (count($row3['nama'])==0)
								{
									echo "-";
								}
									else
								{										
								while (count($row3['nama'])!=0)
								{
									echo "- ".$row3['nama']."<br>";
									$row3 = mysqli_fetch_array($result3);
								}
						}
					?>
				<ul>	
				</section>
			</div>
			
			<div id="blanket" style="display:none;"></div>
			<div id="popUpDiv" style="display: none;">
                <div id="newcategory">
                    <div id="closeedit">
                        <a href="" onclick="popup('popUpDiv')" >CLOSE</a>
                    </div>
                    <div id="edittitle">Edit Profile</div>
                    <div id="elcategory">
                        <form name="registration" action="editprofile.php" method="post" enctype="multipart/form-data">
                            <input name="username" type="hidden" value="<? echo $row['nama_lengkap']?>" id="txtsamp" />
							<input name="email" type="hidden" value="<? echo $row['email']?>" id="txtsamp" />
							<label>Nama Lengkap</label>
                            <input name="namaleng" placeholder="Nama Lengkap" onkeyup="nama_validatingprof()">
							<img src="pict/blank.png" alt="icon2" id="nameicon"  />
                            <label>Avatar</label>
                            <input type="file" name="avatar" onchange="avatar_validatingprof()" />
							<img src="pict/blank.png" alt="icon7" id="avaicon" />
							<label>tanggal lahir</label>
							<input type="text" placeholder="Tanggal Lahir" name="tanggal" id="date" onkeyup="date_validatingprof()" />
							<img src="pict/blank.png" alt="icon8" id="dateicon"  />
							<label>Ubah Password</label>
							<input name="password" type="password" placeholder="password" onkeyup="pass_validatingprof()"  />
							<img src="pict/blank.png" alt="icon3" id="passicon" />
							<label>Confirm Password</label>
							<input name="confirmpass" type="password" placeholder="confirm password" onkeyup="conf_validatingprof()"   />
							<img src="pict/blank.png" alt="icon4" id="conficon" />
							</br></br>
                            <input class= "submitreg" disabled id="submitedit" name="submit" type="submit" value="Save Change">
                        </form>
                    </div>
                </div>
            </div>
		</div>
		
		</div>
		<!-- ==================================================================-->
		
	</body>
</html>