<?php
	require 'utilities/db.php';
	require 'utilities/model.php';
	require 'utilities/view.php';
	require 'models/tugas.php';
	$q=$_GET["q"];
	$tugas = new Tugas();

	echo "<table border ='1'>";
	if($q=="")
	{
		$i=1;
		$success[$i] = $tugas->getTugas($i);
		while (!empty($success[$i]))
		{
			echo "<tr>";
			echo "<td>" , $success[$i]["nama"], "</td>";
			echo "<td>" , $success[$i]["kategori"], "</td>";
			echo "</tr>";
			$i++;
			$success[$i] = $tugas->getTugas($i);
		}
	}
	else
	{
		$i=1;
		$success[$i] = $tugas->getTugas($i);
		while (!empty($success[$i]))
		{
			if($success[$i]["kategori"]==$q)
			{
				echo "<tr>";
				echo "<td>" , $success[$i]["nama"], "</td>";
				echo "<td>" , $success[$i]["kategori"], "</td>";
				echo "</tr>";
			}
			$i++;
			$success[$i] = $tugas->getTugas($i);
		}
	}
	echo "</table>";
?>