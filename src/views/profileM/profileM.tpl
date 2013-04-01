<?php include './includes/header.inc.php' ?>
                <h1>User Profile</h1>
                <div class="profile">
					<form name="updateprof" method="post" enctype="multipart/form-data" >
						<div class="profiledetail" id="profileContent">
							  <div><div class="rincianLabelProfile" > Name </div>
								<div id="usernameDisplayDiv" ><?php echo $username;?></div>
								<div id="nameDisplayDiv" name="fullname"><strong id="Fullnama"><?php echo $fullname?></strong> <a href="#">@<?php echo $username?></a></div>
							  </div>	
							  <div>
								<div class="rincianLabelProfile" > Email </div> 
								<div id="emailDisplayDiv"><strong id="emailValue"><?php echo $email?></strong></div>
							  </div>

							  <div><div class="rincianLabelProfile">Born date </div>					  
									<div class="rincianDetailProfile" id="birthDisplayDiv" class="inlineblock" ><strong id="birthDay"><?php echo $tgl_lahir?></strong></div>
							  </div>
							  
							  <div>
								<div class="rincianLabelProfile" > Major </div> 
								<div> <strong>Teknik Informatika</strong> </div> 
							  </div>   
							  
							  <div>
							  </div> 
							  <div><div class="rincianLabelProfile" id="oldpassEditLabel"> Old Password </div>
								
							  </div>
							  
							  <div>
								<div id="changeAvatarLabel" class="rincianLabelProfile">Avatar</div>
								
							  </div>		
						</div>
					</form>
				<div class="profilepict"><img src="images/avatars/<?php echo $avat?>" width="200" height="200" alt="Edo Thobi Bram"/></div>
                </div>
				
				<div id="tasklist">

				</div>
<?php include './includes/footer.inc.php' ?>
