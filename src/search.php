<?php include 'header.php';?>

<div id="main">
	<div id="konten">
		<?php
		$q = $_GET['q'];
		$o = $_GET['o'];
		?>
		<div class='atas-search'></div>
		<div class='tengah'>
			<div id='search-judul'>SEARCH RESULT</div>
			<div id='search-keyword'>Keyword: <?php echo $q;?></div>
			<div class='line-konten'></div>
		<?php
		if ((strcmp($o, "All") == 0) || (strcmp($o, "User") == 0)) {
			$qres = mysqli_query($con, "SELECT * FROM members WHERE username LIKE '%$q%' OR email LIKE '%$q%' OR fullname LIKE '%$q%' OR about LIKE '%$q%' LIMIT 0, 10");
			$count = mysqli_num_rows($qres);
			echo "<span id='searchtype'>[User]</span><br />";
			if ($count == 0) {
				echo "<div id='message'>No results found</div>";
			} else {
				while ($row=mysqli_fetch_array($qres)) {
		?>
			<div class='line-konten'></div>
			<div class='judul'>
				<p class='alignleft'><a href="profile.php?id=<?php echo $row['id']?>"><?php echo $row['username']?></a></p>
				<p class='alignright'><img class='search-img' align='middle' src='<?php echo $row['avatar']?>' alt='avatar' width='150'/></p>
				<div style='clear: both;'></div>
			</div>
		<?php
				}
			}
		}

		if ((strcmp($o,"All") == 0) || (strcmp($o,"Category") == 0)) {
			$qres = mysqli_query($con, "SELECT * FROM categories WHERE name LIKE '%$q%' LIMIT 0, 10");
			$count = mysqli_num_rows($qres);
			echo "<span id='searchtype'>[Category]</span><br />";
			if ($count == 0) {
				echo "<div id='message'>No ResultsFound</div>";
			} else {
				while ($row = mysqli_fetch_array($qres)) {
		?>
			<div class='line-konten'></div>
			<div class='judul'>
				<?php echo $row['name'];?>
			</div>
			<div class='detail'>
				<?php
				$cat_id = $row['id'];
				$tasks = mysqli_query($con, "SELECT * FROM `tasks` WHERE category=$cat_id");
				echo "<ol>";
				while ($task = mysqli_fetch_array($tasks)) {
					echo "<li><a href='rinciantugas.php?id=".$task['id']."'>".$task['name']."</a></li>";
				}
				echo "</ol>";
				?>
			</div>
		<?php
				}
			}
		}

		if (strcmp($o,"All") == 0 || (strcmp($o,"Content") == 0)) {
			mysqli_query($con, "CREATE VIEW task_tags AS SELECT tasks.*, tags.name as tag FROM tasks, tags WHERE tasks.id = tags.tagged");
			$qres = mysqli_query($con, "SELECT DISTINCT id, name, creator, timestamp, deadline, category FROM task_tags WHERE name LIKE '%$q%' LIMIT 0, 10");
			$count = mysqli_num_rows($qres);
			echo "<span id='searchtype'>[Content]</span><br />";
			if ($count == 0) {
				echo "<div id='message'>No results found</div>";
			} else {
				while ($row=mysqli_fetch_array($qres)) {
		?>
			<div class='line-konten'></div>
			<div class='judul'>
				<a href="rinciantugas.php?id=<?php echo $row['id'];?>"><?php echo $row['name']?></a>
			</div>
			<div class='detail'>
				<div>
					Tags : 
					<?php
					$task_id = $row['id'];
					$tags = mysqli_query($con, "SELECT name FROM tags WHERE tagged=$task_id");
					$tag = mysqli_fetch_array($tags);
					echo $tag['name'];
					while ($tag=mysqli_fetch_array($tags)) {
						echo ", ".$tag['name'];
					}
					?>
				</div>
				<div class='dkonten-clear'>
				</div>
			</div>
		<?php
				}
			}
			mysqli_query($con, "DROP VIEW task_tags");
		}
		?>
		</div>
		<div class='bawah-search'></div>
	</div>
</div>

<?php include 'footer.php';?>