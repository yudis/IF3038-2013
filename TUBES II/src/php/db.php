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
        $attachment = $_GET['attachment'];
        $deadline = $_GET['deadline'];
        $listassignee = $_GET['listassignee'];
        $listtag = $_GET['listtag'];
        
        break;
    }
?>

	