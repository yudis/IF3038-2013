<?php
$q=$_GET["q"];
require "config.php";

if(strlen($q) > 0){
	$hint="";
	$sql="SELECT name FROM tag WHERE name LIKE '%$q%'";
	$tag = mysqli_query($con,$sql);
	$hasiltag = array();
	while(($tag != null) && ($current_tag = mysqli_fetch_array($tag)))
	{
		$hasiltag[] = $current_tag['name'];
	}
	
	if (count($hasiltag) > 0)
	{
		for($i=0; $i<count($hasiltag); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasiltag[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasiltag[$i];
			}
		}
		//$hint.=",";
	}
	if ($hint == ""){
		$response="";
	}else{
		$response=$hint;
	}
	//output the response
	echo $response;
}

?>