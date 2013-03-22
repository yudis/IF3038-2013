<?php
require_once('config.php');
?>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
    </head>
    <body>
        <?php
        include 'header.php';

        $IDTask = $_GET['IDTask'];


        $TaskQuery = "SELECT * FROM task WHERE IDTask=" . $IDTask . ";";
        $TaskResult = mysql_query($TaskQuery);
        $result = mysql_fetch_array($TaskResult);

        $TaskName = $result[2];
        $deadline = $result[4];

        $AttachmentQueryText = "SELECT * FROM attachment WHERE IDTask=" . $IDTask . ";";
        $AttachmentQuery = mysql_query($AttachmentQueryText);

        $AssigneeQueryText = "SELECT * FROM assignment WHERE IDTask=" . $IDTask . ";";
        $AssigneeQuery = mysql_query($AssigneeQueryText);

        $TagQueryText = "SELECT * FROM tasktag,tag WHERE IDTask=" . $IDTask . " AND tasktag.IDTag=tag.IDTag";
        $TagQuery = mysql_query($TagQueryText);

        $CommentQueryText = "SELECT * FROM comment WHERE IDTask=" . $IDTask . ";";
        $CommentQuery = mysql_query($CommentQueryText);
        ?>
        <div id="category">      
            <div class = "kategori"><a title="Go to Dashboard" href="dashboard.php">Back to Dashboard</a></div>
        </div>
        <div id ="listtugas" class="list">
            <div class="tugasyeah" id="rincitugas">
                Name: <?php echo$TaskName ?> <br/>
                Attachment: 
                <div class="attachment" >
                    <?php
                    $x = 1;
                    while ($result = mysql_fetch_array($AttachmentQuery)) {
                        $resultcheck = explode(".", $result[2]);
                        $arraysize = count($resultcheck);
                        echo('attachment ' . $x . ' :<br/>');
                        $x++;
                        if ($resultcheck[$arraysize - 1] == "jpg" || $resultcheck[$arraysize - 1] == "png" || $resultcheck[$arraysize - 1] == "jpeg") {
                            echo'<a href="' . $result[2] . '"><img width = "150px" height = "150px" src="' . $result[2] . '"></a><br/>';
                        } else if ($resultcheck[$arraysize - 1] == "ogg") {
                            echo "<video width=\"320\" height=\"240\" controls autoplay><source src=" . $result[2] . " type=\"video/" . $resultcheck[$arraysize - 1] . "\"></video><br>";
                        } else {
                            echo'<a href="' . $result[2] . '">' . $result[2] . '</a><br/>';
                        }
                        echo '<br/>';
                    }
                    ?>
                </div><br/>
                Deadline: <?php echo$deadline ?><br/>

                Assignee: 
                <?php
                $check = false;
                while ($result = mysql_fetch_array($AssigneeQuery)) {
                    echo'<a href="profile.php?user=' . $result[1] . '" class="asignee">' . $result[1] . '</a>, ';
                }
                ?>
                <br/>
                Tag: 
                <?php
                $check = false;
                while ($result = mysql_fetch_array($TagQuery)) {
                    echo'<a href="" class="tag">' . $result[4] . '</a>, ';
                }
                ?>
                <br/>
                <br/>Comment (<?php  echo mysql_num_rows($CommentQuery)?>) :<br/>
                <div class="komentar" id="isikomentar">
                    <hr>
                    <?php
                        while ($result = mysql_fetch_array($CommentQuery)){
                            echo '<div class="commentyeah">';
                            echo'<div class="commentcontent">'.$result[3].'</div>';
                            $getCommentUserQuery = "SELECT * FROM user WHERE username='".$result[2]."';";
                            $getCommentUser = mysql_query($getCommentUserQuery);
                            $usercommentresult= mysql_fetch_array($getCommentUser);
                            echo ('<div class="commentinfo"> <img width=30px height=30px src="'.$usercommentresult[5].'" /> <a href="profile.php?user=' . $result[2] . '">'.$result[2].'</a> at '.$result[4].'');
                            if($result[2]==$_COOKIE['UserLogin']){
                                echo'<br/><input type="button" class="remove" onclick="removeComment('.$result[0].')" value="remove">';
                            }
                            echo'</div></div><hr>';
                        }
                    ?>
                </div><br/>
                <form>
                    <textarea id="addCommentText"></textarea>
                    <input type="button" name="submit" onclick="addComment('<?php echo $_COOKIE['UserLogin']; ?>','<?php echo $IDTask; ?>')" value="submit">
                </form>
            <br/><br/>
            <a onclick="showEdit();" class="button">edit</a><br/>
            </div>
        <div class="tugass" id ="edittugas">
            <form>
                Name: Nama Task<br/>
                Attachment: <div class="attachment"><input id="upload" type="file"></div><br/>
                Deadline: <input type="date"><br/>
                Assignee: <div class="assignee"><input type="text" list="assignee"></div><br/>
                Tag: <div class="tag"> <input type="text"></div> <br/>
                Comment: <br/>
                <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
            </form> <br/>
            <a onclick="showRinci()" class="button">save</a><br/>
        </div>
        </div>
    </body>
    <script type="text/javascript" src="script.js"></script>
 </html>