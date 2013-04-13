<!DOCTYPE html>
<?php session_start(); ?>
<html lang="en">
	<head>
        <title>Create New Task -- CanDo</title>
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
	
	<script src="DashboardPage.js"></script>
	<body>
		<?php include "header.php"?>
			<form name="Kategori" method="post" accept-charset="utf-8" onsubmit="tutup()">
						<p>
							<label for="kategori">Nama Kategori</label>
							<input type="text" name="Kategori" placeholder="kategori tugas" required>
							<br>
							<label>Assignee </label>
							<input type="text">
							<select>				
								<?php	
								$result = mysql_query('SELECT * FROM user');
								while($row = mysql_fetch_array($result))
									:?>
										<?php 
											$name = $row ['username'];
											$idrow = $row['id'];
											echo "<option value = \"$idrow\">$name</option>";					
										?>
								<?php	endwhile;
										
								?>
							</select>

							<br>
							<input type="submit" value="done" id="done" name="done" > 
							</p>
						</form>

			<div>

			</div>

			<footer>
				
			</footer>
		</div>
	</body>
</html>
