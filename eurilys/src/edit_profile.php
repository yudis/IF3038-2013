<script type="text/javascript" src="../js/editprofile.js"> </script> 
<?php include 'logged_in_header.php';
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	
	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}

	/*get user details from database*/
	$query	= "SELECT * FROM user WHERE username='$username' LIMIT 1";
	$result	=  mysql_query($query) or die(mysql_error());

	/*put details to variable*/
	$row = mysql_fetch_array($result, MYSQL_ASSOC);
	$username = $row['username'];
	$fullname = $row['full_name'];
	$birthdate = $row['birthdate'];
	$email = $row['email'];
	
 ?>	

<section>
	<!-- Navigation Bar -->
	<?php include 'navigation_bar.php'; ?>
	
	<div id="dynamic_content">
		<div id="edit_profil_container">
			<h1>  Edit Profile </h1>
			<form id="edit_profile_form" method="POST" action="submit_edit_profile.php" enctype="multipart/form-data">
				<label> Username </label> <?php echo $username;?>
				<input type="hidden" id="edit_username" value="<?php echo $username;?>">
				<input type="hidden" id="edit_email" value="<?php echo $email;?>">
				
				<h2>Change Password</h2>
				<label> New Password </label> 	
				<input type="password" name="password" onkeypress="javascript:editProfileCheck();" id="edit_password" title="Password should be at least 8 characters long" >
				<img src="../img/no.png" id="password_validation" class="signup_form_validation" alt="validation image">
				
				<label> Confirm New Password </label> 	
				<input type="password" name="password_confirm" onkeypress="javascript:editProfileCheck();" id="edit_password_confirm" title="Confirmation password should be the same with Password" >
				<img src="../img/no.png" id="confirm_validation" class="signup_form_validation" alt="validation image">
				<div class="clear"></div>
				
				<h2>Change Details</h2>
				<label> Full Name </label> 
				<input type="text" name="fullname" id="fullname" onkeypress="javascript:editProfileCheck();" value="<?php echo $fullname; ?>" title="Your name should be at least consists first name and last name">
				<img src="../img/yes.png" id="name_validation" class="signup_form_validation" alt="validation image">
				<br><label> Birthdate </label> <input type="date" id="birthdate" name="birthdate" value=<?php echo $birthdate;?>>  
				<br><label> Avatar </label> <input type="file" id="avatar" name="avatar" onchange="javascript:editProfileCheck();">
				
				<input type="submit" name="edit_profile_submit" class="link_red top10 bold" id="edit_profile_submit" value="SAVE" disabled="disabled">
				
			</form>
			<!--<form enctype='multipart/form-data' name='avatarupload' action='submit_edit_profile.php' method='POST'>
				<input name='filename' type='file'>
				<input type='submit' value='Submit' name='submitavatar'>
			</form>-->
		</div>
	</div>
</section>
		
<?php include '../footer.php'; ?>