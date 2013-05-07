<?php
$q = $_GET['q'];
$xml = new SimpleXMLElement("<xml/>");
header("Content-type: text/xml");

$con = mysql_connect("localhost", "root", "");
mysql_select_db("progin_405_13510056", $con);
$query = "SELECT name FROM tag WHERE name LIKE '%" . $q . "%'";
$result = mysql_query($query);
print mysql_error($con);

if (mysql_affected_rows($con) > 0) {
    while ($row = mysql_fetch_array($result)) {
        $data = $xml->addChild("Data");
        $data->addChild("ID", $row[0]);
        $data->addChild("String", $row[0]);
    }
}

print ($xml->asXML());
?>
