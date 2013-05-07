<?php

require_once("db.php");
session_start();

$code = $_GET["code"];

$query1 = "DELETE FROM tarelation WHERE id_task='" . $code . "';";
$result1 = ProginDB::getInstance()->query($query1);
echo "";

$query2 = "DELETE FROM ttrelation WHERE id_task='" . $code . "';";
$result2 = ProginDB::getInstance()->query($query2);
echo "";

$query3 = "DELETE FROM comment WHERE id_task='" . $code . "';";
$result3 = ProginDB::getInstance()->query($query3);
echo "";

$query4 = "DELETE FROM utrelation WHERE id_task='" . $code . "';";
$result4 = ProginDB::getInstance()->query($query4);
echo "";

$query5 = "DELETE FROM task WHERE id_task='" . $code . "';";
$result5 = ProginDB::getInstance()->query($query5);
echo "";
?>
