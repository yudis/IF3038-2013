<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/tugas.php';
	session_start();
	$q=$_GET["q"];
	$tugas = new Tugas();
	if($q=="")
	{
		$i=0;
		$success = $tugas->getAllTugas();
		while (!empty($success[$i]))
		{
			if($success[$i]["pemilik"]==$_SESSION['user'])
			{
				echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
				echo "<div class=\"tugas\">";
					echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
					echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
					echo "<div>";
						echo "Tags: ";
						echo "<ul class=\"tag\">";
							$n=0;
							$tags=$tugas->getTags($success[$i]["id"]);
							while(!empty($tags[$n]))
							{
								echo "<li>",$tags[$n],"</li>";
								$n++;
							}
						echo "</ul>";
					echo "</div>";   
					if($success[$i]["status"]==0)
						{
							echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\"></div>";
						}
					else if($success[$i]["status"]==1)
						{
							echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\" checked></div>";
						}
					echo"<button id='deleteTugas' onclick='setChosenT(\"",$success[$i]["id"],"\");deleteTask()'>Delete Tugas</button>";
				echo "</div>";
			}
			else
			{
				$x=0;
				$r= $tugas->getAsignee2($success[$i]["id"]);
				while(!empty($r[$x]["username"]))
				{
					if($r[$x]["username"]==$_SESSION['user'] && $success[$i]["pemilik"] !=$r[$x]["username"])
					{
						echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
						echo "<div class=\"tugas\">";
							echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
							echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
							echo "<div>";
								echo "Tags: ";
								echo "<ul class=\"tag\">";
									$n=0;
									$tags=$tugas->getTags($success[$i]["id"]);
									while(!empty($tags[$n]))
									{
										echo "<li>",$tags[$n],"</li>";
										$n++;
									}
								echo "</ul>";
							echo "</div>";   
							if($success[$i]["status"]==0)
								{
									echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\"></div>";
								}
							else if($success[$i]["status"]==1)
								{
									echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\" checked></div>";
								}
						echo "</div>";
						
					}
					$x++;
				}
			} 
			$i++;
		}
	}
	else
	{
		$i=0;
		$success = $tugas->getAllTugas();
		while (!empty($success[$i]))
		{
			if($success[$i]["id_kategori"]==$q)
			{
				if($success[$i]["pemilik"]==$_SESSION['user'])
				{
					echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
					echo "<div class=\"tugas\">";
						echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
						echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
						echo "<div>";
							echo "Tags: ";
							echo "<ul class=\"tag\">";
								$n=0;
								$tags=$tugas->getTags($success[$i]["id"]);
								while(!empty($tags[$n]))
								{
									echo "<li>",$tags[$n],"</li>";
									$n++;
								}
							echo "</ul>";
						echo "</div>";   
						if($success[$i]["status"]==0)
							{
								echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\"></div>";
							}
						else if($success[$i]["status"]==1)
							{
								echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\" checked></div>";
							}
						echo"<button id='deleteTugas' onclick='setChosenT(\"",$success[$i]["id"],"\");deleteTask()'>Delete Tugas</button>";
					echo "</div>";
				}
				else
				{
					$x=0;
					$r= $tugas->getAsignee2($success[$i]["id"]);
					while(!empty($r[$x]["username"]))
					{
						if($r[$x]["username"]==$_SESSION['user'] && $success[$i]["pemilik"] !=$r[$x]["username"])
						{
							echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
							echo "<div class=\"tugas\">";
								echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
								echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
								echo "<div>";
									echo "Tags: ";
									echo "<ul class=\"tag\">";
										$n=0;
										$tags=$tugas->getTags($success[$i]["id"]);
										while(!empty($tags[$n]))
										{
											echo "<li>",$tags[$n],"</li>";
											$n++;
										}
									echo "</ul>";
								echo "</div>";   
								if($success[$i]["status"]==0)
									{
										echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\"></div>";
									}
								else if($success[$i]["status"]==1)
									{
										echo "<div>Status : <input id=\"stats\" type=\"checkbox\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\" checked></div>";
									}
							echo "</div>";
							
						}
						$x++;
					}
				} 
			}
			$i++;
		}
			
	}
	
?>