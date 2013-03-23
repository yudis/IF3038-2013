<?php

require_once("db.php");
session_start();

$name = $_GET['name'];
if ($_GET['authUsers'] != NULL) {
    $authUsers = explode(",", $_GET['authUsers']);
    $authUserCount = count($authUsers);
}

$query = "INSERT INTO category (name, categ_creator) VALUES ('" . $name . "', '" . $_SESSION['username'] . "');";
$result = ProginDB::getInstance()->query($query);

$query = "SELECT id_category FROM category WHERE name='" . $name . "';";
$result = ProginDB::getInstance()->query($query);
echo "";
$row = mysqli_fetch_array($result);

$queryCreator = "INSERT INTO caurelation (id_category, authorized_user) VALUES ('" . $row['id_category'] . "', '" . $_SESSION['username'] . "');";
$resultCreator = ProginDB::getInstance()->query($queryCreator);
echo "";

$queryAu = "INSERT INTO ucrelation (username, id_category) VALUES ('" . $_SESSION['username'] . "', '" . $row['id_category'] . "');";
$resultAu = ProginDB::getInstance()->query($queryAu);
echo "";

if ($authUserCount != 0) {
    for ($x = 0; $x < $authUserCount; $x++) {
        if ($authUsers[$x] != "") {
            $queryAu = "INSERT INTO ucrelation (username, id_category) VALUES ('" . $authUsers[$x] . "', '" . $row['id_category'] . "');";
        $resultAu = ProginDB::getInstance()->query($queryAu);
        echo "";

        $queryCateg = "INSERT INTO caurelation (id_category, authorized_user) VALUES ('" . $row['id_category'] . "', '" . $authUsers[$x] . "');";
        $resultCateg = ProginDB::getInstance()->query($queryCateg);
        echo "";
        }
    }
}
?>
