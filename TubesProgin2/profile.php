<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<?php
	require_once('config.php');
?>

<html>
    <head>
        <title>BANG! - Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>

<?php
if(connectDB()){
	$queryProfile = "SELECT* FROM user";
	$result = mysql_query($queryProfile);
        if ($result > 0) {        
?>        
        
        <header>
            <a href="dashboard.php" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
            <div id="dashboard"><a title="Go to Dashboard" href="dashboard.php">Dashboard</a></div>
            <div id="profile"><a title="Go to Profile" href="profile.php">My Profile</a></div>
            <div id="logout"><a title="Log out from here" href="index.php">Log Out</a></div>
            <form id="search">
                <input type="text" name="Search" id="box">
                <input type="submit" value="Search">
            </form>
        </header>
        <div id="panel"></div>

        <div id="donelist">
            Ridho Ramadan<br/>
            Daniel Ginting<br/>
            Timotius Nugraha<br/>
            Nicolas Rio<br/>
            Aditya Agung
        </div>
        <div id="todolist">
            Afif Alhawari<br/>
            Flora Monica <br/>
            Nugroho Satrijandi<br/>
        </div>
<?php
            $data = mysql_fetch_array($result);
            $uname= $data['Username'];
            $name= $data['Fullname'];
            $bday= $data['DateOfBirth'];
            $ava= $data['Avatar'];
            $email = $data['Email'];
        }
?>

        <div id="biodata">
            <img id="foto" src="img/foto_anonim.png">
            <img id="badge" src="img/badge.png">
            <div id="biousername"><script>document.write(localStorage.username);</script></div>
            <div id="bioleft">
                User Name<br>
                Full Name<br>
                Date of Birth<br>
                Email<br>
            </div>
            <div id="bioright">
                <?php echo $uname; ?> <br>
                <?php echo $name; ?> <br>
                <?php echo $bday; ?> <br>
                <?php echo $email; ?> <br>
            </div>
        </div>
        
    </body>

<?php
    } else { // jika koneksi database tidak berhasil
    	die('Database connection error');
    }
?>
    
</html>
