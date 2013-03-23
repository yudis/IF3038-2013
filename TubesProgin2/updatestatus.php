<?php

require_once('config.php');
?>

<?php

session_start();

if (connectDB()) {
    $CheckStatus = $_GET["status"];
    $IDTask = $_GET["IDTask"];

    $GetStatusQuery = "SELECT * FROM task WHERE IDTask=" . $IDTask . ";";
    $GetStatus = mysql_query($GetStatusQuery);

    $result = mysql_fetch_array($GetStatus);

    $UpdateStatusQuery = "UPDATE  `task` SET  `Status` ='done' WHERE IDTask=" . $IDTask . ";";
    $UpdateStatus = mysql_query($UpdateStatusQuery);    
    
    if ($result[3] === "done") {
        if ($CheckStatus=="true") {
            $check=true;
        } else {
            $check=false;
            $UpdateStatusQuery = "UPDATE  `task` SET  `Status` ='undone' WHERE IDTask=" . $IDTask . ";";
            $UpdateStatus = mysql_query($UpdateStatusQuery);
        }
    } else {
        if ($CheckStatus=="true") {
            $check=true;
            $UpdateStatusQuery = "UPDATE  `task` SET  `Status` ='done' WHERE IDTask=" . $IDTask . ";";
            $UpdateStatus = mysql_query($UpdateStatusQuery);
        } else {
            $check=false;
        }
    }

    if ($check) {
        echo' DONE <input type="checkbox" id="checkboxstatus" checked onclick="changestatus(' . $IDTask . ')" >';
    } else {
        echo' DONE <input type="checkbox" id="checkboxstatus" onclick="changestatus(' . $IDTask . ')" >';
    }
}
?>