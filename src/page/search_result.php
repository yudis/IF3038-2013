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
			<div id="search-result-body">
				<?php
					$mode = $_POST['modesearch'];
					switch($mode)
					{
						case "1":
							echo "";
							break;
						case "2":
							break;
						case "3":
							break;
						case "4":
							break;
					}
				?>
				<div id="main-dashboard">
					<div id="dashboard-title"><b>TASK<br /></b><br /></div>
					<div id="sort">	
						Sort by :
						<select name="Sort by">
							<option value="Auto">Auto</option>
							<option value="Name">Name</option>
							<option value="Date">Date</option>
						</select>&nbsp;			
					</div>
				</div>
			</div>
		</div>
	</body>
</html>