<?php
    require_once("db.php");
    session_start();
    if(isset($_GET['c']) && isset($_GET['i'])){
           $now = date('Y/m/d/H/i');
           $idtask = $_GET['i'];
           ProginDB::getInstance()->query("INSERT INTO comment (id_task, username, timestamp, content) VALUES ('". $idtask ."','". $_SESSION['username']."','".$now."','".$_GET['c']."')");
           $resultAvatarSpec = ProginDB::getInstance()->query("SELECT * FROM user WHERE username = '".$_SESSION['username']."'");
           $rowAvatarSpec = mysqli_fetch_array($resultAvatarSpec, MYSQLI_ASSOC);
           
           $result = ProginDB::getInstance()->query("SELECT id_comment FROM comment WHERE id_task='".$idtask."' AND username='".$_SESSION['username']."' AND timestamp='".$now."' AND content='".$_GET['c']."'");
           $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
           $query = $rowAvatarSpec['avatar'].";".$_SESSION['username'].";".date('d;m;H;i').";".$_GET['c'].";".$row['id_comment']."";
           echo $query;
    }
    
?>
