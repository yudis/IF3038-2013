<!DOCTYPE HTML>
<html>
	<?php
		$kategori = $_GET['kategori'];
	?>
	<head>
		<link href="css/main.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" type="text/css" media="all" href="css/jsDatePick_ltr.min.css" />
		<script type="text/javascript" src="js/jsDatePick.min.1.3.js"></script>
        <script type="text/javascript" src="js/main.js"></script>        
	</head>
	<body onload="kalender_deadline(); loadRegex()">
	<h1>Tugas Baru</h1>
		<form action="createtask_process.php" method="post" enctype="multipart/form-data">
            <div>
                <input id="nama_tugas" class="input_field" type="text" name="task_name" placeholder="Nama Tugas" onkeyup="validate_nama_tugas()">&nbsp;
                <strong id="error_nama_tugas" class="error_warning"></strong>
            </div>
            <div>
                <input id="attachment" name="attachment[]" class="input_field mouseover_effect" type="file" multiple onchange="validate_attachment()">&nbsp;
                <strong id="error_attachment" class="error_warning"></strong>
            </div>
            <div>
                <input type="hidden">
                <input class="input_field" type="text" name="assignee" placeholder="Nama Penanggungjawab" onkeyup="userhint(this.value)">&nbsp;
                <strong id="error_assignee" class="error_warning"></strong>
            </div>
            <div>
                <input id="tag" class="input_field" type="text" name="tag" placeholder="Tag">&nbsp;
                <strong id="error_tag" class="error_warning"></strong>
            </div>
            <div>
                <input id="input_deadline" name="deadline" class="input_field" type="text" name="deadline" placeholder="Deadline">&nbsp;
                <strong id="error_input_deadline" class="error_warning"></strong>
            </div>
		<input type="hidden" name="kategori" value=<? echo $kategori; ?>>
		<input class="input_button" type="submit" name="simpan" value="Simpan" onclick="validate()"><br>
		</form>
    </body>
</html>