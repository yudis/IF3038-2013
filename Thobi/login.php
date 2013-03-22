<html>
<head>
</head>
<body>
	<?php
		$uname=$_GET["uname"];
		$pwd=$_GET["pwd"];

		$con = mysql_connect('localhost', 'root', 'rootadmin');
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}

		mysql_select_db("progin_405_13510035", $con);

		$sql="SELECT * FROM user WHERE username='".$uname."' AND password='".$pwd."'";

		$result = mysql_query($sql);
		
		$row = mysql_fetch_array($result);

		if ($row!=NULL)
		{
			echo "true";
		}
		else
		{
			echo "false";
		}

		mysql_close($con);
	?>
</body>
</html>