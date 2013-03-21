<?php ?>

<div id="inputregister">
				<form id="regForm" method="post" action="register.php" enctype="multipart/form-data">
					<input type="text" id="regusername" name="regusername" pattern="^.{5,}$" required><img id="valid1" src=""><br>
					<input type="text" id="regname" name="regname" pattern="^.+ .+$" required><img id="valid2" src=""><br>
					<input type="date" id="regdate" name="regdate" onchange="dateChange();"><img id="valid7" src=""><br>
					<input type="password" id="regpassword1" name="regpassword1" pattern="^.{8,}$" required><img id="valid3" src=""><br>
					<input type="password" id="regpassword2" name="regpassword2" pattern="^.{8,}$" required><img id="valid4" src=""><br>
					<input type="text" id="regemail" name="regemail" pattern="^.+@.+\...+$" required><img id="valid5" src=""><br>
					<input type="file" id="regfile" name="regfile" onchange="checkImage();"><img id="valid6" src=""><br>
					<input type="submit" id="regbutton" name="submit" value="Register" disabled="disabled">
				</form>
</div>
<script type="text/javascript" src="script.js"/>