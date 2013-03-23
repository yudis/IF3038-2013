<?php
    session_start();
    if (isset($_SESSION['username']) || !empty($_SESSION['username'])){
        header('Location: dashboard.php');
    }
?>
<!DOCTYPE html>
<html>
    <head>
        <title>BANG!</title>
        <link rel="stylesheet" type="text/css" href="css.css"/>
        <link rel="stylesheet" href="mediaqueries.css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width; initial-scale=1.0"/>
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>
        <img src="img/parchment.jpg" id="homepage-bg" alt="homepage-bg">
        <div id="homepage-header">
            <div id="text-logo">
                <img src="img/logo_small.png" alt="Logo" href="dashboard.html" title="Home"/>
            </div>	
            <div id="login">
                <form name="login" method="POST">
                    Username<input type="text" id="logusername" name="logusername" size="15"/> 
                    Password<input type="password" id="logpassword" name="logpassword" size="10"/> 
                    <input type="button" value="Login" onclick="logindeh();"/>
                </form>
            </div>
        </div>
        <div id="homepage-content">
            <!--<div id="pic-logo">
                <img src="img/logo_big.png" alt="Frontpic"/>
            </div>-->
            
            <div id="register">
                <img src="img/register.png" id="reg-bg" alt="reg-bg"/>
                <big><b>Sign yourself up now!</b></big><br>
                <form name="regform" method="POST" action="register.php" enctype="multipart/form-data">
                    Username<input type="text" id="regusername" name="regusername" onchange="regFill();"/><br>
                        <small id="errusernamea">Username should be at least 5 characters long</br></small>
                        <small id="errusernameb">Username and password cannot be identical</br></small>
                        <small id="errusernamec">This username has already been used</br></small>
                    Name<input type="text" id="regname" name="regname" onkeyup="regFill();"/><br>
                        <small id="errname">Name should be constructed by two or more words separated by space</br></small>
                    Birth<input type="date" id="regdate" name="regdate"/><br>
                    Password<input type="password" id="regpassword1" name="regpassword1" onkeyup="regFill();"/><br>
                        <small id="errpassword1a">Password should be at least 8 characters long</br></small>
                        <small id="errpassword1b">Username and password cannot be identical</br></small>
                    Confirm Password<input type="password" id="regpassword2" name="regpassword2" onkeyup="regFill();"/><br>
                        <small id="errpassword2">Confirmed password and password are not the same</br></small>
                    Email<input type="text" id="regemail" name="regemail" onkeyup="regFill();"/><br>
                        <small id="erremaila">There should be at least one character before '@' character</br></small>
                        <small id="erremailb">There should be at least one character between '@' and '.' character</br></small>
                        <small id="erremailc">There should be at least two characters after '.' character</br></small>
                        <small id="erremaild">This email has already been used</br></small>
                    Avatar<input type="file" id="regfile" name="regfile" accept="image/jpg, image/jpeg" onchange="regFill();"/><br>
                    <input id="regbutton" type="submit" value="Register" disabled/>
                </form>
            </div>    
        </div>
    </body>
</html>

