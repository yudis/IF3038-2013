<html>
	<head>
		<title>Shared To Do List - Task Details</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
		<link rel="shortcut icon" href="favicon.ico">
		<script type="text/javascript" src="../validation.js"></script>
		<script type="text/javascript" src="comment.js"></script>
		<script type="text/javascript" src="listOfComments.js"></script>
		
	</head>
	<body>
		
		<div id="navigation">
			<img>
			<a href="../dashboard.php">DASHBOARD</a>
			<a href="../profile.php">PROFILE</a>
			<a href="#" onclick="toggleSearch()">SEARCH</a>
			<a href="../index.php">LOGOUT</a>			
		</div>
		<div id="search">
			<input type="text" size="50%">
		</div>
		</div>
		<div class="clearall container" id=task>
			<br><br><br>
			<h2>Task Details</h2><br>
			<div class="box">
				<br>
				<h3>
				<p>
					Task Name<br><br>
				</p>
				<p>
					Attachment<br><br><br><br><br><br><br><br><br>
				</p>
				<p>
					Deadline<br><br>
				</p>
				<p>
					Tag<br><br>
				</p>
				<p>
					Assignee<br><br>
				</p>
				</h3>
			</div>
			<div class="box2">
				<h3>
					<p>
						<b><u>Tugas Besar 1 IF3038</b></u>
					</p>
					<p>
						<div> 
							<br><br>
							<video id="video1" width="420">
								<source src="video/contoh.mp4" type="video/mp4">
								<source src="video/contoh.webm" type="video/webm">
								Your browser is extremely cupu
							</video>
							<br>
							<button onclick="playPause()">Play/Pause</button> 
						</div>
					</p>
					<p>
						<b>31 - 12 - 2013</b>
						<br><br>
					</p>
					<p>
						<i>html, css, javascript, datepicker</i>
						<br><br>
					</p>
					<p>
						<b>yudhistira16</b>
					</p>
					<p>
						<div>
						</div><br/>
						<script type="text/javascript">
							loc = new ListOfComments();
							loc.display("loc");
						</script>
					</p>
				</h3>
			</div>
		</div>
	</body>
</html>