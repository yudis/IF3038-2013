<?php include 'header.php';?>
		
<div id="main">
	<div id="konten">
		<div class="atas">
		</div>
		<div class="tengah">
			<div class="judul">
				PROFIL
			</div>
			<div class="isi">
				<img src=<?php echo $_SESSION['avatar'];?> alt="avatar" width="620"/>
			</div>
			
			<div id="profiledetail">
			<div class="profilefont"> Username		: <?php echo $_SESSION['myusername'];?> </div>
			<div class="profilefont"> Nama Lengkap	: <?php echo $_SESSION['fullname'];?> </div>
			<div class="profilefont"> Tanggal lahir : <?php echo $_SESSION['birthdate'];?> </div>
			<div class="profilefont"> Email			: <?php echo $_SESSION['email'];?> </div>
			<div class="profilefont"> Jenis Kelamin : <?php if ($_SESSION['gender'] == 'M') echo 'laki-laki'; else echo 'perempuan';?> </div>
            <div class="profilefont"> Tugas :
            <?php 
            	$member_id=$_SESSION['id'];
				$result1=mysqli_query($con,"SELECT * FROM `assignees` WHERE member=$member_id");
				$count=mysqli_num_rows($result1);
				if ($count > 0) {
					echo '<br /><ol>';
					while ($row=mysqli_fetch_array($result1)) {
						$task_id=$row['task'];
						$result2=mysqli_query($con,"SELECT * FROM tasks WHERE id=$task_id");
						$task=mysqli_fetch_array($result2);
						echo '<li>'.$task['name'].'</li>';
					}
					echo '</ol>';
				}
            ?>
            </div>
			<div class="profilefont"> About me		: <?php echo $_SESSION['about'];?> </div>
			<div class="register-submit"><input type="button" name="register" value="Edit" disabled="able" id="form-button"  /></div>
			</div>
			
			
		<div class="bawah">
		</div>
	</div>
</div>
		
<?php include 'footer.php';?>