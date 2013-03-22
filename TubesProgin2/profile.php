<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>
        <?php
        include 'header.php';
        ?>
        <?php
        if (connectDB()) {
            $queryProfile = "SELECT* FROM user WHERE Username='" . $_COOKIE['UserLogin'] . "'";
            $result = mysql_query($queryProfile);
            $data = mysql_fetch_array($result);
            
            $queryTaskDone = "SELECT TaskName FROM assignment, task WHERE task.IDTask = assignment.IDTask AND assignment.Username='" . $_COOKIE['UserLogin'] . "' AND task.Status = 'done' ";
            $queryTask = "SELECT TaskName FROM assignment, task WHERE task.IDTask = assignment.IDTask AND assignment.Username='" . $_COOKIE['UserLogin'] . "' AND task.Status = 'undone' ";        
        $uname= $data['Username'];
        $name= $data['Fullname'];
        $bday= $data['DateOfBirth'];
        $ava= $data['Avatar'];
        $email = $data['Email'];
        
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
                <div id="editP" onclick="editProfile();">
                    edit profile
                </div>
            </div>

            <div id="donelist">
                <?php
                $resultDone = mysql_query($queryTaskDone);

                while ($dataDone = mysql_fetch_array($resultDone)) {
                    $taskDone = $dataDone['TaskName'];
                    ?>        
                    <?php echo $taskDone; ?> <br/>
                    <?php
                }
                ?>
            </div>
            <div id="todolist">
                <?php
                $resultTask = mysql_query($queryTask);

                while ($dataTask = mysql_fetch_array($resultTask)) {
                    $taskUndone = $dataTask['TaskName'];
                    ?>        

                    <?php echo $taskUndone; ?> <br/>
                    <?php
                }
                $uname = $data['Username'];
                $name = $data['Fullname'];
                $bday = $data['DateOfBirth'];
                $ava = $data['Avatar'];
                $email = $data['Email'];
                ?>
            </div>
            <div id="biodata">
                <img id="foto" src=<?php echo $ava; ?>>
                <img id="badge" src="img/badge.png">
                <div id="biousername"> <?php echo $uname; ?></div>
                <div id="bioleft">
                    Full Name<br>
                    Date of Birth<br>
                    Email<br>
                </div>
                <div id="bioright">
                    <?php echo $name; ?> <br>
                    <?php echo $bday; ?> <br>
                    <?php echo $email; ?> <br>
                </div>
            </div>
        
        <div id='edit'>
                <p class="title">edit profile</p>
               
                Full Name: <input type="text" id="regname" onchange="Register();"><br>
                Birthday: <input type="date" id="regdate"><br>
                &nbsp; <br/>
                New Password:<br/> 
                <input type="password" id="regpassword1" onchange="Register();"><br>
                Confirm Password:<br/> 
                <input type="password" id="regpassword2" onchange="Register();"><br>
                &nbsp;<br/>
                Upload New Avatar: <input type="file" id="regfile"><br>
                &nbsp; &nbsp; <br/>
                <input type="submit" onclick="" value="save changes">
                <input type="submit" onclick="restoreP();" value="cancel">
            </div>

        </body>

        <?php
    } else { // jika koneksi database tidak berhasil
        die('Database connection error');
    }
    ?>

</html>
