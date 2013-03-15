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
            <div>
				<?php 
					require("../php/init_function.php");	
					$mode = $_POST['modesearch'];
					$keytext = $_POST['search_text'];
					
					if(strcmp($mode,"1") == 0){	// all
						echo "<h1>USER</h1>";
						SearchAvatar($keytext);
						echo "<h1>CATEGORY</h1>";
						SearchCategory($keytext);
						echo "<h1>TASK</h1>";
						SearchTask($keytext);
					}else if(strcmp($mode,"2") == 0){ // user
						SearchAvatar($keytext);
					}else if(strcmp($mode,"3") == 0){ // category
						SearchCategory($keytext);
					}else if(strcmp($mode,"4") == 0){ // task
						SearchTask($keytext);
					}
				?>
            </div>
		</div>
	</body>
</html>