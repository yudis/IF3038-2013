<?php
require '../utilities/db.php';
require '../utilities/model.php';
require '../utilities/view.php';
require '../models/tugas.php';
require '../models/komentar.php';

session_set_cookie_params(30 * 24 * 60 * 60); 
session_start();


$data = Array();
try {
	if (isset($_GET["removec"]) && isset($_GET["id_komentar"])) {
		$comment = new Komentar();

		$datakomentar = $comment->getCommentById($_GET["id_komentar"]);
		if ($_SESSION["user"]["username"] == $datakomentar["user"])
		{
			$comment->delete($_GET["id_komentar"]);
			$tugas = new Tugas();
			$tugas->updateTimestamp($datakomentar["id_tugas"]);

			$data["responseStatus"] = 200;
		}
		else
		{
			$data["responseStatus"] = 403;
			$data["message"] = "Forbidden";
		}
	} else if (isset($_POST["addc"]) && isset($_POST["id_tugas"]) && isset($_POST["content"])) {
		$comment = new Komentar();

	    $comment->set_id_tugas($_POST["id_tugas"]);
	    $comment->set_username($_SESSION["user"]["username"]);
	    $comment->set_content($_POST["content"]);
	    $comment->store();

		$tugas = new Tugas();
		$tugas->updateTimestamp($_POST["id_tugas"]);

		$data["responseStatus"] = 200;
	} else if (isset($_GET["chstatus"]) && isset($_GET["id_tugas"]) && isset($_GET["status"])) {
		$tugas = new Tugas();
		$tugas->setStats($_GET["id_tugas"], $_GET["status"]);

		$data["responseStatus"] = 200;
	} else if (isset($_GET["removea"]) && isset($_GET["id_tugas"]) && isset($_GET["username"])) {
		$tugas = new Tugas();
		$tugas->removeAssignee($_GET["id_tugas"], $_GET["username"]);

		if ($_GET["username"] == $_SESSION["user"]["username"])
		{
			$data["killself"] = 1;
		}
		else
		{		
			$data["killself"] = 0;
		}
		
		$data["responseStatus"] = 200;
	} else if (isset($_GET["adda"]) && isset($_GET["id_tugas"]) && isset($_GET["username"])) {
		$tugas = new Tugas();
		$tugas->addAssignee($_GET["id_tugas"], $_GET["username"]);
		$tugas->updateTimestamp($_GET["id_tugas"]);

		$data["responseStatus"] = 200;
	} else if (isset($_GET["suggesta"]) && isset($_GET["id_tugas"]) && isset($_GET["start"])) {
		$tugas = new Tugas();
		$data = Array();
		$data["responseStatus"] = 200;
		$data["suggestedAssignees"] = $tugas->getSuggestionAssignees($_GET["id_tugas"], $_GET["start"] . '%', 10);
	} else {
		$data["responseStatus"] = 400;
		$data["message"] = "Bad request";
	}
} catch (Exception $e) {
	$data["responseStatus"] = 400;
	$data["message"] = $e->getMessage();
}

header('Content-Type:application/json');
echo json_encode($data);