<?php
	include "connect.php";
	$user = $_COOKIE['name'];
	$file_name = $_FILES['attachment_upload']['name'];
	$file_name = stripslashes($file_name);
	$file_name = str_replace("'","",$file_name);
	$copy = copy
	(
		$_FILES['attachment_upload']['tmp_name'],"../file/".$file_name
	);
	
	function rand_string( $length ) {
		$str = "";
		$chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";	
		$size = strlen( $chars );
		for( $i = 0; $i < $length; $i++ ) {
			$str .= $chars[ rand( 0, $size - 1 ) ];
		}
		return $str;
	}
	$idUnik = true;
	$idTask = rand_string(8);
	$result = mysql_query("SELECT * FROM task WHERE idtask ='".$idTask."'");
	if (mysql_fetch_array($result)>0) $idUnik = false;
	while(!$idUnik){
		$idTask = rand_string(8);
		$result = mysql_query("SELECT * FROM task WHERE idtask ='".$idTask."'");
		if (mysql_fetch_array($result)>0) $idUnik = false;
		else $idUnik = true;
	}
	
	$query = "INSERT INTO task (idtask,idcat,taskname,status,attachment,deadline)
            VALUES(
					 '".$idTask."'
					 ,'".$_GET['idcat']."'
                ,'".$_POST['task_name_input']."'
                ,'0'
                ,'file/".$file_name."'
					 ,'".$_POST['deadline_input']."'
                );";
	mysql_query($query);
	
	$query = "INSERT INTO do (username, idtask)
				 VALUES(
					'".$user."'
					,'".$idTask."'
				 );";
	
	mysql_query($query);
	
	$arrayA = explode(",",$_POST['assignee_input']);
	for($i=0; $i<count($arrayA); $i++){
		$query = "INSERT INTO do (username, idtask)
				 VALUES(
					'".$arrayA[$i]."'
					,'".$idTask."'
				 );";
		mysql_query($query);
	}
	
	$arrayA = explode(",", $_POST['tag_input']);
	for($i=0; $i<count($arrayA); $i++){
		$query = "INSERT INTO tag (idtask, name)
				 VALUES(
					'".$idTask."'
					,'".$arrayA[$i]."'
				 );";
		mysql_query($query);
	}
	
	$location = "Location: taskDetail.php?idtask=".$idTask;
	header($location);
?>