<?php
	require "config.php";
	$id_task = $_GET['idtask'];
	//dummy here
	$username = "ArieDoank";
	$deadline = $_POST['deadline'];
	$assignee = $_POST['Assignee'];
	$updatedeadline = "UPDATE task SET deadline='$deadline' WHERE id_task='$id_task'";
	mysqli_query($con,$updatedeadline);
	//id cat
	$search_cat_sql = "SELECT id_cat FROM task where id_task='$id_task'";
	$search_result = mysqli_query($con,$search_cat_sql);
	$cat = mysqli_fetch_array($search_result);
	$id_cat = $cat['id_cat'];
	
	//delete semua assignee yg berhub dgn task kecuali task creator
	$delassquery = "DELETE FROM assignee WHERE id_task='$id_task'";
	mysqli_query($con,$delassquery);
	//cari creator
	$searchcreator = "SELECT pemilik from task where id_task='$id_task'";
	$creator_result = mysqli_query($con,$searchcreator);
	$creator = mysqli_fetch_array($creator_result);
	$id_creator = $creator['pemilik'];
	
	$sqlassignee = "INSERT INTO assignee(`username`, `id_task`) VALUES ('$id_creator','$id_task')";
	mysqli_query($con,$sqlassignee);
	
	//delete semua tag yg berhub dgn task
	$deltagquery = "DELETE FROM tasktag WHERE tasktag.id_task='$id_task'";
	mysqli_query($con,$deltagquery);
	$array_assignee = explode(",",$assignee);
	$arraylength1 = count($array_assignee);
	for($i=0;$i<$arraylength1-1;$i++)
		{
			$sqlassignee = "REPLACE INTO assignee SET username='$array_assignee[$i]', id_task='$id_task'";
			mysqli_query($con,$sqlassignee);
			//insert ke kategori
			$sqljoincat = "REPLACE INTO joincategory SET username='$array_assignee[$i]', id_cat='$id_cat'";
			mysqli_query($con,$sqljoincat)
		}
	
	$tag = $_POST['tag'];
	$array_tag = explode(",",$tag);
	$arraylength2 = count($array_tag);
	for($j=0;$j<$arraylength2;$j++)
		{
			$searchtagsql = "SELECT name FROM tag WHERE name='$array_tag[$j]'";
			if($getsearchtagresult = mysqli_query($con,$searchtagsql)){
				$row_count = mysqli_num_rows($getsearchtagresult);
			}
			if($row_count===0){
				$sqltag = "INSERT INTO tag(`name`) VALUES ('$array_tag[$j]')";
				mysqli_query($con,$sqltag);
			}
			//cari id_tag
			$search_idtag_sql = "SELECT id_tag FROM tag where name='$array_tag[$j]'";
			$search_idtag_result = mysqli_query($con,$search_idtag_sql);
			$searchtag = mysqli_fetch_array($search_idtag_result);
			$id_tag = $searchtag['id_tag'];
			//insert tasktag
			$sqltasktag = "INSERT INTO tasktag(`id_task`, `id_tag`) VALUES ('$id_task','$id_tag')";
			mysqli_query($con,$sqltasktag);
		}
	header('Location: createtask.php');
?>