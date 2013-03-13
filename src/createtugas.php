<?php
/* Standard Header */
include './utilities/db.php';
include './utilities/model.php';
include './models/tugas.php';

session_start();
if (isset($_SESSION["user"]))
{
	// user sudah login, dialihkan ke halaman lain
	header('Location: ../dashboard.php');	
}
else
{
	if (isset($_POST["namatask"]) && isset($_POST["attachment"]) && isset($_POST["deadline"]) && isset($_POST["assignee"]) && isset($_POST["tag"]))
	{
		try
		{
			$tugas = new Tugas();
			$tugas->set_taskname = $tugas["namatask"];
			$tugas->set_attachment = $tugas["attachment"];
			$tugas->set_tgl_deadline = $tugas["tgl_deadline"];
			$tugas->set_status = $tugas["status"];
			$tugas->set_last_mod = $tugas["last_mod"];
			$tugas->set_PIC = $_SESSION["user"];
			

			$tugas->store();
			
			$avatarfile = $_POST["uname"] . "." . pathinfo($_FILES['ava']['name'], PATHINFO_EXTENSION);	
			if (!copy($_FILES["ava"]["tmp_name"], "./images/avatars/" . $avatarfile))
			{		
				die("Failed to upload avatar picture.");
			}
			
			header('Location: ./dashboard.php');	
		}
		catch(Exception $e)
		{
			die($e->getMessage());
		}
		
	}
	else
	{
		die("Parameters do not complete.");
	}
}