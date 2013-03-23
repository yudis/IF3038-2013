<?php
	$taskname = (isset($_GET['taskname'])) ? $_GET['taskname'] : "";
	$category = (isset($_GET['category'])) ? $_GET['category'] : "";
	$username = (isset($_GET['username'])) ? $_GET['username'] : "";
	
	require 'connectDB.php';
	$query = "select * from task where taskname = " . $taskname . " and category = " . $category;
	
	$result = mysql_query($query);
	$row = mysql_fetch_array($result);
	?>
	<h2><?php echo $row['taskname']; ?></h2>
	<div class="row">
		<div class="cell">Status</div>
		<div class="cell" id="status<?php echo $row['taskname'] . $row['category']; ?>"><?php echo $row['status'] == 1 ? 'Selesai' : 'Belum Selesai'; ?></div>
	</div>	
	<div class="row">
		<div class="cell">Change status</div>
		<div class="cell">
			<input class="<?php echo $row['taskname'] . $row['category']; ?>"type="checkbox" id="<?php echo 'changestatus.php?taskname=\'' . $row['taskname'] . '\'' . '&category=\'' . $row['category'] . '\'' ; ?>" value="1" <?php echo $row['status'] == 1 ? 'checked' : ''; ?> onchange="changeStatus(this)" />
		</div>
	</div>	
	<div class="row">
		<div class="cell">Deadline</div>
		<div class="cell"><?php echo $row['deadline']; ?></div>
	</div>
	<div class="row">
		<div class="cell">Assignee</div>
		<div class="cell"><?php echo $row['assignee']; ?></div>
	</div>
	<div class="row">
		<div class="cell">Tags</div>
		<div class="cell"><?php echo $row['tags']; ?></div>
	</div>
	<button class="<?php echo $row['taskname'] . $row['category']; ?>" id="<?php echo 'deletetask.php?taskname=\'' . $row['taskname'] . '\'' . '&category=\'' . $row['category'] . '\'' ; ?>" onclick="deleteTask(this); return false;">Delete Task</button>
	<?php
	$query = "select count(*) as count from comments where taskname = " . $taskname . " and category = " . $category;
	
	$result = mysql_query($query);
	$row = mysql_fetch_array($result);
	?>
	
	<h3><?php echo $row['count']; ?> Comments</h3>
	
	<?php
	$query = "select * from comments where taskname = " . $taskname . " and category = " . $category . "order by time asc";
	
	$result = mysql_query($query);
	while($row = mysql_fetch_array($result)){ ?>
		<div class="row" disabled id="<?php echo $row['username'] . $row['time']; ?>">
			<div class="cell"><?php echo $row['username']; ?></div>
			<div class="cell"><?php echo $row['time']; ?></div>
			<div class="cell"><?php echo $row['comment']; ?></div>			
			<div class="cell">
				<?php if($row['username'] == $username){ ?>
					<button class="<?php echo $row['username'] . $row['time']; ?>" id="<?php echo 'deletecomment.php?username=\'' . $row['username'] . '\'' . '&time=\'' . $row['time'] . '\'' ; ?>" onclick="deleteComment(this); return false;">Delete</button>
				<?php } ?>
			</div>
		</div>
	<?php
	}
	
	mysql_close($con);
?>
