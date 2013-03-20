<?php

	//$q = "timo";
	$q = $_GET['q'];
	header("Content-type: text/xml");
	
	$xml = new SimpleXMLElement("<xml/>");

	$con = mysql_connect("localhost","progin","progin");
	mysql_select_db("progin",$con);
	$sq1 = "SELECT username,fullname FROM user WHERE username LIKE '%".$q."%' OR fullname LIKE '%".$q."%'";
	$re1 = mysql_query($sq1);
	print mysql_error($con);
	
	//ATTENTION PLEASE :)
	//Jangan ubah2 atribut addChild -> biarkan dia terisi dari Data, ID sama String
	//ID isinya adalah data yang mau lu masukin ke textbox / textarea dsb
	//String isinya adalah bagaimana lu mau data lu tampak di list pilihan autocompletenya
	//Bingung? Coba saja broo :) Feel free to ask
	
	/*Contoh jadinya : 
		<xml>
			<Data>
				<ID>nicholas</ID>
				<String>Nicholas Rio</String>
			</Data>
			<Data>
				<ID>timotius</ID>
				<String>Timotius Kevin Levandi</String>
			</Data>
			<Data>
				<ID>jeremy</ID>
				<String>Jeremy Joseph Hanniel</String>
			</Data>
		</xml>
	*/
	if(mysql_affected_rows($con)>0){
		while($v = mysql_fetch_array($re1)){	
			$data = $xml->addChild("Data");
			$data->addChild("ID",$v[0]);
			$data->addChild("String",$v[1]);
		}
	}
	
	print ($xml->asXML());
	
?>