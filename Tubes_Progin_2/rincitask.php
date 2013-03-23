<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<?php
	include "login.php";
	$username = $_SESSION['username'];
	require "config.php";
	$id = $_GET["id"];
?>

<!DOCTYPE html>
<html>
    <head>
        <title>BANG!!!-DASHBOARD</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
        <script type="text/javascript" src="script.js"></script>
		<script>
		</script>
    </head>
    <body>
        <?php
			include "header.php";
		?>
		<div id="category">
			
		</div>
		<div id="wanted">
			<img src="img/kertas2.png">
		</div>
		<div class="tugas" id="rincitugas">
                Task Name:
				<?php 
					$sql_task = "SELECT * FROM task WHERE id_task='$id'";
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
				
                Comments:<br/>
                <div id="list_comment"></div>
				
				<b>Submit Your Comment: </b> <br/>
                <form id="submit_comment">
                    <textArea id="comment"></textarea>
                    <input type="button" name="submit" value="Submit" onClick="storeComment();">
                </form>
				
                <br/><br/>
                <a onclick="showEdit();" class="button">edit</a><br/>
            </div>
			<script>
				window.onload=generate_page();
				setInterval(function(){generate_page();},5000)
			</script>
    </body>
</html>
