<?php
$newava = $_GET['newava'];
$userid = $_GET['userid'];

$file = $newava;
$extension = end(explode(".", $file));
$newfile = "../avatar/$userid.".$extension;

if (!copy($file, $newfile)) {
    echo "failed to copy $file...\n";
}else{
	echo $newava."ad".$userid;
}
?>