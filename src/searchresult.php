<html>
	<head>
		<title>Shared To Do List - Dashboard</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="favicon.ico">		
		<script type="text/javascript" src="validation.js"></script>
		<script>checkLogged()</script>
	</head>
	<body>
		<div id="navsearch">
			<script>checkLogged()</script>
		</div>
		<div class="clearall container">
			<h2>Search Result</h2>
		</div>
<?php
	$term = (isset($_GET['term'])) ? $_GET['term'] : "";
	$type = (isset($_GET['type'])) ? $_GET['type'] : "";
	$offset = (isset($_GET['offset'])) ? $_GET['offset'] : 0;
	$limit = (isset($_GET['limit'])) ? $_GET['limit'] : 10;
	
	require 'connectDB.php';
	
	switch($type){
		case "semua":
			?>
			<div class="clearall container">
				<h2>Username</h2>
				<div class="row">
					<div class="cell th">Username</div>
					<div class="cell th centered">Full name</div>
					<div class="cell th centered">Avatar</div>
				</div>
			<?php			
			$query = "select * from login where username like '%" . $term . "%' limit " . $limit . " offset " . $offset;
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){ ?>
				<div class="row" id="<?php echo $row['username']; ?>">
					<div class="cell"><?php echo $row['username']; ?></div>
					<div class="cell centered"><?php echo $row['fullname']; ?></div>
					<div class="cell centered"><img src="<?php echo "images/".$row['photo']; ?>" ></div>
				</div>
			<?php }
			echo '<br>';
			
			$query = "select count(*) as count from login where username like '%" . $term . "%'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			$pageamount = ceil($row['count'] / 10);			
			
			if($pageamount > 1){
				for($i = 0; $i < $pageamount; $i++){
					$start = $i * 10;
					if($i + 1 == $pageamount){
						$limit = $row['count'] % 10;
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
					else{
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
				}
			}
			?> </div>
			
			<div class="clearall container">
				<h2>Category</h2>			
				<div class="row">
					<div class="cell th">Category</div>
					<div class="cell th centered">Users</div>
				</div>
			<?php
			$query = "select * from category where category like '%" . $term . "%' limit " . $limit . " offset " . $offset;
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){ ?>
				<div class="row" id="<?php echo $row['category']; ?>">
					<div class="cell"><?php echo $row['category']; ?></div>
					<div class="cell centered"><?php echo $row['users']; ?></div>
				</div>
				
			<?php }
			echo '<br>';
			
			$query = "select count(*) as count from category where category like '%" . $term . "%'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			$pageamount = ceil($row['count'] / 10);			
			
			if($pageamount > 1){
				for($i = 0; $i < $pageamount; $i++){
					$start = $i * 10;
					if($i + 1 == $pageamount){
						$limit = $row['count'] % 10;
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
					else{
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
				}
			}			
			?> </div>
			
			<div class="clearall container">
				<h2>Task</h2>			
				<div class="row">
					<div class="cell th">Task Name</div>
					<div class="cell th centered">Deadline</div>
					<div class="cell th centered">Tags</div>			
					<div class="cell th centered">Status</div>
					<div class="cell th centered">Ubah Status</div>
				</div>			
			<?php
			$query = "select * from task where taskname like '%" . $term . "%' or tags like '%" . $term . "%' limit " . $limit . " offset " . $offset;
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
				</div>
				
			<?php }
			echo '<br>';
			
			$query = "select count(*) as count from task where taskname like '%" . $term . "%' or tags like '%" . $term . "%'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			$pageamount = ceil($row['count'] / 10);			
			
			if($pageamount > 1){
				for($i = 0; $i < $pageamount; $i++){
					$start = $i * 10;
					if($i + 1 == $pageamount){
						$limit = $row['count'] % 10;
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
					else{
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
				}
			}
			?> </div> <?php
			
			break;
		case "username":
			?>
			<div class="clearall container">
				<h2>Username</h2>
				<div class="row">
					<div class="cell th">Username</div>
					<div class="cell th centered">Full name</div>
					<div class="cell th centered">Avatar</div>
				</div>
			<?php			
			$query = "select * from login where username like '%" . $term . "%' limit " . $limit . " offset " . $offset;
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){ ?>
				<div class="row" id="<?php echo $row['username']; ?>">
					<div class="cell"><a href="profile/?u=<?= $row['username']; ?>"><?php echo $row['username']; ?></a></div>
					<div class="cell centered"><?php echo $row['fullname']; ?></div>
					<div class="cell centered"><img src="<?php echo "images/".$row['photo']; ?>" ></div>
				</div>
			<?php }
			echo '<br>';
			
			$query = "select count(*) as count from login where username like '%" . $term . "%'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			$pageamount = ceil($row['count'] / 10);			
			
			if($pageamount > 1){
				for($i = 0; $i < $pageamount; $i++){
					$start = $i * 10;
					if($i + 1 == $pageamount){
						$limit = $row['count'] % 10;
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
					else{
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
				}
			}
			?> </div> <?php
			
			break;
		case "category":
			?>
			<div class="clearall container">
				<h2>Category</h2>			
				<div class="row">
					<div class="cell th">Category</div>
					<div class="cell th centered">Users</div>
				</div>
			<?php
			$query = "select * from category where category like '%" . $term . "%' limit " . $limit . " offset " . $offset;
			$result = mysql_query($query);
			while($row = mysql_fetch_array($result)){ ?>
				<div class="row" id="<?php echo $row['category']; ?>">
					<div class="cell"><?php echo $row['category']; ?></div>
					<div class="cell centered"><?php echo $row['users']; ?></div>
				</div>
				
			<?php }
			echo '<br>';
			
			$query = "select count(*) as count from category where category like '%" . $term . "%'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			$pageamount = ceil($row['count'] / 10);			
			
			if($pageamount > 1){
				for($i = 0; $i < $pageamount; $i++){
					$start = $i * 10;
					if($i + 1 == $pageamount){
						$limit = $row['count'] % 10;
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
					else{
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
				}
			}			
			?> </div> <?php
			
			break;
		case "task":
			?>
			<div class="clearall container">
				<h2>Task</h2>			
				<div class="row">
					<div class="cell th">Task Name</div>
					<div class="cell th centered">Deadline</div>
					<div class="cell th centered">Tags</div>			
					<div class="cell th centered">Status</div>
					<div class="cell th centered">Ubah Status</div>
				</div>			
			<?php
			$query = "select * from task where taskname like '%" . $term . "%' or tags like '%" . $term . "%' limit " . $limit . " offset " . $offset;
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
				</div>
				
			<?php }
			echo '<br>';
			
			$query = "select count(*) as count from task where taskname like '%" . $term . "%' or tags like '%" . $term . "%'";
			$result = mysql_query($query);
			$row = mysql_fetch_array($result);
			$pageamount = ceil($row['count'] / 10);			
			
			if($pageamount > 1){
				for($i = 0; $i < $pageamount; $i++){
					$start = $i * 10;
					if($i + 1 == $pageamount){
						$limit = $row['count'] % 10;
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
					else{
						echo "<a href='searchresult.php?term=$term&type=$type&offset=$start&limit=$limit'>$i</a>&nbsp;";
					}
				}
			}
			?> </div> <?php
			
			break;
	}
?>
	</body>
</html>