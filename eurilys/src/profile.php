<?php 
	session_start();

	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	
	/*get user details from database*/
	$query	= "SELECT * FROM user WHERE username='$username' LIMIT 1";
	$result	=  mysql_query($query) or die(mysql_error());

	/*put details to variable*/
	$row = mysql_fetch_array($result, MYSQL_ASSOC);
	$username = $row['username'];
	$fullname = $row['full_name'];
	$birthdate = $row['birthdate'];
	$avatar = $row['avatar'];
	$email = $row['email'];

	/*get user's task*/
	$querytask = "SELECT DISTINCT *
	FROM task_asignee RIGHT JOIN task 
	ON task.task_id=task_asignee.task_id 
	WHERE username='$username' OR task_creator='$username'";

	$resulttask = mysql_query($querytask);
	
?>

<!DOCTYPE html>
<html>
	<head>
        <link href='../css/mobile.css' rel='stylesheet' type='text/css'>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
        <link href='../css/wide.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/edit_task.js"> </script> 
		<script type="text/javascript" src="../js/animation.js"> </script> 
		<script type="text/javascript" src="../js/catselector.js"> </script> 
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >		
        <meta name="viewport" content="width=device-width; initial-scale=1.0">
		<title> Eurilys </title>
	</head>
	
	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div class="left">
					<a href="dashboard.php"> <img src="../img/logo.png" alt="logo"> </a>
				</div>
				<input id="search_box" type="text" placeholder="search...">
				<select id="search_box_filter">
					<option> All </option>
					<option> User </option> <!-- username, email, nama lengkap, birthdate -->
					<option> Category </option>
					<option> Task </option> <!-- task name, tag, comment -->
				</select>
				<div class="header_menu"> 
					<div id="menu_dashboard" class="header_menu_button"> <a href="dashboard.php"> DASHBOARD </a>  </div>
					<div id="menu_profile" class="header_menu_button current_header_menu">  <a href="profile.php"> PROFILE </a> </div>
					<div id="menu_logout" class="header_menu_button"> <a id="logout" href="../index.php"> LOGOUT </a> </div>
				</div>
			</div>
			<div class="thin_line"></div>
		</header>	


		
<!-- Web Content -->
<section>
	<?php include 'navigation_bar.php'; ?>
	
	<div id="dynamic_content">
		<div class="half_div">
			<div id="upperprof">
				<img id="mainpp" width="225" src="<?php echo $avatar; ?>" alt=""/>
				<div id="namauser"><?php echo $fullname; ?></div>
			</div>
			<br/><br/>
			<?php echo $email; ?>
			<br/>
			<?php echo $birthdate; ?>

		</div>
		<div class="half_div">
			<div class="half_tall">
				<div class="headsdeh">Current Tasks</div>
					<?php
					$rowtask2 = array(); 
					while ($rowtask = mysql_fetch_array($resulttask, MYSQL_ASSOC)) {
						$rowtask2[] = $rowtask;
						$taskstatus=$rowtask['task_status'];
						
						if ($taskstatus == 0) {
							$taskname=$rowtask['task_name'];
							$taskID=$rowtask['task_id'];
							echo "<div class='cursorPointer darkBlueLink' onclick='javascript:viewTask(\"$taskID\")'>$taskname</div> ";
						}
					}
					?>
			</div>
			<div class="half_tall">
				<div class="headsdeh">Finished Tasks</div>
					<?php 
					foreach($rowtask2 as $rowtask){
						$taskstatus=$rowtask['task_status'];
						if ($taskstatus==1){
							$taskID=$rowtask['task_id'];
							$taskname=$rowtask['task_name'];
							echo "<div class='cursorPointer darkBlueLink' onclick='javascript:viewTask(\"$taskID\")'>$taskname</div></br>";
						}
					}
					?>
			</div>
		</div>
		
	</div>
</section>
		
<?php include '../footer.php'; ?>	