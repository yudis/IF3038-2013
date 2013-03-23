<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<!DOCTYPE html>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
		<script>
			<?php
				require "config.php";
				
				//$user = mysqli_query($con,$sql);
				//$current_user = mysqli_fetch_array($user);
			?>
		</script>
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
                Task Name:
				<?php 
					$sql_task = "SELECT * FROM task WHERE pemilik = 'EndyDoank'";
					$task = mysqli_query($con,$sql_task);
					$current_task = mysqli_fetch_array($task);
					echo $current_task['name'];
					echo "<br />";
				?> 
				Task Status: 
				<div id="status_detail"></div>
				
                Attachment: 
                <div id="attachment"></div>
				
                Deadline: 
				<?php
					echo $current_task['deadline'];
				?>
				<br/>
                Assignee:
				<div id="assignee"></div>
				
                Tag: <div id="tag"></div>
				
                Comment:<br/>
                <div class="komentar">Dangerous criminal. Proceed with caution.</div><br/>
                <form>
                    <textArea></textarea>
                    <input type="button" name="submit" value="submit">
                </form>
                <br/><br/>
                <a onclick="showEdit();" class="button">edit</a><br/>
            </div>
			<script> 
				window.onload=update2;
			</script>
    </body>
</html>
