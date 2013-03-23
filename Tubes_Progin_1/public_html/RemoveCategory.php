<?php

require_once("db.php");
session_start();

$code = $_GET["code"];

// Delete all tasks contained in the category
$query = "SELECT id_task FROM task WHERE id_category='" . $code . "';";
$result = ProginDB::getInstance()->query($query);
while ($row = mysqli_fetch_array($result)) {
    $queryDeleteTask = "DELETE FROM ttrelation WHERE id_task='" . $row['id_task'] . "';";
    $resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
    echo "";

    $queryDeleteTask = "DELETE FROM utrelation WHERE id_task='" . $row['id_task'] . "';";
    $resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
    echo "";

    $queryDeleteTask = "DELETE FROM tarelation WHERE id_task='" . $row['id_task'] . "';";
    $resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
    echo "";

    $queryDeleteTask = "DELETE FROM comment WHERE id_task='" . $row['id_task'] . "';";
    $resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
    echo "";

    $queryDeleteTask = "DELETE FROM task WHERE id_task='" . $row['id_task'] . "';";
    $resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
    echo "";
}

// Delete the category anywhere
$queryDeleteTask = "DELETE FROM ucrelation WHERE id_category='" . $code . "';";
$resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
echo "";

$queryDeleteTask = "DELETE FROM caurelation WHERE id_category='" . $code . "';";
$resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
echo "";

$queryDeleteTask = "DELETE FROM category WHERE id_category='" . $code . "';";
$resultDelete = ProginDB::getInstance()->query($queryDeleteTask);
echo "";

// Refresh the category list again
$query = "SELECT name, category.id_category AS categoryID FROM category JOIN ucrelation WHERE category.id_category = ucrelation.id_category AND username = '" . $_SESSION['username'] . "'";
$result = ProginDB::getInstance()->query($query);
echo "<div class='kategori' id='categoryAll' onclick='showTaskList(0);'>All</div>";
while ($row = mysqli_fetch_array($result)) {
    echo "<div class='kategori' id='category" . $row['categoryID'] . "' onclick='showTaskList(" . $row['categoryID'] . ");'>" . $row['name'] . "</div>";

    $queryCategCreator = "SELECT categ_creator FROM category WHERE id_category='" . $row['categoryID'] . "';";
    $resultCategCreator = ProginDB::getInstance()->query($queryCategCreator);
    while ($rowCategCreator = mysqli_fetch_array($resultCategCreator)) {
        if ($_SESSION['username'] === $rowCategCreator['categ_creator']) {
            echo "<div class='kategori' id='removeCategory" . $row['categoryID'] . "' onclick='removeCategory(" . $row['categoryID'] . ");'>REMOVE " . $row['name'] . "</div>";
        }
    }
}
?>
