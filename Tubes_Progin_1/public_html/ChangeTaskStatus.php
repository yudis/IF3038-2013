<?php

require_once("db.php");
session_start();

$code = $_GET["code"];
$chk = $_GET["chkYesNo"];

if ($chk == "0") {
    $query = "UPDATE task SET status='F' WHERE id_task='" . $code . "';";
} else if ($chk == "1") {
    $query = "UPDATE task SET status='T' WHERE id_task='" . $code . "';";
}

ProginDB::getInstance()->query($query);

$afterQuery = "SELECT status FROM task WHERE id_task='" . $code . "';";
$resultAfterQuery = ProginDB::getInstance()->query($afterQuery);
while ($row = mysqli_fetch_array($resultAfterQuery)) {
    echo "<div>" . $row['status'] . "</div>";
    echo "<div>";
    if ($row['status'] == "T") {
        echo "<input type='checkbox' id='checkboxTask" . $code . "' onclick='changeTaskStatus(" . $code . ", this.checked);' checked>";
    } else if ($row['status' == "F"]) {
        echo "<input type='checkbox' id='checkboxTask" . $code . "' onclick='changeTaskStatus(" . $code . ", this.checked);'>";
    }
    echo "Done</div>";
}
?>
