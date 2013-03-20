<?php
    // Connection's Parameters
    echo ("kata afif suruh echo");  
    $db_host = "localhost";
    $db_name = "progin_405_13510020";
    $username = "progin";
    $password = "progin";
    $db_con = mysql_connect($db_host, $username, $password);
    $connection_string = mysql_select_db($db_name);
    // Connection
    mysql_connect($db_host, $username, $password);
    mysql_select_db($db_name);
?>