<?php
	include "connect.php";
	$output="";
	$result4 = mysql_query("SELECT idcat FROM able WHERE username='".$_COOKIE['name']."'");
	while ($row4 = mysql_fetch_array($result4)){
		$result = mysql_query("SELECT * FROM task WHERE idcat='".$row4[0]."'");
		while ($row = mysql_fetch_array($result)){
			if ($row['status'] == 0){
				$status = "Belum Selesai";
			}
			else if ($row['status'] == 1) {
				$status = "Sudah Selesai";
			}
			$idtask = '\'' . $row['idtask'] . '\'';
			$output.='<div class="task_view left">
						<img src="../img/done.png" class="task_done_button" onclick="javascript:deleteTask('.$idtask.')" />
							<div class="left dynamic_content_left">Task Name</div>
							<div class="left dynamic_content_right"><a href="taskDetail.php?idtask='.$row['idtask'].'">'.$row['taskname'].'</a></div>
							<br><br>
							<div class="left dynamic_content_left">Deadline</div>
							<div class="left dynamic_content_right">'.$row['deadline'].'</div>
							<br><br>
							<div class="left dynamic_content_left">Status</div>						
							<div class="left dynamic_content_right">'.$status.' ';
			if ($row['status'] == 0)
				$output.='<a href="javascript:void(0)" onclick="javascript:markAsFinished('.$idtask.')">Mark as finished</a>';
							
			$output.=	'</div>
							<br><br>
							<div class="left dynamic_content_left">Tag</div>
							<div class="left dynamic_content_right">';
			$result2 = mysql_query("SELECT name FROM tag WHERE idtask='".$row['idtask']."'");
			while ($row2 = mysql_fetch_array($result2)){
				$output.=$row2[0].', ';
			}
			$output = substr($output, 0, -2);
			$result3 = mysql_query("SELECT catname FROM category WHERE idcat='".$row4[0]."'");
			$category = mysql_fetch_array($result3);
			$output.='</div>
							<br>
							<div class="task_view_category">'.$category[0].'</div>
							<br>
						</div>';
		}
	}
	echo $output;
?>