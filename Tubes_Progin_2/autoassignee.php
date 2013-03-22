<?php
$q=$_GET["q"];
require "config.php";

if(strlen($q) > 0){
	$hint="";
	$sql="SELECT username FROM user WHERE username LIKE '%$q%'";
	$user = mysqli_query($con,$sql);
	$hasiluser = array();
	while(($user != null) && ($current_user = mysqli_fetch_array($user)))
	{
		$hasiluser[] = $current_user['username'];
	}
	
	if (count($hasiluser) > 0)
	{
		for($i=0; $i<count($hasiluser); $i++)
		{
			if (strtolower($q)==strtolower(substr($hasiluser[$i],0,strlen($q))))
			{
				$hint .= "<br>".$hasiluser[$i];
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