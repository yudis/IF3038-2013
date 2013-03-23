<?php

	$q = $_GET['q'];
	$opt = $_GET['opt'];
	$usr = $_GET['usr'];
	header("Content-type: text/xml");
	
	$xml = new SimpleXMLElement("<xml/>");

	$con = mysql_connect("localhost","progin","progin");
	mysql_select_db("progin",$con);
	
	if($opt == 'a' || $opt == 'u'){
		$sq1 = "SELECT username,fullname FROM user WHERE username LIKE '%".$q."%' OR fullname LIKE '%".$q."%' OR email LIKE '%"
		.$q."%' OR dob LIKE '%".$q."%'";
		$re1 = mysql_query($sq1);
		print mysql_error($con);
		
		if(mysql_affected_rows($con)>0){
			while($v = mysql_fetch_array($re1)){	
				$data = $xml->addChild("User");
				$data->addChild("ID",$v[0]);
				$data->addChild("String",$v[1]);
			}
		}
	}
	
	if($opt == 'a' || $opt == 'c'){
		$sq1 = "SELECT id_category,name FROM category WHERE name LIKE '%".$q."%'";		
		$re1 = mysql_query($sq1);
		print mysql_error($con);
		
		if(mysql_affected_rows($con)>0){
			while($v = mysql_fetch_array($re1)){	
				$data = $xml->addChild("Category");
				$data->addChild("ID",$v[0]);
				$data->addChild("String",$v[1]);
			}
		}
	}
	
	if($opt == 'a' || $opt == 't'){
		$sq1 = "SELECT DISTINCT t.id_task, t.name FROM task AS t 
		INNER JOIN ttrelation AS tt
		ON t.id_task = tt.id_task
		INNER JOIN tag AS ta
		ON tt.id_tag = ta.id_tag
		INNER JOIN utrelation AS u
		ON t.id_task = u.id_task
		WHERE (t.name LIKE '%".$q."%' OR ta.name LIKE '%".$q."%') AND u.username = '".$usr."'";		
		$re1 = mysql_query($sq1);
		print mysql_error($con);
		
		if(mysql_affected_rows($con)>0){
			while($v = mysql_fetch_array($re1)){	
				$data = $xml->addChild("Task");
				$data->addChild("ID",$v[0]);
				$data->addChild("String",$v[1]);
			}
		}
	}
	
	print ($xml->asXML());
	
?>