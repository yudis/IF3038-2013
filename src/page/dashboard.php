<!DOCTYPE html>
<html>
	<head>
		<title>Dashboard</title>
		<link rel="stylesheet" type="text/css" href="style.css">
        <link href="modal.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="dashboard.js"></script>
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
					<a href="profile.php"><img alt="" id="photo" src="../avatar/<?php echo $_SESSION["userlistapp"]?>.png" width=120 height="150"/>
   					<img alt="" id="photo" src="../avatar/<?php echo $_SESSION["userlistapp"]?>.jpg" width=120 height="150"/><br />
					<b><?php echo $_SESSION["userlistapp"]?></b></a>
				</div>
				<div id="main-dashboard">
					<div id="dashboard-title"><b>MY TASK<br /></b><br /></div>
					<div id="add-category"><a href="#add_task"><button>+ New Category</button></a>&nbsp;</div>
					
					<!-- popup form #1 -->
					<a href="#x" class="overlay" id="add_task"></a>
					<div class="popup">
						<h2>Add New Category</h2>
						<br>
                        <form action="../php/insertcategory.php" method="post">
						<div>
							<label for="login">Name:</label>
							<input type="text" id="login" value="" name="newCategory"/>
						</div>
						<div>
							<label for="asignee">Assignee:</label>
							<input type="text" id="asignee" value="" name="listAssignee" onKeyUp="autoCompleteAsignee()" list="assignee" placeholder="tag1,tag2,tag3"/>
							<div id="assignee-suggest"></div>
						</div>
						<div align="right"><input type="submit" value="Finish"/></div>
						</form>

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
