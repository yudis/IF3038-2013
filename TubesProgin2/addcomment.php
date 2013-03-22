<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    $userID = $_GET["user"];
    $comment = $_GET["comment"];
    $taskID = $_GET["task"];

    $InsertCommentQuery = "INSERT INTO `comment` (`IDComment`, `IDTask`, `Username`, `Content`, `Timestamp`) 
        VALUES (NULL, '" . $taskID . "', '" . $userID . "', '" . $comment . "', CURRENT_TIMESTAMP);";
    $InsertComment = mysql_query($InsertCommentQuery);

    $CommentQueryText = "SELECT * FROM comment WHERE IDTask=" . $taskID." and Content='".$comment."';"; // " and Content=".$comment.";";
    $CommentQuery = mysql_query($CommentQueryText);
    while ($result=  mysql_fetch_array($CommentQuery)){
        $TimeStamp = $result[4];
        $commentID = $result[0];
    }
    echo '<div class="commentyeah">';
    echo'<div class="commentcontent">' . $comment . '</div>';
    $getCommentUserQuery = "SELECT * FROM user WHERE username='" . $userID . "';";
    $getCommentUser = mysql_query($getCommentUserQuery);
    $usercommentresult = mysql_fetch_array($getCommentUser);
    echo ('<div class="commentinfo"> <img width=30px height=30px src="' . $usercommentresult[5] . '" /> <a href="profile.php?user=' . $userID . '">' . $userID . '</a> at ' . $TimeStamp . '');
    if ($userID == $_COOKIE['UserLogin']) {
        echo'<br/><input type="button" class="remove" onclick="removeComment(' . $commentID . ')" value="remove">';
    }
    echo'</div></div><hr>';
}
?>
