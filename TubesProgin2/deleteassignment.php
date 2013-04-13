<?php

require_once('config.php');
?>

<?php

session_start();
if (connectDB()) {
    $IDAssignment = $_GET['IDAssignment'];
    $IDTask = $_GET['IDTask'];

    $DeleteAssigneeQuery = "DELETE FROM assignment WHERE IDAssignment='".$IDAssignment."';";
    $DeleteAssignee = mysql_query($DeleteAssigneeQuery);
    
    $AssigneeQueryText = "SELECT * FROM assignment WHERE IDTask=" . $IDTask . ";";
    $AssigneeQuery = mysql_query($AssigneeQueryText);
    while ($result = mysql_fetch_array($AssigneeQuery)) {
        if ($result[1] != $_COOKIE['UserLogin']) {
            echo'<a href="profile.php?user=' . $result[1] . '" class="asignee">' . $result[1] . '</a>  <img src="img/salah.png" alt="" onclick="deleteassignee(' . $result[0] . ',' . $IDTask . ')" /><br/> ';
        }
    }
    echo'<br/>';
}
?>