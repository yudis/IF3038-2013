<?php
/* Standard Header */
require '../utilities/db.php';
require '../utilities/model.php';
require '../models/komentar.php';

session_set_cookie_params(30 * 24 * 60 * 60); 
session_start();

$return = array();
if (!isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	// header('Location: ../dashboard.php');
	$return["status"] = 403;
	$return["message"] = "Forbidden";
}
else
{
	if (isset($_GET["id_tugas"]))
	{
		$idtugas = $_GET["id_tugas"];
		$startindex = isset($_GET["start"]) ? $_GET["start"] : 0;
		$count = isset($_GET["count"]) ? $_GET["count"] : 10;
	
		$comments = new Komentar();

		$return["status"] = 200;
		$return["idtugas"] = $idtugas;
		$return["startindex"] = $startindex;
		$return["count"] = $count;
		$return["total"] = $comments->getCommentsCount($idtugas);
		$return["comments"] = $comments->getComments($idtugas, $startindex, $count, $_SESSION["user"]["username"]);
	}
	else
	{
		$return["status"] = 400;
		$return["message"] = "Bad Request";
	}
}

header('Content-Type:application/json');
echo json_encode($return);