<?php
require('init_function.php');
session_start();

$username = $_SESSION['userlistapp'];
$con = getConnection();
$comment = $_GET['comment'];
$taskid = $_GET['taskid'];
$commentid = getNextCommentId();
$createdate = date('Y:m:d H:i:s');

$con = getConnection();
$query = "INSERT INTO comment VALUES ($commentid,'$createdate','$comment','$username',$taskid)";
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
	echo " 		<div id=\"delete-comment\">";
		if($row['username'] == $_SESSION['userlistapp']){
			echo " 			<a href=\"#\" onClick=\"deleteComment(".$row['commentid'].",$taskid)\"><i>Delete Comment</i></a>";
		}
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