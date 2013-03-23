<?php
require('init_function.php');
session_start();

$con = getConnection();

$ListAssignee = $_GET['tag'];
$taskid = $_GET['taskid'];

$con = getConnection();

$query = "DELETE FROM assignee WHERE taskid = $taskid";
mysqli_query($con,$query);

$useractive = $_SESSION['userlistapp'];
$query = "INSERT INTO `assignee` (`username`, `taskid`) VALUES ('$useractive', $taskid)";
mysqli_query($con,$query);
$Assignee = explode(',', $ListAssignee);
for($i = 0; $i < count($Assignee) ; $i++){
	$query = "INSERT INTO `assignee` (`username`, `taskid`) VALUES ('$Assignee[$i]', $taskid)";
		mysqli_query($con,$query);
}

$query = "SELECT username FROM assignee WHERE taskid = $taskid";
$result = mysqli_query($con,$query);
echo "Shared with : <i>";
while($row = mysqli_fetch_array($result)){
	echo "<a href=\"profile.php?username=".$row['username']."\"><u>".$row['username']."</u></a> ";
}
echo "</i>";

?>