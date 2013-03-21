<?php
	$title = 'New Tugas';
	$login_permission = 1;
	include 'inc/header.php';
?>
		<div class="content">
			<div class="add-profile">
				<header>
					<h1>New Profile</h1>
				</header>
				<form id="new_profile" action="profile.php" method="post">
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
						<div class="buttons">
							<button type="submit">Save</button>
						</div>
				</form>
			</div>
		</div>
<?php
	include 'inc/footer.php';
?>
