<?php

session_start();
$kode=$_SESSION['login'];
$tag = $_POST['tag'];
$assignee = $_POST['assignee'];

$assignees = (explode(",",$assignee));
$tags = (explode(",",$tag));

$con = mysqli_connect ('localhost', 'progin', 'progin');
if (mysqli_connect_errno($con))
  {
  echo "Failed to connect to MySQL: " . mysqli_connect_error();
  }
  
mysql_select_db("progin", $con);


$id = mysql_query("SELECT IDTask FROM assignee WHERE asignee.IDUser='".$kode."'");
$data = mysql_query("SELECT * FROM task WHERE ID='".$id."'");
$numtaskquery = mysqli_fetch_array(mysqli_query("SELECT COUNT(*) as num FROM task"));
$lastnumtask = $numtaskquery['num'];
$newnumtask = $lastnumtask+1;

if ($newnumtask<10) then
	$idToInsert= "U00".$newnumtask;
else if if ($newnumtask<100) then
	$idToInsert= "U0".$newnumtask;
else 
	$idToInsert= "U".$newnumtask;
	
$sql=mysqli_query("INSERT INTO task (ID,IDCreator,Nama, Status, Deadline)
		VALUES ('".$idToinsert."','".$kode."','".$_POST[namatask]."','0','".$_POST[deadline]."')");

//$sql2=mysqli_query("INSERT INTO assignee (IDTask, IDUser) 
	//	VALUES ("$idToInsert","$kode")");
  
//$id="SELECT ID FROM task WHERE ID= ";

foreach ($assignees as $ass){
	$data2 = mysqli_query("INSERT INTO assignee (IDTask,IDUser) VALUES ("$idToInsert","$ass")");
}

foreach ($tags as $t){
	$data2 = mysqli_query("INSERT INTO tags(IDTask, Tag) VALUES("$idToInsert","$t")");
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
	}	
}*/
 
mysqli_close($con);

?>

<SCRIPT LANGUAGE="JavaScript">
			window.alert ("Tugas berhasil ditambahkan");
			setTimeout("location.href = 'home.php?link=halamanprofil&username=<?php echo $username; ?>';",1);
</SCRIPT> 