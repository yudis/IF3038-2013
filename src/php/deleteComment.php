<?php
require('init_function.php');
session_start();

$con = getConnection();
$comment = $_GET['commentid'];
$taskid = $_GET['taskid'];

$con = getConnection();
$query = "DELETE FROM comment WHERE commentid=$comment";
mysqli_query($con,$query);

$query = "SELECT * FROM comment WHERE taskid = $taskid";
$result = mysqli_query($con,$query);

echo "<p><b>".getNComment($taskid)."</b></p>";
echo "<div id=\"comment-list\">";
                    
while($row = mysqli_fetch_array($result)){
	echo " <div id=\"comment\">";
	echo " 	<div id=\"user-info\">";
	echo " 		<div id=\"left-comment-body\">";
	echo " 			<img src=\"../avatar/".getAvatar($row['username'])."\" width=\"50px\" height=\"50px\"/>";
	echo " 		</div>";
	echo " 		<div id=\"right-comment-body\">";
	echo " 			<b id=\"komentator\">".$row['username']."</b>";
	echo " 			<br>";
	echo " 			<b id=\"post-date\">Post at ".$row['createdate']."</b>";
	echo " 		</div>";
	echo " 	</div>";
	echo " 	<div id=\"comment-box\">";
	echo " 		<p>";
	echo $row['message'];
	echo " 		</p>";
	echo " 	</div>";
	echo " </div>";
}
echo "</div>";
?>