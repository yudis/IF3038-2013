<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<?php
	//$username = $_SESSION['username'];
	$username = "EndyDoank";
	
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
        <script type="text/javascript" src="script.js"></script>
    </head>
    <body>
        <?php
			include "header.php";
		?>
		<div id="category">
			<div class="kategori" onclick="showList();"><a>Fraud</a></div>
			<div class="kategori" onclick="showList3();"><a>Robbery</a></div>
			<div class="kategori" onclick="showList2();"><a>Gambling</a></div>
			<div class="kategori" onclick="showList();"><a>Public Drunkenness</a></div>
			<div class="kategori" onclick="showList3();"><a>Drug Law Violation</a></div>
			<div class="kategori" onclick="showList2();"><a>Motor Vehicle Theft</a></div>
		</div>
        <div id="addCat">
            <a onclick="addCategory();">+ category</a>
        </div>
		<div id="wanted">
			<img src="img/kertas2.png">
		</div>
		<div class="tugas" id="buattugas"><br/>
			<form id="createForm" method="post" action="create.php" enctype="multipart/form-data">
                Name: <div class="nama"><input type="text" id="namaTask" name="namaTask"></div><br/>
                Attachment: <div class="attachment"><input type="file"></div><br/>
                Deadline: <div class="deadline"><input type="date"></div><br/>
                Assignee: <div class="asignee"><input type="text"></div><br/>
                Tag: <div class="tag"> <input type="text"></div> <br/>
                <br/>
				<input type="submit" id="regbutton" name="submit" value="Register" disabled="disabled">
			</form>
		</div>
    </body>
</html>
