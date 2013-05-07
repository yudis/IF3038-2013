<?php

require_once("db.php");
session_start();

$code = $_GET['code'];

$query = "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username = '" . $_SESSION['username'] . "' AND utrelation.id_task = task.id_task AND task.id_task='" . $code . "';";
$result = ProginDB::getInstance()->query($query);

while ($row = mysqli_fetch_array($result)) {
    echo "<big><b>" . $row['taskName'] . "</b></big><br/>";
    echo "Attachment:";
    
    $pieces = explode('/', $row['deadline']);
    echo "Deadline: " . $pieces[0].'/'.$pieces[1].'/'.$pieces[2].' -- '.$pieces[3].':'.$pieces[4]."<br/>";

    echo "Assignee: ";
    $queryAssignee = "SELECT username FROM utrelation WHERE id_task='" . $row['taskID'] . "'";
    $resultAssignee = ProginDB::getInstance()->query($queryAssignee);
    while ($rowAssignee = mysqli_fetch_array($resultAssignee)) {
        echo "<a href='profile.php?userprofile=".$rowAssignee['username']."' class='assignee'>" . $rowAssignee['username'] . "</a>";
        echo ", ";
    }
    echo "<br/>";
    
    echo "<div class='attachment'>";

    $queryAttach = "SELECT path FROM tarelation JOIN attachment WHERE id_task='" . $code . "' AND tarelation.id_attachment = attachment.id_attachment;";
    $resultAttachment = ProginDB::getInstance()->query($queryAttach);
    while ($rowAttach = mysqli_fetch_array($resultAttachment)) {
        $attachExt = substr($rowAttach['path'], strripos($rowAttach['path'], ".") + 1);
        if ((substr_compare($attachExt, "jpg", 0) == 0) or (substr_compare($attachExt, "jpeg", 0) == 0) or
                (substr_compare($attachExt, "png", 0) == 0)) {   // if image
            echo "<img src='" . $rowAttach['path'] . "' height='100' width='100'";
        } else if (substr_compare($attachExt, "mp4", 0) == 0) {  // if video
            echo "<video height='240' width='320' controls";
            echo "<source src='" . $rowAttach['path'] . "' type='video/mp4'>";
            echo "Your browser does not support the video tag.";
            echo "</video>";
        } else {    // if any other file
            $attachName = substr($rowAttach['path'], strripos($rowAttach['path'], "\\") + 1);
            echo "<a href='" . $rowAttach['path'] . "'>" . $attachName . "</a>";
        }
        echo "<br/><br/>";
    }
    echo "</div>";
    
    echo "Komentar: <br/>";



    //=================dari sini test comment nya Timo=================
    echo "<div id='comment_nonform' style='width:480px;height:200px;background-color:white;overflow:auto;'>";
    $resultComments = ProginDB::getInstance()->query("SELECT * FROM comment WHERE id_task=".$row['taskID']);
    while ($rowComments = mysqli_fetch_array($resultComments)) {
        echo "<div id='comment".$rowComments['id_comment']."'>";
        $resultAvatarSpec = ProginDB::getInstance()->query("SELECT * FROM user WHERE username = '".$rowComments['username']."'");
        $rowAvatarSpec = mysqli_fetch_array($resultAvatarSpec, MYSQLI_ASSOC);
        echo "<img src='".$rowAvatarSpec['avatar']."' style='width:30px; height:30px; padding: 5px;'/>";
        $pieces = explode("/", $rowComments['timestamp']);
        echo "<div style='color:black;'>".$rowComments['username'].", ".$pieces[3].":".$pieces[4]." -- ".$pieces[2]."/".$pieces[1]."</div>";
        echo "<div style='color:black;font-size:8pt;text-align:justify;'>".$rowComments['content']."</div>";
        if ($_SESSION['username']===$rowComments['username']){
            echo "<a href='javascript:deleteComment(".$rowComments['id_comment'].");' style='color:black;font-size:10pt;'>Delete</a>";
        }
        echo"</br>";
        echo"</div>";
    }
    echo "</div></br>";

    echo "<div id='comment_form'\>";
    echo "<textarea id='ccontent'></textarea></br>
        <input type='button' value='Submit' onclick=postComment(".$code.");>";
    echo"</div>";
    //=========================sampai sini==================================
    //echo "<textarea id='addCommentaryTask" . $row['taskID'] . "'></textarea><br/>";
    //echo "<button onclick='submitNewCommentary('" . $_SESSION['username'] . "', '" . $row['taskID'] . "');'>Submit commentary</button><br/>";

    echo "<br/>Tag: ";
    $queryTag = "SELECT tag.name AS tagName FROM utrelation JOIN tag JOIN ttrelation WHERE username='" . $_SESSION['username'] . "' AND tag.id_tag = ttrelation.id_tag AND utrelation.id_task = ttrelation.id_task AND utrelation.id_task='" . $row['taskID'] . "';";
    $resultTag = ProginDB::getInstance()->query($queryTag);
    while ($rowTag = mysqli_fetch_array($resultTag)) {
        echo $rowTag['tagName'];
        echo ", ";
    }
    echo "<br/><br/>";

    $queryCreator = "SELECT creator FROM task WHERE id_task='" . $row['taskID'] . "';";
    $resultCreator = ProginDB::getInstance()->query($queryCreator);
    while ($rowOwner = mysqli_fetch_array($resultCreator)) {
        if ($_SESSION['username'] === $rowOwner['creator']) {
            echo "<a class='button' onclick='editTaskDetail(" . $row['taskID'] . ");'>Edit Task</a><br/>";
        }
    }
}
?>
