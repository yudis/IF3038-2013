<?php
include 'session.php';
include 'database.php';

// Connect to server and select databse.
$con=mysqli_connect($host,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_error();
}

$member = $_POST['id'];
$task = $_POST['task'];
date_default_timezone_set('Asia/Jakarta');
$timestamp = date('Y-m-d H:i:s', time());
$komentar = $_POST['komentar'];

mysqli_query($con, "INSERT INTO `comments` (member,task,timestamp,comment) 
				VALUES ($member, $task, '$timestamp', '$komentar')");

$result7=mysqli_query($con,"SELECT * FROM `comments` WHERE task=$task");
$count_comment = 0;
while ($commented = mysqli_fetch_array($result7)) {
	$comment[$count_comment] = $commented;
	$id_commenter = $commented['member'];
	$result8=mysqli_query($con,"SELECT * FROM members WHERE id=$id_commenter");
	$commenter[$count_comment] = mysqli_fetch_array($result8);
	$count_comment++;
}
for ($i = 0; $i < $count_comment; $i++) {
	$current1=$comment[$i];
	$current2=$commenter[$i];
	echo '<div class="komen-nama">'.$current2['fullname'].'</div>';
	echo '<div class="komen-tgl">'.$current1['timestamp'].'</div>';
	echo '<div class="komen-isi">'.$current1['comment'].'</div>';
	echo '<div class="line-konten"></div>';
}

mysqli_close($con);
// header("location:rinciantugas.php?id=".$task);

?>