<?php
	$user = new User();
	$user->data = $_POST;
	$user->avatar = $_FILES['avatar']['name'];	
	$temperror = $user->checkValidity();
	
	if ($temperror)
	{
		// TODO print error screen
		$return["error"] = array_merge($return["error"], $temperror);
	}
	else
	{
		// generate token
		$temp = explode(".",$_FILES['avatar']['name']);
		$extension = end($temp);
		$new_filename = strtoupper(md5(uniqid(rand(), true))).".".$extension;
		$user->avatar = $new_filename;
		if ($user->save())
		{
			move_uploaded_file($_FILES["avatar"]["tmp_name"],$_SESSION['full_path']."upload/user_profile_pict/" . $new_filename);
			
			Header("Location: index");
			// entah move ke index atau langsung login atau pesan sukses
		}
		else
		{
			// TODO print error screen
			echo "fail";
		}
	}
?>