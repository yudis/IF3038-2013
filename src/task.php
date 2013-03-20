<?php
  
  
?>


<?php

  if ($_GET['tujuan'] == "buat"){
	
	require_once("connectdb.php");
	require_once("php_class/createtask.php");  
	$ct = new CreateTask($_POST, $_FILES);
	$ct->Create();
	
  } else
  if ($_GET['tujuan'] == "lihat"){
	
	require_once("connectdb.php");
	require_once("php_class/viewtask.php");
	$vt = new ViewTask($_GET['id']);
	
	$_COOKIE["lt_tugas"] = $vt->getTask();
	$_COOKIE["lt_attachment"] = $vt->getAttachment();
	
	//
	//foreach ($task as $key => $value) {
	//  echo "Key: $key; Value: ".json_encode($value)."<br />\n";
	//}
	
	require_once("lihattask.php");
	
  } else {}
  
  
?>