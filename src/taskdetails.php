<html>
	<head>
		<title>Shared To Do List - Task Details</title>
		<link rel="stylesheet" type="text/css" href="style.css">
		<link rel="shortcut icon" href="favicon.ico">
		<script type="text/javascript" src="validation.js"></script>
		<script type="text/javascript" src="comment.js"></script>
		<script type="text/javascript" src="listOfComments.js"></script>
		<script>checkLogged()</script>
	</head>
	<body>
		<div id="navsearch">
			<script>checkLogged()</script>
		</div>
		</div>
		<div class="clearall container" id="details">
			<?php
				$taskname = (isset($_GET['taskname'])) ? $_GET['taskname'] : "";
				$category = (isset($_GET['category'])) ? $_GET['category'] : "";				
			?>
			<div name="taskname" id="<?php echo $taskname; ?>"></div>
			<div name="category" id="<?php echo $category; ?>"></div>
			<script>loadTaskDetails()</script>
		</div>
	</body>
</html>