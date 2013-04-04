<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="viewport" content="width=device-width; initial-scale=1.0" />
        <title>Welcome to Todolist!</title>
        <link rel="stylesheet" type="text/css" href="styles/index.css" />
        <link rel="stylesheet" type="text/css" href="styles/mediaqueries.css" />
        <script type="text/javascript" src="scripts/helper/ajaxhelper.js"></script>
        <script type="text/javascript" src="scripts/helper/helper.js"></script>
        <script type="text/javascript" src="scripts/helper/json2.js"></script>
        <script type="text/javascript" src="scripts/helper/datetimepicker_css.js"></script>
        <script type="text/javascript" src="scripts/index.js"></script>
    </head> 
    <body>
        <div class="page">
            <header class="content">
                <nav>
                    <div class="logo"><a href="#home"><img alt="Home" src="images/logo.png" /></a></div>
                    <div id="slogan">Share your to do list with everyone!</div>
                    <div id="loginwrapper">
                        <form name="login" onsubmit="return false;">
                            <div id="login_username">
                                Username <br>
                                <input type="text" class="textbox" name="username" value="" />
                            </div>
                            <div id="login_password">
                                Password <br>
                                <input type="password" class="textbox" name="password" value="" />
                            </div>
                            <div id="login_button">
                                <input type='submit' value='Login' class="button" onclick="return loginX();" />
                            </div>
                        </form>
                    </div>
                </nav>
            </header>
            <div class="content">
                <div id="leftcolumn">
                    <h1>So How Does Our System Work?</h1>
                    Let's see the brief explanation from these video below.. 
                    <div id="slider">
                        <div><img src="images/image-1.jpg" id="slider_picture" alt="" /></div>
                        <button onclick="back()" class="slider_button">Back</button>
                        <button onclick="next()" class="slider_button">Next</button>
                    </div>
                    <h2>It's easy, beautiful, important to have, and <br /> the best part is.. it's FREE to use!</h2>
                    What are you waiting for?<br />Join our system and start sharing your to do list right now!<br />
                    If you find any trouble registering, please contact us asap!
                </div>
                <div id="rightcolumn">
                    <div id="registerwrapper">
                        <h1>Register Now!</h1>
                        It only takes you a few more steps!
                        <form name="registration" onsubmit="return validateForm();" method="post" enctype="multipart/form-data" action="register">
                            <div class="wrapper">
                                <input type="text" class="regbox" id="uname" name="uname" placeholder="Username.." required="required" onkeyup="validateUName();" onchange="validateUName();" />
                            </div>
                            <div class="wrapper">
                                <input type="password" class="regbox" id="pwd" name="pwd" placeholder="Password.." required="required" onkeyup="validatePassword();" onchange="validatePassword();"/>
                            </div>
                            <div class="wrapper">
                                <input type="password" class="regbox" id="pwd_confirm" name="pwd_confirm" placeholder="Confirm Password.." required="required" onkeyup="validateRePassword();" onchange="validateRePassword();"/>
                            </div>
                            <div class="wrapper">
                                <input type="text" class="regbox" id="name" name="name" placeholder="Full Name.." required="required" onkeyup="validateFullName();" onchange="validateFullName();" />
                            </div>
                            <div class="wrapper">
                                <input type="email" class="regbox" id="email" name="email" placeholder="Your Email.." required="required" onkeyup="validateEmail();" onchange="validateEmail();" />
                            </div>
                            <div class="wrapper">
                                <input type="text" class="regbox" id="bday" name="bday" required="required" placeholder="Birthday (yyyy-mm-dd)" onkeyup="validateBday();" onchange="validateBday();" /><a href="#" onclick="NewCssCal('bday', 'yyyyMMdd', 'dropdown', false, 24, false, 'past');
                                return false;"><img src="./images/cal/cal.gif" width="16" height="16" border="0" alt="Pick a date"></a>
                            </div>
                            <div class="wrapper">
                                <p class="regtext">Profile Image:<br /></p><input type="file" id="ava" name="ava" accept="image/*" required="required"  onkeyup="validateAvatar();" onchange="validateAvatar();" />
                            </div>
                            <p>By clicking Sign Up, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use. </p>
                            <input type="submit" class="button" id="register" value="Sign Up" disabled="true" />
                        </form>
                    </div>
                </div>
            </div>
            <footer class="content">
                This website is created solely for the purpose of fulfilling our college task.<br />
                IF3094 - Pemrograman Internet.
                <br/>
                &copy; 2013 Ranger Enemy - Edward Samuel Pasaribu, Felix Terahadi, Wilson Fonda
            </footer>
        </div>
    </body> 
</html>
