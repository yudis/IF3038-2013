<?php 
	ob_start(); //You could also just output the $image via header() and bypass this buffer capture.
	
	/* Configuring Server & Database */
	$host        =    'localhost';
	$user        =    'root';
	$password    =    '';
	$database    =    'progin_405_13510086';
	$con        =    mysql_connect($host,$user,$password) or die('Server information is not correct.');
	mysql_select_db($database,$con) or die('Database information is not correct');

	/*
	if (isset($_SESSION['username'])) {
		$username = $_SESSION['username']; 
	}
	
	$query	= "SELECT avatar FROM user WHERE username='$username' LIMIT 1";
	$result	=  mysql_query($query) or die(mysql_error());
	
	
	while ($row = mysql_fetch_row($result)) {
		echo '<img src="data:image/jpg;base64,'.base64_encode($row[0]).'" alt="photo">';
	} */

?>
<div id="navbar">
	<div id="short_profile">
		<img id="profile_picture" src="../img/avatar1.png" alt="">
		<div id="profile_info">
			Welcome, <br>
			<a href="profile.php" class="darkBlue"> <?php if (isset($_SESSION['fullname'])) {echo $_SESSION['fullname']; }?> </a>
			<br><br>
			<div class="link_tosca" id="edit_profile_button"> Edit Profile </div>
		</div>
	</div>
	<div id="category_list">
		<div class="link_blue_rect" id="category_title"><a href="#" onclick="catchange(0)">All Categories </a> </div>
		<ul id="category_item">
			<?php 
				$query 	= "SELECT * FROM category;";
				$result	= mysql_query($query);
				while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
					echo "<li> <a href='#' onclick='catchange('".$row['cat_id']."')' id='kuliah'> ".$row['cat_name']." </a> </li>";
				}
			?>
		</ul>
		<div id="add_task_link"> <a href="addtask.php"> + new task </a> </div>
		<div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
		<div id="category_form">
			<div id="category_form_inner">
				Category name : <br>
				<input type="text" id="add_category_name" value="">
				<br><br>
				Assignee(s) : <br>
				<input type="text" id="add_category_asignee_name" value="">
				<br><br>
				<div id="add_category_button" class="link_red" onclick="add_category()"> Add </div>
			</div>
		</div>
	</div>
</div>