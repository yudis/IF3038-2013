<?php

require_once('config.php');
?>
<?php

if (connectDB()) {
    if (isset($_GET['email'])) {
        $email = $_GET['email'];
        
        $getUserQuery = 'SELECT * FROM user WHERE email=\'' . $email . '\'';

        $getUser = mysql_query($getUserQuery);
        $getUserRecordCount = mysql_num_rows($getUser);
        
        echo $getUserRecordCount+" ";
    }
}
?>
