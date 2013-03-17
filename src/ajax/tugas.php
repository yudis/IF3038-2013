<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/tugas.php';
	$q=$_GET["q"];
	//$_SESSION['user']["username"]=$_GET["n"];
	//echo json_encode($_SESSION['user']["username"]);
	$tugas = new Tugas();
	if($q=="")
	{
		$i=1;
		$success[$i] = $tugas->getTugas($i);
		while (!empty($success[$i]))
		{
			//if($success[$i]["pemilik"]==$_SESSION['user']["username"])
			//{
				echo "<h2>",$success[$i]["kategori"],"</h2>";
				echo "<div class=\"tugas\">";
					echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
					echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
					echo "<div>";
						echo "Tags: ";
						echo "<ul class=\"tag\">";
							$n=0;
							while(!empty($success[$i]["tag"][$n]))
							{
								echo "<li>",$success[$i]["tag"][$n],"</li>";
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
				$i++;
				$tugas = new Tugas();
				$success[$i] = $tugas->getTugas($i);
			//}
			/* else
			{
				$x=0;
				while(!empty($success[$i]["asignee"][$x]))
				{
					if($success[$i]["asignee"][$x]==$_SESSION['user']["username"])
					{
						echo "<h2>",$success[$i]["kategori"],"</h2>";
						echo "<div class=\"tugas\">";
							echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
							echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
							echo "<div>";
								echo "Tags: ";
								echo "<ul class=\"tag\">";
									$n=0;
									while(!empty($success[$i]["tag"][$n]))
									{
										echo "<li>",$success[$i]["tag"][$n],"</li>";
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
						$i++;
						$tugas = new Tugas();
						$success[$i] = $tugas->getTugas($i);
					}
				}
			} */
		}
	}
	else
	{
		$i=1;
		$success[$i] = $tugas->getTugas($i);
		while (!empty($success[$i]))
		{
			if($success[$i]["id_kategori"]==$q)
			{
				//if($success[$i]["pemilik"]==$_SESSION['user']["username"])
				//{
					echo "<h2>",$success[$i]["kategori"],"</h2>";
					echo "<div class=\"tugas\">";
						echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
						echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
						echo "<div>";
							echo "Tags: ";
							echo "<ul class=\"tag\">";
								$n=0;
								while(!empty($success[$i]["tag"][$n]))
								{
									echo "<li>",$success[$i]["tag"][$n],"</li>";
									$n++;
								}
							echo "</ul>";
						echo "</div>";
						if($success[$i]["status"]==0)
						{
							echo "<div>Status : <input id=\"stats\" type=\"checkbox\" name=\"status\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\"></div>";
						}
						else if($success[$i]["status"]==1)
						{
							echo "<div>Status : <input id=\"stats\" type=\"checkbox\" name=\"status\" onchange=\"updateStatus(this.value,",$success[$i]["id"],")\" value=\"",$success[$i]["status"],"\" checked></div>";
						}
					echo "</div>";
				/* }
				else
				{
					$x=0;
					while(!empty($success[$i]["asignee"][$x]))
					{
						if($success[$i]["asignee"][$x]==$_SESSION['user']["username"])
						{
							echo "<h2>",$success[$i]["kategori"],"</h2>";
							echo "<div class=\"tugas\">";
								echo "<div><a href=\"tugas.html?name=Tugas%20Besar%201%3A%20Algoritma%20Genetik&amp;deadline=2013-02-22&amp;tags=AI%2C%20Genetics%2C%20Algorithm\">",$success[$i]["nama"],"</a></div>";
								echo "<div>Deadline: <strong>",$success[$i]["tgl_deadline"],"</strong></div>";
								echo "<div>";
									echo "Tags: ";
									echo "<ul class=\"tag\">";
										$n=0;
										while(!empty($success[$i]["tag"][$n]))
										{
											echo "<li>",$success[$i]["tag"][$n],"</li>";
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
							$i++;
							$tugas = new Tugas();
							$success[$i] = $tugas->getTugas($i);
						}
					}
				} */
			}
			$i++;
			$tugas = new Tugas();
			$success[$i] = $tugas->getTugas($i);
		}
	}
?>