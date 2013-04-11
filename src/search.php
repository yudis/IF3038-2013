<?php
	$title = "Searching '".$_GET['q']."'";
	$login_permission = 1;
	include 'inc/header.php';
?>
		<script>
			window.onload=function(){localStorage.user_id = <?php echo getUserID(); ?>; 
			_q = '<?php echo $_GET['q']?>'; _mode = <?php echo $_GET['mode']; ?>; _page = 0; _done = false;
			tasks = false;
			users = false;
			categories = false;
			getSearchResult(_q,_mode,0);
			window.onscroll = loadMoreSearch;
			}
		</script>
		<div class="content">
			<div class="dashboard">	
				<header>
					<h1>Search Result</h1>
				</header>
				
				<div class="primary">
					<section class="tasks current" id="searchResult">
					
					</section>
				</div>
			
				<div class="secondary">
					<section class="categories">
						<header>
							<h3>Search Tips</h3>
						</header>
						<ul id="categoryList">
							<li>Scroll below to get more data, if any</li>
						</ul>
					</section>
				</div>

			</div>

		</div>
<?php
	include 'inc/footer.php';
?>
