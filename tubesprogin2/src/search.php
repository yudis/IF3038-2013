<!DOCTYPE html>
<?php include '../php/fungsiget.php'?>
<?php include '../php/getalltaskfordashboard.php'?>
<?php include '../php/getassignee.php'?>

<?php
      $kategori = get_allkategoriphp();
      //print_r($kategori);
      $task = getalltask();
      $asignee = get_asignee();
    //$all_task = get_alltaskphp();
    ?>
<html>
	<!--
	<IFRAME name="iframe" src="src/header.html" width='100%' height='auto' marginwidth=0 marginheight=0 hspace=0 vspace=0 frameborder=0 scrolling=auto></IFRAME>
	-->
	
	<head>
		<link href='../css/desktop_style.css' rel='stylesheet' type='text/css'>
		<link rel="shortcut icon" type="image/x-icon" href="../img/favicon.ico">
		<script type="text/javascript" src="../js/animation.js"> </script>
                <script type="text/javascript" src="../js/add_kategori.js"> </script>
                
		<script type="text/javascript" src="../js/catselector.js"> </script> 	
                <script type="text/javascript" src="../js/add_task.js"></script>
                <script type="text/javascript" src="../js/fungsiget.js">
                    
                   <!-- var taskid = "<?php echo $last_idx; ?>";-->
                   <!-- alert(taskid);-->
                </script>
                <script type="text/javascript" src="../js/edit_task.js"></script>
             
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>

	<body>
		<!-- Web Header -->
		<header>
			<div id="header_container"> 
				<div class="left">
					<a href="dashboard.html"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<input id="search_box" name="search_box" type="text" placeholder="search...">
				<div class="header_menu"> 
					<div class="header_menu_button current_header_menu"> <a href="dashboard.html"> DASHBOARD </a>   </div>
					<div class="header_menu_button">  <a href="profile.html"> PROFILE </a> </div>
					<div class="header_menu_button"> <a id="logout" href="../index.html"> LOGOUT </a> </div>
				</div>
				
			</div>
			<div class="thin_line"></div>
		</header>	
	
		
		<!-- Web Content -->
		<section>
			<div id="navbar">
				<div id="short_profile">
					<img id="profile_picture" src="../img/avatar1.png" alt="">
					<div id="profile_info">
						Ruth Natasha 
						<br><br>
						<div class="link_tosca" id="edit_profile_button"> Edit Profile </div>
					</div>
				</div>
				<div id="category_list">
					<div class="link_blue_rect" id="category_title"><a href="#" onclick="catchange(0)">All Categories </a> </div>
					<ul id="category_item">
                                                <?php
                                                  
                                                    foreach($kategori as $eachkategori){
                                                ?>
                                                      
                                                        <li><a href="#" onclick="get_taskkategorijs('<?php echo $eachkategori['cat_name']?>')" > <?php echo $eachkategori['cat_name']?></a></li>
                                                      
                                                      
                                                        
                                                <?php }
                                                ?>
					
					</ul>
					<!--<div id="add_task_link"> <a href="../src/addtask.php"> + new task </a> </div>-->
                                        <div class="link_blue_rect" id="category_title"><a href="#join_form"> +New Category </a></div>
				
				</div>
			</div>
			<div id="dynamic_content">
				<?php
				//declaring variable
				$input = $_GET['searchterm'];
				$searchtype = $_GET['searchtype'];

				//If they did not enter a search term we give them an error
				if ($input == ""){			
					echo "<p><h3>Silahkan masukkan kata pencarian</h3>";
					exit;
					}
				$dbhost="localhost";
				$dbname="progin_405_13510060";
				$dbusername="progin";
				$dbpassword="progin";
				$dbcon=mysql_connect($dbhost,$dbusername,$dbpassword);
				$connection_string=mysql_select_db($dbname);

				mysql_connect($dbhost,$dbusername,$dbpassword);
				mysql_select_db($dbname,$dbcon);

				//filtering input for xss and sql injection
				$input = strip_tags( $input );
				$input = mysql_real_escape_string( $input );
				$input = trim( $input );
				
				if ($searchtype == "username"){
					//search username
					$sql = "SELECT * FROM pengguna WHERE username LIKE '%$input%'";
					$data = mysql_query($sql, $dbcon) or die(mysql_error());
					while ($result = mysql_fetch_array($data)) {
						//giving names to the fields
						$username = $result['username'];
						$full_name = $result['full_name'];
						$avatar = $result['avatar'];
						//put the results on the screen
						echo "<br><b>$username</b>";	
						echo "";
						echo "<u>$full_name</u><br>";
						echo "<img src=$info/><br>";
					}
				}
				else if ($searchtype == "category"){
					//search category name
					$sql = "SELECT * FROM task WHERE cat_task_name LIKE '%$input%'";
					$data = mysql_query($sql, $dbcon) or die(mysql_error());
					while ($result = mysql_fetch_array($data)) {
						//giving names to the fields
						$taskname = $result['task_name'];
						$deadline = $result['task_deadline'];
						$tasktag = $result['task_tag_multivalue'];
						$taskstatus = $result['task_status'];
						//put the results on the screen
						echo "<br><b>$taskname</b>";	
						echo "";
						echo "<u>Deadline: $deadline</u><br>";
						echo "<img src=$info/><br>";
					}
				}
				
				//search task
				
				else if ($searchtype == "category"){
					//search category name
					$sql = "SELECT * FROM task WHERE task_name LIKE '%$input%' OR task_tag_multivalue '%$input%'";
					$data = mysql_query($sql, $dbcon) or die(mysql_error());
					while ($result = mysql_fetch_array($data)) {
						//giving names to the fields
						$taskname = $result['task_name'];
						$deadline = $result['task_deadline'];
						$tasktag = $result['task_tag_multivalue'];
						$taskstatus = $result['task_status'];
						//put the results on the screen
						echo "<br><b>$taskname</b>";	
						echo "";
						echo "<u>Deadline: $deadline</u><br>";
						echo "<img src=$info/><br>";
					}
				}
				

			?>

			</div>
		</section>
		
		<!-- Footer Section -->
		<div class="thin_line"></div>
		<footer>
			<div id="footer_container"> 
				<br><br>
				About &nbsp;&nbsp;&nbsp; FAQ &nbsp;&nbsp;&nbsp; Feedback &nbsp;&nbsp;&nbsp; Terms &nbsp;&nbsp;&nbsp; Privay &nbsp;&nbsp;&nbsp; Copyright 
				<br>
				Eurilys 2013
			</div>
		</footer>
                
                <a href="#" class="overlay" id="join_form"></a>
		
		<div class="popup">
			<div id="category_form_inner">
                            Category name : <br>
                            <input type="text" id="add_category_name" name="nama_kategori" value="">
                            <br><br>
                            Assignee(s) : <br>
                            <div id="arr_of_asignee">
                                
                            </div>
                            <select id="add_category_asignee_name" name="customers" onchange="set_assignee(this)">
                                  <option id="asignee_name" value="">Select assignee:</option>
                                <?php foreach($asignee as $eachasignee){?>
                                    <option value=""><?php echo $eachasignee['asignee_name']?></option>
                                <?php } ?>
                               
                            </select>
                           
                            <br><br>
                            <div id="add_category_button" class="link_red" onclick="add_category()"> Add Category</div>
                            <div id="add_category_button" class="link_red" ><a href="#close">Close </a></div>
                            
                        </div>
			
	
		</div>
	</body>

<!-- ini nanti jadiin footer -->
</html>


