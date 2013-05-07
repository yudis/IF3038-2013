<?php
    require_once("db.php");
    session_start();
    
    
?>
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
			include_once("header.php");
			if(isset($_GET['userprofile'])){
				$userprofile = $_GET['userprofile'];
				$result = ProginDB::getInstance()->query("SELECT * FROM user WHERE username = '".$userprofile."'");
				$row = mysqli_fetch_array($result, MYSQLI_ASSOC);
			}else{
				$userprofile = $_SESSION['username'];
			}
		?>
        <div id="panel">
            <img src="img/bg.jpg" id="panelbg"/>
            <img src="
                <?php 
                    echo $row['avatar'];
                ?>" id="avatarbig"
            />
            <img id="badge" src="img/badge.png">
            <div id="biousername"><?php echo ($userprofile); ?></div>
            <div id="biodata">
                Name<b><?php echo ": ".$row['fullname'];?></b></br>
                Birth<b><?php echo ": ".$row['dob'];?></b></br>
                Email<b><?php echo ": ".$row['email'];?></b></br></br>
                <?php
                	if($userprofile==$_SESSION['username']){
                		echo "<input type='button' value='Edit Profile' onclick='showForm();'/>";
                	}
                ?>
            </div>
            <form id="bioform" name="bioform" method="POST" action="editprofile.php" enctype="multipart/form-data">
                <div id="popoutovl"></div>
                <div id="popoutbg">
                    Name<input type="text" id="bioname" name="bioname" size="10" value='<?php echo $row['fullname'];?>' onchange="bioFill();"/></br>
                        <small id="errname">Name should be constructed by two or more words separated by space</br></small>
                    Avatar<input type="file" id="biofile" name="biofile" accept="image/jpg, image/jpeg" onchange="bioFill();"/></br>
                    Birth<input type="date" id="biodate" name="biodate" size="10" value='<?php echo $row['dob'];?>'/></br>
                    Password<input type="password" id="biopassword1" name="biopassword1" size="10" value='<?php echo $_SESSION['password']; ?>' onchange="bioFill();"/></br>
                        <small id="errpassword1">Password should be at least 8 characters long</br></small>
                    Confirm Password<input type="password" id="biopassword2" name="biopassword2" size="10" value='<?php echo $_SESSION['password']; ?>' onchange="bioFill();"/></br>
                        <small id="errpassword2">Confirmed password and password are not the same</br></small>
                    </br><input type="submit" id="biobutton" value="Done Editing" onclick="hideForm();"/>
                </div>
            </form>
        </div>
        <div id="wall">
            <div id="donelist">
                <?php 
                    ProginDB::getInstance()->donelist_display($userprofile);
                ?>
            </div>
            <div id="todolist">
                <?php 
                    ProginDB::getInstance()->todolist_display($userprofile);
                ?>
            </div>
        </div>
        <?php
            /*if(isset($_GET['edited']))
            {
                echo "<script type=\"text/javascript\">".
                    "alert('Your profile has been successfully edited');".
                    "</script>";
            } else {
                echo "<script type=\"text/javascript\">".
                    "alert('No changes have been made');".
                    "</script>";
            }*/
        ?>
    </body>
</html>
