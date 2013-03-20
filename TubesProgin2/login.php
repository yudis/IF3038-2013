<?php include ('config.php'); ?>
<?php
    if(isset($_GET['username']) && isset($_GET['pass'])){
        $username = $_GET['username'];
        $pass = $_GET['pass'];
        
        $getUserQuery = 'SELECT * FROM `user` WHERE username='. $username .' and password=md5('. $pass .')';
        $getUser = mysql_query($getUserQuery);
        $getUserRecordCount = mysql_num_rows($getUser);
        
        echo $getUserRecordCount;
    }
?>
