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
$changePriviledge = false;
if (isset($_GET["update"]) && isset($_GET["id_tugas"]))
{
	$needUpdate = !$tugas->isUpdated($_GET["id_tugas"], $_GET["update"]);
	if (!$needUpdate)
	{
		$dataT = $tugas->getTugas($_GET["id_tugas"], $_SESSION["user"]["username"]);
		if ($dataT == false) 
		{
			$needUpdate = true;
		}
		else if (isset($_GET["priviledge"]) && (($dataT["priviledge"]) ? 'true' : 'false') != $_GET["priviledge"])
		{
			$changePriviledge = true;
		}
	}
}

if ($changePriviledge)
{
	$data = Array();
	$data["responseStatus"] = 205;
	$data["responseTime"] = $_SERVER['REQUEST_TIME'];
	$data["message"] = "Change Priviledge";
}
else if ($needUpdate)
{
	if (isset($_GET["id_tugas"]))
	{
		/* Get Tugas */
		$data = $tugas->getTugas($_GET["id_tugas"], $_SESSION["user"]["username"]);
		if ($data) 
		{
			$data["responseStatus"] = 200;
			$data["responseTime"] = $_SERVER['REQUEST_TIME'];

			/* Get Komentar */
			$idtugas = $_GET["id_tugas"];
			$startindex = isset($_GET["startc"]) ? $_GET["startc"] : 0;
			$count = isset($_GET["countc"]) ? $_GET["countc"] : 10;

			$comments = new Komentar();
			$datakomentar = Array();
			$datakomentar["count"] = $count;
			$datakomentar["total"] = $comments->getCommentsCount($idtugas);
			while ($startindex > $datakomentar["total"]) {
				$startindex -= $count;
			}
			$datakomentar["startindex"] = $startindex;
			$datakomentar["comments"] = $comments->getComments($idtugas, $startindex, $count, $_SESSION["user"]["username"]);

			$data["comments"] = $datakomentar;
		}
		else
		{
			$data["responseStatus"] = 205;
			$data["message"] = "Reset Content";
			$data["responseTime"] = $_SERVER['REQUEST_TIME'];
		}
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
	$data["responseStatus"] = 304;
	$data["responseTime"] = $_SERVER['REQUEST_TIME'];
	$data["message"] = "Not Modified";
}

header("Content-type: application/json");
echo json_encode($data);