<?php
session_start();
require_once 'db.php';
?>

<!DOCTYPE html>
<html>

	<head>
		<title>BANG! - Dashboard</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css.css" media="screen" />
		<link rel="stylesheet" type="text/css" href="result_page.css"/>
		<script type="text/javascript" src="script.js"></script>
	</head>
	<body>
		<?php
		include_once "header.php";

		$opt = $_POST['options'];
		$box = $_POST['Search'];

		$con = mysql_connect("localhost", "progin", "progin");
		mysql_select_db("progin", $con);
		?>
		<div id = "wall">
			<div id="category_wall">
				<?php
				if ($opt == 'c' || $opt=='a') {
					$sq1 = "SELECT id_category,name FROM category WHERE id_category = '" . $box . "'";
					$re1 = mysql_query($sq1);
					while($v = mysql_fetch_array($re1)){
						echo $v[0] . "<br/>";
						echo $v[1] . "<br/>";						
					}
				}
				?>
			</div>
			<div id="user_wall">
				<?php
				if ($opt == 'u' || $opt=='a') {
					$sq1 = "SELECT username,fullname,avatar FROM user WHERE username = '" . $box . "'";
					$re1 = mysql_query($sq1);
					while ($v = mysql_fetch_array($re1)) {
						echo "<a href = 'profile.php?userprofile=".$v[0]."'>";
						echo $v[0] . "<br/>";
						echo "</a>";
						echo $v[1] . "<br/>";
						echo "<img src='".$v[2]."'></img>";
					}
				}
				?>
			</div>
			<div id="task_wall">
				<?php
				if ($opt == 't' || $opt=='a') {
					$sq1 = "SELECT t.id_task,t.name,t.deadline,t.status FROM task AS t
					JOIN utrelation AS u
					ON t.id_task = u.id_task
					WHERE t.id_task = '".$box."' AND u.username='".$_SESSION['username']."'";
					$re1 = mysql_query($sq1);
					print mysql_error($con);
					while($v = mysql_fetch_array($re1)){
						echo "<a href = 'dashboard.php?seektask=".$v[0]."'>";
						echo $v[0] . "<br/>";
						echo "</a>";
						echo $v[1] . "<br/>";
						echo $v[2] . "<br/>";
						echo "<div id='statusTask".$v[0]."'>".$v[3] . "<br/>";
						if($v[3]=='T'){
							echo "<input type='checkbox' name='task".$v[0]."' id='task".$v[0]."'onclick='changeTaskStatus(".$v[0].",this.checked)' checked='checked'/>";		
						}else{
							echo "<input type='checkbox' name='task".$v[0]."' id='task".$v[0]."'onclick='changeTaskStatus(".$v[0].",this.checked)'/>";						
						}
						echo "</div>";				
					}
				}
				?>
			</div>
		</div>
	</body>
</html>

