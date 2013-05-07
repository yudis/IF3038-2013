<?php

require_once("db.php");
session_start();

$query = "SELECT task.id_task AS taskID, task.name AS taskName, deadline, status FROM utrelation JOIN task WHERE username = '" . $_SESSION['username'] . "' AND utrelation.id_task = task.id_task;";
$result = ProginDB::getInstance()->query($query);

while ($row = mysqli_fetch_array($result)) {
    echo "<div class='listTugas' id='task" . $row['taskID'] . "'>";
    echo "<a id='task" . $row['taskID'] . "' onclick='showRinci(" . $row['taskID'] . ");'><b>" . $row['taskName'] . "</b></a>";
    echo "<div>" . $row['deadline'] . "</div>";

    $queryTag = "SELECT tag.name AS tagName FROM utrelation JOIN tag JOIN ttrelation WHERE username='" . $_SESSION['username'] . "' AND tag.id_tag = ttrelation.id_tag AND utrelation.id_task = ttrelation.id_task AND utrelation.id_task='" . $row['taskID'] . "';";
    $resultTag = ProginDB::getInstance()->query($queryTag);

    echo "<div style='font-size:10pt;'>Tag: <i>";
    while ($rowTag = mysqli_fetch_array($resultTag)) {
        echo $rowTag['tagName'] . ", ";
    }
    echo "</i></div>";

    echo "<div id='statusTask" . $row['taskID'] . "'>";
    echo "<div>" . $row['status'] . "</div>";
    echo "<div>";
    if ($row['status'] == "T") {
        echo "<input type='checkbox' id='checkboxTask" . $row['taskID'] . "' onclick='changeTaskStatus(" . $row['taskID'] . ", this.checked);' checked>";
    } else if ($row['status' == "F"]) {
        echo "<input type='checkbox' id='checkboxTask" . $row['taskID'] . "' onclick='changeTaskStatus(" . $row['taskID'] . ", this.checked);'>";
    }
    echo "Done</div>";
    echo "</div>";

    $queryOwner = "SELECT creator FROM task WHERE id_task='" . $row['taskID'] . "';";
    $resultOwner = ProginDB::getInstance()->query($queryOwner);
    while ($rowOwner = mysqli_fetch_array($resultOwner)) {
        if ($_SESSION['username'] === $rowOwner['creator']) {
            echo "<div class='removeTask'><input type='submit' id='removeTaskBtn" . $row['taskID'] . "' onclick='removeTask(" . $_SESSION['category'] . "," . $row['taskID'] . ");' value='Remove Task'/></div>";
        }
    }
    echo "</div>";
}
?>
