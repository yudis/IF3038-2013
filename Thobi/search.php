<link rel="stylesheet" type="text/css" href="default.css" />
       <?PHP
				// Create connection
				$con=mysqli_connect("localhost","progin","progin","progin_405_13510029");
				$string = $_GET["s"];  
				$outside = $_GET["oo"]; 
				$isi_open = $_GET["o"];
				$isi_close = $_GET["c"];
				$mode = $_GET["m"];
				// Check connection
				if (mysqli_connect_errno($con))
				  {
				  echo "Failed to connect to MySQL: " . mysqli_connect_error();
				  }
				if (ISSET($_GET['page'])){
					// If page is set, lets get it
					$page =  intval($_GET['page']);
				}else{
					$page = 1;
				}
				  
				if($mode=='1'){
					$result = mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'");
					$num = mysqli_num_rows($result);
				}else if ($mode=='2'){
					$result = mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'");
					$num = mysqli_num_rows($result);
				}else if ($mode=='3'){
					$result = mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'");
					$num = mysqli_num_rows($result);
				}else if ($mode=='4'){
					$result = mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%$string%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id");
					$num = mysqli_num_rows($result);
				}else{
					$num = 0;
					if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'")) > 0){
						//echo "<h2>Username</h2>";
						$result = mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'");
						$num += mysqli_num_rows($result);
					}
					
					if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'"))>0){
						//echo "<h2>Category</h2>";
						$result = mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'");
						$num += mysqli_num_rows($result);
					}
					
					if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'"))>0){
						//echo "<h2>Task</h2>";
						$result = mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'");
						$num += mysqli_num_rows($result);
					}
					
					if(mysqli_num_rows(mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%$string%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id"))>0){
						if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'"))==0){
							//echo "<h2>Task</h2>";
						}
						$result = mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%$string%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id");
						$num += mysqli_num_rows($result);
					}
				}
				
				// Lets set how many messages we want to display
				$per_page = "1";
				
				// Now we must calculate the last page
				$last_page = ceil($num/$per_page);
				
				// And set the first page
				$first_page = "1";
				
				// Here we are making the "First page" link
				echo "<a href='?m=".$mode."&s=".$string."&page=".$first_page."'>First page</a> ";
				
				// If page is 1 then remove link from "Previous" word
				if($page == $first_page){
					
					echo "Previous ";
					
				}else{
					
					if(!isset($page)){
						
						echo "Previous ";
						
					}else{
						
						// But if page is set and it's not 1.. Lets add link to previous word to take us back by one page
						$previous = $page-1;
						echo "<a href='?m=".$mode."&s=".$string."&page=".$previous."'>Previous</a> ";
					}
					
				}
				
				// If the page is last page.. lets remove "Next" link
				if($page == $last_page){
					
					echo "Next ";	
					
				}else{
					
					// If page is not set or it is set and it's not the last page.. lets add link to this word so we can go to the next page
					if(!isset($page)){
						
						$next = $first_page+1;
						echo "<a href='?m=".$mode."&s=".$string."&page=".$next."'>Next</a> ";
					}else{
					
						$next = $page+1;
						echo "<a href='?m=".$mode."&s=".$string."&page=".$next."'>Next</a> ";
					
					}
					
				}
				
				// And now lets add the "Last page" link<h4></h4>
				echo "<a href='?m=".$mode."&s=".$string."&page=".$last_page."'>Last page</a>";

				// Math.. It gets us the start number of message that will be displayed
				$start = ($page-1)*$per_page;
				$calculate = ($page * $per_page)-$per_page;
				$finish = $page * $per_page;
				
				// Now lets set the limit for our query
				$limit = "LIMIT $start, $per_page";

				if($mode=='1'){
					echo "<h2>Username</h2>";
					$result = mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%' $limit");
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
					echo "<h2>Category</h2>";
					$result = mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%' $limit");
						
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
					echo "<h2>Task</h2>";
					$result = mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%' $limit");
					while($row = mysqli_fetch_array($result))
					{
					  $temp = $row['task_id'];
					  echo "<div class = 'tugas' id  = '$temp'>";
					  echo $isi_open;
					  echo $row['task_name'];
					  
					  echo $isi_close;
					  echo "<div>";
					  echo "Deadline : ";
					  echo $row['deadline'];
					  echo "</div>";
					  echo "<div>";
					  echo "Tag : ";
					  $subresult = mysqli_query($con,"SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '$temp' and tag.tag_id = tasktag.tag_id");
				  		while($subrow = mysqli_fetch_array($subresult))
						{
							echo $subrow['tag_name'];
							echo " ";
						}
						  $parameter = "'".$temp."'";
						  $function = "change(".$parameter.")";
						  echo "</div>";
						  echo "<div>";
						  echo "Status : ";
						  if ($row['status']=='1'){
							  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' checked='checked' onchange=$function>";
							  echo "sudah selesai";
						  }else{
							  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' onchange=$function>";
							  echo "belum selesai";
						  }
					  echo "</div>";
					  echo $isi_close;
					}
				}else if ($mode=='4'){
					echo "<h2>Tag</h2>";
					$result = mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%$string%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id $limit");
					while($row = mysqli_fetch_array($result))
					{
					  $temp = $row['task_id'];
					  echo "<div class = 'tugas' id  = '$temp'>";
					  echo $isi_open;
					  echo $row['task_name'];
					  $parameter = "'".$temp."'";
			  		  $function = "change(".$parameter.")";
					  echo $isi_close;
					  echo "<div>";
					  echo "Deadline : ";
					  echo $row['deadline'];
					  echo "</div>";
					  echo "<div>";
					  echo "Tag : ";
					  $subresult = mysqli_query($con,"SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '$temp' and tag.tag_id = tasktag.tag_id");
					  
						while($subrow = mysqli_fetch_array($subresult))
						{
							echo $subrow['tag_name'];
							echo " ";
						}
					 
					  echo "</div>";
					  echo "<div>";
					  echo "Status : ";
					  if ($row['status']=='1'){
						  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' checked='checked' onchange=$function>";
						  echo "sudah selesai";
					  }else{
						  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' onchange=$function>";
						  echo "belum selesai";
					  }
					  echo "</div>";
					  echo $isi_close;
					}
				}else{
					$allresult = array();
					if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'")) > 0){
							
							$result = mysqli_query($con,"SELECT * FROM user WHERE username LIKE '%$string%'");
							while($row = mysqli_fetch_array($result))
							{
							  $allresult[]=$row['username'];
							}
							
					}
					
						if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'"))>0){
								$result = mysqli_query($con,"SELECT * FROM category WHERE category_name LIKE '%$string%'");
								while($row = mysqli_fetch_array($result))
								{
								  $allresult[] = "C".$row['category_id'];
								}
								
							
						}
						
					if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'"))>0){
							$result = mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'");
							while($row = mysqli_fetch_array($result))
							{
							  $allresult[] = "T".$row['task_id'];
							}
						
					}
					
					if(mysqli_num_rows(mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%$string%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id"))>0){
						if(mysqli_num_rows(mysqli_query($con,"SELECT * FROM task WHERE task_name LIKE '%$string%'"))==0){
							echo "<h2>Task</h2>";
						}
						
							$result = mysqli_query($con,"SELECT task_name FROM task, tag, tasktag WHERE tag.tag_name LIKE '%$string%' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id");
							while($row = mysqli_fetch_array($result))
							{
								 $allresult[] = "G".$row['task_name'];
							}
					}
					
					$i = $start;
					while ($i<$finish){
							
							if (mysqli_num_rows(mysqli_query($con,"SELECT * FROM user WHERE username = '".$allresult[$i]."' "))>0){
								echo "<h2>Username</h2>";
								$result = mysqli_query($con,"SELECT * FROM user WHERE username = '".$allresult[$i]."' ");
							
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
						
						if (substr($allresult[$i],0,1)=='C'){
							echo substr($allresult[$i],1);
						if (mysqli_num_rows(mysqli_query($con,"SELECT * FROM category WHERE category_id = '".substr($allresult[$i],1)."'"))>0){
							echo "<h2>Category</h2>";	
							$result = mysqli_query($con,"SELECT * FROM category WHERE category_id = '".substr($allresult[$i],1)."'");
								while($row = mysqli_fetch_array($result))
								{
								  $temp = $row['category_name'];
								  echo $outside;
								  echo $isi_open;
								  echo $temp;
								  echo $isi_close;
								  echo $isi_close;
								  //$allresult[] = $row['category_name'];
								}
						}
						}
						
						if (substr($allresult[$i],0,1)=='T'){
						if (mysqli_num_rows(mysqli_query($con,"SELECT * FROM task WHERE task_id = '".substr($allresult[$i],1)."'"))>0){	
							echo "<h2>Taskname</h2>";
						$result = mysqli_query($con,"SELECT * FROM task WHERE task_id = '".substr($allresult[$i],1)."'");
							while($row = mysqli_fetch_array($result))
							{
							  $temp = $row['task_id'];
							  echo "<div class = 'tugas' id  = '$temp'>";
							  echo $isi_open;
							  echo $row['task_name'];
							  
							  echo $isi_close;
							  echo "<div>";
							  echo "Deadline : ";
							  echo $row['deadline'];
							  echo "</div>";
							  echo "<div>";
							  echo "Tag : ";
							  $subresult = mysqli_query($con,"SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '$temp' and tag.tag_id = tasktag.tag_id");
								while($subrow = mysqli_fetch_array($subresult))
								{
									echo $subrow['tag_name'];
									echo " ";
								}
								  $parameter = "'".$temp."'";
								  $function = "change(".$parameter.")";
								  echo "</div>";
								  echo "<div>";
								  echo "Status : ";
								  if ($row['status']=='1'){
									  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' checked='checked' onchange=$function>";
									  echo "sudah selesai";
								  }else{
									  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' onchange=$function>";
									  echo "belum selesai";
								  }
							  echo "</div>";
							  echo $isi_close;
							}
						}
						}
						if (substr($allresult[$i],0,1)=='G'){
						if (mysqli_num_rows(mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE task.task_name = '".substr($allresult[$i],1)."' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id LIMIT $per_page"))>0){	
						echo "<h2>Tag</h2>";
						$result = mysqli_query($con,"SELECT task.task_id, task.task_name, task.deadline, task.status, tag.tag_name FROM task, tag, tasktag WHERE task.task_name = '".substr($allresult[$i],1)."' and task.task_id = tasktag.task_id and tag.tag_id = tasktag.tag_id LIMIT $per_page");
							while($row = mysqli_fetch_array($result))
							{
								$temp = $row['task_id'];
						  		echo "<div class = 'tugas' id  = '$temp'>";
								echo $isi_open;
								echo $row['task_name'];
								echo $isi_close;
								echo "<div>";
								echo "Deadline : ";
								echo $row['deadline'];
								echo "</div>";
								echo "<div>";
								echo "Tag : ";
								$subresult = mysqli_query($con,"SELECT tag.tag_name FROM tag,tasktag WHERE tasktag.task_id = '$temp' and tag.tag_id = tasktag.tag_id");
							  
								while($subrow = mysqli_fetch_array($subresult))
								{
									echo $subrow['tag_name'];
									echo " ";
								}
							 $parameter = "'".$temp."'";
							  $function = "change(".$parameter.")";
								echo "</div>";
								echo "<div>";
								echo "Status : ";
								if ($row['status']=='1'){
								  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' checked='checked' onchange=$function>";
								  echo "sudah selesai";
								}else{ 
								  echo "<input id='checkbox_".$temp."' value = '".$temp."' type='checkbox' onchange=$function>";
								  echo "belum selesai";
								}
								echo "</div>";
								echo $isi_close;
								// $allresult[] = $row['tag_id'];
							}
						}
						}
							
						$i++;
					}
					
					
				}
				
				mysqli_close($con);
	   	?>