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
    if(connectDB()){
            $search = "frilla";
            $queryProfile = "SELECT* FROM user WHERE Username='".$_SESSION['username']."'";
            $result = mysql_query($queryProfile);
            $data = mysql_fetch_array($result);

            $uname= $data['Username'];
            $ava= $data['Avatar'];

            $queryTask = "SELECT * FROM task WHERE TaskName ='".$search."'";
            $queryCat = "SELECT * FROM category WHERE CategoryName ='".$search."'";
            $queryUser = "SELECT * FROM user WHERE Username ='".$search."'";
            $rTask = mysql_query($queryTask);
            $rCat = mysql_query($queryCat);
            $rUser = mysql_query($queryUser);
    ?>        
        
        <header>
            <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
            <div id="dashboard"><a title="Go to Dashboard" href="dashboard.php">Dashboard</a></div>
            
            <div id="profile"><a title="Go to Profile" href="profile.php"><?php echo $uname ?></a></div>
            <div id="logout"><a title="Log out from here" href="logout.php">Log Out</a></div>
            <form id="search">
                <input type="text" name="Search" id="box">
                <select>
                    <option> All </option>   
                    <option> Category </option>
                    <option> Task </option>
                    <option> Username </option>
                </select>
                <input type="submit" value="Search">
            </form>
            <img id="smallava" src="<?php echo $ava?>" />
        </header>
        
        <div id="panel">
            search result for: <br/>
            <i><?php echo $search ?></i>
        </div>
        
        <div id="results">
            <div id="rCategory">
                <strong>Category</strong><br/>
                ----------------------------------------------------------------------------------------------------<br/>
<?php 
    while ($resCat = mysql_fetch_array($rCat)) {
        $resultCat = $resCat['CategoryName'];
?>
            <?php echo $resultCat; ?> <br/>            
<?php
    }    
?>  
            </div>
            <div id="rTask">
                <strong>Task</strong><br/>
                ----------------------------------------------------------------------------------------------------<br/>
<?php 
    while ($resTask = mysql_fetch_array($rTask)) {
        $resultTask = $resTask['TaskName'];
?>
            <?php echo $resultTask; ?> <br/>            
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
            <?php echo $resultUser; ?> <br/>            
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
