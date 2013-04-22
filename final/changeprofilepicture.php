<?php
	if(isset($_FILES['cpp'])){
		$file_name = $_FILES['cppname'];
		$file_size =$_FILES['cpp']['size'];
		$file_tmp =$_FILES['cpp']['tmp_name'];
		
		if($file_size > 2097152){
			$errors[]='File size must be less than 2 MB';
		}
		
		if(empty($errors)==true){
			move_uploaded_file($file_tmp,"avatar/".$file_name."jpg");
		}
	}
?>