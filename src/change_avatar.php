<?php include 'connect.php' ?>
<?php
	$curr_username = $_COOKIE['username'];
	$sql_id = mysqli_query($conn, "SELECT id FROM user WHERE username LIKE '".$curr_username."'");
	$loginid = mysqli_fetch_array($sql_id);
	
	$avatar = $_FILES['avatar']['name'];
	mysqli_query($conn, "UPDATE user SET avatar='".$avatar."' WHERE id=".$loginid['id']);
	
	if ($_FILES['avatar']['error'] > 0) {
		echo "Return Code: " . $_FILES["avatar"]["error"] . "<br>";
	} else {
		move_uploaded_file($_FILES["avatar"]["tmp_name"], "../img/" . $_FILES["avatar"]["name"]);
	}

	
	header("Location:profile.php?user=".$curr_username);
?>