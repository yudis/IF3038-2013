<?php

require_once('config.php');
?>
<?php

if (connectDB()) {
    $IDTask = $_GET['IDTask'];
    $CommentQueryText = "SELECT * FROM comment WHERE IDTask=" . $IDTask . ";";
    $CommentQuery = mysql_query($CommentQueryText);
    $numrows = mysql_num_rows($CommentQuery);

    $page = $_GET['page'];
    $pagecount = (int) (($numrows / 10));
    if ($numrows % 10 > 0) {
        $pagecount+=1;
    }

    echo'<br/>Comment (' . $numrows . ') :<br/>';
    echo'<div class="komentar" id="isikomentar">';
    echo'<hr>';
    $countPage = $page;
    if ($countPage > 1) {
        $countPage--;
        for ($x = 0; $x < 10; $x++) {
            $result = mysql_fetch_array($CommentQuery);
        }
    }

    $x = 0;
    while ($result = mysql_fetch_array($CommentQuery)) {
        if ($x < 10) {
            $x++;
            echo '<div class="commentyeah">';
            echo'<div class="commentcontent">' . $result[3] . '</div>';
            $getCommentUserQuery = "SELECT * FROM user WHERE username='" . $result[2] . "';";
            $getCommentUser = mysql_query($getCommentUserQuery);
            $usercommentresult = mysql_fetch_array($getCommentUser);
            $arrayTime = explode(" ", $result[4]);
            $arrayDate = explode("-", $arrayTime[0]);
            $arrayTimeHour = explode(":", $arrayTime[1]);
            $time = $arrayTimeHour[0].":".$arrayTimeHour[1]." - ".$arrayDate[2]."/".$arrayDate[1];
            echo ('<div class="commentinfo"> <img width=30px height=30px src="' . $usercommentresult[5] . '" /> <a href="profile.php?user=' . $result[2] . '">' . $result[2] . '</a> at ' . $time . '');
            if ($result[2] == $_COOKIE['UserLogin']) {
                echo'<br/><input type="button" class="remove" onclick="removeComment(' . $result[0] . ')" value="remove">';
            }
            echo'</div></div><hr>';
        }
    }
    echo'</div><br/>';
    echo'<div id="commentpage" >';
    $x = 1;
    for ($x = 1; $x <= $pagecount; $x++) {
        if ($x == $page) {
            echo'<a class="commentselected" onclick="setPage(' . $x . ')">[' . $x . ']</a>';
        } else {
            echo'<a onclick="setPage(' . $x . ')">[' . $x . ']</a>';
        }
    }
    echo'</div></div>';
}
?>