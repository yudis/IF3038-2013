<%-- 
    Document   : header
    Created on : Apr 6, 2013, 8:08:53 AM
    Author     : VAIO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Header</title>
        <link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="javascript/header.js"></script>
    </head>
    <body onLoad="initialize()">
        <div id="header-logo"><a href="dashboard.php"><img src="image/logo.png" width="100px" height="60px"/></a></div>
        <div id="header-title"><a href="dashboard.php"><img src="image/title.png" width="250px" height="80px"/></a></div>
        <div id="header-link"><a href="dashboard.php"><b>Go To Dashboard</b></a></div>
        <div id="header-right-side">
                <div id="header-right-search">
                        <form action="search_result.php" method="post">
                                <div id="user-filtering">
                                        <select name="filtering" id="filtering">
                                                <option value="1">None</option>
                                                <option value="2">Email</option>
                                                <option value="3">Full Name</option>
                                                <option value="4">Birthdate</option>
                                        </select>  
                                </div>
                                <div id="searching-header">
                                        <select name="modesearch" id="modesearch" onChange="filter()">
                                                        <option value="1">All</option>
                                                        <option value="2">User</option>
                                                        <option value="3">Category</option>
                                                        <option value="4">Task</option>
                                                </select>    
                                <input type="text" name="search_text" id="search_text" list="searching-auto" value="" onKeyUp="checkHeaderValidation()" />
                                <input type="submit" value="Search"/>
                                <div id="list-search"></div> 
                                </div>
                        </form>
                </div>
                <div id="header-right-user">
                <?php // Create connection
                                $con=mysqli_connect("localhost","root","","progin_405_13511601");
                                // Check connection
                                if (mysqli_connect_errno($con))
                                {
                                        echo "Failed to connect to MySQL: " . mysqli_connect_error();
                                }
                                $username = $_SESSION['userlistapp'];
                                $query = "SELECT * FROM user WHERE username='$username'";
                                $result = mysqli_query($con,$query);
                                $row = mysqli_fetch_array($result);
                        ?>
                        <div id="header-photo"><a href="profile.php?username=<?php echo $row['username']?>"><img id="photo" src="../avatar/<?php 
                                echo $row['avatar'];
                        ?>" width="60" height="60"/></a></div>
                        <div id="header-profile">
                                You logged as, <b><?php echo $_SESSION["userlistapp"]?></b>
                                <ul>
                                        <li><a href="profile.php?username=<?php echo $_SESSION["userlistapp"]?>">Go to Profile</a></li>
                                        <li><a href="../php/signout.php">Sign Out</a></li>
                                </ul>
                        </div>
                </div>
        </div>
    </body>
</html>
