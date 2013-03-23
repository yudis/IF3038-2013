		<div class="row">
			<div class="cell th centered">Category Name</div>
		</div>
<?php
	require 'connectDB.php';
	
	$query = "select * from category";
	$result = mysql_query($query);
	while($row = mysql_fetch_array($result)){ ?>
		<div class="row" id="<?php echo $row['category']; ?>" onclick="showTasks(this)">
			<div class="cell"><?php echo ucwords($row['category']); ?></div>
		</div>
	<?php }	
	mysql_close($con);
?>