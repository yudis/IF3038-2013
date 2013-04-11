<%
	String title = "Profile";
	boolean login_permission = true;
        String _user_id = (String) request.getParameter("user_id");
%>      
<%@include file="inc/header.jsp" %>

		<script>
			window.onload=function(){localStorage.user_id = <%= _user_id %>; refreshTask1(localStorage.user_id,0);};
		</script>
		
		<div class="content">
			<div class="profile">
				<header>
					<h1><%= Function.getUserName(_user_id) %></h1>
					<% if (_user_id.compareTo(user_id.toString()) == 0) {%>
						<ul>
							<li><a href="#" id="editProfileLink">Edit Profile</a></li>
						</ul>
					<% } %>
				</header>
				
				<div id="current-profile">
				
				<section class="profile-details">
					<figure class="profile-image">
						<img src="avatar/<%= _user_id %>.jpg" alt="Profile Photo">
					</figure>
					<p class="description">
						<span class="detail-label">About Me:</span>
						<span class="detail-value">Lorem Ipsum Dolor Sit Amet</span>
					</p>
					<p class="username">
						<span class="detail-label">Username:</span>
						<span class="detail-value"><%= Function.getUserUserName(_user_id) %></span>
					</p>
					
					<p class="name">
						<span class="detail-label">Full Name:</span>
						<span class="detail-value"><%= Function.getUserName(_user_id) %></span>
					</p>
					
					<p class="email">
						<span class="detail-label">Email:</span>
						<span class="detail-value"><%= Function.getUserEmail(_user_id) %></span>
					</p>

					<p class="date-of-birth">
						<span class="detail-label">Date of Birth:</span>
						<span class="detail-value"><%= Function.getUserBirthdate(_user_id) %></span>
					</p>
				</section>
				</div>
				
				<div id="edit-profile" class="editingprofile">
					<%//<form id="new_profile" action="#" method="post">%>
						<div class="field">
							<label>Full Name</label>
							<input size="30" maxlength="50" name="name" id="name" type="text">
							<div class="buttons">
								<button id="namebutton" onclick="updateProfile('name',<%= _user_id %>);">Save Name</button>
							</div><br>&nbsp;
						</div>
                                                <form id="new_profile" action="update_profile_photo.jsp" method="post" enctype="multipart/form-data">
						<div class="field">
							<label>New Avatar</label>
							<input name="avatar" id="avatar" type="file" accept="image/jpeg">
							<div class="buttons">
								<button type="submit" id="avatarbutton" >Save Avatar</button>
							</div><br>&nbsp;
						</div>
                                                </form>
						<div class="field">
							<label>Tanggal Lahir Baru</label>
							<input size = "30" name="tanggal_lahir" id="tanggal_lahir" type="date">
							<div class="buttons">
								<button onclick="updateProfile('tanggal_lahir',<%= _user_id %>);">Save Tanggal lahir</button>
							</div><br>&nbsp;
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
							<button type="submit" name="ganti_pass" id="submitPass" onclick="updateProfile('password',<%= _user_id %>);">Save Password</button>
						</div>
					<% if (_user_id.compareTo(user_id.toString()) == 0) {%>
							<br>
						</div>
					<% //</form> %>
					<% } %>
				</div>

				<div class="primary2">
					<section class="tasks current" id="activeTask1">
					
					</section>

					<section class="tasks completed" id="doneTask1">

					</section>
				</div>
			
				
			
			</div>
		</div>
<%@include file="inc/footer.jsp" %>