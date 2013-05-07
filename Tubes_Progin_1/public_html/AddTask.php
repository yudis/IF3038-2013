<?php

require_once("db.php");
session_start();

$name = $_POST['newTaskName'];


if ($_POST['newTaskAssignee'] != NULL) {
    $assignee = explode(",", $_POST['newTaskAssignee']);
}

if ($_POST['newTaskTag'] != NULL) {
    $tag = explode(",", $_POST['newTaskTag']);
}

if ($_POST['newTaskDeadline']!=NULL){
	$deadline_ex = explode("-",$_POST['newTaskDeadline']);
	$deadline = $deadline_ex[0]."/".$deadline_ex[1]."/".$deadline_ex[2]."/23/03";
}

$query = "INSERT INTO task (name, deadline, status, id_category, creator) VALUES ('"
        . $name . "', '" . $deadline . "', 'F', '" . $_SESSION['category'] . "', '" . $_SESSION['username'] . "');";
$result = ProginDB::getInstance()->query($query);
echo "";

$query = "SELECT id_task FROM task WHERE name='" . $name . "' AND creator='" . $_SESSION['username'] . "';";
$result = ProginDB::getInstance()->query($query);
$row = mysqli_fetch_array($result);

$queryCreator = "INSERT INTO utrelation (username, id_task) VALUES ('" . $_SESSION['username'] . "', '" . $row['id_task'] . "');";
$resultCreator = ProginDB::getInstance()->query($queryCreator);
echo "";

if (isset($_FILES['newTaskAttach'])) {
	$i = 0;
    foreach ($_FILES['newTaskAttach']['tmp_name'] as $file) {
        $file_name = $_FILES['newTaskAttach']['name'][$i];
        $file_tmp = $_FILES['newTaskAttach']['tmp_name'][$i];
        if (is_dir("img/" . $file_name) == FALSE) {
            $newName = "img/".$file_name;
            move_uploaded_file($file_tmp, $newName);
        } else {
            rename($file_tmp, "img/".$file_name.time());
        }
        
        $queryAttach = "INSERT INTO attachment (path) VALUES ('" . $newName . "');";
        $resultAttach = ProginDB::getInstance()->query($queryAttach);
        echo "";
        
        $con = mysql_connect("localhost","progin","progin");
		mysql_select_db("progin",$con);
        $queryAttach = "SELECT id_attachment FROM attachment WHERE path='" . $newName . "';";
		$resultAttach = ProginDB::getInstance()->query($queryAttach);
        $rowAttach = mysqli_fetch_array($resultAttach);
		echo $rowAttach[0];
        
        $queryConAttach = "INSERT INTO tarelation (id_task, id_attachment) VALUES ('" . $row['id_task'] . "', '" . $rowAttach[0] . "');";
        $resultConAttach = ProginDB::getInstance()->query($queryConAttach);
        echo "";
		$i = $i + 1;
    }
}

if ($_POST['newTaskAssignee'] != NULL) {
    foreach ($assignee as $value) {
        if ($value != "") {
            $queryAssignee = "INSERT INTO utrelation (username, id_task) VALUES ('" . $value . "', '" . $row['id_task'] . "');";
            $resultAssignee = ProginDB::getInstance()->query($queryAssignee);
            echo "";
        }
    }
}

if ($_POST['newTaskTag'] != NULL) {
;
   

    foreach ($tag as $value) {
        if ($value != "") {
        	$queryTag = "SELECT name FROM tag WHERE name='".$value."'";
			$resultTag = ProginDB::getInstance()->query($queryTag);
			$temp = mysqli_fetch_array($resultTag);
			if($temp[0]=='' || $temp[0] == NULL){
	            $queryInsertTag = "INSERT INTO tag (name) VALUES ('" . $value . "');";
	            $resultInsertTag = ProginDB::getInstance()->query($queryInsertTag);
			}            
			$querySearchTag = "SELECT id_tag FROM tag WHERE name='" . $value . "';";
            $resultSearchTag = ProginDB::getInstance()->query($querySearchTag);
            $rowSearchTag = mysqli_fetch_array($resultSearchTag);

            $queryTaskTag = "INSERT INTO ttrelation (id_task, id_tag) VALUES ('" . $row['id_task'] . "', '" . $rowSearchTag['id_tag'] . "');";
            $resultTaskTag = ProginDB::getInstance()->query($queryTaskTag);
            echo "";
        }
    }
}
?>
