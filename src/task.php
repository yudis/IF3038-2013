<?php
  
  /* 
   * 
   * - BUAT
   * - LIHAT
   * 
   */
  
?>


<?php

  if ($_GET['tujuan'] == "buat"){
	
	require_once("connectdb.php");
	require_once("php_class/createtask.php");  
	$ct = new CreateTask($_POST, $_FILES);
	$ct->Create();
	//$ct->Tes();
	echo "<a href='task.php?tujuan=lihat&id=".$ct->getId()."'>klik disini</a>";
	
  } else
  if ($_GET['tujuan'] == "lihat"){
	
	require_once("connectdb.php");
	require_once("php_class/viewtask.php");
	$vt = new ViewTask($_GET['id']);
	
	$_COOKIE["lt_tugas"] = $vt->getTask();
	$_COOKIE["lt_attachment"] = $vt->getAttachment();
	$_COOKIE["lt_assignee"] = $vt->getAssignee();
	$_COOKIE["lt_tag"] = $vt->getTag();
	$_COOKIE["lt_komen"] = $vt->getKomen();
	$_COOKIE["lt_komentator"] = $vt->getKomentator();
	
	//echo var_dump($vt->getTag());
	
	//echo json_encode($vt->getAssignee());
	//$aaa = $vt->getKomentator();
	//$bbb = $vt->getKomen();
	//foreach ($aaa as $key => $value) {
	//  echo "Key: $key; Value: ".json_encode($value)."<br />\n";
	//}
	//foreach ($bbb as $key => $value) {
	//  echo "Key: $key; Value: ".json_encode($value)."<br />\n";
	//}
	
	require_once("lihattask.php");
	
  } else {}
  
  
?>