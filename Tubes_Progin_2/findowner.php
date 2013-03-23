<?php
	$id_task = $cur_task['id_task'];
	$findowner_sql = "SELECT pemilik FROM task WHERE id_task='$id_task'";
	$findowner_result = mysqli_query($con,$findowner_sql);
	$taskowner = mysqli_fetch_array($findowner_result);
	if($taskowner==$cur_user){
		//tampilkan button delete
	}
?>