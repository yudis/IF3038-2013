<?php
	
?>
<html>
    <head>
        <title> Next | Search Result </title>
        <link rel="stylesheet" href="css/css.css">
		<link rel="stylesheet" href="css/search_result.css">
		 <script src="js/search_result.js"></script>
	</head>
	
	<body onLoad="tampilkan_hasil('<?php echo $_GET["jenis"];?>','<?php echo $_GET["q"];?>')">
		<?php require_once("header.php"); ?>
		<div id="main">
			
		</div>
	</body>
</html>