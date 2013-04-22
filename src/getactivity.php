<?php
	$user = $_GET["user"];

	$con = mysqli_connect('localhost', 'progin', 'progin');
	if (!$con)
	{
		die('Could not connect: ' . mysqli_error());
	}
	
	mysqli_select_db($con, "progin_405_13510029");
	
	$sql = "SELECT task.task_id, task.task_name, task.status, task.deadline, task.task_category 
			FROM task, task_incharge 
			WHERE task.task_id = task_incharge.task_id 
			AND task_incharge.people_incharge_task = '".$user."' 
			ORDER BY task.task_category ASC";
	$result = mysqli_query($con, $sql);
	$kategori = "";
	
	while ($row = mysqli_fetch_array($result, MYSQLI_ASSOC))
	{
		if ($kategori !== $row['task_category'])
		{
			$kategori = $row['task_category'];
			$sql2 = "SELECT category_name FROM category WHERE category_id = '".$kategori."'";
			$result2 = mysqli_query($con, $sql2);
			$row2 = mysqli_fetch_array($result2, MYSQLI_NUM);
			$namakategori = $row2[0];
			echo "<h2>".$namakategori."</h2>";
		}
		echo "<div class='tugas'>";
		echo "<div><a href='tugas.php?id=".$row['task_id']."'>".$row['task_name']."</a></div>";
		echo "<div>Submission: <strong>".$row['deadline']."</strong></div>";
		echo "<div>Status: ";
		if ($row['status'] === 0)
		{
			echo "Belum Selesai</div>";
		}
		else
		{
			echo "Selesai</div>";
		}
		echo "</div>";
	}
	
	mysqli_close($con);
?>