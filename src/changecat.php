
<?php
$id=$_GET["id"];

include "koneksi.php";
$cat="select * from tugas where idkat= '$id'";
$hasilcat=mysql_query($cat);
$i = 1;

echo "<div style=\"display:block;\" id=\"add_links\"><center><a href=\"addtask.php?idkat=".$id."\"><span class='link_blue'>Add Task</span></a></center></div>";
while($row=mysql_fetch_array($hasilcat)){
$idtask=$row['id'];
echo "<br>";
echo "<div class=\"task_view\" id=\"curtask".$i."\">";
$i++;
echo "<img src=\"../img/done.png\" id=\"finish_".$idtask."\" onclick=\"deletetask(".$idtask.")\" class=\"task_done_button\" alt=\" \"/>";
echo "<div id=\"task_name_ltd\" class=\"left dynamic_content_left\">Nama Task</div>";
echo "<div id=\"task_name_rtd\" class=\"left dynamic_content_right\">";
echo "<a href=\"detail.php?id=".$idtask."\"> ";
echo $row['namatask'];
echo "</a> </div> <br><br>";
echo "<div class=\"left dynamic_content_left\">Deadline</div><div class=\"left dynamic_content_right\">";
echo $row['deadline'];
echo "</div><br><br>";
echo "<div class=\"left dynamic_content_left\">Status</div><div id=\"status\" class=\"left dynamic_content_right\">";
if ($row['status'] == 1)
					{
						echo "Selesai";
					}
					else
					{
						echo "Belum Selesai";
					}
echo "</div>";
echo "<input id=\"edit_task_button\" class=\"changestat\" onclick=\"changestat(".$idtask.")\" type=\"button\" value=\"Ubah\">";
echo "<br><br>	<div class=\"left dynamic_content_left\">Tag</div> <div class=\"left dynamic_content_right\"> ";
$tag="select * from tag where idtask= '$idtask'";
					$hasiltag=mysql_query($tag);
					while($rowtag=mysql_fetch_array($hasiltag)){

					echo "<b>".$rowtag['namatag']." </b>";
					echo " | ";
					}
echo "</div>
					<br>
					<div class=\"task_view_category\"> 
					
					</div>
					<br>
				</div>";

}

?> 