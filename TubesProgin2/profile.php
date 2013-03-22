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
        
        $usrname = $_GET['user'];
        ?>
        <?php
        if (connectDB()) {
            $queryProfile = "SELECT* FROM user WHERE Username='".$usrname."'";
            $result = mysql_query($queryProfile);
            $data = mysql_fetch_array($result);
            
            $queryTaskDone = "SELECT TaskName FROM assignment, task WHERE task.IDTask = assignment.IDTask AND assignment.Username='".$usrname."' AND task.Status = 'done' ";
            $queryTask = "SELECT TaskName FROM assignment, task WHERE task.IDTask = assignment.IDTask AND assignment.Username='" .$usrname. "' AND task.Status = 'undone' ";        
        $uname= $data['Username'];
        $name= $data['Fullname'];
        $bday= $data['DateOfBirth'];
        $ava= $data['Avatar'];
        $email = $data['Email'];
        
?>                  

            <div id="panel">
                <?php
                if ($uname === $_COOKIE['UserLogin']) {
                    echo '<div id="editP" onclick="editProfile();">';
                    echo 'edit profile';
                    echo '</div>';
                }
                ?>
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
               
                <form id="regForm" method="post" action="register.php" enctype="multipart/form-data">
                    Full name: <input type="text" id="regname" name="regname" pattern="^.+ .+$" required><img id="valid2" src=""><br>
                    Birthdate: <input type="date" id="regdate" name="regdate" onchange="dateChange();"><img id="valid7" src=""><br>
                    New Password:<br/><input type="password" id="regpassword1" name="regpassword1" pattern="^.{8,}$" required><img id="valid3" src=""><br>
                    Confirm new password:<br/><input type="password" id="regpassword2" name="regpassword2" pattern="^.{8,}$" required><img id="valid4" src=""><br>
                    Upload new avatar: <br/><input type="file" id="regfile" name="regfile" onchange="checkImage();"><img id="valid6" src=""><br>
                    <input type="submit" onclick="restoreP();" value="save changes">
                    <input type='submit' onclick="restoreP();" value="cancel">
                </form>

            </div>

        </body>

        <?php
    } else { // jika koneksi database tidak berhasil
        die('Database connection error');
    }
    ?>

</html>
