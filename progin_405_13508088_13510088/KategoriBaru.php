<!DOCTYPE html>
<html lang="en">
	<head>
        <title>Create New Task -- CanDo</title>
        <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0;">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="style.css" type="text/css"/>
    </head>
	
	<script src="DashboardPage.js"></script>
	<body>
		 <div class ="header" id="headerother">
				<img src="images/Logo.png" class="logosmall">
				
		</div>	
			<form name="Kategori" method="post" accept-charset="utf-8" onsubmit="tutup()">
						<p>
							<label for="kategori">Nama Kategori</label>
							<input type="text" name="Kategori" placeholder="kategori tugas" required>
							<br>
							<label for="Admin Assignee">Admin Assigne (pisahkan dengan ';')</label>
							<input type="text" name="assignee" placeholder="A1;A2;A3" required>  
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
