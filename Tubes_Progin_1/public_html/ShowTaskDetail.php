<?php

require_once("db.php");
session_start();

$code = $_GET['code'];

$query = "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username = '" . $_SESSION['username'] . "' AND utrelation.id_task = task.id_task AND task.id_task='" . $code . "';";
$result = ProginDB::getInstance()->query($query);

while ($row = mysqli_fetch_array($result)) {
    echo "Nama task: " . $row['taskName'] . "<br/>";
    echo "Attachment:";
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

    echo "Deadline: " . $row['deadline'] . "<br/>";

    echo "Assignee: ";
    $queryAssignee = "SELECT username FROM utrelation WHERE id_task='" . $row['taskID'] . "'";
    $resultAssignee = ProginDB::getInstance()->query($queryAssignee);
    while ($rowAssignee = mysqli_fetch_array($resultAssignee)) {
        echo "<a href='#' class='assignee'>" . $rowAssignee['username'] . "</a>";
        echo ", ";
    }
    echo "<br/>";

    echo "<br/>Komentar: <br/>";



    echo "<textarea id='addCommentaryTask" . $row['taskID'] . "'></textarea><br/>";
    echo "<button onclick='submitNewCommentary('" . $_SESSION['username'] . "', '" . $row['taskID'] . "');'>Submit commentary</button><br/>";

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


//Nama task: Nama Tugas <br/>
//Attachment:
//<div class = "attachment">
//file.zip<br/>
//picture.jpg<br/>
//video.mp4<br/>
//</div><br/>
//Deadline: 17-12-2014<br/>
//Assignee: <a href = "" class = "asignee">Timo</a>, <a href = "" class = "asignee">Stefan</a>, <a href = "" class = "asignee">Frilla</a><br/>
//Tag: <a href = "" class = "tag">dangerous</a>, <a href = "" class = "tag">novice</a> <br/>
//<br/>Komentar:<br/>
//<div class = "komentar">lalalsalkdl sajdlkjaslkd sjadlkj sjadlkj jaskjkj jsk. jsad kjasd ajsdlj jksjd as jksjd owjijld.</div><br/>
//<form>
//<textArea></textarea>
//<input type = "button" name = "submit" value = "submit">
//</form>
//<br/><br/>
//<a onclick = "showEdit();" class = "button">edit</a><br/>-->
?>
