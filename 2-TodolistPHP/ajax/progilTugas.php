<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/tugas.php';
	$username=$_GET["username"];
	$tugas = new Tugas();
	$i=0;
	$success = $tugas->getAllTugas();
	if($success[$i]["status"]==0)
	{
		echo "<h2>Tugas yang belum selesai</h2>";
		while (!empty($success[$i]))
		{
			if($success[$i]["status"]==0)
			{
				if($success[$i]["pemilik"]==$username)
				{
					echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
					echo "<div class=\"tugas\">";
						echo "<div><a href=\"tugas.php?id=" . $success[$i]["id"] . "\">",$success[$i]["nama"],"</a></div>";
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
				else
				{
					$x=0;
					$r= $tugas->getAsignee2($success[$i]["id"]);
					while(!empty($r[$x]["username"]))
					{
						if($r[$x]["username"]==$username && $success[$i]["pemilik"] !=$r[$x]["username"])
						{
							echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
							echo "<div class=\"tugas\">";
							echo "<div><a href=\"tugas.php?id=" . $success[$i]["id"] . "\">",$success[$i]["nama"],"</a></div>";
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
	else if($success[$i]["status"]==1)
	{
	echo "<h2>Tugas yang sudah selesai</h2>";
		while (!empty($success[$i]))
		{
			if($success[$i]["status"]==0)
			{
				if($success[$i]["pemilik"]==$username)
				{
					echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
					echo "<div class=\"tugas\">";
						echo "<div><a href=\"tugas.php?id=" . $success[$i]["id"] . "\">",$success[$i]["nama"],"</a></div>";
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
				else
				{
					$x=0;
					$r= $tugas->getAsignee2($success[$i]["id"]);
					while(!empty($r[$x]["username"]))
					{
						if($r[$x]["username"]==$username && $success[$i]["pemilik"] !=$r[$x]["username"])
						{
							echo "<h2>",$success[$i]["nama_kategori"],"</h2>";
							echo "<div class=\"tugas\">";
							echo "<div><a href=\"tugas.php?id=" . $success[$i]["id"] . "\">",$success[$i]["nama"],"</a></div>";
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