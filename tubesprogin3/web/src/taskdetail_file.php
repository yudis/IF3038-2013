<!DOCTYPE html>
	<?php
			include_once("../php/loginchecker.php");
		?>
	
<?php include '../php/fungsiget.php'?>
<?php include '../php/getnewtaskdetail.php'?>
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
                <script type="text/javascript" src="../js/edit_task2.js"></script>
		<meta http-equiv="Content-Type" content="text/html;charset=utf-8" >
		<title> Eurilys </title>
	</head>

	<body>
		<!-- Web Header -->
		<?php
			include_once("header.php");
		?>
	
		
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
                        <?php
                            $ct = $_GET["ct"];
                            $tm = $_GET["tm"];
                            $ts = $_GET["ts"];
                            $td = $_GET["td"];
                            $ttm = $_GET["ttm"];
                            $c = $_GET["c"];
                        ?>
			<div id="dynamic_content">
                            <div id="edit_task_header" class="left top30 dynamic_content_head">
                                    <?php echo $ct?>
				</div>
				
				<input id="edit_task_button" class="left top30 link_blue_rect" 
					onclick="edit_task2('<?php echo $tm ?>')" type="button" value="Edit Task" />
				
				
				<input id="save_button_td" class="left top30 link_blue_rect" 
					onclick="save_edit_task()" type="button" value="Save"/>
				
				
				<div id="row1_taskdetail" class="left top30 dynamic_content_row">
					<div id="task_name_ltd" class="left dynamic_content_left">Task Name</div>
					<div id="task_name_rtd" class="left dynamic_content_right"><?php echo $tm ?></div>
				</div>
				
				<div id="row2_taskdetail" class="left top10 dynamic_content_row">
					<div id="attachment_ltd" class="left dynamic_content_left">Attachment</div>
					<div id="attachment_rtd" class="left dynamic_content_right">
						<a id="task_attachment" href="../file/taskdetail_file.pdf">Assignment.pdf</a>
					</div>
				</div>
				
				<div id="row3_taskdetail" class="left top10 dynamic_content_row">
					<div id="deadline_ltd" class="left dynamic_content_left">Deadline</div>
					<div id="deadline_rtd" class="left dynamic_content_right"><?php echo $td ?></div>
				</div>
				
				<div id="row4_taskdetail" class="left top10 dynamic_content_row">
					<div id="assignee_ltd" class="left dynamic_content_left">Assignee</div>
					<div id="assignee_rtd" class="left dynamic_content_right">Sharon</div>
				</div>
			
				<div id="row5_taskdetail" class="left top10 dynamic_content_row">
					<div id="tag_ltd" class="left dynamic_content_left">Tag</div>
					<div id="tag_rtd" class="left dynamic_content_right"><?php echo $ttm ?></div>
				</div>
				
				<div id="row6_taskdetail" class="left top10 dynamic_content_row">
					<div id="comment_ltd" class="left dynamic_content_left">Comment</div>
					<div id="comment_rtd" class="left dynamic_content_right">Lorem ipsum dolor sit amet, 
					ea per meliore copiosae gubergren, ei latine iracundia euripidis sed, 
					ut odio lorem qui. Mea ne nobis feugait, sea iuvaret pertinax at, 
					harum commodo ne per. Per timeam dolorum no. Ne nam utroque percipitur, 
					augue partem imperdiet in pri. At est omnes habemus. Atqui antiopam 
					indoctum quo cu, no purto antiopam vis. Agam vidit ea nam, audiam alterum 
					appellantur mel ad. Copiosae erroribus vulputate no est, eos affert 
					minimum atomorum ei. Id nec choro luptatum, oblique suscipit nam ex.
					Sit elitr legere postulant te. Id putent dolorem usu, in sanctus 
					scripserit est. Has at zril quaeque sensibus, at case elaboraret vis, 
					nec natum noluisse salutandi ut. Putent habemus adversarium vim et, 
					has platonem hendrerit eu, vim at urbanitas pertinacia. Id pri partem 
					malorum aliquando, ne eum veniam animal probatus.
					</div>
				</div>
				
				<div id="row7_taskdetail" class="left top10 dynamic_content_row">
					<div id="addcomment_ltd" class="left dynamic_content_left">Add Comment</div>
					<div id="addcomment_rtd" class="left dynamic_content_right">
						<form autocomplete="off">
							<textarea id="comment_textarea" rows="5" cols="50" name="CommentBox">
							</textarea><br>
							<input type="submit" value="Add">
						</form>
					</div>
				</div>
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
