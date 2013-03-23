<?php
	$title = 'Profile';
	$login_permission = 1;
	include 'inc/header.php';
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
						<li><a href="#" id="editProfileLink">Edit Profile</a></li>
					</ul>
				</header>
				
				<div id="current-profile">
				
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
				</div>
				
				<div id="edit-profile" class="editingprofile">
					<form id="new_profile" action="#" method="post">
						<div class="field">
							<label>Username</label>
							<input size="30" maxlength="50" name="username" id="username" type="text">
						</div>
						<div class="field">
								<label>New Avatar</label>
								<input name="avatar" id="avatar" type="file" accept="image/jpeg">
						</div>
						<div class="field">
								<label>Tanggal Lahir Baru</label>
								<input size = "30" name="tanggal_lahir" id="tanggal_lahir" type="date">
							</div>
						<div class="field">
								<label>New Password</label>
								<input size="30" maxlength="50" name="password" id="password" type="password">
							</div>
							<div class="field">
								<label>Confirm New Password</label>
								<input size="30" maxlength="50" name="password_k" id="password_k" type="password">
							</div>
					</form>
				</div>

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
