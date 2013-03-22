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
    $type =  $_REQUEST['type'];
    
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
                echo $type;
            ?>
            <div id="rTask">
                <strong>Task</strong><br/>
                ----------------------------------------------------------------------------------------------------<br/>
                <?php 
                    while ($resTask = mysql_fetch_array($rTask)) {
                        $resultTask = $resTask['TaskName'];
                ?>
                            <a href="RinciTugas.php?IDTask=<?php echo $resTask['IDTask'] ?>"><?php echo $resultTask ?></a> <br/>             <br/>            
                <?php
                    }    
                ?>
            </div>
            
            <div id="rUser">
                <strong>Username</strong><br/>
                ----------------------------------------------------------------------------------------------------<br/>
<?php 
    while ($resUser = mysql_fetch_array($rUser)) {
        $resultUser = $resUser['Username'];
?>
            <a href="profile.php?user=<?php echo $uname ?>"><?php echo $uname ?></a> <br/>            
<?php
    }
    ?>
            </div>
        </div>
        
    </body>

<?php
    } else { // jika koneksi database tidak berhasil
    	die('Database connection error');
    }
?>
    
</html>
