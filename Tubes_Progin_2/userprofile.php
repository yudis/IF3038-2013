<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<?php
	require "config.php";
	include "login.php";
	$username = $_SESSION['username'];
	$userid = $_GET['usr'];
?>

<!DOCTYPE html>
<html>
    <head>
        <title>BANG! - Profile</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css.css" media="screen" />
		<script>
			<?php
				//dummy here
				$usersql = "SELECT * FROM user WHERE username = '$userid'";
				$userprofile = mysqli_query($con,$usersql);
				$thisuser = mysqli_fetch_array($userprofile);
				$sql_notdone_list = "SELECT task.name FROM task, assignee WHERE status='0' and username='$userid' and assignee.id_task = task.id_task;";
				$not_done_list = mysqli_query($con,$sql_notdone_list);
				$sql_done_list = "SELECT task.name FROM task, assignee WHERE status='1' and username='$userid' and assignee.id_task = task.id_task;";
				$done_list = mysqli_query($con,$sql_done_list);
			?>
		</script>
    </head>
    <body>
        <?php
			require "header.php";
		?>
        <div id="panel"></div>
        <div id="donelist">
            <?php
				$done_list = mysqli_query($con,$sql_done_list);
				while($done_row = mysqli_fetch_array($done_list)){
					echo $done_row['name'];
					echo "<br />";
				}
			?>
        </div>
        <div id="todolist">
            <?php
				$not_done_list = mysqli_query($con,$sql_notdone_list);
				while($not_done_row = mysqli_fetch_array($not_done_list)){
					echo $not_done_row['name'];
					echo "<br />";
				}
			?>
        </div>
        <div id="biodata">
            <img id="foto" src="<?php echo $thisuser['avatar'];?>">
            <img id="badge" src="img/badge.png">
            <div id="biousername">
				<?php
					echo $thisuser['username'];
				?>
			</div>
            <div id="bioleft">
                Name<br>
                Date of Birth<br>
                Email<br>
            </div>
            <div id="bioright">: <?php echo $thisuser['fullname'];?><br>
                : <?php echo $thisuser['birthday'];?><br>
                : <?php echo $thisuser['email'];?><br>
            </div>
        </div>
	<script type="text/javascript" src="script.js"></script>
    </body>
</html>