<!DOCTYPE html>
<?php include '../php/fungsiget.php'?>
	<?php
      $kategori = get_allkategoriphp();
      print_r($kategori);
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
		<script type="text/javascript" src="../js/catselector.js"> </script> 	
                <script type="text/javascript" src="../js/add_task.js"></script>
                <script type="text/javascript" src="../js/fungsiget.js">
                    
                   <!-- var taskid = "<?php echo $last_idx; ?>";-->
                   <!-- alert(taskid);-->
                </script>
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
				<input id="search_box" type="text" placeholder="search...">
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
					<div id="add_new_category" onclick="toggle_visibility('category_form')";> + new category </div>
					<div id="category_form">
                                          
						<div id="category_form_inner">
							Category name : <br>
							<input type="text" id="add_category_name" name="nama_kategori" value="">
							<br><br>
							Assignee(s) : <br>
							<input type="text" id="add_category_asignee_name" name="assignee_name" value="">
							<br><br>
							<div id="add_category_button" class="link_red" onclick="add_category()"> Add </div>
						</div>
                                         
					</div>
				</div>
			</div>
			<div id="dynamic_content">
                            
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
	</body>

<!-- ini nanti jadiin footer -->
</html>
