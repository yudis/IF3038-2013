<?php

$f = $_GET["f"];
session_start();


echo '				  <div><div class="rincianLabelProfile" > Name </div>
						<div id="nameDisplayDiv" name="fullname" >'. $_SESSION["user"]["full_name"].' <a href="#">@edwt</a></div>
						<div id="nameEditDiv"><input type="text" id="fname" name="fname" value="'.$_SESSION["user"]["full_name"].'" onkeyup="validateFullName();" onchange="validateFullName();"/></div>
					  </div>	
                      <div>
						<div class="rincianLabelProfile" > Email </div> 
						<div id="emailDisplayDiv"><strong> '. $_SESSION["user"]["email"].'</strong></div>
					  </div>

                      <div><div class="rincianLabelProfile">Born date </div>					  
							<div class="rincianDetailProfile" id="birthDisplayDiv" class="inlineblock" >'. $_SESSION["user"]["tgl_lahir"].'</div>
                            <div class="rincianDetailProfile" id="birthEditDiv" >
                                <input type="date" id="Bday" name="Bday" value="'. $_SESSION["user"]["tgl_lahir"].'" onkeyup="validateBday();" onchange="validateBday();"/>
                            </div>
					  </div>
					  
                      <div>
						<div class="rincianLabelProfile" > Major </div> 
						<div> <strong>Teknik Informatika</strong> </div> 
					  </div>   
					  
                      <div>
						<div class="rincianLabelProfile" > Last Login </div> 
						<div> <strong>Sabtu, 16 Februari 2013, 17:01 WIT</strong> </div>
					  </div> 
					  <div><div class="rincianLabelProfile" id="passEditLabel"> New Password </div>
						<div id="passEditDetail"><input type="password" id="pwd" name="pwd" value="" onkeyup="validatePassword();" onchange="validatePassword();"/></div>
					  </div>						  
					  <div><div class="rincianLabelProfile" id="repassEditLabel"> rePassword </div>
						<div id="repassEditDetail"><input type="password" id="pwd_confirm" name="pwd_confirm" value="" onkeyup="validateRePassword();" onchange="validateRePassword();"/></div>
					  </div>
					  
					  <div>
						<div id="changeAvatarLabel" class="rincianLabelProfile">Avatar</div>
						<div id="changeAvatarDetail"><input type="file" id="ava" name="ava" accept="image/*" required="required"  onkeyup="validateAvatar();" onchange="validateAvatar();" /></div>
					  </div>
                      <div id="editButton" class="edit_button"><button type="button" onclick="return editProfile()">Edit</button> </div>
					  <div id="doneButton" class="done_button"><button type="button" onclick="return saveProfile()">Done</button> </div>
';
