<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<?php
	require_once('config.php');

// Inialize session
session_start();

// Check, if username session is NOT set then this page will jump to login page
if (!isset($_SESSION['username'])) {
header('Location: index.php');
}

?>
<html>
    <head>
        <title>BANG! - Search Result</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    
    <body>        
    <?php
    include 'header.php';
    $search = $_POST['searchquery'];
    $type =  $_POST['tipe'];
    
    if(connectDB()){
            
            $queryProfile = "SELECT* FROM user WHERE Username='".$_SESSION['username']."'";
            $result = mysql_query($queryProfile);
            $data = mysql_fetch_array($result);
            $uname= $data['Username'];
            $ava= $data['Avatar'];

            if ($type = 'All') {
                $queryTask = "SELECT * FROM task WHERE TaskName ='".$search."'";
                $queryCat = "SELECT * FROM category WHERE CategoryName ='".$search."'";
                $queryUser = "SELECT * FROM user WHERE Username ='".$search."'";
                $rTask = mysql_query($queryTask);
                $rCat = mysql_query($queryCat);
                $rUser = mysql_query($queryUser);
            } 
            else if ($type= 'Task') {
                $queryTask = "SELECT * FROM task WHERE TaskName ='".$search."'";
                $rTask = mysql_query($queryTask);
            }
            else if ($type = 'Category') {
                $queryCat = "SELECT * FROM category WHERE CategoryName ='".$search."'";
                $rCat = mysql_query($queryCat);
            }
            else if ($type = 'User') {
                $queryUser = "SELECT * FROM user WHERE Username ='".$search."'";
                $rUser = mysql_query($queryUser);
            }
    ?>        
        
        
        <div id="panel">
            search result for: <br/>
            <i><?php echo $search ?></i>
        </div>
        
        <div id="results">
            <?php
                if (($type = 'All') or ($type = 'Category')) {
                    echo '<div id="rCategory">
                    <strong>Category</strong><br/>
                    ----------------------------------------------------------------------------------------------------<br/>';
                    while ($resCat = mysql_fetch_array($rCat)) {
                            if ($resCat != NULL) {
                            $resultCat = $resCat['CategoryName'];
                            echo $resultCat;
                            echo '<br/>';            
                            } else {    
                                echo 'NONE';
                            }
                    }  
                    echo '</div>';
                }
            ?>
            <?php
                if (($type = 'All') or ($type = 'Task')) {
                    echo '<div id="rTask">
                    <strong>Task</strong><br/>
                    ----------------------------------------------------------------------------------------------------<br/>';
                    while ($resTask = mysql_fetch_array($rTask)) {
                            if ($resTask != NULL) {
                            $resultTask = $resTask['TaskName'];
                            echo $resultTaskk;
                            echo '<br/>';            
                            } else {    
                                echo 'NONE';
                            }
                    }  
                    echo '</div>';
                }
            ?>
            
            <?php
                if (($type = 'All') or ($type = 'Username')) {
                    echo '<div id="rUser">
                    <strong>User</strong><br/>
                    ----------------------------------------------------------------------------------------------------<br/>';
                    while ($resUser = mysql_fetch_array($rUser)) {
                            if ($resUser != NULL) {
                            $resultUser = $resUser['Username'];
                            ?>
                    <a title="Go to Profile" href="profile.php?user=<?php echo $resultUser ?>"><?php echo $resultUser ?></a>
            <?php
                            echo '<br/>';            
                            } else {    
                                echo 'NONE';
                            }
                    }  
                    echo '</div>';
                }
            ?>
        </div>
        
    </body>

<?php
    } else { // jika koneksi database tidak berhasil
    	die('Database connection error');
    }
?>
    
</html>
