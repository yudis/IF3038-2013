<?php

require_once('config.php');
?>
<?php

session_start();

if (connectDB()) {
    if (isset($_GET['username']) && isset($_GET['pass'])) {
        $username = $_GET['username'];
        $pass = $_GET['pass'];
        $getUserQuery = 'SELECT * FROM user WHERE username=\'' . $username . '\' and password=md5(\'' . $pass . '\')';
        $getUser = mysql_query($getUserQuery);
        $getUserRecordCount = mysql_num_rows($getUser);

        if ($getUserRecordCount == 1) {
            setcookie("UserLogin", $username, time() + 3600*24*30);
            $_SESSION['username'] = $username;
        }
        echo $getUserRecordCount;
    }
}
?>
