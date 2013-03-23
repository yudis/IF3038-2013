<?php
	include "login.php";
	require "config.php";
	$username = $_SESSION['username'];
	$text = $_GET['q'];
	$tipefilter = $_GET['t'];
	$posisi = $_GET['p'];
	$hint = array();
	$tambahan = "";
	if ( $tipefilter == "all result")
	{
		$sql = "SELECT DISTINCT task.id_task as id_task,task.name as name,task.deadline as deadline, task.status as status FROM (task LEFT OUTER JOIN tasktag ON task.id_task = tasktag.id_task) LEFT OUTER JOIN tag ON tasktag.id_tag = tag.id_tag LEFT OUTER JOIN comment ON task.id_task = comment.id_task LEFT OUTER JOIN assignee ON task.id_task = assignee.id_task WHERE (task.name LIKE '%$text%' or tag.name LIKE '%$text%' or comment.content LIKE '%$text%') AND assignee.username = '$username'";
		$user = mysqli_query($con,$sql);
		$k = 0;
		$j = 0;
		$hint [$j] = "";
		$hint [$j] .= "task";
		while (($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hint[$j] .= "<br>".$current_user['id_task'].",".$current_user['name'].",".$current_user['deadline'].",".$current_user['status'];
			
			$idtask = $current_user['id_task'];
			$sql2 = "SELECT name FROM tag WHERE id_tag in (SELECT id_tag FROM tasktag WHERE id_task='$idtask')";
			$user2 = mysqli_query($con,$sql2);
			$tag = array();
			while(($user2 != null) && ($data2 = mysqli_fetch_array($user2)))
			{
				$tag[] = $data2['name'];
			}
			for($i = 0; $i < count($tag); $i++)
			{
				$hint[$j] .= ",".$tag[$i];
			}
			
			if (($k+1) % 10 == 0)
			{
				$j++;$hint [$j] = "task";
			}
			$k++;
		}
		$hint [$j] .= "<x>";
		$hint [$j] .= "username";
		$sql = "SELECT DISTINCT username,fullname,avatar FROM user WHERE username LIKE '%$text%' or fullname LIKE '%$text%' or birthday LIKE '%$text%' or email LIKE '%$text%'";
		$user = mysqli_query($con,$sql);
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hint[$j] .= "<br>".$current_user['username'].",".$current_user['fullname'].",".$current_user['avatar'];
			if (($k+1) % 10 == 0)
			{
				$j++;$hint[$j]="username";
			}
			$k++;
		}
		$hint [$j] .= "<x>";
		$hint [$j] .= "kategory";
		$sql="SELECT DISTINCT name FROM category WHERE name LIKE '%$text%' AND id_cat in (SELECT id_cat FROM joincategory WHERE username='$username') UNION SELECT name FROM category WHERE id_cat in (SELECT id_cat FROM categorycreator WHERE name LIKE '%$text%' AND username='$username')";
		$user = mysqli_query($con,$sql);
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hint[$j] .= "<br>".$current_user['name'];
			if (($k+1) % 10 == 0)
			{
				$j++;$hint[$j]="kategori";
			}
			$k++;
		}
		//$hint [$j] .= "<x>";
		if ($posisi < $j)
		{
			$num = $posisi + 1;
		} else $num = $posisi;
		if ($posisi > 0){
			$numback = $posisi;
		}
		else $numback = 0;
		$tambahan .= "<x>".$numback.",".$num;
	}
	else if ($tipefilter == "username")
	{	
		$sql = "SELECT DISTINCT username,fullname,avatar FROM user WHERE username LIKE '%$text%' or fullname LIKE '%$text%' or birthday LIKE '%$text%' or email LIKE '%$text%'";
		$user = mysqli_query($con,$sql);
		$k = 0;
		$j = 0;
		$hint [$j] = "";
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hint[$j] .= "<br>".$current_user['username'].",".$current_user['fullname'].",".$current_user['avatar'];
			if (($k+1) % 10 == 0)
			{
				$j++;$hint [$j] = "";
			}
			$k++;
		}
		
		if ($posisi < $j)
		{
			$num = $posisi + 1;
		} else $num = $posisi;
		if ($posisi > 0){
			$numback = $posisi;
		}
		else $numback = 0;
		$tambahan .= "<br>".$numback.",".$num;
	}
	else if ($tipefilter == "category")
	{
		$sql="SELECT DISTINCT name FROM category WHERE name LIKE '%$text%' AND id_cat in (SELECT id_cat FROM joincategory WHERE username='$username') UNION SELECT name FROM category WHERE id_cat in (SELECT id_cat FROM categorycreator WHERE name LIKE '%$text%' AND username='$username')";
		$user = mysqli_query($con,$sql);
		$k = 0;
		$j = 0;
		$hint [$j] = "";
		while(($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hint[$j] .= "<br>".$current_user['name'];
			if (($k+1) % 10 == 0)
			{
				$j++;$hint [$j] = "";
			}
			$k++;
		}
		if ($posisi < $j)
		{
			$num = $posisi + 1;
		} else $num = $posisi;
		if ($posisi > 0){
			$numback = $posisi;
		}
		else $numback = 0;
		$tambahan .= "<br>".$numback.",".$num;
	}
	else if ($tipefilter == "task")
	{
		$sql = "SELECT DISTINCT task.id_task as id_task,task.name as name,task.deadline as deadline, task.status as status FROM (task LEFT OUTER JOIN tasktag ON task.id_task = tasktag.id_task) LEFT OUTER JOIN tag ON tasktag.id_tag = tag.id_tag LEFT OUTER JOIN comment ON task.id_task = comment.id_task LEFT OUTER JOIN assignee ON task.id_task = assignee.id_task WHERE (task.name LIKE '%$text%' or tag.name LIKE '%$text%' or comment.content LIKE '%$text%') AND assignee.username = '$username'";
		$user = mysqli_query($con,$sql);
		$k = 0;
		$j = 0;
		$hint [$j] = "";
		while (($user != null) && ($current_user = mysqli_fetch_array($user)))
		{
			$hint[$j] .= "<br>".$current_user['id_task'].",".$current_user['name'].",".$current_user['deadline'].",".$current_user['status'];
			
			$idtask = $current_user['id_task'];
			$sql2 = "SELECT name FROM tag WHERE id_tag in (SELECT id_tag FROM tasktag WHERE id_task='$idtask')";
			$user2 = mysqli_query($con,$sql2);
			$tag = array();
			while(($user2 != null) && ($data2 = mysqli_fetch_array($user2)))
			{
				$tag[] = $data2['name'];
			}
			for($i = 0; $i < count($tag); $i++)
			{
				$hint[$j] .= ",".$tag[$i];
			}
			if (($k+1) % 10 == 0)
			{
				$j++;$hint [$j] = "";
			}
			$k++;
		}
		if ($posisi < $j)
		{
			$num = $posisi + 1;
		} else $num = $posisi;
		if ($posisi > 0){
			$numback = $posisi;
		}
		else $numback = 0;
		$tambahan .= "<br>".$numback.",".$num;
	}
	//output the response
	echo $hint[$posisi].$tambahan;
?>