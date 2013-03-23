<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->

<?php
	//dummy here
	$id_task = 1;
	require "config.php";
	$task_sql = "SELECT * FROM task WHERE id_task = '$id_task'";
	$task = mysqli_query($con,$task_sql);
	$cur_task = mysqli_fetch_array($task);
	
	$assignee_sql = "SELECT username FROM assignee WHERE id_task = '$id_task'";
	$assignee_result = mysqli_query($con,$assignee_sql);
	$curassignee="";
	while($assignee = mysqli_fetch_array($assignee_result)){
		$curassignee.=$assignee['username'].",";
	}
	
	$curtag="";
	$tag_sql = "SELECT tag.name FROM tasktag,tag WHERE tasktag.id_task = '$id_task' and tag.id_tag = tasktag.id_tag";
	$tag_result = mysqli_query($con,$tag_sql);
	while($tag = mysqli_fetch_array($tag_result)){
		$curtag.=$tag['name'].",";
	}
	$curtag = substr($curtag,0,-1);
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
		<div class="tugas" id="edittask"><br/>
			<form id="editTaskForm" method="post" action="ubahtask.php?idtask=<?php echo $id_task;?>" enctype="multipart/form-data">
                Task Name: 
				<div class="nama">
					<?php echo $cur_task['name'];?>
				</div><br/>
                Deadline:
				<div class="deadline">
					<input type="date" name="deadline" value="<?php echo $cur_task['deadline'];?>" onchange="changeDeadline();" required><img id="validtask3" src="">
				</div><br/>
                Assignee: 
				<div class="asignee">
					<input type="text" value="<?php echo $curassignee;?>" autocomplete="off" name="Assignee" id="Assignee" onkeyup="showAssignee(this.value);">
				</div><br/>
				<div id="hasilsearchassigneeedit"></div>
                Tag: 
				<div class="tag">
					<input type="text" value="<?php echo $curtag;?>" id="tag" name="tag" autocomplete="off" onkeyup="showTag(this.value);"></div> <br/>
                <br/>
				<div id="hasilsearchtag"></div>
				<input type="submit" id="editbut" name="submit" value="Edit Task">
				<input type="button" id="cancelEditTask" name="cancelEditTask" value="Cancel" onclick="">
			</form>
		</div>
		<script type="text/javascript" src="script.js"></script>
		<script type="text/javascript" src="validationedittask.js"></script>
    </body>
</html>
