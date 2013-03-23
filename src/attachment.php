<?php

/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';


session_set_cookie_params(7 * 24 * 60 * 60); 
session_start();
if (isset($_SESSION["user"]) && isset($_GET["file"]) &&  isset($_GET["nama"]))
{
	header("Content-type: application/octet-stream");
	header('Content-Disposition: attachment; filename="' . $_GET["nama"] . '"');
	
	echo file_get_contents('./files/' . $_GET["file"]);
}