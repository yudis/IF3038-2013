<?php
session_start(); 
include 'ChromePhp.php';
ChromePhp::log('dbphp');

$iduser = $_SESSION['login'];
ChromePhp::log($iduser);
$tabletype = $_GET['tabletype'];
$idtask = $_GET['idtask'];

$con = mysqli_connect(localhost,"progin","progin","progin") or die ('Cannot connect to database : ' . mysql_error());

switch ($tabletype) {
    case "komentar" :
        $comment = $_GET['commentarea'];
        $query = "SELECT COUNT(*) as num FROM komentar";
        $countid = mysqli_fetch_array(mysqli_query($con, $query));
        $nextid = $countid['num']+1;
        if ($nextid < 10) {
            $nextidstring = "K00".$nextid;
        } else if ($nextid < 100) {
            $nextidstring = "K0".$nextid;
        } else {
            $nextidstring = "K".$nextid;
        }
        $query = "INSERT INTO komentar (ID,IDTask,IDUser,Waktu,Isi) VALUES ('".$nextidstring."','".$idtask."','".$iduser."',now(),'".$comment."')";
        
        if (mysqli_query($con, $query))
        {
            echo "Record inserted";
        }
        else 
        {
            echo "Error inserting record: " . mysql_error();
        }
        break;
    case "taskdetails" :
        $namatask = $_GET['namatask'];
        //$attachment = $_GET['attachment'];
        $deadline = $_GET['deadline'];
        $listassignee = $_GET['listassignee'];
        $listtag = $_GET['listtag'];
        
        $assignees = explode(",", $listassignee);
        $tags = explode(",", $listtag);
        
        $query = "UPDATE task SET NAMA='".$namatask."', Deadline='".$deadline."' WHERE ID='".$idtask."'";
        
        if (mysqli_query($con, $query))
        {
            echo "Record task updated";
        }
        else 
        {
            echo "Error updating record task: " . mysql_error();
        }
        
        $query = "DELETE FROM tags WHERE IDTask='".$idtask."'";
        if (mysqli_query($con, $query))
        {
            echo "Record tags deleted";
        }
        else 
        {
            echo "Error deleting record tags: " . mysql_error();
        }
        
        $i = 0;
        while ($i < count($tags)) {
            $query = "INSERT INTO tags VALUES ('".$idtask."','".$tags[$i]."')";
            if (mysqli_query($con, $query))
            {
                echo "Record tags updated";
            }
            else 
            {
                echo "Error updating record tags: " . mysql_error();
            }
            $i++;
        }
        
        $query = "DELETE FROM assignee WHERE IDTask='".$idtask."'";
        if (mysqli_query($con, $query))
        {
            echo "Record assignee deleted";
        }
        else 
        {
            echo "Error deleting record assignee: " . mysql_error();
        }
        
        $i = 0;
        while ($i < count($assignees)) {
            $query = "INSERT INTO assignee VALUES ('".$idtask."','".$assignees[$i]."')";
            if (mysqli_query($con, $query))
            {
                echo "Record assignee updated";
            }
            else 
            {
                echo "Error updating record assignee: " . mysql_error();
            }
            $i++;
        }
        
        
        break;
    }
?>

	