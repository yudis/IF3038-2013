<html>
	<head>
		<title>Shared To Do List - Profile</title>
		<link rel="stylesheet" type="text/css" href="../style.css">
		<link rel="shortcut icon" href="favicon.ico">		
		<script type="text/javascript" src="../ajax.js"></script>
		<script type="text/javascript" src="../mpquery.js"></script>
		<script type="text/javascript" src="../validation.js"></script>
		<script type="text/javascript" src="profilecontroller.js"></script>
	</head>
	<body onload="checkLogged(); loadProfile('<%=request.getParameter("u")%>', '<%=request.getParameter("e")%>');">
		<div id="navsearch">
		</div>
		<div class="clearall container topcontainer" id="profile container">
			<div class="clearall container">				
				<div id="initprofile"><h4>Now Loading...</h4></div>
				
			</div>
			<div class="clearall box">
			</div>
		</div>
	</body>
</html>