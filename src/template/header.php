<header>
	<div id="header_wrap" class="wrap">
		<a href="index.php"> <img id="header_logo" src="<?php echo $_SESSION['base_url']; ?>images/logo.png" alt="Logo Produk" /> </a>
		<div id="header_title">
			<h1> <a href="index.php">MOA</a> </h1>
			<h4>Multiuser Online Agenda</h4>
		</div>
		<div id="border_header"></div>
		<div id="header_menu">
			<nav>
				<ul>
					<?php
						foreach ($menu as $key => $value)
						{
							echo "<li>";
							echo "<a ";
							foreach ($value as $key2 => $value2)
							{
								echo $key2."=\"".$value2."\" ";
							}
							echo ">".$key."</a>";
							echo "</li>";
						}
					?>
				</ul>
			</nav>
			<div id="login_area">
				<?php 
					if (!ISSET($_SESSION['user_id']))
					{
				?>
					<nav id="login_button">
						<ul>
							<li> <a id="login_link" href="#">Masuk</a> </li>
						</ul>
					</nav>
					<div class="clear"></div>
					<div id="border_login"><div id="border_login_inner"></div></div>
					<div id="login_form_wrap">
						<form id="login_form" action="dashboard.html" method="post">
							<div class="row">
								<label for="login_username">Username</label> <br />
								<input id="login_username" name="username" type="text" required /> <br />
							</div>
							<div class="row">
								<label for="login_password">Sandi</label> <br />
								<input id="login_password" name="password" type="password" required /> <br />
							</div>
							<input type="submit" value="Masuk" />
						</form>
					</div>
				<?php
					}
					else
					{
				?>
					<nav id="login_button">
						<ul>
							<li> <a id="login_link" href="#">Keluar</a> </li>
						</ul>
					</nav>
				<?php
					}
				?>
				<div class="clear"></div>
			</div>
			<?php 
				if (ISSET($_SESSION['user_id']))
				{
			?>
					<div id="border_search"></div>

					<div id="search">
						<div id="search_inner">
							<div id="search_button"></div>
							<div id="search_left"></div>
							<form id="search_form">
								<input id="search_text" name="search_query" type="text" />
							</form>
							<div id="search_right"></div>
						</div>
					</div>
			<?php
				}
			?>
		</div>
		<div class="clear"></div>
	</div>
</header>
<script type="text/javascript">
	var base_url = "<?php echo $_SESSION['base_url']; ?>";
</script>
<?php 
	if (!ISSET($_SESSION['user_id']))
	{
?>
		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/login.js"></script>
<?php
	}
	else
	{
?>
		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/search.js"></script>
		<script type="text/javascript" src="<?php echo $_SESSION['base_url']; ?>js/logout.js"></script>
<?php
	}
?>