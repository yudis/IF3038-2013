<?php
	require '../utilities/db.php';
	require '../utilities/model.php';
	require '../utilities/view.php';
	require '../models/tugas.php';
	$q=$_GET["q"];
	$tugas = new Tugas();
	if($q=="")
	{
		$i=1;
		$success[$i] = $tugas->getTugas($i);
		while (!empty($success[$i]))
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
			echo "</div>";
			$i++;
			$tugas = new Tugas();
			$success[$i] = $tugas->getTugas($i);
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
				echo "</div>";
			}
			$i++;
			$tugas = new Tugas();
			$success[$i] = $tugas->getTugas($i);
		}
	}
?>