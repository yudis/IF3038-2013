<!DOCTYPE html>
<html>
	<head>
		<title>Search Result</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<div id="main-body-general">
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