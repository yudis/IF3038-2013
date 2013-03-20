<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/tugas.php';
	session_start();

	
	$tugas = new Tugas;
	
	$tugas->set_taskname($_POST["namatask"]);
	$tugas->set_tgl_deadline($_POST["deadline"]);
	$tugas->set_pemilik($_SESSION['user']);
	$tugas->set_id_kategori($_POST["namakategori"]);
	$tugas->store();
	$assigneeArr=explode(',', $_POST["assigneeI"]);
	echo json_encode($assigneeArr);
	$i=0;
	while($assigneeArr[$i]!="")
	{
		if($assigneeArr[$i]!=$_SESSION['user'])
		{
			echo $assigneeArr[$i],"+",$i;
			$tugas->addNewestAssignee($assigneeArr[$i]);
		}
		$i++;
	}
	
	$tags=explode(',', $_POST["tag"]);
	$i=0;
	while(!empty($tags[$i]))
	{
		if($tags[$i]!=$_SESSION['user'])
		{
			echo $tags[$i],"+",$i;
			$tugas->addNewestTag($tags[$i]);
		}
		$i++;
	}

	$tugas->addNewestAttachments('attachments');
	header('Location: dashboard.php');
?>