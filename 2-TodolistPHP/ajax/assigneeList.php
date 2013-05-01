<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/user.php';
	
	$user = new User();
	
	$user = $user->getUser();
	 
	$i=0;
	while(!empty($user[$i]["username"]))
	{
		echo "<option value='",$user[$i]["username"],"'>";
		$i++;
	}
?>