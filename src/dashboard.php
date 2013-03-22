<?php
/* Standard Header */
require 'utilities/db.php';
require 'utilities/model.php';
require 'utilities/view.php';
require 'models/tugas.php';
require 'models/kategori.php';

session_start();
if (isset($_SESSION["user"]["username"]))
{
	$view = new View('views/dashboard/dashboard.tpl');
	$view->set('title', 'Todolist | Dashboard');
			$view->set('headTags', '<link rel="stylesheet" type="text/css" href="styles/default.css" />
							<link rel="stylesheet" type="text/css" href="styles/mediaqueries.css" />
							<script src="scripts/helper.js" type="application/javascript"></script>
							<script src="scripts/popup.js" type="application/javascript"></script>
							<script src="scripts/dashboard.js" type="application/javascript"></script>
							<script src="scripts/kategori.js" type="application/javascript"></script>');
	$view->set('bodyAttrs','<body onload="updateAddButtonVisibility();loadtugas(\'\');">
        <div id="blanket"></div>
        <div id="popUpDiv">
            <h1>Create new category</h1>
            <div class="padding12px"><label for="txtNewKategori">Name</label>:<br />
                <input id="txtNewKategori" type="text" placeholder="eg: IF40XX" /></div>
            <br />
            <div class="padding12px">
                Priviledge users:<br />
                <ul id="userList" class="tag"></ul>
				<br>
				<input id="userL" name="userL" onfocus="showCoordinator()" type="text" tabindex="4" list="user" />
				<datalist id="user" >
				</datalist>
				<button onclick="return addCoordinator();">Add</button>
            </div>
            <br />
            <div class="rightalign padding12px"><button onclick="popup(\'popUpDiv\',\'blanket\',300,600); NewKategori()">OK</button> <button onclick="popup(\'popUpDiv\',\'blanket\',300,600)">Cancel</button></div>
            <br />
        </div>');
	echo $view->output();
}
else
{
	header('Location: index.php');
}