<?php
	$u = (isset($_GET['username'])) ? $_GET['username'] : "" ;
	$e = (isset($_GET['e'])) ? $_GET['e'] : "" ;
?>
<div id="navigation">
	<img src="../images/logo.gif">
	<a href="../dashboard.php">DASHBOARD</a>
	<a href="../profile/?u=<?=$u?>&e=1">PROFILE</a>
	<a href="#" onclick="toggleSearch()">SEARCH</a>
	<a href="../index.php" onclick="logout()">LOGOUT</a>			
</div>
<div id="search">
	<input id="searchterm" type="text" size="50%" />
	<select id="searchtype">
		<option value="semua" selected>Semua</option>
		<option value="username">Username saja</option>
		<option value="category">Category saja</option>
		<option value="task">Task saja</option>
	</select>
	<button id="searchbutton" onclick="search2()">Search</button>
</div>