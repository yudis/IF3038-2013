<?php
include 'session.php';
include 'database.php';

// Connect to server and select databse.
$con=mysqli_connect($host,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_error();
}

$id = $_POST['id'];
$task = $_POST['task'];

mysqli_query($con, "DELETE FROM comments 
				WHERE id=$id");

$result7=mysqli_query($con,"SELECT * FROM `comments` WHERE task=$task ORDER BY timestamp DESC");
$count_comment = 0;
while ($commented = mysqli_fetch_array($result7)) {
	$comment[$count_comment] = $commented;
	$id_commenter = $commented['member'];
	$result8=mysqli_query($con,"SELECT * FROM members WHERE id=$id_commenter");
	$commenter[$count_comment] = mysqli_fetch_array($result8);
	$count_comment++;
}
if ($count_comment > 10) $count_comment = 10;
for ($i = 0; $i < $count_comment; $i++) {
	$current1=$comment[$i];
	$current2=$commenter[$i];
	echo '<div class="komen-avatar"><img src="'.$current2['avatar'].'" height="24"/></div>';
	echo '<div class="komen-nama">'.$current2['fullname'].'</div>';
	echo '<div class="komen-tgl">'.$current1['timestamp'].'</div>';
	echo '<div class="komen-isi">'.$current1['comment'].'</div>';
	if ($_SESSION['id'] == $current2['id']) {
		echo '<input type="button" name="delete" value="Delete" onclick="delete_comment('.$task.",".$current1['id'].')"/>';
	}
	echo '<div class="line-konten"></div>';
}
echo '<input type="button" value="More" onclick="comment_more('.$task['id'].',10);this.style.display=\'none\'">';

mysqli_close($con);
?>