<?php
	$q=$_GET["q"];
	require_once("getcateg.php");
	$con= connectDatabase();
	$result = mysql_query($con, "SELECT * FROM task WHERE namaKategori = '".$q."'");
	
	while($row = mysqli_fetch_array($result))
	  {
		
		$namatask=$row[0];
		$deadline=$row[1];
		$status=$row[2];
		?>
		<a href="deletetask.php"><img src="images/delete.png"/></a>
		<a href="viewtask.php?nama="'.$namatask.'"><?php echo $namatask; ?></a>
		<?php
		echo "<PRE></PRE>";
		echo $deadline;
		echo "<PRE></PRE>";
		?>
		<a href="changetaskstatus.php?task="'.$namatask.'"&filter=-2"><img src="images/check.png"/></a>
		<?php echo $status; ?>
		<?php
		echo "<PRE></PRE>";
		
		$result2 = mysqli_query($con,"SELECT tag FROM tagging WHERE namaTask = '$namatask'");
		while($row2 = mysqli_fetch_array($result2)){
			$tag=$row2[0];
			echo $tag;
			echo ", ";

		}
		
		echo "<PRE></PRE>";
		}
		
?>