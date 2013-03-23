<?php
require('init_function.php');
session_start();

$con = getConnection();

$listtag = $_GET['tag'];
$taskid = $_GET['taskid'];

$con = getConnection();

$query ="DELETE FROM task_tag Where taskid=$taskid";
mysqli_query($con,$query);
$Tag = explode(',', $listtag);
	for($i = 0; $i < count($Tag) ; $i++){
			$tagid = getTagId($Tag[$i]);
			$query = "INSERT INTO `task_tag` (`taskid`, `tagid`) VALUES ($taskid, ".$tagid.");";
			mysqli_query($con,$query);
	}

echo "Tag : <i>";
$result3 = mysqli_query($con,"SELECT tagid FROM task_tag WHERE taskid = '".$taskid."'");
while($row3 = mysqli_fetch_array($result3))
{
	$tagname = getTagname($row3['tagid']);
	echo "<u>".$tagname."</u> ";
}
echo "</i>";								
?>