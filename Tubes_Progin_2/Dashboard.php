<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<?php
	include "login.php";
	$username = $_SESSION['username'];
	
	require "config.php";
	
	$sql = "SELECT * FROM user WHERE username = '$username'";
	$user = mysqli_query($con,$sql);
	$current_user = mysqli_fetch_array($user);
?>

<!DOCTYPE html>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        
    </head>
    <body>
        <?php
			include "header.php";
		?>

            <div id="category">
            </div>
        <div id="addCat">
            <a onclick="addCategory();">+ category</a>
        </div>
        
        <div id ="listtugas" class="list">
        </div>
        
        <div id='add'>
            Category Name:<br/> <input type='text' id='cate'><br/>
            User:<br/> <input type='text'><br/>
            <input type="submit" onclick="addCat();" value="create">
            <input type="submit" onclick="restore();" value="cancel">
        </div>
		<script type="text/javascript" src="script.js"></script>
		<script>		
			window.onload=update;
			setInterval(function(){update();},5000)
		</script>
    </body>
</html>
