<?php

require_once("db.php");
session_start();

$code = $_GET['code'];
$deadline = $_GET['newDeadline'];
$assignee = explode(",", $_GET['newAssignee']);
$tag = explode(",", $_GET['newTags']);

$query = "UPDATE task SET deadline='" . $deadline . "' WHERE id_task='" . $code . "';";
$result = ProginDB::getInstance()->query($query);
echo "";

$queryCreator = "SELECT creator FROM task WHERE id_task='" . $code . "';";
$resultCreator = ProginDB::getInstance()->query($queryCreator);
$rowCreator = mysqli_fetch_array($resultCreator);

$queryDeleteAssignee = "DELETE FROM utrelation WHERE id_task='" . $code . "' AND username!='" . $rowCreator['creator'] . "';";
$resultDeleteAssignee = ProginDB::getInstance()->query($queryDeleteAssignee);
echo "";

$queryUserList = "SELECT username FROM user;";
$resultUserList = ProginDB::getInstance()->query($queryUserList);
$rowUserList = mysqli_fetch_array($resultUserList);

foreach ($assignee as $value) {
    if ($value != "") {
        if (in_array($value, $rowUserList['username'])) {
            $queryAddAssignee = "INSERT INTO utrelation (username, id_task) VALUES ('" . $value . "', '" . $code . "');";
            $resultAddAssignee = ProginDB::getInstance()->query($queryAddAssignee);
            echo "";
            
            $queryAsd = "SELECT id_category FROM ucrelation WHERE id_category='" . $_SESSION['category'] . "';";
            $resultAsd = ProginDB::getInstance()->query($queryAsd);
            $rowAsd = mysqli_fetch_array($resultAsd);
            if (count($rowAsd) == 0) {
                $queryBnm = "INSERT INTO ucrelation (username, id_category) VALUES ('" . $value . "', '" . $_SESSION['category'] . "');";
                $resultBnm = ProginDB::getInstance()->query($queryBnm);
                echo "";
            }
        }
    }
}

$query = "SELECT tag.id_tag AS tagID FROM tag JOIN ttrelation WHERE id_task='" . $code . "' AND tag.id_tag = ttrelation.id_tag;";
$result = ProginDB::getInstance()->query($query);
while ($row = mysqli_fetch_array($result)) {
    $queryDeleteTag = "DELETE FROM ttrelation WHERE id_tag='" . $row['tagID'] . "';";
    $resultDeleteTag = ProginDB::getInstance()->query($queryDeleteTag);
    echo "";
}

$queryTag = "SELECT name FROM tag;";
$resultTag = ProginDB::getInstance()->query($queryTag);
$rowTag = mysqli_fetch_array($resultTag);

foreach ($tag as $value) {
    if ($value != "") {
        if (!in_array($value, $rowTag)) {
            $queryInsertTag = "INSERT INTO tag (name) VALUES ('" . $value . "');";
            $resultInsertTag = ProginDB::getInstance()->query($queryInsertTag);
            echo "";
        }

        if (!in_array($value, $row)) {
            $querySearchTag = "SELECT id_tag FROM tag WHERE name='" . $value . "';";
            $resultSearchTag = ProginDB::getInstance()->query($querySearchTag);
            $rowSearchTag = mysqli_fetch_array($resultSearchTag);

            $queryTaskTag = "INSERT INTO ttrelation (id_task, id_tag) VALUES ('" . $row['id_task'] . "', '" . $rowSearchTag['id_tag'] . "');";
            $resultTaskTag = ProginDB::getInstance()->query($queryTaskTag);
            echo "";
        }
    }
}
?>
