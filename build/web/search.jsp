 <html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		
		<link href="css/style2.css" rel="stylesheet" type="text/css" />
		<meta charset="utf-8" />
		<script type="text/javascript" src="login.js"></script>
	</head>

	<body>
	<div id='search'>
		<div id="searchbox">
			<label>Search: 
				<input type="text_search" name="text_search" id="text_search" placeholder="Search" />
				<input type="SUBMIT" name="SUBMIT" id="SUBMIT" value="Search" > 
			</label>
		</div>
		<div id="searchoption">
			<label>Filter: 
				<select id="filter" name="filter" width="400">
					<option> All </option>
					<option> Users </option>
					<option> Tasks </option>
					<option> Tags </option>
				</select>
			</label>
		</div>
	</div>
	</body>
	<!--<?php include("search.php"); ?>-->
</html>