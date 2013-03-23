<html>
	<head>
		<title>Shared To Do List - Add Task</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
		<link rel="shortcut icon" href="favicon.ico">
		<script type="text/javascript" src="../validation.js"></script>		
		<script type="text/javascript" src="addtaskcontroller.js"></script>	
		<script type="text/javascript" src="../mpquery.js"></script>
	</head>
	<body>
		<div id="navigation">
			<img>
			<a href="../dashboard.php">DASHBOARD</a>
			<a href="../profile/index.php?u=<?=$_SESSION['username'];?>">PROFILE</a>
			<a href="#" onclick="toggleSearch()">SEARCH</a>
			<a href="../index.php">LOGOUT</a>			
		</div>
		<div id="search">
			<input type="text" size="50%">
		</div>
		<div class="box2" id="coba">
		</div>
		<div class="clearall container" id="tasks">
			<br><br><br>
			<h2>Add Task</h2>
			<div class="box">
				<br><br>
				<h3><img src="../images/patrick.png"></h3>
			</div>		
			<div class="box2">
				<h3></h3>
				<form method="POST" action="../taskdetails/" onsubmit="submitNewTask();"><!--addtasksubmit.php"--><!--taskdetails.php"-->
				<fieldset>
					<p>
						<label>Task Name<abbr title="Required">*</abbr></label>
						<input value="" id="taskname"
						required="required" aria-required="true"
						pattern="^[A-Za-z0-9_ ]{1,25}$"
						title="Your task title"
						type="text" spellcheck="false" size="20">
					</p>
					<p>
						<label>Deadline <abbr title="Required">*</abbr></label>
						<input type="date" id="deadline"
						required="required" aria-required="true"/>
					</p>
					<p>
						<label>Assignee <abbr title="Required">*</abbr></label>
						<input value="" id="assignee"
						required="required" aria-required="true"
						pattern="^[a-zA-Z0-9_]{5,}$"
						title="Username responsible for this task"
						type="text" spellcheck="false" size="20" />
					</p>
					<p>
						<label>Tag <abbr title="Required">*</abbr></label>
						<input value="" id="tags"
						required="required" aria-required="true"
						pattern="^[A-Za-z0-9_ ,]{1,50}$"
						title="	Tag for this task"
						type="text" spellcheck="false" size="20" />
					</p>
					<p>
						<label>Attachment<abbr title="Required">*</abbr></label>
						<input type="file" id="attachment" 
						required="required" aria-required="true"
						title="Picture or Video related to this task"/>
					</p>			
				</fieldset>
				<fieldset>
				<button name="addtask" type="submit">Add Task</button>
				</fieldset>
				</form>
			</div>
		</div>
	</body>
</html>