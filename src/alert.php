<?php
	// kegunaan: alert seperti javascript
	function alert($text)
	{
		echo "<script type='text/javascript'> alert('$text'); </script>";
	}
?>