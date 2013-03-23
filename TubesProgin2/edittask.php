<?php

require_once('config.php');
?>

<?php

session_start();
if (connectDB()) {
    $deadline = $_GET['deadline'];
    $assignee = $_GET['assignee'];
    $tag = $_GET['tag'];
    $IDTask = $_GET['IDTask'];

    if ($deadline != "") {
        $deadlineEditQuery = "UPDATE task SET `Deadline` ='" . $deadline . "' WHERE `IDTask`=" . $IDTask . ";";
        $deadlineEdit = mysql_query($deadlineEditQuery);
    }

    $taglist = explode(",", $tag);
    $num_tag = count($taglist);

    if ($assignee != "") {
        $assigneelist = explode(";", $assignee);
        $num_assignee = count($assigneelist);
        for ($x = 0; $x < $num_assignee - 1; $x++) {
            $CheckAssigneeQuery = "SELECT * FROM assignment WHERE Username='" . $assigneelist[$x] . "' AND IDTask='" . $IDTask . "';";
            $CheckAssignee = mysql_query($CheckAssigneeQuery);
            if (mysql_num_rows($CheckAssignee) == 0) {
                $addAssigneeQuery = "INSERT INTO assignment (`IDAssignment`, `Username`, `IDTask`) 
            VALUES (NULL, '" . $assigneelist[$x] . "', '" . $IDTask . "');";
                $addAssignee = mysql_query($addAssigneeQuery);
            }
        }
    }
    if ($tag != "") {
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
                VALUES (NULL , '" . $IDTask . "', '" . $idTag . "');";
            $insertTaskTag = mysql_query($insertTaskTagQuery);
        }
    }
}
?>