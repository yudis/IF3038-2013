<?php

require_once('config.php');
?>

<?php

session_start();

if (connectDB()) {
    $taskname = $_POST['namaTask'];
    $deadline = $_POST['newDeadlineTask'];
    $assignee = $_POST['newAssigneeTask'];
    $tag = $_POST['newTagTask'];
    $file = $_FILES['attachfile']['tmp_name'];
    $category = $_POST['newCategoryID'];
    $userID = $_POST['newUserID'];

    $insertTaskQuery = "INSERT INTO `task` (`IDTask` ,`IDCategory` ,`TaskName` ,`Status` ,`Deadline`,`Creator`)
        VALUES (NULL , '" . $category . "', '" . $taskname . "', 'undone', '" . $deadline . "', '" . $userID . "');";
    $insertTask = mysql_query($insertTaskQuery);

    $idTaskQuery = "SELECT * from task";
    $idTaskList = mysql_query($idTaskQuery);
    if ($idTaskList > 0) {
            while ($data = mysql_fetch_array($idTaskList)) {
                $idTask=$data['IDTask'];
            }
     }

    $assigneelist = explode(";", $assignee);

    $addAssigneeQuery = "INSERT INTO assignment (`IDAssignment`, `Username`, `IDTask`) 
            VALUES (NULL, '" . $userID . "', '" . $idTask . "');";
    $addAssignee = mysql_query($addAssigneeQuery);

    $num_assignee = count($assigneelist);
    for ($x = 0; $x < $num_assignee - 1; $x++) {
        $CheckAssigneeQuery = "SELECT * FROM assignment WHERE Username='" . $assigneelist[$x] . "' AND IDTask='" . $idTask . "';";
        $CheckAssignee = mysql_query($CheckAssigneeQuery);
        if (mysql_num_rows($CheckAssignee) == 0) {
            $addAssigneeQuery = "INSERT INTO assignment (`IDAssignment`, `Username`, `IDTask`) 
            VALUES (NULL, '" . $assigneelist[$x] . "', '" . $idTask . "');";
            $addAssignee = mysql_query($addAssigneeQuery);
        }
    }

    $taglist = explode(",", $tag);
    $num_tag = count($taglist);
    for ($x = 0; $x < $num_tag; $x++) {
        $CheckTagQuery = "SELECT * FROM tag where TagName='" . $taglist[$x] . "';";
        $CheckTag = mysql_query($CheckTagQuery);
        if (mysql_num_rows($CheckTag) > 0) {
            $FetchTag = mysql_fetch_array($CheckTag);
            $idTag = $FetchTag[0];
        } else {
            $insertTagQuery = "INSERT INTO `tag` (`IDTag` ,`TagName`)
                VALUES (NULL , '" . $taglist[$x] . "');";
            $insertTag = mysql_query($insertTagQuery);
            $idTagQuery = "SELECT * from tag";
            $idTagList = mysql_query($idTagQuery);
            $idTag = mysql_num_rows($idTagList);
        }
        $insertTaskTagQuery = "INSERT INTO `tasktag` (`IDTaskTag` ,`IDTask` ,`IDTag`)
                VALUES (NULL , '" . $idTask . "', '" . $idTag . "');";
        $insertTaskTag = mysql_query($insertTaskTagQuery);
    }


    if (isset($file)) {
        $num_files = count($file);
        for ($x = 0; $x < $num_files; $x++) {
            $filename = "attachment/" . $taskname . "_" . $_FILES["attachfile"]["name"][$x];
            move_uploaded_file($file[$x], $filename);
            $insertAttachmentQuery = "INSERT INTO `attachment` (`IDAttachment`, `IDTask`, `PathFile`) 
                VALUES (NULL, '" . $idTask . "', '" . $filename . "');";
            $insertAttachment = mysql_query($insertAttachmentQuery);
        }
    }
    header('Location: RinciTugas.php?IDTask='.$idTask);
}
?>