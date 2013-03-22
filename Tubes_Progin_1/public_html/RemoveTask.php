<?php

require_once("db.php");
session_start();

$code = $_GET["code"];

$query = "DELETE FROM tarelation WHERE id_task='" . $code . "';";
$result = ProginDB::getInstance()->query($query);
$query = "DELETE FROM ttrelation WHERE id_task='" . $code . "';";
$result1 = ProginDB::getInstance()->query($query);
$query = "DELETE FROM comment WHERE id_task='" . $code . "';";
$result2 = ProginDB::getInstance()->query($query);
$query = "DELETE FROM utrelation WHERE id_task='" . $code . "';";
$result3 = ProginDB::getInstance()->query($query);
$query = "DELETE FROM task WHERE id_task='" . $code . "';";
$result4 = ProginDB::getInstance()->query($query);



?>
