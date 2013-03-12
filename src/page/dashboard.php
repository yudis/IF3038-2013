<!DOCTYPE html>
<html>
	<head>
		<title>Dashboard - meckyr</title>
		<link rel="stylesheet" type="text/css" href="style.css">
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
					<div id="add-category"><button onclick="myFunction()">+ New Category</button>&nbsp;</div>
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
		</div>
	</body>
</html>
