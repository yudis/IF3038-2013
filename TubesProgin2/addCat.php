<?php

require_once('config.php');
?>

<?php

session_start();

if (connectDB()) {
    $catname = $_POST['newcat'];
    $catuser = $_POST['newcatuser'];

    $insertCatQuery = "INSERT INTO `category` (`IDCategory` ,`CategoryName` ,`Creator`)
        VALUES (NULL , '" . $catname . "', '" . $_COOKIE['UserLogin'] . "');";
    $insertCat = mysql_query($insertCatQuery);

    $idcatQuery = "SELECT IDCategory from category WHERE CategoryName =\"".$catname."\"";
    $idCatList = mysql_query($idcatQuery);
    if ($idCatList > 0) {
            while ($data = mysql_fetch_array($idCatList)) {
                $idCat=$data['IDCategory'];
            }
   }

    $authlist = explode(";", $catuser);
    
    $num_auth = count($authlist);
    for ($x = 0; $x < $num_auth - 1; $x++) {
        $CheckAuthQuery = "SELECT * FROM authority WHERE Username='" . $authlist[$x] . "' AND IDCategory='" . $idCat . "';";
        $CheckAuth = mysql_query($CheckAuthQuery);
        if (mysql_num_rows($CheckAuth) == 0) {
            $addAuthQuery = "INSERT INTO `authority` (`IDAuthority`, `IDCategory`, `Username`) 
            VALUES (NULL, '" . $idCat . "', '" . $authlist[$x]. "');";
            $addAuth = mysql_query($addAuthQuery);
        }
    }

    header('Location: Dashboard.php');
}
?>