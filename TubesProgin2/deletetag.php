<?php

require_once('config.php');
?>

<?php

session_start();
if (connectDB()) {
    $IDTag = $_GET['IDTag'];
    $IDTask = $_GET['IDTask'];

    $DeleteTagQuery = "DELETE FROM tasktag WHERE IDTaskTag='" . $IDTag . "';";
    $DeleteTag = mysql_query($DeleteTagQuery);

    
    
    $TagQueryText = "SELECT * FROM tasktag,tag WHERE IDTask=" . $IDTask . " AND tasktag.IDTag=tag.IDTag";
    $TagQuery = mysql_query($TagQueryText);
    while ($result = mysql_fetch_array($TagQuery)) {
        echo'<a href="" class="asignee">' . $result[4] . '</a> <img src="img/salah.png" alt="" onclick="deletetag(' . $result[0] . ',' . $IDTask . ')" /><br/> ';
    }
    echo'<br/>';
}
?>