<?php
require '../utilities/db.php';
require '../utilities/model.php';
require '../utilities/view.php';
require '../models/tugas.php';
require '../models/komentar.php';

session_set_cookie_params(30 * 24 * 60 * 60); 
session_start();

$tugas = new Tugas();
$needUpdate = true;
if (isset($_GET["update"]) && isset($_GET["id_tugas"]))
{
	$needUpdate = !$tugas->isUpdated($_GET["id_tugas"], $_GET["update"]);
}

if ($needUpdate)
{
	if (isset($_GET["id_tugas"]))
	{
		/* Get Tugas */
		$data = $tugas->getTugas($_GET["id_tugas"]);
		$data["responseStatus"] = 200;
		$data["responseTime"] = $_SERVER['REQUEST_TIME'];

		/* Get Komentar */
		$idtugas = $_GET["id_tugas"];
		$startindex = isset($_GET["startc"]) ? $_GET["startc"] : 0;
		$count = isset($_GET["countc"]) ? $_GET["countc"] : 10;

		$comments = new Komentar();
		$datakomentar = Array();
		$datakomentar["startindex"] = $startindex;
		$datakomentar["count"] = $count;
		$datakomentar["total"] = $comments->getCommentsCount($idtugas);
		$datakomentar["comments"] = $comments->getComments($idtugas, $startindex, $count, $_SESSION["user"]["username"]);

		$data["comments"] = $datakomentar;
	}
	else
	{
		$data = Array();
		$data["responseStatus"] = 400 ;
		$data["responseTime"] = $_SERVER['REQUEST_TIME'];
		$data["message"] = "Bad Request";
	}
}
else
{
	$data = Array();
	$data["responseStatus"] = 204;
	$data["responseTime"] = $_SERVER['REQUEST_TIME'];
	$data["message"] = "No content";
}

header("Content-type: application/json");
echo json_encode($data);