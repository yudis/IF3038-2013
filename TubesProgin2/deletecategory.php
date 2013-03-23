<?php
    require_once('config.php');
?>

<?php
session_start();
if (connectDB()) {
    if (isset($_GET['IDCategory'])) {
        //delete categorynya sendiri
       $deleteCatQuery = "DELETE FROM category WHERE IDCategory='".$_GET['IDCategory']."'"; 
       $deleteQuery=mysql_query($deleteCatQuery);
       
       //delete authority
       $deleteCatAuthQuery = "DELETE FROM authority WHERE IDCategory='".$_GET['IDCategory']."'"; 
       $deleteAuthQuery=mysql_query($deleteCatAuthQuery);
       
       //delete semua yang terkait sama task
       $selectTaskQuery = "SELECT IDTask FROM task WHERE IDCategory='".$_GET['IDCategory']."'";
       $taskcat = mysql_query($selectTaskQuery);
       
       if ($taskcat > 0) {
       while ($data = mysql_fetch_array($taskcat)) {
          
            //delete tasknya sendiri
            $deleteTaskQuery = "DELETE FROM task WHERE IDTask='".$data['IDTask']."'"; 
            $deleteQuery=mysql_query($deleteTaskQuery);

            //delete tasktag
            $deleteTasktagQuery = "DELETE FROM tasktag WHERE IDTask='".$data['IDTask']."'"; 
            $deleteTasktag=mysql_query($deleteTasktagQuery);

            //delete comment
            $deletecommentQuery = "DELETE FROM comment WHERE IDTask='".$data['IDTask']."'"; 
            $deletecomment=mysql_query($deletecommentQuery);

            //delete attachment
            $deleteattachQuery = "DELETE FROM attachment WHERE IDTask='".$data['IDTask']."'"; 
            $deleteattach=mysql_query($deleteattachQuery);

            //delete assignment
            $deleteassignQuery = "DELETE FROM assignment WHERE IDTask='".$data['IDTask']."'"; 
            $deleteassign=mysql_query($deleteassignQuery);
       }
    }
       
       //delete task
       $deletetaskCatQuery = "DELETE FROM task WHERE IDCategory='".$_GET['IDCategory']."'"; 
       $deletetaskQuery=mysql_query($deletetaskCatQuery);
    }
}
?>