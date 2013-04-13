<?php

$searchquery = $_GET['searchquery'];
$filter = $_GET['filter'];
$task = $_GET['task'];
require_once("database.php");
$con = connectDatabase();

        $query = 'SELECT * FROM task WHERE namaTask="'.$task.'"';
	$result = mysqli_query($con,$query);
        $row =  mysqli_fetch_array($result);
        session_start();
        if($row['creatorTaskName']== $_SESSION['login']){
            if($row['status']=="done"){
                $query = "UPDATE  `sharedtodolist`.`task` SET  `status` =  'undone' WHERE  `task`.`namaTask` =  '$task';";
            }else{
                $query = "UPDATE  `sharedtodolist`.`task` SET  `status` =  'done' WHERE  `task`.`namaTask` =  '$task';";
            }
            mysqli_query($con, $query);
        }
        if($filter==-1)
            header("Location:viewtask.php?nama=".$task);
        else if($filter==-2)
            header("Location:dashboard.php");
        else
            header("Location:search-result.php?searchquery=".$searchquery."&filter=".$filter);
            
?>
