<html>
<head>
</head>
<body>
	<?php
		$uname=$_GET["uname"];
		$email=$_GET["email"];

		$con = mysql_connect('localhost', 'progin', 'progin');
		if (!$con)
		{
			die('Could not connect: ' . mysql_error());
		}

		mysql_select_db("progin_405_13510029", $con);

		$sql="SELECT * FROM user WHERE username='".$uname."' OR email='".$email."'";

		$result = mysql_query($sql);
		
		$row = mysql_fetch_array($result);

		if ($row==NULL)
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