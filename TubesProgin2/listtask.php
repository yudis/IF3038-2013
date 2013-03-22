<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    if (isset($_GET['category'])) {
        $task = 'SELECT * FROM task WHERE IDCategory=\'' . $_GET['category'] . '\'';
        $result = mysql_query($task);
        $output = "";
        if ($result > 0) {
            while ($data = mysql_fetch_array($result)) {

                $output = $output . "<a class=\"listTugas\" onclick=\"showRinci();\"></a>";
            }
            $output = $output . "<a onclick='showBuat()' class='addTask'></a>";
            echo($output);
        }
    }
} else {
    die('Database connection error');
}
?>
