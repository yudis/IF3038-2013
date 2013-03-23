<?php
include 'session.php';
include 'database.php';

// Connect to server and select database
$con = mysqli_connect($hostname,$username,$password,$db_name);
if (mysqli_connect_errno()) {
	echo "Failed to connect to MySQL: ".mysqli_connect_errno();
}

$id_task = $_POST['id'];

$deadlinedate = $_POST['inputdeadline'];
$deadlinehour = $_POST['hour'];
$deadlineminute = $_POST['minute'];
$deadlinesecond = $_POST['second'];
$deadlinestring = $deadlinedate." ".$deadlinehour.":".$deadlineminute.":".$deadlinesecond;

$assignee = $_POST['inputassignee'];
$assignees = array();
$assignees = explode(",",$assignee);
$count_assignee = count($assignees);

$tag = $_POST['inputtag'];
$tags = array();
$tags = explode(",",$tag);
$count_tag = count($tags);

// Update deadline
mysqli_query($con, "UPDATE tasks 
				SET deadline='$deadlinestring'
				WHERE id=$id_task");

// Update assignee
// Delete all assignees
mysqli_query($con, "DELETE FROM assignees
				WHERE task=$id_task");
// Insert assignees
for ($i = 0; $i < $count_assignee; $i++) {
	$current = $assignees[$i];
	$current = trim($current," ");
	// Get user id
	$check = mysqli_query($con, "SELECT * 
							FROM members 
							WHERE username='$current'");
	$user = mysqli_fetch_array($check);
	$id_user = $user['id'];
	echo $id_user."<br />";
	mysqli_query($con, "INSERT INTO assignees
					VALUES ($id_user,$id_task)");
}

// Update tag
// Delete all tags
mysqli_query($con, "DELETE FROM tags 
				WHERE tagged=$id_task");
// Insert tags
for ($i = 0; $i < $count_tag; $i++) {
	$current = $tags[$i];
	$current = trim($current," ");
	mysqli_query($con, "INSERT INTO tags 
					VALUES ('$current',$id_task)");
}

mysqli_close($con);
header("location:rinciantugas.php?id=".$id_task);
?>