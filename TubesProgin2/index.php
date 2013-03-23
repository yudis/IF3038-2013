<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<?php
// Inialize session
session_start();

// Check, if user is already login, then jump to secured page
if(isset($_COOKIE['UserLogin'])){
    header('Location: Dashboard.php');
}
?>
<html>
    <head>
        <title>BANG! Where Bounty Hunters Meet</title>
        <link rel="stylesheet" type="text/css" href="css.css">
        <link href="mediaqueries.css" rel="stylesheet" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width; initial-scale=1.0">
    </head>
    <body>
        <div id="header">
            <div id="logo">
                <img src="img\Logo_Small2.png" alt="Logo & Tagline" onclick="Redirect();"></img>
            </div>
            <div id="login">
                Username &nbsp <input type="text" id="logusername"> &nbsp &nbsp 
                Password &nbsp <input type="password" id="logpassword"> &nbsp &nbsp 
                <div class="logbutton" onclick="logins();">Login</div>
            </div>
        </div>
        <div id="frontpage">
            <div id="register">
                <b>Sign yourself up now!</b><br>
                Username<br>
                Name<br>
                Date of birth<br>
                Password<br>
                Confirm Password<br>
                Email<br>
                Avatar<br>
            </div>
            <div id="inputregister">
                <form id="regForm" method="post" action="register.php" enctype="multipart/form-data">
                    <input type="text" id="regusername" name="regusername" pattern="^.{5,}$" required onkeyup="CheckValidity()"><img id="valid1" src=""><br>
                    <input type="text" id="regname" name="regname" pattern="^.+ .+$" required><img id="valid2" src=""><br>
                    <input type="date" id="regdate" name="regdate" onchange="dateChange();"><img id="valid7" src=""><br>
                    <input type="password" id="regpassword1" name="regpassword1" pattern="^.{8,}$" required><img id="valid3" src=""><br>
                    <input type="password" id="regpassword2" name="regpassword2" pattern="^.{8,}$" required><img id="valid4" src=""><br>
                    <input type="text" id="regemail" name="regemail" pattern="^.+@.+\...+$" required><img id="valid5" src=""><br>
                    <input type="file" id="regfile" name="regfile" onchange="checkImage();"><img id="valid6" src=""><br>
                    <input type="submit" id="regbutton" name="submit" value="Register" disabled="disabled">
                </form>
            </div>
            <img src="img\register.png" id="fpposter" alt="Poster"></img>
            <img src="img\Logo_Big2.png" id="fplogo" alt="BigLogo"></img>
            <img src="img\parchment.jpg" id="fpbackground" alt="Background"></img>
        </div>
    </body>

    <script type="text/javascript" src="script.js"></script>
    <script type="text/javascript" src="ajax.js"></script>
</html>
