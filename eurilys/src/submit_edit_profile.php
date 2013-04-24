<?php
	session_start();
	//session_destroy();
	ob_start();

	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$ret		 = 1;

	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	$username = $_SESSION['username']; 

	
	if (isset($_POST['edit_profile_submit'])) { //when submit button button is pressed

		//process full name
		if($_POST['fullname'] !== null) {
			//edit user's full name
			$fullname = mysql_real_escape_string($_POST['fullname']);
			$query = "UPDATE user SET full_name='$fullname' WHERE username='$username'";
			if (mysql_query($query)) {
				mysql_query($query);
			} else {
				$ret = 0;
			}
		}

		//process birthdate
		if($_POST['birthdate'] !== null) {
			//edit user's birthdate
			$birthdate = mysql_real_escape_string($_POST['birthdate']);
			$query = "UPDATE user SET birthdate='$birthdate' WHERE username='$username'";
			if (mysql_query($query)) {
				//mysql_query($query);				
			} else {
				$ret = 0;
			}
		}

		if($_POST['password'] !== "") {
			//edit user's password
			//checking password confirmation is not necessary as it's done in javascript
			$password = mysql_real_escape_string($_POST['password']);
			$query = "UPDATE user SET password='$password' WHERE username='$username'";
			if (mysql_query($query)) {
				mysql_query($query);				
			} else {
				$ret = 0;
			}
		}
		var_dump($_FILES["avatar"]);

		if(is_uploaded_file($_FILES['avatar']['tmp_name'])) {
			$target = "../img/".$username.$_FILES["avatar"]["name"];
			move_uploaded_file($_FILES["avatar"]["tmp_name"],$target);
			$query = "UPDATE user SET avatar='$target' WHERE username='$username'";
			if (mysql_query($query)) {
				mysql_query($query);				
			} else {
				$ret = 0;
			}

		}

		header('location:profile.php');
		

}	


/*//process avatar
	if(isset($_POST['submitavatar'])){	
		if(is_uploaded_file($_FILES['filename']['tmp_name'])){
			$maxsize=5000000;
			$size=$_FILES['filename']['size'];
			// getting the image info.
			$imgdetails = getimagesize($_FILES['filename']['tmp_name']);
			$mime_type = $imgdetails['mime']; 
 
			// checking for valid image type
			if(($mime_type=='image/jpeg')||($mime_type=='image/gif')){
	  		// checking for size again
	  		if($size<$maxsize){
	    		$filename=$_FILES['filename']['name'];	
	    		$imgData =addslashes (file_get_contents($_FILES['filename']['tmp_name']));
	    		$sql="UPDATE user SET avatar=$imgData WHERE username=$username";					
	    		mysql_query($sql) or die(mysql_error());
	  		}else{
	    		echo "<font class='error'>Image to be uploaded is too large..Error uploading the image!!</font>";
	    		//echo "$size </br> $maxsize";
	  		}
		}else{
	  		echo "<font class='error'>Not a valid image file! Please upload jpeg or gif image.</font>";
		}
 	}else{			
  		echo "Error uploading avatar";	
  	}		
	} */ 




?>