<?php include 'connect.php' ?>
<?php
	$q = $_GET['q'];
	$continue = strtok($q,"|");
	$newpass = strtok("|");
	
	$user = $_COOKIE['username'];
	$sql_id = mysqli_query($conn, "SELECT * FROM user WHERE username LIKE '".$user."'");
	$id = mysqli_fetch_array($sql_id);
	
	if ($continue == 1)
		mysqli_query($conn, "UPDATE user SET password = '" . $newpass . "' WHERE id = " . $id['id']);
	
	echo "
		<span id='left'>
			<span id='change_password'>
				<button class='link_tosca' id='change_pass_button' onclick='change_pass()'> Change Password </button>";
			if ($id['password'] == $newpass && $continue == 1)
				echo "You input the same password as your old one !";
	echo "
			</span>
		</span>
		<span id='right'>
			<span id='form_change_password'>
			</span>
		</span>
	";
?>