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
				<form id="status"> 
					<?php 
						if ($current_task['status'] == 0){ ?>
							<input type="radio" name="status_done" value="Done" onClick="UpdateDB();"> Done
							<input type="radio" name="status_notdone" value="NotDone" checked> Not Done <br/>
					<?php
						}else{ ?>
							<input type="radio" name="status" value="Done" checked> Done
							<input type="radio" name="status" value="NotDone"> Not Done <br/>
					<?php
						}
					?>
				</form>
				
                Attachment: 
                    <div class="attachment">
                        <a href="img/file.zip">file.zip</a><br/>
                        <a href="img/badge.png">picture.png</a><br/>
                    </div><br/>
                Deadline: 
				<?php
					echo $current_task['deadline'];
				?>
				<br/>
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
