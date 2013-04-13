<?php

session_start();
$kode=$_SESSION['login'];
$task= $_GET['task'];
//$namatask= $_POST['namatask'];
//$tag = $_POST['tag'];
//$assignee = $_POST['assignee'];
//echo "oke";

//$assignees = (explode(",",$assignee));
//$tags = (explode(",",$tag));

$con = mysql_connect ('localhost', 'progin', 'progin');
if (!$con)
  {
  echo "Failed to connect to MySQL: " . mysql_error();
  }
  
mysql_select_db("progin", $con);


$id = mysql_query("SELECT IDTask FROM assignee WHERE asignee.IDUser='".$kode."'");
$data = mysql_query("SELECT * FROM task WHERE ID='".$id."'");
$numtaskquery = mysql_fetch_array(mysql_query("SELECT COUNT(*) as num FROM task"));
$lastnumtask = $numtaskquery['num'];
$newnumtask = $lastnumtask+1;

$idToInsert='';
if ($newnumtask<10)
	$idToInsert= "U00" . $newnumtask;
if ($newnumtask<100)
	$idToInsert= "U0" . $newnumtask;
else 
	$idToInsert= "U" . $newnumtask;
	
/*$sql=mysql_query("INSERT INTO task (ID,IDCreator,Nama, Status, Deadline)
		VALUES ('".$idToinsert."','".$kode."','".$namatask."','0','".$_POST[deadline]."')");

$sql2=mysql_query("INSERT INTO assignee (IDTask, IDUser) 
		// VALUES ('".$idToInsert."','".$kode."')");
  
//$id="SELECT ID FROM task WHERE ID= ";

foreach ($assignees as $ass){
	$data2 = mysql_query("INSERT INTO assignee (IDTask,IDUser) VALUES ('".$idToInsert."','".$ass."')");
}

foreach ($tags as $t){
	$data2 = mysql_query("INSERT INTO tags(IDTask, Tag) VALUES('".$idToInsert."','".$t."')");
}
 
/*foreach ($_FILES["attachment"]["error"] as $key => $error) {
    if ($error == UPLOAD_ERR_OK) {
			$tmp_name = $_FILES["attachment"]["tmp_name"][$key];
			$name = $_FILES["attachment"]["name"][$key];
			move_uploaded_file($tmp_name, "attachment"."/".$name);
			
			$uploaddir="./attachment/";
			$alamatfile=$uploaddir.$name;
			
			$j = 1;
			$dummy = mysql_query("SELECT * FROM attachment WHERE kodeuser='$kodeuser' AND kodetugas='$i'");
			while($baris=mysql_fetch_array($dummy))
			{
				$j++;
			}
			}
			
			$data = mysql_query("INSERT INTO attachment VALUES('$kodeuser','$i','$j','$alamatfile')");	
	}*/	

 
mysql_close($con);

?>

