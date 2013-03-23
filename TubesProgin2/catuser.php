<?php
    require_once('config.php');
?>
<?php

// Inialize session
session_start();

if (connectDB()) {
    $q = $_GET['q'];
    header("Content-type: text/xml");
    $xml = new SimpleXMLElement("<xml/>");


    $sqlQuery = "SELECT username,fullname FROM user WHERE username LIKE '%" . $q . "%' OR fullname LIKE '%" . $q . "%'";
    $query = mysql_query($sqlQuery);

    while ($v = mysql_fetch_array($query)) {
        $data = $xml->addChild("Data");
        $data->addChild("ID", $v[0]);
        $data->addChild("String", $v[1]);
    }

    print ($xml->asXML());
}
?>