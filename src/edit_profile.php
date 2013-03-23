<?php include 'connect.php'?>
<?php
	$username = $_COOKIE['username'];
	$sql = mysqli_query($conn, "SELECT * FROM user WHERE username LIKE '".$username."'");
	$profile = mysqli_fetch_array($sql);
	
	$sql2 = mysqli_query($conn, "SELECT id FROM user WHERE fullname LIKE '".$profile['fullname']."'");
	$id = mysqli_fetch_array($sql2);

	
	echo "<div id='upperprof'>
		<img id='profpic' src='../img/avatar/".$profile['avatar']."' alt=''>
		<div id='namauser'>
			<input type='text' class='bio_edit' id='bio_fullname' value='" . $profile['fullname'] . "'>
		</div>
	</div>
	<div id='bio'>
		<span id='left'>
		<b>Username</b>
		<br/>
		<b>Email</b>
		<br/>
		<b>Birthdate</b>
		<br/>
		<button class='link_tosca' id='save_profile_button' onclick=\"save_profile(".$id['id'].",'".$profile['password']."')\"> Save Changes </button>
		</span>
		<span id='right'>
			: " . $profile['username'] . "
			<br/>
			: <input type='email' class='bio_edit' id='bio_email' value='" . $profile['email'] . "'>
			<br/>
			: <input type='text' class='bio_edit' name='bio_birthdate' id='bio_birthdate' value='" . $profile['birthdate'] . "'>
		</span>
	</div>";
?>