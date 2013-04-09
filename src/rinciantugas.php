<?php
include 'header.php';

$id_task= $_GET['id'];

// Get task
$result1=mysqli_query($con,"SELECT * FROM `tasks` WHERE id=$id_task");
$task = mysqli_fetch_array($result1);
$id_creator = $task['creator'];

// Get creator
$result2=mysqli_query($con,"SELECT * FROM `members` WHERE id=$id_creator");
$creator = mysqli_fetch_array($result2);

// Get tags
$result5=mysqli_query($con,"SELECT * FROM `tags` WHERE tagged=$id_task");
$count_tag = 0;
while ($tagged = mysqli_fetch_array($result5)) {
	$tag[$count_tag] = $tagged['name'];
	$count_tag++;
}

// Get attachments
$result6=mysqli_query($con,"SELECT * FROM `attachments` WHERE task=$id_task");
$count_attachment = 0;
while ($attachment[$count_attachment] = mysqli_fetch_array($result6)) {
	$count_attachment++;
}

// Get comments
$result7=mysqli_query($con,"SELECT * FROM `comments` WHERE task=$id_task ORDER BY timestamp DESC");
$count_comment = 0;
while ($commented = mysqli_fetch_array($result7)) {
	$comment[$count_comment] = $commented;
	$id_commenter = $commented['member'];
	$result8=mysqli_query($con,"SELECT * FROM members WHERE id=$id_commenter");
	$commenter[$count_comment] = mysqli_fetch_array($result8);
	$count_comment++;
}

$id_user = $_SESSION['id'];
$res = mysqli_query($con, "SELECT * 
						FROM assignees 
						WHERE member=$id_user 
						AND task=$id_task");
$assignee = mysqli_fetch_array($res);
?>
<!-- created by Enjella-->
<div id="main">
	<div id="konten">
		<div class="atas">
		</div>
		<div class="tengah">
			<div class="judul">
				<?php echo $task['name'];?>
			</div>
			<div class="isi">
			</div>
            
			<div class="detail">
				<div class="byon">
					Posted by <strong><span class="by"><?php echo $creator['username'];?></span></strong> on <strong><?php echo $task['timestamp'];?></strong>
				</div>
				<div class="byon" id="<?php echo $id_task;?>">
					Status : <strong><?php if ($assignee['finished'] == 1) echo 'Selesai'; else echo 'Belum selesai';?></strong><br />
					<input name="YourChoice" type="checkbox" value="selesai" <?php if($assignee['finished']==1) echo "checked"; ?> onclick="change_status('<?php echo $id_task;?>',<?php echo $assignee['finished'];?>,<?php echo $id_task;?>)"> Selesai
				</div>
				<div id="done">
					<br />
					<div class="byon">
						Deadline : <strong><?php echo $task['deadline'];?></strong>
					</div>
					<br />
					<div class="byon">
						Assignee : <strong>
						<?php
						unset($assignee);
						// Get assignee
						$result3=mysqli_query($con,"SELECT * FROM `assignees` WHERE task=$id_task");
						$count_assignee = 0;
						while ($assigned = mysqli_fetch_array($result3)) {
							$id_assignee = $assigned['member'];
							$result4=mysqli_query($con,"SELECT * FROM `members` WHERE id=$id_assignee");
							$assignee[$count_assignee] = mysqli_fetch_array($result4);
							$count_assignee++;
						}

						for ($i = 0; $i < $count_assignee; $i++) {
							$current=$assignee[$i];
							echo $current['username'];
							if ($i < $count_assignee - 1) echo ", ";
						}
						?>
						</strong>
					</div>
					<br />
	                <div class="byon">
						Tag : <strong>
						<?php
						for ($i = 0; $i < $count_tag; $i++) {
							echo $tag[$i];
							if ($i < $count_tag - 1) echo ",";
						}
						?>
						</strong>
					</div>
					<br />
					<?php
					if ($task['creator'] == $_SESSION['id']) {
					?>
					<div class="count"><input type="button" name="edit" onclick="edit_task()" value="Edit"/></div>
					<?php
					}
					?>
				</div>
				<form id="edit" action="edittask.php" method="post">
					<script type="text/javascript" src="mainpage.js"></script>
					<div class="byon">
						<?php
						$partdead = array();
						$partdead = explode(" ", $task['deadline']);
						?>
						<div id="left">
							Deadline : <input type="text" name="inputdeadline" id="form-tgl" value="<?php echo $partdead[0];?>"/>
						</div>
						<div id="caldad">
							<div id="calendar"></div>
							<a href="javascript:showcal(3,2013);void(0);"><img src="images/cal.gif" alt="Calendar" /></a>
						</div>
						Jam: 
						<?php
						$parthour = array();
						$parthour = explode(":", $partdead[1]);
						?>
						<select name="hour">
							<?php
							for ($i = 0; $i < 24; $i++) {
								echo "<option value='".$i."'";
								if ($i == $parthour[0]) {
									echo " selected";
								}
								echo ">".$i."</option>";
							}
							?>
						</select>
						<select name="minute">
							<?php
							for ($i = 0; $i < 60; $i++) {
								echo "<option value='".$i."'";
								if ($i == $parthour[1]) {
									echo " selected";
								}
								echo ">".$i."</option>";
							} 
							?>
						</select>
						<select name="second">
							<?php
							for ($i = 0; $i < 60; $i++) {
								echo "<option value='".$i."'";
								if ($i == $parthour[2]) {
									echo " selected";
								}
								echo ">".$i."</option>";
							} 
							?>
						</select>
					</div>
					<br />
					<div class="byon">
						Assignee : <input type="text" name="inputassignee"
						<?php
						echo " value='";
						for ($i = 0; $i < $count_assignee; $i++) {
							$current=$assignee[$i];
							echo $current['username'];
							if ($i < $count_assignee - 1) echo ", ";
						}
						echo "' ";
						?>
						list="user"/>
						<datalist id ="user" />
						<option value = "enjella" />
						<option value = "kevin" />
						<option value = "vincentius" />
						</datalist>
					</div>
					
	                <div class="byon">
						Tag : <input type="text" name="inputtag"
						<?php
						echo " value='";
						for ($i = 0; $i < $count_tag; $i++) {
							echo $tag[$i];
							if ($i < $count_tag - 1) echo ",";
						}
						echo "' ";
						?>
						autocomplete="on"/>
					</div>
					<input type="hidden" name="id" value="<?php echo $id_task;?>" />
                	<div class="count"><input type="submit" name="submit" value="Submit"/></div>
				</form>
                
			</div><br /><br /><br />
			<form action="deletetask.php" method="post">
				<input type="hidden" name="deltask" value="<?php echo $id_task?>" />
				<input type="submit" name="submit" value="Delete" />
			</form>
			<div class="videomode" align="center"> <br> Attachment :
				<?php
				for ($i = 0; $i < $count_attachment; $i++) {
					$current = $attachment[$i];
					if (strcmp($current['filetype'],"file") == 0) {
						$pos = strrpos($current['path'],"/");
						if ($pos === false) {
							$filename = $current['path'];
						} else {
							$filename = substr($current['path'],$pos + 1);
						}
				?>
				<a href="<?php echo $current['path'];?>"><?php echo $filename;?></a><br />
				<?php
					} else if (strcmp($current['filetype'],"image") == 0) {
				?>
				<img src="<?php echo $current['path'];?>" /><br />
				<?php	
					} else if (strcmp($current['filetype'],"video") == 0) {
				?>
				<video width="320" height="240" controls src="<?php echo $current['path'];?>"></video><br />
				<?php	
					}
				}
				?>
				<!--<a href="images/Up.png" target="images/Up.png">Up.png</a><br><img src="images/gajah.png"></img><br><video width="320" height="240" controls src="images/movie.ogg"></video>-->
				</div>
			<div class="komen">
				<div class="komenjudul">Comments</div>
				<div id="konten-commenter">
					<strong><span id="jmlkomen"><?php echo $count_comment;?></span></strong> comments
				</div>
				<div class="line-konten"></div>
				<div id="komen-tulis"><strong>Tulis Komentar</strong></div>
					<input type="hidden" id="id" name="id" value="<?php echo $_SESSION['id'];?>">
					<input type="hidden" id="task" name="task" value="<?php echo $id_task;?>">
					<textarea id="komentar" name="komentar" rows="3" cols="60" id="form-komen"></textarea>
					<div class="komen-submit"><input type="button" name="submit" value="Submit" onclick="comment();"/></div>
				<div class="line-konten"></div>
				<div id="lkomen">
					<?php
					if ($count_comment > 10) $count_comment = 10;
					for ($i = 0; $i < $count_comment; $i++) {
						$current1=$comment[$i];
						$current2=$commenter[$i];
						echo '<div class="komen-avatar"><img src="'.$current2['avatar'].'" height="24"/></div>';
						echo '<div class="komen-nama">'.$current2['fullname'].'</div>';
						echo '<div class="komen-tgl">'.$current1['timestamp'].'</div>';
						echo '<div class="komen-isi">'.$current1['comment'].'</div>';
						if ($_SESSION['id'] == $current2['id']) {
							echo '<input type="button" name="delete" value="Delete" onclick="delete_comment('.$id_task.",".$current1['id'].')"/>';
						}
						echo '<div class="line-konten"></div>';
					}
					echo '<input type="button" value="More" onclick="comment_more('.$task['id'].',10);this.style.display=\'none\'">';
					?>
				</div>
				
					
				
			</div>
		</div>
		<div class="bawah">
		</div>
	</div>
</div>

<?php include 'footer.php';?>