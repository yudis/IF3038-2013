<?php
header('Content-type: application/x-font-woff');
echo file_get_contents($_GET['filename']);
?>