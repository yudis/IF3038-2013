<?php

require_once('config.php');
?>
<?php

if (connectDB()) {
    if (isset($_GET['username'])) {
        $username = $_GET['username'];
        
        $getUserQuery = 'SELECT * FROM user WHERE username=\'' . $username . '\'';

        $getUser = mysql_query($getUserQuery);
        $getUserRecordCount = mysql_num_rows($getUser);
        
        echo $getUserRecordCount;
    }
}
?>
