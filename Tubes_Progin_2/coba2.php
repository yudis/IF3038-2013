<html>
<head><title></title></head>
<body onload="SlideShow();">
	<form action="register.php" method="post">
					<input type="text" id="regusername" pattern="^.{5,}$" required><img id="valid1" src=""><br>
					<input type="text" id="regname" pattern="^.+ .+$" required><img id="valid2" src=""><br>
					<input type="date" id="regdate"><br>
					<input type="password" id="regpassword1" pattern="^.{8,}$" required><img id="valid3" src=""><br>
					<input type="password" id="regpassword2" pattern="^.{8,}$" required><img id="valid4" src=""><br>
					<input type="text" id="regemail" pattern="^.+@.+\...+$" required><img id="valid5" src=""><br>
					<input type="file" id="regfile" onchange="checkImage();"><img id="valid6" src=""><br>
				</form>
</body>
<script type="text/javascript" src="script.js"></script>
</html>