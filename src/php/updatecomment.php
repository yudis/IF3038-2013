<?php
require('init_function.php');
session_start();

$username = $_SESSION['userlistapp'];
$con = getConnection();
$comment = $_GET['comment'];
$taskid = $_GET['taskid'];
$commentid = getNextCommentId();
$createdate = date('Y:m:d H:i:s');

echo $comment;

?>