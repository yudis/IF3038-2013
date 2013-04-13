<?php session_start(); ?>
<!DOCTYPE html>
<html lang="en">
	<body>
		
		<?php include "header.php"?>
		<div class="body">
		
			<div class="profile">
				<img src="images/User1.jpg" class="profpic">
				<p class="nama">Only for you.</p>
				<input type="submit" id="editprofilebutton" value="Edit Profile" onclick="editProfile()">
				<?php 
					echo $login_session ."\n";
					echo $FullNamelogin_session ."\n";
					echo $Tanggallogin_session ."\n";
					echo $Bulanlogin_session ."\n";
					echo $Tahunlogin_session ."\n";
				?>
				
				<br>
				<br>
				<div class="atributprofile">Password</div>
                <div id="atributpassword">(tidak ditampilkan)</div>
				<br>
				<div class="atributprofile">Confirm Password</div>
                <div id="atributconfirmpassword">(tidak ditampilkan)</div>
			</div>	
						
						
			<div class="catagories">
				<br>
                <h3>Daftar Tugas</h3>
				<?php
						$result3 = mysql_query('SELECT * FROM tugas INNER JOIN kategori ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id ) WHERE iduser = '.+ $IDlogin_session);
						while($row3 = mysql_fetch_array($result3)){
							$namatask = $row3 ['namatask'];
							echo "<li><a href=\"rinciantugas5.php\">$namatask</a>";

						}
				?>
            </div>
            <div class="contents">
				<br>
                <h3>Tugas Selesai</h3>
                <?php
						$result4 = mysql_query('SELECT * FROM tugas INNER JOIN kategori ON ( tugas.idkat = kategori.id ) INNER JOIN assignee ON ( assignee.idtask = tugas.id ) WHERE tugas.status = 1 AND iduser = '.+ $IDlogin_session);
						while($row4 = mysql_fetch_array($result4)){
							$namatask2 = $row4 ['namatask'];
							echo "<li><a href=\"rinciantugas5.php\">$namatask2</a>";

						}
				?>
				<br />
				
			</div>
			
			
            
		</div>
		<footer>
            CanDo. Yes you can!
            <br>
            About us
		</footer>
	</body>

</html>
