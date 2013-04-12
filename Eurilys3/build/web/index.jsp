<%@include file="src/indexHeader.jsp"%>

<!-- Web Content -->
<section>
   <div id="index_left">
      <div id="tutorial_container">
         <div>
            <img id="tutorial_image" src="img/tutorial.png" alt="tutorial_pic"/>
         </div>
         <div id="tutorial">
            <span class="blue"> Eurilys </span> is an online to-do-list website which is easy to use, user-friendly, and the most important 
            thing of all, it's <span class="red">FREE</span>!
            <br><br>
            You can add task, category for each task, and you can write down all the task's details here. 
            <br><br>
            No more papers, pen, and confusion. <span class="blue"> Eurilys </span> is here to be your organization's partner.
         </div>
      </div>
   </div>
   <div id="index_right">
      <div id="login_form_container" class="left">
         <form id="login_form" name="login_form" method="post" action="Login">
            <label> Username </label> <input type="text" id="login_username" name="login_username" onkeydown="if (event.keyCode == 13) document.getElementById('login_button_submit').click()"/>
            <br/><label> Password </label> <input type="password" id="login_password" name="login_password" onkeydown="if (event.keyCode == 13) document.getElementById('login_button_submit').click()"/>
            <br/><label id="remember_me" class = "left"> Remember me </label>		
            <input type="checkbox" id="remember_me_check" class = "left" name="remember_me_checkbox" onen/> 				
            <div id="login_button_submit" class="right link_blue_big top10" onclick="loginCheck()"> Login </div>
         </form>
      </div>

      <div id="signup_label" class="left">
         Have No Account?
      </div>
      <div id="signup_form_container" class="right">
         <form name="signup_form" id="signup_form" method="post" action="ServletHandler?type=Register" enctype="multipart/form-data">
            <div class = "label_container left">
               <div class = "label_content_1 left">Username</div>	
               <div class = "label_content_2 left"><input type="text" name="signup_username" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" id="reg_username" title="Username should be at least 5 characters long" required></div>
               <div class = "label_content_3 left"><img src="img/none.png" id="username_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div class = "label_container left">
               <div class = "label_content_1 left">Password</div> 	
               <div class = "label_content_2 left"><input type="password" name="signup_password" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" id="reg_password" title="Password should be at least 8 characters long" required></div>
               <div class = "label_content_3 left"><img src="img/none.png" id="password_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div class = "label_container left">
               <div class = "label_content_1 left">Confirm</div> 	 	
               <div class = "label_content_2 left"><input type="password" name="signup_confirm_password" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" id="reg_confirm" title="Confirmation password should be the same with Password" required></div>
               <div class = "label_content_3 left"><img src="img/none.png" id="confirm_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div class = "label_container left">
               <div class = "label_content_1 left">Name</div> 	
               <div class = "label_content_2 left"><input type="text" name="signup_name" id="reg_name" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" title="Your name should be at least consists first name and last name" required></div>
               <div class = "label_content_3 left"><img src="img/none.png" id="name_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div class = "label_container left">
               <div class = "label_content_1 left">Email</div> 	 		
               <div class = "label_content_2 left"><input type="text" name="signup_email" id="reg_email" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" title="Email should be written in the right format" required></div>
               <div class = "label_content_3 left"><img src="img/none.png" id="email_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div class = "label_container left">
               <div class = "label_content_1 left">Birth Date</div> 	
               <div class = "label_content_2 left"><input type="date" name="signup_birthdate" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" id="reg_birthdate" title="YYYY-MM-DD"></div>
               <div class = "label_content_3 left"><img src="img/none.png" id="birthdate_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div class = "label_container left">
               <div class = "label_content_1 left">Avatar</div> 	
               <div class = "label_content_2 left"><input type="file" onkeyup="javascript:regCheck();" onchange="javascript:regCheck();" name="avatar_upload" id="avatar_upload"> </div>
               <div class = "label_content_3 left"><img src="img/none.png" id="avatar_validation" class="signup_form_validation right" alt="validation image"></div>
            </div>

            <div id="signup_button_submit" onclick="javascript:signup();" class="right link_blue_big_disabled top10 bold" title="Semua elemen form harus diisi dengan benar dahulu."> SIGN UP </div>
         </form>
      </div>
   </div>
</section>

<%@include file="src/footer.jsp"%>
</html>
