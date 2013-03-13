<!DOCTYPE html>
<html> 
    <head>
    </head>
    <body>
       <?PHP
	   		// Create connection
			$con=mysqli_connect("localhost","progin","progin","progin_405_13510035");
			
			// Check connection
			if (mysqli_connect_errno($con))
			  {
			  echo "Failed to connect to MySQL: " . mysqli_connect_error();
			  }
			
			$string = $_GET["s"];  
			$outside = $_GET["oo"]; 
	   		$isi_open = $_GET["o"];
			$isi_close = $_GET["c"];
			$mode = $_GET["m"];
			
			if($mode=='1'){
				$result = mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'");
				while($row = mysqli_fetch_array($result))
			  	{
				  $temp = $row['username'];
				  echo $outside;
				  echo $isi_open;
				  echo $temp;
				  echo $isi_close;
				  echo "<div>";
				  echo "Fullname : ";
				  echo $row['fullname'];
				  echo "</div>";
				  echo $isi_close;
			  	}
	   		}else if ($mode=='2'){
				$result = mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%string%'");
				
				while($row = mysqli_fetch_array($result))
			  	{
				  $temp = $row['category_name'];
				  echo $outside;
				  echo $isi_open;
				  echo $temp;
				  echo $isi_close;
				  echo $isi_close;
			  	}
			}else if ($mode=='3'){
				$result = mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'");
				
				while($row = mysqli_fetch_array($result))
			  	{
				  echo $outside;
				  echo $isi_open;
				  echo $row['task_name'];
				  echo $isi_close;
				  echo "<div>";
				  echo "Deadline : ";
				  echo $row['deadline'];
				  echo "</div>";
				  echo "<div>";
				  /*echo "Tag : ";
				  echo $row['tag'];*/
				 echo "</div>";
				  echo "<div>";
				  echo "Status : ";
				  if ($row['status']=='1'){
					  echo "sudah selesai";
				  }else{
					  echo "belum selesai";
				  }
				  echo "</div>";
				  echo $isi_close;
			  	}
			}else if ($mode=='4'){
				
			}else{
				if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'")) > 0){
					echo "<h2>Username</h2>";
					$result = mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'");
					while($row = mysqli_fetch_array($result))
					{
					  $temp = $row['username'];
					  echo $outside;
					  echo $isi_open;
					  echo $temp;
					  echo $isi_close;
					  echo "<div>";
					  echo "Fullname : ";
					  echo $row['fullname'];
					  echo "</div>";
					  echo $isi_close;
					}
				}
				
				if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'"))>0){
					echo "<h2>Category</h2>";
					$result = mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'");
					
					while($row = mysqli_fetch_array($result))
					{
					  $temp = $row['category_name'];
					  echo $outside;
					  echo $isi_open;
					  echo $temp;
					  echo $isi_close;
					  echo $isi_close;
					}
				}
				
				if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'"))>0){
					echo "<h2>Task</h2>";
					$result = mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'");
					
					while($row = mysqli_fetch_array($result))
					{
					  echo $outside;
					  echo $isi_open;
					  echo $row['task_name'];
					  echo $isi_close;
					  echo "<div>";
					  echo "Deadline : ";
					  echo $row['deadline'];
					  echo "</div>";
					  echo "<div>";
					  /*echo "Tag : ";
					  echo $row['tag'];*/
					 echo "</div>";
					  echo "<div>";
					  echo "Status : ";
					  if ($row['status']=='1'){
						  echo "sudah selesai";
					  }else{
						  echo "belum selesai";
					  }
					  echo "</div>";
					  echo $isi_close;
					}
				}
			}
			
			mysqli_close($con);
	   ?>
    </body>    
</html>
