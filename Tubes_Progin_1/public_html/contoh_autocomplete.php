<html>

	<head>

	</head>

	<body>
		
		<!-- Jangan lupa di add cssnya, kalo nggak hasilnya ancur2an!!!!-->
		<link rel="stylesheet" type="text/css" href="autocomplete.css">

		<form method='POST' action='handle.php'/>
		
		<!-- Lihat bagian onkeyup sama onfocusin,ikutin aja. Bagian yang getdata.php itu adalah script php yang ngehasilin xmlnya-->
		<input type='text' name='input_f' id='input_f' onkeyup="multiAutocomp(this,'contoh_getdata.php');" onfocusin="multiAutocompClearAll()"/>
		<br/>
		<input type='text' name='test_f' id='test_f' onkeyup="autocomp(this,'contoh_getdata.php');" onfocusin="autocompClearAll()"/>
		<br/>
		<input type='submit' value='Submit'/>
		</form>

		<!-- Library javascript yang musti diinclude coy -->
		<script src="multiautocomplete.js"></script>
		<script src="autocomplete.js"></script>
		
	</body>

</html>