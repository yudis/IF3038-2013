<!--
To change this template, choose Tools | Templates
and open the template in the editor.
-->
<!DOCTYPE html>
<html>
    <head>
        <title>BANG! Where Bounty Hunters Meet</title>
		<?php
			require "config.php";
		?>
    </head>
    <body>
        <?php
			$sql = "SELECT * FROM user WHERE username = 'endiajah'";
			$result = mysqli_query($con,$sql);
			$row = mysqli_fetch_array($result);
			echo $row['fullname'];
		?>
    </body>
</html>
