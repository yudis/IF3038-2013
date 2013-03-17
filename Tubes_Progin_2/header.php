<header>
	<a href="dashboard.html" title="Home"><img id="logo-small" src="img/Logo_Small2.png" alt="" /></a>
	<div id="dashboard"><a title="Go to Dashboard" href="dashboard.html">Dashboard</a></div>
	<div id="profile"><a title="Go to Profile" href="profile.html">My Profile</a></div>
	<div id="logout"><a title="Log out from here" href="index.html">Log Out</a></div>
	<form id="search" action="search.php" method="post">
		<span>
			<input type="text" autocomplete="off" name="Search" id="box" onkeyup="showHint(this.value)">
			<input id="selectedKategori" name="namafilter" type="hidden" value="all result">
			<label onclick="showfilter();"></label>
			<div id="filter">
				<div onclick="filter(this.innerHTML);">all result</div>
				<div onclick="filter(this.innerHTML);">username</div>
				<div onclick="filter(this.innerHTML);">category</div>
				<div onclick="filter(this.innerHTML);">task</div>
			</div>
		</span>
		
		<div id="hasilsearch"></div>
	</form>
</header>