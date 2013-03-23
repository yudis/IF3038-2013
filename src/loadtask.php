			<div class="row">
				<div class="cell th">Task Name</div>
				<div class="cell th centered">Deadline</div>
				<div class="cell th centered">Tags</div>			
			</div>
<?php
	require 'connectDB.php';
	
	$query = "select * from task";
	$result = mysql_query($query);
	while($row = mysql_fetch_array($result)){ ?>
		<div class="row" id="<?php echo $row['taskname']; ?>">
			<div class="cell"><?php echo $row['taskname']; ?></div>
				<div class="cell centered"><?php echo $row['deadline']; ?></div>
				<div class="cell centered"><?php echo $row['tags']; ?></div>
		</div>
	<?php }	
	mysql_close($con);
?>