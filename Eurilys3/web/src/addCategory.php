<?php
	include "connect.php";
	$assignees = $_GET['assignee'];
	$category = $_GET['category'];
	$user = $_COOKIE['name'];
	$arrayA = explode(",",$assignees);
	
	function rand_string( $length ) {
		$str = "";
		$chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";	
		$size = strlen( $chars );
		for( $i = 0; $i < $length; $i++ ) {
			$str .= $chars[ rand( 0, $size - 1 ) ];
		}
		return $str;
	}
	$idUnik = true;
	$idCategory = rand_string(8);
	$result = mysql_query("SELECT * FROM category WHERE idcat ='".$idCategory."'");
	if (mysql_fetch_array($result)>0) $idUnik = false;
	while(!$idUnik){
		$idCategory = rand_string(8);
		$result = mysql_query("SELECT * FROM category WHERE idcat ='".$idCategory."'");
		if (mysql_fetch_array($result)>0) $idUnik = false;
		else $idUnik = true;
	}
	
	$query = "INSERT INTO category (idcat,username,catname)
            VALUES(
                '".$idCategory."'
                ,'".$user."'
                ,'".$category."'
                );";
	mysql_query($query);
	
	$query = "INSERT INTO able (username,idcat)
            VALUES(
                '".$user."'
                ,'".$idCategory."'
                );";
		mysql_query($query);
	
	for($i=0; $i<count($arrayA); $i++){
		$query = "INSERT INTO able (username,idcat)
            VALUES(
                '".$arrayA[$i]."'
                ,'".$idCategory."'
                );";
		mysql_query($query);
	}
?>