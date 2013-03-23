<?php

require_once("db.php");
session_start();

$code = $_GET["code"];

$query = "SELECT id_task, name, deadline FROM task WHERE id_task='" . $code . "';";
$result = ProginDB::getInstance()->query($query);

while ($row = mysqli_fetch_array($result)) {
    echo "<form>";
    echo "Nama task: " . $row['name'] . "<br/>";
    echo "<br/>Deadline: <input type='date' id='newDeadline'><br/>";
    echo "<br/>Assignee: <div class='assignee'><input type='text' id='newAssignee' value='";
    $queryAssignee = "SELECT username FROM utrelation WHERE id_task='" . $row['id_task'] . "'";
    $resultAssignee = ProginDB::getInstance()->query($queryAssignee);
    while ($rowAssignee = mysqli_fetch_array($resultAssignee)) {
        echo $rowAssignee['username'] . ",";
    }
    echo "' onkeyup=\"multiAutocomp(this, 'GetAllUsernames.php');\"
        onfocusin='multiAutocompClearAll()'></div><br/>";

    echo "Tag: <div class='tag'><input type='text' id='newTag' value='";
    $queryTag = "SELECT tag.name AS tagName FROM utrelation JOIN tag JOIN ttrelation WHERE username='" . $_SESSION['username'] . "' AND tag.id_tag = ttrelation.id_tag AND utrelation.id_task = ttrelation.id_task AND utrelation.id_task='" . $row['id_task'] . "';";
    $resultTag = ProginDB::getInstance()->query($queryTag);
    while ($rowTag = mysqli_fetch_array($resultTag)) {
        echo $rowTag['tagName'] . ",";
    }
    echo "' onkeyup=\"multiAutocomp(this, 'GetAllTags.php');\"
        onfocusin='multiAutocompClearAll()'></div><br/>";

    echo "</form><br/>";
    echo "<a onclick='removeTask(". $_SESSION['category'] . "," . $code . ");' href='dashboard.php' class='button'>Remove Task</a>";
    echo "<a onclick='saveTaskDetail(" . $code . ");showRinci(" . $code . ");' class='button'>Save</a><br/>";
}
?>
