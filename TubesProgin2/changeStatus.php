<?php
    require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    if (isset($_GET['IDTask']) && isset($_GET['Status'])) {
       if ($_GET['Status'] =="done")
        {$queryStatus= "UPDATE task SET Status=\"undone\" WHERE IDTask=\"".$_GET['IDTask']."\"";}
       else
        {$queryStatus= "UPDATE task SET Status=\"done\" WHERE IDTask=\"".$_GET['IDTask']."\"";}
        $query = mysql_query($queryStatus);
        echo ($_GET['Status']);
    }
}
?>
