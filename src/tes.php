<?php
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';

	$tugas = new Tugas();
	$success = $tugas->getTugas(1);
	echo $success["tag"][0];
	