<?php
	$task = new Task();
	$task->data = $_POST;
	$task->id_user = $this->currentUserId;
	$task->checkValidity();
	//$task->save();
	$selection = array("asdf","ghjkl");/* 
	$select = "";
		if ($selection)
		{
			foreach($selection as $tempselect)
			{
				$select .= $tempselect."____________ ";
			}
			$select = substr($select, 0, -2);
		}
			else
			{
				$select = "*";
			}
	print_r($select); */
	//tag parsing pake koma
	// bikin tag model
	// array --> jadiin model dengan id_task
	// get id after insert mysq
	// save tag beserta id nya, kalau udah ada ga di save lagi
	// save assignee beserta id nya
	//$task->checkValidity();
	exit;
?>