<?php

require_once('config.php');
?>
<?php

if (connectDB()) {
    $IDTask = $_GET['IDTask'];
    $CommentQueryText = "SELECT * FROM comment WHERE IDTask=" . $IDTask . ";";
    $CommentQuery = mysql_query($CommentQueryText);
    
    header("Content-type: text/xml");
    $xml = new SimpleXMLElement("<xml/>");
    echo'<br/>Comment ('.mysql_num_rows($CommentQuery) .') :<br/>';
    echo'<div class="komentar" id="isikomentar">';
    echo'<hr>';
    while ($result = mysql_fetch_array($CommentQuery)) {
        echo '<div class="commentyeah">';
        echo'<div class="commentcontent">' . $result[3] . '</div>';
        $getCommentUserQuery = "SELECT * FROM user WHERE username='" . $result[2] . "';";
        $getCommentUser = mysql_query($getCommentUserQuery);
        $usercommentresult = mysql_fetch_array($getCommentUser);
        echo ('<div class="commentinfo"> <img width=30px height=30px src="' . $usercommentresult[5] . '" /> <a href="profile.php?user=' . $result[2] . '">' . $result[2] . '</a> at ' . $result[4] . '');
        if ($result[2] == $_COOKIE['UserLogin']) {
            echo'<br/><input type="button" class="remove" onclick="removeComment(' . $result[0] . ')" value="remove">';
        }
        echo'</div></div><hr>';
    }
    echo'</div><br/></div>';
}
?>