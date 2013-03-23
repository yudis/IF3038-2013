			<div id="task">
				<div class="row">
					<div class="cell th">Task Name</div>
					<div class="cell th centered">Deadline</div>
					<div class="cell th centered">Tags</div>			
					<div class="cell th centered">Status</div>
					<div class="cell th centered">Ubah Status</div>
					<div class="cell th centered">Hapus Status</div>
				</div>			
<?php
	require 'connectDB.php';
	$category = (isset($_GET['category'])) ? $_GET['category'] : "";
	
	if($category == ""){
		$query = "select * from task";
	}
	else{
		$query = "select * from task where category = '" . $category . "'";
	}
	
	$result = mysql_query($query);
	
	while($row = mysql_fetch_array($result)){ ?>
		<div class="row" id="<?php echo $row['taskname'] . $row['category']; ?>">
			<div class="cell"><?php echo $row['taskname']; ?></div>
			<div class="cell centered"><?php echo $row['deadline']; ?></div>
			<div class="cell centered"><?php echo $row['tags']; ?></div>
			<div class="cell centered" id="status<?php echo $row['taskname'] . $row['category']; ?>"><?php echo $row['status'] == 1 ? 'Selesai' : 'Belum Selesai'; ?></div>
			<div class="cell centered">
				<input class="<?php echo $row['taskname'] . $row['category']; ?>"type="checkbox" id="<?php echo 'changestatus.php?taskname=\'' . $row['taskname'] . '\'' . '&category=\'' . $row['category'] . '\'' ; ?>" value="1" <?php echo $row['status'] == 1 ? 'checked' : ''; ?> onchange="changeStatus(this)" />
			</div>
			<div class="cell centered">
				<button class="<?php echo $row['taskname'] . $row['category']; ?>" id="<?php echo 'deletetask.php?taskname=\'' . $row['taskname'] . '\'' . '&category=\'' . $row['category'] . '\'' ; ?>" onclick="deleteTask(this); return false;">Delete</button>
			</div>
		</div>
	<?php }	?>
	</div>
	<?php mysql_close($con);
?>