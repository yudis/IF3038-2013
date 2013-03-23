<?php
require_once('config.php');
?>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <link rel="stylesheet" type="text/css" href="autocomplete.css" media="screen" />
    </head>
    <body onload="loadcomment();
            loadpagevar()">
              <?php
              include 'header.php';

              $IDTask = $_GET['IDTask'];

              if (connectDB()) {
                  $TaskQuery = "SELECT * FROM task WHERE IDTask=" . $IDTask . ";";
                  $TaskResult = mysql_query($TaskQuery);
                  $result = mysql_fetch_array($TaskResult);

                  $TaskName = $result[2];
                  $deadline = $result[4];
                  $Status = $result[3];

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
                <div>
                    <input value="<?php echo$IDTask; ?>" hidden="true" id="HiddenIDTask">
                </div>
            </div>
            <div id ="listtugas" class="list">
                <div class="tugasyeah" id="rincitugas">
                    Name: <?php echo$TaskName; ?> <br/>
                    Status : 
                    <div id="checkstatus">
                        <?php
                        if ($Status === "done") {
                            echo' DONE <input type="checkbox" id="checkboxstatus" checked onclick="changestatus(' . $IDTask . ')" >';
                        } else {
                            echo'DONE <input type="checkbox" id="checkboxstatus" onclick="changestatus(' . $IDTask . ')" >';
                        }
                        ?>
                    </div>
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
                                echo'<a href="' . $result[2] . '" target="_blank">' . $result[2] . '</a><br/>';
                            }
                            echo '<br/>';
                        }
                        ?>
                    </div><br/>
                    Deadline: <?php echo$deadline ?><br/>

                    Assignee: 
                    <?php
                    while ($result = mysql_fetch_array($AssigneeQuery)) {
                        if ($result[1] != $_COOKIE['UserLogin']) {
                            echo'<a href="profile.php?user=' . $result[1] . '" class="asignee">' . $result[1] . '</a>, ';
                        }
                    }
                    ?>
                    <br/>
                    Tag: 
                    <?php
                    while ($result = mysql_fetch_array($TagQuery)) {
                        echo'<a href="" class="tag">' . $result[4] . '</a>, ';
                    }
                    ?>
                    <br/>
                    <div id="komentaryey"></div>
                    <form>
                        AddComment <br/>
                        <input type="text" id="addCommentText"  >
                        <input type="button" name="submit" onclick="addComment('<?php echo $_COOKIE['UserLogin']; ?>', '<?php echo $IDTask; ?>')" value="submit">
                    </form>
                    <br/><br/>
                    <a onclick="showEdit();" class="button">edit</a><br/>
                </div>
                <div class="tugasyeahh" id ="edittugas">
                    EDIT TASK<br/><br/>
                    <form>
                        Deadline: <input type="date" id="editdeadline"><br/>
                        Assignee: <div class="assignee">
                            <div id="ListEditAssignee">
                                <?php
                                $AssigneeQuery = mysql_query($AssigneeQueryText);
                                while ($result = mysql_fetch_array($AssigneeQuery)) {
                                    if ($result[1] != $_COOKIE['UserLogin']) {
                                        echo'<a href="profile.php?user=' . $result[1] . '" class="asignee">' . $result[1] . '</a>  <img src="img/salah.png" alt="" onclick="deleteassignee(' . $result[0] . ',' . $IDTask . ')" /><br/> ';
                                    }
                                }
                                echo'<br/>';
                                ?>
                            </div>
                            <input type="text" id="addnewassignee" list="assignee" onkeyup="multiAutocomp(this, 'assignee.php', 'edittugas')"></div><br/>
                        Tag: 
                        <div id="ListEditTag">
                            <?php
                            $TagQuery = mysql_query($TagQueryText);
                            while ($result = mysql_fetch_array($TagQuery)) {
                                echo'<a href="" class="asignee">' . $result[4] . '</a> <img src="img/salah.png" alt="" onclick="deletetag(' . $result[0] . ',' . $IDTask . ')" /><br/> ';
                            }
                            echo'<br/>';
                            ?>
                        </div>
                        <div class="tag"> <input id="edittag" type="text"></div> <br/>
                    </form> <br/>
                    <a onclick="editTask('<?php echo$IDTask; ?>')" class="button">OK</a><br/><br/><br/>
                    <?php
                    $QueryNewText = "SELECT * FROM task WHERE IDTask=" . $IDTask . ";";
                    $QueryNew = mysql_query($QueryNewText);
                    $resulta = mysql_fetch_array($QueryNew);
                    if ($resulta['Creator'] == $_COOKIE['UserLogin']) {
                        ?>
                        <a onclick="deleteTaskYey('<?php echo$IDTask; ?>')" class="button">DELETE TASK</a>
                    <?php } ?>
                </div>
            </div>
        </body>
        <script type="text/javascript" src="script.js"></script>
    <?php } ?>
</html>