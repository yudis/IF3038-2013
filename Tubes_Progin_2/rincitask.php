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
		<div class="tugas" id="rincitugas">
                Name: Nama Tugas <br/>
                Attachment: 
                    <div class="attachment">
                        <a href="img/file.zip">file.zip</a><br/>
                        <a href="img/badge.png">picture.png</a><br/>
                    </div><br/>
                Deadline: 17-12-2014<br/>
                Assignee: <a href="" class="asignee">Timo</a>, <a href="" class="asignee">Stefan</a>, <a href="" class="asignee">Frilla</a><br/>
                Tag: <a href="" class="tag">dangerous</a>, <a href="" class="tag">novice</a> <br/>
                <br/>Comment:<br/>
                <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
                <form>
                    <textArea></textarea>
                    <input type="button" name="submit" value="submit">
                </form>
                <br/><br/>
                <a onclick="showEdit();" class="button">edit</a><br/>
            </div>
    </body>
</html>
