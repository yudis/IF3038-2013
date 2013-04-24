<?php 
	ob_start(); //You could also just output the $image via header() and bypass this buffer capture.
	
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

?>
<div id="navbar">
	<div id="short_profile">
		<a href="profile.php"> <img id="profile_picture" src="<?php echo $avatar?>" alt=""> </a>
		<div id="profile_info">
			Welcome, <br>
			<a href="profile.php" class="darkBlue"> <?php echo $fullname; ?> </a>
			<br><br>
			<div class="link_tosca" id="edit_profile_button"> <a href="edit_profile.php"> Edit Profile </a></div>
		</div>
	</div>
	<div id="category_list">
		<div class="link_blue_rect" id="category_title"><a href="#" onclick="javascript:generateTask('all')"> All Categories </a> </div>
		<ul id="category_item">
			<?php 
				$query 	= "SELECT * FROM category WHERE cat_creator = '$username'";
				$result	= mysql_query($query);
				while ($row = mysql_fetch_array($result, MYSQL_ASSOC)) {
					echo "<li> <span class='categoryList' onclick='javascript:generateTask(\"".$row['cat_name']."\")'> ".$row['cat_name']." </span> </li>";
					
				}
				
				$query2 	= "SELECT cat_id FROM cat_asignee WHERE username = '$username'";
				$result2	= mysql_query($query2);
				while ($row2 = mysql_fetch_array($result2, MYSQL_ASSOC)) {
					$categoryID = $row2['cat_id'];
					
					$query1 	= "SELECT * FROM category WHERE cat_id = '$categoryID'";
					$result1	= mysql_query($query1);
					while ($row1 = mysql_fetch_array($result1, MYSQL_ASSOC)) {
						echo "<li> <span class='categoryList' onclick='javascript:generateTask(\"".$row1['cat_name']."\")'> ".$row1['cat_name']." </span> </li>";
					}
				}
			?>
		</ul>
		<script type="text/javascript" src="../js/animation.js"> </script> 

		<div id="add_task_link"> <a id="add_task" name="" onclick="addCatName();" href="addtask.php"> + new task </a> </div>
		<div id="add_new_category" onclick="toggle_visibility('category_form');"> + new category </div>
		<div id="category_form">
			<div id="category_form_inner">
				<form method="POST" action="add_category_script.php">
					Category name : <br>
					<input type="text" name="add_category_name" id="add_category_name" value="">
					<br><br>
					Assignee(s) : <br>
					<input type="text" name="add_category_asignee_name" onkeyup="showAssigne(this.value)" id="add_category_asignee_name" value="">
					<div id="categoryHint"> </div>
					<br><br>
					<button type="submit" id="add_category_button" name="add_category_button" class="link_red"> Add </div>
				</form>
			</div>
		</div>
	</div>
</div>