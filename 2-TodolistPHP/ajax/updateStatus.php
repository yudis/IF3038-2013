<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/tugas.php';
	$q=$_GET["q"];
	$n=$_GET["n"];
	$tugas = new Tugas();
	if($n==0)
	{
		$tugas->setStats($q,1);
	}
	else if($n==1)
	{
		$tugas->setStats($q,0);
	}
?>