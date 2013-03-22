<?php
	$title = 'Profile';
	$login_permission = 1;
	include 'inc/header.php';
	if (!isset($_GET['user_id']))
		die('User ID Not Found !');
	$tugas = query('select * from user where user_id = :user_id',array('user_id' => $_GET['user_id']));
?>

		<script>
			window.onload=function(){localStorage.user_id = <?php echo getUserID(); ?>; refreshTask1(localStorage.user_id,0);};
			function refreshTaskRoutine() {
			  refreshTask1(localStorage.user_id,_category_id);
			}
			var interval = setInterval(refreshTaskRoutine, 10000);
		</script>
		
		<div class="content">
			<div class="profile">
				<header>
					<h1><?php echo getUserName(getUserID()); ?></h1>
					<ul>
						<li class="edit-profile-link"><a href="new_profile.php" id="editProfileButton">Edit Profile</a></li>
					</ul>
				</header>
				<section class="profile-details">
					<figure class="profile-image">
						<img src="avatar/<?php echo getUserID(); ?>.jpg" alt="Profile Photo">
					</figure>
					<p class="description">
						<span class="detail-label">About Me:</span>
						<span class="detail-value">Lorem Ipsum Dolor Sit Amet</span>
					</p>
					<p class="username">
						<span class="detail-label">Username:</span>
						<span class="detail-value"><?php echo getUserUsername(getUserID()); ?></span>
					</p>
					
					<p class="name">
						<span class="detail-label">Full Name:</span>
						<span class="detail-value"><?php echo getUserFullname(getUserID()); ?></span>
					</p>
					
					<p class="email">
						<span class="detail-label">Email:</span>
						<span class="detail-value"><?php echo getUserEmail(getUserID()); ?></span>
					</p>

					<p class="date-of-birth">
						<span class="detail-label">Date of Birth:</span>
						<span class="detail-value"><?php echo getUserBirthdate(getUserID()); ?></span>
					</p>
				</section>

				<div class="primary2">
					<section class="tasks current" id="activeTask1">
					
					</section>

					<section class="tasks completed" id="doneTask1">

					</section>
				</div>
			
				
			
			</div>
		</div>
<?php
	include 'inc/footer.php';
?>
