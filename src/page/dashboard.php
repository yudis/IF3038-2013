<!DOCTYPE html>
<html>
	<head>
		<title>Dashboard - meckyr</title>
		<link rel="stylesheet" type="text/css" href="style.css">
        <link href="modal.css" rel="stylesheet" type="text/css" />
		<script>
		function myFunction()
		{
			var x;
			var name=prompt("Masukkan nama kategori baru : ","Kategori Baru");
			var list=prompt("Masukkan ID pengguna yang berhak modifikasi :","<id1>,<id2>,...");
			if (name!=null)
			  {
			  x="<div class=task-category-body><div class=category-title><b>"+name+
			  "</b></div><ul><li>There's no task in this category</li><br><div class = add-task>"+
			  "<a href = add_task.php>Create New Task</a></div></ul><br><div><hr id=border></div>";
			  document.getElementById("new-category").innerHTML=x;
			  }
		}
		</script>
	</head>
	<body>
		<div id="main-body-general">
			<!--Header-->
			<div id="header">
				<?php
					include("header.php");
				?>
			</div>
			<div><hr id="border"></div>
			<!--Body-->
			<div id="dashboard-body">
				<div id="profile-pic">
					<img id="photo" src="../image/ecky.jpg" width=120/><br />
					<b>meckyr</b>
				</div>
				<div id="main-dashboard">
					<div id="dashboard-title"><b>MY TASK<br /></b><br /></div>
					<div id="add-category"><a href="#add_task"><button>+ New Category</button></a>&nbsp;</div>
					
					<!-- popup form #1 -->
					<a href="#x" class="overlay" id="add_task"></a>
					<div class="popup">
						<h2>Add New Category</h2>
						<br>
						<div>
							<label for="login">Name:</label>
							<input type="text" id="login" value="" />
						</div>
						<div>
							<label for="password">Assignee:</label>
							<input type="text" id="password" value="" />
						</div>
						<div align="right"><input type="button" value="Finish"/></div>

						<a class="close" href="#close"></a>
					</div>
					
					<div id="sort">	
						Sort by :
						<select name="Sort by">
							<option value="Auto">Auto</option>
							<option value="Name">Name</option>
							<option value="Date">Date</option>
						</select>&nbsp;			
					</div>
					<!--Category list (static)-->
                    <?php
						include("listCategory.php");
					?>
                        
						<div id="new-category"></div>
					<!--New category button ==> popup-->
						<!--Name-->
						<!--List of priveleged users-->
					<!--New task button ==> new_task.html (this button only appears if a category is selected)-->
				</div>
			</div>
		</div>
	</body>
</html>
