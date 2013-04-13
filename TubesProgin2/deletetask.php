<?php
    require_once('config.php');
?>

<?php

session_start();

if (connectDB()) {
    if (isset($_GET['IDTask'])) {
        
        //delete tasknya sendiri
       $deleteTaskQuery = "DELETE FROM task WHERE IDTask='".$_GET['IDTask']."'"; 
       $deleteQuery=mysql_query($deleteTaskQuery);
       
       //delete tasktag
       $deleteTasktagQuery = "DELETE FROM tasktag WHERE IDTask='".$_GET['IDTask']."'"; 
       $deleteTasktag=mysql_query($deleteTasktagQuery);
       
       //delete comment
       $deletecommentQuery = "DELETE FROM comment WHERE IDTask='".$_GET['IDTask']."'"; 
       $deletecomment=mysql_query($deletecommentQuery);
       
       //delete attachment
       $deleteattachQuery = "DELETE FROM attachment WHERE IDTask='".$_GET['IDTask']."'"; 
       $deleteattach=mysql_query($deleteattachQuery);
       
       //delete assignment
       $deleteassignQuery = "DELETE FROM assignment WHERE IDTask='".$_GET['IDTask']."'"; 
       $deleteassign=mysql_query($deleteassignQuery);
    }
}
?>