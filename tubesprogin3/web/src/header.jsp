<header>
			<div id="header_container"> 
				<div class="left">
					<a href="dashboard.php"> <img src="../img/logo.png" alt=""> </a>
				</div>
				<form id="searchform" action="javascript:search()" method="post">
				<input id="search_box" type="text" placeholder="search..."> <input id="search_type" type="text" placeholder="username/category/task"><br/>   <input type="submit" name="Submit" value="Search"/>
				</form>
				<div class="header_menu"> 
					<div class="header_menu_button current_header_menu"> <a href="dashboard.php"> DASHBOARD </a>   </div>
					<div class="header_menu_button">  <a href="profile.php"> PROFILE </a> </div>
					<div class="header_menu_button"> <a id="logout" href="../php/logout.php"> LOGOUT </a> </div>
				</div>
				
			</div>
			<div class="thin_line"></div>
		</header>	